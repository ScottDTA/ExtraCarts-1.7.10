package com.dta.extracarts.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Skylar on 10/16/2014.
 */
public interface OpenableGUI {
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z);
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);
}
