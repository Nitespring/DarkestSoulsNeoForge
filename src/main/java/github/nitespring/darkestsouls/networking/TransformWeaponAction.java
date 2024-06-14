package github.nitespring.darkestsouls.networking;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.common.item.ITransformableItem;
import github.nitespring.darkestsouls.common.item.TrickWeapon;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record TransformWeaponAction() implements CustomPacketPayload{

	//public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "attack");
	public static final CustomPacketPayload.Type<TransformWeaponAction> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "transform"));
	public static final TransformWeaponAction INSTANCE = new TransformWeaponAction();

	public static final StreamCodec<ByteBuf,TransformWeaponAction> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public class ServerPayloadHandler {

		public static void handleData(final TransformWeaponAction data, final IPayloadContext ctx) {
			ctx.enqueueWork(() -> {
						Player player = ctx.player();
						if (player==null)
							return;
						ItemStack mainHand = player.getMainHandItem();

						if(mainHand.getItem() instanceof ITransformableItem) {
							((ITransformableItem)mainHand.getItem()).transform(player, player.level());
						}
					})
					.exceptionally(e -> {
						ctx.disconnect(Component.translatable(DarkestSouls.MODID + ".networking.failed", e.getMessage()));
						return null;
					});
		}

	}
}

