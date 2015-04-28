package com.dta.extracarts.mods.enderio.entities;

import com.dta.extracarts.block.FakeBlockRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.machine.capbank.CapBankType;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/27/2015.
 */
public class EntityVibrantCapacitorBankCart extends EntityCapacitorBankCart {
	private Block vibrantCapBank = GameRegistry.findBlock("extracarts", "fakeBlock." +
			FakeBlockRegistry.getFakeBlockByName(getBlockName()).getBlockNumber());

	public EntityVibrantCapacitorBankCart(World world) {
		super(world, 0);
	}

	public EntityVibrantCapacitorBankCart(World world, int energy) {
		super(world, energy);
	}

	@Override
	public Block getCartBlock() {
		return vibrantCapBank;
	}

	@Override
	public String getBlockName() {
		return "fakeVibrantCapacitorBank";
	}

	@Override
	public CapBankType getCapBankType() {
		return CapBankType.VIBRANT;
	}
}
