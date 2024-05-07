package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.enchantment.*;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.common.item.IAmmoConsumingItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT,
            DarkestSouls.MODID);
    public static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
    public static final EnchantmentCategory GUN = EnchantmentCategory.create("gun", item -> item instanceof Gun);
    public static final EnchantmentCategory AMMO_CONSUMER = EnchantmentCategory.create("ammo_consumer", item -> item instanceof IAmmoConsumingItem);
    public static final DeferredHolder<Enchantment,FirepowerEnchantment> FIREPOWER = ENCHANTMENTS.register("firepower",
            () -> new FirepowerEnchantment(Enchantment.Rarity.COMMON));
    public static final DeferredHolder<Enchantment,GreaterFirepowerEnchantment> GREATER_FIREPOWER = ENCHANTMENTS.register("greater_firepower",
            () -> new GreaterFirepowerEnchantment(Enchantment.Rarity.RARE));
    public static final DeferredHolder<Enchantment,GunslingerEnchantment> GUNSLINGER = ENCHANTMENTS.register("gunslinger",
            () -> new GunslingerEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final DeferredHolder<Enchantment,SharpshooterEnchantment> SHARPSHOOTER = ENCHANTMENTS.register("sharpshooter",
            () -> new SharpshooterEnchantment(Enchantment.Rarity.RARE));
    public static final DeferredHolder<Enchantment,FlamingShotEnchantment> FLAMING_SHOT = ENCHANTMENTS.register("flaming_shot",
            () -> new FlamingShotEnchantment(Enchantment.Rarity.RARE));
    public static final DeferredHolder<Enchantment,ExplosingShotEnchantment> EXPLODING_SHOT = ENCHANTMENTS.register("exploding_shot",
            () -> new ExplosingShotEnchantment(Enchantment.Rarity.VERY_RARE));
    public static final DeferredHolder<Enchantment,OphidianBiteEnchantment> OPHIDIAN_BITE = ENCHANTMENTS.register("ophidian_bite",
            () -> new OphidianBiteEnchantment(Enchantment.Rarity.RARE));
    public static final DeferredHolder<Enchantment,ExpandingShotEnchantment> EXPANDING_SHOT = ENCHANTMENTS.register("expanding_shot",
            () -> new ExpandingShotEnchantment(Enchantment.Rarity.RARE));
    public static final DeferredHolder<Enchantment,RicochetEnchantment> RICOCHET_SHOT = ENCHANTMENTS.register("ricochet_shot",
            () -> new RicochetEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final DeferredHolder<Enchantment,PiercingEnchantment> PIERCING_SHOT = ENCHANTMENTS.register("piercing_shot",
            () -> new PiercingEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final DeferredHolder<Enchantment,MiserSoulEnchantment> MISER_SOUL = ENCHANTMENTS.register("miser_soul",
            () -> new MiserSoulEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final DeferredHolder<Enchantment,ChildOfTheThunderGodEnchantment> CHILD_OF_THUNDER = ENCHANTMENTS.register("child_of_the_thunder_god",
            () -> new ChildOfTheThunderGodEnchantment(Enchantment.Rarity.UNCOMMON));



}
