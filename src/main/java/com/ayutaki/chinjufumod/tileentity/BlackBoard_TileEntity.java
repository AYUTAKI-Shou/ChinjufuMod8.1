package com.ayutaki.chinjufumod.tileentity;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.TileEntity_CM;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.block.BlockState;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlackBoard_TileEntity extends TileEntity {
	private int MAX = 6;
	/** private final ITextComponent[] boardText -> public final ITextComponent[] messages ... for Multi. **/
	public final ITextComponent[] boardText = new ITextComponent[]{StringTextComponent.EMPTY, StringTextComponent.EMPTY, StringTextComponent.EMPTY, 
			StringTextComponent.EMPTY, StringTextComponent.EMPTY, StringTextComponent.EMPTY};
	private boolean isEditable = true;
	private PlayerEntity playerWhoMayEdit;
	private final IReorderingProcessor[] renderText = new IReorderingProcessor[MAX];
	private DyeColor color = DyeColor.WHITE;
	private boolean hasGlowingText;
	private boolean isWaxed;
	
	public BlackBoard_TileEntity() {
		super(TileEntity_CM.BLACKBOARD);
	}
	
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);

		for(int i = 0; i < MAX; ++i) {
			String s = ITextComponent.Serializer.toJson(this.boardText[i]);
			compound.putString("Text" + (i + 1), s); }

		compound.putString("Color", this.color.getName());
		compound.putBoolean("GlowingText", this.hasGlowingText);
		compound.putBoolean("is_waxed", this.isWaxed);
		return compound;
	}

	public void load(BlockState state, CompoundNBT compound) {
		this.isEditable = false;
		super.load(state, compound);
		this.color = DyeColor.byName(compound.getString("Color"), DyeColor.WHITE);
		this.hasGlowingText = compound.getBoolean("GlowingText");
		this.isWaxed = compound.getBoolean("is_waxed");

		for(int i = 0; i < MAX; ++i) {
			String s = compound.getString("Text" + (i + 1));
			ITextComponent iText = ITextComponent.Serializer.fromJson(s.isEmpty() ? "\"\"" : s);
			if (this.level instanceof ServerWorld) {
				try {
					this.boardText[i] = TextComponentUtils.updateForEntity(this.createCommandSourceStack((ServerPlayerEntity)null), iText, (Entity)null, 0); } 

				catch (CommandSyntaxException commandsyntaxexception) {
					this.boardText[i] = iText; }
			} 
			
			else {
				this.boardText[i] = iText; }

			this.renderText[i] = null;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public ITextComponent getMessage(int i) {
		return this.boardText[i];
	}

	public void setMessage(int i, ITextComponent iText) {
		this.boardText[i] = iText;
		this.renderText[i] = null;
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public IReorderingProcessor getRenderMessage(int i, Function<ITextComponent, IReorderingProcessor> iText) {
		if (this.renderText[i] == null && this.boardText[i] != null) {
			this.renderText[i] = iText.apply(this.boardText[i]); }

		return this.renderText[i];
	}

	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.worldPosition, 9, this.getUpdateTag());
	}

	public CompoundNBT getUpdateTag() {
		return this.save(new CompoundNBT());
	}

	public boolean onlyOpCanSetNbt() {
		return true;
	}

	public boolean isEditable() {
		return this.isEditable;
	}

	@OnlyIn(Dist.CLIENT)
	public void setEditable(boolean flag) {
		this.isEditable = flag;
		if (!flag) {
			this.playerWhoMayEdit = null;
		}
	}

	public void setAllowedPlayerEditor(PlayerEntity playerIn) {
		this.playerWhoMayEdit = playerIn;
	}

	public PlayerEntity getPlayerWhoMayEdit() {
		return this.playerWhoMayEdit;
	}

	public boolean executeClickCommands(PlayerEntity playerIn) {
		for(ITextComponent iText : this.boardText) {
			Style style = iText == null ? null : iText.getStyle();
			if (style != null && style.getClickEvent() != null) {
				ClickEvent clickevent = style.getClickEvent();
				if (clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
					playerIn.getServer().getCommands().performCommand(this.createCommandSourceStack((ServerPlayerEntity)playerIn), clickevent.getValue());
				}
			}
		}

		return true;
	}

	public CommandSource createCommandSourceStack(@Nullable ServerPlayerEntity playerIn) {
		String s = playerIn == null ? "BlackBoard" : playerIn.getName().getString();
		ITextComponent iText = (ITextComponent)(playerIn == null ? new StringTextComponent("BlackBoard") : playerIn.getDisplayName());
		return new CommandSource(ICommandSource.NULL, Vector3d.atCenterOf(this.worldPosition), Vector2f.ZERO, (ServerWorld)this.level, 2, s, iText, this.level.getServer(), playerIn);
	}

	public void clearText() {
		ITextComponent empty = (ITextComponent)StringTextComponent.EMPTY;
		this.setMessage(0, empty);
		this.setMessage(1, empty);
		this.setMessage(2, empty);
		this.setMessage(3, empty);
		this.setMessage(4, empty);
		this.setMessage(5, empty);
		this.hasGlowingText = false;
		markUpdated();
		//ChinjufuMod.LOGGER.info("ChinjufuMod clear BlackBoard TileEntity.");
	}
	
	public DyeColor getTxtColor() {
		return this.color;
	}

	public boolean setTxtColor(DyeColor dye) {
		if (dye != this.getTxtColor()) {
			this.color = dye;
			markUpdated();
			return true; }
		
		else { return false; }
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
		this.setChanged();
		this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
	}
}
