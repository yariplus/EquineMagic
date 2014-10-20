package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.TileSpectralMiner;
import com.yaricraft.equinemagic.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

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
        this.setBlockName(ModNames.BLOCK_SPECTRAL_MINER);
        this.setBlockTextureName("blockSpectralMiner");
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[2];
        icons[0] = iconRegister.registerIcon("EquineMagic:blockSpectralMiner");
        icons[1] = iconRegister.registerIcon("EquineMagic:blockSpectralActive");
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

    @Override
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
        return p_149660_9_;
    }
}
