package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.item.ItemSpectralChip;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Yari on 9/17/2014.
 */
public class TileSpectralAscender extends TileSpectralInventory
{
    public int activeX = 0;
    public int activeY = 0;
    public int activeZ = 0;
    public int activeLayer = 0;

    private int height = 8;
    private int width = 4;

    private int chargeNeeded = 20 * 60;
    private int chargeAmount = 0;

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
            if(tank.getFluidAmount() == 0)
            {
                tank.setFluid(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, 1000));
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

                    player.addChatMessage(new ChatComponentText("Installed the chip."));

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    this.markDirty();
                }else{
                    player.addChatMessage(new ChatComponentText("No more open sockets for the chip."));
                }
            }else{
                player.addChatMessage(new ChatComponentText("Can't install a chip while the miner is charging."));
            }
        }else{
            doStatusClick(player);
        }

        return true;
    }

    private void doStatusClick(EntityPlayer player)
    {
        if (tank.getFluidAmount() == 0)
        {
            player.addChatMessage(new ChatComponentText("No spectra in the miner."));
        } else
        {
            player.addChatMessage(new ChatComponentText("Fluid inside: " + String.valueOf(tank.getFluidAmount()) + "mb"));
        }

        player.addChatMessage(new ChatComponentText("Charge: " + chargeAmount + "/" + chargeNeeded));

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
        // Don't do anything on the clientside
        if (worldObj.isRemote) return;

        if (tank.getFluidAmount() != 0)
        {
            tank.drain(1, true);

            for (int i = 0; i < 10; i++)
            {
                activeX++;
                if (activeX > width * 2 + 1)
                {
                    activeX = 0;

                    activeZ++;
                    if (activeZ > width * 2 + 1)
                    {
                        activeZ = 0;

                        activeY++;
                        if (activeY > height + 4)
                        {
                            activeY = 0;
                        }
                    }
                }

                if (yCoord + height - activeY < 6) return;
                if (worldObj.getTileEntity(xCoord - width + activeX, yCoord + height - activeY + 2, zCoord - width + activeZ) != null)
                    return;
                if (worldObj.getTileEntity(xCoord - width + activeX, yCoord + height - activeY + 2 - 1, zCoord - width + activeZ) != null)
                    return;

                Block activeBlock = worldObj.getBlock(xCoord - width + activeX, yCoord + height - activeY + 2, zCoord - width + activeZ);
                Block lowerBlock = worldObj.getBlock(xCoord - width + activeX, yCoord + height - activeY + 2 - 1, zCoord - width + activeZ);

                if (activeBlock instanceof BlockAir && !(lowerBlock instanceof BlockAir))
                {
                    worldObj.setBlock(xCoord - width + activeX, yCoord + height - activeY + 2, zCoord - width + activeZ, lowerBlock);
                    worldObj.setBlock(xCoord - width + activeX, yCoord + height - activeY + 2 - 1, zCoord - width + activeZ, Blocks.air);
                }
            }
        }
    }
}
