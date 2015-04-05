package com.dta.extracarts.mods.minechem;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.ModInfo;
import com.dta.extracarts.Module;
import com.dta.extracarts.mods.minechem.entities.EntityLeadedChestCart;
import com.dta.extracarts.mods.minechem.items.ItemLeadedChestCart;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 3/30/2015.
 */
public class MinechemModule extends Module {

	public static Item itemLeadedChestCart;
	public static Block leadedChest;

	@Override
	public String getModuleName() {
		return "MineChem";
	}

	@Override
	public Boolean areRequirementsMet() {
		return Loader.isModLoaded("minechem");
	}

	@Override
	public void init(FMLPreInitializationEvent event) {
		itemLeadedChestCart = new ItemLeadedChestCart();
		GameRegistry.registerItem(itemLeadedChestCart, ModInfo.MODID + "_" + itemLeadedChestCart.getUnlocalizedName().substring(5));
		leadedChest = GameRegistry.findBlock("minechem", "tile.leadChest");
		GameRegistry.addShapelessRecipe(new ItemStack(itemLeadedChestCart, 1, 0), new ItemStack(leadedChest, 1, 0), Items.minecart);
		EntityRegistry.registerModEntity(EntityLeadedChestCart.class, "EntityLeadChestCart", 10, ExtraCarts.instance, 80, 3, true);
	}
}
