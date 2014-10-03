package com.yaricraft.equinemagic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Yari on 9/25/2014.
 */
public class EquineMagicPlayer implements IExtendedEntityProperties
{
    public final static String NAME = "EquineMagicPlayer";

    private EntityPlayer player;

    private int magic, chaos, darkness;

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();

        properties.setInteger("Magic", this.magic);
        properties.setInteger("Chaos", this.chaos);
        properties.setInteger("Darkness", this.darkness);

        compound.setTag(NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(NAME);

        this.magic = properties.getInteger("Magic");
        this.chaos = properties.getInteger("Chaos");
        this.darkness = properties.getInteger("Darkness");
    }

    @Override
    public void init(Entity entity, World world)
    {
        this.chaos = 0;
        this.darkness = 0;
        this.magic = 0;
        this.player = (EntityPlayer)entity;
    }

    public static EquineMagicPlayer get(EntityPlayer player)
    {
        return (EquineMagicPlayer) player.getExtendedProperties(NAME);
    }
}
