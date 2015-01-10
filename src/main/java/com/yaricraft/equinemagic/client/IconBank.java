package com.yaricraft.equinemagic.client;

import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.util.IIcon;

/**
 * Created by Yari on 11/18/2014.
 */
public enum  IconBank
{
    DECOR    (ModNames.EQUINE_DECOR),
    LED      (ModNames.CRYSTAL_PRIMATIC);

    IIcon icon;

    IconBank(String asset)
    {
        //IconRegistry.
    }
}
