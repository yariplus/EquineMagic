package com.yaricraft.equinemagic.client.handler;


import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.client.settings.Key;
import com.yaricraft.equinemagic.client.settings.KeyBindings;
import com.yaricraft.equinemagic.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Yari on 11/21/2014.
 */
public class KeyInputHandler
{
    private  static Key getPressedKeybinding()
    {
        if (KeyBindings.hud_toggle.isPressed())
        {
            return Key.HUD_TOGGLE;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        switch (getPressedKeybinding())
        {
            case HUD_TOGGLE:
                LogHelper.info("HUD key pressed.");
                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                EquineMagicPlayer properties = (EquineMagicPlayer)player.getExtendedProperties(EquineMagicPlayer.NAME);
//                EquineMessageExtendedProperties message = new EquineMessageExtendedProperties();
                if (properties.hud == true)
                {
                    properties.hud = false;
                }else
                {
                    properties.hud = true;
                }
                break;
            case UNKNOWN:
                break;
        }
    }
}
