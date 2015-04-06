package com.dta.extracarts.proxy;

import com.dta.extracarts.mods.minechem.entities.EntityLeadedChestCart;
import com.dta.extracarts.mods.minechem.renderers.RenderLeadedChestCart;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Skylar on 3/31/2015.
 */
public class ClientProxy extends CommonProxy {
	@Override
	public void init(FMLPreInitializationEvent event) {
		this.registerRenderers();
	}

	private void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityLeadedChestCart.class, new RenderLeadedChestCart());
	}
}
