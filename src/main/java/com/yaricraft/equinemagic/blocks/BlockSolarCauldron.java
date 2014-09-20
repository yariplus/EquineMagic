package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.items.ItemBucketSpectraSlurry;
import com.yaricraft.equinemagic.logic.TileCauldron;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.utility.LogHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

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
        return new TileCauldron();
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
