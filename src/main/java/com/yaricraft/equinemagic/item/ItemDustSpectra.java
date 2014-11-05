package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;

/**
 * Created by Yari on 9/8/2014.
 */
public class ItemDustSpectra extends EquineMagicItem
{
    public ItemDustSpectra() {
        super();
        this.setUnlocalizedName(ModNames.DUST_SPECTRA);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
    }
}
