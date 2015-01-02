package com.yaricraft.equinemagic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yari on 9/25/2014.
 */
public class EquineMagicPlayer implements IExtendedEntityProperties
{
    public final static String NAME = "EquineMagicPlayer";

    //private EntityPlayer player;

    public int magic, chaos, darkness;
    public boolean hud;

    public ArrayList<String> aEquineAuras = new ArrayList<String>();

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();

        properties.setInteger("Magic", this.magic);
        properties.setInteger("Chaos", this.chaos);
        properties.setInteger("Shadow", this.darkness);

        properties.setBoolean("HUD", this.hud);

        NBTTagList listAura = new NBTTagList();
        for (String uuid : aEquineAuras) listAura.appendTag(new NBTTagString(uuid));
        properties.setTag("Attached Auras", listAura);

        compound.setTag(NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(NAME);

        this.magic = properties.getInteger("Magic");
        this.chaos = properties.getInteger("Chaos");
        this.darkness = properties.getInteger("Shadow");

        this.hud = properties.getBoolean("HUD");

        this.aEquineAuras = new ArrayList<String>();

        NBTTagList listAura = properties.getTagList("Attached Auras", 8);
        for (int i = 0; i < listAura.tagCount() ; i++ )
        {
            this.aEquineAuras.add(listAura.getStringTagAt(i));
        }
    }

    @Override
    public void init(Entity entity, World world)
    {
        this.chaos = 0;
        this.darkness = 0;
        this.magic = 0;

        this.hud = true;

        //this.player = (EntityPlayer)entity;
    }

    public static EquineMagicPlayer get(EntityPlayer player)
    {
        return (EquineMagicPlayer) player.getExtendedProperties(NAME);
    }
}
