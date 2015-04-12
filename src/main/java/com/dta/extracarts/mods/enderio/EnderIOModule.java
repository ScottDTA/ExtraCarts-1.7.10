package com.dta.extracarts.mods.enderio;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.ModInfo;
import com.dta.extracarts.Module;
import com.dta.extracarts.mods.enderio.entities.EntityCapacitorBankCart;
import com.dta.extracarts.mods.enderio.items.ItemCapacitorBankCart;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Skylar on 4/8/2015.
 */
public class EnderIOModule extends Module {
	public static Item itemCapacitorBankCart;

	@Override
	public String getModuleName() {
		return "Ender IO";
	}

	@Override
	public Boolean areRequirementsMet(){
		return Loader.isModLoaded("EnderIO");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		itemCapacitorBankCart = new ItemCapacitorBankCart();
		GameRegistry.registerItem(itemCapacitorBankCart, ModInfo.MODID + "_" + itemCapacitorBankCart.getUnlocalizedName());
		EntityRegistry.registerModEntity(EntityCapacitorBankCart.class, "EntityCapacitorBankCart", 11, ExtraCarts.instance, 80, 3, true);
	}
}
