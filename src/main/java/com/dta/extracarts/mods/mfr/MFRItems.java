package com.dta.extracarts.mods.mfr;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.mods.mfr.items.ItemMFRCart;
import com.dta.extracarts.mods.mfr.items.crafting.DSUCartRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

public class MFRItems {

		public static Item MFRCart;
		private static IRecipe dsuCart = new DSUCartRecipe();
		
		public static void init() {
			MFRCart = new ItemMFRCart();
		}
		
		public static void registerItems() {
			GameRegistry.registerItem(MFRCart, ModInfo.MODID + "_" + MFRCart.getUnlocalizedName().substring(5));
		}
		
		public static void registerRecipes() {
			GameRegistry.addRecipe(dsuCart);
		}
}
