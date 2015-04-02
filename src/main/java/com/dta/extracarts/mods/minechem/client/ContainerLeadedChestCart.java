package com.dta.extracarts.mods.minechem.client;

import com.dta.extracarts.client.ContainerExtraChestCart;
import com.dta.extracarts.mods.minechem.entities.EntityLeadedChestCart;
import minechem.api.INoDecay;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skylar on 3/30/2015.
 */
public class ContainerLeadedChestCart extends ContainerExtraChestCart implements INoDecay {
	public ContainerLeadedChestCart(IInventory invPlayer, EntityLeadedChestCart cart) {
		super(invPlayer, cart, 176, 217, 1, 9);
	}

	//Begin Minechem Code
	public List<ItemStack> getStorageInventory()
	{
		List<ItemStack> storageInventory = new ArrayList<ItemStack>();
		for (int slot = 0; slot < 9; slot++)
		{
			ItemStack stack = getSlot(slot).getStack();
			if (stack != null)
			{
				storageInventory.add(stack);
			}
		}
		return storageInventory;
	}

	public List<ItemStack> getPlayerInventory() {
		List<ItemStack> playerInventory = new ArrayList<ItemStack>();
		for (int slot = 9; slot < this.inventorySlots.size(); slot++)
		{
			ItemStack stack = getSlot(slot).getStack();
			if (stack != null)
			{
				playerInventory.add(stack);
			}
		}
		return playerInventory;
	}
	//End Minechem Code
}
