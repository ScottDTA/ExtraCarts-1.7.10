package com.dta.extracarts.mods.enderio.blocks;

import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.mods.enderio.client.ContainerRFLoaders;
import com.dta.extracarts.mods.enderio.client.GuiRFLoaders;
import crazypants.enderio.machine.AbstractPowerConsumerEntity;
import crazypants.enderio.machine.SlotDefinition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Skylar on 11/13/2014.
 */
public class TileEntityRFLoaders extends AbstractPowerConsumerEntity implements IInventory, OpenableGUI {
	public TileEntityRFLoaders() {
		super(new SlotDefinition(9, 1));
		inventory = new ItemStack[9];
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
	protected boolean isMachineItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public float getProgress() {
		return 0;
	}

	@Override
	public void updateEntity() {
		if(worldObj == null) { // sanity check
			return;
		}

		boolean powerChanged = (lastSyncPowerStored != getEnergyStored(ForgeDirection.NORTH) && worldObj.getTotalWorldTime() % 5 == 0);
		if(powerChanged) {
			lastSyncPowerStored = getEnergyStored(ForgeDirection.NORTH);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty();
		}
	}

	@Override
	protected boolean processTasks(boolean redstoneCheckPassed) {
		return false;
	}

	@Override
	public void readCustomNBT(NBTTagCompound tagCompound) {
		super.readCustomNBT(tagCompound);
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
	public void writeCustomNBT(NBTTagCompound tagCompound) {
		super.writeCustomNBT(tagCompound);
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

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiRFLoaders(player.inventory, (TileEntityRFLoaders)world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerRFLoaders(player.inventory, (TileEntityRFLoaders)world.getTileEntity(x, y, z));
	}

	@Override
	public String getMachineName() {
		return "tileEntityRFLoader";
	}
}
