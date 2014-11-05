package com.yaricraft.equinemagic.enums;

/**
 * Created by Yari on 11/5/2014.
 */
public enum EEquineGem
{
    OPAL       ("opal"),
    BLACK_OPAL ("black_opal"),
    CHROMA     ("chroma"),
    ZIRCON     ("zircon"),
    DOLOMITE   ("dolomite"),
    HAFNIUM    ("hafnium"),
    SPECTRA    ("spectra");

    private final String name;

    EEquineGem(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
