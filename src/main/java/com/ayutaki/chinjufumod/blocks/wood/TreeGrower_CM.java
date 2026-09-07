package com.ayutaki.chinjufumod.blocks.wood;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.world.features.TreeFeatures_CM;
import com.mojang.serialization.Codec;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

/* block.grower.TreeGrower */
public class TreeGrower_CM {
	private static final Map<String, TreeGrower_CM> GROWERS = new Object2ObjectArrayMap<>();
	public static final Codec<TreeGrower_CM> CODEC = Codec.stringResolver(function -> function.name, GROWERS::get);
	
	public static final TreeGrower_CM SAKURA = new TreeGrower_CM("grower_sakura", 0.1F,
		Optional.empty(), Optional.empty(),
		Optional.of(TreeFeatures_CM.SAKURA_CONFIG), Optional.of(TreeFeatures_CM.SAKURA_FANCY_CONFIG),
		Optional.empty(), Optional.empty());

	public static final TreeGrower_CM KAEDE = new TreeGrower_CM("grower_kaede", 0.05F,
			Optional.empty(), Optional.empty(),
			Optional.of(TreeFeatures_CM.KAEDE_CONFIG), Optional.of(TreeFeatures_CM.KAEDE_FANCY_CONFIG),
			Optional.empty(), Optional.empty());
	
	public static final TreeGrower_CM ICHOH = new TreeGrower_CM("grower_ichoh", 0.1F,
			Optional.empty(), Optional.empty(),
			Optional.of(TreeFeatures_CM.ICHOH_CONFIG), Optional.of(TreeFeatures_CM.ICHOH_FANCY_CONFIG),
			Optional.empty(), Optional.empty());
	
	public static final TreeGrower_CM OAKKARE = new TreeGrower_CM("grower_autumnoak", 0.1F,
			Optional.empty(), Optional.empty(),
			Optional.of(TreeFeatures_CM.OAKKARE_CONFIG), Optional.of(TreeFeatures_CM.OAKKARE_FANCY_CONFIG),
			Optional.empty(), Optional.empty()); 
	
	private final String name;
	private final float secondaryChance;
	private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree;
	private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree;
	private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree;
	private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree;
	private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers;
	private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers;

	public TreeGrower_CM(String id, Optional<ResourceKey<ConfiguredFeature<?, ?>>> mega, 
			Optional<ResourceKey<ConfiguredFeature<?, ?>>> normal, Optional<ResourceKey<ConfiguredFeature<?, ?>>> grass) {
		this(id, 0.0F, mega, Optional.empty(), normal, Optional.empty(), grass, Optional.empty());
	}

	public TreeGrower_CM(String id, float chance, 
			Optional<ResourceKey<ConfiguredFeature<?, ?>>> mega, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaFancy,
			Optional<ResourceKey<ConfiguredFeature<?, ?>>> normal, Optional<ResourceKey<ConfiguredFeature<?, ?>>> fancy,
			Optional<ResourceKey<ConfiguredFeature<?, ?>>> grass, Optional<ResourceKey<ConfiguredFeature<?, ?>>> grassFancy) {
		this.name = id;
		this.secondaryChance = chance;
		this.megaTree = mega;
		this.secondaryMegaTree = megaFancy;
		this.tree = normal;
		this.secondaryTree = fancy;
		this.flowers = grass;
		this.secondaryFlowers = grassFancy;
		GROWERS.put(id, this);
	}

	@Nullable
	private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource rand, boolean flag) {
		if (rand.nextFloat() < this.secondaryChance) {
			if (flag && this.secondaryFlowers.isPresent()) { return this.secondaryFlowers.get(); }

			if (this.secondaryTree.isPresent()) { return this.secondaryTree.get(); }
		}

		return flag && this.flowers.isPresent() ? this.flowers.get() : this.tree.orElse(null);
	}

	@Nullable
	private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource rand) {
		return this.secondaryMegaTree.isPresent() && rand.nextFloat() < this.secondaryChance ? this.secondaryMegaTree.get() : this.megaTree.orElse(null);
	}

	public boolean growTree(ServerLevel worldIn, ChunkGenerator gen, BlockPos pos, BlockState state, RandomSource rand) {
		ResourceKey<ConfiguredFeature<?, ?>> resourcekey = this.getConfiguredMegaFeature(rand);
		if (resourcekey != null) {
			Holder<ConfiguredFeature<?, ?>> holder = worldIn.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(resourcekey).orElse(null);
			var event = net.minecraftforge.event.ForgeEventFactory.blockGrowFeature(worldIn, rand, pos, holder);
			holder = event.getFeature();
			if (event.getResult() == net.minecraftforge.eventbus.api.Event.Result.DENY) return false;
			
			if (holder != null) {
				for (int i = 0; i >= -1; i--) {
					for (int j = 0; j >= -1; j--) {
						if (isTwoByTwoSapling(state, worldIn, pos, i, j)) {
							ConfiguredFeature<?, ?> configuredfeature = holder.value();
							BlockState blockstate = Blocks.AIR.defaultBlockState();
							worldIn.setBlock(pos.offset(i, 0, j), blockstate, 4);
							worldIn.setBlock(pos.offset(i + 1, 0, j), blockstate, 4);
							worldIn.setBlock(pos.offset(i, 0, j + 1), blockstate, 4);
							worldIn.setBlock(pos.offset(i + 1, 0, j + 1), blockstate, 4);
							if (configuredfeature.place(worldIn, gen, rand, pos.offset(i, 0, j))) { return true; }

							worldIn.setBlock(pos.offset(i, 0, j), state, 4);
							worldIn.setBlock(pos.offset(i + 1, 0, j), state, 4);
							worldIn.setBlock(pos.offset(i, 0, j + 1), state, 4);
							worldIn.setBlock(pos.offset(i + 1, 0, j + 1), state, 4);
							return false; }
					}
				}
			}
		}

		ResourceKey<ConfiguredFeature<?, ?>> resourcekey1 = this.getConfiguredFeature(rand, this.hasFlowers(worldIn, pos));
		if (resourcekey1 == null) { return false; } 
		
		else {
			Holder<ConfiguredFeature<?, ?>> holder1 = worldIn.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(resourcekey1).orElse(null);
			if (holder1 == null) { return false; } 

			else {
				ConfiguredFeature<?, ?> configuredfeature1 = holder1.value();
				BlockState blockstate1 = worldIn.getFluidState(pos).createLegacyBlock();
				worldIn.setBlock(pos, blockstate1, 4);

				if (configuredfeature1.place(worldIn, gen, rand, pos)) {
					if (worldIn.getBlockState(pos) == blockstate1) { worldIn.sendBlockUpdated(pos, state, blockstate1, 2); }
					return true;
				}
				
				else { worldIn.setBlock(pos, state, 4); return false; }
			}
		}
	}

	private static boolean isTwoByTwoSapling(BlockState state, BlockGetter worldIn, BlockPos pos, int xIn, int zIn) {
		Block block = state.getBlock();
		return worldIn.getBlockState(pos.offset(xIn, 0, zIn)).is(block)
			&& worldIn.getBlockState(pos.offset(xIn + 1, 0, zIn)).is(block)
			&& worldIn.getBlockState(pos.offset(xIn, 0, zIn + 1)).is(block)
			&& worldIn.getBlockState(pos.offset(xIn + 1, 0, zIn + 1)).is(block);
	}

	private boolean hasFlowers(LevelAccessor worldIn, BlockPos pos) {
		for (BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(2).west(2), pos.above().south(2).east(2))) {
			if (worldIn.getBlockState(blockpos).is(BlockTags.FLOWERS)) { return true; }
		}
		return false;
	}
}
