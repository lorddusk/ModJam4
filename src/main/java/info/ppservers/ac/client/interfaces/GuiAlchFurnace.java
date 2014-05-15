package info.ppservers.ac.client.interfaces;

import info.ppservers.ac.client.inventory.ContainerAlchFurnace;
import info.ppservers.ac.tileentity.TileEntityAlchFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Tim on 5/15/2014.
 */
public class GuiAlchFurnace extends GuiContainer {
    private static final ResourceLocation TEXTURE = ResourceHelper.getResource("furnace");
    private TileEntityAlchFurnace tileEntityAlchFurnace;

    public GuiAlchFurnace(InventoryPlayer inventoryPlayer, TileEntityAlchFurnace tileEntityAlchFurnace) {
        super(new ContainerAlchFurnace(inventoryPlayer, tileEntityAlchFurnace));
        this.tileEntityAlchFurnace = tileEntityAlchFurnace;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = tileEntityAlchFurnace.hasCustomInventoryName() ? tileEntityAlchFurnace.getInventoryName() : I18n.format(tileEntityAlchFurnace.getInventoryName(), new Object[0]);
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(TEXTURE);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int i;
        if (tileEntityAlchFurnace.isBurning()) {
            i = tileEntityAlchFurnace.getBurnTimeRemainingScale(12);
            drawTexturedModalRect(k + 56, l + 36 + 12 - i, 176, 12 - i, 14, i + 2);
        }
        i = tileEntityAlchFurnace.getCookProgessScaled(24);
        drawTexturedModalRect(k + 79, l + 34, 176, 14, i + 1, 16);
    }
}
