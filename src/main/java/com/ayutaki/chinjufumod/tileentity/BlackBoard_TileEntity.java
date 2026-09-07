package com.ayutaki.chinjufumod.tileentity;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.TileEntity_CM;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
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
	public final ITextComponent[] boardText = new ITextComponent[]{new StringTextComponent(""), new StringTextComponent(""), new StringTextComponent(""), 
			new StringTextComponent(""), new StringTextComponent(""), new StringTextComponent("")};
	private boolean isEditable = true;
	private PlayerEntity player;
	private final String[] renderText = new String[MAX];
	private DyeColor textColor = DyeColor.WHITE;
	private boolean hasGlowingText;
	private boolean isWaxed;
	
	public BlackBoard_TileEntity() {
		super(TileEntity_CM.BLACKBOARD);
	}

	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);

		for(int i = 0; i < MAX; ++i) {
			String s = ITextComponent.Serializer.toJson(this.boardText[i]);
			compound.putString("Text" + (i + 1), s); }

		compound.putString("Color", this.textColor.getTranslationKey());
		compound.putBoolean("GlowingText", this.hasGlowingText);
		compound.putBoolean("is_waxed", this.isWaxed);
		return compound;
	}

	public void read(CompoundNBT compound) {
		this.isEditable = false;
		super.read(compound);
		this.textColor = DyeColor.byTranslationKey(compound.getString("Color"), DyeColor.WHITE);
		this.hasGlowingText = compound.getBoolean("GlowingText");
		this.isWaxed = compound.getBoolean("is_waxed");
		
		for(int i = 0; i < MAX; ++i) {
			String s = compound.getString("Text" + (i + 1));
			ITextComponent iText = ITextComponent.Serializer.fromJson(s.isEmpty() ? "\"\"" : s);
			if (this.world instanceof ServerWorld) {
				try {
					this.boardText[i] = TextComponentUtils.updateForEntity(this.getCommandSource((ServerPlayerEntity)null), iText, (Entity)null, 0); } 
				
				catch (CommandSyntaxException var6) {
					this.boardText[i] = iText; }
			} 
			else {
				this.boardText[i] = iText; }

			this.renderText[i] = null;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public ITextComponent getText(int line) {
		return this.boardText[line];
	}

	public void setText(int line, ITextComponent iText) {
		this.boardText[line] = iText;
		this.renderText[line] = null;
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public String getRenderText(int line, Function<ITextComponent, String> iText) {
		if (this.renderText[line] == null && this.boardText[line] != null) {
			this.renderText[line] = iText.apply(this.boardText[line]); }

		return this.renderText[line];
	}

	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.pos, 9, this.getUpdateTag());
	}

	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}

	public boolean onlyOpsCanSetNbt() {
		return true;
	}

	public boolean getIsEditable() {
		return this.isEditable;
	}

	@OnlyIn(Dist.CLIENT)
	public void setEditable(boolean isEditableIn) {
		this.isEditable = isEditableIn;
		if (!isEditableIn) {
			this.player = null;
		}
	}

	public void setPlayer(PlayerEntity playerIn) {
		this.player = playerIn;
	}

	public PlayerEntity getPlayer() {
		return this.player;
	}

	public boolean executeCommand(PlayerEntity playerIn) {
		for(ITextComponent iText : this.boardText) {
			Style style = iText == null ? null : iText.getStyle();
			if (style != null && style.getClickEvent() != null) {
				ClickEvent clickevent = style.getClickEvent();
				if (clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
					playerIn.getServer().getCommandManager().handleCommand(this.getCommandSource((ServerPlayerEntity)playerIn), clickevent.getValue()); }
			}
		}
		return true;
	}

	public CommandSource getCommandSource(@Nullable ServerPlayerEntity playerIn) {
		String s = playerIn == null ? "BlackBoard" : playerIn.getName().getString();
		ITextComponent iText = (ITextComponent)(playerIn == null ? new StringTextComponent("BlackBoard") : playerIn.getDisplayName());
		return new CommandSource(ICommandSource.DUMMY, new Vec3d((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D), Vec2f.ZERO, (ServerWorld)this.world, 2, s, iText, this.world.getServer(), playerIn);
	}

	public void clearText() {
		ITextComponent empty = (ITextComponent)new StringTextComponent("");
		this.setText(0, empty);
		this.setText(1, empty);
		this.setText(2, empty);
		this.setText(3, empty);
		this.setText(4, empty);
		this.setText(5, empty);
		this.hasGlowingText = false;
		markUpdated();
		//ChinjufuMod.LOGGER.info("ChinjufuMod clear BlackBoard TileEntity.");
	}
	
	public DyeColor getTxtColor() {
		return this.textColor;
	}

	public boolean setTxtColor(DyeColor newColor) {
		if (newColor != this.getTxtColor()) {
			this.textColor = newColor;
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
		this.markDirty();
		this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
	}
}
