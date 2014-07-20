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
		
		/*
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:ironGoldUpgrade 4096 cpw.mods.ironchest.ItemChestChanger@44dd687f (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:goldDiamondUpgrade 4097 cpw.mods.ironchest.ItemChestChanger@23f78d2b (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:copperSilverUpgrade 4098 cpw.mods.ironchest.ItemChestChanger@5155147f (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:silverGoldUpgrade 4099 cpw.mods.ironchest.ItemChestChanger@28b4c790 (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:copperIronUpgrade 4100 cpw.mods.ironchest.ItemChestChanger@7499d3d0 (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:diamondCrystalUpgrade 4101 cpw.mods.ironchest.ItemChestChanger@1f21387e (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:woodIronUpgrade 4102 cpw.mods.ironchest.ItemChestChanger@3e03e829 (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:woodCopperUpgrade 4103 cpw.mods.ironchest.ItemChestChanger@727efb8d (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:diamondObsidianUpgrade 4104 cpw.mods.ironchest.ItemChestChanger@6eb520b4 (req. id -1)
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:BlockIronChest 165 cpw.mods.ironchest.BlockIronChest@250b0af0 (req. id -1)
[11:19:41] [Client thread/DEBUG] [FML/IronChest]: Found matching Block cpw.mods.ironchest.BlockIronChest@250b0af0 for ItemBlock cpw.mods.ironchest.ItemIronChest@77fb258f at id 165, original id requested: -1
[11:19:41] [Client thread/TRACE] [FML/IronChest]: Registry add: IronChest:BlockIronChest 165 cpw.mods.ironchest.ItemIronChest@77fb258f (req. id 165)
		*/ 
		 
		GameRegistry.addShapelessRecipe(new ItemStack(IronChestCart, 1, 0), new ItemStack(ironChest, 1, 0), Items.minecart);
	}
}
