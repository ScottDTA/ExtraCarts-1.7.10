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

	private boolean loader = true;
	private ForgeDirection facing = ForgeDirection.UP;
	protected EnergyStorage energyStorage = new EnergyStorage(100000);
	private int maxIO = 1000;


	public TileEntityRFLoader() {
		this(0);
	}

	public TileEntityRFLoader(int metadata) {
		super();
		if(metadata == 0) {
			loader = true;
		} else {
			loader = false;
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(worldObj == null) { // sanity check
			return;
		}

		boolean powerChanged = (lastSyncPowerStored != getEnergyStored() && worldObj.getTotalWorldTime() % 5 == 0);
		if(powerChanged) {
			lastSyncPowerStored = getEnergyStored();
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty();
		}

		if(CartTools.isMinecartOnSide(worldObj, xCoord, yCoord, zCoord, 0, facing)) {
			List<EntityMinecart> minecartsOnSide = CartTools.getMinecartsOnSide(worldObj, xCoord, yCoord, zCoord, 0, facing);
			if(minecartsOnSide.get(0) != null) {
				if(loader) {
					loadCart(minecartsOnSide.get(0));
				} else {
					unloadCart(minecartsOnSide.get(0));
				}
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
		energyStorage.readFromNBT(tagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		energyStorage.writeToNBT(tagCompound);
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

	private void loadCart(EntityMinecart entityMinecart) {
		int amountToExtract = this.extractEnergyForCart(getMaxIO(), true);
		int amountExtracted = 0;
		if(entityMinecart instanceof IEnergyStorage) {
			amountExtracted = ((IEnergyStorage) entityMinecart).receiveEnergy(amountToExtract, false);
		} else if(entityMinecart instanceof IEnergyReceiver) {
			amountExtracted = ((IEnergyReceiver) entityMinecart).receiveEnergy(ForgeDirection.UNKNOWN, amountToExtract, false);
		}
		this.extractEnergyForCart(amountExtracted, false);
	}

	private int extractEnergyForCart(int amountToExtract, boolean simulate) {
		return energyStorage.extractEnergy(amountToExtract, simulate);
	}

	private void unloadCart(EntityMinecart entityMinecart) {
		int amountToReceive = this.receiveEnergyFromCart(getMaxIO(), true);
		int amountReceived = 0;
		if(entityMinecart instanceof IEnergyStorage) {
			amountReceived = ((IEnergyStorage) entityMinecart).extractEnergy(amountToReceive, false);
		} else if(entityMinecart instanceof IEnergyProvider) {
			amountReceived = ((IEnergyProvider) entityMinecart).extractEnergy(ForgeDirection.UNKNOWN, amountToReceive, false);
		}
		this.receiveEnergyFromCart(amountReceived, false);
	}

	private int receiveEnergyFromCart(int amountToReceive, boolean simulate) {
		return energyStorage.receiveEnergy(amountToReceive, simulate);
	}

	public ForgeDirection getFacing() {
		return facing;
	}

	public void setFacing(ForgeDirection facing) {
		this.facing = facing;
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

	@Override
	public int extractEnergy(ForgeDirection forgeDirection, int maxExtract, boolean simulate) {
		if(!loader) {
			return energyStorage.extractEnergy(maxExtract, simulate);
		}
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection forgeDirection, int maxReceive, boolean simulate) {
		if(loader) {
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

	public int getMaxIO() {
		return maxIO;
	}

	public void setMaxIO(int maxIO) {
		this.maxIO = maxIO;
	}
}


