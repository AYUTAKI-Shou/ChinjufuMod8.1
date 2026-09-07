package com.ayutaki.chinjufumod.tileentity;

import com.ayutaki.chinjufumod.blocks.kitchen.Reizou;
import com.ayutaki.chinjufumod.gui.ReizouMenu;
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
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IChestLid.class)
public class Reizou_TileEntity extends AbstractReizouTileEntity implements IChestLid {

	private NonNullList<ItemStack> items = NonNullList.withSize(45, ItemStack.EMPTY);
	private int tickInterval;
	
	protected Reizou_TileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
	}

	public Reizou_TileEntity() {
		this(TileEntity_CM.REIZOU);
	}

	public int getContainerSize() {
		return 45;
	}

	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.chinjufumod.reizou");
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(compound)) {
			ItemStackHelper.loadAllItems(compound, this.items);
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		if (!this.trySaveLootTable(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items);
		}
		return compound;
	}

	public void tick() {
		int i = this.worldPosition.getX();
		int j = this.worldPosition.getY();
		int k = this.worldPosition.getZ();
		++this.tickInterval;
		this.openCount = getOpenCount(this.level, this, this.tickInterval, i, j, k, this.openCount);
		this.preOpenness = this.lidOpenness;
		//float f = 0.1F;
		if (this.openCount > 0 && this.lidOpenness == 0.0F) {
			this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(Reizou.OPEN, Boolean.valueOf(true)), 3);
			this.playSound(SoundEvents_CM.REIZOU_OPEN);
		}

		if (this.openCount == 0 && this.lidOpenness > 0.0F || this.openCount > 0 && this.lidOpenness < 1.0F) {
			float f1 = this.lidOpenness;
			if (this.openCount > 0) {
				this.lidOpenness += 0.1F;
			}
			else {
				this.lidOpenness -= 0.1F;
			}

			if (this.lidOpenness > 1.0F) {
				this.lidOpenness = 1.0F;
			}

			float f2 = 0.5F;
			if (this.lidOpenness < f2 && f1 >= f2) {
				this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(Reizou.OPEN, Boolean.valueOf(false)), 3);
				this.playSound(SoundEvents_CM.REIZOU_CLOSE);
			}
			
			if (this.lidOpenness < 0.0F) {
				this.lidOpenness = 0.0F;
				this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(Reizou.OPEN, Boolean.valueOf(false)), 3);
			}
		}
		
		if (!this.level.isClientSide){
			if (this.power()) { 
				this.slotCook(0, this.level, this);
				this.slotCook(1, this.level, this);
				this.slotCook(2, this.level, this);
				this.slotCook(3, this.level, this);
				this.slotCook(4, this.level, this);
				this.slotCook(5, this.level, this);
				this.slotCook(6, this.level, this);
				this.slotCook(7, this.level, this);
			}
			
			else {
				this.storageTime = 0;
				this.setChanged(); }
		}
	}

	public static int getOpenCount(World worldIn, LockableTileEntity lTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayerUsing) {
		if (!worldIn.isClientSide && numPlayerUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0) {
			numPlayerUsing = getOpenCount(worldIn, lTileEntity, x, y, z);
		}
		return numPlayerUsing;
	}

	/* ChestContainer -> ReizouMenu */
	public static int getOpenCount(World worldIn, LockableTileEntity lTileEntity, int x, int y, int z) {
		int i = 0;
		float f = 5.0F;

		for(PlayerEntity playerIn : worldIn.getEntitiesOfClass(PlayerEntity.class, new AxisAlignedBB((double)((float)x - f), (double)((float)y - f), (double)((float)z - f), (double)((float)(x + 1) + f), (double)((float)(y + 1) + f), (double)((float)(z + 1) + f)))) {
			if (playerIn.containerMenu instanceof ReizouMenu) {
				IInventory iinventory = ((ReizouMenu)playerIn.containerMenu).getContainer();
				if (iinventory == lTileEntity) {
					++i;
				}
			}
		}
		return i;
	}

	protected void signalOpenCount() {
		Block block = this.getBlockState().getBlock();
		if (block instanceof Reizou) {
			this.level.blockEvent(this.worldPosition, block, 1, this.openCount);
			this.level.updateNeighborsAt(this.worldPosition, block);
		}
	}

	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	protected void setItems(NonNullList<ItemStack> stack) {
		this.items = stack;
	}

	@OnlyIn(Dist.CLIENT)
	public float getOpenNess(float count) {
		return MathHelper.lerp(count, this.preOpenness, this.lidOpenness);
	}

	public static void swapContents(Reizou_TileEntity tileEntity, Reizou_TileEntity otherTileEntity) {
		NonNullList<ItemStack> nonnulllist = tileEntity.getItems();
		tileEntity.setItems(otherTileEntity.getItems());
		otherTileEntity.setItems(nonnulllist);
	}

	protected Container createMenu(int id, PlayerInventory inventory) {
		return new ReizouMenu(MenuTypes_CM.REIZOU_MENU.get(), id, inventory, this);
		//return ChestContainer.threeRows(count, inventory);
	}

	////////////////////////
	protected net.minecraftforge.items.IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof Reizou)) {
			return new net.minecraftforge.items.wrapper.InvWrapper(this);
		}
		IInventory inv = Reizou.getContainer((Reizou) state.getBlock(), state, getLevel(), getBlockPos(), true);
		return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
	}
	
	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected int getTotalCookTime(World worldIn, AbstractReizouTileEntity tileEntity) {
		return worldIn.getRecipeManager().getRecipeFor(CM_RecipeType.CHILL_RECIPE, tileEntity, worldIn)
				.map(AbstractColdRecipe::getCookingTime).orElse(400);
	}
	
	protected boolean power() {
		BlockState state = this.level.getBlockState(this.worldPosition);
		boolean hasPower = (state.getBlock() instanceof Reizou)? state.getValue(Reizou.POWERED) : false;
		boolean open = (state.getBlock() instanceof Reizou)? state.getValue(Reizou.OPEN) : false;
		
		return hasPower && !open;
	}

	private void slotCook(int i, World worldIn, Reizou_TileEntity tileEntity) {
		AbstractReizouTileEntity.invSlot = i;
		tileEntity.setChanged();
		
		if (tileEntity.readyCook(i)) {
			
			IRecipe<?> iRecipe = worldIn.getRecipeManager().getRecipeFor(CM_RecipeType.CHILL_RECIPE, tileEntity, worldIn).orElse(null);
			if (iRecipe != null) {

				ItemStack output = ((AbstractColdRecipe) iRecipe).assemble(tileEntity);
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
	@Override
	public boolean canPlaceItem(int i, ItemStack stack) {
		return i < 46;
	}
}
