package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.effect.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class EffectInit {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT,
            DarkestSouls.MODID);
   /*public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION,
            DarkestSouls.MODID);*/

    public static final DeferredHolder<MobEffect,BleedMobEffect> BLEED =   EFFECTS.register("hemorragie",
            () -> new BleedMobEffect(MobEffectCategory.HARMFUL,7673883));

    public static final DeferredHolder<MobEffect,MobEffect> FROST =   EFFECTS.register("frost",
            () -> new FrostMobEffect(MobEffectCategory.HARMFUL,15072511)
                    .addAttributeModifier(Attributes.ARMOR,"648d7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                            -2.0, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS,"648d7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                            -1.0, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ATTACK_SPEED,"55FCED67-E92A-486E-9800-B47F202C4386",
                            -0.05000000149011612, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,"7107DE5E-7CE8-4030-940E-514C1F160890",
                            -0.15000000596046448, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );

    public static final DeferredHolder<MobEffect,RotMobEffect> ROT =   EFFECTS.register("rot",
            () -> new RotMobEffect(MobEffectCategory.HARMFUL,7673883));
    public static final DeferredHolder<MobEffect,ToxicMobEffect> TOXIC = EFFECTS.register("toxic",
            () -> new ToxicMobEffect(MobEffectCategory.HARMFUL, 5377115));
    public static final DeferredHolder<MobEffect,ParasitesMobEffect> PARASITES =   EFFECTS.register("parasites",
            () -> new ParasitesMobEffect(MobEffectCategory.HARMFUL,7673883));
    public static final DeferredHolder<MobEffect,ChikageMobEffect> CHIKAGE=EFFECTS.register("chikage",
            () -> new ChikageMobEffect(MobEffectCategory.HARMFUL, 7673883));

    public static final DeferredHolder<MobEffect,MobEffect> FAKE_POISON=EFFECTS.register("fake_poison",
            () -> new SimpleMobEffect(MobEffectCategory.NEUTRAL, 5149489));




}
