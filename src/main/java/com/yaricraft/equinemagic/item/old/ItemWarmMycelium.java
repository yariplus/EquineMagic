
package com.yaricraft.equinemagic.item.old;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.init.EquineMagicItem;

public class ItemWarmMycelium extends EquineMagicItem
{
	public ItemWarmMycelium()
	{
		super();
		this.setUnlocalizedName("warm_mycelium");
        this.setTextureName("EquineMagic:warm_mycelium");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}

}
