package com.dta.extracarts.mods.enderio.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.mods.enderio.container.ContainerRFLoader;
import com.dta.extracarts.mods.enderio.gui.GuiRFLoader;
import mods.railcraft.api.carts.CartTools;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * Created by Skylar on 5/27/2015.
 */
public class TileEntityRFLoader extends TileEntity implements IInventory, OpenableGUI, IEnergyReceiver, IEnergyProvider {
	private int lastSyncPowerStored = 0;

	private LoaderMode loaderMode;

	public TileEntityRFLoader() {
		this(0);
	}

	public TileEntityRFLoader(int metadata) {
		super();
		if(metadata == 0) {
			this.setLoaderMode(new Loader());
		} else {
			this.setLoaderMode(new Unloader());
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(worldObj == null) { // sanity check
			return;
		}

		boolean powerChanged = (lastSyncPowerStored != loaderMode.getEnergyStored() && worldObj.getTotalWorldTime() % 5 == 0);
		if(powerChanged) {
			lastSyncPowerStored = loaderMode.getEnergyStored();
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty();
		}

		if(CartTools.isMinecartOnSide(worldObj, xCoord, yCoord, zCoord, 0, ForgeDirection.DOWN)) {
			List<EntityMinecart> minecartsOnSide = CartTools.getMinecartsOnSide(worldObj, xCoord, yCoord, zCoord, 0,
					ForgeDirection.DOWN);
			if(minecartsOnSide.get(0) != null) {
				loaderMode.doWork(minecartsOnSide.get(0));
			}
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}


	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		loaderMode.readFromNBT(tagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		loaderMode.writeToNBT(tagCompound);
	}

	//EIO
	public int getEnergyStoredScaled(int scale) {
		float percent = (float)getEnergyStored(ForgeDirection.NORTH) / (float)getMaxEnergyStored(ForgeDirection.NORTH);
		int energyStoredScaled = Math.round(scale * percent);
		return energyStoredScaled;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiRFLoader(player.inventory, (TileEntityRFLoader)world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerRFLoader(player.inventory, (TileEntityRFLoader)world.getTileEntity(x, y, z));
	}

	public LoaderMode getLoaderMode() {
		return loaderMode;
	}

	public void setLoaderMode(LoaderMode loaderMode) {
		this.loaderMode = loaderMode;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) { }

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
				entityPlayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		return false;
	}

	public int extractEnergy(int maxExtract, boolean simulate) {
		return loaderMode.extractEnergy(ForgeDirection.UNKNOWN, maxExtract, simulate);
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return loaderMode.receiveEnergy(from, maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection forgeDirection, int maxExtract, boolean simulate) {
		return extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return loaderMode.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return loaderMode.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return loaderMode.canConnectEnergy(from);
	}
}

abstract class LoaderMode implements IEnergyProvider, IEnergyReceiver {
	protected EnergyStorage energyStorage = new EnergyStorage(100000);
	protected int cartSendAmount = 100;

	abstract void doWork(EntityMinecart entityMinecart);

	@Override
	public int extractEnergy(ForgeDirection forgeDirection, int maxExtract, boolean simulate) {
		if(forgeDirection.equals(ForgeDirection.UNKNOWN)) {
			return energyStorage.extractEnergy(maxExtract, simulate);
		}
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection forgeDirection, int maxReceive, boolean simulate) {
		if(forgeDirection.equals(ForgeDirection.UNKNOWN)) {
			return energyStorage.receiveEnergy(maxReceive, simulate);
		}
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection forgeDirection) {
		return energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection forgeDirection) {
		return energyStorage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection forgeDirection) {
		return true;
	}

	public int getEnergyStored() {
		return energyStorage.getEnergyStored();
	}

	public int getMaxEnergyStored() {
		return energyStorage.getMaxEnergyStored();
	}

	public void readFromNBT(NBTTagCompound tagCompound) {
		energyStorage.readFromNBT(tagCompound);
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		energyStorage.writeToNBT(tagCompound);
	}
}

class Loader extends LoaderMode {
	@Override
	void doWork(EntityMinecart entityMinecart) {
		int amountToExtract = this.extractEnergy(ForgeDirection.UNKNOWN, cartSendAmount, true);
		int amountExtracted = 0;
		if(entityMinecart instanceof IEnergyStorage) {
			amountExtracted = ((IEnergyStorage) entityMinecart).receiveEnergy(amountToExtract, false);
		} else if(entityMinecart instanceof IEnergyReceiver) {
			amountExtracted = ((IEnergyReceiver) entityMinecart).receiveEnergy(ForgeDirection.UNKNOWN, amountToExtract, false);
		}
		this.extractEnergy(ForgeDirection.UNKNOWN, amountExtracted, false);
	}

	@Override
	public int receiveEnergy(ForgeDirection forgeDirection, int maxReceive, boolean simulate) {
		return energyStorage.receiveEnergy(maxReceive, simulate);
	}
}

class Unloader extends LoaderMode {
	@Override
	void doWork(EntityMinecart entityMinecart) {
		int amountToReceive = this.receiveEnergy(ForgeDirection.UNKNOWN, cartSendAmount, true);
		int amountReceived = 0;
		if(entityMinecart instanceof IEnergyStorage) {
			amountReceived = ((IEnergyStorage) entityMinecart).extractEnergy(amountToReceive, false);
		} else if(entityMinecart instanceof IEnergyProvider) {
			amountReceived = ((IEnergyProvider) entityMinecart).extractEnergy(ForgeDirection.UNKNOWN, amountToReceive, false);
		}
		this.receiveEnergy(ForgeDirection.UNKNOWN, amountReceived, false);
	}

	@Override
	public int extractEnergy(ForgeDirection forgeDirection, int maxExtract, boolean simulate) {
		return energyStorage.extractEnergy(maxExtract, simulate);
	}
}


