package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.AbstractCoralPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.KelpBlock;
import net.minecraft.block.KelpTopBlock;
import net.minecraft.block.SeaGrassBlock;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.block.TallSeaGrassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CMEvents {
	/* Sound Only */
	public static void soundAmado(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.AMADO_CANCEL, SoundCategory.BLOCKS, 1.0F, 1.0F); } //4
	
	public static void soundAmadoWin(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.AMADO_CANCEL, SoundCategory.BLOCKS, 0.8F, 1.1F); } //3
	
	public static void soundBubble(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 2.0F, 0.8F); } //17
	
	public static void soundClothBreak(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F); } //3
	
	public static void soundClothPlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); } //7
	
	public static void soundCurtain(World worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.CURTAIN, SoundCategory.BLOCKS, volume, pitch); } //5
	
	public static void soundDrink(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.GOKU, SoundCategory.PLAYERS, 1.0F, 1.0F); } //7
	
	public static void soundEat(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.PAKU, SoundCategory.PLAYERS, 1.0F, 1.0F); } //26

	public static void soundFlint(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, 0.8F); } //7
	
	public static void soundFireExting(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.2F, 2.0F); } //13

	public static void soundFish(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.NEUTRAL, 0.3F, 1.0F); } //5
	
	public static void soundFusumaL(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.FUSUMA, SoundCategory.BLOCKS, 1.0F, 1.0F); } //13
	
	public static void soundFusumaS(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.FUSUMA_SHORT, SoundCategory.BLOCKS, 1.0F, 1.0F); } //15

	
	public static void soundHikidoL(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.HIKIDO, SoundCategory.BLOCKS, 1.0F, 1.0F); } //3
	
	public static void soundHikidoS(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.HIKIDO_SHORT, SoundCategory.BLOCKS, 0.7F, 1.0F); } //3
	
	public static void soundItemPick(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, 2.0F); } //4
	
	public static void soundKinuzure(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.KINUZURE, SoundCategory.BLOCKS, 1.0F, 1.0F); } //10
	
	public static void soundKotePlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_HIT, SoundCategory.BLOCKS, 0.8F, 3.0F); } //3
	
	public static void soundOpenOven(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.OPEN_OVEN, SoundCategory.BLOCKS, 0.8F, 1.0F); } //5
	
	public static void soundPage(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.PAGE_TURN, SoundCategory.BLOCKS, 1.0F, 0.8F); } //3
	
	
	public static void soundSAKEBottleFill(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 0.7F, 0.8F); } //4

	public static void soundSHOUYU(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.SHOUYU, SoundCategory.BLOCKS, 1.0F, 1.0F); } //5
	
	public static void soundSitChair(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.SIT_CHAIR, SoundCategory.BLOCKS, 1.0F, 1.0F); } //5
	
	public static void soundSnowBreak(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F); } //15

	public static void soundSnowTake(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.BLOCKS, 0.8F, 1.2F); } //4
	
	public static void soundSnowPlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 0.8F, 1.2F); } //15
	
	public static void soundStoneButton_Off(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.8F, 0.65F); } //8
	
	public static void soundStoneButton_On(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0F, 0.8F); } //7 - ShishiOdoshi_2

	public static void soundStonePlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); } //12

	public static void soundTouchBlock(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.TOUCH_BLOCK, SoundCategory.BLOCKS, 1.0F, 0.75F); } //99

	
	public static void soundWaterUse(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WATER_SPLASH, SoundCategory.BLOCKS, 0.5F, 1.2F); } //8
	
	public static void soundWin_Open(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_OPEN, SoundCategory.BLOCKS, 0.8F, 1.1F); } //18
	
	public static void soundWin_Close(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_CLOSE, SoundCategory.BLOCKS, 0.8F, 1.1F); } //18
	
	public static void soundWin_OpenL(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F); } //3
	
	public static void soundWin_CloseL(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_CLOSE, SoundCategory.BLOCKS, 1.0F, 1.0F); } //3
	
	public static void soundWoodPlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); } //29

	
	public static void soundBucketEmpty(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F); } 
	} //5
	
	public static void soundBucketFill(World worldIn, BlockPos pos, PlayerEntity playerIn, float volume, float pitch) {
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, volume, pitch); }
	} //6
	
	public static void wadaikoTop(World worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.WADAIKO_TOP, SoundCategory.BLOCKS, volume, pitch); } //17
	
	public static void wadaikoSide(World worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.WADAIKO_SIDE, SoundCategory.BLOCKS, volume, pitch); } //17

	
	/* Sound & Message */
	public static void textEarlyCollect(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.earlycollect"), true); } //112
	
	public static void textEarlyUse(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.earlyuse"), true); } //6
	
	public static void textFullItem(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.fullitem"), true); } //116
	
	public static void textNoPlace(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.noplace"), true); } //25
	
	public static void textNotEnough_Items(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.notenough_items"), true); } //11
	
	public static void textNotHave(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.nothave"), true); } //244
	
	public static void textNotSneak(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.notsneak"), true); } //9
	
	public static void textIsBlocked(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.blocked"), true); } //65

	public static void textIsEmpty(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.empty"), true); } //49
	
	public static void textIsSleeping(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.sleeping"), true); } //3
	
	public static void textIsWaterlogged(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.waterlogged"), true); } //101
	
	public static void textRequestHeat(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.heat"), true); } //4
	
	public static void textRequestCool(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.cool"), true); } //2
	
	public static void textNotEnough_EXP(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.notenough_exp"), true); } //5
	
	public static void textNotDig(PlayerEntity playerIn) {
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.notdig"), true); } //3
	
	public static void textNeedClean(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.need_clean"), true); } //3
	
	public static void textIsWaxed(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.is_waxed"), true); } //3
	
	
	/** Drop Item and Sound**/
	public static void drop1_ROTTENFOOD(ServerWorld worldIn, BlockPos pos) {
		CMEvents.soundSnowBreak(worldIn, pos);
		ItemStack stack = new ItemStack(Items_NoTab.ROTTEN_FOOD, 1);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	} //73
	
	public static void dropN_ROTTENFOOD(int amount, ServerWorld worldIn, BlockPos pos) {
		CMEvents.soundSnowBreak(worldIn, pos);
		ItemStack stack = new ItemStack(Items_NoTab.ROTTEN_FOOD, amount);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	} //6
	
	public static void destroyN_STICK_ROTTEN(int amount, ServerWorld worldIn, BlockPos pos) {
		CMEvents.dropN_ROTTENFOOD(amount, worldIn, pos);
		ItemStack stack = new ItemStack(Items.STICK, amount);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
		worldIn.destroyBlock(pos, false);
	} //17
	
	public static void destroyDrop_ClothB(ServerWorld worldIn, BlockPos pos) {
		CMEvents.soundClothBreak(worldIn, pos);
		worldIn.destroyBlock(pos, true);
	} //6
	
	
	/** Sound & add Item **/
	public static void emptyTake_1Item(World worldIn, BlockPos pos, PlayerEntity playerIn, Item item) {
		CMEvents.soundItemPick(worldIn, pos);
		playerIn.inventory.addItemStackToInventory(new ItemStack(item, 1)); } //29
	
	public static void emptyTake_NItem(World worldIn, BlockPos pos, PlayerEntity playerIn, Item item, int amount) {
		CMEvents.soundItemPick(worldIn, pos);
		playerIn.inventory.addItemStackToInventory(new ItemStack(item, amount)); } //5

	public static void emptyTake1_SnowB(World worldIn, BlockPos pos, PlayerEntity playerIn, Item item) {
		CMEvents.soundSnowTake(worldIn, pos);
		CMEvents.emptyTake_1Item(worldIn, pos, playerIn, item); } //24
	
	public static void emptyTakeN_SnowB(World worldIn, BlockPos pos, PlayerEntity playerIn, Item item, int amount) {
		CMEvents.soundSnowTake(worldIn, pos);
		CMEvents.emptyTake_NItem(worldIn, pos, playerIn, item, amount); } //18
	
	public static void emptyTakeN_ClothB(World worldIn, BlockPos pos, PlayerEntity playerIn, Item item, int amount) {
		CMEvents.soundClothBreak(worldIn, pos);
		CMEvents.emptyTake_NItem(worldIn, pos, playerIn, item, amount); } //6
	
	public static void take_KINOKO(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items.BROWN_MUSHROOM); } //7
	
	public static void take_SAKANA(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.KUSHI_SAKANA_C); } //109

	
	/** Consume Item **/
	public static void consume1_Stack(ItemStack hStack, PlayerEntity playerIn) {
		boolean mode = playerIn.abilities.isCreativeMode;
		if (mode) { }
		else { hStack.shrink(1); } } //5
	
	public static void consumeN_Hand(int amount, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean mode = playerIn.abilities.isCreativeMode;
		if (mode) { }
		else { hStack.shrink(amount); } } //39

	public static void washHAKE_Cauldron(IWorld iworld, BlockPos pos, ItemStack hStack, PlayerEntity playerIn) {
		iworld.playSound(playerIn, pos, SoundEvents_CM.WATER_SPLASH, SoundCategory.BLOCKS, 0.5F, 1.2F);
		
		ItemStack take = new ItemStack(Items_Wadeco.HAKE, 1);
		if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
		CMEvents.consume1_Stack(hStack, playerIn); } //17
	
	public static void consume1_seBottle(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 0.7F, 1.0F); }
	} //4

	public static void consume1_seCheese(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.2F);
	} //7
	
	public static void consume1_seCloth(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundClothPlace(worldIn, pos); } //12
	
	public static void consume1_seDish(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.BLOCKS, 1.0F, 3.0F);
	} //20
	
	public static void consume1_seFlint(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, 0.8F);
	} //7

	public static void consume1_seSnowB(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundSnowTake(worldIn, pos); } //10
	
	public static void consume1_seSnowP(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundSnowPlace(worldIn, pos); } //136

	public static void consume1_seSplash(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWaterUse(worldIn, pos); } //10
	 
	public static void consume1_seStoneP(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundStonePlace(worldIn, pos); } //11

	public static void consume1_seWoodP(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWoodPlace(worldIn, pos); } //28
	
	public static void consume1_seWoodDish(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 3.0F);
	} //5
	
	public static void consume1_seWrite(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents_CM.WRITE_REPORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
	} //3
	
	public static void consumeN_seSnowP(int amount, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(amount, playerIn, hand);
		CMEvents.soundSnowPlace(worldIn, pos); } //14

	
	/** Take Item **/
	public static void takeN_Item(int amount, PlayerEntity playerIn, Hand hand, Item item) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		ItemStack take = new ItemStack(item, amount);
		if (hStack.isEmpty()) { playerIn.inventory.addItemStackToInventory(take); }
		else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
	}
	
	public static void take1Item(PlayerEntity playerIn, Hand hand, Item item) {
		CMEvents.takeN_Item(1, playerIn, hand, item);
	} //29
	
	public static void takeSAKEBottle_Fill(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, Item item) {
		CMEvents.soundItemPick(worldIn, pos);
		CMEvents.soundSAKEBottleFill(worldIn, pos);
		CMEvents.take1Item(playerIn, hand, item); } //5
	
	
	/** Change Item in hand **/
	public static void changeBottle_seBottle(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, Item item) {
		CMEvents.consume1_seBottle(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item); } //6
	
	public static void changeBottle_seSplash(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, Item item) {
		CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item); } //3
	
	public static void changeBowl_seBucket(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, Item item) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.2F);
		CMEvents.take1Item(playerIn, hand, item); } //8
	
	public static void changeBucket_seBucket(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, Item item) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.0F);
		CMEvents.take1Item(playerIn, hand, item); } //6
	
	public static void changeDish_seSnowB(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, Item item) {
		CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item); } //33
	
	public static void changeDish_seTea(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, Item item) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents_CM.TEA, SoundCategory.PLAYERS, 1.0F, 1.0F);
		CMEvents.take1Item(playerIn, hand, item); } //4
	
	
	/** Change Specific Item in Hand **/
	public static void Bottle_toWaterBottle(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		CMEvents.consume1_seBottle(worldIn, pos, playerIn, hand);
		ItemStack take = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.WATER);
		if (hStack.isEmpty()) { playerIn.setHeldItem(hand, take); }
		else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); } } //5

	public static void mode1Through_Consume(PlayerEntity playerIn, Hand hand, Item item) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean mode = playerIn.abilities.isCreativeMode;
		
		if (mode) { } 
		else { //!mode
			hStack.shrink(1);
			CMEvents.take1Item(playerIn, hand, item); } } //10
	
	public static void Bucket_toEmpty(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items.BUCKET);
		CMEvents.soundBucketEmpty(worldIn, pos, playerIn); } //8
	
	public static void MIZUOKE_toEmpty(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_Teatime.MIZUOKE);
		CMEvents.soundBucketEmpty(worldIn, pos, playerIn); } //8
	

	public static void Soysauce_to2(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_24);
		CMEvents.soundSHOUYU(worldIn, pos); } //3
	
	public static void Soysauce_to3(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_34);
		CMEvents.soundSHOUYU(worldIn, pos); } //3
	
	public static void Soysauce_to4(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_44);
		CMEvents.soundSHOUYU(worldIn, pos); } //3
	
	public static void Soysauce_toBottle(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items.GLASS_BOTTLE);
		CMEvents.soundSHOUYU(worldIn, pos); } //3
	
	
	/** Wash with MIZUOKE. **/
	public static void wash_Banner(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		ItemStack copy = hStack.copy();
		copy.setCount(1);
		BannerTileEntity.removeBannerData(copy);
		playerIn.addStat(Stats.CLEAN_BANNER);
		
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWaterUse(worldIn, pos);
		
		if (hStack.isEmpty()) { playerIn.setHeldItem(hand, copy); }
		else if (!playerIn.inventory.addItemStackToInventory(copy)) { playerIn.dropItem(copy, false); }
		else if (playerIn instanceof ServerPlayerEntity) {
			((ServerPlayerEntity)playerIn).sendContainerToPlayer(playerIn.container); }
	} //3
	
	public static void wash_Shulker(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		ItemStack SHULKER = new ItemStack(Blocks.SHULKER_BOX, 1);
		if (hStack.hasTag()) { SHULKER.setTag(hStack.getTag().copy()); }

		playerIn.setHeldItem(hand, SHULKER);
		CMEvents.soundWaterUse(worldIn, pos);
		playerIn.addStat(Stats.CLEAN_SHULKER_BOX);
	} //3
	
	public static void washHAKE_MIZUOKE(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.soundWaterUse(worldIn, pos);
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, Items_Wadeco.HAKE);
	} //3
	
	
	/** Creative mode Through **/
	public static void mode1Through_takeItem(PlayerEntity playerIn, Hand hand, Item item) {
		boolean mode = playerIn.abilities.isCreativeMode;
		if (mode) { } 
		else { CMEvents.take1Item(playerIn, hand, item); } } //4
	
	public static void toolDamege(int damage, PlayerEntity playerIn, ItemStack hStack) {
		boolean mode = playerIn.abilities.isCreativeMode;
		if (playerIn != null) {
			hStack.damageItem(mode? 0 : damage, playerIn, user -> { user.sendBreakAnimation(playerIn.getActiveHand()); } ); }
	} //29
	
	
	/** Get EXP. **/
	public static void addEXP(int i, World worldIn, BlockPos pos) {
		worldIn.addEntity(new ExperienceOrbEntity(worldIn, pos.getX(), pos.getY() + 0.5D, pos.getZ(), i)); 
	} //27
	
	public static void BoneMeal_Particle(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		
		for(int n = 0; n < 15; ++n) {
			double d0 = worldIn.rand.nextGaussian() * 0.02D;
			double d1 = worldIn.rand.nextGaussian() * 0.02D;
			double d2 = worldIn.rand.nextGaussian() * 0.02D;
			worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + worldIn.rand.nextFloat(), pos.getY() +worldIn.rand.nextFloat(), pos.getZ() + worldIn.rand.nextFloat(), d0, d1, d2); }
	} //6
	
	public static void Wax_Particle(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		
		for(int n = 0; n < 15; ++n) {
			double d0 = worldIn.rand.nextGaussian() * 0.02D;
			double d1 = worldIn.rand.nextGaussian() * 0.02D;
			double d2 = worldIn.rand.nextGaussian() * 0.02D;
			worldIn.addParticle(ParticleTypes_CM.WAX_PT, pos.getX() + worldIn.rand.nextFloat(), pos.getY() +worldIn.rand.nextFloat(), pos.getZ() + worldIn.rand.nextFloat(), d0, d1, d2); }
		worldIn.playSound(null, pos, SoundEvents_CM.PAINT, SoundCategory.BLOCKS, 1.0F, 1.0F); 
	} //3
	
	
	/** Error when using item. **/
	public static void soundError(World worldIn, PlayerEntity playerIn) {
		worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.ERROR, SoundCategory.PLAYERS, 1.0F, 1.0F);
	} //5
	
	public static void Item_Waterlogged(World worldIn, PlayerEntity playerIn) {
		CMEvents.soundError(worldIn, playerIn);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.waterlogged"), true);
	} //4
	
	public static void walkOnWater(World worldIn, PlayerEntity playerIn) {
		BlockState AIR = Blocks.AIR.getDefaultState();
		BlockState WAKE1 = Chinjufu_Blocks.WAKE_WATER1.getDefaultState();
		
		double x = (double) playerIn.prevPosX;
		double y = (double) playerIn.prevPosY;
		double z = (double) playerIn.prevPosZ;
		
		/** for start 着水時の落下が多いため範囲を拡大 **/
		for(double i = -1.3D; i <= 1.3D; i++)
		for(double j = -1.3D; j <= 1.3D; j++)

		if (worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j)).getBlock() instanceof FlowingFluidBlock) {
			BlockState underState = worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j));
			if (underState.getMaterial() == Material.WATER && underState.get(FlowingFluidBlock.LEVEL).intValue() == 0) {

				BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
				if (levelState.getMaterial().isReplaceable()) {
					if (levelState.getMaterial() == Material.WATER && levelState.get(FlowingFluidBlock.LEVEL).intValue() == 0) { }
					else { worldIn.setBlockState(new BlockPos(x + i, y, z + j), WAKE1, 3); }
				}
				
				else { } }
			else { } 
		}
		/** for end. **/
		
		for(double i = -1.3D; i <= 1.3D; i++)
		for(double j = -1.3D; j <= 1.3D; j++)
		if (worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j)).getBlock() instanceof KelpTopBlock ||
				worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j)).getBlock() instanceof KelpBlock) {
			
			BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
			if (levelState.getMaterial().isReplaceable()) {
				if (levelState.getMaterial() == Material.WATER && levelState.get(FlowingFluidBlock.LEVEL).intValue() == 0) { }
				else { worldIn.setBlockState(new BlockPos(x + i, y, z + j), WAKE1, 3); }
			}
			else { } 
		}
		
		for(double i = -1.3D; i <= 1.3D; i++)
		for(double j = -1.3D; j <= 1.3D; j++)
		if (worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j)).getBlock() instanceof SeaGrassBlock ||
				worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j)).getBlock() instanceof TallSeaGrassBlock) {
			
			BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
			if (levelState.getMaterial().isReplaceable()) {
				if (levelState.getMaterial() == Material.WATER && levelState.get(FlowingFluidBlock.LEVEL).intValue() == 0) { }
				else { worldIn.setBlockState(new BlockPos(x + i, y, z + j), WAKE1, 3); }
			}
			else { } 
		}
		
		for(double i = -1.3D; i <= 1.3D; i++)
		for(double j = -1.3D; j <= 1.3D; j++)
		if (worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j)).getBlock() instanceof AbstractCoralPlantBlock) {
			BlockState underState = worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j));
			if (underState.get(AbstractCoralPlantBlock.WATERLOGGED)) {
				
				BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
				if (levelState.getMaterial().isReplaceable()) {
					if (levelState.getMaterial() == Material.WATER && levelState.get(FlowingFluidBlock.LEVEL).intValue() == 0) { }
					else { worldIn.setBlockState(new BlockPos(x + i, y, z + j), WAKE1, 3); }
				}
				
				else { } }
			else { } 
		}
		
		for(double i = -1.3D; i <= 1.3D; i++)
		for(double j = -1.3D; j <= 1.3D; j++)
		if (worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j)).getBlock() instanceof SeaPickleBlock) {
			BlockState underState = worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j));
			if (underState.get(SeaPickleBlock.WATERLOGGED)) {
				
				BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
				if (levelState.getMaterial().isReplaceable()) {
					if (levelState.getMaterial() == Material.WATER && levelState.get(FlowingFluidBlock.LEVEL).intValue() == 0) { }
					else { worldIn.setBlockState(new BlockPos(x + i, y, z + j), WAKE1, 3); }
				}
				
				else { } }
			else { } 
		}
		
		/** Player Run Speed 5.6 m/s. Bunny Hop 7.1m/s. **/
		for(int ew = -8; ew <= 8; ew++)
		for(int m = -8; m <= -6; m++)
		for(int h = -1; h <= 0; h++)
		if (worldIn.getBlockState(new BlockPos(x + ew, y + h, z + m)) == WAKE1){
			worldIn.setBlockState(new BlockPos(x + ew, y + h, z + m), AIR, 3); }
		
		for(int ew = -8; ew <= 8; ew++)
		for(int p = 6; p <= 8; p++)
		for(int h = -1; h <= 0; h++)
		if (worldIn.getBlockState(new BlockPos(x + ew, y + h, z + p)) == WAKE1){
			worldIn.setBlockState(new BlockPos(x + ew, y + h, z + p), AIR, 3); }
		
		for(int m = -8; m <= -6; m++)
		for(int ns = -6; ns <= 6; ns++)
		for(int h = -1; h <= 0; h++)
		if (worldIn.getBlockState(new BlockPos(x + m, y + h, z + ns)) == WAKE1){
			worldIn.setBlockState(new BlockPos(x + m, y + h, z + ns), AIR, 3); }
		
		for(int p = 6; p <= 8; p++)
		for(int ns = -6; ns <= 6; ns++)
		for(int h = -1; h <= 0; h++)
		if (worldIn.getBlockState(new BlockPos(x + p, y + h, z + ns)) == WAKE1){
			worldIn.setBlockState(new BlockPos(x + p, y + h, z + ns), AIR, 3); }
		
		else { }
	} //14
}
