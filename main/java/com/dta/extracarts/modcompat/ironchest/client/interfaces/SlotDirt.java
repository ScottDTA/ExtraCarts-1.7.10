package com.dta.extracarts.modcompat.ironchest.client.interfaces;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDirt extends Slot {
	
	public SlotDirt(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == new ItemStack(Blocks.dirt).getItem();
	}
}
