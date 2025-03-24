package github.nitespring.darkestsouls.client.render.entity.mob.demon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Darkwraith;
import github.nitespring.darkestsouls.common.entity.mob.demon.CapraDemon;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class CapraDemonItemLayer<T extends CapraDemon & GeoEntity> extends BlockAndItemGeoLayer<T>{

	public CapraDemonItemLayer(GeoRenderer<T> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, T animatable) {
		 if (bone.getName().equals("item_right")) {
				 return ItemInit.DEMON_GREAT_MACHETE.get().getDefaultInstance();
		 }else if (bone.getName().equals("item_left")) {
			 return ItemInit.DEMON_GREAT_MACHETE.get().getDefaultInstance();
		 }else{
				 return null;
			 }
		 }
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
		if (bone.getName().equals("item_right")) {
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else if (bone.getName().equals("item_left")) {
			return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		}else{
			return null;
		}
	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, T animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
		poseStack.pushPose();
	if (bone.getName().equals("item_right")) {

		poseStack.translate(0, -0.1, -0.1);
		poseStack.mulPose(Axis.XP.rotationDegrees(-90));
		poseStack.mulPose(Axis.YP.rotationDegrees(0));
		poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		poseStack.scale(1.25f,1.25f,1.25f);

	}else if (bone.getName().equals("item_left")) {

		poseStack.translate(0, 0.28, -0.56);
		poseStack.mulPose(Axis.XP.rotationDegrees(-20));
		poseStack.mulPose(Axis.YP.rotationDegrees(0));
		poseStack.mulPose(Axis.ZP.rotationDegrees(180));
		poseStack.scale(1.25f,1.25f,1.25f);

	}
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
	poseStack.popPose();
}
	


}
