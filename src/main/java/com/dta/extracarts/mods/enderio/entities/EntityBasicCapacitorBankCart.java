package com.dta.extracarts.mods.enderio.entities;

import com.dta.extracarts.block.FakeBlockRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.machine.capbank.CapBankType;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/27/2015.
 */
public class EntityBasicCapacitorBankCart extends EntityCapacitorBankCart {
	private Block simpleCapBank = GameRegistry.findBlock("extracarts", "fakeBlock." +
			FakeBlockRegistry.getFakeBlockByName(getBlockName()).getBlockNumber());

	public EntityBasicCapacitorBankCart(World world) {
		super(world, 0);
	}

	public EntityBasicCapacitorBankCart(World world, int energy) {
		super(world, energy);
	}

	@Override
	public Block getCartBlock() {
		return simpleCapBank;
	}

	@Override
	public String getBlockName() {
		return "fakeSimpleCapacitorBank";
	}

	@Override
	public CapBankType getCapBankType() {
		return CapBankType.SIMPLE;
	}
}
