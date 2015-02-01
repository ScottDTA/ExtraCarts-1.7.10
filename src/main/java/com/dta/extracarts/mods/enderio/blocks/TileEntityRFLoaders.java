package com.dta.extracarts.mods.enderio.blocks;

import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.mods.enderio.client.ContainerRFLoaders;
import com.dta.extracarts.mods.enderio.client.GuiRFLoaders;
import crazypants.enderio.machine.AbstractPowerConsumerEntity;
import crazypants.enderio.machine.SlotDefinition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Skylar on 11/13/2014.
 */
public class TileEntityRFLoaders extends AbstractPowerConsumerEntity implements OpenableGUI {

	public TileEntityRFLoaders() {
		super(new SlotDefinition(0, 0));
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
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tagCompound) {
		super.writeCustomNBT(tagCompound);
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
