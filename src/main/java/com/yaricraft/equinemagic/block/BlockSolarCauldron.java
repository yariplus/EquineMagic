package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.tileentity.TileSolarCauldron;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/15/2014.
 */
public class BlockSolarCauldron extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockSolarCauldron()
    {
        super(Material.iron);
        this.setBlockName(ModNames.BLOCK_SOLAR_CAULDRON);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileSolarCauldron();
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
