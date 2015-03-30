package com.dta.extracarts.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by Skylar on 3/27/2015.
 */
public class FakeBlock extends Block {
	private FakeSubBlock[] fakeSubBlockArray = new FakeSubBlock[16];
	private int blockNumber;

	public FakeBlock(int blockNumber) {
		super(Material.iron);
		this.blockNumber = blockNumber;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister) {
		for(FakeSubBlock fakeSubBlock: fakeSubBlockArray) {
			if(fakeSubBlock != null)
				fakeSubBlock.registerBlockIcons(iIconRegister);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return fakeSubBlockArray[metadata].getIcon(side, metadata);
	}

	@Override
	public String getUnlocalizedName() {
		return "fakeBlock." + blockNumber;
	}

	public FakeSubBlock[] getFakeSubBlockArray() {
		return fakeSubBlockArray;
	}

	public void setFakeSubBlockArray(FakeSubBlock[] fakeSubBlockArray) {
		this.fakeSubBlockArray = fakeSubBlockArray;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}
}
