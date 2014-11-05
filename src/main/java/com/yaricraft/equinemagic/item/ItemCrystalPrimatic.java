package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;

/**
 * Created by Yari on 9/20/2014.
 */
public class ItemCrystalPrimatic extends EquineMagicItem
{
    public ItemCrystalPrimatic()
    {
        super();
        this.setUnlocalizedName(ModNames.CRYSTAL_PRIMATIC);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.SHADOW;
    }
}
