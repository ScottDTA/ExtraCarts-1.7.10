package com.dta.extracarts.mods.enderio.entities;

import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/9/2015.
 */
public class CapacitorBankCart extends EntityExtraCartChestMinecart {
	private Block block = GameRegistry.findBlock("EnderIO", "capbank");
	public CapacitorBankCart(World world, int capBankType) {
		super(world);
		this.setDisplayTileData(capBankType);
	}

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public int getMinecartType() {
		return 1;
	}
}
