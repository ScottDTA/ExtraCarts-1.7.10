package com.dta.extracarts.mods.minechem.client;

import com.dta.extracarts.mods.minechem.entities.EntityLeadedChestCart;
import com.dta.extracarts.othermodcode.minechem.gui.GuiContainerTabbed;
import com.dta.extracarts.othermodcode.minechem.gui.GuiTabHelp;
import com.dta.extracarts.othermodcode.minechem.gui.GuiTabPatreon;
import com.dta.extracarts.othermodcode.minechem.reference.Reference;
import com.dta.extracarts.othermodcode.minechem.reference.Textures;
import com.dta.extracarts.othermodcode.minechem.utils.MinechemUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Skylar on 4/1/2015.
 */
public class GUILeadedChestCart extends GuiContainerTabbed {
	int guiWidth = 176;
	int guiHeight = 217;

	public GUILeadedChestCart(IInventory invPlayer, EntityLeadedChestCart cart) {
		super(new ContainerLeadedChestCart(invPlayer, cart));

		addTab(new GuiTabHelp(this, MinechemUtil.getLocalString("help.leadChest")));
		addTab(new GuiTabPatreon(this));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		super.drawGuiContainerForegroundLayer(par1, par2);
		String info = MinechemUtil.getLocalString("gui.title.leadedchest");
		int infoWidth = this.fontRendererObj.getStringWidth(info);
		this.fontRendererObj.drawString(info, (guiWidth - infoWidth) / 2, 5, 0x000000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.ID, Textures.Gui.LEADED_CHEST));

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0.0F);
		GL11.glPopMatrix();
	}
}
