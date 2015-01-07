package com.dta.extracarts.mods.enderio.client;

import com.dta.extracarts.mods.enderio.blocks.TileEntityRFLoaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Skylar on 11/13/2014.
 */
public class GuiRFLoaders extends GuiContainer {
	private TileEntityRFLoaders tileEntityRFLoaders;

	protected static final int POWER_Y = 14;
	protected final int POWER_X = 15;
	protected static final int POWER_WIDTH = 10;
	protected static final int POWER_HEIGHT = 42;
	protected static final int BOTTOM_POWER_Y = POWER_Y + POWER_HEIGHT;

	public GuiRFLoaders(InventoryPlayer inventoryPlayer, TileEntityRFLoaders tileEntityRFLoaders) {
		super(new ContainerRFLoaders(inventoryPlayer, tileEntityRFLoaders));
		this.tileEntityRFLoaders = tileEntityRFLoaders;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		//draw text and stuff here
		//the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Tiny", 8, 6, 4210752);
		//draws "Inventory" or your regional equivalent
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}
	//EIO
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("enderio:textures/gui/poweredSpawner.png"));
		int sx = (width - xSize) / 2;
		int sy = (height - ySize) / 2;

		drawTexturedModalRect(sx, sy, 0, 0, this.xSize, this.ySize);

		renderPowerBar(sx, sy);

		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	}
	//EIO
	public void renderPowerBar(int k, int l) {
		int i1 = tileEntityRFLoaders.getEnergyStoredScaled(getPowerHeight());
		// x, y, u, v, width, height
		drawTexturedModalRect(k + getPowerX(), l + (getPowerY() + getPowerHeight()) - i1, getPowerU(), getPowerV(), getPowerWidth(), i1);
	}
	//EIO
	protected int getPowerX() {
		return POWER_X;
	}
	//EIO
	protected int getPowerY() {
		return POWER_Y;
	}
	//EIO
	protected int getPowerWidth() {
		return POWER_WIDTH;
	}
	//EIO
	protected int getPowerHeight() {
		return POWER_HEIGHT;
	}
	//EIO
	protected int getPowerV() {
		return 31;
	}
	//EIO
	protected int getPowerU() {
		return 176;
	}
}
