
package com.yaricraft.equinemagic.reference;

public class ModData
{
	public static final String	MODID				= "EquineMagic";
	public static final String	MODNAME				= "EquineMagic";
	public static final String	VERSION				= "0.0.8.2";

	public static final boolean	DEBUG				= true;

	public static final String	CLIENT_PROXY_CLASS	= "com.yaricraft.equinemagic.proxy.ClientProxy";
	public static final String	SERVER_PROXY_CLASS	= "com.yaricraft.equinemagic.proxy.ServerProxy";
	public static final String	GUI_FACTORY_CLASS	= "com.yaricraft.equinemagic.client.gui.GUIFactory";

    public static final String ASSETSUF_ICON_TOP    = "_top";
    public static final String ASSETSUF_ICON_BOTTOM = "_bottom";
    public static final String ASSETSUF_ICON_FRONT  = "_front";
    public static final String ASSETSUF_ICON_BACK   = "_back";
    public static final String ASSETSUF_ICON_LEFT   = "_left";
    public static final String ASSETSUF_ICON_RIGHT  = "_right";
    public static final String ASSETSUF_ICON_SIDE  = "_side";

    public static final String[] ASSETSUF_ICON     = new String[] {ASSETSUF_ICON_BOTTOM, ASSETSUF_ICON_TOP, ASSETSUF_ICON_FRONT, ASSETSUF_ICON_BACK, ASSETSUF_ICON_LEFT, ASSETSUF_ICON_RIGHT};
    public static final String[] ASSETSUF_META     = new String[] {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};

    public static final String ASSET_SPACER        = "_";

    public static final int GUIID_BOOK_RESEARCH = 1;

    public static final String NBTInvTagPrefix = "storage";

    public static final int CHARGE_SECONDS = 1;
}
