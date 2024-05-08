package github.nitespring.darkestsouls.networking;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.common.item.Weapon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;


public class ItemLeftClickAction implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(DarkestSouls.MODID, "hit");

	public static ItemLeftClickAction create(FriendlyByteBuf buf) {
		return new ItemLeftClickAction();
	}

	public static ItemLeftClickAction create() {
		return new ItemLeftClickAction();
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

				if(mainHand.getItem() instanceof ILeftClickItem) {
					if (player.getAttackStrengthScale(0)>=0.8) {
						((ILeftClickItem)mainHand.getItem()).doLeftClickAction(player, mainHand);
					}
				}
			});
		});
	}


	      
}

