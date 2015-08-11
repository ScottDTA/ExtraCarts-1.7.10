package com.dta.extracarts.mods.enderio.gui;

import com.dta.extracarts.mods.enderio.container.ContainerRFLoader;
import com.dta.extracarts.mods.enderio.tileentity.TileEntityRFLoader;
import com.enderio.core.client.gui.GuiContainerBase;
import com.enderio.core.client.gui.widget.GuiToolTip;
import com.enderio.core.client.gui.widget.TextFieldEnder;
import com.enderio.core.client.render.RenderUtil;
import crazypants.enderio.machine.power.PowerDisplayUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by Skylar on 5/27/2015.
 */
public class GuiRFLoader extends GuiContainerBase {
	protected TileEntityRFLoader tileEntityRFLoader;

	//EIO
	protected static final int POWER_Y = 14;
	protected final int POWER_X = 15;
	protected static final int POWER_WIDTH = 10;
	protected static final int POWER_HEIGHT = 42;

	private int inputX = 78 + 24;
	private int inputY = 18;

	private TextFieldEnder maxIOTF;

	public GuiRFLoader(InventoryPlayer inventoryPlayer, final TileEntityRFLoader tileEntityRFLoader) {
		super(new ContainerRFLoader(inventoryPlayer, tileEntityRFLoader));
		this.tileEntityRFLoader = tileEntityRFLoader;

		FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

		addToolTip(new GuiToolTip(new Rectangle(getPowerX(), getPowerY(), getPowerWidth(), getPowerHeight()), "") {
			@Override
			protected void updateText() {
				text.clear();
				text.add(PowerDisplayUtil.formatPower(tileEntityRFLoader.getEnergyStored(ForgeDirection.UNKNOWN)) + " "
						+ PowerDisplayUtil.ofStr());
				text.add(EnumChatFormatting.WHITE + PowerDisplayUtil
						.formatPower(tileEntityRFLoader.getMaxEnergyStored(ForgeDirection.UNKNOWN)) + " "
						+ EnumChatFormatting.GRAY + PowerDisplayUtil.abrevation());
			}
		});

		int x = inputX - 24;
		int y = inputY;
		maxIOTF = new TextFieldEnder(fontRenderer, x, y, 68, 16);
		maxIOTF.setMaxStringLength(10);
		maxIOTF.setCharFilter(TextFieldEnder.FILTER_NUMERIC);

		textFields.add(maxIOTF);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderUtil.bindTexture("enderio:textures/gui/poweredSpawner.png");
		int sx = (width - xSize) / 2;
		int sy = (height - ySize) / 2;

		drawTexturedModalRect(sx, sy, 0, 0, xSize, ySize);

		renderPowerBar(sx, sy);

		super.drawGuiContainerBackgroundLayer(par1, par2, par3);
	}

	//EIO
	public void renderPowerBar(int k, int l) {
		int i1 = tileEntityRFLoader.getEnergyStoredScaled(getPowerHeight());
		// x, y, u, v, width, height
		drawTexturedModalRect(k + getPowerX(), l + (getPowerY() + getPowerHeight()) - i1, getPowerU(), getPowerV(), getPowerWidth(), i1);
	}

	protected int getPowerX() {
		return POWER_X;
	}

	protected int getPowerY() {
		return POWER_Y;
	}

	protected int getPowerWidth() {
		return POWER_WIDTH;
	}

	protected int getPowerHeight() {
		return POWER_HEIGHT;
	}

	protected int getPowerV() {
		return 31;
	}

	protected int getPowerU() {
		return 176;
	}
}
