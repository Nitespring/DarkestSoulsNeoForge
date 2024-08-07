package github.nitespring.darkestsouls.core.event;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.beast.BeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Skeleton;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.ArmourUtils;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = DarkestSouls.MODID, bus = EventBusSubscriber.Bus.GAME)
public class EntityEvents {
    @SubscribeEvent
    public static void applyDamageEvent(final LivingIncomingDamageEvent event){
        DamageSource source = event.getSource();
        //System.out.println(event.getAmount());
        //System.out.println(event.getEntity().getType());
        //System.out.println(event.getEntity().getType().getTags().count());
        if(event.getEntity() instanceof Player player){
            if(source.is(DamageTypeTags.IS_FIRE)) {
                event.setAmount((float) (event.getAmount()*(1-0.01*ArmourUtils.getFireResistance(player))));
            }
            if(source.is(DamageTypeTags.BYPASSES_RESISTANCE)){
                event.setAmount((float) (event.getAmount()*(1-0.01*ArmourUtils.getMagicDefence(player))));
            }
        }
        if(event.getEntity().getType().is(CustomEntityTags.BEAST)){
            if(source.is(DamageTypeTags.IS_FIRE)) {
                event.setAmount(event.getAmount()*2.5f);
            }else if (event.getEntity().isOnFire()){
                event.setAmount(event.getAmount()*1.2f);
            }
        }
        if(source.is(DamageTypes.PLAYER_ATTACK)&&source.getEntity()!=null&&source.getEntity() instanceof Player attacker){

            if(attacker.hasItemInSlot(EquipmentSlot.MAINHAND)){
                ItemStack itemStack = attacker.getItemInHand(InteractionHand.MAIN_HAND);

                if (itemStack.getItem() instanceof Weapon weapon) {
                    if(event.getEntity().getType().is(CustomEntityTags.BEAST)) {
                        event.setAmount((float) (event.getAmount()*(1+0.1*weapon.getSerratedLevel(attacker,itemStack))));
                        //System.out.println(event.getAmount());
                    }
                    if(event.getEntity().getType().is(CustomEntityTags.BEASTLY)) {
                        event.setAmount((float) (event.getAmount()*(1+0.05*weapon.getSerratedLevel(attacker,itemStack))));
                        //System.out.println(event.getAmount());
                    }
                    if(event.getEntity().getType().is(CustomEntityTags.ABYSSAL)) {
                        event.setAmount((float) (event.getAmount()*(1+0.1*weapon.getHolyLevel(attacker,itemStack))));
                        //System.out.println(event.getAmount());
                    }
                } else {
                    if(itemStack.isEnchanted()) {
                        int serratedLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.SERRATED).get());
                        int holyLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.ABYSS_CLEANSER).get());
                        int bloodLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.BLOODBLADE).get());
                        int poisonLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.POISONED_BLADE).get());
                        int toxicLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.TOXIC_BLADE).get());
                        int frostLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.FROST_BLADE).get());
                        int rotLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.ROTTEN_BLADE).get());
                        int witherLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.WITHERED_BLADE).get());

                        if(serratedLevel>=1){
                            if(event.getEntity().getType().is(CustomEntityTags.BEAST)) {
                                event.setAmount((float) (event.getAmount() * (1 + (0.1 * serratedLevel))));
                                //System.out.println(event.getAmount());
                            }
                            if(event.getEntity().getType().is(CustomEntityTags.BEASTLY)) {
                                event.setAmount((float) (event.getAmount() * (1 + (0.05 * serratedLevel))));
                                //System.out.println(event.getAmount());
                            }
                        }
                        if(holyLevel>=1){
                            if(event.getEntity().getType().is(CustomEntityTags.ABYSSAL)) {
                                event.setAmount((float) (event.getAmount() * (1 + (0.1 * holyLevel))));
                                //System.out.println(event.getAmount());
                            }
                        }
                        if (!event.getEntity().getType().is(CustomEntityTags.POISON_IMMUNE)) {
                            if (poisonLevel >= 1) {
                                event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 90 + poisonLevel * 45, poisonLevel-1), source.getEntity());
                            }
                            if (toxicLevel >= 1) {
                                event.getEntity().addEffect(new MobEffectInstance(EffectInit.TOXIC, 90 +toxicLevel * 45, toxicLevel-1), source.getEntity());
                            }
                        }
                        if (!event.getEntity().getType().is(CustomEntityTags.ROT_IMMUNE)) {
                            if (rotLevel >= 1) {
                                event.getEntity().addEffect(new MobEffectInstance(EffectInit.ROT, 90 + rotLevel * 45, rotLevel - 1), source.getEntity());
                            }
                        }
                        if (!event.getEntity().getType().is(CustomEntityTags.FROST_IMMUNE)) {
                            if (frostLevel >= 1) {
                                event.getEntity().addEffect(new MobEffectInstance(EffectInit.FROST, 90 + frostLevel * 45, frostLevel - 1), source.getEntity());
                            }
                        }
                        if (!event.getEntity().getType().is(CustomEntityTags.WITHER_IMMUNE)) {
                            if (witherLevel >= 1) {
                                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 90 + witherLevel * 45, witherLevel - 1), source.getEntity());
                            }
                        }
                        if (!event.getEntity().getType().is(CustomEntityTags.BLEED_IMMUNE)) {
                            if (bloodLevel >= 1) {
                                int bloodLevelFinalized=1+bloodLevel*2;
                                if (event.getEntity().hasEffect(EffectInit.BLEED)) {
                                    int amount = event.getEntity().getEffect(EffectInit.BLEED).getAmplifier() + bloodLevelFinalized;
                                    event.getEntity().removeEffect(EffectInit.BLEED);
                                    event.getEntity().addEffect(new MobEffectInstance(EffectInit.BLEED, 240, amount));
                                    System.out.println(amount);
                                } else {
                                    int amount = bloodLevelFinalized - 1;
                                    event.getEntity().addEffect(new MobEffectInstance(EffectInit.BLEED, 240, amount));
                                    System.out.println(amount);
                                }
                                System.out.println(bloodLevelFinalized);
                            }
                        }
                    }
                }

            }

        }

    }

    @SubscribeEvent
    public static void removeStatusesEvent(final EntityTickEvent.Pre event){
        if(event.getEntity() instanceof LivingEntity entity){
            if(entity.getType().is(CustomEntityTags.FROST_IMMUNE)&&entity.hasEffect(EffectInit.FROST)){
                entity.removeEffect(EffectInit.FROST);
            }
            if(entity.getType().is(CustomEntityTags.WITHER_IMMUNE)&&entity.hasEffect(MobEffects.WITHER)){
                entity.removeEffect(MobEffects.WITHER);
            }
            if(entity.getType().is(CustomEntityTags.ROT_IMMUNE)&&entity.hasEffect(EffectInit.ROT)){
                entity.removeEffect(EffectInit.ROT);
            }
            if(entity.getType().is(CustomEntityTags.POISON_IMMUNE)){
                if(entity.hasEffect(MobEffects.POISON)){
                    entity.removeEffect(MobEffects.POISON);
                }
                if(entity.hasEffect(EffectInit.TOXIC)){
                    entity.removeEffect(EffectInit.TOXIC);
                }
            }
            if(entity.getType().is(CustomEntityTags.BLEED_IMMUNE)&&entity.hasEffect(EffectInit.BLEED)){
                entity.removeEffect(EffectInit.BLEED);
            }


        }
    }

    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event) {

        if(event.getEntity() instanceof Mob mob) {
            if (mob instanceof IronGolem || mob instanceof SnowGolem) {
                mob.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(mob, Mob.class, true, predicate -> predicate instanceof DarkestSoulsAbstractEntity mob1 && (mob1.getDSTeam() == 1 || mob1.getDSTeam() == 4 || mob1.getDSTeam() == 6)));
            }
            if (mob instanceof Wolf) {
                mob.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(mob, Skeleton.class, true));
                mob.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(mob, Bonewheel.class, true));
            }
            if (mob instanceof Villager villager) {
                villager.targetSelector.addGoal(2, new AvoidEntityGoal<>(villager, Mob.class, 12.0F, 1.0, 1.0, predicate -> predicate instanceof DarkestSoulsAbstractEntity mob1 && (mob1.getDSTeam() == 1 || mob1.getDSTeam() == 4 || mob1.getDSTeam() == 6)));
            }
            if (mob instanceof WanderingTrader villager) {
                villager.targetSelector.addGoal(2, new AvoidEntityGoal<>(villager, Mob.class, 12.0F, 1.0, 1.0, predicate -> predicate instanceof DarkestSoulsAbstractEntity mob1 && (mob1.getDSTeam() == 1 || mob1.getDSTeam() == 4 || mob1.getDSTeam() == 6)));
            }
            if (mob instanceof Animal animal && !(mob instanceof Wolf || mob instanceof HoglinBase)) {
                animal.targetSelector.addGoal(2, new AvoidEntityGoal<>(animal, Mob.class, 12.0F, 1.2, 1.4, predicate -> predicate instanceof DarkestSoulsAbstractEntity mob1 && (mob1.getDSTeam() == 4)));
            }
        }

    }




}
