package com.yaricraft.equinemagic.api.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;

import java.util.Random;

/**
 * Created by Yari on 10/18/2014.
 */
public interface ITileSpectralManipulator
{
    public void MineBlock(int x, int y, int z);
}
