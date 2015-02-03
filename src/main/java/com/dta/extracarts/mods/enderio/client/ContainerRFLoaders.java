package com.dta.extracarts.mods.enderio.client;

import com.dta.extracarts.mods.enderio.blocks.TileEntityRFLoaders;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by Skylar on 11/13/2014.
 */
public class ContainerRFLoaders extends Container {
	private TileEntityRFLoaders tileEntityRFLoaders;

	public ContainerRFLoaders (InventoryPlayer inventoryPlayer, TileEntityRFLoaders tileEntityRFLoaders){
		super();
		this.setTileEntityRFLoaders(tileEntityRFLoaders);
		this.bindPlayerInventory(inventoryPlayer);
	}

	protected void bindPlayerInventory(InventoryPlayer inventory) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return tileEntityRFLoaders.isUseableByPlayer(entityPlayer);
	}

	public TileEntityRFLoaders getTileEntityRFLoaders() {
		return tileEntityRFLoaders;
	}

	public void setTileEntityRFLoaders(TileEntityRFLoaders tileEntityRFLoaders) {
		this.tileEntityRFLoaders = tileEntityRFLoaders;
	}
}
