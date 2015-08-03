package com.dta.extracarts.mods.enderio.container;

import cofh.api.energy.IEnergyContainerItem;
import com.dta.extracarts.client.ContainerExtraChestCart;
import com.dta.extracarts.mods.enderio.entities.EntityCapacitorBankCart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by Skylar on 4/11/2015.
 */
public class ContainerCapacitorBankCart extends ContainerExtraChestCart {
	private EntityCapacitorBankCart entityCapacitorBankCart;

	public ContainerCapacitorBankCart(final EntityPlayer entityPlayer,final IInventory player, EntityCapacitorBankCart entityCapacitorBankCart) {
		this.entityCapacitorBankCart = entityCapacitorBankCart;

		int armorOffset = 21;
		for(int x = 0; x < 4; x++) {
			addSlotToContainer(new Slot(this.entityCapacitorBankCart, x, 59 + armorOffset + (x * 20), 59) {
				@Override
				public boolean isItemValid(ItemStack itemStack) {
					return isItemElectric(itemStack);
				}
			});
		}

		// add players inventory
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(player, j + i * 9 + 9, armorOffset + 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			addSlotToContainer(new Slot(player, i, armorOffset + 8 + i * 18, 142));
		}

		//armor slots
		for (int i = 0; i < 4; ++i) {
			final int k = i;
			addSlotToContainer(new Slot(player, player.getSizeInventory() - 1 - i, -15 + armorOffset, 12 + i * 18) {

				@Override
				public int getSlotStackLimit() {
					return 1;
				}

				@Override
				public boolean isItemValid(ItemStack par1ItemStack) {
					if (par1ItemStack == null) {
						return false;
					}
					return par1ItemStack.getItem().isValidArmor(par1ItemStack, k, ((InventoryPlayer) player).player);
				}

				@Override
				@SideOnly(Side.CLIENT)
				public IIcon getBackgroundIconIndex() {
					return ItemArmor.func_94602_b(k);
				}
			});
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		int startPlayerSlot = 4;
		int endPlayerSlot = startPlayerSlot + 26;
		int startHotBarSlot = endPlayerSlot + 1;
		int endHotBarSlot = startHotBarSlot + 9;

		ItemStack copystack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);
		if(slot != null && slot.getHasStack()) {

			ItemStack origStack = slot.getStack();
			copystack = origStack.copy();

			if(slotIndex < 4) {
				// merge from machine input slots to inventory
				if(!mergeItemStackIntoArmor(entityPlayer, origStack, slotIndex)) {
					if(!mergeItemStack(origStack, startPlayerSlot, endHotBarSlot, false)) {
						return null;
					}
				}

			} else {
				//Check from inv-> charge then inv->hotbar or hotbar->inv
				if(slotIndex >= startPlayerSlot) {
					if(!isItemElectric(origStack) || !mergeItemStack(origStack, 0, 4, false)) {

						if(slotIndex <= endPlayerSlot) {
							if(!mergeItemStack(origStack, startHotBarSlot, endHotBarSlot, false)) {
								return null;
							}
						} else if(slotIndex >= startHotBarSlot && slotIndex <= endHotBarSlot) {
							if(!mergeItemStack(origStack, startPlayerSlot, endPlayerSlot, false)) {
								return null;
							}
						}

					}
				}
			}

			if(origStack.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}

			slot.onSlotChanged();

			if(origStack.stackSize == copystack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(entityPlayer, origStack);
		}

		return copystack;
	}

	private boolean mergeItemStackIntoArmor(EntityPlayer entityPlayer, ItemStack origStack, int slotIndex) {
		if(origStack == null || !(origStack.getItem() instanceof ItemArmor)) {
			return false;
		}
		ItemArmor armor = (ItemArmor) origStack.getItem();
		int index = 3 - armor.armorType;
		ItemStack[] ai = entityPlayer.inventory.armorInventory;
		if(ai[index] == null) {
			ai[index] = origStack.copy();
			origStack.stackSize = 0;
			return true;
		}
		return false;
	}

	private boolean isItemElectric(ItemStack itemStack) {
		if(itemStack == null) {
			return false;
		}
		return itemStack.getItem() instanceof IEnergyContainerItem;
	}
}
