package com.dta.extracarts.mods.enderio.client;

import com.dta.extracarts.mods.enderio.blocks.TileEntityRFLoaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Skylar on 11/13/2014.
 */
public class GuiRFLoaders extends GuiContainer {
	private TileEntityRFLoaders tileEntityRFLoaders;

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

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("enderio:textures/gui/stirlingGenerator.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
