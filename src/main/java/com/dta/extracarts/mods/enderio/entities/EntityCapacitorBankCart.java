package com.dta.extracarts.mods.enderio.entities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyStorage;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.enderio.container.ContainerCapacitorBankCart;
import com.dta.extracarts.mods.enderio.gui.GuiCapacitorBankCart;
import com.dta.extracarts.utils.LogUtils;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import crazypants.enderio.machine.capbank.CapBankType;
import crazypants.vecmath.VecmathUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/9/2015.
 */
public class EntityCapacitorBankCart extends EntityExtraCartChestMinecart implements IEnergyStorage, OpenableGUI,
		IEntityAdditionalSpawnData {
	private Block capBank = GameRegistry.findBlock("EnderIO", "blockCapBank");
	private EnergyStorage energyStorage;
	private CapBankType capBankType;

	private int maxInput;
	private int maxOutput;

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
		energyStorage = new EnergyStorage(this.capBankType.getMaxEnergyStored(), this.capBankType.getMaxIO());
		setEnergyStored(energy);
	}

	@Override
	public void entityInit(){
		super.entityInit();
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
		energyStorage = energyStorage.readFromNBT(nbtTagCompound);
		capBankType = capBankType.readTypeFromNBT(nbtTagCompound);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
		super.writeEntityToNBT(nbtTagCompound);
		energyStorage.writeToNBT(nbtTagCompound);
		capBankType.writeTypeToNBT(nbtTagCompound);
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		NBTTagCompound tag = new NBTTagCompound();
		energyStorage.writeToNBT(tag);
		capBankType.writeTypeToNBT(tag);
		ByteBufUtils.writeTag(data, tag);
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		try {
			NBTTagCompound nbtTagCompound = ByteBufUtils.readTag(data);
			energyStorage = energyStorage.readFromNBT(nbtTagCompound);
			capBankType = capBankType.readTypeFromNBT(nbtTagCompound);
		}
		catch(Exception e) {
			LogUtils.debug("Failed to retreive information from server.");
			super.setDead();
			e.printStackTrace();
		}
	}

	public void setCapBankType(int capBankType) {
		setCapBankType(CapBankType.getTypeFromMeta(capBankType));
	}

	public void setCapBankType(CapBankType capBankType) {
		this.capBankType = capBankType;
	}

	public CapBankType getCapBankType() {
		return capBankType;
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
	public double getEnergyStoredRatio() {
		if(getMaxEnergyStored() <= 0) {
			return 0;
		}
		return (double) getEnergyStored() / getMaxEnergyStored();
	}

	public void setMaxOutput(int maxOutput) {
		if(maxOutput > getMaxIO()) {
			maxOutput = getMaxIO();
		}
		this.maxOutput = maxOutput;
	}

	public void setMaxInput(int maxInput) {
		if(maxInput > getMaxIO()) {
			maxInput = getMaxIO();
		}
		this.maxInput = maxInput;
	}

	public int getMaxOutput() {
		return getMaxIO();
	}

	public int getMaxInput() {
		return getMaxIO();
	}

	public int getMaxIO() {
		return getCapBankType().getMaxIO();
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
