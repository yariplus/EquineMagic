package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import java.util.Random;

/**
 * Created by Yari on 12/20/2014.
 */
public class BlockEquineCloudavator extends EquineMagicBlock
{
    public BlockEquineCloudavator()
    {
        super(Material.ground);
        this.setBlockName(ModNames.CLOUDAVATOR);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public int quantityDropped(Random p_149745_1_)
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(ModData.MODID + ":" + ModNames.CLOUDAVATOR);
    }
}
