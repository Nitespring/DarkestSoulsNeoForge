package github.nitespring.darkestsouls.common.entity.mob.skeleton;

import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class SkeletonSpear extends Skeleton implements GeoEntity {

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);


    //private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f, false);
    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f,0.6f, EntityAttachments.createDefault(0.9f, 0.8f), false);

    protected Vec3 aimVec;

    public SkeletonSpear(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=10;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }

    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) {
        /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeleton.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeleton.new"));
        }
        return PlayState.CONTINUE;
    }


    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
            this.setResetAnimation(false);
        }*/
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeleton.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeleton.stun"));
                    break;
                case 2:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeleton.hit_stun"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeleton.spear.attack1"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeleton.spear.attack2"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeleton.spear.attack3"));
                    break;
                case 24:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeleton.spear.attack4"));
                    break;
                default:
                    if(this.onClimbable()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeleton.climb"));
                    }else if(!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeleton.fall"));

                    }else if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        if(getCombatState()==1){
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeleton.spear.run"));
                        }else {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeleton.spear.walk"));
                        }
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeleton.spear.idle"));
                    }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreakDoorGoal(this, (p_34082_) -> {
            return p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
        }));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(1, new SkeletonSpear.AttackGoal(this));
        super.registerGoals();

    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {return false;}

    @Override
    public EntityDimensions getDefaultDimensions(Pose p_21047_) {

        if(this.getAnimationState()==1) {
            return CRAWLING_BB;
        }else {
            return this.getType().getDimensions();
        }
    }

    @Override
    public void tick() {
        if(this.tickCount%5==0&&this.hasEffect(EffectInit.BLEED)){
            this.removeEffect(EffectInit.BLEED);
        }
        if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {
            this.playAnimation();
        }
        if(this.tickCount%5==0){this.refreshDimensions();}
        super.tick();
        /*if(this.shouldResetAnimation()){
            this.setResetAnimation(false);
        }*/
    }

    protected void playAnimation() {
        increaseAnimationTick(1);
        boolean flag = this.getTarget()!=null && this.distanceTo(this.getTarget())<=4;
        switch (this.getAnimationState()) {
            case 1:
                this.getNavigation().stop();
                if(getAnimationTick()==1){
                    this.playSound(SoundEvents.BLAZE_HURT);
                }
                if(getAnimationTick()>=30) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    this.resetPostureHealth();
                    setAnimationState(0);
                }
                break;
            case 2:
                this.getNavigation().stop();
                if (getAnimationTick() >= 6) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    this.resetPoiseHealth();
                    setAnimationState(0);
                }
                break;
            //Attack
            case 21:
                if(getAnimationTick()>=4) {this.getNavigation().stop();}
                else{this.moveToTarget();}
                if(getAnimationTick()==4) {
                    this.setDeltaMovement(0,1,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==5){
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.15));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.15));
                    }

                }
                if(getAnimationTick()==6) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)+1, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=10&&flag) {
                    this.getNavigation().stop();
                    int r = this.getRandom().nextInt(2048);
                    if(r<=400)      {
                        //this.setResetAnimation(true);
                        setAnimationTick(0);
                        this.setAnimationState(24);
                        }
                    else if(r<=800) {
                        setAnimationTick(0);
                        this.setAnimationState(22);
                    }
                    else if(r<=1200){
                        setAnimationTick(0);
                        this.setAnimationState(23);
                    }
                }
                if(getAnimationTick()>=12) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    int r = getRandom().nextInt(2048);
                    if (r <= 240) {
                        setCombatState(0);
                    }
                }
                break;
            case 22:
                if(!(getAnimationTick()>=15&&getAnimationTick()<=22)) {this.getNavigation().stop();}
                else{this.moveToTarget();}
                if(getAnimationTick()==24) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=36) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    int r = getRandom().nextInt(2048);
                    if (r <= 240) {
                        setCombatState(0);
                    }
                }
                break;
            case 23:
                this.getNavigation().stop();
                if(getAnimationTick()==11) {
                    this.setDeltaMovement(0,1,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==12){
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.5));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.5));
                    }

                }
                if(getAnimationTick()==13) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)+2, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=17&&flag) {
                    this.getNavigation().stop();
                    int r = this.getRandom().nextInt(2048);
                    if(r<=400) {setAnimationTick(0);
                        this.setAnimationState(22);}
                }
                if(getAnimationTick()>=21) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    int r = getRandom().nextInt(2048);
                    if (r <= 240) {
                        setCombatState(0);
                    }
                }
                break;
            case 24:
                if(getAnimationTick()>=4) {this.getNavigation().stop();}
                else{this.moveToTarget();}
                if(getAnimationTick()==4) {
                    this.setDeltaMovement(0,1,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==5){
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.15));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.15));
                    }

                }
                if(getAnimationTick()==6) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)+1, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=10&&flag) {
                    this.getNavigation().stop();
                    int r = this.getRandom().nextInt(2048);
                    if(r<=400)      {
                        setAnimationTick(0);
                        this.setAnimationState(21);
                    }
                    else if(r<=800) {
                        setAnimationTick(0);
                        this.setAnimationState(22);
                    }
                    else if(r<=1200){
                        setAnimationTick(0);
                        this.setAnimationState(23);
                    }
                }
                if(getAnimationTick()>=12) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    int r = getRandom().nextInt(2048);
                    if (r <= 240) {
                        setCombatState(0);
                    }
                }
                break;
        }
    }
    public void moveToTarget(){
        boolean flag = this.getTarget()!=null;
        if(flag) {
            this.getLookControl().setLookAt(this.getTarget(), 10.0F, 10.0F);
            Path path = this.getNavigation().createPath(this.getTarget(), 0);
            this.getNavigation().moveTo(path, 1.2f);
        }

    }

    public class AttackGoal extends Goal {


        private final double walkingSpeedModifier = 1.1f;
        private final double runningSpeedModifier = 1.7f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final SkeletonSpear mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;
        private int lastCanUpdateStateCheck;



        public AttackGoal(SkeletonSpear entityIn) {
            this.mob = entityIn;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            if(this.mob.getAnimationState()==0) {
                long i = this.mob.level().getGameTime();
                if (i - this.lastCanUseCheck < 20L) {
                    return false;
                } else {
                    this.lastCanUseCheck = i;
                    LivingEntity livingentity = this.mob.getTarget();
                    if (livingentity == null) {
                        return false;
                    } else if (!livingentity.isAlive()) {
                        return false;
                    } else {
                        if (canPenalize) {
                            if (--this.ticksUntilNextPathRecalculation <= 0) {
                                this.path = this.mob.getNavigation().createPath(livingentity, 0);
                                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                                return this.path != null;
                            } else {
                                return true;
                            }
                        }
                        this.path = this.mob.getNavigation().createPath(livingentity, 0);
                        if (this.path != null) {
                            return true;
                        } else {
                            return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                        }
                    }
                }
            }else{
                return false;
            }
        }


        @Override
        public boolean canContinueToUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if(this.mob.getAnimationState()!=0) {
                return false;
            }else if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (!this.followingTargetEvenIfNotSeen) {
                return !this.mob.getNavigation().isDone();
            } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
                return false;
            } else {
                return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
            }

        }
        @Override
        public void start() {
            this.mob.getNavigation().moveTo(this.path, this.getSpeedModifier());
            this.mob.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextAttack = 5;
            this.lastCanUpdateStateCheck = 120;
            int r = this.mob.getRandom().nextInt(2048);
            if(this.mob.getCombatState()==0) {
                if (r <= 1200) {
                    this.mob.setCombatState(1);
                }
            }
            this.mob.setAnimationState(0);
        }
        @Override
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }

            this.mob.getNavigation().stop();
        }




        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            double reach = this.getAttackReachSqr(target);

            this.doMovement(target, reach);
            this.checkForAttack(distance, reach);

            this.lastCanUpdateStateCheck = Math.max(this.lastCanUpdateStateCheck-1, 0);
            if(this.lastCanUpdateStateCheck<=0){
                if(mob.getCombatState()==1) {
                    int r = this.mob.getRandom().nextInt(2048);
                    if (r <= 360) {
                        this.mob.setCombatState(0);
                        this.mob.getNavigation().stop();
                        this.mob.getNavigation().moveTo(this.path, this.getSpeedModifier());
                        this.ticksUntilNextPathRecalculation=0;
                    }
                    this.lastCanUpdateStateCheck = 200;
                }else{
                    int r = this.mob.getRandom().nextInt(2048);
                    if (r <= 720) {
                        this.mob.setCombatState(1);
                        this.mob.getNavigation().stop();
                        this.mob.getNavigation().moveTo(this.path, this.getSpeedModifier());
                        this.ticksUntilNextPathRecalculation=0;
                    }
                    this.lastCanUpdateStateCheck = 160;
                }
            }

            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

        }
        public double getSpeedModifier(){
            switch(mob.getCombatState()){
                case 1:
                    return runningSpeedModifier;
                default:
                    return walkingSpeedModifier;
            }
        }

        @SuppressWarnings("unused")
        private void checkForPreciseAttack() {
            if (this.ticksUntilNextAttack <= 0) {

                this.mob.setAnimationState(42);
            }

        }


        @SuppressWarnings("unused")
        protected void doMovement(LivingEntity livingentity, Double d0) {
            this.mob.getLookControl().setLookAt(this.mob.getTarget(), 30.0F, 30.0F);
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
                this.pathedTargetX = livingentity.getX();
                this.pathedTargetY = livingentity.getY();
                this.pathedTargetZ = livingentity.getZ();
                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                if (this.canPenalize) {
                    this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                    if (this.mob.getNavigation().getPath() != null) {
                        net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
                        if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                            failedPathFindingPenalty = 0;
                        else
                            failedPathFindingPenalty += 10;
                    } else {
                        failedPathFindingPenalty += 10;
                    }
                }
                if (d0 > 1024.0D) {
                    this.ticksUntilNextPathRecalculation += 10;
                } else if (d0 > 256.0D) {
                    this.ticksUntilNextPathRecalculation += 5;
                }

                if (!this.mob.getNavigation().moveTo(livingentity, this.getSpeedModifier())) {
                    this.ticksUntilNextPathRecalculation += 15;
                }
            }

        }







        protected void checkForAttack(double distance, double reach){
            if (distance <= reach && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=850) {

                    this.mob.setAnimationState(21);

                }else if(r<=1600){

                    this.mob.setAnimationState(21);

                } else{

                    this.mob.setAnimationState(21);
                }
            }

        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(this.mob.getBbWidth() * 8.0F * this.mob.getBbWidth());
        }

    }
}
