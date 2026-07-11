package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.core.init.KeybindInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public abstract class DualWieldingWeaponSecondary extends DualWieldingWeaponChild{



    public DualWieldingWeaponSecondary(Tier tier, float attack, float speed, float reach, float knockback, int poise, int posture, int blood, int poison, int frost, int rot, int death, int fire, int holy, int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, posture, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if(entity instanceof Player player){

            if(player.getItemInHand(InteractionHand.MAIN_HAND)==stack){
                if(!player.getItemInHand(InteractionHand.OFF_HAND).is(getTwinWeapon())) {
                    stack.setCount(0);
                }
            }else if(player.getItemInHand(InteractionHand.OFF_HAND)==stack){
                if(!player.getItemInHand(InteractionHand.MAIN_HAND).is(getTwinWeapon())) {
                    stack.setCount(0);
                }
            }
            }
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag p_41424_) {


        String colour = "\u00A78\u00A7o";

        tooltip.add(Component.translatable("translation.darkestsouls.trick1").append(Component.translatable(KeybindInit.TRICK_KEYBIND.get().getKey().getName())).append(Component.translatable("translation.darkestsouls.trick2")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));



        super.appendHoverText(stack, context, tooltip, p_41424_);
    }

}
