package com.ayutaki.chinjufumod.tileentity;

import com.ayutaki.chinjufumod.blocks.kitchen.ReizouTop;
import com.ayutaki.chinjufumod.gui.ReizouTopMenu;
import com.ayutaki.chinjufumod.handler.MenuTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.handler.TileEntity_CM;
import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;
import com.ayutaki.chinjufumod.recipe_type.CM_RecipeType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ReizouTop_TileEntity extends AbstractReizouTileEntity {

	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	private int tickInterval;

	public ReizouTop_TileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
	}

	public ReizouTop_TileEntity() {
		this(TileEntity_CM.REIZOU_TOP);
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.chinjufumod.reitou");
	}

	protected Container createMenu(int id, PlayerInventory inventory) {
		return new ReizouTopMenu(MenuTypes_CM.REIZOUTOP_MENU.get(), id, inventory, this);
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	public void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items);
		}
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound)) {
			ItemStackHelper.loadAllItems(compound, this.items);
		}
	}

	/* 効果音 ITickableTileEntity */
	public void tick() {
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		++this.tickInterval;
		this.openCount = calculatePlayersUsingSync(this.world, this, this.tickInterval, i, j, k, this.openCount);
		this.prevLidAngle = this.lidAngle;

		if (this.openCount > 0 && this.lidAngle == 0.0F) {
			this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(ReizouTop.OPEN, Boolean.valueOf(true)), 3);
			this.playSound(SoundEvents_CM.REIZOU_OPEN);
		}

		if (this.openCount == 0 && this.lidAngle > 0.0F || this.openCount > 0 && this.lidAngle < 1.0F) {
			float f1 = this.lidAngle;
			if (this.openCount > 0) {
				this.lidAngle += 0.1F;
			} else {
				this.lidAngle -= 0.1F;
			}

			if (this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}

			if (this.lidAngle < 0.5F && f1 >= 0.5F) {
				this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(ReizouTop.OPEN, Boolean.valueOf(false)), 3);
				this.playSound(SoundEvents_CM.REIZOU_CLOSE);
			}

			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
				this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(ReizouTop.OPEN, Boolean.valueOf(false)), 3);
			}
		}
		
		if (!this.world.isRemote){
			if (this.power()) { 
				this.slotCook(0, this.world, this);
				this.slotCook(1, this.world, this);
				this.slotCook(2, this.world, this);
				this.slotCook(3, this.world, this);
				this.slotCook(4, this.world, this);
				this.slotCook(5, this.world, this);
				this.slotCook(6, this.world, this);
				this.slotCook(7, this.world, this);
			}
			
			else {
				this.storageTime = 0;
				this.markDirty(); }
		}
	}

	public static int calculatePlayersUsingSync(World worldIn, LockableTileEntity lTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayerUsing) {
		if (!worldIn.isRemote && numPlayerUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0) {
			numPlayerUsing = calculatePlayersUsing(worldIn, lTileEntity, x, y, z);
		}
		return numPlayerUsing;
	}

	public static int calculatePlayersUsing(World worldIn, LockableTileEntity lTileEntity, int x, int y, int z) {
		int i = 0;

		for(PlayerEntity playerIn : worldIn.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double)((float)x - 5.0F), (double)((float)y - 5.0F), (double)((float)z - 5.0F), (double)((float)(x + 1) + 5.0F), (double)((float)(y + 1) + 5.0F), (double)((float)(z + 1) + 5.0F)))) {
			if (playerIn.openContainer instanceof ReizouTopMenu) {
				IInventory iinventory = ((ReizouTopMenu)playerIn.openContainer).getLowerChestInventory();
				if (iinventory == lTileEntity) {
					++i;
				}
			}
		}
		return i;
	}

	protected void onOpenOrClose() {
		Block block = this.getBlockState().getBlock();
		if (block instanceof ReizouTop) {
			this.world.addBlockEvent(this.pos, block, 1, this.openCount);
			this.world.notifyNeighborsOfStateChange(this.pos, block);
		}
	}

	public static void swapContents(ReizouTop_TileEntity te, ReizouTop_TileEntity otherTe) {
		NonNullList<ItemStack> list = te.getItems();
		te.setItems(otherTe.getItems());
		otherTe.setItems(list);
	}

	protected net.minecraftforge.items.IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof ReizouTop)) {
			return new net.minecraftforge.items.wrapper.InvWrapper(this);
		}
		IInventory inv = ReizouTop.getContainer((ReizouTop) state.getBlock(), state, getWorld(), getPos(), true);
		return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
	}

	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected int getTotalCookTime(World worldIn, AbstractReizouTileEntity tileEntity) {
		return worldIn.getRecipeManager().getRecipe(CM_RecipeType.FREEZE_RECIPE, tileEntity, worldIn)
				.map(AbstractColdRecipe::getCookingTime).orElse(400);
	}
	
	protected boolean power() {
		BlockState state = this.world.getBlockState(this.pos);
		boolean hasPower = (state.getBlock() instanceof ReizouTop)? state.get(ReizouTop.POWERED) : false;
		boolean open = (state.getBlock() instanceof ReizouTop)? state.get(ReizouTop.OPEN) : false;
		
		return hasPower && !open;
	}

	private void slotCook(int i, World worldIn, ReizouTop_TileEntity tileEntity) {
		AbstractReizouTileEntity.invSlot = i;
		tileEntity.markDirty();
		
		if (tileEntity.readyCook(i)) {
			IRecipe<?> iRecipe = worldIn.getRecipeManager().getRecipe(CM_RecipeType.FREEZE_RECIPE, tileEntity, worldIn).orElse(null);

			if (iRecipe != null) {
				ItemStack output = ((AbstractColdRecipe) iRecipe).getCraftingResult(tileEntity);
				
				if (!output.isEmpty()) {
					boolean hasRemain = tileEntity.items.get(i).hasContainerItem();
					
					if (hasRemain) {
						if (tileEntity.canRemainCook(i, output)) {
							tileEntity.storageTime++;
							
							if (tileEntity.storageTime >= tileEntity.recipeCookingTime) {
								tileEntity.remainItem(i);
								tileEntity.cookRecipe(i, output, iRecipe); } }
					}
					
					if (!hasRemain) {
						if (tileEntity.canCook(i, output)) {
							tileEntity.storageTime++;
							
							if (tileEntity.storageTime >= tileEntity.recipeCookingTime) {
								tileEntity.cookRecipe(i, output, iRecipe); } }
					}
					
				}
			}
		}
	}
	
	/// ISidedInventory //////////
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i < 28;
	}
}
