
package com.yaricraft.equinemagic.world.gen.feature;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;

public class BaseOreGenerator implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

    }
    /*
	private OreGenerator	orePegagin;
	private OreGenerator	oreChroma;
	private OreGenerator	oreSpectra;

	private int oreX;
	private int oreY;
	private int oreZ;
	
	public BaseOreGenerator()
	{
		orePegagin = new OreGenerator(ModBlocks.orePegagin, 0, 4, false);
		oreChroma = new OreGenerator(ModBlocks.oreChroma, 0, 4, false);
		oreSpectra = new OreGenerator(ModBlocks.oreSpectra, 0, 4, false);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		genOre(orePegagin, random, chunkX, chunkZ, world);
		genOre(oreChroma, random, chunkX, chunkZ, world);
		genOre(oreSpectra, random, chunkX, chunkZ, world);
	}

	private void genOre(OreGenerator og, Random random, int chunkX, int chunkZ, World world)
	{
		oreX = chunkX * 16 + random.nextInt(16);
		oreZ = chunkZ * 16 + random.nextInt(16);
		oreY = random.nextInt(60) + 4;
		og.generate(world, random, oreX, oreY, oreZ);
	}
    */
}
