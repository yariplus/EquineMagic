package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.EEquineOre;
import com.yaricraft.equinemagic.item.EquineMagicItem;
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

/**
 * Created by Yari on 11/3/2014.
 */
public class BlockEquineOre extends EquineMagicBlock
{
    public BlockEquineOre()
    {
        super(Material.iron);
        this.setBlockName(ModNames.EQUINE_ORE);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.ELEMENTAL;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        EEquineOre ore = EEquineOre.values()[meta];
        switch (ore)
        {
            case OPAL:
                break;
            case ZIRCON:
                break;
            case DOLOMITE:
                break;
            case SPECTRA:
                break;
        }

        return EquineMagicItem.dustChroma;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 1 + random.nextInt(3);
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
        for (int i = 0; i < EEquineOre.values().length; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}
