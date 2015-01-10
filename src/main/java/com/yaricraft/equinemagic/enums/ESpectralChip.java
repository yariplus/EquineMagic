package com.yaricraft.equinemagic.enums;

/**
 * Created by Yari on 11/6/2014.
 */
public enum ESpectralChip
{
    //{"area", "dome", "fill", "grind", "helix", "plane", "silk", "target", "tiles", "vortex"};
    AREA    ("area"),
    DOME    ("dome"),
    FILL    ("fill"),
    GRIND   ("grind"),
    HELIX   ("helix"),
    PLANE   ("plane"),
    SILK    ("silk"),
    TARGET  ("target"),
    TILES   ("tiles"),
    VORTEX  ("vortex");

    private final String name;

    ESpectralChip(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
