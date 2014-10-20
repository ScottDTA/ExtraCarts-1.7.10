package com.dta.extracarts.mods.extracarts;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.ModInfo;
import com.dta.extracarts.Module;
import com.dta.extracarts.mods.extracarts.entities.EntityEnderChestCart;
import com.dta.extracarts.mods.extracarts.items.ItemEnderChestCart;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 10/17/2014.
 */
public class ExtraCartsModule implements Module {
	public static Item EnderChestCart;

	@Override
	public void init(FMLPreInitializationEvent event) {
		if (ModInfo.ENDER_CART_ENABLED) {
			EnderChestCart = new ItemEnderChestCart();
			GameRegistry.registerItem(EnderChestCart, ModInfo.MODID + "_" + EnderChestCart.getUnlocalizedName().substring(5));
		}
	}

	@Override
	public void load(FMLInitializationEvent event) {
		if (ModInfo.ENDER_CART_ENABLED) {
			GameRegistry.addShapelessRecipe(new ItemStack(EnderChestCart, 1, 0), Blocks.ender_chest, Items.minecart);
			EntityRegistry.registerModEntity(EntityEnderChestCart.class, "EntityEnderChestCart", 0, ExtraCarts.instance, 80, 3, true);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}
}
