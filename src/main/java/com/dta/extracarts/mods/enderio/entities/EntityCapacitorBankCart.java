package com.dta.extracarts.mods.enderio.entities;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyStorage;
import com.dta.extracarts.block.FakeBlockRegistry;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.enderio.container.ContainerCapacitorBankCart;
import com.dta.extracarts.mods.enderio.gui.GuiCapacitorBankCart;
import com.enderio.core.common.vecmath.VecmathUtil;
import crazypants.enderio.machine.capbank.CapBankType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/9/2015.
 */
abstract public class EntityCapacitorBankCart extends EntityExtraCartChestMinecart implements IEnergyStorage, OpenableGUI {
	private static final int INPUT_ID = 29;
	private static final int OUTPUT_ID = 30;
	private static final int STORED_ENERGY_ID = 31;

	public EntityCapacitorBankCart(World world) {
		this(world, 0);
	}

	public EntityCapacitorBankCart(World world, int energy) {
		super(world);
		this.setDisplayTileData(FakeBlockRegistry.getFakeBlockByName(getBlockName()).getMetaNumber());
		minecartContainerItems = new ItemStack[getSizeInventory()];
		setEnergyStored(energy);
		setMaxInput(getCapBankType().getMaxIO());
		setMaxOutput(getCapBankType().getMaxIO());
	}

	@Override
	public void entityInit(){
		super.entityInit();
		dataWatcher.addObject(INPUT_ID, 0);
		dataWatcher.addObject(OUTPUT_ID, 0);
		dataWatcher.addObject(STORED_ENERGY_ID, 0);
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
	public boolean interactFirst(EntityPlayer player) {
		return super.interactFirst(player);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
		super.readEntityFromNBT(nbtTagCompound);
		setEnergyStored(nbtTagCompound.getInteger("Energy"));
		setMaxInput(nbtTagCompound.getInteger("Input"));
		setMaxOutput(nbtTagCompound.getInteger("Output"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
		super.writeEntityToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("Energy", getEnergyStored());
		nbtTagCompound.setInteger("Input", getMaxInput());
		nbtTagCompound.setInteger("Output", getMaxOutput());
	}

	@Override
	public void onUpdate() {
		chargeItems(getMinecartContainerItems());
		super.onUpdate();
	}

	abstract public CapBankType getCapBankType();

	abstract public String getBlockName();

	public boolean chargeItems(ItemStack[] items) {
		if(items == null) {
			return false;
		}
		boolean chargedItem = false;
		int available = Math.min(getEnergyStored(), getMaxIO());
		for (ItemStack item : items) {
			if(item != null && available > 0) {
				int used = 0;
				if(item.getItem() instanceof IEnergyContainerItem) {
					IEnergyContainerItem chargeable = (IEnergyContainerItem) item.getItem();

					int max = chargeable.getMaxEnergyStored(item);
					int cur = chargeable.getEnergyStored(item);
					int canUse = Math.min(available, max - cur);
					if(cur < max) {
						used = chargeable.receiveEnergy(item, canUse, false);
					}

				}
				if(used > 0) {
					addEnergy(-used);
					chargedItem = true;
					available -= used;
				}
			}
		}
		return chargedItem;
	}

	public void addEnergy(int energy) {
		if(!getCapBankType().isCreative()) {
			setEnergyStored(getEnergyStored() + energy);
			if(getEnergyStored() > getMaxEnergyStored()) {
				setEnergyStored(getMaxEnergyStored());
			} else if(getEnergyStored() < 0) {
				setEnergyStored(0);
			}
		}
	}

	// Energy Stuff
	public void setEnergyStored(int stored) {
		if (stored > getMaxEnergyStored()) {
			stored = getMaxEnergyStored();
		} else if (stored < 0) {
			stored = 0;
		}
		dataWatcher.updateObject(STORED_ENERGY_ID, stored);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int energyReceived = Math.min(getMaxEnergyStored() - getEnergyStored(), Math.min(getMaxInput(), maxReceive));

		if (!simulate) {
			setEnergyStored(getEnergyStored() + energyReceived);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int energyExtracted = Math.min(getEnergyStored(), Math.min(getMaxOutput(), maxExtract));

		if (!simulate) {
			setEnergyStored(getEnergyStored()- energyExtracted);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {
		return dataWatcher.getWatchableObjectInt(STORED_ENERGY_ID);
	}

	@Override
	public int getMaxEnergyStored() {
		return getCapBankType().getMaxEnergyStored();
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

	public void setMaxOutput(int maxOutput) {
		dataWatcher.updateObject(OUTPUT_ID, MathHelper.clamp_int(maxOutput, 0, getMaxIO()));
	}

	public void setMaxInput(int maxInput) {
		dataWatcher.updateObject(INPUT_ID, MathHelper.clamp_int(maxInput, 0, getMaxIO()));
	}

	public int getMaxOutput() {
		return dataWatcher.getWatchableObjectInt(OUTPUT_ID);
	}

	public int getMaxInput() {
		return dataWatcher.getWatchableObjectInt(INPUT_ID);
	}

	public int getMaxIO() {
		return getCapBankType().getMaxIO();
	}

	public double getEnergyStoredRatio() {
		if(getMaxEnergyStored() <= 0) {
			return 0;
		}
		return (double) getEnergyStored() / getMaxEnergyStored();
	}

	public int getEnergyStoredScaled(int scale) {
		return (int) VecmathUtil.clamp(Math.round(scale * this.getEnergyStoredRatio()), 0, scale);
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
