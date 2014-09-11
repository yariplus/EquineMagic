
package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.material.Material;

public class BlockDecor extends EquineMagicBlock
{
    public BlockDecor(Material material)
	{
		super(material);
		this.setHardness(2.0f);
        this.setBlockName(ModNames.BLOCK_DECOR);
        this.setBlockTextureName(ModNames.BLOCK_DECOR);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
	}

    public BlockDecor()
	{
		this(Material.rock);
	}
}
