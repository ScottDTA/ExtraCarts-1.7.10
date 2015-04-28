package com.dta.extracarts.mods.enderio.entities;

import com.dta.extracarts.block.FakeBlockRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.machine.capbank.CapBankType;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/27/2015.
 */
public class EntityCreativeCapacitorBankCart extends EntityCapacitorBankCart {
	private Block creativeCapBank = GameRegistry.findBlock("extracarts", "fakeBlock." +
			FakeBlockRegistry.getFakeBlockByName(getBlockName()).getBlockNumber());

	public EntityCreativeCapacitorBankCart(World world) {
		super(world, 0);
	}

	public EntityCreativeCapacitorBankCart(World world, int energy) {
		super(world, energy);
	}

	@Override
	public Block getCartBlock() {
		return creativeCapBank;
	}

	@Override
	public String getBlockName() {
		return "fakeCreativeCapacitorBank";
	}

	@Override
	public CapBankType getCapBankType() {
		return CapBankType.CREATIVE;
	}
}
