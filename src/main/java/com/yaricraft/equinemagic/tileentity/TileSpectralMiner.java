package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.api.tileentity.ITileSpectralManipulator;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.item.IItemSpectralChip;
import com.yaricraft.equinemagic.item.ItemSpectralChip;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fluids.*;

import java.util.Random;

/**
 * Created by Yari on 10/17/2014.
 */
public class TileSpectralMiner extends TileSpectralInventory implements ITileSpectralManipulator
{
    private int syncedFluid = 0;

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
        if (!worldObj.isRemote)
        {
            // Server

            if (tank.getFluidAmount() == 1000)
            {
                chargeAmount++;
            }

            if (chargeAmount >= chargeNeeded)
            {
                Shoot();
                chargeAmount = 0;
                tank.drain(1000, true);
            }
        }else
        {
            // Client
        }
    }

    private void Shoot()
    {
        for (int y = -2; y >= -64; y--)
        {
            for (int x = -1; x <= 1; x++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    MineBlock(x, y, z);
                }
            }
        }
    }

    @Override
    public void MineBlock(int x, int y, int z)
    {
        Block minedBlock = worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z);
        int minedMeta = worldObj.getBlockMetadata(xCoord + x, yCoord + y, zCoord + z);
        TileEntity minedTile = worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z);
        Block replacedBlock = Blocks.air;
        Item minedItem = minedBlock.getItemDropped( minedMeta, new Random(), 0 );
        int minedQuantity = minedBlock.quantityDropped( minedMeta, 0, new Random() );
        ItemStack minedStack = new ItemStack(minedItem, minedQuantity, minedMeta);

        if (minedTile == null && !(minedBlock == Blocks.bedrock))
        {
            // Replace the mined block.
            for (int i = 0; i < itemStacks.length; i++)
            {
                if (itemStacks[i] != null) replacedBlock = ((IItemSpectralChip) itemStacks[i].getItem()).ReplaceBlock(itemStacks[i].getItemDamage(), replacedBlock);
            }
            worldObj.setBlock(xCoord + x, yCoord + y, zCoord + z, replacedBlock);

            if (!worldObj.isRemote && !(minedBlock instanceof BlockLiquid || minedBlock instanceof BlockAir))
            {
                // Get the itemStack dropped.
                ItemStack newStack = null;
                for (int i = 0; i < itemStacks.length; i++)
                {
                    if (itemStacks[i] != null) newStack = ((IItemSpectralChip) itemStacks[i].getItem()).MineBlock(itemStacks[i].getItemDamage(), minedBlock, minedQuantity);
                    if (newStack != null)
                    {
                        minedStack = newStack;
                        i = itemStacks.length;
                    }
                }

                if(minedStack != null)
                {
                    worldObj.spawnEntityInWorld(
                            new EntityItem(worldObj,
                                    xCoord + 0.5D,
                                    yCoord + 3.5D,
                                    zCoord + 0.5D,
                                    minedStack));
                }
            }
        }
    }
}
