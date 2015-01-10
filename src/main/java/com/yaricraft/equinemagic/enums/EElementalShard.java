package com.yaricraft.equinemagic.enums;

/**
 * Created by Yari on 11/5/2014.
 */
public enum EElementalShard
{
    ORANGE      ("orange"),
    BLUE        ("blue"),
    RED         ("red"),
    VIOLET      ("violet"),
    PINK        ("pink"),
    GREEN       ("green"),
    WHITE       ("white"),
    BLACK       ("primatic");

    private final String name;

    EElementalShard(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
