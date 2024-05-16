package github.nitespring.darkestsouls.networking;





import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


import java.util.function.Consumer;

//@EventBusSubscriber(modid = DarkestSouls.MODID)
public class DarkestSoulsPacketHandler {

	//@SubscribeEvent
	public static void onRegisterPayloadHandler(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(DarkestSouls.MODID)
				.versioned("1.0")
				.optional();
		registrar.playToServer(
				ItemLeftClickAction.TYPE,
				ItemLeftClickAction.STREAM_CODEC,
				ItemLeftClickAction.ServerPayloadHandler::handleData);
		registrar.playToServer(
				TransformWeaponAction.TYPE,
				TransformWeaponAction.STREAM_CODEC,
				TransformWeaponAction.ServerPayloadHandler::handleData);
	}

	public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
		PacketDistributor.sendToServer(message);
	}

	public static <MSG extends CustomPacketPayload> void sendToPlayer(MSG message, ServerPlayer player) {
		PacketDistributor.sendToPlayer(player, message);
	}


}

