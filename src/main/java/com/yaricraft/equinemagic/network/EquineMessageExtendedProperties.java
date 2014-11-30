package com.yaricraft.equinemagic.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by Yari on 11/14/2014.
 */
public class EquineMessageExtendedProperties implements IMessage
{
    public EquineMessageExtendedProperties() {}

    public int magic = 0;
    public int chaos = 0;
    public int darkness = 0;
    public boolean hud = true;

    @Override
    public void fromBytes(ByteBuf buf)
    {
        chaos = buf.readInt();
        darkness = buf.readInt();
        magic = buf.readInt();
        hud = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(chaos);
        buf.writeInt(darkness);
        buf.writeInt(magic);
        buf.writeBoolean(hud);
    }
}
