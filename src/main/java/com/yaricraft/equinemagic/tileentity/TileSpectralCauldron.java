package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.enums.EEquineDust;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.init.EquineMagicItem;
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
    public TileSpectralCauldron()
    {
        super();
    }

    // Remaining cook time.
    public int cooktime = 0;

    @Override
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        // Don't do anything on the clientside
        if (worldObj.isRemote) return true;

        ItemStack heldStack = player.getHeldItem();
        if(heldStack == null)
        {
            if (player.isSneaking())
            {
                if (itemStacks[0] != null)
                {
                    player.inventory.addItemStackToInventory(new ItemStack(itemStacks[0].getItem(), itemStacks[0].stackSize, itemStacks[0].getItemDamage()));
                    player.inventoryContainer.detectAndSendChanges();
                    itemStacks[0] = null;

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    this.markDirty();
                }else
                {
                    player.addChatMessage(new ChatComponentText("No dust inside."));
                }
            }else
            {
                doStatusClick(player);
            }

            return true;
        }

        Item heldItem = heldStack.getItem();

        if(heldItem == Items.bucket)
        {
            if(tank.getFluidAmount() >= 1000)
            {
                tank.drain(1000, true);

                if (heldStack.stackSize == 1)
                {
                    player.inventory.setItemStack(new ItemStack(EquineMagicFluid.itemBucketSpectraSlurry));
                }else
                {
                    player.inventory.getCurrentItem().stackSize--;
                    player.inventory.addItemStackToInventory(new ItemStack(EquineMagicFluid.itemBucketSpectraSlurry));
                }

                //player.addChatMessage(new ChatComponentText("Filled bucket with spectra slurry from the cauldron."));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else
            {
                player.addChatMessage(new ChatComponentText("Not enough fluid to fill the bucket."));
            }
        }else if(heldItem == EquineMagicItem.equine_dust &&
                (heldStack.getItemDamage() == EEquineDust.DIRTY_SPECTRA.ordinal()
              || heldStack.getItemDamage() == EEquineDust.IMPURE_SPECTRA.ordinal()
              || heldStack.getItemDamage() == EEquineDust.COMMON_SPECTRA.ordinal()
              || heldStack.getItemDamage() == EEquineDust.RADIANT_SPECTRA.ordinal()))
        {
            if(itemStacks[0] == null)
            {
                itemStacks[0] = new ItemStack(EquineMagicItem.equine_dust, 1, heldStack.getItemDamage());
                player.inventory.getCurrentItem().stackSize--;
                cooktime = 100;
                player.addChatMessage(new ChatComponentText("Placed the dust in the cauldron."));


                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else if(itemStacks[0].getItem() == EquineMagicItem.equine_dust && itemStacks[0].getItemDamage() == heldStack.getItemDamage())
            {
                itemStacks[0].stackSize++;
                player.inventory.getCurrentItem().stackSize--;
                cooktime = 100;
                player.addChatMessage(new ChatComponentText("Placed dust in cauldron."));

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                this.markDirty();
            }else if(itemStacks[0].stackSize > itemStacks[0].getMaxStackSize())
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
        if (!worldObj.isRemote)
        {
            if (itemStacks[0] != null)
            {
                int fillAmount = 250;

                if (cooktime > 0) cooktime--;
                if (cooktime <= 0 && this.tank.getFluidAmount() <= this.tank.getCapacity() - fillAmount)
                {
                    this.decrStackSize(0, 1);
                    this.tank.fill(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, fillAmount), true);
                    if (itemStacks[0] != null) cooktime = 100;
                }
            }
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
            if (itemStack.getItem() == EquineMagicItem.equine_dust &&
                          (itemStack.getItemDamage() == EEquineDust.DIRTY_SPECTRA.ordinal()
                        || itemStack.getItemDamage() == EEquineDust.IMPURE_SPECTRA.ordinal()
                        || itemStack.getItemDamage() == EEquineDust.COMMON_SPECTRA.ordinal()
                        || itemStack.getItemDamage() == EEquineDust.RADIANT_SPECTRA.ordinal())) return true;
        }
        return false;
    }
}
