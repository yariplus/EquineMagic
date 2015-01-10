package com.yaricraft.equinemagic.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import io.netty.buffer.ByteBuf;

/**
 * Created by Yari on 12/19/2014.
 */
public class MessagePlayerMovement implements IMessage
{
    public MessagePlayerMovement() {}

    public int direction = 0;
    public long lbits = 0;
    public long mbits = 0;

    @Override
    public void fromBytes(ByteBuf buf)
    {
        direction = buf.readInt();
        mbits = buf.readLong();
        lbits = buf.readLong();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(direction);
        buf.writeLong(mbits);
        buf.writeLong(lbits);
    }
}
