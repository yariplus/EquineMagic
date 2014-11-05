
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;

public class ItemDustChroma extends EquineMagicItem
{
    public ItemDustChroma()
    {
        super();
        this.setUnlocalizedName(ModNames.DUST_CHROMA);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.ELEMENTAL;
    }
}
