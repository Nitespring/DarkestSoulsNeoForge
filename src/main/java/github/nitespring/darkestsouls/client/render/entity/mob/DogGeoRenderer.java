package github.nitespring.darkestsouls.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.dog.Dog;
import github.nitespring.darkestsouls.common.entity.mob.kin.Spider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DogGeoRenderer<T extends Dog & GeoEntity> extends GeoEntityRenderer<T>{

	public DogGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new DogModel<>());
        
        this.shadowRadius = 0.5F;

     
       
    }
	
	@Override
	protected float getDeathMaxRotation(T entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	public int getPackedOverlay(T animatable, float u, float partialTick) {

		return OverlayTexture.NO_OVERLAY;
	}

	
	 @Override
	public RenderType getRenderType(T animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		 return RenderType.entityCutoutNoCull(texture);
	}
	 
	 @Override
	public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		 float scaleFactor = entity.getBaseScale();
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


    public static class DogModel<T extends Dog & GeoEntity> extends GeoModel<T> {

        @Override
        public ResourceLocation getAnimationResource(T animatable) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "animations/dog.animation.json");
        }

        @Override
        public ResourceLocation getModelResource(T object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "geo/dog.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(T object) {
            switch(object.getDogType()) {
                case 1:
                    return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/dog/rabid_dog1.png");
                case 2:
                    return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/dog/rabid_dog2.png");
                case 3:
                    return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/dog/silver_dog.png");
                case 4:
                    return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/dog/hunting_dog.png");
                case 5:
                    return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/dog/hollow_dog.png");
                case 6:
                    return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/dog/hollow_dog1.png");
                case 7:
                    return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/dog/skeletal_dog.png");
                default:
                    return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/dog/rabid_dog.png");

            }
        }


        @Override
        public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
            super.setCustomAnimations(entity, uniqueID, customPredicate);
            GeoBone head = this.getAnimationProcessor().getBone("head_rotation");
            EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));
        }

    }
}
