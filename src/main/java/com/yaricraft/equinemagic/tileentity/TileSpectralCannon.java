package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Random;

/**
 * Created by Yari on 10/17/2014.
 */
public class TileSpectralCannon extends EquineMagicTile
{
    private FluidStack fluid;

    private int chargeNeeded = 20 * 60;
    private int chargeAmount = 0;

    public TileSpectralCannon()
    {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        fluid = FluidStack.loadFluidStackFromNBT(nbt);
        fluid = FluidStack.loadFluidStackFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        if(fluid != null) fluid.writeToNBT(nbt);
        if(fluid != null) fluid.writeToNBT(nbt);
    }

    // TODO: Properly send sync packets from server for client MP
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ, TileEntity tile)
    {
        //TileSpectralCannon tileCauldron = (TileSpectralCannon) tile;

        if(player.getHeldItem() == null)
        {
            if(world.isRemote)
            {
                if(fluid != null)
                {
                    if (fluid.amount == 0)
                    {
                        player.addChatMessage(new ChatComponentText("The device is empty."));
                    } else
                    {
                        player.addChatMessage(new ChatComponentText("Fluid inside: " + fluid.amount + "mb"));
                    }
                }else
                {
                    player.addChatMessage(new ChatComponentText("The device is empty."));
                }
            }
            return true;
        }

        Item held = player.getHeldItem().getItem();

        if(held == EquineMagicFluid.itemBucketSpectraSlurry)
        {
            if(fluid == null)
            {
                fluid = new FluidStack(FluidContainerRegistry.getFluidForFilledItem(player.getHeldItem()), 1000);
                player.inventory.getCurrentItem().stackSize--;
                player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
                if (world.isRemote) player.addChatMessage(new ChatComponentText("Placed spectra in the device."));
            }else
            {
                if(fluid.amount + 1000 <= 5000)
                {
                    fluid.amount += 1000;
                    player.inventory.getCurrentItem().stackSize--;
                    player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
                    if (world.isRemote) player.addChatMessage(new ChatComponentText("Placed spectra in the device."));
                }else
                {
                    if (world.isRemote) player.addChatMessage(new ChatComponentText("The device is full."));
                }
            }
        }

        return true;
    }

    @Override
    public void updateEntity()
    {
        if (fluid != null && fluid.amount > 0)
        {
            if (chargeAmount != -1)
            {
                chargeAmount++;
                if (chargeAmount == chargeNeeded)
                {
                    Shoot();
                    chargeAmount = -1;
                    fluid = null;
                }
            }
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

    private void MineBlock(int x, int y, int z)
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
