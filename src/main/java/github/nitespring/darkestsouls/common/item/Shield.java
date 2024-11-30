package github.nitespring.darkestsouls.common.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

public class Shield extends ShieldItem {
    public Shield(Properties properties) {
        super(properties);
    }


    public static final IClientItemExtensions SHIELD_ITEM_EXTENSIONS = new IClientItemExtensions() {
        @Nullable
        @Override
        public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
            return entityLiving.isUsingItem()&&entityLiving.getUseItem()==itemStack ? HumanoidModel.ArmPose.BLOCK : HumanoidModel.ArmPose.ITEM;
        }
        @Override
        public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player,
                                               HumanoidArm arm, ItemStack itemInHand,
                                               float partialTick, float equipProcess, float swingProcess) {
            if(player.isUsingItem()&&player.getUseItem()==itemInHand){
                if(player.getUsedItemHand()==InteractionHand.MAIN_HAND){
                    poseStack.translate(-0.15,0.25f,0.1f);
                }else if(player.getUsedItemHand()==InteractionHand.OFF_HAND){
                    poseStack.translate(0.15,0.25f,0.1f);
                }
                poseStack.scale(1.4f,1.4f,1.4f);
            }
            return IClientItemExtensions.super.applyForgeHandTransform(poseStack,player,arm,itemInHand,
                    partialTick,equipProcess,swingProcess);
        }
    };


}
