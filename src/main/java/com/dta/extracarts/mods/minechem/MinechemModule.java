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
import net.minecraft.item.Item;

/**
 * Created by Skylar on 3/30/2015.
 */
public class MinechemModule extends Module {

	public static Item itemLeadedChestCart;

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
		EntityRegistry.registerModEntity(EntityLeadedChestCart.class, "EntityLeadChestCart", 9, ExtraCarts.instance, 80, 3, true);
	}
}
