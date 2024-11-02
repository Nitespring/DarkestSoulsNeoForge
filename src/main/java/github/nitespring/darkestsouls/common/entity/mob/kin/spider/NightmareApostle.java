package github.nitespring.darkestsouls.common.entity.mob.kin.spider;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class NightmareApostle extends Spider{
    public NightmareApostle(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public int getHeadType() {return 0;}
    public float getBaseScale() {return 1.0f;}
    public boolean canPerformMagic() {
        return true;
    }
}
