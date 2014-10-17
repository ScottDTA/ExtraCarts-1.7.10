package com.dta.extracarts;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dta.extracarts.mods.extracarts.items.ItemEnderChestCart;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item EnderChestCart;
	
	public static void init() {
		EnderChestCart = new ItemEnderChestCart();
	}
	
	public static void registerItems() {
		GameRegistry.registerItem(EnderChestCart, ModInfo.MODID + "_" + EnderChestCart.getUnlocalizedName().substring(5));
	}
	
	public static void registerRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(EnderChestCart, 1, 0), Blocks.ender_chest, Items.minecart);
	}

}
