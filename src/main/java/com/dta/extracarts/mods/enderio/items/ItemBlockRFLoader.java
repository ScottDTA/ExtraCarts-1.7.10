package com.dta.extracarts.mods.enderio.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 5/27/2015.
 */
public class ItemBlockRFLoader extends ItemBlockWithMetadata {
	public ItemBlockRFLoader(Block block) {
		super(block, block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}

	public String getUnlocalizedName() {
		return "tile.blockRFLoaders";
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		if(itemstack.getItemDamage() == 0) {
			return this.getUnlocalizedName() + ".loader";
		}
		return this.getUnlocalizedName() + ".unloader";
	}
}
