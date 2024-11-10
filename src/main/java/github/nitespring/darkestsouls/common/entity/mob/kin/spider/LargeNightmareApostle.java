package github.nitespring.darkestsouls.common.entity.mob.kin.spider;

import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LargeNightmareApostle extends LargeSpider{
    public LargeNightmareApostle(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    public int getHeadType() {return 1;}
    public boolean canPerformMagic() {
        return true;
    }



}
