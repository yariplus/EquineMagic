package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.block.BlockSpectralMiner;
import com.yaricraft.equinemagic.block.EquineMagicBlock;
import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.tileentity.TileSpectralInventory;
import com.yaricraft.equinemagic.tileentity.TileSpectralMiner;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMycelium;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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
        }else if(event.block instanceof BlockSpectralMiner){
            TileSpectralMiner te = (TileSpectralMiner)event.world.getTileEntity(event.x, event.y, event.z);
            for (int i = 0; i < te.itemStacks.length; i++)
            {
                if (te.itemStacks[i] != null)
                {
                    event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y + 0.75D, event.z, te.itemStacks[i]));
                }
            }
        }else if(event.block instanceof EquineMagicBlock){
            TileEntity te = event.world.getTileEntity(event.x, event.y, event.z);
            if (te instanceof TileSpectralInventory)
            {
                for (int i = 0; i < ((TileSpectralInventory)te).itemStacks.length; i++)
                {
                    if (((TileSpectralInventory)te).itemStacks[i] != null)
                    {
                        event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y + 0.75D, event.z, ((TileSpectralInventory)te).itemStacks[i]));
                    }
                }
            }
        }
    }
}
