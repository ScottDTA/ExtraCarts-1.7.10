package com.dta.extracarts;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Skylar on 12/14/2014.
 */
public class ClientProxy extends CommonProxy {
	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
}
