package com.dta.extracarts.mods.minechem.client;

import com.dta.extracarts.container.ContainerExtraChestCart;
import com.dta.extracarts.mods.minechem.entities.EntityLeadedChestCart;
import minechem.api.INoDecay;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skylar on 3/30/2015.
 */
public class ContainerLeadedChestCart extends ContainerExtraChestCart implements INoDecay {
	public ContainerLeadedChestCart(IInventory inventoryPlayer, EntityLeadedChestCart cart) {
		//super(invPlayer, cart, 176, 217, 1, 9);
		this.setCart(cart);

		this.bindOutputSlots();
		this.bindPlayerInventory(inventoryPlayer);
	}

	//Begin Minechem Code
	private void bindOutputSlots()
	{
		int x = 8;
		int y = 18;
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(getCart(), i, x + (i * 18), y));
		}
	}

	private void bindPlayerInventory(IInventory inventoryPlayer)
	{
		int inventoryY = 50;
		int hotBarY = 108;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, inventoryY + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, hotBarY));
		}
	}

	@Override
	public List<ItemStack> getStorageInventory() {
		List<ItemStack> storageInventory = new ArrayList<ItemStack>();
		for (int slot = 0; slot < 9; slot++) {
			ItemStack stack = getSlot(slot).getStack();
			if (stack != null) {
				storageInventory.add(stack);
			}
		}
		return storageInventory;
	}

	@Override
	public List<ItemStack> getPlayerInventory() {
		List<ItemStack> playerInventory = new ArrayList<ItemStack>();
		for (int slot = 9; slot < this.inventorySlots.size(); slot++) {
			ItemStack stack = getSlot(slot).getStack();
			if (stack != null) {
				playerInventory.add(stack);
			}
		}
		return playerInventory;
	}
	// End Minechem code
}
