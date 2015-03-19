package com.dta.extracarts.mods.mfr.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.dta.extracarts.mods.mfr.entities.EntityDSUCarts;

public class GuiDSUCart extends GuiContainer {

	private static final ResourceLocation DSUTexture = new ResourceLocation("minefactoryreloaded", "textures/gui/deepstorageunit.png");
	private EntityDSUCarts cart = null;
		
	@Override
	public void initGui() {
		super.initGui();
	}
	
	public GuiDSUCart(IInventory invPlayer, EntityDSUCarts cart) {
		super(new ContainerDSUCart(invPlayer, cart));
		this.cart = cart;
		this.ySize=205;
		this.xSize=184;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(DSUTexture);
		int left = (width - xSize)/2;
		int top = (height - ySize)/2;
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 184, 205);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		fontRendererObj.drawString("Stored:", 110, 70, 4210752);
		fontRendererObj.drawString("Deep Storage Unit", 8, 6, 4210752);
		fontRendererObj.drawString("Inventory", 8, ySize - 96 + 5, 4210752);
		fontRendererObj.drawString(((Integer)cart.getQuantity()).toString(), 110, 80, 4210752);
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
	}

}
