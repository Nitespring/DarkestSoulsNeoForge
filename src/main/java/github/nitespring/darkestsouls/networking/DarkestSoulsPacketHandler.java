package github.nitespring.darkestsouls.networking;





import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.PacketDistributor;


import java.util.function.Consumer;


public class DarkestSoulsPacketHandler {


	public static void onRegisterPayloadHandler(RegisterPayloadHandlerEvent event) {
		final IPayloadRegistrar registrar = event.registrar(DarkestSouls.MODID)
				.versioned("1.0")
				.optional();
		registrar.play(ItemLeftClickAction.ID, ItemLeftClickAction::create, handler -> handler
				.server(ItemLeftClickAction::handle));
		registrar.play(TransformWeaponAction.ID, TransformWeaponAction::create, handler -> handler
				.server(TransformWeaponAction::handle));
	}

	public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
		PacketDistributor.SERVER.noArg().send(message);
	}

	public static <MSG extends CustomPacketPayload> void sendToPlayer(MSG message, ServerPlayer player) {
		PacketDistributor.PLAYER.with(player).send(message);
	}
}

