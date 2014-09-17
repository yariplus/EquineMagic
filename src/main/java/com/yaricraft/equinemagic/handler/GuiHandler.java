package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.container.ContainerNotes;
import com.yaricraft.equinemagic.gui.GuiEquineResearch;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.utility.LogHelper;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/16/2014.
 */
public class GuiHandler implements IGuiHandler
{
    //public static GuiHandler INSTANCE = new GuiHandler();

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        LogHelper.info("Doing server GUI");
        switch (ID)
        {
            case ModData.GUIID_BOOK_RESEARCH:
                return new GuiEquineResearch(new ContainerNotes());
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        LogHelper.info("Doing client GUI");
        switch (ID)
        {
            case ModData.GUIID_BOOK_RESEARCH:
                return new GuiEquineResearch(new ContainerNotes());
        }
        return null;
    }
}
