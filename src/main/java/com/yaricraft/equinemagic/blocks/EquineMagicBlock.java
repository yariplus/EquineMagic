
package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import com.yaricraft.equinemagic.reference.ModData;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public abstract class EquineMagicBlock extends Block
{
    // Static declarations
    public static final EquineMagicBlock blockDecor      = new BlockDecor();

    public static final EquineMagicBlock blockOreChroma  = new BlockOreChroma();
    public static final EquineMagicBlock blockOrePegagin = new BlockOrePegagin();
    public static final EquineMagicBlock blockOreSpectra = new BlockOreSpectra();

    public static final EquineMagicBlock blockSilkyTNT = new BlockSilkyTNT();

    public static void init()
    {

        GameRegistry.registerBlock(blockDecor, EquineMagicItemBlock.class, blockDecor.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerBlock(blockOreChroma, blockOreChroma.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerBlock(blockOrePegagin, blockOrePegagin.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerBlock(blockOreSpectra, blockOreSpectra.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerBlock(blockSilkyTNT, blockSilkyTNT.getUnlocalizedName().substring(6 + ModData.MODID.length()));



    }

	protected EquineMagicBlock(Material material)
	{
		super(material);
		this.setHardness(2.0f);
	}

	protected EquineMagicBlock()
	{
		this(Material.rock);
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", ModData.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
