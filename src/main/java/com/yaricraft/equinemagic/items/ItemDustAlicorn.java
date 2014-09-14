package com.yaricraft.equinemagic.items;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Yari on 9/8/2014.
 */
public class ItemDustAlicorn extends EquineMagicItem
{
    public ItemDustAlicorn() {
        super();
        this.setUnlocalizedName(ModNames.DUST_ALICORN);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            Block hit = world.getBlock(x, y, z);
            if (hit instanceof BlockBookshelf) {
                world.setBlock(x, y, z, Blocks.air);
                world.notifyBlockOfNeighborChange(x, y, z, Blocks.air);

                if (Math.random() < 0.20D)
                {
                    player.addChatMessage(new ChatComponentText("I found some useful Equine history here, I'll start keeping research notes in a book!"));
                    --stack.stackSize;
                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(EquineMagicItem.bookResearch)));
                } else {
                    player.addChatMessage(new ChatComponentText("These books didn't contain any useful information."));
                    if (Math.random() < 0.20D) {
                        player.addChatMessage(new ChatComponentText("Dang, the dust has been used up."));
                        --stack.stackSize;
                    }
                }

                return true;
            }
        }

        return false;
    }
}