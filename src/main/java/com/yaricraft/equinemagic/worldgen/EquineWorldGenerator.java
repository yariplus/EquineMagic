
package com.yaricraft.equinemagic.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class EquineWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		if (true)
		{
			switch (world.provider.dimensionId)
			{
				case 0:
					generateOverworld(random, world, chunkX * 16, chunkZ * 16);
					break;
				case 1:
					generateEnd(random, world, chunkX * 16, chunkZ * 16);
					break;
				case -1:
					generateNether(random, world, chunkX * 16, chunkZ * 16);
					break;
				default:
					generateOverworld(random, world, chunkX * 16, chunkZ * 16);
					break;
			}
		}
	}

	private void generateNether(Random random, World world, int x, int z)
	{
	}

	private void generateEnd(Random random, World world, int x, int z)
	{
	}

	private void generateOverworld(Random random, World world, int x, int z)
	{
        EquineWorldGenTrees trees = new EquineWorldGenTrees(true);

        trees.generate(world, random, x+random.nextInt(16), random.nextInt(16)+56, z+random.nextInt(16));

        //ElementsGenerator elementsGenerator = new ElementsGenerator();

        //elementsGenerator.generate(world, random, x+random.nextInt(16), 75, z+random.nextInt(16));

        /*
		if (true)
			addOres(ModBlocks.oreChroma, world, random, x, z, ModConfig.oreGenDataChroma.VeinSizeMin, ModConfig.oreGenDataChroma.VeinSizeMax, ModConfig.oreGenDataChroma.Chances, ModConfig.oreGenDataChroma.YMin, ModConfig.oreGenDataChroma.YMax);
		if (true)
			addOres(EquineMagicBlock.orePegagin, world, random, x, z, ModConfig.oreGenDataPegagin.VeinSizeMin, ModConfig.oreGenDataPegagin.VeinSizeMax, ModConfig.oreGenDataPegagin.Chances, ModConfig.oreGenDataPegagin.YMin, ModConfig.oreGenDataPegagin.YMax);
		if (true)
			addOres(ModBlocks.oreSpectra, world, random, x, z, ModConfig.oreGenDataSpectra.VeinSizeMin, ModConfig.oreGenDataSpectra.VeinSizeMax, ModConfig.oreGenDataSpectra.Chances, ModConfig.oreGenDataSpectra.YMin, ModConfig.oreGenDataSpectra.YMax);
			*/
	}

	public void addOres(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize,
			int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		WorldGenMinable minable = new WorldGenMinable(block, minVeinSize + random.nextInt(maxVeinSize - minVeinSize), Blocks.stone);

		for (int i = 0; i < chancesToSpawn; i++)
		{
			int posX = blockXPos + random.nextInt(16);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(16);
			minable.generate(world, random, posX, posY, posZ);
		}
	}
}
