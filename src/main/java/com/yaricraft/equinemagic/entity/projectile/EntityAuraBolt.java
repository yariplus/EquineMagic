package com.yaricraft.equinemagic.entity.projectile;

import com.yaricraft.equinemagic.enums.EAuraBolt;
import com.yaricraft.equinemagic.item.IItemSpectralChip;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Yari on 12/18/2014.
 */
public class EntityAuraBolt extends EntitySmallFireball
{
    EAuraBolt type;

    public EntityAuraBolt(World world)
    {
        super(world);
        this.setSize(0.3125F, 0.3125F);
        type = EAuraBolt.MINING;
    }

    public EntityAuraBolt(World world, EntityLivingBase entityLivingBase, double xPos, double yPos, double zPos)
    {
        super(world, entityLivingBase, xPos, yPos, zPos);
        this.setSize(0.3125F, 0.3125F);
        type = EAuraBolt.MINING;
    }

    public EntityAuraBolt(World world, double xPos, double yPos, double zPos, double targetX, double targetY, double targetZ)
    {
        super(world, xPos, yPos, zPos, targetX, targetY, targetZ);
        this.setSize(0.3125F, 0.3125F);
        type = EAuraBolt.MINING;
    }

    @Override
    protected void onImpact(MovingObjectPosition position)
    {
        if (!this.worldObj.isRemote)
        {
            if (position.entityHit != null)
            {
                if (!position.entityHit.isImmuneToFire() && position.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F))
                {
                    if (type == EAuraBolt.FIRE) position.entityHit.setFire(5);
                }
            }
            else
            {
                int x = position.blockX;
                int y = position.blockY;
                int z = position.blockZ;

                switch (type)
                {
                    case FIRE:
                        switch (position.sideHit)
                        {
                            case 0:
                                --y;
                                break;
                            case 1:
                                ++y;
                                break;
                            case 2:
                                --z;
                                break;
                            case 3:
                                ++z;
                                break;
                            case 4:
                                --x;
                                break;
                            case 5:
                                ++x;
                        }

                        if (this.worldObj.isAirBlock(x, y, z))
                        {
                            this.worldObj.setBlock(x, y, z, Blocks.fire);
                        }
                        break;
                    case MINING:
                        Block minedBlock = worldObj.getBlock(x, y, z);
                        int minedBlockMeta = worldObj.getBlockMetadata(x, y, z);
                        TileEntity minedBlockTile = worldObj.getTileEntity(x, y, z);

                        // getItemDropped( meta, random, fortune );
                        // returns null for shurbs etc.
                        Item dropItem = minedBlock.getItemDropped( minedBlockMeta, new Random(), 0 );
                        int dropMeta = minedBlock.damageDropped(minedBlockMeta);
                        int dropQuantity = minedBlock.quantityDropped( minedBlockMeta, 0, new Random() );
                        ItemStack dropStack = new ItemStack(dropItem, dropQuantity, dropMeta);

                        // Can we replace the Block?
                        if (dropItem != null && minedBlockTile == null && minedBlock != Blocks.bedrock && !(minedBlock instanceof BlockAir) && !(minedBlock instanceof BlockLiquid))
                        {
                            worldObj.setBlock( x, y, z, Blocks.air);
                            worldObj.spawnEntityInWorld(new EntityItem(worldObj, x + 0.5, y + 0.5, z + 0.5, dropStack));
                        }
                        break;
                }
            }

            this.setDead();
        }
    }
}
