package com.dta.extracarts.mods.enderio.container;

import com.dta.extracarts.container.IGhostSlot;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 8/14/2015.
 */
public class SlotMinecart extends Slot implements IGhostSlot{
	public SlotMinecart(IInventory iInventory, int slotNumber, int x, int y) {
		super(iInventory, slotNumber, x, y);
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack itemStack) {
		return itemStack.getItem() instanceof ItemMinecart || itemStack.getItem() instanceof IMinecart;
	}

	public boolean canTakeStack(EntityPlayer entityPlayer) {
		return false;
	}

	@Override
	public boolean canChange() {
		return true;
	}
}
