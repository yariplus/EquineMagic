
package com.yaricraft.equinemagic.item.old;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.init.EquineMagicItem;

public class ItemWarmTear extends EquineMagicItem
{
	public ItemWarmTear()
	{
		super();
		this.setUnlocalizedName("warm_tear");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}

}
