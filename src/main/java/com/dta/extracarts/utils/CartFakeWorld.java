package com.dta.extracarts.utils;

import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import java.lang.reflect.Method;

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
	public Block getBlock(int x, int y, int z) {
		return entityExtraCartChestMinecart.getCartBlock();
	}

	@Override
	public TileEntity getTileEntity(int x, int y, int z) {
		return entityExtraCartChestMinecart.getTileEntity();
	}

	@Override
	public void addBlockEvent(int x, int y, int z, Block block, int int1, int int2) {

	}

	@Override
	public void tick() {

	}

	@Override
	protected boolean chunkExists(int p_72916_1_, int p_72916_2_) {
		Class clazz = this.entityExtraCartChestMinecart.worldObj.getClass();
		try {
			Method chunk = clazz.getDeclaredMethod("chunkExists", int.class, int.class);
			boolean exists = (Boolean)chunk.invoke(this.entityExtraCartChestMinecart.worldObj, p_72916_1_, p_72916_2_);
			return exists;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean blockExists(int p_72899_1_, int p_72899_2_, int p_72899_3_)
	{
		return this.entityExtraCartChestMinecart.worldObj.blockExists(p_72899_1_, p_72899_2_, p_72899_3_);
	}

	@Override
	public Chunk getChunkFromChunkCoords(int p_72964_1_, int p_72964_2_) {
		return this.entityExtraCartChestMinecart.worldObj.getChunkFromChunkCoords(p_72964_1_, p_72964_2_);
	}
}
