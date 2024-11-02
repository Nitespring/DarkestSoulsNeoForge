package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.core.util.CustomBlockTags;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ArcaneBullet extends AbstractHurtingProjectile implements GeoEntity{

    public float damage=2.0f;
    public int maxLifeTime=60;
    public static float dScale=1;

    public int lifeTicks=0;

    @Nullable private LivingEntity target;

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public ArcaneBullet(EntityType<? extends AbstractHurtingProjectile> e, Level level) {
        super(e, level);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.magic_bullet.new"));
        return PlayState.CONTINUE;
    }

    public void setDamage(float f){
        this.damage=f;
    }
    public void setMaxLifeTime(int i){
        this.maxLifeTime=i;
    }
    public void setDimensionScale(float f){
        this.dScale=f;
    }
    public float getDimensionScale(){
        return dScale;
    }

    @Override
    public void tick() {
        super.tick();
        this.lifeTicks++;
        if(lifeTicks>=this.maxLifeTime){
            this.doRemoval();
        }
        Vec3 mov = this.getDeltaMovement();
        double d0 = mov.horizontalDistance();
        if(mov!=null) {
            this.setYRot((float) (Mth.atan2(mov.x, mov.z) * (double) (180F / (float) Math.PI)));
            this.setXRot((float) (Mth.atan2(mov.y, d0) * (double) (180F / (float) Math.PI)));
        }

        if(this.getTarget()!=null&&mov!=null){
            if(tickCount%2==0) {
                Vec3 sPos = this.position();
                Vec3 tPos = this.getTarget().position().add(0, (this.getTarget().getBbHeight()/2+this.getTarget().getEyeHeight())/2, 0);
                Vec3 aim0 = new Vec3(
                        tPos.x() - sPos.x(),
                        tPos.y() - sPos.y(),
                        tPos.z() - sPos.z()
                );
                if(Math.abs(tPos.y() - sPos.y())<=0.5f){
                    aim0 = new Vec3(
                            tPos.x() - sPos.x(),
                            0,
                            tPos.z() - sPos.z()
                    );
                }
                float d = (float) Math.max(0.01f,aim0.length()/20);
                Vec3 aim = aim0.normalize().scale(d0);

                float a = Math.min(2.5f, (tickCount/30f)/Math.min(1.0f, d));
                float f = a / 6f;
                float f1 = 0.075f*a;
                d0 = d0*(1+Math.min(0.25f, tickCount/600f));
                Vec3 mov1 = new Vec3(
                        mov.x() + Math.signum(aim.x) * Math.min(f1, Math.abs(aim.x * f)),
                        mov.y() + Math.signum(aim.y) * Math.min(f1, Math.abs(aim.y * f)),
                        mov.z() + Math.signum(aim.z) * Math.min(f1, Math.abs(aim.z * f))
                ).normalize().scale(d0);

                this.setDeltaMovement(mov1);
            }
        }


        //this.level().addAlwaysVisibleParticle(ParticleTypes.SOUL_FIRE_FLAME, this.position().x, this.position().y + 0.6, this.position().z, 0, 0, 0);
        for(int i=0; i<=1+20*this.getDimensionScale(); i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.5f,0.5f,0.5f);
            this.level().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(0.7f, 0.9f, 1), 0.5f),
                    this.position().x+0.5*this.getDimensionScale()*off.x, this.position().y + this.getBbHeight()/2 + 0.5*this.getDimensionScale()*off.y, this.position().z +0.5*this.getDimensionScale()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
        }
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public void doRemoval(){
        this.onRemoval();
        this.remove(RemovalReason.DISCARDED);
    }
    public void onRemoval(){}

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
       return null;
        //return ParticleTypes.SCULK_SOUL;
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity e = p_37259_.getEntity();
        e.hurt(e.level().damageSources().indirectMagic(this, this.getOwner()),this.damage);
        this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, this.getSoundSource(), 1.0f, 2.0f);
        for(int i=0; i<=1+26*this.getDimensionScale(); i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(0.75f, 0.75f, 0.75f);
            if(this.level() instanceof ServerLevel level) {
                level.sendParticles(new DustParticleOptions(new Vector3f(0.2f, 0.8f, 1),1.25f), this.position().x+off.x, this.position().y+this.getBbHeight()*0.5f+off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
            }
        }
        doDiscard();
    }


    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState block = this.level().getBlockState(result.getBlockPos());
        if(block.is(CustomBlockTags.BOMB_BREAKABLE)){
            this.level().destroyBlock(result.getBlockPos(), true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, result.getBlockPos());
        }else {
            this.doDiscard();
        }
    }

    public void doDiscard(){


            this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, this.getSoundSource(), 1.0f, 2.0f);
            for (int i = 0; i <= 1 + 28 * this.getDimensionScale(); i++) {
                RandomSource r = this.random;
                Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(0.75f, 0.75f, 0.75f);
                if(this.level() instanceof ServerLevel level) {
                    level.sendParticles(new DustParticleOptions(new Vector3f(0.2f, 0.8f, 1),1.5f), this.position().x+off.x, this.position().y+this.getBbHeight()*0.5f+off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                    level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, this.position().x+off.x, this.position().y+this.getBbHeight()*0.5f+off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                }
            }

        super.discard();
    }


}
