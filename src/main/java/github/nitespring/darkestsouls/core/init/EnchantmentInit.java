package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.enchantment.*;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.common.item.IAmmoConsumingItem;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import github.nitespring.darkestsouls.core.util.CustomItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;

import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;


public class EnchantmentInit {
    /*public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
            DarkestSouls.MODID);*/
    public static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
    private static ResourceKey<Enchantment> key(String string) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID,string));
    }

    public static final ResourceKey<Enchantment> BLOODBLADE = key("bloodblade");
    public static final ResourceKey<Enchantment> FROST_BLADE = key("frost_blade");
    public static final ResourceKey<Enchantment> POISONED_BLADE = key("poisoned_blade");
    public static final ResourceKey<Enchantment> TOXIC_BLADE = key("toxic_blade");
    public static final ResourceKey<Enchantment> ROTTEN_BLADE = key("rotten_blade");
    public static final ResourceKey<Enchantment> WITHERED_BLADE = key("withered_blade");
    public static final ResourceKey<Enchantment> SERRATED = key("serrated");
    public static final ResourceKey<Enchantment> ABYSS_CLEANSER = key("abyss_cleanser");
    public static final ResourceKey<Enchantment> BEAST_HUNTER = key("beast_hunter");
    public static final ResourceKey<Enchantment> FIREPOWER = key("firepower");
    public static final ResourceKey<Enchantment> GREATER_FIREPOWER = key("greater_firepower");
    public static final ResourceKey<Enchantment> STARPOWER = key("starpower");
    public static final ResourceKey<Enchantment> MOON_BLESSING = key("moon_blessing");
    public static final ResourceKey<Enchantment> GUNSLINGER = key("gunslinger");
    public static final ResourceKey<Enchantment> SHARPSHOOTER = key("sharpshooter");
    public static final ResourceKey<Enchantment> FLAMING_SHOT = key("flaming_shot");
    public static final ResourceKey<Enchantment> EXPLODING_SHOT = key("exploding_shot");
    public static final ResourceKey<Enchantment> OPHIDIAN_BITE = key("ophidian_bite");
    public static final ResourceKey<Enchantment> EXPANDING_SHOT = key("expanding_shot");
    public static final ResourceKey<Enchantment> RICOCHET_SHOT = key("ricochet_shot");
    public static final ResourceKey<Enchantment> PIERCING_SHOT = key("piercing_shot");
    public static final ResourceKey<Enchantment> MISER_SOUL = key("miser_soul");
    public static final ResourceKey<Enchantment> CHILD_OF_THUNDER = key("child_of_the_thunder_god");
    
    
    
    /*public static final DeferredHolder<Enchantment,BloodBladeEnchantment> BLOODBLADE = key("bloodblade",
            () -> new BloodBladeEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            4, 3,
                            Enchantment.dynamicCost(4,20),
                            Enchantment.dynamicCost(30,24),
                            2, EquipmentSlot.MAINHAND)));
    public static final DeferredHolder<Enchantment,MobEffectInflictEnchantment> FROST_BLADE = key("frost_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            2, 2,
                            Enchantment.dynamicCost(4,14),
                            Enchantment.dynamicCost(24,16),
                            2, EquipmentSlot.MAINHAND),
                    EffectInit.FROST));
    public static final DeferredHolder<Enchantment,MobEffectInflictEnchantment> POISONED_BLADE = key("poisoned_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            4, 2,
                            Enchantment.dynamicCost(4,10),
                            Enchantment.dynamicCost(24,12),
                            2, EquipmentSlot.MAINHAND),
                    MobEffects.POISON));
    public static final DeferredHolder<Enchantment,MobEffectInflictEnchantment> TOXIC_BLADE = key("toxic_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            1, 1,
                            Enchantment.constantCost(16),
                            Enchantment.constantCost(56),
                            4, EquipmentSlot.MAINHAND),
                    EffectInit.TOXIC));
    public static final DeferredHolder<Enchantment,MobEffectInflictEnchantment> ROTTEN_BLADE = key("rotten_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            1, 2,
                            Enchantment.dynamicCost(6,14),
                            Enchantment.dynamicCost(28,16),
                            3, EquipmentSlot.MAINHAND),
                    EffectInit.ROT));
    public static final DeferredHolder<Enchantment,MobEffectInflictEnchantment> WITHERED_BLADE = key("withered_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            1, 2,
                            Enchantment.dynamicCost(5,12),
                            Enchantment.dynamicCost(25,14),
                            4, EquipmentSlot.MAINHAND),
                    MobEffects.WITHER));
    public static final DeferredHolder<Enchantment,PercentageDamageEnchantment> SERRATED = key("serrated",
            () -> new PercentageDamageEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            3, 2,
                            Enchantment.dynamicCost(5,10),
                            Enchantment.dynamicCost(25,12),
                            2, EquipmentSlot.MAINHAND),
                    CustomEntityTags.BEAST));
    public static final DeferredHolder<Enchantment,PercentageDamageEnchantment> ABYSS_CLEANSER = key("abyss_cleanser",
            () -> new PercentageDamageEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            2, 2,
                            Enchantment.dynamicCost(5,10),
                            Enchantment.dynamicCost(25,12),
                            2, EquipmentSlot.MAINHAND),
                    CustomEntityTags.ABYSSAL));
    public static final DeferredHolder<Enchantment, DamageEnchantment> BEAST_HUNTER = key("beast_hunter",
            () -> new DamageEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            2, 5,
                            Enchantment.dynamicCost(5,8),
                            Enchantment.dynamicCost(25,8),
                            1, EquipmentSlot.MAINHAND),
                    Optional.of(CustomEntityTags.BEAST)));


    public static final DeferredHolder<Enchantment,FirepowerEnchantment> FIREPOWER = key("firepower",
            () -> new FirepowerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            10, 5,
                            Enchantment.dynamicCost(1,8),
                            Enchantment.dynamicCost(24,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,GreaterFirepowerEnchantment> GREATER_FIREPOWER = key("greater_firepower",
            () -> new GreaterFirepowerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 5,
                            Enchantment.dynamicCost(6,10),
                            Enchantment.dynamicCost(36,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,StarpowerEnchantment> STARPOWER = key("starpower",
            () -> new StarpowerEnchantment(
                    Enchantment.definition(CustomItemTags.MAGIC_ENCHANTABLE,
                            10, 5,
                            Enchantment.dynamicCost(1,8),
                            Enchantment.dynamicCost(24,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,LunarPowerEnchantment> MOON_BLESSING = key("moon_blessing",
            () -> new LunarPowerEnchantment(
                    Enchantment.definition(CustomItemTags.MAGIC_ENCHANTABLE,
                            2, 5,
                            Enchantment.dynamicCost(6,10),
                            Enchantment.dynamicCost(36,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,GunslingerEnchantment> GUNSLINGER = key("gunslinger",
            () -> new GunslingerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(1,10),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,SharpshooterEnchantment> SHARPSHOOTER = key("sharpshooter",
            () -> new SharpshooterEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(2,10),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,FlamingShotEnchantment> FLAMING_SHOT = key("flaming_shot",
            () -> new FlamingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 1,
                            Enchantment.constantCost(20),
                            Enchantment.constantCost(46),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,ExplosingShotEnchantment> EXPLODING_SHOT = key("exploding_shot",
            () -> new ExplosingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 3,
                            Enchantment.dynamicCost(10,18),
                            Enchantment.dynamicCost(36,20),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,OphidianBiteEnchantment> OPHIDIAN_BITE = key("ophidian_bite",
            () -> new OphidianBiteEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 2,
                            Enchantment.dynamicCost(6,12),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,ExpandingShotEnchantment> EXPANDING_SHOT = key("expanding_shot",
            () -> new ExpandingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(6,12),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,RicochetEnchantment> RICOCHET_SHOT = key("ricochet_shot",
            () -> new RicochetEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 1,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(30,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,PiercingEnchantment> PIERCING_SHOT = key("piercing_shot",
            () -> new PiercingEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            6, 5,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(30,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,MiserSoulEnchantment> MISER_SOUL = key("miser_soul",
            () -> new MiserSoulEnchantment(
                    Enchantment.definition(CustomItemTags.AMMO_CONSUMING,
                            6, 6,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(20,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,ChildOfTheThunderGodEnchantment> CHILD_OF_THUNDER = key("child_of_the_thunder_god",
            () -> new ChildOfTheThunderGodEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 1,
                            Enchantment.constantCost(25),
                            Enchantment.constantCost(50),
                            1, HAND_SLOTS)));*/


}
