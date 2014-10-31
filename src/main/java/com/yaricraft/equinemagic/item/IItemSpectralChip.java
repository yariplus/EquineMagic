package com.yaricraft.equinemagic.item;

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
    public Block ReplaceBlock(int meta, Block block);

    // Return what a mined block should drop.
    public ItemStack MineBlock(int meta, Block block);

    // Decides where to drop the ItemStack based on the Devices position.
    // Returns if the stack was successfully stored.
    public boolean StoreStack(int meta, ItemStack itemstack, World world, int x, int y, int z);
}
