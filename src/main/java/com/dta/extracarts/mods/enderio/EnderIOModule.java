package com.dta.extracarts.mods.enderio;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.Module;
import com.dta.extracarts.block.FakeBlockRegistry;
import com.dta.extracarts.block.FakeSubBlock;
import com.dta.extracarts.mods.enderio.block.FakeActivatedCapacitorBank;
import com.dta.extracarts.mods.enderio.block.FakeCreativeCapacitorBank;
import com.dta.extracarts.mods.enderio.block.FakeSimpleCapacitorBank;
import com.dta.extracarts.mods.enderio.block.FakeVibrantCapacitorBank;
import com.dta.extracarts.mods.enderio.entities.EntityActivatedCapacitorBankCart;
import com.dta.extracarts.mods.enderio.entities.EntityCreativeCapacitorBankCart;
import com.dta.extracarts.mods.enderio.entities.EntityBasicCapacitorBankCart;
import com.dta.extracarts.mods.enderio.entities.EntityVibrantCapacitorBankCart;
import com.dta.extracarts.mods.enderio.items.ItemCapacitorBankCart;
import com.dta.extracarts.utils.EntityUtils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Skylar on 4/8/2015.
 */
public class EnderIOModule extends Module {
	public static Item itemCapacitorBankCart;

	public static FakeSubBlock fakeSimpleCapacitorBlock;
	public static FakeSubBlock fakeActivatedCapacitorBlock;
	public static FakeSubBlock fakeVibrantCapacitorBlock;
	public static FakeSubBlock fakeCreativeCapacitorBlock;

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

		registerFakeBlocks();
		registerEntities();
	}

	public void registerFakeBlocks() {
		fakeSimpleCapacitorBlock = new FakeSimpleCapacitorBank();
		fakeActivatedCapacitorBlock = new FakeActivatedCapacitorBank();
		fakeVibrantCapacitorBlock = new FakeVibrantCapacitorBank();
		fakeCreativeCapacitorBlock = new FakeCreativeCapacitorBank();

		FakeBlockRegistry.registerSubBlock(fakeSimpleCapacitorBlock);
		FakeBlockRegistry.registerSubBlock(fakeActivatedCapacitorBlock);
		FakeBlockRegistry.registerSubBlock(fakeVibrantCapacitorBlock);
		FakeBlockRegistry.registerSubBlock(fakeCreativeCapacitorBlock);
	}

	public void registerEntities() {
		EntityUtils.registerEntity(EntityBasicCapacitorBankCart.class, "EntityBasicCapacitorBankCart");
		EntityUtils.registerEntity(EntityActivatedCapacitorBankCart.class, "EntityActivatedCapacitorBankCart");
		EntityUtils.registerEntity(EntityVibrantCapacitorBankCart.class, "EntityVibrantCapacitorBankCart");
		EntityUtils.registerEntity(EntityCreativeCapacitorBankCart.class, "EntityCreativeCapacitorBankCart");
	}
}
