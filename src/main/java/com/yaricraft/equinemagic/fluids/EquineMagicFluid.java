package com.yaricraft.equinemagic.fluids;

import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.*;

import java.util.ArrayList;

/**
 * Created by Yari on 9/15/2014.
 */
public class EquineMagicFluid
{
    public static ArrayList<BlockFluidBase> fluidBlocks = new ArrayList<BlockFluidBase>();
    public static ArrayList<ItemBucket> fluidBuckets = new ArrayList<ItemBucket>();

    public static Fluid fluidSpectraSlurry;
    public static BlockFluidClassic blockFluidSpectraSlurry;
    public static ItemBucket itemBucketSpectraSlurry;

    public static void init()
    {
        fluidSpectraSlurry = new FluidSpectraSlurry(ModNames.FLUID_SPECTRA_SLURRY);
        FluidRegistry.registerFluid(fluidSpectraSlurry);

        blockFluidSpectraSlurry = new BlockFluidSpectraSlurry(fluidSpectraSlurry, Material.water);
        GameRegistry.registerBlock(blockFluidSpectraSlurry, blockFluidSpectraSlurry.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        fluidBlocks.add(blockFluidSpectraSlurry);

        itemBucketSpectraSlurry = new ItemBucketSpectraSlurry(blockFluidSpectraSlurry);
        GameRegistry.registerItem(itemBucketSpectraSlurry, itemBucketSpectraSlurry.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        fluidBuckets.add(itemBucketSpectraSlurry);

        FluidContainerRegistry.registerFluidContainer(
                FluidRegistry.getFluidStack(
                    ModNames.FLUID_SPECTRA_SLURRY.toLowerCase(),
                    FluidContainerRegistry.BUCKET_VOLUME),
                new ItemStack(itemBucketSpectraSlurry),
                new ItemStack(Items.bucket));

        fluidSpectraSlurry.setUnlocalizedName(ModNames.FLUID_SPECTRA_SLURRY);
        itemBucketSpectraSlurry.setUnlocalizedName(ModNames.FLUID_SPECTRA_SLURRY).setContainerItem(Items.bucket);


    }
}
