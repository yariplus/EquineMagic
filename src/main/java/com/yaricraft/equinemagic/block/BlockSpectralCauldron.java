package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.tileentity.TileSpectralCauldron;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Yari on 9/15/2014.
 */
public class BlockSpectralCauldron extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockSpectralCauldron()
    {
        super(Material.iron);
        this.setBlockTextureName(ModNames.SPECTRAL_CAULDRON);
        this.setBlockName(ModNames.SPECTRAL_CAULDRON);
        disableStats();
        this.foci = EEquineFoci.PEGASUS;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return EquineMagicItem.spectral_cauldron;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileSpectralCauldron();
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
