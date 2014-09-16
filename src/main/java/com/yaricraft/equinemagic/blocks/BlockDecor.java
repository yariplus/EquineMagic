
package com.yaricraft.equinemagic.blocks;

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

import javax.swing.*;
import java.util.List;

public class BlockDecor extends EquineMagicBlock
{
    public BlockDecor(Material material)
    {
        super(material);
        this.setHardness(2.0f);
        this.setBlockName(ModNames.BLOCK_DECOR);
        //this.setBlockTextureName(ModNames.BLOCK_DECOR);
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
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[MCData.THREE_SIDED * MCData.BLOCK_META_MAX];

        for (int i = 0; i < icons.length / MCData.THREE_SIDED; i++)
        {
            icons[i * MCData.THREE_SIDED + 0] = par1IconRegister.registerIcon(this.getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + ModData.ASSETSUF_ICON_TOP + "0");
            icons[i * MCData.THREE_SIDED + 1] = par1IconRegister.registerIcon(this.getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + ModData.ASSETSUF_ICON_BOTTOM + "0");
            icons[i * MCData.THREE_SIDED + 2] = par1IconRegister.registerIcon(this.getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + ModData.ASSETSUF_ICON_SIDE + "0");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        switch (par1)
        {
            case 0:
                return icons[par2 * MCData.THREE_SIDED + 0];
            case 1:
                return icons[par2 * MCData.THREE_SIDED + 1];
            default:
                return icons[par2 * MCData.THREE_SIDED + 2];
        }
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
