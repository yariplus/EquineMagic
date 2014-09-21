package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.tileentity.TileSpectralAscensionDevice;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/17/2014.
 */
public class BlockSpectralAscensionDevice extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockSpectralAscensionDevice()
    {
        super(Material.iron);
        this.setBlockName(ModNames.BLOCK_SPECTRAL_ASCENSION_DEVICE);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileSpectralAscensionDevice();
    }
}
