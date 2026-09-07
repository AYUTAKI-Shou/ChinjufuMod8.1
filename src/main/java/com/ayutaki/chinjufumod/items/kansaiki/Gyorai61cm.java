package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;

import com.ayutaki.chinjufumod.entity.Gyorai61cmEntity;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class Gyorai61cm extends Item implements ProjectileItem {

	private final static float attackDamage = 11.0F - 1.0F;
	private final static float attackSpeed = -2.4F;

	public Gyorai61cm(Item.Properties props) {
		super(props);
	}

	@SuppressWarnings("deprecation")
	public Item.Properties applySwordProperties(Item.Properties props, float damage, float speed) {
		HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
		return this.applyCommonProperties(props)
			.component(DataComponents.TOOL,
				new Tool(List.of(
						Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
						Tool.Rule.overrideSpeed(holdergetter.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)), 1.0F, 2))
			.attributes(this.createSwordAttributes(damage, speed));
	}
	
	public ItemAttributeModifiers createSwordAttributes(float damage, float speed) {
		return ItemAttributeModifiers.builder()
				.add(Attributes.ATTACK_DAMAGE,
						new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)Gyorai61cm.attackDamage, AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ATTACK_SPEED,
						new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)Gyorai61cm.attackSpeed, AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND)
				.build();
	}//add .attributes on registry
	
	private Item.Properties applyCommonProperties(Item.Properties props) {
		return props;
	}
	
	/* Hold it in your hand. */
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.shrink(1);
		return true;
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player playerIn) {
		return !playerIn.isCreative();
	}
	
	@Override
	public boolean mineBlock(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity attacker) {
		stack.shrink(1);
		return true;
	}
	
	/* RightClick Action */
	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean mode = playerIn.getAbilities().instabuild;

		if (!playerIn.getCooldowns().isOnCooldown(hStack)) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.GYORAI.get(), SoundSource.MASTER, 1.0F, 1.0F);
			
			if (worldIn instanceof ServerLevel server) {
				Gyorai61cmEntity kansaiki = new Gyorai61cmEntity(playerIn, server, hStack);
				int POWER = 8; // Add the speed and distance of the Entity.
				
				boolean suirai = (ShipTypes_CM.typeDestroyer(playerIn) || ShipTypes_CM.typeCruiser(playerIn) || ShipTypes_CM.typeSubmarine(playerIn));
				float basePower = suirai? 0.25F : 0.2F;
				double LEVEL = (playerIn.experienceLevel >= 25)? 3.0D : ((playerIn.experienceLevel >= 19 && playerIn.experienceLevel < 25)? 2.0D : 
					((playerIn.experienceLevel >= 12 && playerIn.experienceLevel < 19)? 1.0D : 0.0D));
	
				if (suirai) { kansaiki.setSuirai(true); }
				var lookup = worldIn.holderLookup(Registries.ENCHANTMENT);
				int j = EnchantmentHelper.getTagEnchantmentLevel(lookup.getOrThrow(Enchantments.SHARPNESS), hStack);
				
				kansaiki.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, basePower * POWER, 1.0F);
				if (j == 0) { kansaiki.setBaseDamage(kansaiki.getBaseDamage() + LEVEL); }
				if (j > 0) { kansaiki.setBaseDamage(kansaiki.getBaseDamage() + LEVEL + (double)j * 0.5D); }
				worldIn.addFreshEntity(kansaiki);
			}
	
			playerIn.awardStat(Stats.ITEM_USED.get(this));
			if (!mode) { hStack.shrink(1); }
			playerIn.getCooldowns().addCooldown(hStack, 15);
		}
		
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_gyorai").withStyle(ChatFormatting.GRAY)); 
		itemTip.add(Component.translatable("tips.item_gyorai2").withStyle(ChatFormatting.GREEN)); }

	@Override
	public Projectile asProjectile(Level worldIn, Position pos, ItemStack stack, Direction direct) {
		return new Gyorai61cmEntity(worldIn, pos.x(), pos.y(), pos.z(), stack);
	}
}
