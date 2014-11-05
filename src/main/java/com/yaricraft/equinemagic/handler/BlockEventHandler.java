package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.block.BlockEquineOre;
import com.yaricraft.equinemagic.block.BlockSpectralMiner;
import com.yaricraft.equinemagic.block.EquineMagicBlock;
import com.yaricraft.equinemagic.enums.EEquineGem;
import com.yaricraft.equinemagic.enums.EEquineOre;
import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.tileentity.TileSpectralInventory;
import com.yaricraft.equinemagic.tileentity.TileSpectralMiner;
import com.yaricraft.equinemagic.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
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
        int x = event.x;
        int y = event.y;
        int z = event.z;
        int meta = event.world.getBlockMetadata(x, y, z);
        double rand = Math.random();

        if(event.block instanceof BlockMycelium)
        {
            if (rand < 0.05D)
            {
                event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(EquineMagicItem.warmMycelium)));
            }
        }else if(event.block instanceof BlockLeaves)
        {
            if (rand < 0.04D)
            {
                event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(EquineMagicItem.warmEgg)));
            }
        }else if(event.block instanceof EquineMagicBlock)
        {
            if (event.block instanceof BlockEquineOre)
            {
                ItemStack stack = event.getPlayer().getHeldItem();
                if (stack != null)
                {
                    Item tool = stack.getItem();
                    EEquineOre ore = EEquineOre.values()[meta];
                    switch (ore)
                    {
                        case OPAL:
                            if (tool instanceof ItemPickaxe)
                            {
                                LogHelper.info("Tried to get egg.");
                                if (((ItemPickaxe) tool).getToolMaterialName() == Item.ToolMaterial.EMERALD.toString())
                                {
                                    event.world.spawnEntityInWorld(new EntityItem(event.world, event.x + 0.5D, event.y + 0.5D, event.z + 0.5D, new ItemStack(EquineMagicItem.equine_gem, 1, EEquineGem.OPAL.ordinal())));
                                }
                            }
                            break;
                        case ZIRCON:
                            break;
                        case DOLOMITE:
                            break;
                        case SPECTRA:
                            break;
                    }
                }
            }
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
