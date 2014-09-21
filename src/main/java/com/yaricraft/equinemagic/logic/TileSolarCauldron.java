package com.yaricraft.equinemagic.logic;

import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import com.yaricraft.equinemagic.items.EquineMagicItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;

/**
 * Created by Yari on 9/17/2014.
 */
public class TileSolarCauldron extends EquineMagicTile
{
    // TODO: clean this clusterfuck

    public FluidStack fluid;
    public ItemStack dust;
    public int capacity;
    public int cooktime = 0;

    public TileSolarCauldron() { }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ, TileEntity tile)
    {
        TileSolarCauldron tileCauldron = (TileSolarCauldron) tile;

        if(player.getHeldItem() == null)
        {
            if(world.isRemote)
            {
                if(fluid == null && dust == null) player.addChatMessage(new ChatComponentText("The cauldron is empty."));
                if (dust != null) player.addChatMessage(new ChatComponentText("The cauldron contains a melting dust."));
                if(fluid != null) player.addChatMessage(new ChatComponentText("The cauldron contains spectra slurry"));
            }
            return true;
        }

        Item held = player.getHeldItem().getItem();

        if(held == EquineMagicFluid.itemBucketSpectraSlurry)
        {
            if(dust == null && fluid == null)
            {
                fluid = new FluidStack(FluidContainerRegistry.getFluidForFilledItem(player.getHeldItem()), 1);
                player.inventory.getCurrentItem().stackSize--;
                player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
                if (world.isRemote) player.addChatMessage(new ChatComponentText("Placed spectra in cauldron."));
            }else
            {
                if (world.isRemote) player.addChatMessage(new ChatComponentText("The cauldron already full."));
            }
        }else if(held == Items.bucket)
        {
            if(fluid != null)
            {
                fluid = null;
                player.inventory.getCurrentItem().stackSize--;
                player.inventory.addItemStackToInventory(new ItemStack(EquineMagicFluid.itemBucketSpectraSlurry));
                if (world.isRemote) player.addChatMessage(new ChatComponentText("Got spectra slurry from the cauldron."));
            }else
            {
                if (world.isRemote) player.addChatMessage(new ChatComponentText("No slurry in the cauldron."));
            }
        }else if(held == EquineMagicItem.dustPegagin)
        {
            if(dust == null && fluid == null)
            {
                cooktime = 500;
                dust = new ItemStack(EquineMagicItem.dustPegagin, 1);
                player.inventory.getCurrentItem().stackSize--;
                if (world.isRemote) player.addChatMessage(new ChatComponentText("Placed dust in cauldron."));
            }else
            {
                if (world.isRemote) player.addChatMessage(new ChatComponentText("The cauldron already full."));
            }
        }else if(held == EquineMagicItem.dustSpectra)
        {
            if(dust == null && fluid == null)
            {
                cooktime = 100;
                dust = new ItemStack(EquineMagicItem.dustSpectra, 1);
                player.inventory.getCurrentItem().stackSize--;
                if (world.isRemote) player.addChatMessage(new ChatComponentText("Placed dust in cauldron."));
            }else
            {
                if (world.isRemote) player.addChatMessage(new ChatComponentText("The cauldron already full."));
            }
        }

        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        fluid = FluidStack.loadFluidStackFromNBT(nbt);
        dust = ItemStack.loadItemStackFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        if(fluid != null) fluid.writeToNBT(nbt);
        if(dust != null) dust.writeToNBT(nbt);
    }

    @Override
    public void updateEntity()
    {
        if(dust != null)
        {
            cooktime--;
            if (cooktime <= 0)
            {
                dust = null;
                fluid = new FluidStack(FluidContainerRegistry.getFluidForFilledItem(new ItemStack(EquineMagicFluid.itemBucketSpectraSlurry)), 1);
            }
        }
    }
}
