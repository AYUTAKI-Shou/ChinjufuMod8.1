package com.ayutaki.chinjufumod.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WoodBoard_TileEntity extends TileEntity {
	private int MAX = 7;
	public final ITextComponent[] boardText = new ITextComponent[] {new TextComponentString(""), new TextComponentString(""), new TextComponentString(""), 
			new TextComponentString(""), new TextComponentString(""), new TextComponentString(""), new TextComponentString("")};
	public int lineBeingEdited = -1;
	private boolean isEditable = true;
	private EntityPlayer player;
	private final CommandResultStats stats = new CommandResultStats();
	public EnumDyeColor textColor = EnumDyeColor.WHITE;
	private boolean hasGlowingText;
	private boolean isWaxed;
	
	protected void setWorldCreate(World worldIn) {
		this.setWorld(worldIn);
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		for (int i = 0; i < MAX; ++i) {
			String s = ITextComponent.Serializer.componentToJson(this.boardText[i]);
			compound.setString("Text" + (i + 1), s); }

		compound.setInteger("Color", this.textColor.getMetadata());
		compound.setBoolean("GlowingText", this.hasGlowingText);
		compound.setBoolean("is_waxed", this.isWaxed);
		this.stats.writeStatsToNBT(compound);
		return compound;
	}

	public void readFromNBT(NBTTagCompound compound) {
		this.isEditable = false;
		super.readFromNBT(compound);
		
		ICommandSender icommandsender = new ICommandSender() {
			public String getName() {
				return "BlackBoard"; }
			
			public boolean canUseCommand(int permLevel, String commandName) {
				return permLevel <= 2; //Forge: Fixes
			}
			public BlockPos getPosition() {
				return WoodBoard_TileEntity.this.pos; }
			
			public Vec3d getPositionVector() {
				return new Vec3d((double)WoodBoard_TileEntity.this.pos.getX() + 0.5D, (double)WoodBoard_TileEntity.this.pos.getY() + 0.5D, (double)WoodBoard_TileEntity.this.pos.getZ() + 0.5D); }
			
			public World getEntityWorld() {
				return WoodBoard_TileEntity.this.world; }
			
			public MinecraftServer getServer() {
				return WoodBoard_TileEntity.this.world.getMinecraftServer(); }
		};

		this.textColor = EnumDyeColor.byMetadata(compound.getInteger("Color"));
		this.hasGlowingText = compound.getBoolean("GlowingText");
		this.isWaxed = compound.getBoolean("is_waxed");

		for (int i = 0; i < MAX; ++i) {
			String s = compound.getString("Text" + (i + 1));
			ITextComponent itextcomponent = ITextComponent.Serializer.jsonToComponent(s);

			try {
				this.boardText[i] = TextComponentUtils.processComponent(icommandsender, itextcomponent, (Entity)null); }
			
			catch (CommandException var7) {
				this.boardText[i] = itextcomponent; }
		}
		this.stats.readStatsFromNBT(compound);
	}

	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
	}

	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(net.minecraft.network.NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}
	
	public boolean onlyOpsCanSetNbt() {
		return true;
	}

	public boolean getIsEditable() {
		return this.isEditable;
	}

	@SideOnly(Side.CLIENT)
	public void setEditable(boolean isEditableIn) {
		this.isEditable = isEditableIn;
		if (!isEditableIn) {
			this.player = null; }
	}

	public void setPlayer(EntityPlayer playerIn) {
		this.player = playerIn;
	}

	public EntityPlayer getPlayer() {
		return this.player;
	}

	public boolean executeCommand(final EntityPlayer playerIn) {
		ICommandSender icommandsender = new ICommandSender() {
			public String getName() {
				return playerIn.getName(); }
			
			public ITextComponent getDisplayName() {
				return playerIn.getDisplayName(); }
			
			public void sendMessage(ITextComponent component) { }
			
			public boolean canUseCommand(int permLevel, String commandName) {
				return permLevel <= 2; }
			
			public BlockPos getPosition() {
				return WoodBoard_TileEntity.this.pos; }
			
			public Vec3d getPositionVector() {
				return new Vec3d((double)WoodBoard_TileEntity.this.pos.getX() + 0.5D, (double)WoodBoard_TileEntity.this.pos.getY() + 0.5D, (double)WoodBoard_TileEntity.this.pos.getZ() + 0.5D); }

			public World getEntityWorld() {
				return playerIn.getEntityWorld(); }
			
			public Entity getCommandSenderEntity() {
				return playerIn; }
			
			public boolean sendCommandFeedback() {
				return false; }
			
			public void setCommandStat(CommandResultStats.Type type, int amount) {
				if (WoodBoard_TileEntity.this.world != null && !WoodBoard_TileEntity.this.world.isRemote) {
					WoodBoard_TileEntity.this.stats.setCommandStatForSender(WoodBoard_TileEntity.this.world.getMinecraftServer(), this, type, amount); }
			}
			public MinecraftServer getServer() {
				return playerIn.getServer(); }
		};

		for (ITextComponent itextcomponent : this.boardText) {
			Style style = itextcomponent == null ? null : itextcomponent.getStyle();

			if (style != null && style.getClickEvent() != null) {
				ClickEvent clickevent = style.getClickEvent();

				if (clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
					playerIn.getServer().getCommandManager().executeCommand(icommandsender, clickevent.getValue()); }
			}
		}
		return true;
	}

	public CommandResultStats getStats() {
		return this.stats;
	}

	public void clearText() {
		ITextComponent empty = (ITextComponent)new TextComponentString("");
		this.boardText[0] = empty;
		this.boardText[1] = empty;
		this.boardText[2] = empty;
		this.boardText[3] = empty;
		this.boardText[4] = empty;
		this.boardText[5] = empty;
		this.boardText[6] = empty;
		this.hasGlowingText = false;
		markUpdated();
		//ChinjufuMod.LOGGER.info("ChinjufuMod clear WoodBoard SERVER.");
	}
	
	public void setTextColor(EnumDyeColor dye) {
		this.textColor = dye;
		markUpdated();
	}

	public EnumDyeColor getTextColor() {
		return textColor;
	}

	public boolean hasGlowText() {
		return this.hasGlowingText;
	}

	public boolean setGlowText(boolean flag) {
		if (this.hasGlowingText != flag) {
			this.hasGlowingText = flag;
			this.markUpdated();
			return true; }
		
		else { return false; }
	}
	
	public boolean isWaxed() {
		return this.isWaxed;
	}

	public boolean setWaxed(boolean flag) {
		if (this.isWaxed != flag) {
			this.isWaxed = flag;
			this.markUpdated();
			return true; }
		
		else { return false; }
	}
	
	public void markUpdated() {
		IBlockState state = world.getBlockState(pos);
		world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), state, state, 3);
		markDirty();
	}
}
