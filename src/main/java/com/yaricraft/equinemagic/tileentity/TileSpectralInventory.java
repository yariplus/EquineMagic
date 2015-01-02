package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.reference.ModData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Yari on 10/25/2014.
 */
public abstract class TileSpectralInventory extends TileSpectraTank implements IInventory
{
    public ItemStack[] itemStacks = new ItemStack[3];

    // TileEntity
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        for (int i = 0; i < itemStacks.length; i++)
        {
            String key = ModData.NBTInvTagPrefix + i;
            if (tag.hasKey(key))
            {
                NBTTagCompound data = tag.getCompoundTag(key);
                itemStacks[i] = ItemStack.loadItemStackFromNBT(data);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        for (int i = 0; i < itemStacks.length; i++)
        {
            if (itemStacks[i] != null)
            {
                NBTTagCompound tagItemStack = new NBTTagCompound();
                itemStacks[i].writeToNBT(tagItemStack);

                String key = ModData.NBTInvTagPrefix + i;
                tag.setTag(key, tagItemStack);
            }
        }
    }

    // IInventory
    @Override
    public int getSizeInventory()
    {
        return 3;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return this.itemStacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.itemStacks[slot] != null)
        {
            ItemStack itemstack;

            if (this.itemStacks[slot].stackSize <= amount)
            {
                itemstack = this.itemStacks[slot];
                this.itemStacks[slot] = null;
                if (worldObj != null)
                {
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                }
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.itemStacks[slot].splitStack(amount);

                if (this.itemStacks[slot].stackSize == 0)
                {
                    this.itemStacks[slot] = null;
                }

                if (worldObj != null)
                {
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                }
                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return itemStacks[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.itemStacks[slot] = itemStack;
        if (worldObj != null) worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return true;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (slot < itemStacks.length && itemStack.getItem() == EquineMagicItem.itemSpectralChip)
        {
            return true;
        }
        return false;
    }
}
