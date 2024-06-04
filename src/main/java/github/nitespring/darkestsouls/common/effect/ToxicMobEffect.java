package github.nitespring.darkestsouls.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ToxicMobEffect extends MobEffect {
    public ToxicMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity p_296276_, int p_296233_) {


            var dTypeReg = p_296276_.damageSources().damageTypes;
            var dType = dTypeReg.getHolder(net.neoforged.neoforge.common.NeoForgeMod.POISON_DAMAGE).orElse(dTypeReg.getHolderOrThrow(net.minecraft.world.damagesource.DamageTypes.MAGIC));
            p_296276_.hurt(new net.minecraft.world.damagesource.DamageSource(dType), 2.0F);

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
        int i = 25 >> p_294232_;
        return i > 0 ? p_295368_ % i == 0 : true;
    }

}
