package github.nitespring.darkestsouls.networking;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.common.item.ITransformableItem;
import github.nitespring.darkestsouls.common.item.TrickWeapon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;


public class TransformWeaponAction implements CustomPacketPayload{

	public static final ResourceLocation ID = new ResourceLocation(DarkestSouls.MODID, "transform");

	public static TransformWeaponAction create(FriendlyByteBuf buf) {
		return new TransformWeaponAction();
	}

	public static TransformWeaponAction create() {
		return new TransformWeaponAction();
	}

	@Override
	public void write(FriendlyByteBuf buf) {
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public void handle(PlayPayloadContext ctx) {
		ctx.workHandler().submitAsync(() -> {
			ctx.player().ifPresent(player -> {
				if (player==null)
					return;
				ItemStack mainHand = player.getMainHandItem();
				if(mainHand.getItem() instanceof ITransformableItem) {
					((ITransformableItem)mainHand.getItem()).transform(player, player.level());
				}
			});
		});
	}
	      
}

