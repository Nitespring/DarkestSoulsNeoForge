package github.nitespring.darkestsouls.client.render.equipment.armour;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class HunterTricornModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    //private final ModelPart tail_back;
    public HunterTricornModel(ModelPart root) {
        super(root);
        //this.tail_back = root.getChild("tail_back");
    }

    public static LayerDefinition createOuterLayer() {
        MeshDefinition meshdefinition = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.75F));
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.getChild("head");
        PartDefinition body = partdefinition.getChild("body");
        PartDefinition right_arm = partdefinition.getChild("right_arm");
        PartDefinition left_arm = partdefinition.getChild("left_arm");
        PartDefinition right_leg = partdefinition.getChild("right_leg");
        PartDefinition left_leg = partdefinition.getChild("left_leg");

        head.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(96, 64).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition hat_part = head.addOrReplaceChild("hat_part", CubeListBuilder.create(), PartPose.offset(-1.0F, -6.25F, 1.75F));

        PartDefinition head_r1 = hat_part.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(64, 2).mirror().addBox(8.0F, -8.0F, -14.0F, 0.0F, 8.0F, 32.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2392F, 0.4274F, 0.0989F));

        PartDefinition head_r2 = hat_part.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(64, 2).addBox(-8.0F, -8.0F, -14.0F, 0.0F, 8.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.2392F, -0.4274F, -0.0989F));

        PartDefinition head_r3 = hat_part.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(44, 1).addBox(-10.0F, 0.0F, -10.0F, 28.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.3038F, -0.762F, -0.2132F));

        PartDefinition collar = body.addOrReplaceChild("collar", CubeListBuilder.create().texOffs(56, 48).addBox(-4.0F, -31.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(32, 43).addBox(-2.0F, -16.0F, -3.25F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.05F))
                .texOffs(0, 40).addBox(-5.0F, -15.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition tail_back_r1 = tail.addOrReplaceChild("tail_back_r1", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(0.1743F, -0.9924F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition tail_back_r2 = tail.addOrReplaceChild("tail_back_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-4.1743F, -0.9924F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition tail_back = tail.addOrReplaceChild("tail_back", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition tail_back_r3 = tail_back.addOrReplaceChild("tail_back_r3", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -0.9924F, -1.8257F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition left_boot_part = left_leg.addOrReplaceChild("left_boot_part", CubeListBuilder.create().texOffs(104, 56).addBox(-3.0F, 5.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.22F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_boot_part = right_leg.addOrReplaceChild("right_boot_part", CubeListBuilder.create().texOffs(104, 56).addBox(-3.2F, 5.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.22F)), PartPose.offset(0.0F, 0.0F, 0.0F));


        PartDefinition left_glove = left_arm.addOrReplaceChild("left_glove", CubeListBuilder.create().texOffs(112, 47).mirror().addBox(-1.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.7F)).mirror(false)
                .texOffs(89, 55).mirror().addBox(-1.0F, 7.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.9F)).mirror(false)
                .texOffs(95, 43).mirror().addBox(-1.0F, 5.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.8F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_arm_r1 = left_glove.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(80, 46).addBox(-0.5F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, 7.5F, 0.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition left_arm_r1 = left_glove.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(94, 49).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 7.5F, -2.75F, 0.0436F, 0.0F, 0.0F));

        PartDefinition left_arm_r2 = left_glove.addOrReplaceChild("left_arm_r2", CubeListBuilder.create().texOffs(94, 49).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 7.5F, 2.75F, -0.0436F, 0.0F, 0.0F));

        PartDefinition left_arm_r3 = left_glove.addOrReplaceChild("left_arm_r3", CubeListBuilder.create().texOffs(80, 46).mirror().addBox(-0.5F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.75F, 7.5F, 0.0F, 0.0F, 0.0F, 0.0436F));


        PartDefinition right_glove = right_arm.addOrReplaceChild("right_glove", CubeListBuilder.create().texOffs(112, 47).addBox(-3.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.7F))
                .texOffs(89, 55).addBox(-3.0F, 7.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.9F))
                .texOffs(95, 43).addBox(-3.0F, 5.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_arm_r4 = right_glove.addOrReplaceChild("left_arm_r4", CubeListBuilder.create().texOffs(80, 46).mirror().addBox(-0.5F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.75F, 7.5F, 0.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition right_arm_r2 = right_glove.addOrReplaceChild("right_arm_r2", CubeListBuilder.create().texOffs(94, 49).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 7.5F, -2.75F, 0.0436F, 0.0F, 0.0F));

        PartDefinition right_arm_r3 = right_glove.addOrReplaceChild("right_arm_r3", CubeListBuilder.create().texOffs(94, 49).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 7.5F, 2.75F, -0.0436F, 0.0F, 0.0F));

        PartDefinition right_arm_r4 = right_glove.addOrReplaceChild("right_arm_r4", CubeListBuilder.create().texOffs(80, 46).addBox(-0.5F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, 7.5F, 0.0F, 0.0F, 0.0F, -0.0436F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    public static LayerDefinition createInnerLayer() {
        MeshDefinition meshdefinition = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F));
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.getChild("body");
        PartDefinition left_leg = partdefinition.getChild("left_leg");
        //PartDefinition right_leg = partdefinition.getChild("right_leg");

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(0, 48)
                .addBox(-7.0F, -2.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)).texOffs(24, 60)
                .addBox(-4.0F, -3.0F, -3.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(-0.27F)), PartPose.offset(1.9F, 10.0F, 0.0F));

        PartDefinition garter = left_leg.addOrReplaceChild("garter", CubeListBuilder.create().texOffs(0, 56)
                .addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.27F)), PartPose.offset(0.0F, 4.5F, 0.0F));

        //PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().addBox(0,0,0,0,0,0), PartPose.offset(0, 0, 0));
        //PartDefinition tail_back = tail.addOrReplaceChild("tail_back", CubeListBuilder.create().addBox(0,0,0,0,0,0), PartPose.offset(0, 0, 0));

       return LayerDefinition.create(meshdefinition, 128, 64);
    }

    /*@Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isCrouching()) {
            this.tail_back.xRot = 15;
        }else{
            this.tail_back.xRot = 0;
        }
    }*/
}
