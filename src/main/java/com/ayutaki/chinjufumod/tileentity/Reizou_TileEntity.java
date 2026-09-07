package com.ayutaki.chinjufumod.tileentity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.kitchen.Reizou;
import com.ayutaki.chinjufumod.gui.ReizouMenu;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.recipe_type.Recipe_Chill;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Reizou_TileEntity extends AbstractReizouTileEntity {

	private NonNullList<ItemStack> items = NonNullList.<ItemStack>withSize(45, ItemStack.EMPTY);
	private int tickInterval;

	public Reizou_TileEntity() { }

	public int getSizeInventory() {
		return 45;
	}

	public String getName() {
		return this.hasCustomName() ? this.customName : "container.chinjufumod.reizou.name";
	}

	public static void registerFixesChest(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(Reizou_TileEntity.class, new String[] {"Items"}));
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.items = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound)) {
			ItemStackHelper.loadAllItems(compound, this.items);
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items);
		}
		return compound;
	}

	@Nullable
	protected Reizou_TileEntity getAdjacentChest(EnumFacing side) {
		BlockPos pos = this.pos.offset(side);

		if (this.isChestAt(pos)) {
			TileEntity tileentity = this.world.getTileEntity(pos);

			if (tileentity instanceof Reizou_TileEntity) {
				Reizou_TileEntity tileentitychest = (Reizou_TileEntity)tileentity;
				return tileentitychest;
			}
		}
		return null;
	}

	private boolean isChestAt(BlockPos posIn) {
		if (this.world == null) {
			return false;
		}
		else {
			Block block = this.world.getBlockState(posIn).getBlock();
			return block instanceof Reizou;
		}
	}

	/*	 チェストの開閉時の効果音 */
	public void update() {
		
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		++this.tickInterval;
		this.openCount = calculatePlayersUsingSync(this.world, this, this.tickInterval, i, j, k, this.openCount);
		this.preOpenness = this.lidOpenness;
		
		if (this.openCount > 0 && this.lidOpenness == 0.0F) {
			this.playSound(SoundEvents_CM.REIZOU_OPEN);
		}

		if (this.openCount == 0 && this.lidOpenness > 0.0F || this.openCount > 0 && this.lidOpenness < 1.0F) {
			float f2 = this.lidOpenness;

			if (this.openCount > 0) {
				this.lidOpenness += 0.1F;
			}
			else {
				this.lidOpenness -= 0.1F;
			}

			if (this.lidOpenness > 1.0F) {
				this.lidOpenness = 1.0F;
			}

			if (this.lidOpenness < 0.5F && f2 >= 0.5F) {
				this.playSound(SoundEvents_CM.REIZOU_CLOSE);
			}

			if (this.lidOpenness < 0.0F) {
				this.lidOpenness = 0.0F;
			}
		}
		
		if (!this.world.isRemote) {
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

	/*	 チェストの開閉処理 */
	public void openInventory(EntityPlayer playerIn) {
		if (!playerIn.isSpectator()) {
			if (this.openCount < 0) {
				this.openCount = 0; }

			++this.openCount;
			this.world.addBlockEvent(this.pos, getBlockType(), 1, this.openCount);
			this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
		}
	}

	public void closeInventory(EntityPlayer playerIn) {
		if (!playerIn.isSpectator() && this.getBlockType() instanceof Reizou) {
			--this.openCount;
			this.world.addBlockEvent(this.pos, getBlockType(), 1, this.openCount);
			this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
		}
	}

	public boolean isOpen() {
		return this.lidOpenness != 0.0F;
	}
	

	public static int calculatePlayersUsingSync(World worldIn, TileEntityLockable lTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayerUsing) {
		if (!worldIn.isRemote && numPlayerUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0) {
			numPlayerUsing = calculatePlayersUsing(worldIn, lTileEntity, x, y, z);
		}
		return numPlayerUsing;
	}
	
	public static int calculatePlayersUsing(World worldIn, TileEntityLockable lTileEntity, int x, int y, int z) {
		int i = 0;

		for(EntityPlayer playerIn : worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)x - 5.0F), (double)((float)y - 5.0F), (double)((float)z - 5.0F), (double)((float)(x + 1) + 5.0F), (double)((float)(y + 1) + 5.0F), (double)((float)(z + 1) + 5.0F)))) {
			if (playerIn.openContainer instanceof ReizouMenu) {
				IInventory iinventory = ((ReizouMenu)playerIn.openContainer).getLowerChestInventory();
				if (iinventory == lTileEntity) {
					++i;
				}
			}
		}
		return i;
	}

	/* GUIのID */
	public String getGuiID() {
		return ChinjufuMod.MOD_ID + ":reizou_45";
	}

	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		this.fillWithLoot(playerIn);
		return new ReizouMenu(playerInventory, this, playerIn);
	}

	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}
	
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}
	
	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected boolean power() {
		IBlockState state = this.world.getBlockState(this.pos);
		boolean hasPower = (state.getBlock() instanceof Reizou)? state.getValue(Reizou.POWERED) : false;
		
		return hasPower && !this.isOpen();
	}

	private void slotCook(int i, World worldIn, Reizou_TileEntity tileEntity) {
		AbstractReizouTileEntity.invSlot = i;
		tileEntity.markDirty();

		if (tileEntity.readyCook(i)) {
			
			ItemStack stack_i = tileEntity.items.get(i);
			ItemStack output = Recipe_Chill.instance().getCookResult(stack_i);
			if (!output.isEmpty()) {
				
				float exp = Recipe_Chill.instance().getCookExp(output);
				boolean hasRemain = stack_i.getItem().hasContainerItem(stack_i);
				if (hasRemain) {
					if (tileEntity.canRemainCook(i, output)) {
						tileEntity.storageTime++;
						
						if (tileEntity.storageTime >= tileEntity.recipeCookingTime) {
							tileEntity.remainItem(i);
							tileEntity.cookRecipe(i, output, exp); } }
				}
				
				if (!hasRemain) {
					if (tileEntity.canCook(i, output)) {
						tileEntity.storageTime++;
						
						if (tileEntity.storageTime >= tileEntity.recipeCookingTime) {
							tileEntity.cookRecipe(i, output, exp); } }
				}
				
			}
		}
	}
	
	/// ISidedInventory //////////
	public boolean canPlaceItem(int i, ItemStack stack) {
		return i < 46;
	}
}
