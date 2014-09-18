package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.blocks.EquineMagicBlock;
import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
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
        int hitX = event.target.blockX;
        int hitY = event.target.blockY;
        int hitZ = event.target.blockZ;

        World world = event.world;
        EntityPlayer player = event.entityPlayer;
        Block block = event.world.getBlock(hitX, hitY, hitZ);

        if (event.current.getItem() == Items.bucket && event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            if (event.entityPlayer != null && !event.entityPlayer.canPlayerEdit(hitX, hitY, hitZ, event.target.sideHit, event.current))
            {
                return;
            }

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
                }else if(block == EquineMagicBlock.solarCauldron)
                {
                    TileEntity te = world.getTileEntity(hitX, hitY, hitZ);
                    if (te==null) return;
                    player.inventory.setItemStack(new ItemStack(EquineMagicFluid.itemBucketSpectraSlurry));
                    event.entityPlayer.addChatMessage(new ChatComponentText("There are " + "x" + " buckets of " + "liquid" + " remaining."));
                    event.entityPlayer.addChatMessage(new ChatComponentText("Nothing left in the cauldron."));
                }
            }
        }
    }
}
