package openmods.gui.component.page;

/**
 * Created by Yari on 9/18/2014.
 */
import com.yaricraft.equinemagic.reference.ModData;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import openmods.gui.component.GuiComponentSprite;
import openmods.gui.component.page.PageBase;
import openmods.utils.render.FakeIcon;

public class IntroPage extends PageBase
{
    private GuiComponentSprite image;

    private static final ResourceLocation texture = new ResourceLocation(ModData.MODID.toLowerCase(), "textures/gui/bookimage.png");

    public static IIcon iconImage = new FakeIcon(0, 0.7421875f, 0, 0.546875f, 95, 70);

    public IntroPage()
    {
        image = new GuiComponentSprite(52, 48, iconImage, texture);
        addComponent(image);
    }
}
