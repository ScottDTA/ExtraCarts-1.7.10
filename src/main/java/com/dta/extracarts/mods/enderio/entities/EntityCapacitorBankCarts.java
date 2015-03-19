package com.dta.extracarts.mods.enderio.entities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import com.dta.extracarts.entities.EntityExtraCartsChestMinecart;
import com.dta.extracarts.mods.enderio.EnderIOItems;
import com.dta.extracarts.mods.enderio.blocks.TileEntityRFLoaders;
import cpw.mods.fml.common.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Created by Skylar on 10/26/2014.
 */
@Optional.Interface(iface="mods.railcraft.api.carts.IMinecart", modid="RailcraftAPI|carts")
public class EntityCapacitorBankCarts extends EntityExtraCartsChestMinecart implements IEnergyStorage {
	private EnergyStorage energyStorage;
	private Block capacitorBank = Block.getBlockFromName("extracarts:blockRFLoaders");

	public EntityCapacitorBankCarts(World world) {
		super(world);
		energyStorage = new EnergyStorage(100000);
		setCartBlock(capacitorBank);
		this.setTileEntity(new TileEntityRFLoaders());
	}

	@Override
	public int getSizeInventory() {
		return 108;
	}

	@Override
	public int getMinecartType() {
		return 1;
	}

	@Override
	public Block func_145817_o() {
		return capacitorBank;
	}

	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource);
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!this.worldObj.isRemote) {
			//CartFakeWorld cartFakeWorld = new CartFakeWorld(this, worldObj, FakeWorldUtils.createWorldSettings(worldObj));
			//getCartBlock().onBlockActivated(cartFakeWorld, (int) this.posX, (int) this.posY,
			//(int) this.posZ, player, 0, (int) player.posX, (int) player.posY, (int) player.posZ);
			System.out.println(energyStorage.getEnergyStored());
		}
		return true;
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(EnderIOItems.itemCapacitorBankCart, 1, 0);
		if (cart instanceof EntityCapacitorBankCarts && stack.getItem() == CartStack.getItem()) {
			return true;
		}
		return false;
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
}
