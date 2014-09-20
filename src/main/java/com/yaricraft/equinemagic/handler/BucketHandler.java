package com.yaricraft.equinemagic.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
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
        int x = event.target.blockX;
        int y = event.target.blockY;
        int z = event.target.blockZ;
        World world = event.world;
        EntityPlayer player = event.entityPlayer;
        Block block = event.world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (event.current.getItem() == Items.bucket && event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            if (event.entityPlayer != null && !event.entityPlayer.canPlayerEdit(x, y, z, event.target.sideHit, event.current)) return;

            Item bucket = buckets.get(block);
            if(bucket == null) return;

            world.setBlockToAir(x, y, z);
            event.setResult(Event.Result.ALLOW);
            if (!player.capabilities.isCreativeMode) return;

            event.result = new ItemStack(bucket);
        }
    }
}
