package github.nitespring.darkestsouls.core.event;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = DarkestSouls.MODID)
public class DamageEvents {
    @SubscribeEvent
    public static void applyDamageEvent(final LivingHurtEvent event){
        DamageSource source = event.getSource();
        //System.out.println(event.getAmount());
        //System.out.println(event.getEntity().getType());
        //System.out.println(event.getEntity().getType().getTags().count());

        if(event.getEntity().getType().is(CustomEntityTags.BEASTLY)){
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
                        int holyLevel = itemStack.getEnchantmentLevel(attacker.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.SERRATED).get());

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




}
