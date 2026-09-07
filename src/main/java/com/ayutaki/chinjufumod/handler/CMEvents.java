package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class CMEvents {
	/* Sound Only */
	public static void soundAmado(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.AMADO_CANCEL, SoundCategory.BLOCKS, 1.0F, 1.0F); } //11, Amado+1, Tobukuro+6
	
	public static void soundAmadoWin(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.AMADO_CANCEL, SoundCategory.BLOCKS, 0.8F, 1.1F); } //4, Tobukuro+1
	
	/*public static void soundBubble(World worldIn, BlockPos pos) {
	worldIn.playSound(null, pos, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 2.0F, 0.8F); }*/
	
	public static void soundClothBreak(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_CLOTH_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F); } //2, destroyDrop_ClothB-1
	
	public static void soundClothPlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); } //9, ReportBox+1, ItemBlock+1
	
	public static void soundCurtain(World worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.CURTAIN, SoundCategory.BLOCKS, volume, pitch); } //5, Large+1, Tall-1
	
	public static void soundDrink(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.GOKU, SoundCategory.PLAYERS, 1.0F, 1.0F); } //5, Cider-1, Mead-1
	
	public static void soundEat(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.PAKU, SoundCategory.PLAYERS, 1.0F, 1.0F); } //26
	
	public static void soundFlint(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, 0.8F); } //7
	
	public static void soundFireExting(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.2F, 2.0F); } //6, WATERLOGGED-5, Cooktop-1, LitIrori-1
	
	/*public static void soundFish(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.NEUTRAL, 0.3F, 1.0F); }*/
	
	public static void soundFusumaL(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.FUSUMA, SoundCategory.BLOCKS, 1.0F, 1.0F); } //21, Tobukuro+8
	
	public static void soundFusumaS(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.FUSUMA_SHORT, SoundCategory.BLOCKS, 1.0F, 1.0F); } //16, Tobukuro+1
	
	
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
		worldIn.playSound(null, pos, SoundEvents_CM.OPEN_OVEN, SoundCategory.BLOCKS, 0.8F, 1.0F); } //9, OvenB+4
	
	public static void soundPage(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.PAGE_TURN, SoundCategory.BLOCKS, 0.7F, 1.0F); } //3
	
	
	public static void soundSAKEBottleFill(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 0.7F, 0.8F); } //4
	
	public static void soundSHOUYU(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.SHOUYU, SoundCategory.BLOCKS, 1.0F, 1.0F); } //5
	
	public static void soundSitChair(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.SIT_CHAIR, SoundCategory.BLOCKS, 1.0F, 1.0F); } //5
	
	public static void soundSnowBreak(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F); } //7, WATERLOGGED-6, dropN_ROTTEN-2

	public static void soundSnowTake(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.BLOCKS, 0.8F, 1.2F); } //4
		
	public static void soundSnowPlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 0.8F, 1.2F); } //12, LitIrori-3, KuriIga-1, ItemBlock+1
	
	public static void soundStoneButton_Off(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.8F, 0.65F); } //10, Shishi+2
	
	public static void soundStoneButton_On(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0F, 0.8F); } //8, Shishi+1

	public static void soundStonePlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); } //15, DirtWall+1, Bricks_CM+1, ItemBlock+1
	
	public static void soundTouchBlock(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.TOUCH_BLOCK, SoundCategory.BLOCKS, 1.0F, 0.75F); } //99
	
	
	public static void soundWaterUse(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WATER_SPLASH, SoundCategory.PLAYERS, 0.5F, 1.2F); } //10, Chouzu+3, Shulker-1
	
	public static void soundWin_Open(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_OPEN, SoundCategory.BLOCKS, 0.8F, 1.1F); } //18
	
	public static void soundWin_Close(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_CLOSE, SoundCategory.BLOCKS, 0.8F, 1.1F); } //18
	
	public static void soundWin_OpenL(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F); } //2
	
	public static void soundWin_CloseL(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents_CM.WINDOW_CLOSE, SoundCategory.BLOCKS, 1.0F, 1.0F); } //2
	
	public static void soundWoodPlace(World worldIn, BlockPos pos) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); } //32, EndaiSub+2, ItemBlock+1
	
	
	public static void soundBucketEmpty(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F); }
	} //4, MIZUOKEfull-1
	
	public static void soundBucketFill(World worldIn, BlockPos pos, EntityPlayer playerIn, float volume, float pitch) {
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, volume, pitch); }
	} //6
	
	public static void wadaikoTop(World worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.WADAIKO_TOP, SoundCategory.BLOCKS, volume, pitch); } //17
	
	public static void wadaikoSide(World worldIn, BlockPos pos, float volume, float pitch) {
		worldIn.playSound(null, pos, SoundEvents_CM.WADAIKO_SIDE, SoundCategory.BLOCKS, volume, pitch); } //17
	
	
	/* Sound & Message */
	public static void textEarlyCollect(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.earlycollect.name", new Object[0]), true); } //109
	
	public static void textEarlyUse(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.earlyuse.name", new Object[0]), true); } //6
	
	public static void textFullItem(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.fullitem.name", new Object[0]), true); } //137
	
	public static void textNoPlace(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.noplace.name", new Object[0]), true); } //16
	
	public static void textNotEnough_Items(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.notenough_items.name", new Object[0]), true); } //23, Report+12
	
	public static void textNotHave(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.nothave.name", new Object[0]), true); } //268
	
	public static void textNotSneak(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.notsneak.name", new Object[0]), true); } //9
	
	public static void textIsBlocked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.blocked.name", new Object[0]), true); } //93, Report+24, Oven+4

	public static void textIsEmpty(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.empty.name", new Object[0]), true); } //43
	
	public static void textIsSleeping(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.sleeping.name", new Object[0]), true); } //3
	
	/*public static void textIsWaterlogged(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.waterlogged.name", new Object[0]), true); }*/
	
	public static void textRequestHeat(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.heat.name", new Object[0]), true); } //9
	
	public static void textRequestCool(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.cool.name", new Object[0]), true); } //3
	
	public static void textNotEnough_EXP(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.notenough_exp.name", new Object[0]), true); } //3, Ami-2
	
	public static void textNotDig(EntityPlayer playerIn) {
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.notdig.name", new Object[0]), true); } //3

	public static void textNeedClean(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.need_clean.name", new Object[0]), true); } //3

	public static void textIsWaxed(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.soundTouchBlock(worldIn, pos);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.is_waxed.name", new Object[0]), true); } //3

	
	/** Drop Item and Sound**/
	
	
	/** Sound & add Item **/
	public static void emptyTake_1Item(World worldIn, BlockPos pos, EntityPlayer playerIn, Item item, int meta) {
		CMEvents.soundItemPick(worldIn, pos);
		playerIn.inventory.addItemStackToInventory(new ItemStack(item, 1, meta)); } //42 Alumi+3, Kit +10
	
	public static void emptyTake_NItem(World worldIn, BlockPos pos, EntityPlayer playerIn, Item item, int amount, int meta) {
		CMEvents.soundItemPick(worldIn, pos);
		playerIn.inventory.addItemStackToInventory(new ItemStack(item, amount, meta)); } //5
	
	public static void emptyTake1_SnowB(World worldIn, BlockPos pos, EntityPlayer playerIn, Item item, int meta) {
		CMEvents.soundSnowTake(worldIn, pos);
		CMEvents.emptyTake_1Item(worldIn, pos, playerIn, item, meta); } //24, Chauke -2, SconeSet+1, PizzaCTS+1
	
	public static void emptyTakeN_SnowB(World worldIn, BlockPos pos, EntityPlayer playerIn, Item item, int amount, int meta) {
		CMEvents.soundSnowTake(worldIn, pos);
		CMEvents.emptyTake_NItem(worldIn, pos, playerIn, item, amount, meta); } //24, InagiTop +1, ShioToufu +2, Taru+2, Pantry_+1
	
	public static void emptyTakeN_ClothB(World worldIn, BlockPos pos, EntityPlayer playerIn, Item item, int amount, int meta) {
		CMEvents.soundClothBreak(worldIn, pos);
		CMEvents.emptyTake_NItem(worldIn, pos, playerIn, item, amount, meta); } //6

	public static void take_KINOKO(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Item.getItemFromBlock(Blocks.BROWN_MUSHROOM), 0); } //7
	
	public static void take_SAKANA(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.KUSHI_SAKANA_C, 0); } //109
	

	/** Consume Item **/
	public static void consume1_Stack(ItemStack hStack, EntityPlayer playerIn) {
		boolean mode = playerIn.capabilities.isCreativeMode;
		if (mode) { }
		else { hStack.shrink(1); } } //4, MatchCampfire -1
	
	public static void consumeN_Hand(int amount, EntityPlayer playerIn, EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean mode = playerIn.capabilities.isCreativeMode;
		if (mode) { }
		else { hStack.shrink(amount); } }// 48, BaseReport+1, Taru+2, Events+6
	
	public static void washHAKE_Cauldron(World worldIn, BlockPos pos, ItemStack hStack, EntityPlayer playerIn) {
		worldIn.playSound(playerIn, pos, SoundEvents_CM.WATER_SPLASH, SoundCategory.BLOCKS, 0.5F, 1.2F);
		
		ItemStack take = new ItemStack(Items_Wadeco.HAKE, 1, 0);
		if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
		CMEvents.consume1_Stack(hStack, playerIn); } //17
	
	public static void consume1_seBottle(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		if (playerIn != null) { worldIn.playSound(playerIn, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 0.7F, 1.0F); }
	} //4
	
	public static void consume1_seCheese(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.2F);
	} //7
	
	public static void consume1_seCloth(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundClothPlace(worldIn, pos); } //11, Ami-2, Cafe+1
	
	public static void consume1_seDish(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.BLOCKS, 1.0F, 3.0F);
	} //26, Kit_+6
	
	public static void consume1_seFlint(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, 0.8F);
	} //7

	public static void consume1_seSnowB(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundSnowTake(worldIn, pos); } //4, Sushi-6
	
	public static void consume1_seSnowP(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundSnowPlace(worldIn, pos); } //140, LitIrori+5, SnowManBot-1
	
	public static void consume1_seSplash(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWaterUse(worldIn, pos); } //10
	
	public static void consume1_seStoneP(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundStonePlace(worldIn, pos); } //20, Kamoi_DIRTWALL+9
	
	public static void consume1_seWoodP(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWoodPlace(worldIn, pos); } //26, Office-1, Tatami+1, Desk-2, Unit-2, Endai+2
	
	public static void consume1_seWoodDish(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 3.0F);
	} //6, TanaShikkiA+1
	
	public static void consume1_seWrite(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents_CM.WRITE_REPORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
	} //3
	
	public static void consumeN_seSnowP(int amount, World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(amount, playerIn, hand);
		CMEvents.soundSnowPlace(worldIn, pos); } //21, Taru_KELP-1, Pantry_+8
	

	/** Take Item **/
	public static void takeN_Item(int amount, EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		ItemStack take = new ItemStack(item, amount, meta);
		if (hStack.isEmpty()) { playerIn.inventory.addItemStackToInventory(take); }
		else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); } 
	}
	
	public static void take1Item(EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		CMEvents.takeN_Item(1, playerIn, hand, item, meta);
	} //23, Sushi-6
	
	public static void takeSAKEBottle_seFill(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		CMEvents.soundItemPick(worldIn, pos);
		CMEvents.soundSAKEBottleFill(worldIn, pos);
		CMEvents.take1Item(playerIn, hand, item, meta); } //6, TaruY+1
	
	
	/** Change Item in Hand **/
	public static void changeBottle_seBottle(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		CMEvents.consume1_seBottle(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item, meta); } //7, Taru+1
	
	public static void changeBottle_seSplash(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item, meta); } //3
	
	public static void changeBowl_seBucket(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.2F);
		CMEvents.take1Item(playerIn, hand, item, meta); } //7, RSoup-2, Taru+1,
	
	public static void changeBucket_seBucket(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.0F);
		CMEvents.take1Item(playerIn, hand, item, meta); } //8, Wake+1, Sink+1
	
	public static void changeDish_seSnowB(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, item, meta); } //35, KURI_BOIL+1, AZUKI+1
	
	public static void changeDish_seTea(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents_CM.TEA, SoundCategory.PLAYERS, 1.0F, 1.0F);
		CMEvents.take1Item(playerIn, hand, item, meta); } //4

	
	/** Change Specific Item in Hand **/
	public static void Bottle_toWaterBottle(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		CMEvents.consume1_seBottle(worldIn, pos, playerIn, hand);
		ItemStack take = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER);
		if (hStack.isEmpty()) { playerIn.setHeldItem(hand, take); }
		else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); } } //5
	
	public static void mode1Through_Consume(EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean mode = playerIn.capabilities.isCreativeMode;
		
		if (mode) { } 
		else { //!mode
			hStack.shrink(1);
			CMEvents.take1Item(playerIn, hand, item, meta); } } //11, MizuokeFullItem_Nether+1
	
	public static void Bucket_toEmpty(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items.BUCKET, 0);
		CMEvents.soundBucketEmpty(worldIn, pos, playerIn); } //8, Youshoku-3, Chouzu +3
	
	public static void MIZUOKE_toEmpty(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_Teatime.MIZUOKE, 0);
		CMEvents.soundBucketEmpty(worldIn, pos, playerIn); } //11, Chouzu+3
	
	
	public static void Soysauce_to2(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_24, 0);
		CMEvents.soundSHOUYU(worldIn, pos); } //2, Geta+Sara
	
	public static void Soysauce_to3(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_34, 0);
		CMEvents.soundSHOUYU(worldIn, pos); } //2, Geta+Sara
	
	public static void Soysauce_to4(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items_NoTab.SHOUYU_bot_44, 0);
		CMEvents.soundSHOUYU(worldIn, pos); } //2, Geta+Sara
	
	public static void Soysauce_toBottle(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.mode1Through_Consume(playerIn, hand, Items.GLASS_BOTTLE, 0);
		CMEvents.soundSHOUYU(worldIn, pos); } //2, Geta+Sara
	
	
	/** Wash with MIZUOKE. **/
	public static void wash_Banner(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		ItemStack copy = hStack.copy();
		copy.setCount(1);
		TileEntityBanner.removeBannerData(copy);
		playerIn.addStat(StatList.BANNER_CLEANED);
		
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWaterUse(worldIn, pos);
		
		if (hStack.isEmpty()) { playerIn.setHeldItem(hand, copy); }
		else if (!playerIn.inventory.addItemStackToInventory(copy)) { playerIn.dropItem(copy, false); }
		else if (playerIn instanceof EntityPlayerMP) {
			((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer); }
	} //3
	
	public static void washHAKE_MIZUOKE(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.soundWaterUse(worldIn, pos);
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.take1Item(playerIn, hand, Items_Wadeco.HAKE, 0);
	} //3
	
	
	/** Creative mode Through **/
	public static void mode1Through_takeItem(EntityPlayer playerIn, EnumHand hand, Item item, int meta) {
		boolean mode = playerIn.capabilities.isCreativeMode;
		if (mode) { } 
		else { CMEvents.take1Item(playerIn, hand, item, meta); } } //9, SnowMan+5
	
	public static void toolDamege(int damage, EntityPlayer playerIn, ItemStack hStack) {
		boolean mode = playerIn.capabilities.isCreativeMode;
		if (playerIn != null) {
			hStack.damageItem(mode? 0 : damage, playerIn); }
	} //33, Shield-1, HAKE+1, Board+4
	
	
	/** Get EXP directly. **/
	public static void addEXP(int i, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		playerIn.addExperience(i);
		worldIn.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.75F);
	}
	
	public static void BoneMeal_Particle(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		
		for(int n = 0; n < 15; ++n) {
			double d0 = worldIn.rand.nextGaussian() * 0.02D;
			double d1 = worldIn.rand.nextGaussian() * 0.02D;
			double d2 = worldIn.rand.nextGaussian() * 0.02D;
			worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + worldIn.rand.nextFloat(), pos.getY() +worldIn.rand.nextFloat(), pos.getZ() + worldIn.rand.nextFloat(), d0, d1, d2); }
	} //8, Grape+2
	
	public static void Wax_ParticleSound(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		
		for(int n = 0; n < 15; ++n) {
			double d0 = worldIn.rand.nextGaussian() * 0.02D;
			double d1 = worldIn.rand.nextGaussian() * 0.02D;
			double d2 = worldIn.rand.nextGaussian() * 0.02D;
			worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + worldIn.rand.nextFloat(), pos.getY() +worldIn.rand.nextFloat(), pos.getZ() + worldIn.rand.nextFloat(), d0, d1, d2); }
		worldIn.playSound(null, pos, SoundEvents_CM.PAINT, SoundCategory.BLOCKS, 1.0F, 1.0F);
	} //3
	
	
	/** Error when using item. **/
	public static void soundError(World worldIn, EntityPlayer playerIn) {
		worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.ERROR, SoundCategory.PLAYERS, 1.0F, 1.0F);
	} //5
	
	public static void Item_Waterlogged(World worldIn, EntityPlayer playerIn) {
		CMEvents.soundError(worldIn, playerIn);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.waterlogged.name", new Object[0]), true);
	} //4

	public static void walkOnWater(World worldIn, EntityPlayer playerIn) {
		IBlockState AIR = Blocks.AIR.getDefaultState();
		IBlockState WAKE1 = Chinjufu_Blocks.WAKE_WATER1.getDefaultState();
		
		int x = (int) playerIn.posX;
		int y = (int) playerIn.posY;
		int z = (int) playerIn.posZ;

		/** for start **/
		for(int i = -1; i <= 1; i++)
		for(int j = -1; j <= 1; j++)

		if (worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j)).getBlock() instanceof BlockLiquid){
			IBlockState underState = worldIn.getBlockState(new BlockPos(x + i, y - 1.0D, z + j));
			if (underState.getMaterial() == Material.WATER && underState.getValue(BlockLiquid.LEVEL).intValue() == 0){
				
				IBlockState levelState = worldIn.getBlockState(new BlockPos(x + i, y, z + j));
				if (levelState.getMaterial().isReplaceable()) {
					if (levelState.getMaterial() == Material.WATER && levelState.getValue(BlockLiquid.LEVEL).intValue() == 0){ }
					else { worldIn.setBlockState(new BlockPos(x + i, y, z + j), WAKE1, 3); }
				}
				
				else { } }
			else { } 
		}
		/** for end. **/
		
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
	
	/** for ItemBlock **/
	public static void ItemBlock_Cloth(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundClothPlace(worldIn, pos); }
	
	public static void ItemBlock_Metal(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);	
		worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); }
	
	public static void ItemBlock_Grass(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); }
	
	public static void ItemBlock_Snow(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundSnowPlace(worldIn, pos); }
	
	public static void ItemBlock_Stone(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundStonePlace(worldIn, pos); }
	
	public static void ItemBlock_Wood(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundWoodPlace(worldIn, pos); } 
}
