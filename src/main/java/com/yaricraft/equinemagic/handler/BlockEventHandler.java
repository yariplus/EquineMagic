package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.items.EquineMagicItem;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMycelium;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by Yari on 9/10/2014.
 */
public class BlockEventHandler
{
    @SubscribeEvent
    public void onBreakEvent(BlockEvent.BreakEvent event)
    {
        double rand = Math.random();
        if(event.block instanceof BlockMycelium)
        {
            if (rand < 0.05D)
            {
                event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(EquineMagicItem.warmMycelium)));
            }
        }else if(event.block instanceof BlockLeaves){
            if (rand < 0.04D)
            {
                event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(EquineMagicItem.warmEgg)));
            }
        }
    }
}
