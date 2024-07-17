package github.nitespring.darkestsouls.core.event;


import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.util.SpawnRules;
import net.minecraft.world.entity.SpawnPlacementTypes;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;


@EventBusSubscriber(modid = DarkestSouls.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegistration {

    @SubscribeEvent
    public static void registerEntitySpawn(RegisterSpawnPlacementsEvent event) {
        event.register(EntityInit.BEAST_PATIENT.get(),
                SpawnPlacementTypes.ON_GROUND, 
                Types.MOTION_BLOCKING_NO_LEAVES, 
                SpawnRules::checkBeastPatientSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.CLOAKED_BEAST_PATIENT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkCloakedBeastPatientSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkAshenBloodBeastPatientSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkMadHollowSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkGravetenderHollowSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkGravetenderHollowSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.GRAVETENDER_HOLLOW_CROSSBOW.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkGravetenderHollowSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.HOLLOW_LONGSWORD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkHollowLongswordSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.HOLLOW_AXE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkHollowAxeSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.HOLLOW_ASSASSIN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkHollowAssassinSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.HOLLOW_CROSSBOW.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkHollowCrossbowSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.SKELETON_FALCHION.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkSkeletonFalchionSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.SKELETON_CURVED_SWORDS.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkSkeletonCurvedSwordsSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.SKELETON_SPEAR.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkSkeletonSpearSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.BONEWHEEL.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkBonewheelSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.TALL_SKELETON_TWIN_SHOTELS.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkSkeletonSwordsmanTwinShotelsSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.CHURCH_DOCTOR.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkChurchDoctorNormalSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.CHURCH_DOCTOR_LANTERN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkChurchDoctorNormalSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.CHURCH_DOCTOR_SCYTHE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkChurchDoctorScytheSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkChurchDoctorCrucifixSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.CHURCH_DOCTOR_PISTOL.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkChurchDoctorPistolSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkChurchDoctorFlamesprayerSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.SEWER_CENTIPEDE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkSewerCentipedeSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.LEECH.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkLeechSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.SIN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkSinSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EntityInit.DARKWRAITH.get(),
                SpawnPlacementTypes.ON_GROUND,
                Types.MOTION_BLOCKING_NO_LEAVES,
                SpawnRules::checkDarkwraithSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

}
