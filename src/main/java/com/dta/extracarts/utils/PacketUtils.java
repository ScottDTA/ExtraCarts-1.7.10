package com.dta.extracarts.utils;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/**
 * Created by Skylar on 12/14/2014.
 */
//EIO
public class PacketUtils {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("extracarts");

	private static int ID = 0;

	public static int nextID()
	{
		return ID++;
	}

	public static void sendToAllAround(IMessage message, TileEntity te, int range) {
		INSTANCE.sendToAllAround(message, new TargetPoint(te.getWorldObj().provider.dimensionId, te.xCoord, te.yCoord, te.zCoord, range));
	}

	public static void sendToAllAround(IMessage message, TileEntity te) {
		sendToAllAround(message, te, 64);
	}
}
