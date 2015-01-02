package com.yaricraft.equinemagic.client.settings;

import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;

/**
 * Created by Yari on 11/21/2014.
 */
public enum Key
{
    UNKNOWN     ("Unknown"),
    HUD_TOGGLE  ("hud_toggle"),
    AURA_SWITCH ("aura_switch");

    private String registryName;

    public String getRegistryName()
    {
        return registryName;
    }

    Key(String registryName)
    {
        this.registryName = ModData.REGISTRY_KEY_PREFIX + registryName;
    }
}
