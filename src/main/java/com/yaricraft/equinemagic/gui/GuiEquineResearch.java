package com.yaricraft.equinemagic.gui;

import com.yaricraft.equinemagic.reference.ModData;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import openmods.gui.ComponentGui;
import openmods.gui.DummyContainer;
import openmods.gui.component.BaseComposite;
import openmods.gui.component.GuiComponentBook;
import openmods.gui.component.page.PageBase;
import openmods.gui.component.page.TitledPage;
import org.lwjgl.opengl.GL11;

/**
 * Created by Yari on 9/16/2014.
 */
public class GuiEquineResearch extends ComponentGui implements GuiYesNoCallback
{
    private static final String MODID = "openblocks";
    private int itemsIndex;
    private int miscIndex;
    private int blocksIndex;

    public static ResourceLocation background = new ResourceLocation(ModData.MODID.toLowerCase(), "textures/gui/bookbase.png");

    public GuiEquineResearch(Container container)
    {
        super(new DummyContainer(), 0, 0);
    }

    /*
    protected void drawGuiContainerBackgroundLayer(float f, int mx, int my)
    {
        drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int i = width - xSize >> 1;
        int j = height - ySize >> 1;
        Minecraft.getMinecraft().getTextureManager().bindTexture(background);
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
    }*/


    @Override
    protected BaseComposite createRoot() {
        final GuiComponentBook book = new GuiComponentBook();
        PageBase contentsPage = new TitledPage("title", "content");

        /*
        GuiComponentLabel lblBlocks = new GuiComponentLabel(27, 90, "- " + StatCollector.translateToLocal("openblocks.gui.blocks"));
        lblBlocks.setListener(new IMouseDownListener() {
            @Override
            public void componentMouseDown(BaseComponent component, int x, int y, int button) {
                book.gotoIndex(blocksIndex);
                book.enablePages();
            }
        });
        GuiComponentLabel lblItems = new GuiComponentLabel(27, 105, "- " + StatCollector.translateToLocal("openblocks.gui.items"));
        lblItems.setListener(new IMouseDownListener() {
            @Override
            public void componentMouseDown(BaseComponent component, int x, int y, int button) {
                book.gotoIndex(itemsIndex);
                book.enablePages();
            }
        });
        GuiComponentLabel lblMisc = new GuiComponentLabel(27, 120, "- " + StatCollector.translateToLocal("openblocks.gui.misc"));
        lblMisc.setListener(new IMouseDownListener() {
            @Override
            public void componentMouseDown(BaseComponent component, int x, int y, int button) {
                book.gotoIndex(miscIndex);
                book.enablePages();
            }
        });
        */

        // Blanks for testing
        book.addPage(PageBase.BLANK_PAGE);
        book.addPage(PageBase.BLANK_PAGE);
        book.addPage(PageBase.BLANK_PAGE);
        book.addPage(PageBase.BLANK_PAGE);
        book.addPage(PageBase.BLANK_PAGE);

        //Examples
        //contentsPage.addComponent(lblBlocks);
        //book.addStandardRecipePage(MODID, "luggage", Items.luggage);
        //book.addPage(new SectionPage("openblocks.gui.misc"));
        //book.addPage(new TitledPage("openblocks.gui.config.title", "openblocks.gui.config.content"));

        book.enablePages();
        xSize = book.getWidth();
        ySize = book.getHeight();
        return book;
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
        prepareRenderState();
        GL11.glPushMatrix();
        root.renderOverlay(this.mc, this.guiLeft, this.guiTop, par1 - this.guiLeft, par2 - this.guiTop);
        GL11.glPopMatrix();
        restoreRenderState();
    }
}
