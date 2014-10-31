package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.api.tileentity.ITileSpectralManipulator;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
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
public class TileSpectralCannon extends TileSpectralInventory implements ITileSpectralManipulator
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
                this.markDirty();
            }
        }else if(heldItem instanceof ItemSpectralChip)
        {
            if(tank.getFluidAmount() == 0)
            {
                int openSocket = -1;
                if(itemStacks[2] == null) openSocket = 2;
                if(itemStacks[1] == null) openSocket = 1;
                if(itemStacks[0] == null) openSocket = 0;

                if (openSocket != -1)
                {
                    itemStacks[openSocket] = new ItemStack(heldItem, 1, heldStack.getItemDamage());
                    player.inventory.getCurrentItem().stackSize--;
                    this.markDirty();
                }
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
        switch (worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
        {
            case 2: // +Z
                for (int z = 1; z <= 64; z++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        for (int x = -1; x <= 1; x++)
                        {
                            MineBlock(x, y, z);
                        }
                    }
                }
                break;
            case 3: // -Z
                for (int z = -1; z >= -64; z--)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        for (int x = -1; x <= 1; x++)
                        {
                            MineBlock(x, y, z);
                        }
                    }
                }
                break;
            case 4: // +X
                for (int x = 1; x <= 64; x++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        for (int z = -1; z <= 1; z++)
                        {
                            MineBlock(x, y, z);
                        }
                    }
                }
                break;
            case 5: // -X
                for (int x = -1; x >= -64; x--)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        for (int z = -1; z <= 1; z++)
                        {
                            MineBlock(x, y, z);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void MineBlock(int x, int y, int z)
    {
        Block mined = worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z);
        int minedMeta = worldObj.getBlockMetadata(xCoord + x, yCoord + y, zCoord + z);
        TileEntity minedTile = worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z);

        if (minedTile == null && !(mined instanceof BlockLiquid || mined instanceof BlockAir || mined == Blocks.bedrock))
        {
            worldObj.setBlock(xCoord + x, yCoord + y, zCoord + z, Blocks.air);
            if (!worldObj.isRemote)
            {
                Item minedItem = mined.getItemDropped(minedMeta, new Random(), 0);
                if(minedItem != null)
                {
                    ItemStack minedStack = new ItemStack(minedItem);
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
