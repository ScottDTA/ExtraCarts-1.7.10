package com.dta.extracarts.modcompat.ironchest;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.modcompat.ironchest.items.ItemIronChestCart;

import cpw.mods.fml.common.registry.GameRegistry;

public class IronChestItems {

	public static Item IronChestCart;
	public static Block ironChest;
	
	public static void init() {
		IronChestCart = new ItemIronChestCart();
	}
	
	public static void registerItems() {
		GameRegistry.registerItem(IronChestCart, ModInfo.MODID + "_" + IronChestCart.getUnlocalizedName().substring(5));
	}
	
	public static void registerRecipes() {
		ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
		for (int i = 0; i < 8; i++) {
			GameRegistry.addShapelessRecipe(new ItemStack(IronChestCart, 1, i), new ItemStack(ironChest, 1, i), Items.minecart);
		}
	}
}
