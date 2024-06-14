package github.nitespring.darkestsouls.common.enchantment;

import com.mojang.serialization.MapCodec;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.item.enchantment.effects.SummonEntityEffect;
import net.minecraft.world.phys.Vec3;

public class BloodBladeEnchantment{
    public BloodBladeEnchantment() {

    }

    public void doPostAttack(LivingEntity attacker, Entity pTarget, int level) {
        if(!(attacker.getMainHandItem().getItem() instanceof Weapon)) {
            if(pTarget instanceof LivingEntity target) {
                EntityType<?> type = target.getType();
                if (!type.is(CustomEntityTags.BLEED_IMMUNE)) {
                    if (target.hasEffect(EffectInit.BLEED)) {
                        int amount = target.getEffect(EffectInit.BLEED).getAmplifier() + 1 + 2 * level;
                        target.removeEffect(EffectInit.BLEED);
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED, 240, amount));
                    } else {
                        int amount = 1+2*level;
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED, 240, amount));
                    }
                }
            }
        }
    }


}
