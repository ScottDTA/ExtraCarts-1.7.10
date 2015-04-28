package com.dta.extracarts.block;

import cpw.mods.fml.common.registry.GameRegistry;

import java.util.ArrayList;

/**
 * Created by Skylar on 3/29/2015.
 */
public class FakeBlockRegistry {
	static private ArrayList<FakeSubBlock> fakeSubBlockArrayList = new ArrayList<FakeSubBlock>();
	static private ArrayList<FakeBlock> fakeBlockArrayList = new ArrayList<FakeBlock>();

	static public void registerBlocks() {
		int numberOfBlocks = (int) Math.ceil(getFakeSubBlockArrayList().size() / 16.0);

		for(int x = 0; x < numberOfBlocks; x++) {
			FakeBlock fakeBlock = new FakeBlock(x);
			getFakeBlockArrayList().add(fakeBlock);
			GameRegistry.registerBlock(fakeBlock, fakeBlock.getUnlocalizedName());
		}

		for(int x = 0; x < getFakeSubBlockArrayList().size(); x++) {
			int blockNumber = (int) Math.floor(x / 16.0);
			int metaNumber = x % 16;
			getFakeSubBlockArrayList().get(x).setBlockNumber(blockNumber);
			getFakeSubBlockArrayList().get(x).setMetaNumber(metaNumber);
			FakeSubBlock[] fakeBlockArray = getFakeBlockArrayList().get(blockNumber).getFakeSubBlockArray();
			fakeBlockArray[metaNumber] = getFakeSubBlockArrayList().get(x);
			getFakeBlockArrayList().get(blockNumber).setFakeSubBlockArray(fakeBlockArray);
		}
	}

	static public void registerSubBlock(FakeSubBlock fakeSubBlock) {
		getFakeSubBlockArrayList().add(fakeSubBlock);
	}

	public static ArrayList<FakeSubBlock> getFakeSubBlockArrayList() {
		return fakeSubBlockArrayList;
	}

	public static ArrayList<FakeBlock> getFakeBlockArrayList() {
		return fakeBlockArrayList;
	}

	public static FakeSubBlock getFakeBlockByName(String name) {
		for(FakeSubBlock fakeSubBlock: getFakeSubBlockArrayList()) {
			if(fakeSubBlock.getBlockName().equals(name))
				return fakeSubBlock;
		}
		return getFakeSubBlockArrayList().get(0);
	}
}
