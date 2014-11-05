package com.yaricraft.equinemagic.enums;

/**
 * Created by Yari on 11/5/2014.
 */
public enum EEquineIngot
{
    OPAL             ("opal"),
    BLACK_OPAL       ("black_opal"),
    CHROMA           ("chroma"),
    ZIRCON           ("zircon"),
    ZIRCON_CELESTATE ("zircon_celestate"),
    MAGNAGLASS       ("magnaglass"),
    DOLOMITE         ("dolomite"),
    MAGNESIUM        ("magnesium_celestate"),
    SPECTRA          ("spectra"),
    DECORIUM         ("decorium");

    private final String name;

    EEquineIngot(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
