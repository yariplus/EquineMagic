package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.enums.EEquineDust;
import com.yaricraft.equinemagic.enums.ESpectralChip;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.item.ItemDustSilky;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

/**
 * Created by Yari on 10/23/2014.
 */
public class TileEquineCrafter extends TileSpectralInventory
{
    @Override
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        // Don't do anything on the clientside
        if (worldObj.isRemote) return true;

        if (player.getHeldItem() != null)
        {
            Item heldItem = player.getHeldItem().getItem();
            ItemStack heldStack = player.getHeldItem();

            if (heldItem == EquineMagicItem.equine_dust && heldStack.getItemDamage() == EEquineDust.CHROMA.ordinal())
            {
                if (itemStacks[0] == null)
                {
                    this.itemStacks[0] = new ItemStack(EquineMagicItem.equine_dust, 1, EEquineDust.CHROMA.ordinal());

                    sendText(player, "Placed chroma dust inside.");

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    markDirty();
                }else{
                    sendText(player, "The fabricator already has chroma dust inside.");
                }
            }else if(heldItem == Item.getItemFromBlock(Blocks.stone))
            {
                if (itemStacks[1] == null)
                {
                    sendText(player, "Placed stone in the pattern slot.");

                    player.inventory.getCurrentItem().stackSize--;

                    this.itemStacks[1] = new ItemStack(Blocks.stone, 1);

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    markDirty();
                }else{
                    sendText(player, "The fabricator already has a pattern inside.");
                }

            }else if(heldItem instanceof ItemDustSilky)
            {
                if (itemStacks[1] == null)
                {
                    sendText(player, "Placed silky dust in the pattern slot.");

                    player.inventory.getCurrentItem().stackSize--;

                    this.itemStacks[1] = new ItemStack(EquineMagicItem.dustSilky, 1);

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    markDirty();
                }else{
                    sendText(player, "The fabricator already has a pattern inside.");
                }
            }else if(heldItem == Item.getItemFromBlock(Blocks.stone_slab))
            {
                if (itemStacks[1] == null)
                {
                    sendText(player, "Placed stone slab in the pattern slot.");

                    player.inventory.getCurrentItem().stackSize--;

                    this.itemStacks[1] = new ItemStack(Blocks.stone_slab);

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    markDirty();
                }else{
                    sendText(player, "The fabricator already has a pattern inside.");
                }
            }else if(heldItem == Items.bowl)
            {
                if (itemStacks[1] == null)
                {
                    sendText(player, "Placed a bowl in the pattern slot.");

                    player.inventory.getCurrentItem().stackSize--;

                    this.itemStacks[1] = new ItemStack(Items.bowl);

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    markDirty();
                }else{
                    sendText(player, "The fabricator already has a pattern inside.");
                }
            }else{
                emptyHand(player);
            }
        }else{
            emptyHand(player);
        }

        return true;
    }

    private void emptyHand(EntityPlayer player)
    {
        if (itemStacks[0] == null)
        {
            if (itemStacks[1] == null)
            {
                sendText(player, "The fabricator is empty.");
            }else{
                sendText(player, "The fabricator contains:");
                sendTextSlotTwo(player);
            }
        }else{
            sendText(player, "The fabricator contains:");
            sendText(player, itemStacks[0].stackSize + " chroma dust.");
            if (itemStacks[1] != null)
            {
                sendTextSlotTwo(player);
            }
        }
    }

    private void sendTextSlotTwo(EntityPlayer player)
    {
        sendText(player, itemStacks[1].stackSize + " other.");
    }

    private void sendText(EntityPlayer player, String text)
    {
        player.addChatMessage(new ChatComponentText(text));
    }

    @Override
    public void updateEntity()
    {
        if (!worldObj.isRemote)
        {
            if (itemStacks[0] != null && itemStacks[1] != null)
            {
                if (itemStacks[0].getItem() == EquineMagicItem.equine_dust && itemStacks[0].getItemDamage() == EEquineDust.CHROMA.ordinal())
                {
                    Item pattern = itemStacks[1].getItem();
                    if(pattern instanceof ItemDustSilky)
                    {
                        craft(new ItemStack(EquineMagicItem.itemSpectralChip, 1, ESpectralChip.SILK.ordinal()));
                        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                        this.markDirty();
                    }else if(pattern == Item.getItemFromBlock(Blocks.stone))
                    {
                        craft(new ItemStack(EquineMagicItem.itemSpectralChip, 1, ESpectralChip.FILL.ordinal()));
                        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                        this.markDirty();
                    }else if(pattern == Item.getItemFromBlock(Blocks.stone_slab))
                    {
                        craft(new ItemStack(EquineMagicItem.itemSpectralChip, 1, ESpectralChip.PLANE.ordinal()));
                        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                        this.markDirty();
                    }else if(pattern == Items.bowl)
                    {
                        craft(new ItemStack(EquineMagicItem.itemSpectralChip, 1, ESpectralChip.DOME.ordinal()));
                        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                        this.markDirty();
                    }else if(pattern instanceof ItemRedstone)
                    {
                    }else if(pattern instanceof ItemRedstone)
                    {
                    }
                }
            }
        }else{
            // TODO: Rendering changes go here maybe?
        }
    }

    private void craft(ItemStack itemStack)
    {
        decrStackSize(0, 1);
        decrStackSize(1, 1);
        worldObj.spawnEntityInWorld(new EntityItem(worldObj,
                xCoord + 0.5D,
                yCoord + 1.5D,
                zCoord + 0.5D,
                itemStack));
    }

    // IInventory
    @Override
    public int getSizeInventory()
    {
        return 2;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (slot == 0 && itemStack.getItem() == EquineMagicItem.equine_dust && itemStack.getItemDamage() == EEquineDust.CHROMA.ordinal()) return true;
        if (slot == 1)
        {
            if (itemStack.getItem() == EquineMagicItem.dustSilky) return true;
            if (itemStack.getItem() == Item.getItemFromBlock(Blocks.stone)) return true;
            if (itemStack.getItem() == Items.bowl) return true;
            if (itemStack.getItem() == Item.getItemFromBlock(Blocks.stone_slab)) return true;
        }
        return false;
    }
}
