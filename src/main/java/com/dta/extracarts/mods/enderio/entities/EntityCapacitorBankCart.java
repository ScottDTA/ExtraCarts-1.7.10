package com.dta.extracarts.mods.enderio.entities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyStorage;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.enderio.container.ContainerCapacitorBankCart;
import com.dta.extracarts.mods.enderio.gui.GuiCapacitorBankCart;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.machine.capbank.CapBankType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/9/2015.
 */
public class EntityCapacitorBankCart extends EntityExtraCartChestMinecart implements IEnergyStorage, OpenableGUI {
	private Block capBank = GameRegistry.findBlock("EnderIO", "blockCapBank");
	private EnergyStorage energyStorage;
	private CapBankType type;

	public EntityCapacitorBankCart(World world) {
		this(world, 0);
	}

	public EntityCapacitorBankCart(World world, int capBankType) {
		this(world, capBankType, 0);
	}

	public EntityCapacitorBankCart(World world, int capBankType, int energy) {
		super(world);
		this.setDisplayTileData(capBankType);
		minecartContainerItems = new ItemStack[getSizeInventory()];
		type = CapBankType.getTypeFromMeta(capBankType);
		energyStorage = new EnergyStorage(type.getMaxEnergyStored(), type.getMaxIO(), type.getMaxIO());
		this.setEnergyStored(energy);
	}

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public int getMinecartType() {
		return 1;
	}

	@Override
	public Block func_145817_o() {
		return capBank;
	}

	@Override
	public Block func_145820_n() {
		return capBank;
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

	// Client Network stuff
	public double getEnergyStoredRatio() {
		if(getMaxEnergyStored() <= 0) {
			return 0;
		}
		return (double) getEnergyStored() / getMaxEnergyStored();
	}

	public int getMaxOutput() {
		return 0;
	}

	public int getMaxInput() {
		return 0;
	}

	public int getMaxIO() {
		return 0;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiCapacitorBankCart(player, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerCapacitorBankCart(player, player.inventory, this);
	}
}
