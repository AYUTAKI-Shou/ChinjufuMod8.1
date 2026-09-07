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
		GROWERS.put(name, this);
	}

	@Nullable
	private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers) {
		if (random.nextFloat() < this.secondaryChance) {
			if (flowers && this.secondaryFlowers.isPresent()) {
				return this.secondaryFlowers.get(); }

			if (this.secondaryTree.isPresent()) {
				return this.secondaryTree.get(); }
		}
		return flowers && this.flowers.isPresent() ? this.flowers.get() : this.tree.orElse(null);
	}

	@Nullable
	private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
		return this.secondaryMegaTree.isPresent() && random.nextFloat() < this.secondaryChance ? this.secondaryMegaTree.get() : this.megaTree.orElse(null);
	}

	public boolean growTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
		ResourceKey<ConfiguredFeature<?, ?>> resourcekey = this.getConfiguredMegaFeature(random);
		if (resourcekey != null) {
			Holder<ConfiguredFeature<?, ?>> holder = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(resourcekey).orElse(null);
			var event = net.neoforged.neoforge.event.EventHooks.fireBlockGrowFeature(level, random, pos, holder);
			holder = event.getFeature();
			if (event.isCanceled()) return false;
			if (holder != null) {
				for (int i = 0; i >= -1; i--) {
					for (int j = 0; j >= -1; j--) {
						if (isTwoByTwoSapling(state, level, pos, i, j)) {
							ConfiguredFeature<?, ?> configuredfeature = holder.value();
							BlockState blockstate = Blocks.AIR.defaultBlockState();
							level.setBlock(pos.offset(i, 0, j), blockstate, 4);
							level.setBlock(pos.offset(i + 1, 0, j), blockstate, 4);
							level.setBlock(pos.offset(i, 0, j + 1), blockstate, 4);
							level.setBlock(pos.offset(i + 1, 0, j + 1), blockstate, 4);
							if (configuredfeature.place(level, chunkGenerator, random, pos.offset(i, 0, j))) {
								return true; }

							level.setBlock(pos.offset(i, 0, j), state, 4);
							level.setBlock(pos.offset(i + 1, 0, j), state, 4);
							level.setBlock(pos.offset(i, 0, j + 1), state, 4);
							level.setBlock(pos.offset(i + 1, 0, j + 1), state, 4);
							return false; }
					}
				}
			}
		}

		ResourceKey<ConfiguredFeature<?, ?>> resourcekey1 = this.getConfiguredFeature(random, this.hasFlowers(level, pos));
		if (resourcekey1 == null) {
			return false; }
		
		else {
			Holder<ConfiguredFeature<?, ?>> holder1 = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(resourcekey1).orElse(null);
			var event = net.neoforged.neoforge.event.EventHooks.fireBlockGrowFeature(level, random, pos, holder1);
			holder1 = event.getFeature();
			
			if (event.isCanceled()) return false;
			if (holder1 == null) {
				return false; }
			
			else {
				ConfiguredFeature<?, ?> configuredfeature1 = holder1.value();
				BlockState blockstate1 = level.getFluidState(pos).createLegacyBlock();
				level.setBlock(pos, blockstate1, 4);
				if (configuredfeature1.place(level, chunkGenerator, random, pos)) {
					if (level.getBlockState(pos) == blockstate1) {
						level.sendBlockUpdated(pos, state, blockstate1, 2); }

					return true; }
				
				else {
					level.setBlock(pos, state, 4);
					return false; }
			}
		}
	}

	private static boolean isTwoByTwoSapling(BlockState state, BlockGetter level, BlockPos pos, int xOffset, int yOffset) {
		Block block = state.getBlock();
		return level.getBlockState(pos.offset(xOffset, 0, yOffset)).is(block)
			&& level.getBlockState(pos.offset(xOffset + 1, 0, yOffset)).is(block)
			&& level.getBlockState(pos.offset(xOffset, 0, yOffset + 1)).is(block)
			&& level.getBlockState(pos.offset(xOffset + 1, 0, yOffset + 1)).is(block);
	}

	private boolean hasFlowers(LevelAccessor level, BlockPos pos) {
		for (BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(2).west(2), pos.above().south(2).east(2))) {
			if (level.getBlockState(blockpos).is(BlockTags.FLOWERS)) {
				return true; }
		}
		return false;
	}
}
