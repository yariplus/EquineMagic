package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.TileEquineCrafter;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yari on 10/22/2014.
 *
 * Base for all objects that accepts items and craft with.
 */
public class BlockEquineCrafter extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockEquineCrafter()
    {
        super(Material.iron);
        this.setBlockName(ModNames.EQUINE_CRAFTER);
        this.setBlockTextureName(ModNames.EQUINE_CRAFTER);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        TileEquineCrafter te = new TileEquineCrafter();
        return te;
    }

    @Override
    public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_)
    {
        TileEquineCrafter te = new TileEquineCrafter();
        return te;
    }
}
