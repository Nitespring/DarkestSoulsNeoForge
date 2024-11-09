package github.nitespring.darkestsouls.common.entity.mob.kin.spider;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class LargeSpider extends Spider{
    public LargeSpider(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public float getBaseScale() {return 2.5f;}
}
