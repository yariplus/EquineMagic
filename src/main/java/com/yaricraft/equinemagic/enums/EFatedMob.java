package com.yaricraft.equinemagic.enums;

import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.util.LogHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.swing.text.html.parser.Entity;

/**
 * Created by Yari on 11/26/2014.
 */

public enum EFatedMob
{
    ZOMBIE    (EntityZombie.class),
    SKELETON  (EntitySkeleton.class),
    CREEPER   (EntityCreeper.class),
    GHAST     (EntityGhast.class),
    PLAYER    (EntityPlayerMP.class),
    COW       (EntityCow.class);

    public Class<? extends EntityLivingBase> clazz;

    EFatedMob(Class<? extends EntityLivingBase> clazz)
    {
        this.clazz = clazz;
    }
}
