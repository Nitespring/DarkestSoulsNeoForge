package github.nitespring.darkestsouls.networking;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.common.item.Weapon;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record  ItemLeftClickAction() implements CustomPacketPayload {

	//public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "attack");
	public static final CustomPacketPayload.Type<ItemLeftClickAction> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "attack"));
	public static final ItemLeftClickAction INSTANCE = new ItemLeftClickAction();

	public static final StreamCodec<ByteBuf,ItemLeftClickAction> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	/*public void handleData(final ItemLeftClickAction data, final IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			Player player = ctx.player();
				if (player==null)
					return;
				ItemStack mainHand = player.getMainHandItem();

				if(mainHand.getItem() instanceof ILeftClickItem) {
					if (player.getAttackStrengthScale(0)>=0.8) {
						((ILeftClickItem)mainHand.getItem()).doLeftClickAction(player, mainHand);
					}
				}
		}).exceptionally(e -> {
			// Handle exception
			ctx.disconnect(Component.translatable(DarkestSouls.MODID + ".networking.failed", e.getMessage()));
			return null;
		});;
	}*/


	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public class ServerPayloadHandler {

		public static void handleData(final ItemLeftClickAction data, final IPayloadContext ctx) {
			ctx.enqueueWork(() -> {
						Player player = ctx.player();
						if (player==null)
							return;
						ItemStack mainHand = player.getMainHandItem();

						if(mainHand.getItem() instanceof ILeftClickItem) {
							if (player.getAttackStrengthScale(0)>=0.8) {
								((ILeftClickItem)mainHand.getItem()).doLeftClickAction(player, mainHand);
							}
						}
					})
					.exceptionally(e -> {
						ctx.disconnect(Component.translatable(DarkestSouls.MODID + ".networking.failed", e.getMessage()));
						return null;
					});
		}

	}
}

