package com.yaricraft.equinemagic.api.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Yari on 10/18/2014.
 */
public interface ITileSpectralManipulator
{
    public void doStatusClick(EntityPlayer player);
    public void shoot();
    public void mineBlock(int x, int y, int z, Block filler);
}
