package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


//@Mod.EventBusSubscriber(modid = DarkestSouls.MODID)
public class SoundInit {


    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT,
            DarkestSouls.MODID);


    public static final DeferredHolder<SoundEvent,SoundEvent> BEAST_PATIENT_IDLE = build("entity.beast_patient_idle");
    public static final DeferredHolder<SoundEvent,SoundEvent> BEAST_PATIENT_HURT = build("entity.beast_patient_hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> BEAST_PATIENT_ATTACK = build("entity.beast_patient_attack");
    public static final DeferredHolder<SoundEvent,SoundEvent> BEAST_PATIENT_SCREAM = build("entity.beast_patient_scream");

    public static final DeferredHolder<SoundEvent,SoundEvent> SIN_IDLE = build("entity.monstruosity_of_sin_idle");
    public static final DeferredHolder<SoundEvent,SoundEvent> SIN_HURT = build("entity.monstruosity_of_sin_hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> SIN_SCREAM = build("entity.monstruosity_of_sin_scream");
    public static final DeferredHolder<SoundEvent,SoundEvent> SIN_BOOM = build("entity.monstruosity_of_sin_boom");
    public static final DeferredHolder<SoundEvent,SoundEvent> SIN_DEATH = build("entity.monstruosity_of_sin_death");

    public static final DeferredHolder<SoundEvent,SoundEvent> HOLLOW_IDLE = build("entity.hollow_idle");
    public static final DeferredHolder<SoundEvent,SoundEvent> HOLLOW_HURT = build("entity.hollow_hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> HOLLOW_ATTACK = build("entity.hollow_attack");
    public static final DeferredHolder<SoundEvent,SoundEvent> HOLLOW_DEATH = build("entity.hollow_death");

    public static final DeferredHolder<SoundEvent,SoundEvent> SEWER_CENTIPEDE_IDLE = build("entity.sewer_centipede_idle");
    public static final DeferredHolder<SoundEvent,SoundEvent> SEWER_CENTIPEDE_DEATH = build("entity.sewer_centipede_death");
    public static final DeferredHolder<SoundEvent,SoundEvent> SEWER_CENTIPEDE_HURT = build("entity.sewer_centipede_hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> SEWER_CENTIPEDE_STEP = build("entity.sewer_centipede_step");

    public static final DeferredHolder<SoundEvent,SoundEvent> DARKWRAITH_IDLE = build("entity.darkwraith_ambient");
    public static final DeferredHolder<SoundEvent,SoundEvent> DARKWRAITH_ATTACK = build("entity.darkwraith_attack");
    public static final DeferredHolder<SoundEvent,SoundEvent> DARKWRAITH_HURT = build("entity.darkwraith_hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> DARKWRAITH_DEATH = build("entity.darkwraith_death");

    private static DeferredHolder<SoundEvent,SoundEvent> build(String id)
    {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, id)));

    }


}