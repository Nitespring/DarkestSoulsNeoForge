package github.nitespring.darkestsouls.client.render.entity.mob.demon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Darkwraith;
import github.nitespring.darkestsouls.common.entity.mob.demon.CapraDemon;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CapraDemonEmissiveLayer<T extends CapraDemon & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation EYES = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/demon/capra_demon_emissive.png");

	public CapraDemonEmissiveLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}
	
	
	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {
		
		RenderType cameo = RenderType.eyes(EYES);

		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
		

		
	}
	
	

	


	

	

	

}
