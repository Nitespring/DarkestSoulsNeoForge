package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;

public class MobEffectInflictEnchantment   {
    private final Holder<MobEffect> effect;
    public MobEffectInflictEnchantment() {
        effect=null;
    }
     


    public void doPostAttack(LivingEntity attacker, Entity pTarget, int level) {
        if(!(attacker.getMainHandItem().getItem() instanceof Weapon)) {
            if(pTarget instanceof LivingEntity target) {
                EntityType<?> type = target.getType();
                if(effect==MobEffects.POISON||effect==EffectInit.TOXIC) {
                    if (!type.is(CustomEntityTags.POISON_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(effect, 90 + level * 45, level - 1), attacker);
                    }
                }else if(effect==MobEffects.WITHER) {
                    if (!type.is(CustomEntityTags.WITHER_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 90 + level * 45, level - 1), attacker);
                    }
                }else if(effect==EffectInit.FROST) {
                    if (!type.is(CustomEntityTags.FROST_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(EffectInit.FROST, 90 + level * 45, level - 1), attacker);
                    }
                }else if(effect==EffectInit.ROT) {
                    if (!type.is(CustomEntityTags.ROT_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(EffectInit.ROT, 90 + level * 45, level - 1), attacker);
                    }
                }else{
                    target.addEffect(new MobEffectInstance(effect, 90 + level * 45, level - 1), attacker);
                }
            }
        }
    }
}
