package github.nitespring.darkestsouls.client.render.equipment.armour;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.LivingEntity;

public class HunterTricornRenderLayer<T extends LivingEntity, M extends HunterTricornModel<T>,A extends HunterTricornModel<T>> extends HumanoidArmorLayer<T, M, A>{

	 private final HunterTricornModel innerModel;
	 private final HunterTricornModel outerModel;

	public HunterTricornRenderLayer(RenderLayerParent<T, M> renderer, A innerModel, A outerModel, ModelManager modelManager) {
		super(renderer, innerModel, outerModel, modelManager);
		this.innerModel = innerModel;
		this.outerModel = outerModel;
	}



	

}
