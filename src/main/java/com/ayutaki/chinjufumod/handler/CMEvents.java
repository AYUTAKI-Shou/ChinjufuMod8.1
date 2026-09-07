package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.KelpPlantBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class CMEvents {
	/* Sound Only */
	public static void soundAmado(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.AMADO_CANCEL.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //4
	
	public static void soundAmadoWin(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.AMADO_CANCEL.get(), SoundSource.BLOCKS, 0.8F, 1.1F); } //3

	public static void soundBubble(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, 2.0F, 0.8F); } //17

	public static void soundClothBreak(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F); } //3
	
	public static void soundClothPlace(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F); } //7
	
	public static void soundCurtain(Level worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.CURTAIN.get(), SoundSource.BLOCKS, volume, pitch); } //5
	
	public static void soundDrink(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.GOKU.get(), SoundSource.PLAYERS, 1.0F, 1.0F); } //7
	
	public static void soundEat(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.PAKU.get(), SoundSource.PLAYERS, 1.0F, 1.0F); } //26

	public static void soundFlint(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 0.8F); } //7
	
	public static void soundFireExting(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.2F, 2.0F); } //13

	public static void soundFish(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.FISH_SWIM, SoundSource.NEUTRAL, 0.3F, 1.0F); } //5
	
	public static void soundFusumaL(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.FUSUMA.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //13
	
	public static void soundFusumaS(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.FUSUMA_SHORT.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //15


	public static void soundHikidoL(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.HIKIDO.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //3
	
	public static void soundHikidoS(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.HIKIDO_SHORT.get(), SoundSource.BLOCKS, 0.7F, 1.0F); } //3

	public static void soundItemPick(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, 2.0F); } //4
	
	public static void soundKinuzure(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.KINUZURE.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //10
	
	public static void soundKotePlace(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.METAL_HIT, SoundSource.BLOCKS, 0.8F, 3.0F); } //3
	
	public static void soundOpenOven(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.OPEN_OVEN.get(), SoundSource.BLOCKS, 0.8F, 1.0F); } //5

	public static void soundPage(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.PAGE_TURN.get(), SoundSource.BLOCKS, 1.0F, 0.8F); } //3
	
	
	public static void soundSAKEBottleFill(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 0.7F, 0.8F); } //4
	
	public static void soundSHOUYU(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.SHOUYU.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //5
	
	public static void soundSitChair(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.SIT_CHAIR.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //5
	
	public static void soundSnowBreak(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.SNOW_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F); } //15

	public static void soundSnowTake(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.SNOW_BREAK, SoundSource.BLOCKS, 0.8F, 1.2F); } //4
	
	public static void soundSnowPlace(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.SNOW_PLACE, SoundSource.BLOCKS, 0.8F, 1.2F); } //15
	
	public static void soundStoneButton_Off(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundSource.BLOCKS, 0.8F, 0.65F); } //8
	
	public static void soundStoneButton_On(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1.0F, 0.8F); } //7 - ShishiOdoshi_2

	public static void soundStonePlace(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F); } //12

	public static void soundTouchBlock(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.TOUCH_BLOCK.get(), SoundSource.BLOCKS, 1.0F, 0.75F); } //99

	
	public static void soundWaterUse(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WATER_SPLASH.get(), SoundSource.BLOCKS, 0.5F, 1.2F); } //8
	
	public static void soundWin_Open(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_OPEN.get(), SoundSource.BLOCKS, 0.8F, 1.1F); } //18
	
	public static void soundWin_Close(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_CLOSE.get(), SoundSource.BLOCKS, 0.8F, 1.1F); } //18
	
	public static void soundWin_OpenL(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_OPEN.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //3
	
	public static void soundWin_CloseL(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_CLOSE.get(), SoundSource.BLOCKS, 1.0F, 1.0F); } //3
	
	public static void soundWoodPlace(Level worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
	} //32, KamoiCherry, KamoiMangrove, KamoiPaleoak

	
	public static void soundBucketEmpty(Level worldIn, BlockPos pos, Player playerIn) {
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F); }
	} //5

	public static void soundBucketFill(Level worldIn, BlockPos pos, Player playerIn, float volume, float pitch) {
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.BUCKET_FILL, SoundSource.PLAYERS, volume, pitch); }
	} //6

	public static void wadaikoTop(Level worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.WADAIKO_TOP.get(), SoundSource.BLOCKS, volume, pitch); } //17
	
	public static void wadaikoSide(Level worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.WADAIKO_SIDE.get(), SoundSource.BLOCKS, volume, pitch); } //17

	
	/* Sound & Message */
	public static void textEarlyCollect(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.earlycollect"), true); } //112
	
	public static void textEarlyUse(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.earlyuse"), true); } //6
	
	public static void textFullItem(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.fullitem"), true); } //116
	
	public static void textNoPlace(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.noplace"), true); } //25
	
	public static void textNotEnough_Items(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.notenough_items"), true); } //11
	
	public static void textNotHave(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.nothave"), true); } //244
	
	public static void textNotSneak(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.notsneak"), true); } //9
	
	public static void textIsBlocked(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.blocked"), true); } //65

	public static void textIsEmpty(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.empty"), true); } //49
	
	public static void textIsSleeping(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.sleeping"), true); } //3
	
	public static void textIsWaterlogged(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.waterlogged"), true); } //101
	
	public static void textRequestHeat(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.heat"), true); } //4
	
	public static void textRequestCool(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.cool"), true); } //2
	
	public static void textNotEnough_EXP(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.notenough_exp"), true); } //5
	
	public static void textNotDig(Player playerIn) {
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.notdig"), true); } //3
	
	public static void textNeedClean(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.need_clean"), true); } //3
	
	public static void textIsWaxed(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.is_waxed"), true); } //3
	
	
	/** Drop Item and Sound**/
	public static void drop1_ROTTENFOOD(ServerLevel worldIn, BlockPos pos) {
		CMEvents.soundSnowBreak(worldIn, pos);
		ItemStack stack = new ItemStack(Items_NoTab.ROTTEN_FOOD.get(), 1);
		Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	} //73
	
	public static void dropN_ROTTENFOOD(int amount, ServerLevel worldIn, BlockPos pos) {
		CMEvents.soundSnowBreak(worldIn, pos);
		ItemStack stack = new ItemStack(Items_NoTab.ROTTEN_FOOD.get(), amount);
		Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	} //6
	
	public static void destroyN_STICK_ROTTEN(int amount, ServerLevel worldIn, BlockPos pos) {
		CMEvents.dropN_ROTTENFOOD(amount, worldIn, pos);
		ItemStack stack = new ItemStack(Items.STICK, amount);
		Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
		worldIn.destroyBlock(pos, false);
	} //17
	
	public static void destroyDrop_ClothB(ServerLevel worldIn, BlockPos pos) {
		CMEvents.soundClothBreak(worldIn, pos);
		worldIn.destroyBlock(pos, true);
	} //6
	
	
	/** Sound & add Item **/
	public static void emptyTake_1Item(Level worldIn, BlockPos pos, Player playerIn, Item item) {
		CMEvents.soundItemPick(worldIn, pos);
		playerIn.getInventory().add(new ItemStack(item, 1)); } //29
	
	public static void emptyTake_NItem(Level worldIn, BlockPos pos, Player playerIn, Item item, int amount) {
		CMEvents.soundItemPick(worldIn, pos);
		playerIn.getInventory().add(new ItemStack(item, amount)); } //5
	
	public static void emptyTake1_SnowB(Level worldIn, BlockPos pos, Player playerIn, Item item) {
		CMEvents.soundSnowTake(worldIn, pos);
		CMEvents.emptyTake_1Item(worldIn, pos, playerIn, item); } //24
	
	public static void emptyTakeN_SnowB(Level worldIn, BlockPos pos, Player playerIn, Item item, int amount) {
		CMEvents.soundSnowTake(worldIn, pos);
		CMEvents.emptyTake_NItem(worldIn, pos, playerIn, item, amount); } //18
	
	public static void emptyTakeN_ClothB(Level worldIn, BlockPos pos, Player playerIn, Item item, int amount) {
		CMEvents.soundClothBreak(worldIn, pos);
		CMEvents.emptyTake_NItem(worldIn, pos, playerIn, item, amount); } //6

	public static void take_KINOKO(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items.BROWN_MUSHROOM); } //7
	
	public static void take_SAKANA(Level worldIn, BlockPos pos, Player playerIn) {
		CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.KUSHI_SAKANA_C.get()); } //109

	
	/** Consume Item **/
	public static void consume1_Stack(ItemStack hStack, Player playerIn) {
		boolean mode = playerIn.getAbilities().instabuild;
		if (mode) { }
		else { hStack.shrink(1); } } //5
	
	public static void consumeN_Hand(int amount, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean mode = playerIn.getAbilities().instabuild;
		if (mode) { }
		else { hStack.shrink(amount); } } //39
	
	public static void washHAKE_Cauldron(Level worldIn, BlockPos pos, ItemStack hStack, Player playerIn) {
		worldIn.playSound(playerIn, pos, SoundEvents_CM.WATER_SPLASH.get(), SoundSource.BLOCKS, 0.5F, 1.2F);
		
		ItemStack take = new ItemStack(Items_Wadeco.HAKE.get(), 1);
		if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }
		CMEvents.consume1_Stack(hStack, playerIn); } //17
	
	public static void consume1_seBottle(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 0.7F, 1.0F); }
	} //4

	public static void consume1_seCheese(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.2F);
	} //7
	
	public static void consume1_seCloth(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundClothPlace(worldIn, pos); } //12
	
	public static void consume1_seDish(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1.0F, 3.0F);
	} //20
	
	public static void consume1_seFlint(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 0.8F);
	} //7

	public static void consume1_seSnowB(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundSnowTake(worldIn, pos); } //10
	
	public static void consume1_seSnowP(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundSnowPlace(worldIn, pos); } //136
	
	public static void consume1_seSplash(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWaterUse(worldIn, pos); } //10
	
	public static void consume1_seStoneP(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundStonePlace(worldIn, pos); } //14, KamoiCherry, KamoiMangrove, _Paleoak

	public static void consume1_seWoodP(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWoodPlace(worldIn, pos); } //31, KamoiCherry, KamoiMangrove, _Paleoak
	
	public static void consume1_seWoodDish(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 3.0F);
	} //5
	
	public static void consume1_seWrite(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents_CM.WRITE_REPORT.get(), SoundSource.BLOCKS, 1.0F, 1.0F); 
	} //3
	
	public static void consumeN_seSnowP(int amount, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(amount, playerIn, hand);
		CMEvents.soundSnowPlace(worldIn, pos); } //14

	
	/** Take Item **/
	public static void takeN_Item(int amount, Player playerIn, InteractionHand hand, Item item) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		ItemStack take = new ItemStack(item, amount);
		if (hStack.isEmpty()) { playerIn.getInventory().add(take); }
		else if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); } }
	
	public static void take1Item(Player playerIn, InteractionHand hand, Item item) {
		CMEvents.takeN_Item(1, playerIn, hand, item); } //29
	
	public static void takeSAKEBottle_Fill(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, Item item) {
		CMEvents.soundItemPick(worldIn, pos);
		CMEvents.soundSAKEBottleFill(worldIn, pos);
		CMEvents.take1Item(playerIn, hand, item); } //5
	

	/** Change Item in hand **/
	public static void changeBottle_seBottle(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, Item item) {
		CMEvents.consume1_seBottle(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item); } //6
	
	public static void changeBottle_seSplash(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, Item item) {
		CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item); } //3
	
	public static void changeBowl_seBucket(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, Item item) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.2F);
		CMEvents.take1Item(playerIn, hand, item); } //8
	
	public static void changeBucket_seBucket(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, Item item) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.0F);
		CMEvents.take1Item(playerIn, hand, item); } //6
	
	public static void changeDish_seSnowB(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, Item item) {
		CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item); } //33
	
	public static void changeDish_seTea(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, Item item) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents_CM.TEA.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
		CMEvents.take1Item(playerIn, hand, item); } //4
	

	/** Change Specific Item in Hand **/
	public static void Bottle_toWaterBottle(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		CMEvents.consume1_seBottle(worldIn, pos, playerIn, hand);
		ItemStack take = PotionContents.createItemStack(Items.POTION, Potions.WATER); 
		if (hStack.isEmpty()) { playerIn.setItemInHand(hand, take); }
		else if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }
		else if (playerIn instanceof ServerPlayer) {
			((ServerPlayer)playerIn).inventoryMenu.sendAllDataToRemote(); } } //5
	
	public static void mode1Through_Consume(Player playerIn, InteractionHand hand, Item item) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean mode = playerIn.getAbilities().instabuild;
		
		if (mode) { } 
		else { //!mode
			hStack.shrink(1);
			CMEvents.take1Item(playerIn, hand, item); } } //10

	public static void Bucket_toEmpty(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items.BUCKET);
		CMEvents.soundBucketEmpty(worldIn, pos, playerIn); } //8
	
	public static void MIZUOKE_toEmpty(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_Teatime.MIZUOKE.get());
		CMEvents.soundBucketEmpty(worldIn, pos, playerIn); } //9, Mizuoke_CAULDRON+1
	

	public static void Soysauce_to2(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_24.get());
		CMEvents.soundSHOUYU(worldIn, pos); } //3
	
	public static void Soysauce_to3(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_34.get());
		CMEvents.soundSHOUYU(worldIn, pos); } //3
	
	public static void Soysauce_to4(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_44.get());
		CMEvents.soundSHOUYU(worldIn, pos); } //3
	
	public static void Soysauce_toBottle(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items.GLASS_BOTTLE);
		CMEvents.soundSHOUYU(worldIn, pos); } //3
	

	/** Wash with MIZUOKE. **/
	public static void wash_Banner(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		BannerPatternLayers bannerLayers = hStack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);

		ItemStack copy = hStack.copyWithCount(1);
		copy.set(DataComponents.BANNER_PATTERNS, bannerLayers.removeLast());
		
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWaterUse(worldIn, pos);
		
		if (hStack.isEmpty()) { playerIn.setItemInHand(hand, copy); }
		else if (playerIn.getInventory().add(copy)) { playerIn.inventoryMenu.sendAllDataToRemote(); } 
		else { playerIn.drop(copy, false); }

		playerIn.awardStat(Stats.CLEAN_BANNER);
	} //3
	
	public static void wash_Shulker(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);

		playerIn.setItemInHand(hand, hStack.transmuteCopy(Blocks.SHULKER_BOX, 1));
		CMEvents.soundWaterUse(worldIn, pos);
		playerIn.awardStat(Stats.CLEAN_SHULKER_BOX);
	} //3
	
	public static void washHAKE_MIZUOKE(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.soundWaterUse(worldIn, pos);
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, Items_Wadeco.HAKE.get());
	} //3
	
	
	/** Creative mode Through **/
	public static void mode1Through_takeItem(Player playerIn, InteractionHand hand, Item item) {
		boolean mode = playerIn.getAbilities().instabuild;
		if (mode) { } 
		else { CMEvents.take1Item(playerIn, hand, item); } } //4
	
	public static void toolDamege(int damage, Player playerIn, ItemStack hStack) {
		boolean mode = playerIn.getAbilities().instabuild;
		if (playerIn != null) { 
			hStack.hurtAndBreak(mode? 0 : damage, playerIn, LivingEntity.getSlotForHand(playerIn.getUsedItemHand())); }
	} //19
	
	public static void toolDamegeLE(int damage, LivingEntity entityLiving, ItemStack hStack) {
		Player playerIn = (Player)entityLiving;
		boolean mode = playerIn.getAbilities().instabuild;
		if (playerIn != null) { 
			hStack.hurtAndBreak(mode? 0 : damage, playerIn, LivingEntity.getSlotForHand(playerIn.getUsedItemHand())); }
	} //6 ...Rensouhou-6, Sword_+2
	
	
	/** Get EXP. **/
	public static void addEXP(int i, Level worldIn, BlockPos pos) {
		worldIn.addFreshEntity(new ExperienceOrb(worldIn, pos.getX(), pos.getY(), pos.getZ(), i));
	} //27

	public static void BoneMeal_Particle(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		
		for(int n = 0; n < 15; ++n) {
			double d0 = worldIn.random.nextGaussian() * 0.02D;
			double d1 = worldIn.random.nextGaussian() * 0.02D;
			double d2 = worldIn.random.nextGaussian() * 0.02D;
			worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + worldIn.random.nextFloat(), pos.getY() +worldIn.random.nextFloat(), pos.getZ() + worldIn.random.nextFloat(), d0, d1, d2); }
		worldIn.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
	} //6
	
	public static void Wax_Particle(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		
		for(int n = 0; n < 15; ++n) {
			double d0 = worldIn.random.nextGaussian() * 0.02D;
			double d1 = worldIn.random.nextGaussian() * 0.02D;
			double d2 = worldIn.random.nextGaussian() * 0.02D;
			worldIn.addParticle(ParticleTypes.WAX_ON, pos.getX() + worldIn.random.nextFloat(), pos.getY() +worldIn.random.nextFloat(), pos.getZ() + worldIn.random.nextFloat(), d0, d1, d2); }
		worldIn.playLocalSound(pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F, false);
	} //3
	
	
	/** Error when using item. **/
	public static void soundError(Level worldIn, Player playerIn) {
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.ERROR, SoundSource.PLAYERS, 1.0F, 1.0F);
	} //5
	
	public static void Item_Waterlogged(Level worldIn, Player playerIn) {
		CMEvents.soundError(worldIn, playerIn);
		playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.waterlogged"), true);
	} //4
	
	@SuppressWarnings("deprecation")
	public static void walkOnWater(Level worldIn, Player playerIn) {
		BlockState AIR = Blocks.AIR.defaultBlockState();
		BlockState WAKE1 = Chinjufu_Blocks.WAKE_WATER1.get().defaultBlockState();
		
		int x = (int) playerIn.getX();
		int y = (int) playerIn.getY();
		int z = (int) playerIn.getZ();
		
		/** for start 着水時の落下が多いため範囲を拡大 **/
		for(int i = -1; i <= 1; i++)
		for(int j = -1; j <= 1; j++)

		if (worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j)).getBlock() instanceof LiquidBlock) {
			BlockState underState = worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j));
			FluidState underFluid = worldIn.getFluidState(new BlockPos(x + i, y - 1, z + j));
			if (underFluid.getType().is(FluidTags.WATER) && underState.getValue(LiquidBlock.LEVEL).intValue() == 0) {
				
				BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
				FluidState levelFluid = worldIn.getFluidState(new BlockPos(x + i, y, z + j));
				if (levelState.canBeReplaced()) {
					if (levelFluid.getType().is(FluidTags.WATER) && levelState.getValue(LiquidBlock.LEVEL).intValue() == 0) { }
					else { worldIn.setBlock(new BlockPos(x + i, y, z + j), WAKE1, 3); }
				}
				
				else { } }
			else { }
		}
		/** for end. **/
		
		for(int i = -1; i <= 1; i++)
		for(int j = -1; j <= 1; j++)
		if (worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j)).getBlock() instanceof KelpPlantBlock ||
				worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j)).getBlock() instanceof KelpBlock) {
			
			BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
			FluidState levelFluid = worldIn.getFluidState(new BlockPos(x + i, y, z + j));
			if (levelState.canBeReplaced()) {
				if (levelFluid.getType().is(FluidTags.WATER) && levelState.getValue(LiquidBlock.LEVEL).intValue() == 0) { }
				else { worldIn.setBlock(new BlockPos(x + i, y, z + j), WAKE1, 3); }
			}
			else { } 
		}
		
		for(int i = -1; i <= 1; i++)
		for(int j = -1; j <= 1; j++)
		if (worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j)).getBlock() instanceof SeagrassBlock ||
				worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j)).getBlock() instanceof TallSeagrassBlock) {
			
			BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
			FluidState levelFluid = worldIn.getFluidState(new BlockPos(x + i, y, z + j));
			if (levelState.canBeReplaced()) {
				if (levelFluid.getType().is(FluidTags.WATER) && levelState.getValue(LiquidBlock.LEVEL).intValue() == 0) { }
				else { worldIn.setBlock(new BlockPos(x + i, y, z + j), WAKE1, 3); }
			}
			else { } 
		}
		
		for(int i = -1; i <= 1; i++)
		for(int j = -1; j <= 1; j++)
		if (worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j)).getBlock() instanceof BaseCoralPlantTypeBlock) {
			BlockState underState = worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j));
			if (underState.getValue(BaseCoralPlantTypeBlock.WATERLOGGED)) {
				
				BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
				FluidState levelFluid = worldIn.getFluidState(new BlockPos(x + i, y, z + j));
				if (levelState.canBeReplaced()) {
					if (levelFluid.getType().is(FluidTags.WATER) && levelState.getValue(LiquidBlock.LEVEL).intValue() == 0) { }
					else { worldIn.setBlock(new BlockPos(x + i, y, z + j), WAKE1, 3); }
				}
				
				else { } }
			else { } 
		}
		
		for(int i = -1; i <= 1; i++)
		for(int j = -1; j <= 1; j++)
		if (worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j)).getBlock() instanceof SeaPickleBlock) {
			BlockState underState = worldIn.getBlockState(new BlockPos(x + i, y - 1, z + j));
			if (underState.getValue(BaseCoralPlantTypeBlock.WATERLOGGED)) {
				
				BlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
				FluidState levelFluid = worldIn.getFluidState(new BlockPos(x + i, y, z + j));
				if (levelState.canBeReplaced()) {
					if (levelFluid.getType().is(FluidTags.WATER) && levelState.getValue(LiquidBlock.LEVEL).intValue() == 0) { }
					else { worldIn.setBlock(new BlockPos(x + i, y, z + j), WAKE1, 3); }
				}
				
				else { } }
			else { } 
		}

		/** Player Run Speed 5.6 m/s. Bunny Hop 7.1m/s. **/
		for(int ew = -8; ew <= 8; ew++)
		for(int m = -8; m <= -6; m++)
		for(int h = -1; h <= 0; h++)
		if (worldIn.getBlockState(new BlockPos(x + ew, y + h, z + m)) == WAKE1){
			worldIn.setBlock(new BlockPos(x + ew, y + h, z + m), AIR, 3); }
		
		for(int ew = -8; ew <= 8; ew++)
		for(int p = 6; p <= 8; p++)
		for(int h = -1; h <= 0; h++)
		if (worldIn.getBlockState(new BlockPos(x + ew, y + h, z + p)) == WAKE1){
			worldIn.setBlock(new BlockPos(x + ew, y + h, z + p), AIR, 3); }
		
		for(int m = -8; m <= -6; m++)
		for(int ns = -6; ns <= 6; ns++)
		for(int h = -1; h <= 0; h++)
		if (worldIn.getBlockState(new BlockPos(x + m, y + h, z + ns)) == WAKE1){
			worldIn.setBlock(new BlockPos(x + m, y + h, z + ns), AIR, 3); }
		
		for(int p = 6; p <= 8; p++)
		for(int ns = -6; ns <= 6; ns++)
		for(int h = -1; h <= 0; h++)
		if (worldIn.getBlockState(new BlockPos(x + p, y + h, z + ns)) == WAKE1){
			worldIn.setBlock(new BlockPos(x + p, y + h, z + ns), AIR, 3); }
		
		else { }
	} //14
}
