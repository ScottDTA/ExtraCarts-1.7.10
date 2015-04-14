package com.dta.extracarts.mods.enderio.entities;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyStorage;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.enderio.container.ContainerCapacitorBankCart;
import com.dta.extracarts.mods.enderio.gui.GuiCapacitorBankCart;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.machine.capbank.CapBankType;
import crazypants.vecmath.VecmathUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/9/2015.
 */
public class EntityCapacitorBankCart extends EntityExtraCartChestMinecart implements IEnergyStorage, OpenableGUI
		/*IEntityAdditionalSpawnData*/ {
	private Block capBank = GameRegistry.findBlock("EnderIO", "blockCapBank");

	private static final int INPUT_ID = 28;
	private static final int OUTPUT_ID = 29;
	private static final int CAP_BANK_TYPE_ID = 30;
	private static final int STORED_ENERGY_ID = 31;

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
		setCapBankType(capBankType);
		setEnergyStored(energy);
		setMaxInput(getCapBankType().getMaxIO());
		setMaxOutput(getCapBankType().getMaxIO());
	}

	@Override
	public void entityInit(){
		super.entityInit();
		dataWatcher.addObject(INPUT_ID, 0);
		dataWatcher.addObject(OUTPUT_ID, 0);
		dataWatcher.addObject(CAP_BANK_TYPE_ID, 0);
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
	public Block func_145817_o() {
		return capBank;
	}

	@Override
	public Block func_145820_n() {
		return capBank;
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		return super.interactFirst(player);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
		super.readEntityFromNBT(nbtTagCompound);
		setEnergyStored(nbtTagCompound.getInteger("Energy"));
		setCapBankType(nbtTagCompound.getInteger("CapBankType"));
		setMaxInput(nbtTagCompound.getInteger("Input"));
		setMaxOutput(nbtTagCompound.getInteger("Output"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
		super.writeEntityToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("Energy", getEnergyStored());
		nbtTagCompound.setInteger("CapBankType", getCapBankTypeInt());
		nbtTagCompound.setInteger("Input", getMaxInput());
		nbtTagCompound.setInteger("Output", getMaxOutput());
	}
/*
	@Override
	public void writeSpawnData(ByteBuf data) {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		nbtTagCompound.setInteger("Energy", getEnergyStored());
		nbtTagCompound.setInteger("CapBankType", getCapBankTypeInt());
		nbtTagCompound.setInteger("Input", getMaxInput());
		nbtTagCompound.setInteger("Output", getMaxOutput());
		ByteBufUtils.writeTag(data, nbtTagCompound);
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		try {
			NBTTagCompound nbtTagCompound = ByteBufUtils.readTag(data);
			setEnergyStored(nbtTagCompound.getInteger("Energy"));
			setCapBankType(nbtTagCompound.getInteger("CapBankType"));
			setMaxInput(nbtTagCompound.getInteger("Input"));
			setMaxOutput(nbtTagCompound.getInteger("Output"));
		}
		catch(Exception e) {
			LogUtils.debug("Failed to retreive information from server.");
			super.setDead();
			e.printStackTrace();
		}
	}*/

	public void setCapBankType(int capBankType) {
		dataWatcher.updateObject(CAP_BANK_TYPE_ID, capBankType);
		int maxIO = CapBankType.getTypeFromMeta(capBankType).getMaxIO();
		if(getMaxInput() > maxIO) {
			setMaxInput(maxIO);
		}
		if(getMaxOutput() > maxIO) {
			setMaxOutput(maxIO);
		}
	}

	public int getCapBankTypeInt() {
		return dataWatcher.getWatchableObjectInt(CAP_BANK_TYPE_ID);
	}

	public CapBankType getCapBankType() {
		return CapBankType.getTypeFromMeta(dataWatcher.getWatchableObjectInt(CAP_BANK_TYPE_ID));
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

	// Client Network stuff
	public void setMaxOutput(int maxOutput) {
		if(maxOutput > getMaxIO()) {
			maxOutput = getMaxIO();
		} else if(maxOutput < 0) {
			maxOutput = 0;
		}
		dataWatcher.updateObject(OUTPUT_ID, maxOutput);
	}

	public void setMaxInput(int maxInput) {
		if(maxInput > getMaxIO()) {
			maxInput = getMaxIO();
		} else if(maxInput < 0) {
			maxInput = 0;
		}
		dataWatcher.updateObject(INPUT_ID, maxInput);
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
