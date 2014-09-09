
package com.yaricraft.equinemagic.items;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemFluxingRod extends EquineMagicItem
{
	public ItemFluxingRod()
	{
		super();
		this.setUnlocalizedName("warmRod");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
	}

	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side,
			float xOffset, float yOffset, float zOffSet)
	{

		if (world.getBlock(x, y + 1, z) == Blocks.air
				&& (world.getBlock(x, y, z) == Blocks.grass
						|| world.getBlock(x, y, z) == Blocks.stone
						|| world.getBlock(x, y, z) == Blocks.dirt || world.getBlock(x, y, z) == Blocks.sand))
		{
			EntitySkeleton ent = new EntitySkeleton(world); // change to
															// whatever entity
															// you're trying to
															// spawn
			ent.setLocationAndAngles(x, y + 1, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			world.spawnEntityInWorld(ent);
		}
		return true;
	}

}
