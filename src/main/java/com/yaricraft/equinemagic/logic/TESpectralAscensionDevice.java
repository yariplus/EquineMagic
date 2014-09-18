package com.yaricraft.equinemagic.logic;

import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Yari on 9/17/2014.
 */
public class TESpectralAscensionDevice extends TileEntity
{
    // 4 Bottles, 4 Chips
    public ItemStack[] items = new ItemStack[8];

    // Tank Basins
    public ItemStack[] basinContent = new ItemStack[4];
    public int[] basinVolume = new int[4];

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
    }
}
