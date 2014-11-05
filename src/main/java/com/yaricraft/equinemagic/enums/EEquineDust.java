package com.yaricraft.equinemagic.enums;

/**
 * Created by Yari on 11/5/2014.
 */
public enum EEquineDust
{
    OPAL             ("opal"),
    BLACK_OPAL       ("black_opal"),
    CHROMA           ("chroma"),
    ZIRCON           ("zircon"),
    ZIRCON_CELESTATE ("zircon_celestate"),
    DOLOMITE         ("dolomite"),
    MAGNESIUM        ("magnesium_celestate"),
    IMPURE_SPECTRA   ("impure_spectra"),
    COMMON_SPECTRA   ("common_spectra"),
    RADIANT_SPECTRA  ("radiant_spectra"),
    DIRTY_SPECTRA    ("dirty_spectra");

    private final String name;

    EEquineDust(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
