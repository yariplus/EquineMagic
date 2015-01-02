
package com.yaricraft.equinemagic.item.old;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.init.EquineMagicItem;

public class ItemWarmScale extends EquineMagicItem
{
	public ItemWarmScale()
	{
		super();
		this.setUnlocalizedName("warm_scale");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}

}
