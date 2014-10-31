package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.item.ItemDustPegagin;
import com.yaricraft.equinemagic.item.ItemDustSpectra;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fluids.*;

/**
 * Created by Yari on 9/17/2014.
 */
public class TileSpectralCauldron extends TileSpectralInventory
{
    public int cooktime = 0;

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
                player.addChatMessage(new ChatComponentText("Placed spectra in the cauldron."));

                tank.setFluid(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, 1000));
                player.inventory.getCurrentItem().stackSize--;
                player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else
            {
                player.addChatMessage(new ChatComponentText("Not enough room for the bucket."));
            }
        }else if(heldItem == Items.bucket)
        {
            if(tank.getFluidAmount() == 1000)
            {
                tank.drain(1000, true);
                player.inventory.getCurrentItem().stackSize--;
                player.inventory.addItemStackToInventory(new ItemStack(EquineMagicFluid.itemBucketSpectraSlurry));
                player.addChatMessage(new ChatComponentText("Filled bucket with spectra slurry from the cauldron."));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else
            {
                player.addChatMessage(new ChatComponentText("Not enough slurry in the cauldron."));
            }
        }else if(heldItem == EquineMagicItem.dustPegagin)
        {
            if(itemStacks[0] == null)
            {
                itemStacks[0] = new ItemStack(EquineMagicItem.dustPegagin, 1);
                player.inventory.getCurrentItem().stackSize--;
                cooktime = 400;
                player.addChatMessage(new ChatComponentText("Placed dust in cauldron."));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else if(itemStacks[0].getItem() == EquineMagicItem.dustPegagin)
            {
                itemStacks[0].stackSize++;
                player.inventory.getCurrentItem().stackSize--;
                cooktime = 400;
                player.addChatMessage(new ChatComponentText("Placed dust in cauldron."));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else if(itemStacks[0].stackSize == itemStacks[0].getMaxStackSize())
            {
                player.addChatMessage(new ChatComponentText("The cauldron is full."));
            }
        }else if(heldItem == EquineMagicItem.dustSpectra)
        {
            if(itemStacks[0] == null)
            {
                itemStacks[0] = new ItemStack(EquineMagicItem.dustSpectra, 1);
                player.inventory.getCurrentItem().stackSize--;
                cooktime = 100;
                player.addChatMessage(new ChatComponentText("Placed dust in cauldron."));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else if(itemStacks[0].getItem() == EquineMagicItem.dustSpectra)
            {
                itemStacks[0].stackSize++;
                player.inventory.getCurrentItem().stackSize--;
                cooktime = 100;
                player.addChatMessage(new ChatComponentText("Placed dust in cauldron."));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else if(itemStacks[0].stackSize == itemStacks[0].getMaxStackSize())
            {
                player.addChatMessage(new ChatComponentText("The cauldron is full."));
            }
        }else
        {
            doStatusClick(player);
        }

        return true;
    }

    private void doStatusClick(EntityPlayer player)
    {
        if(tank.getFluidAmount() == 0)
        {
            player.addChatMessage(new ChatComponentText("No spectra in the cauldron."));
        }else
        {
            player.addChatMessage(new ChatComponentText("The cauldron contains " + tank.getFluidAmount() + "mb spectra slurry"));
        }

        if (itemStacks[0] == null)
        {
            player.addChatMessage(new ChatComponentText("No dusts in the cauldron."));
        }else
        {
            player.addChatMessage(new ChatComponentText("The cauldron contains " + itemStacks[0].stackSize + " " + "melting dusts" + "."));
            // player.addChatMessage(new ChatComponentText("Cooktime remaining is " + cooktime + "."));
        }
    }

    @Override
    public void updateEntity()
    {
        if(itemStacks[0] != null)
        {
            if (cooktime > 0) cooktime--;

            int liquid = this.tank.getFluidAmount();

            if (cooktime <= 0 && liquid <= 750)
            {
                this.decrStackSize(0, 1);
                this.tank.setFluid(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, liquid + 250));
            }
        }
    }

    private void setCooktime()
    {
        if(itemStacks[0] != null)
        {
            if (itemStacks[0].getItem() instanceof ItemDustSpectra) { cooktime = 100; } else
            if (itemStacks[0].getItem() instanceof ItemDustPegagin) { cooktime = 400; }
        }
    }

    // IInventory
    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (slot == 0)
        {
            if (itemStack.getItem() == EquineMagicItem.dustPegagin) return true;
            if (itemStack.getItem() == EquineMagicItem.dustSpectra) return true;
        }
        return false;
    }
}
