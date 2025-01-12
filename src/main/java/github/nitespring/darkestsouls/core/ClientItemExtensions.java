package github.nitespring.darkestsouls.core;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

public class ClientItemExtensions {

    public static final IClientItemExtensions SHIELD_ITEM_EXTENSIONS = new IClientItemExtensions() {

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
    public static final IClientItemExtensions BLUNDERBUSS_ITEM_EXTENSIONS = new IClientItemExtensions() {

        @Override
        public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player,
                                               HumanoidArm arm, ItemStack itemInHand,
                                               float partialTick, float equipProcess, float swingProcess) {
            if(player.isUsingItem()&&player.getUseItem()==itemInHand&&
                    player.getUsedItemHand()==InteractionHand.MAIN_HAND){

                poseStack.translate(-0.4,0.3,0);

                poseStack.mulPose(Axis.ZP.rotationDegrees(0));
                poseStack.mulPose(Axis.XP.rotationDegrees(-20));

            }
            return IClientItemExtensions.super.applyForgeHandTransform(poseStack,player,arm,itemInHand,
                    partialTick,equipProcess,swingProcess);
        }
    };

    public static final IClientItemExtensions GUN_ITEM_EXTENSIONS = new IClientItemExtensions() {

        @Nullable
        @Override
        public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
            return entityLiving.isUsingItem()&&entityLiving.getUseItem()==itemStack&&
                    entityLiving.getUsedItemHand()==InteractionHand.MAIN_HAND&&!entityLiving.swinging ? HumanoidModel.ArmPose.CROSSBOW_HOLD : HumanoidModel.ArmPose.ITEM;
        }

        @Override
        public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player,
                                               HumanoidArm arm, ItemStack itemInHand,
                                               float partialTick, float equipProcess, float swingProcess) {
            if(player.isUsingItem()&&player.getUseItem()==itemInHand&&
                    player.getUsedItemHand()==InteractionHand.MAIN_HAND){

                poseStack.translate(-0.5,0.3,0);
                poseStack.mulPose(Axis.ZP.rotationDegrees(5));
                poseStack.mulPose(Axis.XP.rotationDegrees(-20));

            }
            return IClientItemExtensions.super.applyForgeHandTransform(poseStack,player,arm,itemInHand,
                    partialTick,equipProcess,swingProcess);
        }
    };

}
