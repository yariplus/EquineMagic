package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
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

/**
 * Created by Yari on 9/17/2014.
 */
public class TileSpectralAscensionDevice extends EquineMagicTile
{
    // 4 Bottles, 4 Chips
    public ItemStack[] items = new ItemStack[8];

    // Tank Basins
    public ItemStack[] basinContent = new ItemStack[4];
    public int[] basinVolume = new int[4];

    // TODO: clean this clusterfuck

    public FluidStack fluid;
    public int activeX = 0;
    public int activeY = 0;
    public int activeZ = 0;
    public int activeLayer = 0;

    private int height = 8;
    private int width = 4;

    // TODO: Fix nbt to use compound tags

    public TileSpectralAscensionDevice() { }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ, TileEntity tile)
    {
        TileSpectralAscensionDevice tileCauldron = (TileSpectralAscensionDevice) tile;

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
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        fluid = FluidStack.loadFluidStackFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        if(fluid != null) fluid.writeToNBT(nbt);
    }

    @Override
    public void updateEntity()
    {
        if (fluid != null)
        {

                if (fluid.amount > 0)
                {
                    fluid.amount--;

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
}
