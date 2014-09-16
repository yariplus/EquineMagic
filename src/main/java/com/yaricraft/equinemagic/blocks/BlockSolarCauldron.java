package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentities.TESolarCauldron;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/15/2014.
 */
public class BlockSolarCauldron extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockSolarCauldron()
    {
        super();
        this.setBlockName(ModNames.BLOCK_SOLAR_CAULDRON);
        this.setBlockTextureName(ModNames.BLOCK_SOLAR_CAULDRON);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TESolarCauldron();
    }
}
