package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.enums.EEquineDust;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

/**
 * Created by Yari on 9/9/2014.
 */
public class LivingEventHandler
{
    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event)
    {
        if (event.source.getDamageType().equals("player"))
        {
            double rand = Math.random();
            if (event.entityLiving instanceof EntityBat) {
                if (rand < 0.10) {
                    event.entityLiving.dropItem(EquineMagicItem.warmWing, 1);
                }
            }else if(event.entityLiving instanceof EntitySkeleton || event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntityCreeper) {
                if (rand < 0.04)
                {
                    event.entityLiving.dropItem(EquineMagicItem.dustAlicorn, 1);
                }
                rand = Math.random();
                if (rand < 0.1)
                {
                    event.entityLiving.entityDropItem(new ItemStack(EquineMagicItem.equine_dust, 1, EEquineDust.CHROMA.ordinal()), 0.5F);
                }
                rand = Math.random();
                if (rand < 0.08)
                {
                    event.entityLiving.entityDropItem(new ItemStack(EquineMagicItem.equine_dust, 1, EEquineDust.DIRTY_SPECTRA.ordinal()), 0.5F);
                }
            }else if(event.entityLiving instanceof EntityBlaze){
                if (rand < 0.05)
                {
                    event.entityLiving.dropItem(EquineMagicItem.warmFluxingRod, 1);
                }
            }else if(event.entityLiving instanceof EntityChicken){
                if (rand < 0.05)
                {
                    event.entityLiving.dropItem(EquineMagicItem.warmFeather, 1);
                }
                if (rand < 0.02)
                {
                    event.entityLiving.dropItem(EquineMagicItem.warmEgg, 1);
                }
            }else if(event.entityLiving instanceof EntityEnderman){
                if (rand < 0.05)
                {
                    event.entityLiving.dropItem(EquineMagicItem.warmScale, 1);
                }
            }else if(event.entityLiving instanceof EntityGhast){
                if (rand < 0.25)
                {
                    event.entityLiving.dropItem(EquineMagicItem.warmTear, 1);
                }
            }
        }
    }
}
