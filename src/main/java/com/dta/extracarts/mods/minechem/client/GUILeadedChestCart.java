package com.dta.extracarts.mods.minechem.client;

import com.dta.extracarts.mods.minechem.entities.EntityLeadedChestCart;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Skylar on 4/1/2015.
 */
public class GUILeadedChestCart extends GuiContainer {
	int guiWidth = 176;
	int guiHeight = 217;

	public GUILeadedChestCart(IInventory invPlayer, EntityLeadedChestCart cart) {
		super(new ContainerLeadedChestCart(invPlayer, cart));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		super.drawGuiContainerForegroundLayer(par1, par2);
		String info = getLocalString("gui.title.leadedchest");
		int infoWidth = this.fontRendererObj.getStringWidth(info);
		this.fontRendererObj.drawString(info, (guiWidth - infoWidth) / 2, 5, 0x000000);
	}

	public static String getLocalString(String key)
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			String localString = StatCollector.translateToLocal(key);
			return localString;
		}
		return key;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("minechem", "textures/gui/LeadedChestGUI.png"));

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0.0F);
		GL11.glPopMatrix();
	}
}
