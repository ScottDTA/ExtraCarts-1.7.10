package com.dta.extracarts.modcompat.ironchest.client.interfaces;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDiamondChestCart extends GuiContainer {
	private static final ResourceLocation ironChestTexture = new ResourceLocation("ironchest", "textures/gui/diamondcontainer.png");

	public GuiDiamondChestCart(IInventory invPlayer, IInventory cart) {
		super(new ContainerDiamondChestCart(invPlayer, cart));
		this.ySize=256;
		this.xSize=238;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(ironChestTexture);
		int left = (width - xSize)/2;
		int top = (height - ySize)/2;
		drawTexturedModalRect(left, top, 0, 0, 238, 256);
	}
}
