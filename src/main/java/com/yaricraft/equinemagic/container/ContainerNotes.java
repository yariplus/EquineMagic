package com.yaricraft.equinemagic.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by Yari on 9/16/2014.
 */
public class ContainerNotes extends Container
{
    protected EntityPlayer player;

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        this.player = player;
        return true;
    }
}
