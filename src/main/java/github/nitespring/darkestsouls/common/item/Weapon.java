package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;


import java.util.List;

public class Weapon extends Item implements ILeftClickItem, ICustomSweepAttackItem{
    private float attackDamage;
    private float attackSpeed;
    private float attackKnockback;
    private float movementSpeed;
    private int durability;
    private int maxTargets;

    public int poisedmg;
    public int posturedmg;
    private int bloodAttack;
    private int poisonAttack;
    private int rotAttack;
    private int darkAttack;
    private int frostAttack;
    private int fire;
    private int holy;
    private final int enchantability;
    private int serrated;
    private final Tier tier;
    //private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final ResourceLocation BASE_ATTACK_KNOCKBACK_ID = ResourceLocation.withDefaultNamespace("base_attack_knockback");
    protected static final ResourceLocation BASE_MOVEMENT_SPEED_ID = ResourceLocation.withDefaultNamespace("base_attack_speed");
    protected static final ResourceLocation BASE_ATTACK_REACH_ID = ResourceLocation.withDefaultNamespace("base_attack_reach");

    public Weapon(Tier tier, float attack, float speed,float reach, float knockback, int poise, int posture, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(properties.stacksTo(1).durability(durability).attributes(
                Weapon.createAttributes(
                        attack-1.0f, reach-3.0, speed-4.0f, knockback, movementSpeed-0.1f)));
        this.tier=tier;
        this.attackDamage=attack-1.0f;
        this.attackSpeed=speed-4.0f;
        this.attackKnockback=knockback;
        this.poisedmg=poise;
        this.posturedmg=posture;
        this.durability=durability;
        this.movementSpeed=movementSpeed-0.1f;
        this.enchantability=enchantability;
        this.maxTargets=maxTargets;
        this.bloodAttack=blood;
        this.poisonAttack=poison;
        this.frostAttack=frost;
        this.rotAttack=rot;
        this.darkAttack =death;
        this.fire=fire;
        this.holy=holy;
        this.serrated=serrated;
    }

    public static ItemAttributeModifiers createAttributes(double attackDamage, double reach, double attackSpeed, double attackKnockback, double movementSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                attackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.HAND
                ).add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.HAND
                ).add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(
                                BASE_ATTACK_REACH_ID,
                                reach,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.HAND
                ).add(Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(BASE_ATTACK_KNOCKBACK_ID,
                                attackKnockback,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.HAND
                ).add(
                        Attributes.MOVEMENT_SPEED,
                        new AttributeModifier(BASE_MOVEMENT_SPEED_ID,
                                movementSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.HAND
                ).build();
    }

    public float getAttackDamage() {return this.attackDamage;}
    public float getAttackDamage(Player playerIn, ItemStack stackIn) {

        float enchantmentsModifier = 0;
        //playerIn.getAttackStrengthScale();
        double strengthModifier = playerIn.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
        /*
        if(playerIn.hasEffect(MobEffects.DAMAGE_BOOST)){effectModifier = effectModifier + (1 + playerIn.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier())*0.2f;}
        if(playerIn.hasEffect(MobEffects.WEAKNESS)){effectModifier = effectModifier - (1 + playerIn.getEffect(MobEffects.WEAKNESS).getAmplifier())*0.2f;}
        */
        if(stackIn.isEnchanted()) {
            //if(stackIn.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
            if(stackIn.isEnchanted()) {
                enchantmentsModifier = enchantmentsModifier + 0.5f * (1 + stackIn.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.SHARPNESS).get()));
            }
        }
        float f = (float) (strengthModifier + enchantmentsModifier);
        //System.out.println(strengthModifier);
        //return f+this.getAttackDamage();
        //System.out.println(f);
        return f;
    }

    public float getSweepAttackDamage(Player playerIn, ItemStack stackIn){
        return 1.0F + (float)playerIn.getAttributeValue(Attributes.SWEEPING_DAMAGE_RATIO) * getAttackDamage(playerIn,stackIn);
    }
    public int getSweepPoiseDamage(Player playerIn, ItemStack stackIn){
        return 1 + Math.round((float)playerIn.getAttributeValue(Attributes.SWEEPING_DAMAGE_RATIO)*0.75f*getPoiseDamage(playerIn,stackIn));
    }
    public int getSweepPostureDamage(Player playerIn, ItemStack stackIn){
        return 1 + Math.round((float)playerIn.getAttributeValue(Attributes.SWEEPING_DAMAGE_RATIO)*0.5f*getPostureDamage(playerIn,stackIn));
    }
    public float getAttackSpeed() {return this.attackSpeed;}
    public float getAttackKnockback() {return this.attackKnockback;}
    public float getMovementSpeed() {return this.movementSpeed;}
    public int getMaxTargets() {return this.maxTargets;}
    public int getMaxTargets(Player playerIn, ItemStack item ) {
        if(this.getMaxTargets()>=1&&item.isEnchanted()){
            int i = this.getMaxTargets() + item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.SWEEPING_EDGE).get());
            return i;
        }else{
            return this.getMaxTargets();
        }
    }

    public int getPoiseDamage(Player playerIn, ItemStack item) {
        if (playerIn.hasEffect(MobEffects.DAMAGE_BOOST)) {
            return this.poisedmg + (int) 2 * playerIn.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier();
        }else{
            return this.poisedmg;
        }
    }
    public int getPostureDamage(Player playerIn, ItemStack item) {
        if (playerIn.hasEffect(MobEffects.DAMAGE_BOOST)) {
            return this.posturedmg + (int) 3.0f * playerIn.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier();
        }else{
            return this.posturedmg;
        }
    }
    public int getDurability() {return this.durability;}
    public int getBloodAttack(Player playerIn, ItemStack item){
        if(item.isEnchanted()&&item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.BLOODBLADE).get())>=1) {
            //if(item.getComponents().has(DataComponents.ENCHANTMENTS))
                return bloodAttack + 1 + 2 * item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.BLOODBLADE).get());
        }else{

            return bloodAttack;
        }
    }
    public int getPoisonAttack(Player playerIn,ItemStack item){return poisonAttack+item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.POISONED_BLADE).get());}
    public int getFrostAttack(Player playerIn,ItemStack item){return frostAttack+item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.FROST_BLADE).get());}
    public int getRotAttack(Player playerIn,ItemStack item){return rotAttack+item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.ROTTEN_BLADE).get());}
    public int getToxicAttack(Player playerIn,ItemStack item){return item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.TOXIC_BLADE).get());}
    public int getWitherAttack(Player playerIn,ItemStack item){return item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.WITHERED_BLADE).get());}
    public int getDarkAttack(Player playerIn,ItemStack item){return darkAttack;}
    public int getFireAttack(Player playerIn,ItemStack item){return fire + item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.FIRE_ASPECT).get());}
    public int getSmiteAttack(Player playerIn,ItemStack item){return holy + item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.SMITE).get());}
    public int getBaneOfArthropodsAttack(Player playerIn,ItemStack item){return item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.BANE_OF_ARTHROPODS).get());}
    public int getBeastHunterAttack(Player playerIn,ItemStack item){return item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.BEAST_HUNTER).get());}
    public int getHolyLevel(Player playerIn,ItemStack item){return holy + item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.ABYSS_CLEANSER).get());}

    public int getSerratedLevel(Player playerIn,ItemStack item){
        if(item.isEnchanted()&&item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.SERRATED).get())>=1) {

            return serrated + item.getEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.SERRATED).get());

        }else{
            return serrated;}}


    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }
    @Override
    public boolean isValidRepairItem(ItemStack p_43311_, ItemStack p_43312_) {
        return this.tier.getRepairIngredient().test(p_43312_) || super.isValidRepairItem(p_43311_, p_43312_);
    }
    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }@Override
    public float getDestroySpeed(ItemStack p_43288_, BlockState p_43289_) {
        if (p_43289_.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return p_43289_.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
        }
    }
    @Override
    public boolean hurtEnemy(ItemStack stackIn, LivingEntity target, LivingEntity entityIn) {
        /*stackIn.hurtAndBreak(1, playerIn, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });*/
        stackIn.hurtAndBreak(1, entityIn, EquipmentSlot.MAINHAND);
        if(entityIn instanceof Player playerIn) {
            /*if (target instanceof DarkestSoulsAbstractEntity) {
                ((DarkestSoulsAbstractEntity) target).damagePoiseHealth(this.getPoiseDamage(playerIn, stackIn));
                ((DarkestSoulsAbstractEntity) target).damagePostureHealth(this.getPostureDamage(playerIn, stackIn));
            }*/
            if (!target.fireImmune()) {
                if (this.getFireAttack(playerIn, stackIn) >= 1) {
                    target.igniteForTicks(40 * this.getFireAttack(playerIn,stackIn));
                }
            }
            if (!target.getType().is(CustomEntityTags.POISON_IMMUNE)) {
                if (this.getPoisonAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 90 + this.getPoisonAttack(playerIn,stackIn) * 45, this.getPoisonAttack(playerIn,stackIn) - 1), playerIn);
                }
                if (this.getToxicAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.TOXIC, 90 + this.getToxicAttack(playerIn,stackIn) * 45, this.getToxicAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.ROT_IMMUNE)) {
                if (this.getRotAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.ROT, 90 + this.getRotAttack(playerIn,stackIn) * 45, this.getRotAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.FROST_IMMUNE)) {
                if (this.getFrostAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.FROST, 90 + this.getFrostAttack(playerIn,stackIn) * 45, this.getFrostAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.WITHER_IMMUNE)) {
                if (this.getWitherAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 90 + this.getWitherAttack(playerIn,stackIn) * 45, this.getWitherAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.BLEED_IMMUNE)) {
                if (this.getBloodAttack(playerIn,stackIn) >= 1) {
                    if (target.hasEffect(EffectInit.BLEED)) {
                        int amount = target.getEffect(EffectInit.BLEED).getAmplifier() + this.getBloodAttack(playerIn,stackIn);
                        target.removeEffect(EffectInit.BLEED);
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED, 240, amount));
                    } else {
                        int amount = this.getBloodAttack(playerIn,stackIn) - 1;
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED, 240, amount));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stackIn, Level p_43283_, BlockState state, BlockPos pos, LivingEntity playerIn) {
        if (state.getDestroySpeed(p_43283_, pos) != 0.0F) {
            /*p_43282_.hurtAndBreak(2, p_43286_, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });*/
            stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);
        }
        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack itemStack, BlockState p_43298_) {
        return p_43298_.is(Blocks.COBWEB);
    }




    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.SWORD_DIG==itemAbility;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {

        return true;
    }
    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return this.durability;
    }


    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }
    /*
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        if(enchantment.getSupportedItems() == ItemTags.WEAPON_ENCHANTABLE ||enchantment.getSupportedItems() == ItemTags.SHARP_WEAPON_ENCHANTABLE  || enchantment.getSupportedItems() == ItemTags.DURABILITY_ENCHANTABLE || enchantment.getSupportedItems() == ItemTags.VANISHING_ENCHANTABLE) {
            return true;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }
*/
    
    @Override
    public boolean isRepairable(ItemStack stack) {

        return true;
    }



    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag p_41424_) {
        int maxTargets = getMaxTargets() + stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE));
        int fire = this.fire + stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT));
        int holy = this.holy + stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.ABYSS_CLEANSER));
        int serrated = this.serrated + stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.SERRATED));
        int blood = this.bloodAttack;
        if(stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.BLOODBLADE))>=1){
           blood = blood+1+2*stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.BLOODBLADE));
        }
        int poison = poisonAttack + stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.POISONED_BLADE));
        int toxic = stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.TOXIC_BLADE));
        int frost = frostAttack + stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.FROST_BLADE));
        int rot = rotAttack + stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.ROTTEN_BLADE));
        int wither = stack.getEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.WITHERED_BLADE));

        if(maxTargets>=1) {
            String info = "" + maxTargets;
            tooltip.add(Component.literal("+").append(Component.literal(info)).append(Component.translatable("translation.darkestsouls.targets")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(serrated>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+serrated)).append(Component.translatable("translation.darkestsouls.serrated")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        }
        if(holy>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+holy)).append(Component.translatable("translation.darkestsouls.holy")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.YELLOW));
        }
        if(fire>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+fire)).append(Component.translatable("translation.darkestsouls.fire")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED));
        }
        if(blood>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+blood)).append(Component.translatable("translation.darkestsouls.blood")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_RED));
        }
        if(poison>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+poison)).append(Component.translatable("translation.darkestsouls.poison")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
        }
        if(toxic>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+toxic)).append(Component.translatable("translation.darkestsouls.toxic")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_PURPLE));
        }
        if(frost>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+frost)).append(Component.translatable("translation.darkestsouls.frost")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.AQUA));
        }
        if(rot>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+rot)).append(Component.translatable("translation.darkestsouls.rot")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.YELLOW));
        }
        if(wither>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+wither)).append(Component.translatable("translation.darkestsouls.wither")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BLACK));
        }

        super.appendHoverText(stack, pContext, tooltip, p_41424_);
    }



    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn){}




    public void doRightClickAction(Player playerIn, ItemStack stackIn){}

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {

        this.doRightClickAction(p_41433_,p_41433_.getItemInHand(p_41434_));

        return super.use(p_41432_, p_41433_, p_41434_);

    }

    @Override
    public void performSweepAttack(Player playerIn, ItemStack stackIn) {

    }
}
