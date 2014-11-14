package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.api.tileentity.ITileSpectralManipulator;
import com.yaricraft.equinemagic.tileentity.TileSpectralManipulator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
    public Block replaceBlock(int metaChip, Block block);

    // Return what a mined block should drop.
    public ItemStack alterDrop(int metaChip, Block minedBlock, int minedMeta, Item dropItem, int dropQuantity, int dropMeta);

    // Decides where to drop the ItemStack based on the Devices position.
    // Returns if the stack was successfully stored.
    public boolean storeStack(int metaChip, ItemStack itemstack, World world, int x, int y, int z);

    // Alter the manipulator's pattern.
    public void install(int metaChip, TileSpectralManipulator manipulator);
}
