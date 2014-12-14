package com.dta.extracarts.mods.enderio.blocks;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.mods.enderio.client.ContainerRFLoaders;
import com.dta.extracarts.mods.enderio.client.GuiRFLoaders;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Skylar on 11/13/2014.
 */
public class TileEntityRFLoaders extends TileEntity implements IInventory, OpenableGUI, IEnergyReceiver, IEnergyHandler {
	private ItemStack[] inventory;
	private EnergyStorage energyStorage;

	public TileEntityRFLoaders() {
		inventory = new ItemStack[9];
		energyStorage = new EnergyStorage(100000);
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "tile.blockRFLoaders.loader";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
				player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		energyStorage.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Inventory", 0);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag =  tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		energyStorage.writeToNBT(tagCompound);
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
	}

	//EIO
	public int getEnergyStoredScaled(int scale) {
		float percent = getEnergyStored(ForgeDirection.NORTH) / getMaxEnergyStored(ForgeDirection.NORTH);
		System.out.println("Energy Stored is " + getEnergyStored(ForgeDirection.NORTH));
		System.out.println("Percent is " + percent);
		if(Math.round(percent) == 0) {
			return scale;
		}
		int energyStoredScaled = Math.round(percent * scale);
		System.out.println("Scaled is " + energyStoredScaled);
		return energyStoredScaled;
	}

	public void setEnergyStored(int energy) {
		energyStorage.modifyEnergyStored(energy);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return energyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return 0;
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
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiRFLoaders(player.inventory, (TileEntityRFLoaders)world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerRFLoaders(player.inventory, (TileEntityRFLoaders)world.getTileEntity(x, y, z));
	}
}
