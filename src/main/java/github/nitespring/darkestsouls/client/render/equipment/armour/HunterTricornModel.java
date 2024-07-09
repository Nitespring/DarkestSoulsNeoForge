package github.nitespring.darkestsouls.client.render.equipment.armour;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class HunterTricornModel<T extends LivingEntity> extends HumanoidArmorModel<T> {


    public HunterTricornModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createOuterLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(64, 2).mirror().addBox(8.0F, -8.0F, -14.0F, 0.0F, 8.0F, 32.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -5.75F, 1.75F, 0.2392F, 0.4274F, 0.0989F));

        PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(64, 2).addBox(-8.0F, -8.0F, -14.0F, 0.0F, 8.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.75F, 1.75F, 0.2392F, -0.4274F, -0.0989F));

        PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(44, 1).addBox(-10.0F, 0.0F, -10.0F, 28.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.75F, -0.25F, 0.3038F, -0.762F, -0.2132F));

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -31.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.75F))
                .texOffs(32, 43).addBox(-2.0F, 8.0F, -3.25F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 40).addBox(-5.0F, 8.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(56, 48).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(0.1743F, -0.9924F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-4.1743F, -0.9924F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition tail_back = tail.addOrReplaceChild("tail_back", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition cube_r3 = tail_back.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -0.9924F, -1.8257F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false)
                .texOffs(104, 56).addBox(-3.0F, 5.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.22F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(104, 56).addBox(-3.2F, 5.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.22F))
                .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition left_glove = left_arm.addOrReplaceChild("left_glove", CubeListBuilder.create().texOffs(112, 47).mirror().addBox(-1.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.7F)).mirror(false)
                .texOffs(89, 55).mirror().addBox(-1.0F, 7.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.9F)).mirror(false)
                .texOffs(95, 43).mirror().addBox(-1.0F, 5.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.8F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_arm_r1 = left_glove.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(80, 46).addBox(-0.5F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, 7.5F, 0.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition left_arm_r1 = left_glove.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(94, 49).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 7.5F, -2.75F, 0.0436F, 0.0F, 0.0F));

        PartDefinition left_arm_r2 = left_glove.addOrReplaceChild("left_arm_r2", CubeListBuilder.create().texOffs(94, 49).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 7.5F, 2.75F, -0.0436F, 0.0F, 0.0F));

        PartDefinition left_arm_r3 = left_glove.addOrReplaceChild("left_arm_r3", CubeListBuilder.create().texOffs(80, 46).mirror().addBox(-0.5F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.75F, 7.5F, 0.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition right_glove = right_arm.addOrReplaceChild("right_glove", CubeListBuilder.create().texOffs(112, 47).addBox(-3.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.7F))
                .texOffs(89, 55).addBox(-3.0F, 7.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.9F))
                .texOffs(95, 43).addBox(-3.0F, 5.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_arm_r4 = right_glove.addOrReplaceChild("left_arm_r4", CubeListBuilder.create().texOffs(80, 46).mirror().addBox(-0.5F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.75F, 7.5F, 0.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition right_arm_r2 = right_glove.addOrReplaceChild("right_arm_r2", CubeListBuilder.create().texOffs(94, 49).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 7.5F, -2.75F, 0.0436F, 0.0F, 0.0F));

        PartDefinition right_arm_r3 = right_glove.addOrReplaceChild("right_arm_r3", CubeListBuilder.create().texOffs(94, 49).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 7.5F, 2.75F, -0.0436F, 0.0F, 0.0F));

        PartDefinition right_arm_r4 = right_glove.addOrReplaceChild("right_arm_r4", CubeListBuilder.create().texOffs(80, 46).addBox(-0.5F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, 7.5F, 0.0F, 0.0F, 0.0F, -0.0436F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }
    public static LayerDefinition createInnerLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(0, 48).addBox(-5.1F, 7.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F))
                .texOffs(24, 60).addBox(-2.1F, 7.0F, -3.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(-0.27F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false)
                .texOffs(0, 56).addBox(-3.0F, 2.5F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.27F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }
}
