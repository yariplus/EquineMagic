package com.yaricraft.equinemagic.fluid;

import net.minecraftforge.fluids.Fluid;

/**
 * Created by Yari on 9/15/2014.
 */
public class FluidSpectraSlurry extends Fluid
{
    public FluidSpectraSlurry(String fluidName)
    {
        super(fluidName);
        //setUnlocalizedName(ModNames.FLUID_SPECTRA_SLURRY);
    }

    /*
    @Override
    public String getUnlocalizedName()
    {
        return String.format("fluid.%s%s", ModData.MODID + ":", getUnwrappedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
    */
}
