package com.yaricraft.equinemagic;

import com.yaricraft.equinemagic.utility.StringHelper;

/**
 * Created by Yari on 11/1/2014.
 */
public enum EquineFoci
{
    PONY      (StringHelper.Color.AQUA  + "Pony"),
    PEGASUS   (StringHelper.Color.GOLD  + "Pegasus"),
    UNICORN   (StringHelper.Color.LIGHT_PURPLE + "Unicorn"),
    PEGACORN  (StringHelper.Color.GOLD  + "Pegasus" + StringHelper.Color.GRAY + "," + StringHelper.Color.LIGHT_PURPLE + " Unicorn"),
    EQUICORN  (StringHelper.Color.AQUA  + "Pony"    + StringHelper.Color.GRAY + "," + StringHelper.Color.LIGHT_PURPLE + " Unicorn"),
    PEGAQUINE (StringHelper.Color.GOLD  + "Pegasus" + StringHelper.Color.GRAY + "," + StringHelper.Color.AQUA         + " Pony"),
    ELEMENTAL (StringHelper.Color.WHITE + "Elemental"),
    CHAOTIC   (StringHelper.Color.GREEN + "Chaotic"),
    SHADOW    (StringHelper.Color.DARK_PURPLE  + "Shadow");

    private final String text;

    EquineFoci(String text)
    {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
