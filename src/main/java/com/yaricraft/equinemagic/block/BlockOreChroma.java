package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOreChroma extends EquineMagicBlock
{
    public BlockOreChroma()
    {
        super(Material.iron);
        this.setBlockName(ModNames.ORE_CHROMA);
        this.setBlockTextureName(ModNames.ORE_CHROMA);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return EquineMagicItem.dustChroma;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 1 + random.nextInt(3);
    }
}
