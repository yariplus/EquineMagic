package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.api.tileentity.ITileSpectralManipulator;
import com.yaricraft.equinemagic.tileentity.TileSpectralManipulator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Yari on 10/20/2014.
 */
public interface IItemSpectralChip
{
    // True if the device should continue processing chips after this one.
    public boolean greedy = false;

    // Returns what to replace a block with when it's processed.
    public Block replaceBlock(int meta, Block block);

    // Return what a mined block should drop.
    public ItemStack mineBlock(int meta, Block block, int quantity, int minedMeta);

    // Decides where to drop the ItemStack based on the Devices position.
    // Returns if the stack was successfully stored.
    public boolean storeStack(int meta, ItemStack itemstack, World world, int x, int y, int z);

    //public

    // Return what a mined block should drop.
    public void install(int meta, TileSpectralManipulator manipulator);
}
