package com.yaricraft.equinemagic.tileentity;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Yari on 11/14/2014.
 */
public class TileEquineStatue extends EquineMagicTile
{
    public boolean isDoneInitialSetup;

    private boolean working;

    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        return true;
    }

    @Override
    public void updateEntity()
    {

    }


}
