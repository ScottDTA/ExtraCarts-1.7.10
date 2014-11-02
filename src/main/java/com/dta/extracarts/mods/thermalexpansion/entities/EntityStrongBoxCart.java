package com.dta.extracarts.mods.thermalexpansion.entities;

import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Skylar on 10/26/2014.
 */
public class EntityStrongBoxCart extends EntityExtraCartChestMinecart{
	private Block strongBox = Block.getBlockFromName("ThermalExpansion:Strongbox");

	public EntityStrongBoxCart(World world) {
		super(world);
	}

	@Override
	public Block func_145817_o() {
		return strongBox;
	}
}
