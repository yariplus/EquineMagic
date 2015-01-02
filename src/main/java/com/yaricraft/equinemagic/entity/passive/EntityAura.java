package com.yaricraft.equinemagic.entity.passive;

import com.yaricraft.equinemagic.entity.projectile.EntityAuraBolt;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Created by Yari on 12/9/2014.
 */
public class EntityAura extends EntityBat
{
    public EntityAura(World p_i1680_1_)
    {
        super(p_i1680_1_);

        // this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    public void Shoot(Vec3 vec3)
    {
        double d0 = vec3.xCoord - this.posX;
        double d1 = vec3.yCoord - (this.posY + (double)(this.height / 2.0F));
        double d2 = vec3.zCoord - this.posZ;

        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);

        EntityAuraBolt entitysmallfireball = new EntityAuraBolt(this.worldObj, this, d0, d1, d2);
        entitysmallfireball.posY = this.posY + (double)(this.height / 2.0F);
        this.worldObj.spawnEntityInWorld(entitysmallfireball);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);

        //this.getEntityData()
    }
}
