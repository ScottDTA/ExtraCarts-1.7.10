package com.dta.extracarts.mods.enderio.client;

import com.dta.extracarts.mods.enderio.blocks.TileEntityRFLoaders;
import crazypants.render.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Skylar on 11/13/2014.
 */
public class GuiRFLoaders extends GuiContainer {
	public GuiRFLoaders(InventoryPlayer inventoryPlayer, TileEntityRFLoaders tileEntityRFLoaders) {
		super(new ContainerRFLoaders(inventoryPlayer, tileEntityRFLoaders));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderUtil.bindTexture("enderio:textures/gui/poweredSpawner.png");
		int sx = (width - xSize) / 2;
		int sy = (height - ySize) / 2;

		drawTexturedModalRect(sx, sy, 0, 0, xSize, ySize);
	}
}
