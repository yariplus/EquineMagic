package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.api.tileentity.ITileSpectralManipulator;
import com.yaricraft.equinemagic.enums.ESpectralManipulator;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.item.IItemSpectralChip;
import com.yaricraft.equinemagic.item.ItemSpectralChip;
import com.yaricraft.equinemagic.reference.MCData;
import com.yaricraft.equinemagic.reference.ModData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
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

    public ESpectralManipulator type = ESpectralManipulator.MINER;
    public ForgeDirection storageDirection = ForgeDirection.UNKNOWN;

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
                    if (storageDirection == ForgeDirection.UNKNOWN)
                    {
                        checkForStorage();
                    } else
                    {
                        shoot();
                        shoot();
                        shoot();
                        tank.drain(1, true);
                    }
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

    public void checkForStorage()
    {
        for (ForgeDirection dir : ForgeDirection.values())
        {
            if (dir != ForgeDirection.UNKNOWN && worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ ) instanceof IInventory)
            {
                storageDirection = dir;
                return;
            }
        }
    }

    @Override
    public void shoot() { return; }

    @Override
    public void mineBlock(int x, int y, int z, Block filler)
    {
        Block minedBlock = worldObj.getBlock(x, y, z);
        int minedBlockMeta = worldObj.getBlockMetadata(x, y, z);
        TileEntity minedBlockTile = worldObj.getTileEntity(x, y, z);

        Item dropItem = minedBlock.getItemDropped( minedBlockMeta, new Random(), 0 );
        int dropMeta = minedBlock.damageDropped(minedBlockMeta);
        int dropQuantity = minedBlock.quantityDropped( minedBlockMeta, 0, new Random() );
        ItemStack dropStack = new ItemStack(dropItem, dropQuantity, dropMeta);

        // Can we replace the Block?
        if (minedBlockTile == null && !(minedBlock == Blocks.bedrock))
        {
            // Replace the Block.
            for (int i = 0; i < itemStacks.length; i++)
            {
                if (itemStacks[i] != null) filler = ((IItemSpectralChip) itemStacks[i].getItem()).replaceBlock(itemStacks[i].getItemDamage(), filler);
            }
            worldObj.setBlock( x, y, z, filler);

            // Can we harvest the Block?
            if (!worldObj.isRemote && !(minedBlock instanceof BlockLiquid || minedBlock instanceof BlockAir))
            {
                // Harvest the Block.
                // Get the itemStack dropped.
                ItemStack newStack = null;
                for (int i = 0; i < itemStacks.length; i++)
                {
                    if (itemStacks[i] != null) newStack = ((IItemSpectralChip) itemStacks[i].getItem()).alterDrop(itemStacks[i].getItemDamage(), minedBlock, minedBlockMeta, dropItem, dropQuantity, dropMeta);
                    if (newStack != null)
                    {
                        dropItem = newStack.getItem();
                        dropStack = newStack;
                        i = itemStacks.length;
                    }
                }

                // Grass and shrubs will be null.
                if (dropItem != null)
                {
                    // Check for storage.
                    if (storageDirection != ForgeDirection.UNKNOWN)
                    {
                        TileEntity storageTE = worldObj.getTileEntity(xCoord + storageDirection.offsetX, yCoord + storageDirection.offsetY, zCoord + storageDirection.offsetZ);
                        if (storageTE instanceof IInventory)
                        {
                            for (int i = 0; i < ((IInventory) storageTE).getSizeInventory(); i++)
                            {
                                int sizeInvStack = 0;
                                if (((IInventory) storageTE).getStackInSlot(i) != null)
                                {
                                    if (((IInventory) storageTE).getStackInSlot(i).getItem() == dropItem && ((IInventory) storageTE).getStackInSlot(i).getItemDamage() == dropMeta && ((IInventory) storageTE).isItemValidForSlot(i, dropStack))
                                    {
                                        //IInventoryHandler
                                        sizeInvStack = ((IInventory) storageTE).getStackInSlot(i).stackSize;

                                        int totalSize = dropQuantity + sizeInvStack;
                                        if (totalSize <= 64)
                                        {
                                            ((IInventory) storageTE).getStackInSlot(i).stackSize = totalSize;
                                            return;
                                        } else
                                        {
                                            ((IInventory) storageTE).getStackInSlot(i).stackSize = 64;
                                            dropQuantity -= 64 - sizeInvStack;
                                        }
                                    }
                                } else
                                {
                                    if (((IInventory) storageTE).isItemValidForSlot(i, dropStack))
                                    {
                                        if (dropQuantity <= 64)
                                        {
                                            ((IInventory) storageTE).setInventorySlotContents(i, new ItemStack(dropItem, dropQuantity, dropMeta));
                                            return;
                                        } else
                                        {
                                            ((IInventory) storageTE).setInventorySlotContents(i, new ItemStack(dropItem, 64, dropMeta));
                                            dropQuantity -= 64;
                                        }
                                    }
                                }
                            }
                        } else
                        {
                            storageDirection = ForgeDirection.UNKNOWN;
                        }
                    }


                    worldObj.spawnEntityInWorld(
                            new EntityItem(worldObj,
                                    xCoord + 0.5D,
                                    yCoord + 1.5D,
                                    zCoord + 0.5D,
                                    dropStack));
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
