package com.dta.extracarts.mods.enderio.entities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyStorage;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.machine.capbank.CapBankType;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/9/2015.
 */
public class CapacitorBankCart extends EntityExtraCartChestMinecart implements IEnergyStorage {
	private Block block = GameRegistry.findBlock("EnderIO", "capbank");
	private EnergyStorage energyStorage;
	private CapBankType type;

	public CapacitorBankCart(World world, int capBankType) {
		super(world);
		this.setDisplayTileData(capBankType);
		minecartContainerItems = new ItemStack[getSizeInventory()];
		type = CapBankType.getTypeFromMeta(capBankType);
		energyStorage = new EnergyStorage(type.getMaxEnergyStored(), type.getMaxIO(), type.getMaxIO());
	}

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public int getMinecartType() {
		return 1;
	}

	public CapBankType getType() {
		return type;
	}

	// Energy Stuff
	public void setEnergyStored(int stored) {
		energyStorage.setEnergyStored(stored);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return energyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return energyStorage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored() {
		return energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored() {
		return energyStorage.getMaxEnergyStored();
	}

	// Inventory Stuff
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		if(itemstack == null) {
			return false;
		}
		return itemstack.getItem() instanceof IEnergyContainerItem;
	}
}
