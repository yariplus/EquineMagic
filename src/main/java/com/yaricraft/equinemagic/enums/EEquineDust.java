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
    IMPURE_SPECTRA   ("spectra_impure"),
    COMMON_SPECTRA   ("spectra_common"),
    RADIANT_SPECTRA  ("spectra_radiant"),
    DIRTY_SPECTRA    ("spectra");

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
