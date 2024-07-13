package github.nitespring.darkestsouls.common.item;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class CustomArmourItem extends ArmorItem {

    protected final int enchantability;
    protected final int bloodResistance;
    protected final int fireDefence;
    protected final int magicDefence;
    private final Supplier<ItemAttributeModifiers> actualDefaultModifiers;
    protected final float defence;
    protected final float toughness;
    public CustomArmourItem(float defence, float toughness,float knockbackResistance, float speedModifier,float jumpModifier, int magicDefence, int fireDefence, int bloodResistance, int durability, int enchantability, Holder<ArmorMaterial> materialType, ArmorItem.Type type, Properties properties) {
        super(materialType, type, properties.stacksTo(1).durability(durability));
        this.enchantability = enchantability;
        this.bloodResistance = bloodResistance;
        this.fireDefence = fireDefence;
        this.magicDefence = magicDefence;
        this.defence=defence;
        this.toughness=toughness;
        this.actualDefaultModifiers = Suppliers.memoize(
                () -> {
                    ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
                    EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(type.getSlot());
                    ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
                    itemattributemodifiers$builder.add(
                            Attributes.ARMOR,
                            new AttributeModifier(resourcelocation, (double) defence, AttributeModifier.Operation.ADD_VALUE),
                            equipmentslotgroup
                    );
                    itemattributemodifiers$builder.add(
                            Attributes.ARMOR_TOUGHNESS,
                            new AttributeModifier(resourcelocation, (double)toughness, AttributeModifier.Operation.ADD_VALUE),
                            equipmentslotgroup
                    );
                    if (knockbackResistance > 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.KNOCKBACK_RESISTANCE,
                                new AttributeModifier(resourcelocation, (double)knockbackResistance, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }
                    if (speedModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.MOVEMENT_SPEED,
                                new AttributeModifier(resourcelocation, speedModifier, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }
                    if (jumpModifier != 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.JUMP_STRENGTH,
                                new AttributeModifier(resourcelocation, (double)jumpModifier, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }
                    return itemattributemodifiers$builder.build();
                }
        );
    }
    public int getDefense() {return (int)defence;}
    public float getToughness() {
        return toughness;
    }
    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }
    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.actualDefaultModifiers.get();
    }

}
