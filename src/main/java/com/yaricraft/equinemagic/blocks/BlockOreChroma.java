package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

import net.minecraft.block.material.Material;

public class BlockOreChroma extends EquineMagicBlock
{
	public BlockOreChroma()
	{
		super(Material.iron);
		this.setBlockName("oreChroma");
		this.setBlockTextureName("oreChroma");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
	}
}
