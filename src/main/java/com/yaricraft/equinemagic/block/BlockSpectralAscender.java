package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.tileentity.TileSpectralAscender;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Yari on 9/17/2014.
 */
public class BlockSpectralAscender extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockSpectralAscender()
    {
        super(Material.iron);
        this.setBlockTextureName(ModNames.SPECTRAL_ASCENDER);
        this.setBlockName(ModNames.SPECTRAL_ASCENDER);
        this.foci = EEquineFoci.PEGASUS;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileSpectralAscender();
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
