package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.SewerCentipede;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SewerCentipedeGeoRenderer extends GeoEntityRenderer<SewerCentipede>{

	public SewerCentipedeGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new SewerCentipedeModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(SewerCentipede entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	public int getPackedOverlay(SewerCentipede animatable, float u, float partialTick) {

		return OverlayTexture.NO_OVERLAY;
	}

	
	 @Override
	public RenderType getRenderType(SewerCentipede animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		 return RenderType.entityCutoutNoCull(texture);
	}
	 
	 @Override
	public void render(SewerCentipede entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		 float scaleFactor = 1.0f;
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


    public static class SewerCentipedeModel extends GeoModel<SewerCentipede> {

        @Override
        public ResourceLocation getAnimationResource(SewerCentipede animatable) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "animations/sewer_centipede.animation.json");
        }

        @Override
        public ResourceLocation getModelResource(SewerCentipede object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "geo/sewer_centipede.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(SewerCentipede object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/sewer_centipede.png");
        }


        @Override
        public void setCustomAnimations(SewerCentipede entity, long uniqueID, AnimationState<SewerCentipede> customPredicate) {
            super.setCustomAnimations(entity, uniqueID, customPredicate);
            GeoBone head = this.getAnimationProcessor().getBone("waist_rotation");
            assert customPredicate != null;
            EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(extraData.headPitch() *0.15f* ((float) Math.PI / 180F));
            //head.setRotY(extraData.netHeadYaw() *0.15f* ((float) Math.PI / 180F));


        }

    }
}
