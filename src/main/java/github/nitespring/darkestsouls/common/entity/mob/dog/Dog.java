package github.nitespring.darkestsouls.common.entity.mob.dog;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctor;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class Dog extends DarkestSoulsAbstractEntity implements GeoEntity {

    private static final EntityDataAccessor<Integer> DOG_TYPE = SynchedEntityData.defineId(Dog.class, EntityDataSerializers.INT);

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public Dog(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=5;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 0;
    }
    @Override
    public boolean isBoss() {
        return false;
    }



    public int getDogType() {return this.getEntityData().get(DOG_TYPE);}
    public void setRobeType(int i){this.getEntityData().set(DOG_TYPE, i);}
    public int getDefaultDogType() {return 0;}
    public float getBaseScale() {return 0.8f;}

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DOG_TYPE, this.getDefaultDogType());
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }


    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.dog.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.dog.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.dog.die"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.dog.stun"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.dog.atk1"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.dog.atk2"));
                    break;
                default:
                    if(this.isInWater()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.dog.swim"));
                    }else if(!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.dog.fall"));
                    }else if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        if(this.getCombatState()==1) {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.dog.run"));
                        }else {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.dog.walk"));
                        }
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.dog.idle"));
                    }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {
        defineDogType();
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_);
    }

    protected void defineDogType(){}

    @Override
    protected void registerGoals() {
        dogAIGoals();
        defaultDogGoals();
        super.registerGoals();

    }

    protected void defaultDogGoals(){
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,0.2f,1));
    }

    protected void dogAIGoals(){
        this.goalSelector.addGoal(2, new Dog.AttackGoal(this));
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.WOLF_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WOLF_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOLF_DEATH;
    }

    @Override
    public int getMaxPoise() {
        return 8;
    }

    @Override
    public void tick() {
        if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {
            this.playAnimation();
        }
        super.tick();
    }

    protected void playAnimation() {
        this.increaseAnimationTick(1);
        Level levelIn = this.level();
        Vec3 pos = this.position();
        boolean flag = this.getTarget() != null && this.distanceTo(this.getTarget()) <= 2;
        switch (this.getAnimationState()) {
            case 1:
                this.getNavigation().stop();
                if (getAnimationTick() >= 85) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    this.resetPoiseHealth();
                    setAnimationState(0);
                }
                break;
            //Attack
            case 21:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 10) {
                    this.playSound(SoundEvents.WOLF_GROWL, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 12) {
                    doAttack(0,1.0f,1.0f);
                }
                if (getAnimationTick() >= 24 && flag) {
                    setAnimationTick(0);
                    setAnimationState(23);
                }
                if (getAnimationTick() >= 30) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 22:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 10) {
                    this.playSound(SoundEvents.WOLF_GROWL, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 12) {
                    doAttack(2.0f,1.2f,1.25f);

                }
                if (getAnimationTick() >= 24 && flag) {
                    setAnimationTick(0);
                    setAnimationState(23);
                }
                if (getAnimationTick() >= 30) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
        }
    }
    public void moveToTarget(){
        boolean flag = this.getTarget()!=null;
        if(flag) {
            this.getLookControl().setLookAt(this.getTarget(), 10.0F, 10.0F);
            Path path = this.getNavigation().createPath(this.getTarget(), 0);
            this.getNavigation().moveTo(path, 1.5f);
        }

    }

    public void doAttack(float dmgFlat, float dmgMull, float range){
        this.playSound(SoundEvents.WOLF_GROWL);
        DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                this.position().add((range*1.0f) * this.getLookAngle().x,
                        0.25,
                        (range*1.0f) * this.getLookAngle().z),
                (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)*dmgMull+dmgFlat, 5);
        h.setOwner(this);
        h.setHitboxScaleAbsolute(0);
        h.setHitboxScaleHeight(0);
        h.setHitboxType(0);
        h.setTarget(this.getTarget());
        this.level().addFreshEntity(h);
    }

    public class AttackGoal extends Goal {

        private final double walkingSpeedModifier = 1.1f;
        private final double runningSpeedModifier = 1.8f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final Dog mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private int ticksUntilNextRangedAttack;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;

        private int lastCanUpdateStateCheck;


        public AttackGoal(Dog entityIn) {
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
            this.ticksUntilNextAttack = 8;
            this.ticksUntilNextRangedAttack = 120;
            this.lastCanUpdateStateCheck = getStateUpdateInitialTimer();
            this.mob.setAnimationState(0);
            if(mob.getCombatState()==1){
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=240) {
                    this.mob.setCombatState(0);
                    this.stop();
                }
            }else{
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=840) {
                    this.mob.setCombatState(1);
                    this.stop();
                }
            }
        }
        @Override
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }

            this.mob.getNavigation().stop();
        }

        public double getSpeedModifier() {
            if(mob.getCombatState()==1){
                return runningSpeedModifier;
            }else{
                return walkingSpeedModifier;
            }
        }
        public int getStateUpdateInitialTimer(){
            if(mob.getCombatState()==1){
                return 800;
            }else{
                return 600;
            }
        }


        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            double distanceSQR = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            double reachSQR = this.getAttackReachSqr(target);
            double distance = this.mob.distanceTo(target);
            double reach = this.getAttackReach(target);
            this.doMovement(target, reachSQR);
            this.checkForAttack(distance, reach);
            //this.checkForPreciseAttack();
            this.lastCanUpdateStateCheck = Math.max(this.lastCanUpdateStateCheck-1, 0);
            if(this.lastCanUpdateStateCheck<=0){
                if(mob.getCombatState()==1) {
                    int r = this.mob.getRandom().nextInt(2048);
                    if (r <= 350) {
                        this.mob.setCombatState(0);
                        this.mob.getNavigation().stop();
                        this.mob.getNavigation().moveTo(this.path, this.getSpeedModifier());
                        this.ticksUntilNextPathRecalculation=0;
                    }
                    this.lastCanUpdateStateCheck = 200;
                }else{
                    int r = this.mob.getRandom().nextInt(2048);
                    if (r <= 450) {
                        this.mob.setCombatState(1);
                        this.mob.getNavigation().stop();
                        this.mob.getNavigation().moveTo(this.path, this.getSpeedModifier());
                        this.ticksUntilNextPathRecalculation=0;
                    }
                    this.lastCanUpdateStateCheck = 200;
                }
            }
            this.ticksUntilNextRangedAttack = Math.max(this.ticksUntilNextRangedAttack - 1, 0);
            if(this.ticksUntilNextRangedAttack<=0 && this.ticksUntilNextAttack <= 0){
                this.ticksUntilNextRangedAttack=10;
            }

            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

        }


        @SuppressWarnings("unused")
        private void checkForPreciseAttack() {
            LivingEntity target = this.mob.getTarget();
            //double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            //double reach = this.getAttackReachSqr(target);
            double distance = this.mob.distanceTo(target);
            double reach = this.getAttackReach(target);
            if (this.ticksUntilNextAttack <= 0 && distance <= reach) {

                this.mob.setAnimationState(23);
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
                if(r<=800)      {this.mob.setAnimationState(21);}
                else if(r<=1600) {this.mob.setAnimationState(22);}
            }


        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity target) {
            return getAttackReach(target)*getAttackReach(target);
        }

        protected double getAttackReach (LivingEntity target){
            return this.mob.getBbWidth() + target.getBbWidth() + 2.0f;
        }

    }

}