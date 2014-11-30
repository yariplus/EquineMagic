package com.yaricraft.equinemagic.entity.monster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Yari on 11/2/2014.
 */
public class EntityChangeling extends EntitySkeleton
{
    public EntityChangeling(World p_i1745_1_)
    {
        super(p_i1745_1_);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase entity, float distance) {

        double d0 = entity.posX - this.posX;
        double d1 = entity.boundingBox.minY + (double)(entity.height / 1.5F) - (this.posY + (double)(this.height / 1.5F));
        double d2 = entity.posZ - this.posZ;

        float f1 = MathHelper.sqrt_float(distance) * 0.5F;
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);

        for (int i = 0; i < 1; ++i)
        {
            EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
            entitysmallfireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
            this.worldObj.spawnEntityInWorld(entitysmallfireball);
        }
    }
}
