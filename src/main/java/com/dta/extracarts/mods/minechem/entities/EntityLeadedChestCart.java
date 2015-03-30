package com.dta.extracarts.mods.minechem.entities;

import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Created by Skylar on 3/30/2015.
 */
public class EntityLeadedChestCart extends EntityExtraCartChestMinecart implements OpenableGUI, IMinecart {
	private Block leadedChest = GameRegistry.findBlock("minechem", "tile.leadedChest");

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
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}
