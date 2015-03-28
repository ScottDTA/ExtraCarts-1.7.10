package com.dta.extracarts.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;

/**
 * Created by Skylar on 3/27/2015.
 */
public class FakeBlockRegister extends Block {
	private ArrayList<FakeBlock> fakeBlockArray = new ArrayList<FakeBlock>();


	public FakeBlockRegister(Material material) {
		super(material);
	}

	public void addBlocktoFakeBlockArray(FakeBlock fakeBlock) {
		fakeBlockArray.add(fakeBlock);
	}

	public ArrayList<FakeBlock> getFakeBlockArray() {
		return fakeBlockArray;
	}

	public void setFakeBlockArray(ArrayList<FakeBlock> fakeBlockArray) {
		this.fakeBlockArray = fakeBlockArray;
	}
}
