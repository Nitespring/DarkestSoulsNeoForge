package github.nitespring.darkestsouls.core.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.event.GeoRenderEvent;

public final class CustomItemTags {

    public static final TagKey<Item> GUN_ENCHANTABLE = create("darkestsouls:enchantable/gun");
    public static final TagKey<Item> AMMO_CONSUMING = create("darkestsouls:enchantable/ammo_consuming");
    public static final TagKey<Item> MAGIC_ENCHANTABLE = create("darkestsouls:enchantable/magic");

    private CustomItemTags() {
    }


    private static TagKey<Item> create(String p_203847_) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(p_203847_));
    }

    public static TagKey<Item> create(ResourceLocation name) {
        return TagKey.create(Registries.ITEM, name);
    }


}
