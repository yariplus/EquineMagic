package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.EquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.TileSpectralMiner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Yari on 9/20/2014.
 */
public class BlockSpectralMiner extends EquineMagicBlock implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockSpectralMiner()
    {
        super(Material.iron);
        this.setBlockName(ModNames.SPECTRAL_MINER);
        this.setBlockTextureName(ModNames.SPECTRAL_MINER);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EquineFoci.PEGASUS;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[2];
        icons[0] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName());
        icons[1] = iconRegister.registerIcon("EquineMagic:spectral_active");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if(side == 0) return icons[1];
        return icons[0];
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileSpectralMiner();
    }
}
