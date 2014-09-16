package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yari on 9/15/2014.
 */
public class BucketHandler
{
    public static BucketHandler INSTANCE = new BucketHandler();
    public Map<Block, Item> buckets = new HashMap<Block, Item>();

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
        if (event.current.getItem() == Items.bucket && event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            int hitX = event.target.blockX;
            int hitY = event.target.blockY;
            int hitZ = event.target.blockZ;
            if (event.entityPlayer != null && !event.entityPlayer.canPlayerEdit(hitX, hitY, hitZ, event.target.sideHit, event.current))
            {
                return;
            }
            Block block = event.world.getBlock(hitX, hitY, hitZ);
            for (int id = 0; id < EquineMagicFluid.fluidBlocks.size(); id++)
            {
                if (block == EquineMagicFluid.fluidBlocks.get(id))
                {
                    if (event.entityPlayer.capabilities.isCreativeMode)
                    {
                        event.world.setBlockToAir(hitX, hitY, hitZ);
                    }
                    else
                    {
                        event.world.setBlockToAir(hitX, hitY, hitZ);
                        event.setResult(Event.Result.ALLOW);
                        event.result = new ItemStack(EquineMagicFluid.fluidBuckets.get(id), 1);
                    }
                }
            }
        }
    }
}
