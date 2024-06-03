package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.enchantment.*;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.common.item.IAmmoConsumingItem;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import github.nitespring.darkestsouls.core.util.CustomItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;


public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT,
            DarkestSouls.MODID);
    public static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

    public static final DeferredHolder<Enchantment,BloodBladeEnchantment> BLOODBLADE = ENCHANTMENTS.register("bloodblade",
            () -> new BloodBladeEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            8, 3,
                            Enchantment.dynamicCost(2,10),
                            Enchantment.dynamicCost(24,12),
                            1, EquipmentSlot.MAINHAND)));

    public static final DeferredHolder<Enchantment,PercentageDamageEnchantment> SERRATED = ENCHANTMENTS.register("serrated",
            () -> new PercentageDamageEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            8, 2,
                            Enchantment.dynamicCost(2,10),
                            Enchantment.dynamicCost(24,12),
                            1, EquipmentSlot.MAINHAND),
                    CustomEntityTags.BEAST));
    public static final DeferredHolder<Enchantment, DamageEnchantment> BEAST_HUNTER = ENCHANTMENTS.register("beast_hunter",
            () -> new DamageEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            8, 5,
                            Enchantment.dynamicCost(2,10),
                            Enchantment.dynamicCost(24,12),
                            1, EquipmentSlot.MAINHAND),
                    Optional.of(CustomEntityTags.BEAST)));


    public static final DeferredHolder<Enchantment,FirepowerEnchantment> FIREPOWER = ENCHANTMENTS.register("firepower",
            () -> new FirepowerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            10, 5,
                            Enchantment.dynamicCost(1,8),
                            Enchantment.dynamicCost(24,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,GreaterFirepowerEnchantment> GREATER_FIREPOWER = ENCHANTMENTS.register("greater_firepower",
            () -> new GreaterFirepowerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 5,
                            Enchantment.dynamicCost(6,10),
                            Enchantment.dynamicCost(36,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,GunslingerEnchantment> GUNSLINGER = ENCHANTMENTS.register("gunslinger",
            () -> new GunslingerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(1,10),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,SharpshooterEnchantment> SHARPSHOOTER = ENCHANTMENTS.register("sharpshooter",
            () -> new SharpshooterEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(2,10),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,FlamingShotEnchantment> FLAMING_SHOT = ENCHANTMENTS.register("flaming_shot",
            () -> new FlamingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 1,
                            Enchantment.constantCost(20),
                            Enchantment.constantCost(46),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,ExplosingShotEnchantment> EXPLODING_SHOT = ENCHANTMENTS.register("exploding_shot",
            () -> new ExplosingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 3,
                            Enchantment.dynamicCost(10,18),
                            Enchantment.dynamicCost(36,20),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,OphidianBiteEnchantment> OPHIDIAN_BITE = ENCHANTMENTS.register("ophidian_bite",
            () -> new OphidianBiteEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 2,
                            Enchantment.dynamicCost(6,12),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,ExpandingShotEnchantment> EXPANDING_SHOT = ENCHANTMENTS.register("expanding_shot",
            () -> new ExpandingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(6,12),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,RicochetEnchantment> RICOCHET_SHOT = ENCHANTMENTS.register("ricochet_shot",
            () -> new RicochetEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 1,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(30,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,PiercingEnchantment> PIERCING_SHOT = ENCHANTMENTS.register("piercing_shot",
            () -> new PiercingEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            6, 5,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(30,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,MiserSoulEnchantment> MISER_SOUL = ENCHANTMENTS.register("miser_soul",
            () -> new MiserSoulEnchantment(
                    Enchantment.definition(CustomItemTags.AMMO_CONSUMING,
                            6, 6,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(20,10),
                            1, HAND_SLOTS)));
    public static final DeferredHolder<Enchantment,ChildOfTheThunderGodEnchantment> CHILD_OF_THUNDER = ENCHANTMENTS.register("child_of_the_thunder_god",
            () -> new ChildOfTheThunderGodEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 1,
                            Enchantment.constantCost(25),
                            Enchantment.constantCost(50),
                            1, HAND_SLOTS)));


}
