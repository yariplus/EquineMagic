package com.yaricraft.equinemagic.client.settings;

import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * Created by Yari on 11/21/2014.
 */
public class KeyBindings
{
    public static KeyBinding hud_toggle = new KeyBinding(Key.HUD_TOGGLE.getRegistryName(), Keyboard.KEY_O, ModNames.Keys.CATEGORY);
    public static KeyBinding aura_switch = new KeyBinding(Key.AURA_SWITCH.getRegistryName(), Keyboard.KEY_1, ModNames.Keys.CATEGORY);
}
