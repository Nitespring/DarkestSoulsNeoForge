package github.nitespring.darkestsouls.core.event;


import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.util.EntityAttributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;


@EventBusSubscriber(modid = DarkestSouls.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributeRegistration {
	
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		

		event.put(EntityInit.SIN.get(), EntityAttributes.setMonstruosityOfSinAttributes().build());
		event.put(EntityInit.BONEWHEEL.get(), EntityAttributes.setBonewheelAttributes().build());
		event.put(EntityInit.SEWER_CENTIPEDE.get(), EntityAttributes.setSewerCentipedeAttributes().build());
		event.put(EntityInit.SKELETON_FALCHION.get(), EntityAttributes.setSkeletonFalchionAttributes().build());
		event.put(EntityInit.SKELETON_CURVED_SWORDS.get(), EntityAttributes.setSkeletonCurvedSwordsAttributes().build());
		event.put(EntityInit.SKELETON_SPEAR.get(), EntityAttributes.setSkeletonSpearAttributes().build());
		event.put(EntityInit.TALL_SKELETON_TWIN_SHOTELS.get(), EntityAttributes.setSkeletonSwordsmanTwinShotelsAttributes().build());
		event.put(EntityInit.HOLLOW_LONGSWORD.get(), EntityAttributes.setHollowSoldierLongswordAttributes().build());
		event.put(EntityInit.HOLLOW_AXE.get(), EntityAttributes.setHollowSoldierAxeAttributes().build());
		event.put(EntityInit.HOLLOW_ASSASSIN.get(), EntityAttributes.setHollowAssassinAttributes().build());
		event.put(EntityInit.GRAVETENDER_HOLLOW_CROSSBOW.get(), EntityAttributes.setGravetenderHollowCrossbowAttributes().build());
		event.put(EntityInit.HOLLOW_CROSSBOW.get(), EntityAttributes.setHollowSoldierCrossbowAttributes().build());
		event.put(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(), EntityAttributes.setMadHollowBrokenStraightswordAttributes().build());
		event.put(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(), EntityAttributes.setGravetenderHollowLongswordAttributes().build());
		event.put(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(), EntityAttributes.setGravetenderHollowBrokenStraightswordAttributes().build());
		event.put(EntityInit.BEAST_PATIENT.get(), EntityAttributes.setBeastPatientAttributes().build());
		event.put(EntityInit.CLOAKED_BEAST_PATIENT.get(), EntityAttributes.setCloakedBeastPatientAttributes().build());
		event.put(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), EntityAttributes.setAshenBloodBeastPatientAttributes().build());
		event.put(EntityInit.LEECH.get(), EntityAttributes.setLeechAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR.get(), EntityAttributes.setChurchDoctorAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_LANTERN.get(), EntityAttributes.setChurchDoctorLanternAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_PISTOL.get(), EntityAttributes.setChurchDoctorPistolAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), EntityAttributes.setChurchDoctorFlamesprayerAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), EntityAttributes.setChurchDoctorScytheAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), EntityAttributes.setChurchDoctorCrucifixAttributes().build());
		event.put(EntityInit.DARKWRAITH.get(), EntityAttributes.setDarkwraithAttributes().build());
		event.put(EntityInit.HUNTSMAN_AXE.get(), EntityAttributes.setHuntsmanAxeAttributes().build());
		event.put(EntityInit.HUNTSMAN_CUTLASS.get(), EntityAttributes.setHuntsmanCutlassAttributes().build());
		event.put(EntityInit.HUNTSMAN_PITCHFORK.get(), EntityAttributes.setHuntsmanPitchforkAttributes().build());
		event.put(EntityInit.HUNTSMAN_RIFLE.get(), EntityAttributes.setHuntsmanRifleAttributes().build());
		event.put(EntityInit.SPIDER.get(), EntityAttributes.setSpiderAttributes().build());
		event.put(EntityInit.NIGHTMARE_APOSTLE.get(), EntityAttributes.setNightmareApostleAttributes().build());
		event.put(EntityInit.LARGE_SPIDER.get(), EntityAttributes.setLargeSpiderAttributes().build());
		event.put(EntityInit.LARGE_NIGHTMARE_APOSTLE.get(), EntityAttributes.setLargeNightmareApostleAttributes().build());
		event.put(EntityInit.RABID_DOG.get(), EntityAttributes.setRabidDogAttributes().build());
		event.put(EntityInit.HUNTING_DOG.get(), EntityAttributes.setHuntingDogAttributes().build());
		event.put(EntityInit.SILVER_DOG.get(), EntityAttributes.setSilverDogAttributes().build());
		event.put(EntityInit.HOLLOW_DOG.get(), EntityAttributes.setHollowDogAttributes().build());
		event.put(EntityInit.UNDEAD_DOG.get(), EntityAttributes.setUndeadDogAttributes().build());
		event.put(EntityInit.LARGE_HOLLOW_DOG.get(), EntityAttributes.setLargeHollowDogAttributes().build());
		event.put(EntityInit.LARGE_UNDEAD_DOG.get(), EntityAttributes.setLargeUndeadDogAttributes().build());
		event.put(EntityInit.CAPRA_DEMON.get(), EntityAttributes.setCapraDemonAttributes().build());


		event.put(EntityInit.HUNTSMAN_GROUP1.get(), SpawnGroupEntity.setAttributes().build());
		event.put(EntityInit.SKELETON_GROUP1.get(), SpawnGroupEntity.setAttributes().build());
		event.put(EntityInit.GRAVETENDER_HOLLOW_GROUP1.get(), SpawnGroupEntity.setAttributes().build());
		event.put(EntityInit.HOLLOW_SOLDIER_GROUP1.get(), SpawnGroupEntity.setAttributes().build());
		event.put(EntityInit.BEAST_PATIENT_GROUP1.get(), SpawnGroupEntity.setAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_GROUP1.get(), SpawnGroupEntity.setAttributes().build());

	}

}
