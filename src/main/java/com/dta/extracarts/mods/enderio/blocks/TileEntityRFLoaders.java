package com.dta.extracarts.mods.enderio.blocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import com.dta.extracarts.api.IRedstoneFluxCart;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.mods.enderio.client.ContainerRFLoaders;
import com.dta.extracarts.mods.enderio.client.GuiRFLoaders;
import com.dta.extracarts.utils.CartUtils;
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
 * Created by Skylar on 11/13/2014.
 */
public class TileEntityRFLoaders extends TileEntity implements IInventory, OpenableGUI, IEnergyReceiver {

	private EnergyStorage energyStorage = new EnergyStorage(100000);
	private int lastSyncPowerStored = 0;

	public TileEntityRFLoaders() {
		super();
	}

	@Override
	public void updateEntity() {
		if(worldObj == null) { // sanity check
			return;
		}

		boolean powerChanged = (lastSyncPowerStored != energyStorage.getEnergyStored() && worldObj.getTotalWorldTime() % 5 == 0);
		if(powerChanged) {
			lastSyncPowerStored = energyStorage.getEnergyStored();
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty();
		}

		if(CartUtils.isMinecartOnSide(worldObj, xCoord, yCoord, zCoord, 0, ForgeDirection.DOWN)) {
			List<EntityMinecart> minecartsOnSide = CartUtils.getMinecartsOnSide(worldObj, xCoord, yCoord, zCoord, 0,
					ForgeDirection.DOWN);
			if(minecartsOnSide.get(0) instanceof IRedstoneFluxCart) {
				//Send energy
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
		return new GuiRFLoaders(player.inventory, (TileEntityRFLoaders)world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerRFLoaders(player.inventory, (TileEntityRFLoaders)world.getTileEntity(x, y, z));
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
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return energyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return energyStorage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}
}
