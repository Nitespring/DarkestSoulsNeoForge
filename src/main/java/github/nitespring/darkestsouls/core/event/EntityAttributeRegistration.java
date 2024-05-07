package github.nitespring.darkestsouls.core.event;


import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.util.EntityAttributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;


@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

	}

}
