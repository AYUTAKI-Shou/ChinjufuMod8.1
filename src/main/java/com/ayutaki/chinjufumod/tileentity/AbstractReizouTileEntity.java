package com.ayutaki.chinjufumod.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class AbstractReizouTileEntity extends TileEntityLockableLoot implements ITickable, ISidedInventory {

	protected float lidOpenness;
	protected float preOpenness;
	protected int openCount;

	public static int invSlot;
	protected int storageTime;
	protected int recipeCookingTime;
	public float totalEXP = 0.0F;
	public int getField(int index) {
		switch (index) {
		case 0:
			return AbstractReizouTileEntity.this.storageTime;
		case 1:
			return AbstractReizouTileEntity.this.recipeCookingTime;
		case 2:
			return AbstractReizouTileEntity.invSlot;
		default:
			return 0;
		}
	}

	public void setField(int index, int value) {
		switch (index) {
		case 0:
			AbstractReizouTileEntity.this.storageTime = value;
		case 1:
			AbstractReizouTileEntity.this.recipeCookingTime = value;
		case 2:
			AbstractReizouTileEntity.invSlot = value;
		}
	}

	public int getFieldCount() {
		return 3;
	}
	
	public AbstractReizouTileEntity() { }

	/*	 インベントリーの数 */
	public abstract int getSizeInventory();

	/* Get the name of this object. For players this returns their username */
	public abstract String getName();

	/* 収納したアイテムの処理 */
	public static void registerFixesChest(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(AbstractReizouTileEntity.class, new String[] {"Items"}));
	}
	
	/* インベントリにおけるスタックの最大値 */
	public int getInventoryStackLimit() {
		return 64;
	}

	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
	}
	
	/* タイルエンティを無効化 */
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}
	
	//protected AbstractReizouTileEntity getAdjacentChest(EnumFacing side) {

	//private boolean isChestAt(BlockPos posIn) {

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.storageTime = compound.getInteger("StorageTime");
		this.recipeCookingTime = compound.getInteger("RecipeCookingTime");
		AbstractReizouTileEntity.invSlot = compound.getInteger("InventorySlot");
		this.totalEXP = compound.getFloat("TotalEXP");
		
		if (compound.hasKey("CustomName", 8)) {
			this.customName = compound.getString("CustomName");
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("StorageTime", this.storageTime);
		compound.setInteger("RecipeCookingTime", this.recipeCookingTime);
		compound.setInteger("InventorySlot", AbstractReizouTileEntity.invSlot);
		compound.setFloat("TotalEXP", this.totalEXP);
		
		if (this.hasCustomName()) {
			compound.setString("CustomName", this.customName);
		}
		return compound;
	}

	//public static int calculatePlayersUsingSync(World worldIn, TileEntityLockable lTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayerUsing) {
	
	//public static int calculatePlayersUsing(World worldIn, TileEntityLockable lTileEntity, int x, int y, int z) {

	protected void playSound(SoundEvent sound) {
		double dx = (double) this.pos.getX() + 0.5D;
		double dy = (double) this.pos.getY() + 0.5D;
		double dz = (double) this.pos.getZ() + 0.5D;
		this.world.playSound((EntityPlayer) null, dx, dy, dz, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}
	
	/*	 クライアントイベントの受信 */
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			this.openCount = type;
			return true;
		}
		else {
			return super.receiveClientEvent(id, type);
		}
	}

	protected abstract NonNullList<ItemStack> getItems();

	/* GUIのID */
	public abstract String getGuiID();

	public abstract Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn);

	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}
	
	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected int getTotalCookTime() {
		return 400;
	}
	
	protected abstract boolean power();
	
	protected ItemStack slotStack(int i) {
		return this.getItems().get(i);
	}
	
	protected boolean readyCook(int i) {
		return !this.slotStack(i).isEmpty();
	}

	protected ItemStack resultStackSet(int slot, int resultSlot, ItemStack stack) {
		return this.getItems().set(slot + resultSlot, stack.copy());
	}
	
	///hasRemain//////////
	protected boolean canRemainCook(int i, ItemStack output) {
		ItemStack stack_i = this.slotStack(i);
		ItemStack remain_i = stack_i.getItem().getContainerItem(stack_i);
		
		ItemStack stack_9 = this.slotStack(i + 9);
		ItemStack stack_1 = this.slotStack(i + 1);
		int count_1 = stack_1.getCount();
		
		if (stack_9.isEmpty()) { 
			if (stack_1.isEmpty()) { return true; }
			
			if (stack_1.isItemEqual(remain_i)) { 
				return count_1 < remain_i.getMaxStackSize(); }
		}
		
		else if (stack_9.isItemEqual(output)) { 
			if (stack_9.getCount() + output.getCount() < output.getMaxStackSize()) {
				if (stack_1.isEmpty()) { return true; }
				
				if (stack_1.isItemEqual(remain_i)) { 
					return count_1 < remain_i.getMaxStackSize(); }
			}
		}
		return false;
	}
	
	protected void remainItem(int i) {
		ItemStack stack_i = this.slotStack(i);
		ItemStack remain_i = stack_i.getItem().getContainerItem(stack_i);
		
		ItemStack stack_1 = this.slotStack(i + 1);
		if (stack_1.isEmpty()) {
			this.resultStackSet(i, 1, remain_i); }
		
		else if (stack_1.isItemEqual(remain_i)) { 
			stack_1.grow(1); }
	}
	
	///not hasRemain//////////
	protected boolean canCook(int i, ItemStack output) {
		ItemStack stack_i = this.slotStack(i);
		if (!stack_i.isEmpty()) {

			ItemStack stack_9 = this.slotStack(i + 9);
			if (stack_9.isEmpty()) { return true; }
			
			else if (stack_9.isItemEqual(output)) {
				return stack_9.getCount() + output.getCount() < output.getMaxStackSize(); }
		}
		return false;
	}
	
	protected void cookRecipe(int i, ItemStack output, float exp) {
		ItemStack stack_9 = this.getItems().get(i + 9);
		if (stack_9.isEmpty()) {
			this.resultStackSet(i, 9, output); }
		
		else if (stack_9.isItemEqual(output)) { 
			stack_9.grow(output.getCount()); }
		
		this.slotStack(i).shrink(1);
		this.recipeCookingTime = this.getTotalCookTime();
		AbstractReizouTileEntity.invSlot = i;
		this.totalEXP += exp;
		this.storageTime = 0;
		this.markDirty();
	}
	
	//private void slotCook(int i, World worldIn, ReizouTop_TileEntity tileEntity) {
	
	/// ISidedInventory //////////
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
	}

	@Override
	public boolean canInsertItem(int i, ItemStack stack, EnumFacing direction) {
		return this.canPlaceItem(i, stack);
	}

	public abstract boolean canPlaceItem(int i, ItemStack stack);

	@Override
	public boolean canExtractItem(int i, ItemStack stack, EnumFacing direction) {
		return true;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		}
		else {
			return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}
	
	public boolean isEmpty() {
		for (ItemStack stack : this.getItems()) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public ItemStack getStackInSlot(int i) {
		return this.getItems().get(i);
	}
	
	public ItemStack decrStackSize(int i, int count) {
		return ItemStackHelper.getAndSplit(this.getItems(), i, count);
	}

	public ItemStack removeStackFromSlot(int i) {
		return ItemStackHelper.getAndRemove(this.getItems(), i);
	}
	
	public void setInventorySlotContents(int i, ItemStack stack) {
		ItemStack stack_i = this.getItems().get(i);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(stack_i) && ItemStack.areItemStackTagsEqual(stack, stack_i);
		this.getItems().set(i, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		
		if (i == 0 && !flag) { this.setAct(i); }
		if (i == 1 && !flag) { this.setAct(i); }
		if (i == 2 && !flag) { this.setAct(i); }
		if (i == 3 && !flag) { this.setAct(i); }
		if (i == 4 && !flag) { this.setAct(i); }
		if (i == 5 && !flag) { this.setAct(i); }
		if (i == 6 && !flag) { this.setAct(i); }
		if (i == 7 && !flag) { this.setAct(i); }
	}
	
	private void setAct(int i) {
		this.recipeCookingTime = this.getTotalCookTime();
		AbstractReizouTileEntity.invSlot = i;
		this.storageTime = 0;
		this.markDirty();
	}
	
	public void clear() {
		this.getItems().clear();
	}
	
	/// exp //////////
	/** Get it from the GUI. **/
	public void expFromGUI(EntityPlayer playerIn) {
		World worldIn = playerIn.world;
		if (worldIn == null || worldIn.isRemote) return;
		
		if (playerIn != null) {
			float total = this.totalEXP;
			
			if (total > 0.0F) {
				int i = MathHelper.floor(total);
				float point = total - i;
				
				if (i >= 1) {
					worldIn.spawnEntity(new EntityXPOrb(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, i));
					this.totalEXP = point;
					this.markDirty(); }
			}
		}
	}
	
	public void dropExp(World worldIn, BlockPos pos) {
		if (worldIn == null || worldIn.isRemote) return;
		
		float total = this.totalEXP;
		if (total > 0.0F) {
			
			int i = MathHelper.floor(total);
			float point = total - i;
			if (point > 0.0F && Math.random() < point)  { i++; }
			
			if (i >= 1) {
				worldIn.spawnEntity(new EntityXPOrb(worldIn, pos.getX(), pos.getY(), pos.getZ(), i));
				this.totalEXP = 0.0F;
				this.markDirty(); }
		}
	}
}
