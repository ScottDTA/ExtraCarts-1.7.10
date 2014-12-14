package com.dta.extracarts.network;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.mods.enderio.blocks.TileEntityRFLoaders;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Skylar on 12/14/2014.
 */
public class PacketPowerStorage implements IMessage, IMessageHandler<PacketPowerStorage, IMessage> {

	private int x;
	private int y;
	private int z;
	private float storedEnergy;

	public PacketPowerStorage() {
	}

	public PacketPowerStorage(TileEntityRFLoaders ent) {
		x = ent.xCoord;
		y = ent.yCoord;
		z = ent.zCoord;
		storedEnergy = ent.getEnergyStored(ForgeDirection.NORTH);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeFloat(storedEnergy);

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		storedEnergy = buf.readFloat();
	}

	@Override
	public IMessage onMessage(PacketPowerStorage message, MessageContext ctx) {
		EntityPlayer player = ExtraCarts.proxy.getClientPlayer();
		TileEntity te = player.worldObj.getTileEntity(message.x, message.y, message.z);
		if(te instanceof TileEntityRFLoaders) {
			TileEntityRFLoaders me = (TileEntityRFLoaders) te;
			me.setEnergyStored(Math.round(message.storedEnergy));
		}
		return null;
	}

}
