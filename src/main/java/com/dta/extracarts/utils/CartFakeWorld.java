package com.dta.extracarts.utils;

import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Created by Skylar on 2/5/2015.
 */
public class CartFakeWorld extends World {

	private EntityExtraCartChestMinecart entityExtraCartChestMinecart;
	public CartFakeWorld(EntityExtraCartChestMinecart entityExtraCartChestMinecart, World world, WorldSettings worldSettings) {
		super(world.getSaveHandler(), world.getWorldInfo().getWorldName(), worldSettings, world.provider, world.theProfiler);
		this.entityExtraCartChestMinecart = entityExtraCartChestMinecart;
	}

	@Override
	protected IChunkProvider createChunkProvider() {
		return null;
	}

	@Override
	protected int func_152379_p() {
		return 0;
	}

	@Override
	public Entity getEntityByID(int p_73045_1_) {
		return null;
	}

	@Override
	public TileEntity getTileEntity(int x, int y, int z) {
		return entityExtraCartChestMinecart.getTileEntity();
	}
}
