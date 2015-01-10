package com.yaricraft.equinemagic.enums;

/**
 * Created by Yari on 11/8/2014.
 */
public enum EPegaiConduit
{
    BLAZEROD    (100, 100),
    EGG         (100, 100),
    FEATHER     (100, 100),
    MUSHROOM    (100, 100),
    SCALE       (100, 100),
    TEAR        (100, 100),
    WING        (100, 100);

    public int spectraMB;
    public int meltTicks;

    EPegaiConduit(int spectraMB, int meltTicks)
    {
        this.spectraMB = spectraMB;
        this.meltTicks = meltTicks;
    }
}