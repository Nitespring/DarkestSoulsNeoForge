package github.nitespring.darkestsouls.core.event;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.mob.DogGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.DarkwraithGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.LeechGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.MonstruosityOfSinGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.SewerCentipedeGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.beast.BeastPatientGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.church.doctor.ChurchDoctorGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.church.huntsman.HuntsmanGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.demon.CapraDemonGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.hollow.HollowGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.kin.NightmareApostleGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.skeleton.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.bullet.BulletModel;
import github.nitespring.darkestsouls.client.render.entity.projectile.bullet.BulletRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.spell.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.throwable.FirebombRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.throwable.MolotovCocktailModel;
import github.nitespring.darkestsouls.client.render.entity.projectile.throwable.MolotovCocktailRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeFlameModel;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeFlameRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeRenderer;
import github.nitespring.darkestsouls.client.render.equipment.armour.*;
import github.nitespring.darkestsouls.client.render.item.gun.GatlingGunGeoRenderer;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.common.item.Shield;
import github.nitespring.darkestsouls.common.item.child.guns.GatlingGun;
import github.nitespring.darkestsouls.common.item.child.guns.Shotgun;
import github.nitespring.darkestsouls.core.ClientItemExtensions;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderArmEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.client.extensions.common.ClientExtensionsManager;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.lwjgl.opengl.ARBTextureCubeMapArray;


@EventBusSubscriber(modid = DarkestSouls.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {





	public static final ModelLayerLocation SQUARE_TEXTURE = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "square_texture"), "main");
	public static final ModelLayerLocation FRAYED_BLADE_FLAME = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "frayed_blade_fire"), "main");
	public static final ModelLayerLocation BULLET = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "bullet"), "main");
	public static final ModelLayerLocation MOLOTOV = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "molotov"), "main");

	public static final ModelLayerLocation TRICORN_OUTER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "hunter_tricorn"), "outer");
	public static final ModelLayerLocation TRICORN_INNER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "hunter_tricorn"), "inner");
	public static final ModelLayerLocation TOP_HAT_OUTER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "alchemist_top_hat"), "outer");
	public static final ModelLayerLocation TOP_HAT_INNER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "alchemist_top_hat"), "inner");
	public static final ModelLayerLocation SPECIALIST_OUTER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "specialist_armour"), "outer");
	public static final ModelLayerLocation SPECIALIST_INNER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "specialist_armour"), "inner");
	public static final ModelLayerLocation TATTERED_WIZARD_ROBE = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "tattered_wizard_robe"), "main");
	public static final ModelLayerLocation WIZARD_ROBE = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "wizard_robe"), "outer");
	public static final ModelLayerLocation WIZARD_ROBE_INNER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "wizard_robe"), "inner");
	public static final ModelLayerLocation ELITE_KNIGHT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "elite_knight"), "main");
	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){

		event.registerLayerDefinition(SQUARE_TEXTURE, SquareTextureEntityModel::createBodyLayer);
		event.registerLayerDefinition(FRAYED_BLADE_FLAME, FrayedBladeFlameModel::createBodyLayer);
		event.registerLayerDefinition(BULLET, BulletModel::createBodyLayer);
		event.registerLayerDefinition(MOLOTOV, MolotovCocktailModel::createBodyLayer);
		event.registerLayerDefinition(TRICORN_INNER, HunterTricornModel::createInnerLayer);
		event.registerLayerDefinition(TRICORN_OUTER, HunterTricornModel::createOuterLayer);
		event.registerLayerDefinition(TOP_HAT_INNER, AlchemistTopHatModel::createInnerLayer);
		event.registerLayerDefinition(TOP_HAT_OUTER, AlchemistTopHatModel::createOuterLayer);
		event.registerLayerDefinition(SPECIALIST_INNER, SpecialistArmourModel::createInnerLayer);
		event.registerLayerDefinition(SPECIALIST_OUTER, SpecialistArmourModel::createOuterLayer);
		event.registerLayerDefinition(TATTERED_WIZARD_ROBE, TatteredWizardRobeModel::createBodyLayer);
		event.registerLayerDefinition(WIZARD_ROBE, WizardRobeModel::createOuterLayer);
		event.registerLayerDefinition(WIZARD_ROBE_INNER, WizardRobeModel::createInnerLayer);
		event.registerLayerDefinition(ELITE_KNIGHT, EliteKnightArmourModel::createBodyLayer);

	}



	 @SubscribeEvent
	 	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		 
		 event.registerEntityRenderer(EntityInit.SIN.get(), MonstruosityOfSinGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.BONEWHEEL.get(), BonewheelGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SEWER_CENTIPEDE.get(), SewerCentipedeGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SKELETON_FALCHION.get(), SkeletonFalchionGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SKELETON_CURVED_SWORDS.get(), SkeletonCurvedSwordsGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SKELETON_SPEAR.get(), SkeletonSpearGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.TALL_SKELETON_TWIN_SHOTELS.get(), TallSkeletonGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_LONGSWORD.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_AXE.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_ASSASSIN.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_CROSSBOW.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.GRAVETENDER_HOLLOW_CROSSBOW.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.BEAST_PATIENT.get(), BeastPatientGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CLOAKED_BEAST_PATIENT.get(), BeastPatientGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), BeastPatientGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.LEECH.get(), LeechGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_LANTERN.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_PISTOL.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.DARKWRAITH.get(), DarkwraithGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTSMAN_AXE.get(), HuntsmanGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTSMAN_CUTLASS.get(), HuntsmanGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTSMAN_PITCHFORK.get(), HuntsmanGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTSMAN_RIFLE.get(), HuntsmanGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SPIDER.get(), NightmareApostleGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.NIGHTMARE_APOSTLE.get(), NightmareApostleGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.LARGE_SPIDER.get(), NightmareApostleGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.LARGE_NIGHTMARE_APOSTLE.get(), NightmareApostleGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.RABID_DOG.get(), DogGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SILVER_DOG.get(), DogGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTING_DOG.get(), DogGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_DOG.get(), DogGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.LARGE_HOLLOW_DOG.get(), DogGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.UNDEAD_DOG.get(), DogGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.LARGE_UNDEAD_DOG.get(), DogGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CAPRA_DEMON.get(), CapraDemonGeoRenderer::new);

		 event.registerEntityRenderer(EntityInit.HITBOX_SMALL.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.HITBOX.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.HITBOX_LARGE.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.FRAYED_BLADE.get(), FrayedBladeRenderer::new);
		 event.registerEntityRenderer(EntityInit.FRAYED_BLADE_FLAME.get(), FrayedBladeFlameRenderer::new);
		 event.registerEntityRenderer(EntityInit.SCIMITAR.get(), ScimitarRenderer::new);
		 event.registerEntityRenderer(EntityInit.FALCHION.get(), FalchionRenderer::new);
		 event.registerEntityRenderer(EntityInit.CLAYMORE.get(), ClaymoreRenderer::new);
		 event.registerEntityRenderer(EntityInit.FLAMBERGE.get(), FlambergeRenderer::new);
		 event.registerEntityRenderer(EntityInit.ZWEIHANDER.get(), ZweihanderRenderer::new);
		 event.registerEntityRenderer(EntityInit.UCHIGATANA.get(), UchigatanaRenderer::new);
		 event.registerEntityRenderer(EntityInit.LONGSWORD.get(), LongswordRenderer::new);
		 event.registerEntityRenderer(EntityInit.BROKEN_STRAIGHTSWORD.get(), BrokenStraightswordRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHIKAGE.get(), ChikageRenderer::new);
		 event.registerEntityRenderer(EntityInit.SAW_CLEAVER.get(), SawCleaverRenderer::new);
		 event.registerEntityRenderer(EntityInit.SAW_CLEAVER_EXTENDED.get(), SawCleaverExtendedRenderer::new);
		 event.registerEntityRenderer(EntityInit.GRAVE_SCYTHE.get(), GraveScytheRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_SCYTHE.get(), ChurchScytheRenderer::new);
		 event.registerEntityRenderer(EntityInit.SHADOW_BLADE.get(), ShadowBladeRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTER_AXE_EXTENDED.get(), HunterAxeExtendedRenderer::new);
		 event.registerEntityRenderer(EntityInit.SPEAR.get(), SpearRenderer::new);
		 event.registerEntityRenderer(EntityInit.DRAGONSLAYER_SWORDSPEAR.get(), DragonslayerSwordspearRenderer::new);
		 event.registerEntityRenderer(EntityInit.DRAGONSLAYER_SPEAR.get(), DragonslayerSpearRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTER_AXE.get(), HunterAxeRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTING_AXE.get(), HuntingAxeRenderer::new);
		 event.registerEntityRenderer(EntityInit.GREATAXE.get(), GreataxeRenderer::new);
		 event.registerEntityRenderer(EntityInit.CURVED_GREATSWORD.get(), CurvedGreatswordRenderer::new);
		 event.registerEntityRenderer(EntityInit.SHOTEL.get(), ShotelRenderer::new);
		 event.registerEntityRenderer(EntityInit.BANDIT_KNIFE.get(), BanditKnifeRenderer::new);
		 event.registerEntityRenderer(EntityInit.GREATSWORD.get(), GreatswordRenderer::new);
		 event.registerEntityRenderer(EntityInit.DARKSWORD.get(), DarkSwordRenderer::new);



		 event.registerEntityRenderer(EntityInit.SOUL_DART.get(), SoulDartRenderer::new);
		 event.registerEntityRenderer(EntityInit.SOUL_ARROW.get(), SoulDartRenderer::new);
		 event.registerEntityRenderer(EntityInit.FIREBALL.get(), FireBallRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHAOS_FIREBALL.get(), FireBallRenderer::new);
		 event.registerEntityRenderer(EntityInit.MAGMA.get(), MagmaRenderer::new);
		 event.registerEntityRenderer(EntityInit.MAGMA_BURST.get(), LitItemRenderer::new);
		 event.registerEntityRenderer(EntityInit.MAGMA_BURST_CHILD.get(), LitItemRenderer::new);
		 event.registerEntityRenderer(EntityInit.LIGHTNING_SPEAR.get(), LightningBoltRenderer::new);
		 event.registerEntityRenderer(EntityInit.PARASITES.get(), ThrownItemRenderer::new);
		 event.registerEntityRenderer(EntityInit.VOMIT.get(), ThrownItemRenderer::new);
		 event.registerEntityRenderer(EntityInit.CRYSTAL_SHARD.get(), CrystalShardRenderer::new);
		 event.registerEntityRenderer(EntityInit.CRYSTAL_RAIN.get(), CrystalBallRenderer::new);
		 event.registerEntityRenderer(EntityInit.WIND_SLASH.get(), WindSlashRenderer::new);
		 event.registerEntityRenderer(EntityInit.THROWING_KNIFE.get(), (EntityRendererProvider.Context context) -> new DirectionalAsItemRenderer(context));
		 event.registerEntityRenderer(EntityInit.FIREBOMB.get(), FirebombRenderer::new);
		 event.registerEntityRenderer(EntityInit.MOLOTOV.get(), MolotovCocktailRenderer::new);
		 event.registerEntityRenderer(EntityInit.BULLET.get(), BulletRenderer::new);
		 event.registerEntityRenderer(EntityInit.FLAME.get(), FlameRenderer::new);
		 event.registerEntityRenderer(EntityInit.MOONLIGHT_WAVE.get(), MoonlightSlashRenderer::new);
		 event.registerEntityRenderer(EntityInit.ARCANE_BULLET.get(), ArcaneBulletRenderer::new);

		 event.registerEntityRenderer(EntityInit.SKELETON_GROUP1.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTSMAN_GROUP1.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_SOLDIER_GROUP1.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.GRAVETENDER_HOLLOW_GROUP1.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_GROUP1.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.BEAST_PATIENT_GROUP1.get(), InvisibleProjectileRenderer::new);

	 }


	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event){
		event.registerItem(ClientItemExtensions.GUN_ITEM_EXTENSIONS, ItemInit.HUNTER_PISTOL);
		event.registerItem(ClientItemExtensions.GUN_ITEM_EXTENSIONS, ItemInit.REPEATING_PISTOL);
		event.registerItem(ClientItemExtensions.GUN_ITEM_EXTENSIONS, ItemInit.EVELYN);
		event.registerItem(ClientItemExtensions.GUN_ITEM_EXTENSIONS, ItemInit.MUSKET);
		event.registerItem(ClientItemExtensions.BLUNDERBUSS_ITEM_EXTENSIONS, ItemInit.BLUNDERBUSS);
		event.registerItem(ClientItemExtensions.SHIELD_ITEM_EXTENSIONS, ItemInit.IRON_ROUND_SHIELD);
		event.registerItem(ClientItemExtensions.SHIELD_ITEM_EXTENSIONS, ItemInit.SMALL_ROUND_SHIELD);

	}

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(
					ItemInit.SMALL_ROUND_SHIELD.get(),
					ResourceLocation.withDefaultNamespace("blocking"),
					(stack, level, player, seed) -> player != null
							&& player.isUsingItem()
							&& player.getUseItem() == stack
							 ? 1.0F : 0.0F
			);
			ItemProperties.register(
					ItemInit.IRON_ROUND_SHIELD.get(),
					ResourceLocation.withDefaultNamespace("blocking"),
					(stack, level, player, seed) -> player != null
							&& player.isUsingItem()
							&& player.getUseItem() == stack
							? 1.0F : 0.0F
			);
		});
	}


}
