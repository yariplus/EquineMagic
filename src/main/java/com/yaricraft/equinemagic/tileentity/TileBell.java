package com.yaricraft.equinemagic.tileentity;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Yari on 11/17/2014.
 */
public class TileBell extends EquineMagicTile
{
    public float rotation = 0;

    public int tickDelay = 20;

    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        tickDelay--;
        if (worldObj.isRemote)
        {
            if (tickDelay <= 0)
            {
                tickDelay = 20;
                if (rotation >= Math.PI)
                {
                    rotation = 0;
                } else
                {
                    rotation += (Math.PI / 8.0);
                }
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    }
}
