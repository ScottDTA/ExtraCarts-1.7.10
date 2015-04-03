package com.dta.extracarts.mods.minechem.renderers;

import minechem.tileentity.leadedchest.LeadedChestTileEntity;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.EntityMinecart;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by Skylar on 3/31/2015.
 */
public class RenderLeadedChestCart extends RenderMinecart {
	LeadedChestTileEntity leadedChest;

	public RenderLeadedChestCart() {
		this.leadedChest = new LeadedChestTileEntity();
	}

	public void doRender(EntityMinecart entityMinecart, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		GL11.glPopMatrix();
		TileEntityRendererDispatcher.instance.renderTileEntityAt(this.leadedChest, p_76986_2_ - 0.5,
				p_76986_4_ - 0.3, p_76986_6_ - 0.5, 0.0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		super.doRender(entityMinecart, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
		GL11.glPushMatrix();
	}
}
