
package com.yaricraft.equinemagic.world.gen.feature;

import java.util.Random;

import com.yaricraft.equinemagic.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/*
 * 
 */
public class OreGenerator extends WorldGenerator
{
	private Block	_block;
	private int		_meta;
	public OreGenerator(Block block, int meta, int tries, boolean flag)
	{
		_block = block;
		_meta = meta;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		LogHelper.info("Making ore at " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z));
		if (validSpawn(world.getBlock(x, y, z)))
			setBlockAndNotifyAdequately(world, x, y, z, _block, _meta);
		if (validSpawn(world.getBlock(x + random.nextInt(4), y, z)))
			setBlockAndNotifyAdequately(world, x, y, z, _block, _meta);
		if (validSpawn(world.getBlock(x - random.nextInt(4), y, z)))
			setBlockAndNotifyAdequately(world, x, y, z, _block, _meta);
		if (validSpawn(world.getBlock(x, y + random.nextInt(4), z)))
			setBlockAndNotifyAdequately(world, x, y, z, _block, _meta);
		if (validSpawn(world.getBlock(x, y - random.nextInt(4), z)))
			setBlockAndNotifyAdequately(world, x, y, z, _block, _meta);
		if (validSpawn(world.getBlock(x, y, z + random.nextInt(4))))
			setBlockAndNotifyAdequately(world, x, y, z, _block, _meta);
		if (validSpawn(world.getBlock(x, y, z - random.nextInt(4))))
			setBlockAndNotifyAdequately(world, x, y, z, _block, _meta);

		return true;
	}

	private boolean validSpawn(Block block)
	{
		if (block == Blocks.stone || block == Blocks.gravel || block == Blocks.dirt)
		{
			return true;
		} else
		{
			return false;
		}
	}
}
