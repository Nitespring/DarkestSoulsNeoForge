package github.nitespring.darkestsouls.core.init;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Darkwraith;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Leech;
import github.nitespring.darkestsouls.common.entity.mob.abyss.SewerCentipede;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.beast.BeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.beast.CloakedBeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.church.*;
import github.nitespring.darkestsouls.common.entity.mob.demon.CapraDemon;
import github.nitespring.darkestsouls.common.entity.mob.dog.*;
import github.nitespring.darkestsouls.common.entity.mob.hollow.*;
import github.nitespring.darkestsouls.common.entity.mob.kin.spider.LargeNightmareApostle;
import github.nitespring.darkestsouls.common.entity.mob.kin.spider.LargeSpider;
import github.nitespring.darkestsouls.common.entity.mob.kin.spider.NightmareApostle;
import github.nitespring.darkestsouls.common.entity.mob.kin.spider.Spider;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.*;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.Bullet;
import github.nitespring.darkestsouls.common.entity.projectile.TrashParasites;
import github.nitespring.darkestsouls.common.entity.projectile.TrashPoison;
import github.nitespring.darkestsouls.common.entity.projectile.spell.*;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.MolotovCocktailEntity;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.Flame;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.MoonlightSlash;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.WindSlash;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.FrayedBladeAttackEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.FrayedBladeFlameEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.HeavyWeaponAttackEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.WeaponAttackEntity;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntities.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE,
			 DarkestSouls.MODID);
	

	public static final DeferredHolder<EntityType<?>, EntityType<MonstruosityOfSin>> SIN = ENTITIES.register("monstruosity_of_sin",
			() -> EntityType.Builder.<MonstruosityOfSin>of(MonstruosityOfSin::new, MobCategory.MONSTER)
			.sized(2.8f, 2.2f)
			.build("monstruosity_of_sin"));
	public static final DeferredHolder<EntityType<?>,EntityType<Bonewheel>> BONEWHEEL = ENTITIES.register("bonewheel",
			() -> EntityType.Builder.<Bonewheel>of(Bonewheel::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("bonewheel"));
	public static final DeferredHolder<EntityType<?>,EntityType<SkeletonFalchion>> SKELETON_FALCHION = ENTITIES.register("skeleton_falchion",
			() -> EntityType.Builder.<SkeletonFalchion>of(SkeletonFalchion::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("skeleton_falchion"));
	public static final DeferredHolder<EntityType<?>,EntityType<SkeletonCurvedSwords>> SKELETON_CURVED_SWORDS = ENTITIES.register("skeleton_curved_swords",
			() -> EntityType.Builder.<SkeletonCurvedSwords>of(SkeletonCurvedSwords::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("skeleton_curved_swords"));
	public static final DeferredHolder<EntityType<?>,EntityType<SkeletonSpear>> SKELETON_SPEAR = ENTITIES.register("skeleton_spear",
			() -> EntityType.Builder.<SkeletonSpear>of(SkeletonSpear::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("skeleton_spear"));
	public static final DeferredHolder<EntityType<?>,EntityType<SkeletonSwordsmanTwinShotels>> TALL_SKELETON_TWIN_SHOTELS = ENTITIES.register("skeleton_swordsman_twin_shotels",
			() -> EntityType.Builder.<SkeletonSwordsmanTwinShotels>of(SkeletonSwordsmanTwinShotels::new, MobCategory.MONSTER)
					.sized(0.6f, 2.0f)
					.build("skeleton_swordsman_twin_shotels"));
	public static final DeferredHolder<EntityType<?>,EntityType<SewerCentipede>> SEWER_CENTIPEDE = ENTITIES.register("sewer_centipede",
			() -> EntityType.Builder.<SewerCentipede>of(SewerCentipede::new, MobCategory.MONSTER)
					.sized(1.4f, 1.2f)
					.build("sewer_centipede"));
	public static final DeferredHolder<EntityType<?>,EntityType<HollowSoldierLongsword>> HOLLOW_LONGSWORD = ENTITIES.register("hollow_longsword",
			() -> EntityType.Builder.<HollowSoldierLongsword>of(HollowSoldierLongsword::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("hollow_longsword"));
	public static final DeferredHolder<EntityType<?>,EntityType<MadHollowBrokenStraightsword>> HOLLOW_BROKEN_STRAIGHTSWORD = ENTITIES.register("hollow_broken_straightsword",
			() -> EntityType.Builder.<MadHollowBrokenStraightsword>of(MadHollowBrokenStraightsword::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("hollow_broken_straightsword"));
	public static final DeferredHolder<EntityType<?>,EntityType<HollowSoldierAxe>> HOLLOW_AXE = ENTITIES.register("hollow_axe",
			() -> EntityType.Builder.<HollowSoldierAxe>of(HollowSoldierAxe::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("hollow_axe"));
	public static final DeferredHolder<EntityType<?>,EntityType<GravetenderHollowLongsword>> GRAVETENDER_HOLLOW_LONGSWORD = ENTITIES.register("gravetender_hollow_longsword",
			() -> EntityType.Builder.<GravetenderHollowLongsword>of(GravetenderHollowLongsword::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("hollow_longsword"));
	public static final DeferredHolder<EntityType<?>,EntityType<HollowAssassin>> HOLLOW_ASSASSIN = ENTITIES.register("hollow_assassin",
			() -> EntityType.Builder.<HollowAssassin>of(HollowAssassin::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("hollow_assassin"));
	public static final DeferredHolder<EntityType<?>,EntityType<GravetenderHollowBrokenStraightsword>> GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD = ENTITIES.register("gravetender_hollow_broken_straightsword",
			() -> EntityType.Builder.<GravetenderHollowBrokenStraightsword>of(GravetenderHollowBrokenStraightsword::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("gravetender_hollow_broken_straightsword"));
	public static final DeferredHolder<EntityType<?>,EntityType<HollowSoldierCrossbow>> HOLLOW_CROSSBOW = ENTITIES.register("hollow_crossbow",
			() -> EntityType.Builder.<HollowSoldierCrossbow>of(HollowSoldierCrossbow::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("hollow_crossbow"));
	public static final DeferredHolder<EntityType<?>,EntityType<GravetenderHollowCrossbow>> GRAVETENDER_HOLLOW_CROSSBOW = ENTITIES.register("gravetender_hollow_crossbow",
			() -> EntityType.Builder.<GravetenderHollowCrossbow>of(GravetenderHollowCrossbow::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("gravetender_hollow_crossbow"));
	public static final DeferredHolder<EntityType<?>,EntityType<HuntsmanAxe>> HUNTSMAN_AXE = ENTITIES.register("huntsman_axe",
			() -> EntityType.Builder.<HuntsmanAxe>of(HuntsmanAxe::new, MobCategory.MONSTER)
					.sized(0.7f, 1.95f)
					.build("huntsman_axe"));
	public static final DeferredHolder<EntityType<?>,EntityType<HuntsmanCutlass>> HUNTSMAN_CUTLASS = ENTITIES.register("huntsman_cutlass",
			() -> EntityType.Builder.<HuntsmanCutlass>of(HuntsmanCutlass::new, MobCategory.MONSTER)
					.sized(0.7f, 1.95f)
					.build("huntsman_cutlass"));
	public static final DeferredHolder<EntityType<?>,EntityType<HuntsmanPitchfork>> HUNTSMAN_PITCHFORK = ENTITIES.register("huntsman_pitchfork",
			() -> EntityType.Builder.<HuntsmanPitchfork>of(HuntsmanPitchfork::new, MobCategory.MONSTER)
					.sized(0.7f, 1.95f)
					.build("huntsman_pitchfork"));
	public static final DeferredHolder<EntityType<?>,EntityType<HuntsmanRifle>> HUNTSMAN_RIFLE = ENTITIES.register("huntsman_rifle",
			() -> EntityType.Builder.<HuntsmanRifle>of(HuntsmanRifle::new, MobCategory.MONSTER)
					.sized(0.7f, 1.95f)
					.build("huntsman_rifle"));
	public static final DeferredHolder<EntityType<?>,EntityType<BeastPatient>> BEAST_PATIENT = ENTITIES.register("beast_patient",
			() -> EntityType.Builder.<BeastPatient>of(BeastPatient::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("beast_patient"));
	public static final DeferredHolder<EntityType<?>,EntityType<CloakedBeastPatient>> CLOAKED_BEAST_PATIENT = ENTITIES.register("cloaked_beast_patient",
			() -> EntityType.Builder.<CloakedBeastPatient>of(CloakedBeastPatient::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("cloaked_beast_patient"));
	public static final DeferredHolder<EntityType<?>,EntityType<AshenBloodBeastPatient>> ASHEN_BLOOD_BEAST_PATIENT = ENTITIES.register("ashen_blood_beast_patient",
			() -> EntityType.Builder.<AshenBloodBeastPatient>of(AshenBloodBeastPatient::new, MobCategory.MONSTER)
					.sized(0.8f, 2.4f)
					.build("ashen_blood_beast_patient"));
	public static final DeferredHolder<EntityType<?>,EntityType<Leech>> LEECH = ENTITIES.register("leech",
			() -> EntityType.Builder.<Leech>of(Leech::new, MobCategory.MONSTER)
					.sized(0.9f, 2.4f)
					.build("leech"));
	public static final DeferredHolder<EntityType<?>,EntityType<ChurchDoctorStick>> CHURCH_DOCTOR = ENTITIES.register("church_doctor",
			() -> EntityType.Builder.<ChurchDoctorStick>of(ChurchDoctorStick::new, MobCategory.MONSTER)
					.sized(0.65f, 2.0f)
					.build("church_doctor"));
	public static final DeferredHolder<EntityType<?>,EntityType<ChurchDoctorLantern>> CHURCH_DOCTOR_LANTERN = ENTITIES.register("church_doctor_lantern",
			() -> EntityType.Builder.<ChurchDoctorLantern>of(ChurchDoctorLantern::new, MobCategory.MONSTER)
					.sized(0.65f, 2.0f)
					.build("church_doctor_lantern"));
	public static final DeferredHolder<EntityType<?>,EntityType<ChurchDoctorScythe>> CHURCH_DOCTOR_SCYTHE = ENTITIES.register("church_doctor_scythe",
			() -> EntityType.Builder.<ChurchDoctorScythe>of(ChurchDoctorScythe::new, MobCategory.MONSTER)
					.sized(0.65f, 2.0f)
					.build("church_doctor_scythe"));
	public static final DeferredHolder<EntityType<?>,EntityType<ChurchDoctorPistol>> CHURCH_DOCTOR_PISTOL = ENTITIES.register("church_doctor_pistol",
			() -> EntityType.Builder.<ChurchDoctorPistol>of(ChurchDoctorPistol::new, MobCategory.MONSTER)
					.sized(0.65f, 2.0f)
					.build("church_doctor_pistol"));
	public static final DeferredHolder<EntityType<?>,EntityType<ChurchDoctorFlamesprayer>> CHURCH_DOCTOR_FLAMESPRAYER = ENTITIES.register("church_doctor_flamesprayer",
			() -> EntityType.Builder.<ChurchDoctorFlamesprayer>of(ChurchDoctorFlamesprayer::new, MobCategory.MONSTER)
					.sized(0.65f, 2.0f)
					.build("church_doctor_flamesprayer"));
	public static final DeferredHolder<EntityType<?>,EntityType<ChurchDoctorCrucifix>> CHURCH_DOCTOR_CRUCIFIX = ENTITIES.register("church_doctor_crucifix",
			() -> EntityType.Builder.<ChurchDoctorCrucifix>of(ChurchDoctorCrucifix::new, MobCategory.MONSTER)
					.sized(0.65f, 2.0f)
					.build("church_doctor_crucifix"));
	public static final DeferredHolder<EntityType<?>,EntityType<Darkwraith>> DARKWRAITH = ENTITIES.register("darkwraith",
			() -> EntityType.Builder.<Darkwraith>of(Darkwraith::new, MobCategory.MONSTER)
					.sized(0.65f, 2.0f)
					.build("darkwraith"));
	public static final DeferredHolder<EntityType<?>,EntityType<CapraDemon>> CAPRA_DEMON = ENTITIES.register("capra_demon",
			() -> EntityType.Builder.<CapraDemon>of(CapraDemon::new, MobCategory.MONSTER)
					.sized(0.8f, 2.75f)
					.eyeHeight(2.6f)
					.build("capra_demon"));
	public static final DeferredHolder<EntityType<?>,EntityType<Spider>> SPIDER = ENTITIES.register("spider",
			() -> EntityType.Builder.<Spider>of(Spider::new, MobCategory.MONSTER)
					.sized(1.25f, 1.2f)
					.eyeHeight(0.6f)
					.build("spider"));
	public static final DeferredHolder<EntityType<?>,EntityType<LargeSpider>> LARGE_SPIDER = ENTITIES.register("large_spider",
			() -> EntityType.Builder.<LargeSpider>of(LargeSpider::new, MobCategory.MONSTER)
					.sized(4.0f, 3.6f)
					.eyeHeight(1.5f)
					.build("large_spider"));
	public static final DeferredHolder<EntityType<?>,EntityType<NightmareApostle>> NIGHTMARE_APOSTLE = ENTITIES.register("nightmare_apostle",
			() -> EntityType.Builder.<NightmareApostle>of(NightmareApostle::new, MobCategory.MONSTER)
					.sized(1.25f, 1.2f)
					.eyeHeight(0.75f)
					.build("nightmare_apostle"));
	public static final DeferredHolder<EntityType<?>,EntityType<LargeNightmareApostle>> LARGE_NIGHTMARE_APOSTLE = ENTITIES.register("large_nightmare_apostle",
			() -> EntityType.Builder.<LargeNightmareApostle>of(LargeNightmareApostle::new, MobCategory.MONSTER)
					.sized(4.0f, 3.6f)
					.eyeHeight(1.8f)
					.build("large_nightmare_apostle"));
	public static final DeferredHolder<EntityType<?>,EntityType<RabidDog>> RABID_DOG = ENTITIES.register("rabid_dog",
			() -> EntityType.Builder.<RabidDog>of(RabidDog::new, MobCategory.MONSTER)
					.sized(0.9f, 1.2f)
					.build("rabid_dog"));
	public static final DeferredHolder<EntityType<?>,EntityType<HuntingDog>> HUNTING_DOG = ENTITIES.register("hunting_dog",
			() -> EntityType.Builder.<HuntingDog>of(HuntingDog::new, MobCategory.MONSTER)
					.sized(0.9f, 1.2f)
					.build("hunting_dog"));
	public static final DeferredHolder<EntityType<?>,EntityType<SilverDog>> SILVER_DOG = ENTITIES.register("silver_dog",
			() -> EntityType.Builder.<SilverDog>of(SilverDog::new, MobCategory.MONSTER)
					.sized(0.9f, 1.2f)
					.build("silver_dog"));
	public static final DeferredHolder<EntityType<?>,EntityType<HollowDog>> HOLLOW_DOG = ENTITIES.register("hollow_dog",
			() -> EntityType.Builder.<HollowDog>of(HollowDog::new, MobCategory.MONSTER)
					.sized(0.9f, 1.2f)
					.build("hollow_dog"));
	public static final DeferredHolder<EntityType<?>,EntityType<LargeHollowDog>> LARGE_HOLLOW_DOG = ENTITIES.register("large_hollow_dog",
			() -> EntityType.Builder.<LargeHollowDog>of(LargeHollowDog::new, MobCategory.MONSTER)
					.sized(1.0f, 1.5f)
					.build("large_hollow_dog"));
	public static final DeferredHolder<EntityType<?>,EntityType<UndeadDog>> UNDEAD_DOG = ENTITIES.register("undead_dog",
			() -> EntityType.Builder.<UndeadDog>of(UndeadDog::new, MobCategory.MONSTER)
					.sized(0.9f, 1.2f)
					.build("undead_dog"));
	public static final DeferredHolder<EntityType<?>,EntityType<LargeUndeadDog>> LARGE_UNDEAD_DOG = ENTITIES.register("large_undead_dog",
			() -> EntityType.Builder.<LargeUndeadDog>of(LargeUndeadDog::new, MobCategory.MONSTER)
					.sized(1.0f, 1.5f)
					.build("large_undead_dog"));

	//SpawnGroups
	public static final DeferredHolder<EntityType<?>,EntityType<HuntsmanSpawnGroupEntity>> HUNTSMAN_GROUP1 = ENTITIES.register("huntsman_group_1",
			() -> EntityType.Builder.<HuntsmanSpawnGroupEntity>of(HuntsmanSpawnGroupEntity::new, MobCategory.MONSTER)
					.sized(4.0f, 2.0f)
					.build("huntsman_group_1"));
	public static final DeferredHolder<EntityType<?>,EntityType<ChurchDoctorSpawnGroupEntity>> CHURCH_DOCTOR_GROUP1 = ENTITIES.register("church_doctor_group_1",
			() -> EntityType.Builder.<ChurchDoctorSpawnGroupEntity>of(ChurchDoctorSpawnGroupEntity::new, MobCategory.MONSTER)
					.sized(6.0f, 2.0f)
					.build("church_doctor_group_1"));
	public static final DeferredHolder<EntityType<?>,EntityType<HollowSoldierSpawnGroupEntity>> HOLLOW_SOLDIER_GROUP1 = ENTITIES.register("hollow_soldier_group_1",
			() -> EntityType.Builder.<HollowSoldierSpawnGroupEntity>of(HollowSoldierSpawnGroupEntity::new, MobCategory.MONSTER)
					.sized(4.0f, 2.0f)
					.build("hollow_soldier_group_1"));
	public static final DeferredHolder<EntityType<?>,EntityType<GravetenderHollowSpawnGroupEntity>> GRAVETENDER_HOLLOW_GROUP1 = ENTITIES.register("gravetender_hollow_group_1",
			() -> EntityType.Builder.<GravetenderHollowSpawnGroupEntity>of(GravetenderHollowSpawnGroupEntity::new, MobCategory.MONSTER)
					.sized(4.0f, 2.0f)
					.build("gravetender_hollow_group_1"));
	public static final DeferredHolder<EntityType<?>,EntityType<BeastPatientSpawnGroupEntity>> BEAST_PATIENT_GROUP1 = ENTITIES.register("beast_patient_group_1",
			() -> EntityType.Builder.<BeastPatientSpawnGroupEntity>of(BeastPatientSpawnGroupEntity::new, MobCategory.MONSTER)
					.sized(4.0f, 2.0f)
					.build("beast_patient_group_1"));
	public static final DeferredHolder<EntityType<?>,EntityType<SkeletonSpawnGroupEntity>> SKELETON_GROUP1 = ENTITIES.register("skeleton_group_1",
			() -> EntityType.Builder.<SkeletonSpawnGroupEntity>of(SkeletonSpawnGroupEntity::new, MobCategory.MONSTER)
					.sized(4.0f, 2.0f)
					.build("skeleton_group_1"));


	public static final DeferredHolder<EntityType<?>,EntityType<DamageHitboxEntity>> HITBOX_SMALL = ENTITIES.register("hitbox_small",
			() -> EntityType.Builder.<DamageHitboxEntity>of(DamageHitboxEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.0f)
					.build("hitbox_small"));
	public static final DeferredHolder<EntityType<?>,EntityType<DamageHitboxEntity>> HITBOX = ENTITIES.register("hitbox",
			() -> EntityType.Builder.<DamageHitboxEntity>of(DamageHitboxEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.25f)
					.build("hitbox"));
	public static final DeferredHolder<EntityType<?>,EntityType<DamageHitboxEntity>> HITBOX_LARGE = ENTITIES.register("hitbox_large",
			() -> EntityType.Builder.<DamageHitboxEntity>of(DamageHitboxEntity::new, MobCategory.MISC)
					.sized(4.0f, 2.5f)
					.build("hitbox_large"));

	public static final DeferredHolder<EntityType<?>,EntityType<FrayedBladeAttackEntity>> FRAYED_BLADE = ENTITIES.register("frayed_blade",
			() -> EntityType.Builder.<FrayedBladeAttackEntity>of(FrayedBladeAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 2.0f)
					.build("frayed_blade"));
	public static final DeferredHolder<EntityType<?>,EntityType<FrayedBladeFlameEntity>> FRAYED_BLADE_FLAME = ENTITIES.register("frayed_blade_flame",
			() -> EntityType.Builder.<FrayedBladeFlameEntity>of(FrayedBladeFlameEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.5f)
					.build("frayed_blade_flame"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> SCIMITAR = ENTITIES.register("scimitar",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.0f)
					.build("scimitar"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> FALCHION = ENTITIES.register("falchion",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.1f, 1.1f)
					.build("falchion"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> CLAYMORE = ENTITIES.register("claymore",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.75f)
					.build("claymore"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> FLAMBERGE = ENTITIES.register("flamberge",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.75f)
					.build("flamberge"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> ZWEIHANDER = ENTITIES.register("zweihander",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.5f, 1.75f)
					.build("zweihander"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> UCHIGATANA = ENTITIES.register("uchigatana",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.5f)
					.build("uchigatana"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> BROKEN_STRAIGHTSWORD = ENTITIES.register("broken_straightsword",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.25f, 1.25f)
					.build("longsword"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> LONGSWORD = ENTITIES.register("longsword",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.5f, 1.25f)
					.build("longsword"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> SAW_CLEAVER = ENTITIES.register("saw_cleaver",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.6f, 1.5f)
					.build("saw_clear"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> SAW_CLEAVER_EXTENDED = ENTITIES.register("saw_cleaver_extended",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.5f)
					.build("saw_cleaver_extended"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> CHIKAGE = ENTITIES.register("chikage",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.8f, 1.25f)
					.build("chikage"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> SHADOW_BLADE = ENTITIES.register("shadow_blade",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.5f)
					.build("shadow_blade"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> GRAVE_SCYTHE = ENTITIES.register("grave_scythe",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.5f, 2.0f)
					.build("grave_scythe"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> CHURCH_SCYTHE = ENTITIES.register("church_scythe",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.5f, 2.0f)
					.build("church_scythe"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> HUNTER_AXE_EXTENDED = ENTITIES.register("hunter_axe_extended",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.5f, 2.0f)
					.build("hunter_axe_extended"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> SPEAR = ENTITIES.register("spear",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.5f, 1.25f)
					.build("spear"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> DRAGONSLAYER_SWORDSPEAR = ENTITIES.register("dragonslayer_swordspear",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.5f)
					.build("dragonslayer_swordspear"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> DRAGONSLAYER_SPEAR = ENTITIES.register("dragonslayer_spear",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.5f, 1.25f)
					.build("dragonslayer_spear"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> HUNTING_AXE = ENTITIES.register("hunting_axe",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.4f, 1.4f)
					.build("hunting_axe"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> HUNTER_AXE = ENTITIES.register("hunter_axe",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.5f, 1.5f)
					.build("hunter_axe"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> BANDIT_KNIFE = ENTITIES.register("bandit_knife",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.0f)
					.build("bandit_knife"));
	public static final DeferredHolder<EntityType<?>,EntityType<HeavyWeaponAttackEntity>> GREATAXE = ENTITIES.register("greataxe",
			() -> EntityType.Builder.<HeavyWeaponAttackEntity>of(HeavyWeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 2.0f)
					.build("greataxe"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> CURVED_GREATSWORD = ENTITIES.register("curved_greatsword",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.25f, 1.5f)
					.build("curved_greatsword"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> SHOTEL = ENTITIES.register("shotel",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.5f, 1.5f)
					.build("shotel"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> GREATSWORD = ENTITIES.register("greatsword",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.5f, 2.0f)
					.build("greatsword"));
	public static final DeferredHolder<EntityType<?>,EntityType<WeaponAttackEntity>> DARKSWORD = ENTITIES.register("darksword",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.5f, 1.25f)
					.build("darksword"));

	public static final DeferredHolder<EntityType<?>,EntityType<SoulDart>> SOUL_DART = ENTITIES.register("soul_dart",
			() -> EntityType.Builder.<SoulDart>of(SoulDart::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("soul_dart"));
	public static final DeferredHolder<EntityType<?>,EntityType<SoulArrow>> SOUL_ARROW = ENTITIES.register("soul_arrow",
			() -> EntityType.Builder.<SoulArrow>of(SoulArrow::new, MobCategory.MISC)
					.sized(1.2f, 1.2f)
					.build("soul_arrow"));
	public static final DeferredHolder<EntityType<?>,EntityType<LightningSpear>> LIGHTNING_SPEAR = ENTITIES.register("lightning_spear",
			() -> EntityType.Builder.<LightningSpear>of(LightningSpear::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("lightning_spear"));
	public static final DeferredHolder<EntityType<?>,EntityType<Fireball>> FIREBALL = ENTITIES.register("fireball",
			() -> EntityType.Builder.<Fireball>of(Fireball::new, MobCategory.MISC)
					.sized(1.25f, 1.25f)
					.build("fireball"));
	public static final DeferredHolder<EntityType<?>,EntityType<ChaosFireball>> CHAOS_FIREBALL = ENTITIES.register("chaos_fireball",
			() -> EntityType.Builder.<ChaosFireball>of(ChaosFireball::new, MobCategory.MISC)
					.sized(2.0f, 2.0f)
					.build("chaos_fireball"));

	public static final DeferredHolder<EntityType<?>,EntityType<MagmaEntity>> MAGMA = ENTITIES.register("magma",
			() -> EntityType.Builder.<MagmaEntity>of(MagmaEntity::new, MobCategory.MISC)
					.sized(1.0f, 0.4f)
					.build("magma"));

	public static final DeferredHolder<EntityType<?>,EntityType<MagmaBurstParent>> MAGMA_BURST = ENTITIES.register("magma_burst",
			() -> EntityType.Builder.<MagmaBurstParent>of(MagmaBurstParent::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("magma_burst"));

	public static final DeferredHolder<EntityType<?>,EntityType<MagmaBurstEntity>> MAGMA_BURST_CHILD = ENTITIES.register("magma_burst_child",
			() -> EntityType.Builder.<MagmaBurstEntity>of(MagmaBurstEntity::new, MobCategory.MISC)
					.sized(0.6f, 0.6f)
					.build("magma_burst_child"));
	public static final DeferredHolder<EntityType<?>,EntityType<FireStormParent>> FIRE_STORM = ENTITIES.register("fire_storm",
			() -> EntityType.Builder.<FireStormParent>of(FireStormParent::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("fire_storm"));

	public static final DeferredHolder<EntityType<?>,EntityType<FireStormEntity>> FIRE_STORM_CHILD = ENTITIES.register("fire_storm_child",
			() -> EntityType.Builder.<FireStormEntity>of(FireStormEntity::new, MobCategory.MISC)
					.sized(0.6f, 0.6f)
					.build("fire_storm_child"));

	public static final DeferredHolder<EntityType<?>,EntityType<TrashParasites>> PARASITES = ENTITIES.register("vomit_parasites",
			() -> EntityType.Builder.<TrashParasites>of(TrashParasites::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("vomit_parasites"));
	public static final DeferredHolder<EntityType<?>,EntityType<TrashPoison>> VOMIT = ENTITIES.register("vomit_poison",
			() -> EntityType.Builder.<TrashPoison>of(TrashPoison::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("vomit_poison"));

	public static final DeferredHolder<EntityType<?>,EntityType<CrystalShardEntity>> CRYSTAL_SHARD = ENTITIES.register("crystal_shard",
			() -> EntityType.Builder.<CrystalShardEntity>of(CrystalShardEntity::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("crystal_shard"));

	public static final DeferredHolder<EntityType<?>,EntityType<CrystalRain>> CRYSTAL_RAIN = ENTITIES.register("crystal_rain",
			() -> EntityType.Builder.<CrystalRain>of(CrystalRain::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("crystal_rain"));

	public static final DeferredHolder<EntityType<?>,EntityType<WindSlash>> WIND_SLASH = ENTITIES.register("wind_slash",
			() -> EntityType.Builder.<WindSlash>of(WindSlash::new, MobCategory.MISC)
					.sized(1.2f, 1.2f)
					.build("wind_slash"));
	public static final DeferredHolder<EntityType<?>,EntityType<MoonlightSlash>> MOONLIGHT_WAVE = ENTITIES.register("moonlight_wave",
			() -> EntityType.Builder.<MoonlightSlash>of(MoonlightSlash::new, MobCategory.MISC)
					.sized(1.8f, 1.2f)
					.build("moonlight_wave"));
	public static final DeferredHolder<EntityType<?>,EntityType<ThrowingKnifeEntity>> THROWING_KNIFE = ENTITIES.register("throwing_knife",
			() -> EntityType.Builder.<ThrowingKnifeEntity>of(ThrowingKnifeEntity::new, MobCategory.MISC)
					.sized(0.4f, 0.4f)
					.build("throwing_knife"));
	public static final DeferredHolder<EntityType<?>,EntityType<FirebombEntity>> FIREBOMB = ENTITIES.register("firebomb",
			() -> EntityType.Builder.<FirebombEntity>of(FirebombEntity::new, MobCategory.MISC)
					.sized(0.6f, 0.6f)
					.build("firebomb"));
	public static final DeferredHolder<EntityType<?>,EntityType<MolotovCocktailEntity>> MOLOTOV = ENTITIES.register("molotov",
			() -> EntityType.Builder.<MolotovCocktailEntity>of(MolotovCocktailEntity::new, MobCategory.MISC)
					.sized(0.6f, 0.8f)
					.build("molotov"));
	public static final DeferredHolder<EntityType<?>,EntityType<Bullet>> BULLET = ENTITIES.register("bullet",
			() -> EntityType.Builder.<Bullet>of(Bullet::new, MobCategory.MISC)
					.sized(0.2f, 0.2f)
					.build("bullet"));

	public static final DeferredHolder<EntityType<?>,EntityType<Flame>> FLAME = ENTITIES.register("flame",
			() -> EntityType.Builder.<Flame>of(Flame::new, MobCategory.MISC)
					.sized(0.4f, 0.4f)
					.build("flame"));

	public static final DeferredHolder<EntityType<?>,EntityType<ArcaneBullet>> ARCANE_BULLET = ENTITIES.register("arcane_bullet",
			() -> EntityType.Builder.<ArcaneBullet>of(ArcaneBullet::new, MobCategory.MISC)
					.sized(0.25f, 0.25f)
					.build("arcane_bullet"));

}
