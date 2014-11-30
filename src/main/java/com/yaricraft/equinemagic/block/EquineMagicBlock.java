
package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.tileentity.EquineMagicTile;
import com.yaricraft.equinemagic.reference.ModData;

import com.yaricraft.equinemagic.util.LogHelper;
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
    public static final EquineMagicBlock equine_log           = new BlockELog();
    public static final EquineMagicBlock equine_leaves        = new BlockELeaves();
    public static final EquineMagicBlock equine_bell          = new BlockBell();
    public static final EquineMagicBlock equine_statue        = new BlockEquineStatue();
    public static final EquineMagicBlock equine_ore           = new BlockEquineOre();
    public static final EquineMagicBlock blockDecor           = new BlockDecor();
    public static final EquineMagicBlock blockEquineCrafter   = new BlockEquineCrafter();
    public static final EquineMagicBlock equine_tnt           = new BlockEquineTNT();
    public static final EquineMagicBlock spectral_cauldron    = new BlockSpectralCauldron();
    public static final EquineMagicBlock spectral_miner       = new BlockSpectralMiner();
    public static final EquineMagicBlock spectral_cannon      = new BlockSpectralCannon();

    public static void init()
    {
        GameRegistry.registerBlock(equine_log, EquineMagicItemBlock.class, equine_log.getRegistryName());
        GameRegistry.registerBlock(equine_leaves, EquineMagicItemBlock.class, equine_leaves.getRegistryName());
        GameRegistry.registerBlock(equine_bell, EquineMagicItemBlock.class, equine_bell.getRegistryName());
        GameRegistry.registerBlock(equine_statue, EquineMagicItemBlock.class, equine_statue.getRegistryName());
        GameRegistry.registerBlock(equine_ore, EquineMagicItemBlockWithMeta.class, equine_ore.getRegistryName());
        GameRegistry.registerBlock(blockDecor, EquineMagicItemBlockWithMeta.class, blockDecor.getRegistryName());
        GameRegistry.registerBlock(blockEquineCrafter, EquineMagicItemBlock.class, blockEquineCrafter.getRegistryName());
        GameRegistry.registerBlock(equine_tnt, EquineMagicItemBlock.class, equine_tnt.getRegistryName());
        GameRegistry.registerBlock(spectral_cauldron, EquineMagicItemBlock.class, spectral_cauldron.getRegistryName());
        GameRegistry.registerBlock(spectral_miner, EquineMagicItemBlock.class, spectral_miner.getRegistryName());
        GameRegistry.registerBlock(spectral_cannon, EquineMagicItemBlock.class, spectral_cannon.getRegistryName());
    }

    public EEquineFoci foci;
    protected String[] subNames = ModData.ASSETSUF_META.clone();

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
