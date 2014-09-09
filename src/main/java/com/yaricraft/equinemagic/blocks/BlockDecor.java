
package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

import net.minecraft.block.material.Material;

public class BlockDecor extends EquineMagicBlock
{
    public BlockDecor(Material material)
	{
		super(material);
		this.setHardness(2.0f);
            this.setBlockName("oreSpectra");
        this.setBlockTextureName("oreSpectra");
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
	}

    public BlockDecor()
	{
		this(Material.rock);
	}
}
