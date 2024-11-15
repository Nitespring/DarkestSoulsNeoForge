package github.nitespring.darkestsouls.common.entity.mob.kin.spider;

import github.nitespring.darkestsouls.common.entity.projectile.spell.ArcaneBullet;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class LargeSpider extends Spider{
    public LargeSpider(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public float getBaseScale() {return 2.5f;}

    @Override
    public int getMaxPoise() {
        return 24;
    }

    @Override
    public int getMaxPosture() {
        return 56;
    }

    @Override
    public void doLargeAttack(float dmgFlat, float dmgMull, float range){
        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
        for(int i = 0; i<=1; i++) {
            Vec3 pos = this.position().add(range * (3.5f + 1.75f*i) * this.getLookAngle().normalize().x,
                    0.25,
                    range * (3.5f + 1.75f*i) * this.getLookAngle().normalize().z);
            DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX_LARGE.get(), level(),
                    pos,
                    (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * dmgMull + dmgFlat, 5);
            h.setOwner(this);
            h.setHitboxScaleWidth(0.75f);
            h.setHitboxScaleHeight(0);

            h.setHitboxType(0);
            h.setTarget(this.getTarget());
            this.level().addFreshEntity(h);
        }
    }
    @Override
    public void doAttack(float dmgFlat, float dmgMull, float range){
        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
        //for(int i = 0; i<=2; i++) {
        Vec3 pos = this.position().add(range * 3.5f  * this.getLookAngle().normalize().x,
                0.25,
                range * 3.5f * this.getLookAngle().normalize().z);
        DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX_LARGE.get(), level(),
                pos,
                (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * dmgMull + dmgFlat, 5);
        h.setOwner(this);
        h.setHitboxScaleWidth(0.5f);
        h.setHitboxScaleHeight(0);

        h.setHitboxType(0);
        h.setTarget(this.getTarget());
        this.level().addFreshEntity(h);
        /*this.level().addParticle(
                ParticleTypes.EXPLOSION,true,
                pos.x, pos.y, pos.z,
                0,0,0);*/
        //}
    }

    public void performRangedAttack1(){
        Level levelIn = this.level();
        Vec3 pos = this.position();

        this.getNavigation().stop();

        if(getAnimationTick()==5) {
            if(this.getTarget()==null) {
                aimVec = this.getLookAngle().normalize();
            }else{
                aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
            }
        }
        if(getAnimationTick()==12) {
            float f = (float) (Math.PI/6);
            for(int i =-1; i<=1; i++) {
                if (aimVec == null) {
                    aimVec = this.getLookAngle().normalize();
                }
                this.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE);
                float x = (float) (pos.x + 3.6 * aimVec.x + Math.cos(f*i));
                float y = (float) (pos.y + 4.6 + 3.6 * aimVec.y);
                float z = (float) (pos.z + 3.6 * aimVec.z + Math.sin(f*i));
                ArcaneBullet entity = new ArcaneBullet(EntityInit.ARCANE_BULLET.get(), levelIn);
                entity.setPos(x, y, z);
                entity.setMaxLifeTime(60);
                float flyingPower = 0.05f;
                entity.setDeltaMovement(aimVec.add(
                        Math.cos(f*i),
                        0,
                        Math.sin(f*i)
                ).scale(0.05f));
                entity.accelerationPower = flyingPower;
                entity.setOwner(this);
                if (this.getTarget() != null) {
                    entity.setTarget(this.getTarget());
                }
                entity.setDamage((float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
                levelIn.addFreshEntity(entity);
            }
        }

        if(getAnimationTick()>=30) {
            setAnimationTick(0);
            setAnimationState(0);
        }
    }
    public void performRangedAttack2(){
        Level levelIn = this.level();
        Vec3 pos = this.position();

        this.getNavigation().stop();

        if(getAnimationTick()==5) {
            if(this.getTarget()==null) {
                aimVec = this.getLookAngle().normalize();
            }else{
                aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
            }
        }
        float f = (float) (Math.PI/12);
        Random r = new Random();
        if(getAnimationTick()==12||getAnimationTick()==16||
                getAnimationTick()==20||getAnimationTick()==24
                ||getAnimationTick()==28||getAnimationTick()==32) {
            if (aimVec == null) {aimVec = this.getLookAngle().normalize();}
            //this.playSound(this.getAttackSound(), 0.2f,1.0f);
            this.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE);
            float rX = r.nextFloat(-0.5f,0.5f);
            float rY = r.nextFloat(-0.5f,0.5f);
            float rZ = r.nextFloat(-0.5f,0.5f);
            float x = (float) (pos.x + 3.6 * aimVec.x+1.25*rX);
            float y = (float) (pos.y + 4.6 + 3.6 * aimVec.y+1.25*rY);
            float z = (float) (pos.z + 3.6 * aimVec.z+1.25*rZ);
            ArcaneBullet entity = new ArcaneBullet(EntityInit.ARCANE_BULLET.get(), levelIn);
            entity.setPos(x,y,z);
            entity.setMaxLifeTime(60);
            float flyingPower = 0.05f;
            entity.setDeltaMovement(aimVec.add(
                    new Vec3(rX*3*f,
                            rY*3*f,
                            rZ*3*f)
            ).scale(0.05f));
            entity.accelerationPower=flyingPower;
            entity.setOwner(this);
            if(this.getTarget()!=null){entity.setTarget(this.getTarget());}
            entity.setDamage((float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
            levelIn.addFreshEntity(entity);

        }

        if(getAnimationTick()>=60) {
            setAnimationTick(0);
            setAnimationState(0);
        }
    }
}
