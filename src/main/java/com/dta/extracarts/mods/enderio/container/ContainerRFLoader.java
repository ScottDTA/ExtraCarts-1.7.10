package com.dta.extracarts.mods.enderio.container;

import com.dta.extracarts.mods.enderio.tileentity.TileEntityRFLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 5/27/2015.
 */
public class ContainerRFLoader extends Container {
	private TileEntityRFLoader tileEntityRFLoader;
	protected Slot upgradeSlot;

	public ContainerRFLoader(InventoryPlayer inventoryPlayer, final TileEntityRFLoader tileEntityRFLoader){
		super();
		this.setTileEntityRFLoader(tileEntityRFLoader);
		this.bindPlayerInventory(inventoryPlayer);

		upgradeSlot = new Slot(getTileEntityRFLoader(), 0, 12, 60) {

			@Override
			public int getSlotStackLimit() {
				return getTileEntityRFLoader().getInventoryStackLimit();
			}

			@Override
			public boolean isItemValid(ItemStack itemStack) {
				return getTileEntityRFLoader().isItemValidForSlot(0, itemStack);
			}
		};
		addSlotToContainer(upgradeSlot);
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
		return tileEntityRFLoader.isUseableByPlayer(entityPlayer);
	}

	public TileEntityRFLoader getTileEntityRFLoader() {
		return tileEntityRFLoader;
	}

	public void setTileEntityRFLoader(TileEntityRFLoader tileEntityRFLoader) {
		this.tileEntityRFLoader = tileEntityRFLoader;
	}
}
