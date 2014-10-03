package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.items.EquineMagicItem;
import com.yaricraft.equinemagic.tileentity.TileSpectralAscensionDevice;
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
public class BlockSpectralAscensionDevice extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockSpectralAscensionDevice()
    {
        super(Material.iron);
        this.setBlockName(ModNames.BLOCK_SPECTRAL_ASCENSION_DEVICE);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return EquineMagicItem.spectralAscensionDevice;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileSpectralAscensionDevice();
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
