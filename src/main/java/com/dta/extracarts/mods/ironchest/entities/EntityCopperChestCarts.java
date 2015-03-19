package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.client.IUpgradeable;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartsChestMinecart;
import com.dta.extracarts.mods.ironchest.IronChestItems;
import com.dta.extracarts.mods.ironchest.client.ContainerCopperChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiCopperChestCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.api.carts.IItemTransfer;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.ArrayList;

@Optional.InterfaceList({
		@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts"),
		@Optional.Interface(iface = "mods.railcraft.api.carts.IItemTransfer", modid = "RailcraftAPI|carts")
})
public class EntityCopperChestCarts extends EntityExtraCartsChestMinecart implements OpenableGUI, IMinecart,
		IItemTransfer, IUpgradeable {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	private Item copperSilverUpgrade = GameRegistry.findItem("IronChest", "copperSilverUpgrade");
	private Item copperIronUpgrade = GameRegistry.findItem("IronChest", "copperIronUpgrade");

	public EntityCopperChestCarts(World world) {
		super(world);
		this.setDisplayTileData(3);
		this.addUpgrades();
	}
	
	@Override
	public int getSizeInventory() {
		return 45;
	}
	
	@Override
	public Block func_145817_o() {
		return ironChest;
	}

	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource, new ItemStack(ironChest, 1, 3));
	}

	@Override
	public ArrayList<Item> getUpgradeItems() {
		return upgrades;
	}

	@Override
	public EntityExtraCartsChestMinecart upgradedCart(Item currentHeldItem, World world) {
		if(currentHeldItem != null) {
			if(currentHeldItem.equals(copperIronUpgrade))
				return new EntityIronChestCarts(world);
			if(currentHeldItem.equals(copperSilverUpgrade))
				return new EntitySilverChestCarts(world);
		}
		return null;
	}

	@Override
	public void addUpgrades() {
		upgrades.add(copperIronUpgrade);
		upgrades.add(copperSilverUpgrade);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiCopperChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerCopperChestCart(player.inventory, this);
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(IronChestItems.IronChestCart, 1, 3);
		if (cart instanceof EntityCopperChestCarts && stack.getItem() == CartStack.getItem() && stack.getItemDamage() == 3) {
				return true;
		}
		return false;
	}
}
