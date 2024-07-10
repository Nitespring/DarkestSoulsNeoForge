package github.nitespring.darkestsouls.common.item.child.armour;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.equipment.armour.HunterTricornModel;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.antlr.v4.runtime.misc.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class HunterArmourItem extends ArmorItem {



    public HunterArmourItem(Holder<ArmorMaterial> p_40386_, Type p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }



    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {

        return innerModel ? ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID , "textures/armour/hunter_armour_tricorn_inner.png") : ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID , "textures/armour/hunter_armour_tricorn_outer.png");
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ArmorRender.INSTANCE);
    }



    private static final class ArmorRender implements IClientItemExtensions {
        private static final ArmorRender INSTANCE = new ArmorRender();


        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ClientListener.TRICORN_INNER : ClientListener.TRICORN_OUTER);
            HunterTricornModel aModel = new HunterTricornModel(root);
            return aModel;
        }


    }


}
