
package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

import com.yaricraft.equinemagic.reference.MCData;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;
import java.util.List;

public class BlockDecor extends EquineMagicBlock
{
    public BlockDecor(Material material)
    {
        super(material);
        this.setHardness(1.5f);
        this.setBlockName(ModNames.EQUINE_DECOR);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    public BlockDecor()
    {
        this(Material.rock);
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[MCData.THREE_SIDED * MCData.BLOCK_META_MAX];

        for (int i = 0; i < icons.length / MCData.THREE_SIDED; i++)
        {
            for(int j = 0; j < MCData.THREE_SIDED; j++)
            {
                icons[i * MCData.THREE_SIDED + j] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName() + ModData.ASSETSUF_ICON[2] + ModData.ASSETSUF_META[i]);
                //icons[i * MCData.THREE_SIDED + j] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName() + ModData.ASSETSUF_ICON[j] + "_0");
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if(side>2) side = 2;
        return icons[meta * MCData.THREE_SIDED + side];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < MCData.BLOCK_META_MAX; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}
