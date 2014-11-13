package com.dta.extracarts.mods.enderio;

import com.dta.extracarts.mods.enderio.blocks.BlockRFLoaders;
import com.dta.extracarts.mods.enderio.items.ItemBlockRFLoaders;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Skylar on 11/9/2014.
 */
public class EnderIOBlocks {
	static BlockRFLoaders blockRFLoaders = new BlockRFLoaders();
	public static void registerBlocks() {
		GameRegistry.registerBlock(blockRFLoaders, ItemBlockRFLoaders.class, "blockRFLoaders");
	}
}
