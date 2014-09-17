package com.yaricraft.equinemagic.items;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/8/2014.
 */
public class ItemDustSpectra extends EquineMagicItem
{
    public ItemDustSpectra() {
        super();
        this.setUnlocalizedName(ModNames.DUST_SPECTRA);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }
}
