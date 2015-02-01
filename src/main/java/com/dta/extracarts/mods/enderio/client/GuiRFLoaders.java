package com.dta.extracarts.mods.enderio.client;

import com.dta.extracarts.mods.enderio.blocks.TileEntityRFLoaders;
import crazypants.enderio.machine.gui.GuiPoweredMachineBase;
import crazypants.render.RenderUtil;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Skylar on 11/13/2014.
 */
public class GuiRFLoaders extends GuiPoweredMachineBase<TileEntityRFLoaders> {
	public GuiRFLoaders(InventoryPlayer inventoryPlayer, TileEntityRFLoaders tileEntityRFLoaders) {
		super(tileEntityRFLoaders, new ContainerRFLoaders(inventoryPlayer, tileEntityRFLoaders));
		configB.visible = false;
		configB.enabled = false;
	}

	//EIO
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderUtil.bindTexture("enderio:textures/gui/poweredSpawner.png");
		int sx = (width - xSize) / 2;
		int sy = (height - ySize) / 2;

		drawTexturedModalRect(sx, sy, 0, 0, xSize, ySize);

		super.drawGuiContainerBackgroundLayer(par1, par2, par3);

		RenderUtil.bindTexture("enderio:textures/gui/poweredSpawner.png");
	}
}
