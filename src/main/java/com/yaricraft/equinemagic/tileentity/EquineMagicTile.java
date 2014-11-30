package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Yari on 9/16/2014.
 */
public abstract class EquineMagicTile extends TileEntity
{
    /**
     * Called in the mod init methods.
     */
    public static void init()
    {
        GameRegistry.registerTileEntity(TileSpectralCauldron.class, ModNames.SPECTRAL_CAULDRON);
        GameRegistry.registerTileEntity(TileSpectralAscender.class, ModNames.SPECTRAL_ASCENDER);
        GameRegistry.registerTileEntity(TileSpectralCannon.class, ModNames.SPECTRAL_CANNON);
        GameRegistry.registerTileEntity(TileSpectralMiner.class, ModNames.SPECTRAL_MINER);
        GameRegistry.registerTileEntity(TileEquineCrafter.class, ModNames.EQUINE_CRAFTER);
        GameRegistry.registerTileEntity(TileEquineStatue.class, ModNames.EQUINE_STATUE);
        GameRegistry.registerTileEntity(TileBell.class, ModNames.EQUINE_BELL);
    }

    /**
     * Called when an EquineMagicBlock in the same tile is activated.
     */
    public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) { return true; }

    // TileEntity //
    /**
     * Server calls this to get the packet data when markBlockForUpdate() is called on the server.
     * Then sends the packet to the clients.
     */
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, nbt);
    }

    /**
     * Client calls to read the NBT when it receives the above packet.
     */
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        super.onDataPacket(net, packet);
        readFromNBT(packet.func_148857_g());
    }
}