package com.dta.extracarts.mods.minechem.renderers;

import com.dta.extracarts.othermodcode.minechem.reference.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

/**
 * Created by Skylar on 3/31/2015.
 */
@SideOnly(Side.CLIENT)
public class RenderLeadedChestCart extends RenderMinecart {

	public RenderLeadedChestCart() {
	}

	public void doRender(EntityMinecart entityMinecart, double xCoord, double yCoord, double zCoord, float p_76986_8_,
						 float p_76986_9_) {
		GL11.glPushMatrix();
		this.bindEntityTexture(entityMinecart);
		long i = (long)entityMinecart.getEntityId() * 493286711L;
		i = i * i * 4392167121L + i * 98761L;
		float f2 = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f3 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f4 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		GL11.glTranslatef(f2, f3, f4);
		double d3 = entityMinecart.lastTickPosX + (entityMinecart.posX - entityMinecart.lastTickPosX) * (double)p_76986_9_;
		double d4 = entityMinecart.lastTickPosY + (entityMinecart.posY - entityMinecart.lastTickPosY) * (double)p_76986_9_;
		double d5 = entityMinecart.lastTickPosZ + (entityMinecart.posZ - entityMinecart.lastTickPosZ) * (double)p_76986_9_;
		double d6 = 0.30000001192092896D;
		Vec3 vec3 = entityMinecart.func_70489_a(d3, d4, d5);
		float f5 = entityMinecart.prevRotationPitch + (entityMinecart.rotationPitch - entityMinecart.prevRotationPitch) * p_76986_9_;

		if (vec3 != null) {
			Vec3 vec31 = entityMinecart.func_70495_a(d3, d4, d5, d6);
			Vec3 vec32 = entityMinecart.func_70495_a(d3, d4, d5, -d6);

			if (vec31 == null) {
				vec31 = vec3;
			}

			if (vec32 == null) {
				vec32 = vec3;
			}

			xCoord += vec3.xCoord - d3;
			yCoord += (vec31.yCoord + vec32.yCoord) / 2.0D - d4;
			zCoord += vec3.zCoord - d5;
			Vec3 vec33 = vec32.addVector(-vec31.xCoord, -vec31.yCoord, -vec31.zCoord);

			if (vec33.lengthVector() != 0.0D) {
				vec33 = vec33.normalize();
				p_76986_8_ = (float)(Math.atan2(vec33.zCoord, vec33.xCoord) * 180.0D / Math.PI);
				f5 = (float)(Math.atan(vec33.yCoord) * 73.0D);
			}
		}

		GL11.glTranslatef((float)xCoord, (float)yCoord, (float)zCoord);
		GL11.glRotatef(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-f5, 0.0F, 0.0F, 1.0F);
		float f7 = (float)entityMinecart.getRollingAmplitude() - p_76986_9_;
		float f8 = entityMinecart.getDamage() - p_76986_9_;

		if (f8 < 0.0F) {
			f8 = 0.0F;
		}

		if (f7 > 0.0F) {
			GL11.glRotatef(MathHelper.sin(f7) * f7 * f8 / 10.0F * (float)entityMinecart.getRollingDirection(), 1.0F, 0.0F, 0.0F);
		}

		int k = entityMinecart.getDisplayTileOffset();

		GL11.glPushMatrix();
		ModelChest modelChest = new ModelChest();
		this.bindTexture(Resources.Model.LEADED_CHEST);
		GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-0.38F, -0.65F, -0.38F);
		float f6 = 0.75F;
		GL11.glScalef(f6, f6, f6);
		modelChest.chestLid.rotateAngleX = 0;
		modelChest.renderAll();

		GL11.glTranslatef(0.0F, (float) k / 16.0F, 0.0F);

		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.bindEntityTexture(entityMinecart);

		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelMinecart.render(entityMinecart, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}
