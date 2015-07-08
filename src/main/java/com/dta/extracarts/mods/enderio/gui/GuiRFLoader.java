package com.dta.extracarts.mods.enderio.gui;

import com.dta.extracarts.mods.enderio.container.ContainerRFLoader;
import com.dta.extracarts.mods.enderio.tileentity.TileEntityRFLoader;
import com.enderio.core.client.render.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Skylar on 5/27/2015.
 */
public class GuiRFLoader extends GuiContainer {
	protected TileEntityRFLoader tileEntityRFLoader;

	//EIO
	protected static final int POWER_Y = 14;
	protected final int POWER_X = 15;
	protected static final int POWER_WIDTH = 10;
	protected static final int POWER_HEIGHT = 42;

	public GuiRFLoader(InventoryPlayer inventoryPlayer, TileEntityRFLoader tileEntityRFLoader) {
		super(new ContainerRFLoader(inventoryPlayer, tileEntityRFLoader));
		this.tileEntityRFLoader = tileEntityRFLoader;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderUtil.bindTexture("enderio:textures/gui/poweredSpawner.png");
		int sx = (width - xSize) / 2;
		int sy = (height - ySize) / 2;

		drawTexturedModalRect(sx, sy, 0, 0, xSize, ySize);

		renderPowerBar(sx, sy);
	}

	//EIO
	public void renderPowerBar(int k, int l) {
		int i1 = tileEntityRFLoader.getEnergyStoredScaled(getPowerHeight());
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
