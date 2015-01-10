package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.entity.passive.EntityAura;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Yari on 12/24/2014.
 */
public class ItemAuraSummoner extends EquineMagicItem
{
    private static int RANGE = 3;

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            int spawn = 1 + world.rand.nextInt(3);
            for (int i = 0; i < spawn; i++)
            {
                EntityAura aura = new EntityAura(world);
                aura.posX = player.posX + (world.rand.nextInt(1+RANGE+RANGE)-RANGE);
                aura.posY = player.posY + (world.rand.nextInt(1+RANGE)+1);
                aura.posZ = player.posZ + (world.rand.nextInt(1+RANGE+RANGE)-RANGE);
                world.spawnEntityInWorld(aura);
            }

            --itemStack.stackSize;
        }

        return itemStack;
    }
}
