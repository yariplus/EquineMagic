package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.items.EquineMagicItem;
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

import java.util.List;
import java.util.Random;

public class BlockSilkyTNT extends EquineMagicBlock
{
    public BlockSilkyTNT()
    {
        super(Material.iron);
        this.setBlockName(ModNames.BLOCK_SILKY_TNT);
        this.setBlockTextureName(ModNames.BLOCK_SILKY_TNT);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[MCData.THREE_SIDED];

        for(int i = 0; i < MCData.THREE_SIDED; i++)
        {
            icons[i] = par1IconRegister.registerIcon(this.getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + ModData.ASSETSUF_ICONS[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        switch (par1)
        {
            case 0:
                return icons[0];
            case 1:
                return icons[1];
            default:
                return icons[2];
        }
    }
}
