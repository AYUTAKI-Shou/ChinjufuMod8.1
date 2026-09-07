package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.crop.Hamaguri;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Kaihori_TT extends IG_Teatime {

	public Kaihori_TT(Item.Properties props) {
		super(props);
	}

	/* FlintAndSteel */
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = worldIn.getBlockState(pos);
		ItemStack hStack = context.getItemInHand();
		Optional<RegistryKey<Biome>> biomeKey = worldIn.getBiomeName(pos);

		BlockState upState = worldIn.getBlockState(pos.above());
		boolean water = (upState.getMaterial() == Material.WATER)? true : false;
		
		if (state.getBlock() == Blocks.SAND || state.getBlock() == Crop_Blocks.KAINASHI) {
			worldIn.playSound(null, pos, SoundEvents.SAND_BREAK, SoundCategory.BLOCKS, 0.8F, 1.2F);
			
			if (state.getBlock() == Blocks.SAND && upState.getMaterial().isReplaceable()) {
				
				if (biomeKey.get().location().getPath().contains("beach") && hasWater(worldIn, pos)) {
					if (worldIn.random.nextInt(4) == 0) {
						worldIn.setBlock(pos.above(), Crop_Blocks.HAMAGURI.defaultBlockState().setValue(Hamaguri.WATERLOGGED, water), 3); } 
				} //Biomes.BEACH && hasWater
				
				else { CMEvents.textNotDig(playerIn); } //!Biomes.BEACH || !hasWater
			} //isReplaceable()
			
			if (state.getBlock() != Blocks.SAND || !upState.getMaterial().isReplaceable()) { CMEvents.textNotDig(playerIn); }
			
			CMEvents.toolDamege(1, playerIn, hStack);
			return ActionResultType.sidedSuccess(worldIn.isClientSide());
		}
		
		else { return ActionResultType.FAIL; }
	}

	private boolean hasWater(World worldIn, BlockPos pos) {
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		return (worldIn.getBlockState(new BlockPos(x - 1, y, z - 1)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x, y, z - 1)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x + 1, y, z - 1)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x - 1, y, z)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x + 1, y, z)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x - 1, y, z + 1)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x, y, z + 1)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x + 1, y, z + 1)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x, y + 1, z)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x, y, z - 2)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x - 2, y, z)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x + 2, y, z)).getMaterial() == Material.WATER) ||
				(worldIn.getBlockState(new BlockPos(x, y, z + 2)).getMaterial() == Material.WATER);
	}
	
	/* Items needed for repair. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.IRON_NUGGET;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_kaihori").withStyle(TextFormatting.GRAY));
	}
}
