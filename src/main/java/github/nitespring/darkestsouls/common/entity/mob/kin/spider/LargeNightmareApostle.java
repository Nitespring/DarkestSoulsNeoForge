package github.nitespring.darkestsouls.common.entity.mob.kin.spider;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class LargeNightmareApostle extends LargeSpider{
    public LargeNightmareApostle(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    public int getHeadType() {return 1;}
    public boolean canPerformMagic() {
        return true;
    }
}
