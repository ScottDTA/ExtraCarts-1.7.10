package com.dta.extracarts.mods.enderio.items;

import com.dta.extracarts.mods.enderio.blocks.BlockRFLoaders;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;


/**
 * Created by Skylar on 11/12/2014.
 */
public class ItemBlockRFLoaders extends ItemBlockWithMetadata {
	public ItemBlockRFLoaders(Block block) {
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
