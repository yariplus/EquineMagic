package com.yaricraft.equinemagic.world.gen.structure;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class ElementsGenerator extends WorldGenerator {

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {

        this.setBlockAndNotifyAdequately(world, x + 0, y + 0, z + 0, Block.getBlockFromName("EquineMagic:block_decor"), 10);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 0, z + 1, Block.getBlockFromName("EquineMagic:block_decor"), 10);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 0, z + 0, Block.getBlockFromName("EquineMagic:block_decor"), 10);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 0, z + 1, Block.getBlockFromName("EquineMagic:block_decor"), 10);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 1, z + 0, Block.getBlockFromName("EquineMagic:spectral_cannon"), 3);
        this.setBlockAndNotifyAdequately(world, x + 0, y + 1, z + 1, Block.getBlockFromName("minecraft:air"), 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 1, z + 0, Block.getBlockFromName("minecraft:air"), 0);
        this.setBlockAndNotifyAdequately(world, x + 1, y + 1, z + 1, Block.getBlockFromName("minecraft:air"), 2);

        NBTTagList TileEntities1 = new NBTTagList();

        NBTTagCompound tagCompound1 = new NBTTagCompound();
        tagCompound1.setString("id", "openblocks_tank");
        tagCompound1.setString("Empty", "");
        tagCompound1.setInteger("z", (int)(z + 1));
        tagCompound1.setInteger("y", (int)(y + 1));
        tagCompound1.setInteger("x", (int)(x + 0));
        TileEntities1.appendTag((NBTTagCompound)tagCompound1);
        world.getTileEntity(x + 0, y + 1, z + 1).readFromNBT(tagCompound1);


        NBTTagCompound tagCompound2 = new NBTTagCompound();
        tagCompound2.setString("id", "spectral_cannon");
        tagCompound2.setInteger("Amount", (int)(807));
        tagCompound2.setInteger("z", (int)(z + 0));
        tagCompound2.setString("FluidName", "spectra_slurry");
        tagCompound2.setInteger("y", (int)(y + 1));
        tagCompound2.setInteger("x", (int)(x + 0));
        TileEntities1.appendTag((NBTTagCompound)tagCompound2);
        world.getTileEntity(x + 0, y + 1, z + 0).readFromNBT(tagCompound2);


        NBTTagCompound tagCompound3 = new NBTTagCompound();
        tagCompound3.setString("id", "openblocks_blockPlacer");
        NBTTagList Items2 = new NBTTagList();
        NBTTagCompound tagCompound4 = new NBTTagCompound();
        tagCompound4.setShort("id", (short)456);
        tagCompound4.setShort("Damage", (short)11);
        tagCompound4.setByte("Count", (byte)16);
        tagCompound4.setByte("Slot", (byte)3);
        Items2.appendTag((NBTTagCompound)tagCompound4);
        NBTTagCompound tagCompound5 = new NBTTagCompound();
        tagCompound5.setShort("id", (short)456);
        tagCompound5.setShort("Damage", (short)11);
        tagCompound5.setByte("Count", (byte)32);
        tagCompound5.setByte("Slot", (byte)4);
        Items2.appendTag((NBTTagCompound)tagCompound5);
        NBTTagCompound tagCompound6 = new NBTTagCompound();
        tagCompound6.setShort("id", (short)456);
        tagCompound6.setShort("Damage", (short)11);
        tagCompound6.setByte("Count", (byte)16);
        tagCompound6.setByte("Slot", (byte)5);
        Items2.appendTag((NBTTagCompound)tagCompound6);
        NBTTagCompound tagCompound7 = new NBTTagCompound();
        tagCompound7.setShort("id", (short)495);
        tagCompound7.setShort("Damage", (short)0);
        tagCompound7.setByte("Count", (byte)1);
        tagCompound7.setByte("Slot", (byte)7);
        Items2.appendTag((NBTTagCompound)tagCompound7);

        tagCompound3.setTag("Items", Items2);
        tagCompound3.setInteger("z", (int)(z + 1));
        tagCompound3.setInteger("y", (int)(y + 1));
        tagCompound3.setInteger("x", (int)(x + 1));
        tagCompound3.setInteger("size", (int)(9));
        TileEntities1.appendTag((NBTTagCompound)tagCompound3);
        world.getTileEntity(x + 1, y + 1, z + 1).readFromNBT(tagCompound3);


        return true;
    }
}