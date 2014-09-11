package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.items.EquineMagicItem;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOreSpectra extends EquineMagicBlock
{
	public BlockOreSpectra()
	{
		super(Material.iron);
		this.setBlockName(ModNames.ORE_SPECTRA);
		this.setBlockTextureName(ModNames.ORE_SPECTRA);
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
	}

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return EquineMagicItem.dustSpectra;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 1 + random.nextInt(3);
    }
}
