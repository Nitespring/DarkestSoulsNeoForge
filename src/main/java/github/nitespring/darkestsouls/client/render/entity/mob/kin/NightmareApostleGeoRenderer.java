package github.nitespring.darkestsouls.client.render.entity.mob.kin;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.mob.beast.BeastPatientEmissiveLayer;
import github.nitespring.darkestsouls.common.entity.mob.beast.BeastPatientEntity;
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

public class NightmareApostleGeoRenderer<T extends Spider & GeoEntity> extends GeoEntityRenderer<T>{

	public NightmareApostleGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NightmareApostleModel<>());
        
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


    public static class NightmareApostleModel<T extends Spider & GeoEntity> extends GeoModel<T> {

        @Override
        public ResourceLocation getAnimationResource(T animatable) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "animations/spider.animation.json");
        }

        @Override
        public ResourceLocation getModelResource(T object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "geo/spider.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(T object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/spider/nightmare_apostle.png");
        }


        @Override
        public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
            super.setCustomAnimations(entity, uniqueID, customPredicate);
            GeoBone head = this.getAnimationProcessor().getBone("head_rotation");
            GeoBone spiderHead = this.getAnimationProcessor().getBone("spider_head");
            GeoBone humanHead = this.getAnimationProcessor().getBone("human_head");
            EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));
            if(entity.getHeadType()==1){
                spiderHead.setHidden(true);
                humanHead.setHidden(false);
            }else{
                spiderHead.setHidden(false);
                humanHead.setHidden(true);
            }


        }

    }
}
