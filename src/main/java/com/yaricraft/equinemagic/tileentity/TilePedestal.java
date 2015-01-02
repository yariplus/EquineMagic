package com.yaricraft.equinemagic.tileentity;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Yari on 11/17/2014.
 */
public class TilePedestal extends EquineMagicTile
{
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        // NOOP
    }
}
