
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.EquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;

/**
 * Created by Yari on 9/16/2014.
 */
public class ItemDustSilky extends EquineMagicItem
{
    public ItemDustSilky()
    {
        super();
        this.setUnlocalizedName(ModNames.DUST_SILKY);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EquineFoci.PEGACORN;
    }
}
