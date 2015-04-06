package com.dta.extracarts.mods.minechem.entities;

import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.minechem.MinechemModule;
import com.dta.extracarts.mods.minechem.client.ContainerLeadedChestCart;
import com.dta.extracarts.mods.minechem.client.GUILeadedChestCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Created by Skylar on 3/30/2015.
 */
@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts")
public class EntityLeadedChestCart extends EntityExtraCartChestMinecart implements OpenableGUI, IMinecart {
	private Block leadedChest = GameRegistry.findBlock("minechem", "tile.leadChest");

	public EntityLeadedChestCart(World world) {
		super(world);
	}

	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource, new ItemStack(leadedChest, 1, 0));
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public Block func_145817_o() {
		return leadedChest;
	}

	@Override
	public Block func_145820_n() {
		return leadedChest;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GUILeadedChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerLeadedChestCart(player.inventory, this);
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(MinechemModule.itemLeadedChestCart, 1, 0);
		if (cart instanceof EntityLeadedChestCart && stack.getItem() == CartStack.getItem() && stack.getItemDamage() == 0) {
			return true;
		}
		return false;
	}
}
