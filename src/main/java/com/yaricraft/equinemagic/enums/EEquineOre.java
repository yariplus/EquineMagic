package com.yaricraft.equinemagic.enums;

import com.yaricraft.equinemagic.utility.StringHelper;

/**
 * Created by Yari on 11/4/2014.
 */
public enum EEquineOre
{
    OPAL      ("opal"),
    ZIRCON    ("zircon"),
    DOLOMITE  ("dolomite"),
    SPECTRA   ("spectra");

    private final String name;

    EEquineOre(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
