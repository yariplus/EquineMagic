package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/16/2014.
 */
public abstract class EquineMagicTile extends TileEntity
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileSolarCauldron.class, ModNames.BLOCK_SOLAR_CAULDRON);
        GameRegistry.registerTileEntity(TileSpectralAscensionDevice.class, ModNames.BLOCK_SPECTRAL_ASCENSION_DEVICE);
        GameRegistry.registerTileEntity(TileSpectralCannon.class, ModNames.BLOCK_SPECTRAL_CANNON);
        GameRegistry.registerTileEntity(TileSpectralMiner.class, ModNames.BLOCK_SPECTRAL_MINER);
    }

    /**
     * TODO: Find out why this is important.
     */
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, nbt);
    }

    /**
     * TODO: Find out why this is important.
     */
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        super.onDataPacket(net, packet);
        readFromNBT(packet.func_148857_g());
    }

    /**
     * Called when an EquineMagicBlock in the same tile is activated.
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ, TileEntity tile) { return true; }
}