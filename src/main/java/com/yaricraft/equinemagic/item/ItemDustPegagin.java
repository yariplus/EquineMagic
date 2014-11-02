
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.EquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;

public class ItemDustPegagin extends EquineMagicItem
{
    public ItemDustPegagin()
    {
        super();
        this.setUnlocalizedName(ModNames.DUST_PEGAGIN);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EquineFoci.PEGASUS;
    }
}
