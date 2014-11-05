
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;

public class ItemDustSilkyGunpowder extends EquineMagicItem
{
    public ItemDustSilkyGunpowder()
    {
        super();
        this.setUnlocalizedName(ModNames.DUST_SILKY_GUNPOWDER);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGACORN;
    }
}
