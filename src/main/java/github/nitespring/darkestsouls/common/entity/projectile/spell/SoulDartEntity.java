package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.util.CustomBlockTags;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

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

public class SoulDartEntity extends AbstractHurtingProjectile {

    public float damage=2.0f;
    public int maxLifeTime=20;
    public static float dScale=1;
    public int lifeTicks=0;

    public SoulDartEntity(EntityType<? extends AbstractHurtingProjectile> e, Level level) {
        super(e, level);
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


        this.level().addAlwaysVisibleParticle(ParticleTypes.SOUL_FIRE_FLAME, this.position().x, this.position().y + 0.6, this.position().z, 0, 0, 0);
        for(int i=0; i<=1+20*this.getDimensionScale(); i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.5f,0.5f,0.5f);
            this.level().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(0.2f, 0.8f, 1), 0.5f),
                    this.position().x+0.5*this.getDimensionScale()*off.x, this.position().y + 0.5 + 0.5*this.getDimensionScale()*off.y, this.position().z +0.5*this.getDimensionScale()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
        }
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
       return new DustParticleOptions(new Vector3f(0.0f,0.8f,1), 1.0f);
        //return ParticleTypes.SCULK_SOUL;
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity e = p_37259_.getEntity();
        e.hurt(e.level().damageSources().indirectMagic(this, this.getOwner()),this.damage);
        if(e instanceof DarkestSoulsAbstractEntity e1){
            if(!this.level().isClientSide()) {
                e1.damagePoiseHealth(7);
                e1.damagePostureHealth(5);
            }
        }
        this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.RESPAWN_ANCHOR_CHARGE, this.getSoundSource(), 1.0f, 2.0f);
        for(int i=0; i<=1+26*this.getDimensionScale(); i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(0.75f, 0.75f, 0.75f);
            if(this.level() instanceof ServerLevel level) {
                level.sendParticles(new DustParticleOptions(new Vector3f(0.2f, 0.8f, 1),1.25f), this.position().x+off.x, this.position().y+this.getBbHeight()*0.5f+off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
            }
        }
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
