package github.nitespring.darkestsouls.core.util;

import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public final class CustomBiomeTags {

    public static final TagKey<Biome> SPIDER_ON_SURFACE = create("spider_on_surface");

    private CustomBiomeTags() {
    }


    private static TagKey<Biome> create(String p_203847_) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID , p_203847_));
    }

    /*public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }*/


}
