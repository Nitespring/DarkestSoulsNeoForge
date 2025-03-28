package github.nitespring.darkestsouls.common.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import github.nitespring.darkestsouls.core.util.ArmourUtils;
import github.nitespring.darkestsouls.core.util.CustomItemTags;
import github.nitespring.darkestsouls.core.util.MathUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Gun extends Item implements IAmmoConsumingItem,ILeftClickItem {

    private final float attackDamage;
    private final int useCooldown;
    private final int poiseDamage;
    private final int postureDamage;
    private final int flyingTime;
    private final float flyingPower;
    private final float size;
    private final int ricochet;
    private final int pierce;
    private final int ammoAmount;
    private final int durability;
    private final int enchantability;

    public Gun(float damage, int cooldown, int poise, int posture, float size, float flyingPower, int flyingTime, int ricochet, int pierce, int ammoAmount, int durability, int enchantability, Properties properties) {
        super(properties.stacksTo(1).durability(durability));
        this.attackDamage = damage;
        this.useCooldown = cooldown;
        this.poiseDamage = poise;
        this.postureDamage = posture;
        this.flyingPower = flyingPower;
        this.flyingTime = flyingTime;
        this.size = size;
        this.ricochet = ricochet;
        this.pierce = pierce;
        this.ammoAmount=ammoAmount;
        this.durability=durability;
        this.enchantability=enchantability;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(hand== InteractionHand.OFF_HAND) {
            ItemStack stackIn = player.getItemInHand(hand);
            int ammoAmount = this.getAmmoAmount();
            if (this.hasAmmo(player, ammoAmount) || player.isCreative()) {
                this.shoot(player, level, stackIn);
                if(hand == InteractionHand.MAIN_HAND) {stackIn.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);}
                if(hand == InteractionHand.OFF_HAND) {stackIn.hurtAndBreak(1, player, EquipmentSlot.OFFHAND);}
                if (!player.isCreative()) {
                    this.consumeAmmoApplyLuck(player, ammoAmount, this.getLuck(player, stackIn));
                }
                return InteractionResultHolder.success(stackIn);
            } else {
                return InteractionResultHolder.fail(stackIn);
            }
        }else {
            if(player.getItemInHand(InteractionHand.OFF_HAND)==ItemStack.EMPTY) {
                player.startUsingItem(hand);
            }
            return super.use(level, player, hand);
        }
    }
    public float getAttackDamage(Player playerIn, ItemStack stackIn) {
        int flatEnchantModifier=0;
        int percentEnchantModifier=0;
        if(stackIn.isEnchanted()){
            flatEnchantModifier = stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.FIREPOWER).get());
            percentEnchantModifier = stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.GREATER_FIREPOWER).get());
        }
        float armourModifier = 1 + (float) ArmourUtils.getGunBonus(playerIn) /100;
        return armourModifier * (attackDamage+2*flatEnchantModifier)*(1+0.2f*percentEnchantModifier);

    }
    public int getUseCooldown(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier = stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.GUNSLINGER).get());
        }
        return (int) (useCooldown*(1-0.1*enchantModifier));
    }
    public int getPoiseDamage(Player playerIn, ItemStack stackIn) {
        return poiseDamage;
    }
    public int getPostureDamage(Player playerIn, ItemStack stackIn) {
        return postureDamage;
    }
    public int getFlyingTime(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.SHARPSHOOTER).get());
        }
        return (int) (flyingTime*(1+0.1* enchantModifier));

    }
    public float getBaseSize(){
        return size;
    }
    public float flyingPower(Player playerIn, ItemStack stackIn){
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.SHARPSHOOTER).get());
        }
        return flyingPower+0.025f* enchantModifier;
    }
    public int getRicochet(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.RICOCHET_SHOT).get());
        }
        return ricochet+enchantModifier;
    }
    public int getPierce(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.PIERCING_SHOT).get());
        }
        return pierce+enchantModifier;
    }
    public int getPoison(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.OPHIDIAN_BITE).get());
        }
        return enchantModifier;
    }
    public int getBlood(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.EXPANDING_SHOT).get());
        }
        return enchantModifier;
    }
    public float getLuck(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.MISER_SOUL).get());
        }
        float armourModifier = (float) ArmourUtils.getLuckBonus(playerIn) /100;
        return 0.1f*enchantModifier+armourModifier;
    }
    public boolean isFire(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.FLAMING_SHOT).get());
        }
        return enchantModifier>0;
    }
    public boolean isLightning(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.CHILD_OF_THUNDER).get());
        }
        return enchantModifier>0;
    }
    public int getExplosion(Player playerIn,ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.EXPLODING_SHOT).get());
        }
        return enchantModifier;
    }
    public int getAmmoAmount() {
        return ammoAmount;
    }

    @Override
    public Predicate<ItemStack> getAmmoType() {
        return (p_43015_) -> {
            return p_43015_.is(ItemInit.QUICKSILVER_BULLET.get());
        };
    }

    public void shoot(Player player, Level level, ItemStack stackIn) {
    }
    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        return durability;
    }

    @Override
    public void doLeftClickAction(Player player, ItemStack stackIn) {
        int ammoAmount = this.getAmmoAmount();
        if (!player.getCooldowns().isOnCooldown(this)&&(this.hasAmmo(player, ammoAmount) || player.isCreative())) {
            this.shoot(player, player.level(), stackIn);
            if(player.getItemInHand(InteractionHand.MAIN_HAND)==stackIn) {
                stackIn.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            }
            if (!player.isCreative()) {
                this.consumeAmmoApplyLuck(player, ammoAmount, this.getLuck(player, stackIn));
            }
        }
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.NONE;
    }

    @Override
       public int getUseDuration(ItemStack stackIn, LivingEntity entityIn) {
        return 40000;
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return false;
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {

        return true;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return this.enchantability;
    }
    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }
    /*@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.getSupportedItems() == CustomItemTags.GUN_ENCHANTABLE || enchantment.getSupportedItems() == CustomItemTags.AMMO_CONSUMING || enchantment.getSupportedItems() == ItemTags.DURABILITY_ENCHANTABLE || enchantment.getSupportedItems() == ItemTags.VANISHING_ENCHANTABLE;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return super.isBookEnchantable(stack, book);
    }
    */
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int blood = stack.getEnchantmentLevel(context.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.EXPANDING_SHOT));
        int poison = stack.getEnchantmentLevel(context.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.OPHIDIAN_BITE));
        int ricochet = stack.getEnchantmentLevel(context.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.RICOCHET_SHOT));
        int pierce = stack.getEnchantmentLevel(context.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.PIERCING_SHOT));
        int luck = stack.getEnchantmentLevel(context.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.MISER_SOUL));
        int flatDamage = stack.getEnchantmentLevel(context.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.FIREPOWER));
        int percentDamage = stack.getEnchantmentLevel(context.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.GREATER_FIREPOWER));

        //double damage = (attackDamage+2*flatDamage)*(1+0.2f*percentDamage);
        double damage = MathUtils.round((attackDamage+2*flatDamage)*(1+0.2f*percentDamage), 1);
        tooltip.add(Component.literal("+").append(Component.literal(""+damage)).append(Component.translatable("translation.darkestsouls.damage")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));

        if(blood>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+blood)).append(Component.translatable("translation.darkestsouls.blood")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_RED));
        }
        if(poison>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+poison)).append(Component.translatable("translation.darkestsouls.poison")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
        }
        if(ricochet>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+ricochet)).append(Component.translatable("translation.darkestsouls.ricochet")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(pierce>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+pierce)).append(Component.translatable("translation.darkestsouls.pierce")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(luck>0) {
            tooltip.add(Component.literal("+").append(Component.literal(""+luck*10)).append(Component.literal("%")).append(Component.translatable("translation.darkestsouls.luck")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(stack.isEnchanted()){
            int i=stack.getEnchantmentLevel(context.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.GUNSLINGER));
            if(i>=1) {
                tooltip.add(Component.literal("+").append(Component.literal("" + i * 10)).append(Component.literal("%")).append(Component.translatable("translation.darkestsouls.cooldown")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
            }
        }
        if(this.getAmmoAmount()==1) {
            tooltip.add(Component.translatable("translation.darkestsouls.require_bullet").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }else{
            tooltip.add(Component.translatable("translation.darkestsouls.require").append(Component.literal(" " + this.getAmmoAmount())).append(Component.translatable("translation.darkestsouls.bullets")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        super.appendHoverText(stack, context, tooltip, flag);
    }





}
