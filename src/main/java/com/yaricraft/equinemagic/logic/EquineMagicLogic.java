package com.yaricraft.equinemagic.logic;

import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Yari on 9/16/2014.
 */
public abstract class EquineMagicLogic extends TileEntity
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TESolarCauldron.class, ModNames.LOGIC_SOLAR_CAULDRON);
        GameRegistry.registerTileEntity(TESpectralAscensionDevice.class, ModNames.LOGIC_SPECTRAL_ASCENSION_DEVICE);
    }

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
