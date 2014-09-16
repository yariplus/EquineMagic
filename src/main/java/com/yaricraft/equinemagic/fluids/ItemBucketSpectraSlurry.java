package com.yaricraft.equinemagic.fluids;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

/**
 * Created by Yari on 9/15/2014.
 */
public class ItemBucketSpectraSlurry extends ItemBucket
{
    public ItemBucketSpectraSlurry(Block fluid)
    {
        super(fluid);
        setUnlocalizedName(ModNames.ITEM_BUCKET_SPECTRA_SLURRY);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(ModData.MODID + ":" + ModNames.ITEM_BUCKET_SPECTRA_SLURRY);
    }
}
