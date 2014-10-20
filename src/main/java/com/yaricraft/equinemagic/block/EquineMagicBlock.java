
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

    public static final EquineMagicBlock blockSilkyTNT = new BlockSilkyTNT();

    public static final EquineMagicBlock solarCauldron           = new BlockSolarCauldron();

    public static final EquineMagicBlock spectralAscensionDevice = new BlockSpectralAscensionDevice();
    public static final EquineMagicBlock spectralMiner = new BlockSpectralMiner();
    public static final EquineMagicBlock spectralCannon = new BlockSpectralCannon();

    public static void init()
    {
        // TODO: Fix naming methods so that they are less messy looking.
        GameRegistry.registerBlock(blockDecor, EquineMagicItemBlock.class, blockDecor.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerBlock(blockOreChroma, blockOreChroma.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerBlock(blockOrePegagin, blockOrePegagin.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerBlock(blockOreSpectra, blockOreSpectra.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerBlock(blockSilkyTNT, blockSilkyTNT.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerBlock(solarCauldron, solarCauldron.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerBlock(spectralAscensionDevice, spectralAscensionDevice.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerBlock(spectralMiner, spectralMiner.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerBlock(spectralCannon, spectralCannon.getUnlocalizedName().substring(6 + ModData.MODID.length()));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(!(this instanceof ITileEntityProvider)) return false;
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile == null) { LogHelper.error("Block missing tile entity at " + x + "," + y + "," + z); return false; }
        return ((EquineMagicTile)tile).onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ, tile);
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

    // Returns tile.MOD:BLOCK
    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", ModData.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    // Return BLOCK
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
