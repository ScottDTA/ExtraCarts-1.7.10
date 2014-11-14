package com.dta.extracarts.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {
	
	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ExtraCarts.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		Entity entity = world.getEntityByID(x);
		if(entity instanceof OpenableGUI) {
			return ((OpenableGUI) entity).getServerGuiElement(ID, player, world, x, y, z);
		}
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof OpenableGUI) {
			return ((OpenableGUI) tileEntity).getServerGuiElement(ID, player, world, x, y, z);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		Entity entity = world.getEntityByID(x);
		if(entity instanceof OpenableGUI) {
			return ((OpenableGUI) entity).getClientGuiElement(ID, player, world, x, y, z);
		}
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof OpenableGUI) {
			return ((OpenableGUI) tileEntity).getClientGuiElement(ID, player, world, x, y, z);
		}
		return null;
	}

}
