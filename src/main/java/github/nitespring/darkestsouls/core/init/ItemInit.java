package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.common.item.child.alchemy.Flamesprayer;
import github.nitespring.darkestsouls.common.item.child.alchemy.LanternNormal;
import github.nitespring.darkestsouls.common.item.child.armour.*;
import github.nitespring.darkestsouls.common.item.child.guns.GatlingGun;
import github.nitespring.darkestsouls.common.item.child.guns.Pistol;
import github.nitespring.darkestsouls.common.item.child.guns.Shotgun;
import github.nitespring.darkestsouls.common.item.child.staves.ChaosStaff;
import github.nitespring.darkestsouls.common.item.child.staves.CrystalStaff;
import github.nitespring.darkestsouls.common.item.child.staves.SorcererStaff;
import github.nitespring.darkestsouls.common.item.child.weapons.*;
import github.nitespring.darkestsouls.common.item.child.weapons.trickweapon.*;
import github.nitespring.darkestsouls.common.item.throwing.Firebomb;
import github.nitespring.darkestsouls.common.item.throwing.MolotovCocktail;
import github.nitespring.darkestsouls.common.item.throwing.ThrowingKnife;
import github.nitespring.darkestsouls.core.enums.Tiers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM,
			DarkestSouls.MODID);
	


	//Special Weapons

	public static final Supplier<FrayedBlade> FRAYED_BLADE = ITEMS.register("frayed_blade",
			() -> new FrayedBlade(Tiers.TITANITE, 8.0f, 1.6f, 3.5f,0.1f, 8, 3,0,0,0,0,0,0,0,1350,12,0.11f,-1, new Item.Properties().rarity(Rarity.EPIC)));
	public static final DeferredHolder<Item, ShadowBlade> SHADOW_BLADE = ITEMS.register("shadow_blade",
			() -> new ShadowBlade(Tiers.TITANITE, 8.0f, 1.6f,3.5f, 0.2f, 8,1,0,0,0,0,1,0,1, 1350,15,0.11f, 2,new Item.Properties().rarity(Rarity.EPIC)));
	public static final DeferredHolder<Item,DragonslayerSpear> DRAGONSLAYER_SPEAR = ITEMS.register("dragonslayer_spear",
			() -> new DragonslayerSpear(Tiers.TITANITE, 6.0f, 2.0f, 4.25f, -0.1f,5, 0,0,0,0,0,0,0, 0,1350,10,0.15f, -1,new Item.Properties().rarity(Rarity.EPIC)));
	public static final DeferredHolder<Item,Weapon> DRAGONSLAYER_SWORDSPEAR = ITEMS.register("dragonslayer_swordspear",
			() -> new DragonslayerSwordspear(Tiers.TITANITE, 7.0f, 1.8f,4.0f, 0.1f, 7, 0,0,0,0,0,0,0,0,1350,10,0.12f, 2,new Item.Properties().rarity(Rarity.EPIC)));
	public static final DeferredHolder<Item,StormCurvedSword> STORM_CURVED_SWORD = ITEMS.register("storm_curved_sword",
			() -> new StormCurvedSword(Tiers.TITANITE, 6.0f, 2.1f, 2.75f,0.1f, 5, 0,0,0,0,0,0,0, 0,1350,10,0.14f, 2,new Item.Properties().rarity(Rarity.EPIC)));
	public static final DeferredHolder<Item,DragonslayerGreataxe> DRAGONSLAYER_GREATAXE = ITEMS.register("dragonslayer_greataxe",
			() -> new DragonslayerGreataxe(Tiers.TITANITE, 10.0f, 1.0f, 3.5f,0.4f, 10, 0,0,0,0,0,0,0,0,225,8,0.07f, 3,new Item.Properties()));
	//Trick Weapons
	public static final DeferredHolder<Item,SawCleaver> SAW_CLEAVER = ITEMS.register("saw_cleaver",
			() -> new SawCleaver(Tiers.TITANITE, 6.0f, 1.7f, 3.0f,0.1f, 6, 0,0,0,0,0,0,0,3,1350,15,0.108f, 2,new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,SawCleaverExtended> SAW_CLEAVER_EXTENDED = ITEMS.register("saw_cleaver_extended",
			() -> new SawCleaverExtended(Tiers.TITANITE, 7.5f, 1.5f,3.5f, 0.2f, 8, 0,0,0,0,0,0,0,0,1350,15,0.104f, 3,new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,HunterAxe> HUNTER_AXE = ITEMS.register("hunter_axe",
			() -> new HunterAxe(Tiers.TITANITE, 7.0f, 1.4f, 3.0f,0.4f, 8, 0,0,0,0,0,0,0,0,1350,15,0.11f, 2,new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,HunterAxeExtended> HUNTER_AXE_EXTENDED = ITEMS.register("hunter_axe_extended",
			() -> new HunterAxeExtended(Tiers.TITANITE, 8.0f, 1.2f, 3.75f,0.6f, 12,0,0,0,0,0,0,0, 0,1350,15,0.11f, 3,new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Chikage> CHIKAGE = ITEMS.register("chikage",
			() -> new Chikage(Tiers.TITANITE, 6.0f, 1.9f, 3.5f,0.1f, 5,1,0,0,0,0,0,0, 0,1350,15,0.12f, 2,new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,ChikageExtended> CHIKAGE_EXTENDED = ITEMS.register("chikage_extended",
			() -> new ChikageExtended(Tiers.TITANITE, 12.0f, 1.7f,3.7f, 0.3f, 8,4,0,0,0,0,0,0, 0,1350,15,0.112f, 2,new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,HolyMoonlightSword> HOLY_MOONLIGHT = ITEMS.register("holy_moonlight_sword",
			() -> new HolyMoonlightSword(Tiers.TITANITE, 7.0f, 1.4f,3.6f, 0.2f, 6, 0,0,0,0,0,0,0,0,1350,17,0.1f, 3,new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,HolyMoonlightSwordLit> HOLY_MOONLIGHT_LIT = ITEMS.register("holy_moonlight_sword_lit",
			() -> new HolyMoonlightSwordLit(Tiers.TITANITE, 8.0f, 1.2f, 3.75f,0.2f, 6, 0,0,0,0,0,0,0,0,1350,17,0.1f, -1,new Item.Properties().rarity(Rarity.RARE)));

	public static final DeferredHolder<Item,BrokenStraightsword> BROKEN_STRAIGHTSWORD = ITEMS.register("broken_straightsword",
			() -> new BrokenStraightsword(Tiers.TITANITE, 3.0f, 1.6f,1.75f, 0.0f, 2, 0,0,0,0,0,0,0,0,127,8,0.1f, 1,new Item.Properties()));
	public static final DeferredHolder<Item,BanditKnife> BANDIT_KNIFE = ITEMS.register("bandit_knife",
			() -> new BanditKnife(Tiers.TITANITE, 3.0f, 2.4f, 2.25f,0.0f, 2, 3,0,0,0,0,0,0,0,63,6,0.18f, 1,new Item.Properties()));
	public static final DeferredHolder<Item,Longsword> LONGSWORD = ITEMS.register("longsword",
			() -> new Longsword(Tiers.TITANITE, 7.0f, 1.6f, 3.25f,0.0f, 6, 0,0,0,0,0,0,0,0,225,8,0.1f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,DarkSword> DARKSWORD = ITEMS.register("dark_sword",
			() -> new DarkSword(Tiers.TITANITE, 7.5f, 1.5f, 3.0f,0.05f, 6, 0,0,0,0,0,0,0,0,1024,10,0.0975f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Scimitar> SCIMITAR = ITEMS.register("scimitar",
			() -> new Scimitar(Tiers.TITANITE, 5.0f, 1.9f,3.0f, -0.2f, 4, 0,0,0,0,0,0,0,0,225,8,0.14f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Falchion> FALCHION = ITEMS.register("falchion",
			() -> new Falchion(Tiers.TITANITE, 6.0f, 1.7f,3.2f, -0.1f, 5,0,0,0,0,0,0,0, 0,275,7,0.13f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Shotel> SHOTEL = ITEMS.register("shotel",
			() -> new Shotel(Tiers.TITANITE, 5.5f, 1.8f,3.0f, -0.2f, 4,0,0,0,0,0,0,0, 0,275,7,0.14f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Shotel> CARTHUS_SHOTEL = ITEMS.register("carthus_shotel",
			() -> new Shotel(Tiers.TITANITE, 5.5f, 1.8f,3.1f, -0.2f, 5, 1,0,0,0,0,0,0,0,275,7,0.14f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,CurvedGreatsword> CARTHUS_CURVED_GREATSWORD= ITEMS.register("carthus_curved_greatsword",
			() -> new CurvedGreatsword(Tiers.TITANITE, 7.0f, 1.6f,3.75f, 0.2f, 7, 2,0,0,0,0,0,0,0,225,8,0.105f, 5,new Item.Properties()));
	public static final DeferredHolder<Item,Claymore> CLAYMORE = ITEMS.register("claymore",
			() -> new Claymore(Tiers.TITANITE, 9.0f, 1.2f, 3.6f, 0.4f, 12,0,0,0,0,0,0,0, 0,500,7,0.09f, 3,new Item.Properties()));
	public static final DeferredHolder<Item,Flamberge> FLAMBERGE = ITEMS.register("flamberge",
			() -> new Flamberge(Tiers.TITANITE, 8.5f, 1.2f, 3.6f,0.3f, 11,4, 0,0,0,0,0,0,1,400,6,0.09f, 3,new Item.Properties()));
	public static final DeferredHolder<Item,Zweihander> ZWEIHANDER = ITEMS.register("zweihander",
			() -> new Zweihander(Tiers.TITANITE, 10.5f, 1.0f, 4.0f,0.8f, 14, 0,0,0,0,0,0,0,0,1024,15,0.07f, 5,new Item.Properties()));
	public static final DeferredHolder<Item,Scimitar> BANDIT_CURVED_SWORD = ITEMS.register("bandit_curved_sword",
			() -> new Scimitar(Tiers.TITANITE, 6.0f, 1.6f, 3.15f,-0.2f, 4, 0,0,0,0,0,0,0,0,250,8,0.14f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Spear> SPEAR = ITEMS.register("spear",
			() -> new Spear(Tiers.TITANITE, 5.0f, 2.0f,4.5f, -0.1f, 4, 0,0,0,0,0,0,0,0,1350,10,0.13f, 1,new Item.Properties()));
	public static final DeferredHolder<Item,GraveScythe> GRAVE_SCYTHE= ITEMS.register("grave_scythe",
			() -> new GraveScythe(Tiers.TITANITE, 7.0f, 1.6f,4.6f, 0.2f, 7, 2,0,0,0,0,0,0,0,225,8,0.105f, 5,new Item.Properties()));
	public static final DeferredHolder<Item,Uchigatana> UCHIGATANA = ITEMS.register("uchigatana",
			() -> new Uchigatana(Tiers.TITANITE, 6.0f, 1.7f,3.5f, 0.1f, 5,2,0,0,0,0,0,0, 0,200,15,0.11f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Weapon> BATTLE_AXE = ITEMS.register("battle_axe",
			() -> new Axe(Tiers.TITANITE, 6.0f, 1.4f,3.0f, 0.1f, 6, 0,0,0,0,0,0,0,0,225,8,0.09f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Greataxe> EXECUTIONER_GREATAXE = ITEMS.register("executioner_greataxe",
			() -> new Greataxe(Tiers.TITANITE, 10.0f, 1.0f, 4.0f,0.4f, 10, 0,0,0,0,0,0,0,0,225,8,0.07f, 3,new Item.Properties()));
	public static final DeferredHolder<Item,Greataxe> CRESCENT_MOON_GREATAXE = ITEMS.register("crescent_moon_greataxe",
			() -> new Greataxe(Tiers.TITANITE, 9.0f, 1.2f, 4.25f,0.4f, 10, 0,0,0,0,0,0,0,0,225,8,0.08f, 3,new Item.Properties()));


	public static final DeferredHolder<Item,Weapon> HUNTSMAN_AXE = ITEMS.register("hunting_axe",
			() -> new Axe(Tiers.TITANITE, 6.0f, 1.4f,3.0f, 0.1f, 6, 0,0,0,0,0,0,0,0,225,8,0.09f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Scimitar> HUNTSMAN_CUTLASS = ITEMS.register("huntsman_cutlass",
			() -> new Scimitar(Tiers.TITANITE, 5.0f, 1.9f,3.0f, -0.2f, 4, 0,0,0,0,0,0,0,0,225,8,0.11f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Weapon> HUNTSMAN_PITCHFORK = ITEMS.register("huntsman_pitchfork",
			() -> new Spear(Tiers.TITANITE, 5.0f, 1.9f,3.8f, -0.2f, 4, 225,0,0,0,0,0,0,0,0,8,0.12f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,Weapon> CHURCH_SCYTHE= ITEMS.register("church_scythe",
			() -> new ChurchScythe(Tiers.TITANITE, 7.5f, 1.6f,4.5f, 0.2f, 7, 0,0,0,0,0,0,1,0,325,12,0.105f, 5,new Item.Properties()));
	public static final DeferredHolder<Item,Weapon> CHURCH_SCYTHE_UNLIT = ITEMS.register("church_scythe_unlit",
			() -> new ChurchScythe(Tiers.TITANITE, 7.5f, 1.6f,4.5f, 0.2f, 7, 0,0,0,0,0,0,1,0,325,12,0.105f, 5,new Item.Properties()));
	public static final DeferredHolder<Item,Weapon> CHURCH_CANE = ITEMS.register("church_cane",
			() -> new Weapon(Tiers.TITANITE, 4.0f, 1.4f, 2.5f,0.1f, 6, 0,0,0,0,0,0,0,0,255,8,0.1f, 0,new Item.Properties()));
	public static final DeferredHolder<Item,Spear> CRUCIFIX = ITEMS.register("crucifix",
			() -> new Spear(Tiers.TITANITE, 6.0f, 1.2f,4.0f, 0.2f, 6, 0,0,0,0,1, 0,1,0,1350,10,0.08f, 2,new Item.Properties()));
	//Staves
	public static final DeferredHolder<Item,SorcererStaff> SORCERER_STAFF_A = ITEMS.register("sorcerer_staff_a",
			() -> new SorcererStaff(2.0f, 128, 0, new Item.Properties().rarity(Rarity.COMMON)));
	public static final DeferredHolder<Item,SorcererStaff> SORCERER_STAFF_B = ITEMS.register("sorcerer_staff_b",
			() -> new SorcererStaff(4.0f, 256, 1, new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,CrystalStaff> CRYSTAL_STAFF = ITEMS.register("crystal_staff",
			() -> new CrystalStaff(4.0f, 256, 0, 1, new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,CrystalStaff> CRYSTAL_STAFF_PURPLE = ITEMS.register("crystal_staff_purple",
			() -> new CrystalStaff(8.0f, 384, 1, 2, new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,CrystalStaff> CRYSTAL_STAFF_BLUE = ITEMS.register("crystal_staff_blue",
			() -> new CrystalStaff(12.0f, 512, 2, 3, new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,ChaosStaff> CHAOS_STAFF = ITEMS.register("chaos_staff",
			() -> new ChaosStaff(10.0f, 1024, 2, new Item.Properties().rarity(Rarity.EPIC)));

	//Guns
	public static final DeferredHolder<Item,Item> QUICKSILVER_BULLET = ITEMS.register("quicksilver_bullet",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(24)));
	public static final DeferredHolder<Item,Pistol> HUNTER_PISTOL = ITEMS.register("hunter_pistol",
			() -> new Pistol(6.0f, 18,2,0.4f,0.5f, 8, 0,0,1, 255, 5, new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Shotgun> BLUNDERBUSS = ITEMS.register("blunderbuss",
			() -> new Shotgun(10.0f, 24,2,0.3f,0.25f, 10, 0,0,1, 127, 0.4f, 0.4f, 5, new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Pistol> EVELYN = ITEMS.register("evelyn",
			() -> new Pistol(6.5f, 12,2,0.3f,0.5f, 10, 0,0,1, 511, 10, new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Pistol> REPEATING_PISTOL = ITEMS.register("repeating_pistol",
			() -> new Pistol(16.0f, 28,6,0.5f,0.5f, 12, 0,0,2, 511, 8, new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,GatlingGun> GATLING_GUN = ITEMS.register("gatling_gun",
			() -> new GatlingGun(2.0f, 60,0,0.5f,0.5f, 12, 0,0,1, 511, 5, new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Pistol> MUSKET = ITEMS.register("musket",
			() -> new Pistol(8.0f, 24,2,0.4f,0.5f, 16, 0,0,1, 511, 8, new Item.Properties().rarity(Rarity.UNCOMMON)));
	//Alchemy
	public static final DeferredHolder<Item,Weapon> HUNTER_TORCH= ITEMS.register("hunter_torch",
			() -> new Weapon(Tiers.TITANITE, 1.0f, 1.9f, 2.5f,-0.2f, 4, 0,0,0,0,0,2,0,0,225,8,0.10f, 2,new Item.Properties()));
	public static final DeferredHolder<Item,LanternNormal> LANTERN = ITEMS.register("lantern",
			() -> new LanternNormal(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Flamesprayer> FLAMESPRAYER = ITEMS.register("flamesprayer",
			() -> new Flamesprayer(4.0f, 30, 10, 0.1f, 1, 0.8f, 1, 1, 256, 15, new Item.Properties().rarity(Rarity.UNCOMMON)));

	//Throwing
	public static final DeferredHolder<Item,ThrowingKnife> THROWING_KNIFE = ITEMS.register("throwing_knife",
			() -> new ThrowingKnife(4.0f, 18,0,0,6, 0.28f, 0.01f, true,0, new Item.Properties().stacksTo(20)));
	public static final DeferredHolder<Item,ThrowingKnife> BONE_KNIFE = ITEMS.register("bone_knife",
			() -> new ThrowingKnife(2.0f, 12,0,0,3,0.22f, 0.012f, true,0,  new Item.Properties().stacksTo(24)));
	public static final DeferredHolder<Item,ThrowingKnife> BLOOD_KNIFE = ITEMS.register("blood_knife",
			() -> new ThrowingKnife(2.0f, 18,2,0,4, 0.28f, 0.01f, true,0, new Item.Properties().stacksTo(20)));
	public static final DeferredHolder<Item,ThrowingKnife> POISON_KNIFE = ITEMS.register("poison_knife",
			() -> new ThrowingKnife(2.0f, 18,0,2,4, 0.28f, 0.01f, true,0, new Item.Properties().stacksTo(20)));
	public static final DeferredHolder<Item,ThrowingKnife> KUKRI = ITEMS.register("kukri",
			() -> new ThrowingKnife(6.0f, 24,4,0,8, 0.3f, 0.008f, false,1,new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(16)));
	public static final DeferredHolder<Item,Firebomb> FIREBOMB = ITEMS.register("firebomb",
			() -> new Firebomb(6.0f, 24, 8, 0, new Item.Properties().stacksTo(20)));
	public static final DeferredHolder<Item,Firebomb> BLACK_FIREBOMB = ITEMS.register("black_firebomb",
			() -> new Firebomb(10.0f, 24, 8, 1, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(12)));
	public static final DeferredHolder<Item,MolotovCocktail> MOLOTOV = ITEMS.register("molotov",
			() -> new MolotovCocktail(6.0f, 28, 4, new Item.Properties().stacksTo(16)));

	//Armour
	public static final DeferredHolder<Item, HunterArmourItem> HUNTER_HAT = ITEMS.register("hunter_hat",
			() -> new HunterArmourItem(1,1,50,3,1,0,0,0.1f,0.3f, 0,0,0,0,50.005f,0.01f,0,0,2,1025,12, ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final DeferredHolder<Item, HunterArmourItem> HUNTER_COAT = ITEMS.register("hunter_coat",
			() -> new HunterArmourItem(1,1,50,5,1,0,0,0.1f,0.3f,0,0,0,0,0.01f,0.02f,0,2,3,1025,12, ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final DeferredHolder<Item, HunterArmourItem> HUNTER_TROUSERS = ITEMS.register("hunter_trousers",
			() -> new HunterArmourItem(1,1,50,4,0,0,0,0.1f,0.2f,0,0,0,0,0.02f,0.04f, 0,1,2,1025,12, ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final DeferredHolder<Item, HunterArmourItem> HUNTER_BOOTS = ITEMS.register("hunter_boots",
			() -> new HunterArmourItem(1,1,50,2,0,0, 0,0.1f,0.2f,0,0,0,0,0.015f,0.03f,0,0,1, 1025,12, ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final DeferredHolder<Item, AlchemistArmourItem> ALCHEMIST_HAT = ITEMS.register("alchemist_hat",
			() -> new AlchemistArmourItem(3,1,80,2,0,0,0,0,0,0,10,0,5,0,0,0,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final DeferredHolder<Item, AlchemistArmourItem> ALCHEMIST_COAT = ITEMS.register("alchemist_coat",
			() -> new AlchemistArmourItem(3,1,80,4,1,0,0,0,0,0,10,0,5,0,0,0,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final DeferredHolder<Item, AlchemistArmourItem> ALCHEMIST_TROUSERS = ITEMS.register("alchemist_trousers",
			() -> new AlchemistArmourItem(3,1,80,2,0,0,0,0,0,0,5,0,0,0,0,0,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final DeferredHolder<Item, AlchemistArmourItem> ALCHEMIST_BOOTS = ITEMS.register("alchemist_boots",
			() -> new AlchemistArmourItem(3,1,80,1,0,0,0,0,0,0,5,0,0,0,0,0,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final DeferredHolder<Item, SpecialistArmourItem> SPECIALIST_HAT = ITEMS.register("specialist_hat",
			() -> new SpecialistArmourItem(4,1,70,2,1,0,0,0,0,0,0,10,7,0,0,0,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final DeferredHolder<Item, SpecialistArmourItem> SPECIALIST_COAT = ITEMS.register("specialist_coat",
			() -> new SpecialistArmourItem(4,1,70,4,1,0,0,0,0,0,0,10,0,0,0,0,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final DeferredHolder<Item, SpecialistArmourItem> SPECIALIST_TROUSERS = ITEMS.register("specialist_trousers",
			() -> new SpecialistArmourItem(4,1,70,3,1,0,0,0,0,0,0,5,0,0,0,0,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final DeferredHolder<Item, SpecialistArmourItem> SPECIALIST_BOOTS = ITEMS.register("specialist_boots",
			() -> new SpecialistArmourItem(4,1,70,2,1,0,0,0,0,0,0,5,0,0,0,0,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final DeferredHolder<Item, TatteredWizardRobeItem> TATTERED_WIZARD_HAT = ITEMS.register("tattered_wizard_hat",
			() -> new TatteredWizardRobeItem(2,0,10,1,0,0,0,0,0,5,0,0,5,0,0,2,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final DeferredHolder<Item, TatteredWizardRobeItem> TATTERED_WIZARD_ROBE = ITEMS.register("tattered_wizard_robe",
			() -> new TatteredWizardRobeItem(2,0,10,2,0,0,0,0,0,5,0,0,5,0,0,3,0,0,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final DeferredHolder<Item, WizardRobeItem> WIZARD_HAT = ITEMS.register("wizard_hat",
			() -> new WizardRobeItem(2,1,11,2,0,0,0,0,0,10,0,0,10,0,0,5,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final DeferredHolder<Item, WizardRobeItem> WIZARD_ROBE = ITEMS.register("wizard_robe",
			() -> new WizardRobeItem(2,1,11,3,0,0,0,0,0,10,0,0,10,0,0,5,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final DeferredHolder<Item, WizardRobeItem> WIZARD_PANTS = ITEMS.register("wizard_pants",
			() -> new WizardRobeItem(2,1,11,2,0,0,0,0,0,5,0,0,5,0,0,2,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final DeferredHolder<Item, WizardRobeItem> WIZARD_BOOTS = ITEMS.register("wizard_boots",
			() -> new WizardRobeItem(2,1,11,1,0,0,0,0,0,5,0,0,5,0,0,3,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final DeferredHolder<Item, KnightArmourItem> KNIGHT_HELM = ITEMS.register("knight_helm",
			() -> new KnightArmourItem(0,1,20,4,3,0.1f,0.05f,0,0.5f,0,0,0,0,-0.005f,0,0,2,3,1024,12,ArmorMaterials.IRON, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final DeferredHolder<Item, KnightArmourItem> KNIGHT_CHESTPLATE = ITEMS.register("knight_chestplate",
			() -> new KnightArmourItem(0,1,20,7,3,0.2f,0.1f,-0.1f,0.5f,0,0,0,0,-0.01f,0,0,3,4,1024,12,ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final DeferredHolder<Item, KnightArmourItem> KNIGHT_PANTS = ITEMS.register("knight_pants",
			() -> new KnightArmourItem(0,1,20,5,2,0.05f,0,0,0.5f,0,0,0,0,-0.002f,0,0,2,2,1024,12,ArmorMaterials.IRON, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final DeferredHolder<Item, KnightArmourItem> KNIGHT_BOOTS = ITEMS.register("knight_boots",
			() -> new KnightArmourItem(0,1,20,3,1,0.05f,0.05f,0,0.5f,0,0,0,0,-0.001f,0,0,1,3,1024,12,ArmorMaterials.IRON, ArmorItem.Type.BOOTS,new Item.Properties()));
	//Eggs
	public static final DeferredHolder<Item,Item> SIN = ITEMS.register("sin_spawn_egg",
			() -> new SpawnEggItem(EntityInit.SIN.get(), 1318437, 16449279, new Item.Properties()));
	public static final DeferredHolder<Item,Item> BONEWHEEL = ITEMS.register("bonewheel_spawn_egg",
			() -> new SpawnEggItem(EntityInit.BONEWHEEL.get(), 13684684, 11432504, new Item.Properties()));
	public static final DeferredHolder<Item,Item> SEWER_CENTIPEDE = ITEMS.register("sewer_centipede_spawn_egg",
			() -> new SpawnEggItem(EntityInit.SEWER_CENTIPEDE.get(), 13686464, 7373164, new Item.Properties()));
	public static final DeferredHolder<Item,Item> SKELETON_FALCHION = ITEMS.register("skeleton_falchion_spawn_egg",
			() -> new SpawnEggItem(EntityInit.SKELETON_FALCHION.get(), 13684684, 14079971, new Item.Properties()));
	public static final DeferredHolder<Item,Item> SKELETON_CURVED_SWORDS = ITEMS.register("skeleton_curved_swords_spawn_egg",
			() -> new SpawnEggItem(EntityInit.SKELETON_CURVED_SWORDS.get(), 13684684, 7367532, new Item.Properties()));
	public static final DeferredHolder<Item,Item> SKELETON_SPEAR = ITEMS.register("skeleton_spear_spawn_egg",
			() -> new SpawnEggItem(EntityInit.SKELETON_SPEAR.get(), 13684684, 8618640, new Item.Properties()));
	public static final DeferredHolder<Item,Item> TALL_SKELETON_TWIN_SHOTELS = ITEMS.register("skeleton_swordsman_twin_shotels_spawn_egg",
			() -> new SpawnEggItem(EntityInit.TALL_SKELETON_TWIN_SHOTELS.get(), 13684684, 2239044, new Item.Properties()));
	public static final DeferredHolder<Item,Item> HOLLOW_LONGSWORD = ITEMS.register("hollow_longsword_spawn_egg",
			() -> new SpawnEggItem(EntityInit.HOLLOW_LONGSWORD.get(), 13945528, 5202790, new Item.Properties()));
	public static final DeferredHolder<Item,Item> HOLLOW_CROSSBOW = ITEMS.register("hollow_crossbow_spawn_egg",
			() -> new SpawnEggItem(EntityInit.HOLLOW_CROSSBOW.get(), 13945528, 5202790, new Item.Properties()));
	public static final DeferredHolder<Item,Item> HOLLOW_AXE = ITEMS.register("hollow_axe_spawn_egg",
			() -> new SpawnEggItem(EntityInit.HOLLOW_AXE.get(), 13945528, 6448753, new Item.Properties()));
	public static final DeferredHolder<Item,Item> HOLLOW_BROKEN_STRAIGHTSWORD = ITEMS.register("hollow_broken_straightsword_spawn_egg",
			() -> new SpawnEggItem(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(), 13945528, 12630442, new Item.Properties()));
	public static final DeferredHolder<Item,Item> GRAVETENDER_HOLLOW_LONGSWORD = ITEMS.register("gravetender_hollow_longsword_spawn_egg",
			() -> new SpawnEggItem(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(), 13945528, 2962739, new Item.Properties()));
	public static final DeferredHolder<Item,Item> HOLLOW_ASSASSIN = ITEMS.register("hollow_assassin_spawn_egg",
			() -> new SpawnEggItem(EntityInit.HOLLOW_ASSASSIN.get(), 13945528, 2304301, new Item.Properties()));
	public static final DeferredHolder<Item,Item> GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD = ITEMS.register("gravetender_hollow_broken_straightsword_spawn_egg",
			() -> new SpawnEggItem(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(), 13945528, 2962739, new Item.Properties()));
	public static final DeferredHolder<Item,Item> BEAST_PATIENT = ITEMS.register("beast_patient_spawn_egg",
			() -> new SpawnEggItem(EntityInit.BEAST_PATIENT.get(), 2962739, 11432504, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CLOAKED_BEAST_PATIENT = ITEMS.register("cloaked_beast_patient_spawn_egg",
			() -> new SpawnEggItem(EntityInit.CLOAKED_BEAST_PATIENT.get(), 2962739, 11432504, new Item.Properties()));
	public static final DeferredHolder<Item,Item> ASHEN_BLOOD_BEAST_PATIENT = ITEMS.register("ashen_blood_beast_patient_spawn_egg",
			() -> new SpawnEggItem(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), 2962739, 11432504, new Item.Properties()));
	public static final DeferredHolder<Item,Item> LEECH = ITEMS.register("leech_spawn_egg",
			() -> new SpawnEggItem(EntityInit.LEECH.get(), 1318437, 7373164, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHURCH_DOCTOR = ITEMS.register("church_doctor_spawn_egg",
			() -> new SpawnEggItem(EntityInit.CHURCH_DOCTOR.get(), 4475990, 14804204, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHURCH_DOCTOR_LANTERN = ITEMS.register("church_doctor_lantern_spawn_egg",
			() -> new SpawnEggItem(EntityInit.CHURCH_DOCTOR_LANTERN.get(), 4475990, 16777204, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHURCH_DOCTOR_SCYTHE = ITEMS.register("church_doctor_scythe_spawn_egg",
			() -> new SpawnEggItem(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), 4475990, 11588863, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHURCH_DOCTOR_PISTOL = ITEMS.register("church_doctor_pistol_spawn_egg",
			() -> new SpawnEggItem(EntityInit.CHURCH_DOCTOR_PISTOL.get(), 4475990, 11250609, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHURCH_DOCTOR_FLAMESPRAYER = ITEMS.register("church_doctor_flamesprayer_spawn_egg",
			() -> new SpawnEggItem(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), 4475990, 16736256, new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHURCH_DOCTOR_CRUCIFIX = ITEMS.register("church_doctor_crucifix_spawn_egg",
			() -> new SpawnEggItem(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), 4475990, 2097152, new Item.Properties()));
	public static final DeferredHolder<Item,Item> DARKWRAITH = ITEMS.register("darkwraith_spawn_egg",
			() -> new SpawnEggItem(EntityInit.DARKWRAITH.get(), 987415, 6750208, new Item.Properties()));

    //Items
	public static final DeferredHolder<Item,Item> TITANITE_FRAGMENT = ITEMS.register("titanite_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Item> RUNE_FRAGMENT = ITEMS.register("rune_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Item> SOUL_FRAGMENT = ITEMS.register("soul_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Item> SMALL_SOUL_FRAGMENT = ITEMS.register("small_soul_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Item> BONE_FRAGMENT = ITEMS.register("bone_fragment",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DEMON_FRAGMENT = ITEMS.register("demon_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> TWINKLING_FRAGMENT = ITEMS.register("twinkling_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> DRAGON_SCALE_FRAGMENT = ITEMS.register("dragon_scale_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> DARK_FRAGMENT = ITEMS.register("dark_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Item> SIDERITE_FRAGMENT = ITEMS.register("siderite_fragment",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHAOS_FRAGMENT = ITEMS.register("chaos_fragment",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLOOD_STONE_FRAGMENT = ITEMS.register("blood_stone_fragment",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLOOD_GEM = ITEMS.register("blood_gem",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Item> BEAST_BLOOD_CLUMP = ITEMS.register("beast_blood_clump",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> QUICKSILVER = ITEMS.register("quicksilver",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DEMON_TITANITE = ITEMS.register("demon_titanite",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> TWINKLING_TITANITE = ITEMS.register("twinkling_titanite",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> DRAGON_SCALE = ITEMS.register("dragon_scale",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> INFUSED_DRAGON_SCALE = ITEMS.register("infused_dragon_scale",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> CORRUPTED_DRAGON_SCALE = ITEMS.register("corrupted_dragon_scale",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> BEWITCHED_BRANCH = ITEMS.register("bewitched_branch",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Item> CHAOS_ROOT = ITEMS.register("chaos_root",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> CINNABAR = ITEMS.register("cinnabar",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> CRYSTAL = ITEMS.register("crystal",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item,Item> GREEN_CRYSTAL = ITEMS.register("green_crystal",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> PURPLE_CRYSTAL = ITEMS.register("purple_crystal",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item,Item> BLOOD_CRYSTAL = ITEMS.register("blood_crystal",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));

	public static final DeferredHolder<Item,Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> SIDERITE_NUGGET = ITEMS.register("siderite_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> TITANITE_NUGGET = ITEMS.register("titanite_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> GOLDEN_NUGGET = ITEMS.register("golden_alloy_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> TWINKLING_NUGGET = ITEMS.register("twinkling_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DEMON_NUGGET = ITEMS.register("demon_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHAOS_NUGGET = ITEMS.register("chaos_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DARK_NUGGET = ITEMS.register("dark_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> NIGHTMARE_NUGGET = ITEMS.register("nightmare_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLOOD_NUGGET = ITEMS.register("blood_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> HOLY_NUGGET = ITEMS.register("holy_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> MAGIC_NUGGET = ITEMS.register("magic_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DRAGON_NUGGET = ITEMS.register("dragon_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> LIGHTNING_NUGGET = ITEMS.register("lightning_nugget",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> SIDERITE_INGOT = ITEMS.register("siderite_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> TITANITE_INGOT = ITEMS.register("titanite_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> GOLDEN_INGOT = ITEMS.register("golden_alloy_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> TWINKLING_INGOT = ITEMS.register("twinkling_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DEMON_INGOT = ITEMS.register("demon_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHAOS_INGOT = ITEMS.register("chaos_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DARK_INGOT = ITEMS.register("dark_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> NIGHTMARE_INGOT = ITEMS.register("nightmare_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLOOD_INGOT = ITEMS.register("blood_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> HOLY_INGOT = ITEMS.register("holy_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> MAGIC_INGOT = ITEMS.register("magic_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DRAGON_INGOT = ITEMS.register("dragon_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> LIGHTNING_INGOT = ITEMS.register("lightning_ingot",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> STRAIGHTSWORD_HILT = ITEMS.register("straightsword_hilt",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final DeferredHolder<Item,Item> CURVED_SWORD_HILT = ITEMS.register("curved_sword_hilt",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final DeferredHolder<Item,Item> GREATSWORD_HILT = ITEMS.register("greatsword_hilt",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final DeferredHolder<Item,Item> REINFORCED_POLE = ITEMS.register("reinforced_pole",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final DeferredHolder<Item,Item> REINFORCED_HANDLE = ITEMS.register("reinforced_handle",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final DeferredHolder<Item,Item> CURVED_HANDLE = ITEMS.register("reinforced_curved_handle",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final DeferredHolder<Item,Item> GUN_HANDLE = ITEMS.register("gun_handle",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> REINFORCED_GUNMETAL_HANDLE = ITEMS.register("reinforced_gunmetal_handle",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> GUNMETAL_BARREL = ITEMS.register("gunmetal_barrel",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> REINFORCED_GUNMETAL_BARREL = ITEMS.register("reinforced_gunmetal_barrel",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> REINFORCED_GUNMETAL_DOUBLE_BARREL = ITEMS.register("reinforced_gunmetal_double_barrel",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> IGNITION_MECHANISM = ITEMS.register("ignition_mechanism",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> WORKSHOP_MECHANISM = ITEMS.register("workshop_mechanism",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final DeferredHolder<Item,Item> SOUL_ESSENCE = ITEMS.register("soul_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> DARK_ESSENCE = ITEMS.register("dark_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> FIRE_ESSENCE = ITEMS.register("fire_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> CHAOS_ESSENCE = ITEMS.register("chaos_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> LIGHTNING_ESSENCE = ITEMS.register("lightning_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> LIGHT_ESSENCE = ITEMS.register("light_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLOOD_ESSENCE = ITEMS.register("blood_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> POISON_ESSENCE = ITEMS.register("poison_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> FROST_ESSENCE = ITEMS.register("frost_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> ROT_ESSENCE = ITEMS.register("rot_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BEAST_ESSENCE = ITEMS.register("beast_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> NIGHTMARE_ESSENCE = ITEMS.register("nightmare_essence",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> EYE = ITEMS.register("eye",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BEAST_EYE = ITEMS.register("beast_eye",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLIND_EYE = ITEMS.register("blind_eye",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLOSSOMED_EYE = ITEMS.register("blossomed_eye",
			() -> new Item(new Item.Properties()));

	public static final DeferredHolder<Item,Item> BLOOD_STONE_SHARD = ITEMS.register("blood_stone_shard",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> TWIN_BLOOD_STONE_SHARDS = ITEMS.register("twin_blood_stone_shards",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLOOD_STONE_CHUNK = ITEMS.register("blood_stone_chunk",
			() -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item,Item> BLOOD_ROCK = ITEMS.register("blood_rock",
			() -> new Item(new Item.Properties()));

	//Blocks

	public static final DeferredHolder<Item,BlockItem> CINNABAR_ORE = ITEMS.register("cinnabar_ore",
			() -> new BlockItem(BlockInit.CINNABAR_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> SIDERITE_ORE = ITEMS.register("siderite_ore",
			() -> new BlockItem(BlockInit.SIDERITE_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> DEEPSLATE_CINNABAR_ORE = ITEMS.register("deepslate_cinnabar_ore",
			() -> new BlockItem(BlockInit.DEEPSLATE_CINNABAR_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> DEEPSLATE_SIDERITE_ORE = ITEMS.register("deepslate_siderite_ore",
			() -> new BlockItem(BlockInit.DEEPSLATE_SIDERITE_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> NETHER_CINNABAR_ORE = ITEMS.register("nether_cinnabar_ore",
			() -> new BlockItem(BlockInit.NETHER_CINNABAR_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> NETHER_SIDERITE_ORE = ITEMS.register("nether_siderite_ore",
			() -> new BlockItem(BlockInit.NETHER_SIDERITE_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> BLACKSTONE_CINNABAR_ORE = ITEMS.register("blackstone_cinnabar_ore",
			() -> new BlockItem(BlockInit.BLACKSTONE_CINNABAR_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> BLACKSTONE_SIDERITE_ORE = ITEMS.register("blackstone_siderite_ore",
			() -> new BlockItem(BlockInit.BLACKSTONE_SIDERITE_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> ENDER_CINNABAR_ORE = ITEMS.register("ender_cinnabar_ore",
			() -> new BlockItem(BlockInit.ENDER_CINNABAR_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> ENDER_SIDERITE_ORE = ITEMS.register("ender_siderite_ore",
			() -> new BlockItem(BlockInit.ENDER_SIDERITE_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> BASALT_CINNABAR_ORE = ITEMS.register("basalt_cinnabar_ore",
			() -> new BlockItem(BlockInit.BASALT_CINNABAR_ORE.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> SIDERITE_BRICKS = ITEMS.register("siderite_bricks",
			() -> new BlockItem(BlockInit.SIDERITE_BRICKS.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> CRACKED_SIDERITE_BRICKS = ITEMS.register("cracked_siderite_bricks",
			() -> new BlockItem(BlockInit.CRACKED_SIDERITE_BRICKS.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> SIDERITE_BRICKS_SLAB = ITEMS.register("siderite_brick_slab",
			() -> new BlockItem(BlockInit.SIDERITE_BRICKS_SLAB.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> SIDERITE_BRICKS_STAIRS = ITEMS.register("siderite_brick_stairs",
			() -> new BlockItem(BlockInit.SIDERITE_BRICKS_STAIRS.get(),new Item.Properties()));
	public static final DeferredHolder<Item,BlockItem> SIDERITE_BRICKS_WALL = ITEMS.register("siderite_brick_wall",
			() -> new BlockItem(BlockInit.SIDERITE_BRICKS_WALL.get(),new Item.Properties()));
}
