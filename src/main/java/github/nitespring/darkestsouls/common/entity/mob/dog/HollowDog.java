package github.nitespring.darkestsouls.common.entity.mob.dog;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class HollowDog extends Dog{


    public HollowDog(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public int getDefaultDogType() {
        return 5;
    }

    @Override
    protected void defineDogType() {}

    @Override
    protected int getDSDefaultTeam() {
        return 0;
    }
}
