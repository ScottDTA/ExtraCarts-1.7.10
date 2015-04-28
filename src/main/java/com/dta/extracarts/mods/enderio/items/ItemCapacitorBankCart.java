package com.dta.extracarts.mods.enderio.items;

import cofh.api.energy.IEnergyContainerItem;
import com.dta.extracarts.items.ExtraCartItem;
import com.dta.extracarts.mods.enderio.entities.EntityActivatedCapacitorBankCart;
import com.dta.extracarts.mods.enderio.entities.EntityCreativeCapacitorBankCart;
import com.dta.extracarts.mods.enderio.entities.EntitySimpleCapacitorBankCart;
import com.dta.extracarts.mods.enderio.entities.EntityVibrantCapacitorBankCart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import crazypants.enderio.machine.capbank.CapBankType;
import crazypants.enderio.power.PowerHandlerUtil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Skylar on 4/10/2015.
 */
public class ItemCapacitorBankCart extends ExtraCartItem implements IEnergyContainerItem {
	@SideOnly(Side.CLIENT)
	private IIcon itemSimpleCapBankCart;
	private IIcon itemActivatedCapBankCart;
	private IIcon itemVibrantCapBankCart;
	private IIcon itemCreativeCapBankCart;

	public ItemCapacitorBankCart() {
		super(1);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabs.tabTransport);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7,
							 float par8, float par9, float par10) {
		EntityMinecart entityMinecart;
		switch (itemstack.getItemDamage()) {
			case 1:
				entityMinecart = new EntitySimpleCapacitorBankCart(world, getEnergyStored(itemstack));
				break;
			case 2:
				entityMinecart = new EntityActivatedCapacitorBankCart(world, getEnergyStored(itemstack));
				break;
			case 3:
				entityMinecart = new EntityVibrantCapacitorBankCart(world, getEnergyStored(itemstack));
				break;
			default:
				entityMinecart = new EntityCreativeCapacitorBankCart(world, getEnergyStored(itemstack));
				break;
		}
		return placeCart(itemstack, player, world, x, y, z, entityMinecart);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemSimpleCapBankCart = register.registerIcon("extracarts:enderio/BasicCapacitorBankCart");
		itemActivatedCapBankCart = register.registerIcon("extracarts:enderio/ActivatedCapacitorBankCart");
		itemVibrantCapBankCart = register.registerIcon("extracarts:enderio/VibrantCapacitorBankCart");
		itemCreativeCapBankCart = register.registerIcon("extracarts:enderio/CreativeCapacitorBankCart");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int dmg) {
		switch (dmg) {
			case 1:
				return itemSimpleCapBankCart;
			case 2:
				return itemActivatedCapBankCart;
			case 3:
				return itemVibrantCapBankCart;
			default:
				return itemCreativeCapBankCart;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		switch (itemStack.getItemDamage()) {
			case 1:
				return "item.BasicCapBankCart";
			case 2:
				return "item.ActivatedCapBankCart";
			case 3:
				return "item.VibrantCapBankCart";
			default:
				return "item.CreativeCapBankCart";
		}
	}

	@Override
	public double getDurabilityForDisplay(ItemStack itemStack) {
		int maxStored = CapBankType.getTypeFromMeta(itemStack.getItemDamage()).getMaxEnergyStored();
		double stored = maxStored - getEnergyStored(itemStack) + 1;
		double max = maxStored + 1;
		return stored / max;
	}

	@Override
	public boolean showDurabilityBar(ItemStack itemStack) {
		return !CapBankType.getTypeFromMeta(itemStack.getItemDamage()).isCreative();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 4; i++) {
			ItemStack stack = new ItemStack(item, 1, i);
			list.add(stack);
		}
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		if(container.stackSize > 1) {
			return 0;
		}
		CapBankType type = CapBankType.getTypeFromMeta(container.getItemDamage());
		int energy = getEnergyStored(container);
		int maxInput = type.getMaxIO();
		int energyReceived = Math.min(type.getMaxEnergyStored() - energy, Math.min(maxReceive, maxInput));

		if(!simulate && !type.isCreative()) {
			energy += energyReceived;
			PowerHandlerUtil.setStoredEnergyForItem(container, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if(container.stackSize > 1) {
			return 0;
		}
		CapBankType type = CapBankType.getTypeFromMeta(container.getItemDamage());
		int energy = getEnergyStored(container);
		int maxOutput = type.getMaxIO();
		int energyExtracted = Math.min(energy, Math.min(maxExtract, maxOutput));

		if(!simulate && !type.isCreative()) {
			energy -= energyExtracted;
			PowerHandlerUtil.setStoredEnergyForItem(container, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return PowerHandlerUtil.getStoredEnergyForItem(container);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return CapBankType.getTypeFromMeta(container.getItemDamage()).getMaxEnergyStored();
	}
}
