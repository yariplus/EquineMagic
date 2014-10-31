
package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.tileentity.EquineMagicTile;
import com.yaricraft.equinemagic.reference.ModData;

import com.yaricraft.equinemagic.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class EquineMagicBlock extends Block
{
    // Static declarations
    // TODO: Refactor these assholes into interfaces.
    public static final EquineMagicBlock blockDecor      = new BlockDecor();

    public static final EquineMagicBlock blockOreChroma  = new BlockOreChroma();
    public static final EquineMagicBlock blockOrePegagin = new BlockOrePegagin();
    public static final EquineMagicBlock blockOreSpectra = new BlockOreSpectra();

    public static final EquineMagicBlock blockEquineCrafter = new BlockEquineCrafter();

    public static final EquineMagicBlock blockSilkyTNT = new BlockEquineTNT();

    public static final EquineMagicBlock spectral_cauldron = new BlockSpectralCauldron();

    public static final EquineMagicBlock spectral_ascender  = new BlockSpectralAscender();
    public static final EquineMagicBlock spectralMiner      = new BlockSpectralMiner();
    public static final EquineMagicBlock spectralCannon     = new BlockSpectralCannon();

    public static void init()
    {
        GameRegistry.registerBlock(blockDecor, EquineMagicItemBlock.class, blockDecor.getRegistryName());

        GameRegistry.registerBlock(blockOreChroma, blockOreChroma.getRegistryName());
        GameRegistry.registerBlock(blockOrePegagin, blockOrePegagin.getRegistryName());
        GameRegistry.registerBlock(blockOreSpectra, blockOreSpectra.getRegistryName());

        GameRegistry.registerBlock(blockEquineCrafter, blockEquineCrafter.getRegistryName());

        GameRegistry.registerBlock(blockSilkyTNT, blockSilkyTNT.getRegistryName());

        GameRegistry.registerBlock(spectral_cauldron, spectral_cauldron.getRegistryName());

        GameRegistry.registerBlock(spectral_ascender, spectral_ascender.getRegistryName());
        GameRegistry.registerBlock(spectralMiner, spectralMiner.getRegistryName());
        GameRegistry.registerBlock(spectralCannon, spectralCannon.getRegistryName());
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(!(this instanceof ITileEntityProvider)) return false;
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile == null) { LogHelper.error("Block was missing a tile entity at " + x + "," + y + "," + z); return false; }
        return ((EquineMagicTile)tile).onBlockActivated(player, side, hitX, hitY, hitZ);
    }

    // Protected so only called by init method and subclasses.
    // or is that wrong?
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
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

    // Returns tile.MODID:BLOCK
    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", ModData.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    // Returns MODID:BLOCK
	public String getUnwrappedUnlocalizedName()
	{
		return this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1);
	}

    // Takes off "tile."
    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    // Returns BLOCK
    public String getRegistryName()
    {
        return this.getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

    // Returns BLOCK_ASITEM
    public String getRegisteryNameAsItem()
    {
        return this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + "_asitem";
    }
}
