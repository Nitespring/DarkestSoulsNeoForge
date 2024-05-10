package github.nitespring.darkestsouls;

import com.mojang.logging.LogUtils;
import github.nitespring.darkestsouls.config.Config;
import github.nitespring.darkestsouls.core.init.*;
import github.nitespring.darkestsouls.networking.DarkestSoulsPacketHandler;
import net.neoforged.api.distmarker.Dist;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(DarkestSouls.MODID)
public class DarkestSouls
{
    public static final String MODID = "darkestsouls";

    public static final Logger LOGGER = LogUtils.getLogger();

    public DarkestSouls(IEventBus modEventBus, ModContainer modContainer)
    {
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);
        //IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.common_config);
        //modEventBus.addListener(this::setup);

        modEventBus.addListener(DarkestSoulsPacketHandler::onRegisterPayloadHandler);
        GeckoLib.initialize(modEventBus);
        SoundInit.SOUNDS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        EntityInit.ENTITIES.register(modEventBus);
        EffectInit.EFFECTS.register(modEventBus);
        CreativeTabInit.TABS.register(modEventBus);
        EnchantmentInit.ENCHANTMENTS.register(modEventBus);
        //NeoForge.EVENT_BUS.register(this);

        
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    /*private void setup(final FMLCommonSetupEvent event)
    {
        DarkestSoulsPacketHandler.register();
    }*/
/*
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }*/
}
