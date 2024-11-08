package github.nitespring.darkestsouls.common.entity.mob.dog;

import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class LargeUndeadDog extends LargeDog{


    public LargeUndeadDog(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public int getDefaultDogType() {
        return 7;
    }

    @Override
    protected void defineDogType() {}

    @Override
    protected int getDSDefaultTeam() {
        return 0;
    }

    @Override
    public void doAttack(float dmgFlat, float dmgMull, float range){
        this.playSound(SoundEvents.WOLF_GROWL);
        DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                this.position().add((range*1.5f) * this.getLookAngle().x,
                        0.25,
                        (range*1.5f) * this.getLookAngle().z),
                (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)*dmgMull+dmgFlat, 5);
        h.setOwner(this);
        h.setHitboxScaleAbsolute(0.5f);
        h.setHitboxScaleHeight(0);
        h.setHitboxType(7);
        h.setTarget(this.getTarget());
        this.level().addFreshEntity(h);
    }
}
