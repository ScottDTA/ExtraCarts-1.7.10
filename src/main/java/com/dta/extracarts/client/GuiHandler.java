package com.dta.extracarts.client;

import com.dta.extracarts.ExtraCarts;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ExtraCarts.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		Object object = ((world.getEntityByID(x) == null) ? world.getTileEntity(x, y, z) : world.getEntityByID(x));
		if(object instanceof OpenableGUI) {
			return ((OpenableGUI) object).getServerGuiElement(ID, player, world, x, y, z);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		Object object = ((world.getEntityByID(x) == null) ? world.getTileEntity(x, y, z) : world.getEntityByID(x));
		if(object instanceof OpenableGUI) {
			return ((OpenableGUI) object).getClientGuiElement(ID, player, world, x, y, z);
		}
		return null;
	}

}
