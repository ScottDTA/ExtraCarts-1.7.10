package com.dta.extracarts.modcompat.ironchest.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGoldChestCart extends GuiContainer {
	private static final ResourceLocation ironChestTexture = new ResourceLocation("ironchest", "textures/gui/goldcontainer.png");

	public GuiGoldChestCart(IInventory invPlayer, IInventory cart) {
		super(new ContainerGoldChestCart(invPlayer, cart));
		this.ySize=256;
		this.xSize=184;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(ironChestTexture);
		int left = (width - xSize)/2;
		int top = (height - ySize)/2;
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 184, 256);
	}
}
