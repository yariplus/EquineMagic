package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.api.tileentity.ITileSpectralManipulator;
import com.yaricraft.equinemagic.enums.ESpectralManipulator;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.item.IItemSpectralChip;
import com.yaricraft.equinemagic.item.ItemSpectralChip;
import com.yaricraft.equinemagic.reference.MCData;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Yari on 11/6/2014.
 *
 * A Manipulator changes blocks based on a pattern and upgrades installed.
 */
public abstract class TileSpectralManipulator extends TileSpectralInventory implements ITileSpectralManipulator
{
    public ArrayList<ArrayList<ArrayList<Integer>>> patterns = new ArrayList<ArrayList<ArrayList<Integer>>>();
    public ArrayList<Block> fillers = new ArrayList<Block>();
    public ArrayList<ItemStack> heldItems = new ArrayList<ItemStack>();

    public ESpectralManipulator type = ESpectralManipulator.MINER;

    public ForgeDirection connectedDirection = ForgeDirection.NORTH;
    private int connectedSlot = 0;
    boolean useEmptySlots = false;

    protected int workColumn = -1;
    protected int workRow = 0;
    protected int workPattern = 0;
    protected int workLayer = 0;

    public int depth = 64;

    public int chargeNeeded = MCData.TICKS_PER_SECOND * ModData.CHARGE_SECONDS;
    protected int chargeAmount = 0;

    public int hOffset = 0;
    public int wOffset = 0;
    public int dOffset = 0;

    @Override
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        // Don't do anything on the clientside
        if (worldObj.isRemote) return true;

        if(player.getHeldItem() == null)
        {
            doStatusClick(player);
            return true;
        }

        ItemStack heldStack = player.getHeldItem();
        Item heldItem = heldStack.getItem();

        if(heldItem == EquineMagicFluid.itemBucketSpectraSlurry)
        {
            if(tank.fill(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, 1000), false) == 1000)
            {
                tank.fill(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, 1000), true);
                player.inventory.getCurrentItem().stackSize--;
                player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
                player.addChatMessage(new ChatComponentText("Placed spectra in the device."));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }
        }else if(heldItem instanceof ItemSpectralChip)
        {
            if(chargeAmount == 0)
            {
                int openSocket = -1;
                if(itemStacks[2] == null) openSocket = 2;
                if(itemStacks[1] == null) openSocket = 1;
                if(itemStacks[0] == null) openSocket = 0;

                if (openSocket != -1)
                {
                    itemStacks[openSocket] = new ItemStack(heldItem, 1, heldStack.getItemDamage());
                    player.inventory.getCurrentItem().stackSize--;
                    ((ItemSpectralChip)heldItem).install(heldStack.getItemDamage(), this);

                    player.addChatMessage(new ChatComponentText("Installed the chip."));

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    this.markDirty();
                }else{
                    player.addChatMessage(new ChatComponentText("No more open sockets for the chip."));
                }
            }else{
                player.addChatMessage(new ChatComponentText("Can't install a chip while the miner is active."));
            }
        }else{
            doStatusClick(player);
        }

        return true;
    }

    @Override
    public void doStatusClick(EntityPlayer player)
    {
        if (tank.getFluidAmount() == 0)
        {
            player.addChatMessage(new ChatComponentText("No spectra in the miner."));
        } else
        {
            player.addChatMessage(new ChatComponentText("Fluid inside: " + String.valueOf(tank.getFluidAmount()) + "mb"));
        }

        if (chargeAmount == chargeNeeded)
        {
            player.addChatMessage(new ChatComponentText("Device is active."));
        }else
        {
            player.addChatMessage(new ChatComponentText("Needs to see the sky and 1000mb of Spectra to start."));
        }

        if (itemStacks[2] != null)
        {
            player.addChatMessage(new ChatComponentText("Three upgrade chips are inside."));
        }else if (itemStacks[1] != null)
        {
            player.addChatMessage(new ChatComponentText("Two upgrade chips are inside."));
        }else if (itemStacks[0] != null)
        {
            player.addChatMessage(new ChatComponentText("An upgrade chip is inside."));
        }
    }

    @Override
    public void updateEntity()
    {
        if (!worldObj.isRemote)
        {
            // Server
            if (worldObj.canBlockSeeTheSky(xCoord, yCoord + 5, zCoord))
            {
                if (chargeAmount >= chargeNeeded && tank.getFluidAmount() >= 1)
                {
                    if (!heldItems.isEmpty())
                    {
                        storeItems();
                    }
                    if (heldItems.size() < 6)
                    {
                        shoot();
                        shoot();
                        shoot();
                    }

                    tank.drain(1, true);
                } else
                {
                    if (tank.getFluidAmount() >= 1000)
                    {
                        if (chargeAmount < chargeNeeded) chargeAmount++;
                    } else
                    {
                        if (chargeAmount > 0) chargeAmount--;
                    }
                }
            }else
            {
                chargeAmount = 0;
            }
        }else
        {
            // Client
        }
    }

    public void storeItems()
    {
        connectedSlot = 0;

        // Check for storage.
        IInventory connectedInventory = getConnectedInventory();
        if (connectedInventory == null) return;

        if (connectedInventory instanceof ISidedInventory)
        {
            int[] sidedSlots = ((ISidedInventory) connectedInventory).getAccessibleSlotsFromSide(connectedDirection.getOpposite().ordinal());
            if (!(sidedSlots.length > 0))
            {
                switchDirection();
                return;
            }else
            {
                storeSided(connectedInventory, sidedSlots);
            }
        }else
        {
            storeInventory(connectedInventory);
        }
    }

    private void storeSided(IInventory connectedInventory, int[] sidedSlots)
    {
        // Check 200 times for a free slot, or until reached end of sidedSlots.length.
        boolean doStore = false;
        storageCheck:
        for (int i = 0; i < sidedSlots.length; i++)
        {
            connectedSlot = sidedSlots[i];

            // Get the slot contents.
            ItemStack connectedItemStack = connectedInventory.getStackInSlot(connectedSlot);

            // See if it's empty.
            if (connectedItemStack == null && connectedInventory.isItemValidForSlot(connectedSlot, heldItems.get(0)))
            {
                //if (useEmptySlots == true)
                //{
                    doStore = true;
                    break storageCheck;
                //}
            }else
            {
                if (connectedInventory.isItemValidForSlot(connectedSlot, heldItems.get(0)) &&
                    connectedItemStack.getItem() == heldItems.get(0).getItem() &&
                    connectedItemStack.stackSize < connectedItemStack.getMaxStackSize() &&
                    connectedItemStack.getItemDamage() == heldItems.get(0).getItemDamage())
                {
                    doStore = true;
                    break storageCheck;
                }
            }
        }

        if (doStore)
        {
            storeStack(connectedInventory);
        }else
        {
            switchDirection();
        }
    }

    private void storeInventory(IInventory connectedInventory)
    {
        // Check 200 times for a free slot, or until reached end of inventory.
        boolean doStore = false;
        storageCheck:
        for (int _try = 0; _try < 200; _try++)
        {
            // Get the slot contents.
            ItemStack connectedItemStack = connectedInventory.getStackInSlot(connectedSlot);

            // See if it's empty.
            if (connectedItemStack == null && connectedInventory.isItemValidForSlot(connectedSlot, heldItems.get(0)))
            {
                doStore = true;
                break storageCheck;
            }else
            {
                if (connectedInventory.isItemValidForSlot(connectedSlot, heldItems.get(0)) &&
                    connectedItemStack.getItem() == heldItems.get(0).getItem() &&
                    connectedItemStack.stackSize < connectedItemStack.getMaxStackSize() &&
                    connectedItemStack.getItemDamage() == heldItems.get(0).getItemDamage())
                {
                    doStore = true;
                    break storageCheck;
                }
            }

            // Return if the increment switches the direction.
            if (!incrementSlot(connectedInventory.getSizeInventory())) return;
        }

        if (doStore) storeStack(connectedInventory);
    }

    private void storeStack(IInventory connectedInventory)
    {
        ItemStack connectedStack = connectedInventory.getStackInSlot(connectedSlot);
        if (connectedStack == null)
        {
            connectedInventory.setInventorySlotContents(connectedSlot, heldItems.get(0));
            heldItems.remove(0);
        }else
        {
            if (connectedStack.stackSize + heldItems.get(0).stackSize <= connectedStack.getMaxStackSize())
            {
                int newsize = connectedStack.stackSize + heldItems.get(0).stackSize;
                connectedInventory.setInventorySlotContents(connectedSlot, new ItemStack(heldItems.get(0).getItem(), newsize, heldItems.get(0).getItemDamage()));
                heldItems.remove(0);
            }else
            {
                int store = connectedStack.getItem().getItemStackLimit(connectedStack) - connectedStack.stackSize;
                int hold = heldItems.get(0).stackSize - store;
                connectedInventory.getStackInSlot(connectedSlot).stackSize = connectedStack.getItem().getItemStackLimit(connectedStack);
                heldItems.get(0).stackSize = hold;
            }
        }
    }

    private IInventory getConnectedInventory()
    {
        validateDirection();
        TileEntity connectedTE = worldObj.getTileEntity(xCoord + connectedDirection.offsetX, yCoord + connectedDirection.offsetY, zCoord + connectedDirection.offsetZ);
        if (connectedTE == null || !(connectedTE instanceof IInventory) || ((IInventory)connectedTE).getSizeInventory() == 0)
        {
            switchDirection();
            connectedSlot = 0;
            return null;
        }
        return (IInventory)connectedTE;
    }

    private boolean validateDirection()
    {
        if (connectedDirection == ForgeDirection.NORTH || connectedDirection == ForgeDirection.EAST || connectedDirection == ForgeDirection.SOUTH || connectedDirection == ForgeDirection.WEST) return true;
        LogHelper.error("A manipulator at " +xCoord+ "," +yCoord+ "," +zCoord+ " was set to an invalid storage direction. (" +connectedDirection.toString()+ ")");
        connectedDirection = ForgeDirection.NORTH;
        return false;
    }

    private boolean incrementSlot(int inventorySize)
    {
        connectedSlot++;
        if (connectedSlot >= inventorySize)
        {
            connectedSlot = 0;
            switchDirection();
            return false;
        }

        return true;
    }

    private void switchDirection()
    {
        switch (connectedDirection)
        {
            case NORTH: connectedDirection = ForgeDirection.EAST; break;
            case EAST: connectedDirection = ForgeDirection.SOUTH; break;
            case SOUTH: connectedDirection = ForgeDirection.WEST; break;
            case WEST: connectedDirection = ForgeDirection.NORTH; break;
            default:
                LogHelper.error("A manipulator at " +xCoord+ "," +yCoord+ "," +zCoord+ " was set to an invalid storage direction. (" +connectedDirection.toString()+ ")");
                connectedDirection = ForgeDirection.NORTH;
                break;
        }

        LogHelper.info("Changed direction to " +connectedDirection.toString() );
    }

    @Override
    public void shoot() { return; }

    @Override
    public void mineBlock(int x, int y, int z, Block filler)
    {
        Block minedBlock = worldObj.getBlock(x, y, z);
        int minedBlockMeta = worldObj.getBlockMetadata(x, y, z);
        TileEntity minedBlockTile = worldObj.getTileEntity(x, y, z);

        // getItemDropped( meta, random, fortune );
        // returns null for shurbs etc.
        Item dropItem = minedBlock.getItemDropped( minedBlockMeta, new Random(), 0 );
        int dropMeta = minedBlock.damageDropped(minedBlockMeta);
        int dropQuantity = minedBlock.quantityDropped( minedBlockMeta, 0, new Random() );
        ItemStack dropStack = new ItemStack(dropItem, dropQuantity, dropMeta);

        // Can we replace the Block?
        if (minedBlockTile == null && minedBlock != Blocks.bedrock && !(minedBlock instanceof BlockAir))
        {
            // Replace the Block.
            for (int i = 0; i < itemStacks.length; i++)
            {
                if (itemStacks[i] != null) filler = ((IItemSpectralChip) itemStacks[i].getItem()).replaceBlock(itemStacks[i].getItemDamage(), filler);
            }
            worldObj.setBlock( x, y, z, filler);

            // Can we harvest the Block?
            if (!worldObj.isRemote && !(minedBlock instanceof BlockLiquid))
            {
                /*for (int i = 0; i < itemStacks.length; i++)
                {
                    if (itemStacks[i] != null) newStack = ((IItemSpectralChip) itemStacks[i].getItem()).alterDrop(itemStacks[i].getItemDamage(), minedBlock, minedBlockMeta, dropItem, dropQuantity, dropMeta);
                    if (newStack != null)
                    {
                        dropItem = newStack.getItem();
                        //dropStack = newStack;
                        i = itemStacks.length;
                    }
                }*/

                // Grass and shrubs will be null.
                if (dropItem != null)
                {
                    heldItems.add(dropStack);
                }
            }
        }
    }

    public void increaseHarvestWidth() {}
    public void increaseHarvestHeight() {}
    public void increaseHarvestArea() {}
    public void increaseHarvestDepth() {}

    // IInventory
    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        return null;
    }
}
