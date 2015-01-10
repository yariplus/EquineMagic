package com.yaricraft.equinemagic.fluid;

import com.yaricraft.equinemagic.item.ItemBucketSpectraSlurry;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;

/**
 * Created by Yari on 9/15/2014.
 */
public class EquineMagicFluid
{
    public static ArrayList<BlockFluidBase> fluidBlocks = new ArrayList<BlockFluidBase>();

    public static Fluid fluidSpectraSlurry;
    public static BlockFluidClassic blockFluidSpectraSlurry;
    public static ItemBucket itemBucketSpectraSlurry;

    public static void init()
    {
        fluidSpectraSlurry = new Fluid(ModNames.SPECTRA_SLURRY);
        FluidRegistry.registerFluid(fluidSpectraSlurry);

        blockFluidSpectraSlurry = new BlockFluidSpectraSlurry(fluidSpectraSlurry, Material.water);
        GameRegistry.registerBlock(blockFluidSpectraSlurry, blockFluidSpectraSlurry.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        fluidBlocks.add(blockFluidSpectraSlurry);

        itemBucketSpectraSlurry = new ItemBucketSpectraSlurry(blockFluidSpectraSlurry);
        GameRegistry.registerItem(itemBucketSpectraSlurry, itemBucketSpectraSlurry.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        FluidContainerRegistry.registerFluidContainer(
                fluidSpectraSlurry,
                new ItemStack(itemBucketSpectraSlurry),
                FluidContainerRegistry.EMPTY_BUCKET);
    }
}
