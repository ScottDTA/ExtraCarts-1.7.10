package com.dta.extracarts.mods.enderio.client;

import com.dta.extracarts.mods.enderio.blocks.TileEntityRFLoaders;
import crazypants.enderio.machine.gui.AbstractMachineContainer;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by Skylar on 11/13/2014.
 */
public class ContainerRFLoaders extends AbstractMachineContainer {
	public ContainerRFLoaders (InventoryPlayer inventoryPlayer, TileEntityRFLoaders tileEntityRFLoaders){
		super(inventoryPlayer, tileEntityRFLoaders);
	}

	@Override
	protected void addMachineSlots(InventoryPlayer playerInv) {

	}
}
