package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

/**
 * Created by Yari on 9/15/2014.
 */
public class ItemBucketSpectraSlurry extends ItemBucket
{
    public ItemBucketSpectraSlurry(Block fluid)
    {
        super(fluid);
        setUnlocalizedName(ModNames.BUCKET_SPECTRA_SLURRY);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        setContainerItem(Items.bucket);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(ModData.MODID + ":" + ModNames.BUCKET_SPECTRA_SLURRY);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", ModData.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", ModData.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
