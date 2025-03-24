package github.nitespring.darkestsouls.common.entity.mob.demon;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.Random;

public class CapraDemon extends DarkestSoulsAbstractEntity implements GeoEntity {

    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f, 0.6f, EntityAttachments.createDefault(0.9f, 0.8f),false);
    protected Vec3 aimVec;
    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public CapraDemon(EntityType<? extends PathfinderMob> e, Level level) {
        super(e, level);
        this.xpReward=25;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 0;
    }
    @Override
    public boolean isBoss() {
        return false;
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "tail_controller", 1, this::tailPredicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }


    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState tailPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.tail"));

        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.stun"));
                    break;
                case 2:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.hit_stun"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack_jump1"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack_jump2"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack1"));
                    break;
                case 24:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack2"));
                    break;
                case 25:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack3"));
                    break;
                case 26:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack4"));
                    break;
                case 27:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack5"));
                    break;
                case 28:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack6"));
                    break;
                case 29:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack7"));
                    break;
                case 30:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack8"));
                    break;
                case 31:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack9"));
                    break;
                case 32:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack10"));
                    break;
                case 33:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack11"));
                    break;
                case 34:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack12"));
                    break;
                case 35:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.attack13"));
                    break;
                case 41:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.fireball"));
                    break;
                case 42:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.fire_breath_start"));
                    break;
                case 43:
                    event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.fire_breath"));
                    break;
                case 44:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.fire_storm_start"));
                    break;
                case 45:
                    event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.fire_storm"));
                    break;
                case 46:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.capra_demon.fire_storm_end"));
                    break;
                default:
                    if(this.isInWater()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.swim"));
                    }else if(!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.fall"));
                    }else if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        if(this.getCombatState()==1) {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.run"));
                        }else {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.walk"));
                        }
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.capra_demon.idle"));
                    }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_);
    }


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

    @Override
    public EntityDimensions getDefaultDimensions(Pose p_21047_) {

        if ((this.isInWater() && this.getAnimationState() == 0) || this.getAnimationState() == 1) {
            return CRAWLING_BB;
        } else {
            return this.getType().getDimensions();
        }
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return true;
    }

    protected void dogAIGoals(){
        this.goalSelector.addGoal(2, new CapraDemon.AttackGoal(this));
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.RAVAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.RAVAGER_DEATH;
    }

    @Override
    public int getMaxPosture() {
        return 12;
    }
    @Override
    public int getMaxPoise() {return 4;}

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
                if(getAnimationTick()==1){
                    this.playSound(SoundEvents.BLAZE_HURT);
                }
                if (getAnimationTick() >= 85) {
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
                if (getAnimationTick() <= 2) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }

                if(getAnimationTick()==12) {
                    this.setDeltaMovement(0,0.75,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==15){
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.5));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.5));
                    }
                }
                if (getAnimationTick() == 16) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if(getAnimationTick()==23) {
                    doAttack(0,1.0f,1.0f);
                }
                if(getAnimationTick()>=32&&flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=800){
                        setAnimationTick(0);
                        setAnimationState(22);
                    }
                }
                if(getAnimationTick()>=48) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    setAnimationState(0);
                    int r = getRandom().nextInt(2048);
                    if (r <= 360) {
                        setCombatState(0);
                    }
                }
                break;
            case 22:
                if (getAnimationTick() <= 2) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }

                if(getAnimationTick()==6) {
                    this.setDeltaMovement(0,0.75,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==8){
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.5));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.5));
                    }
                }
                if(getAnimationTick()==12) {
                    doAttack(0,1.0f,1.0f);
                }
                if(getAnimationTick()>=20&&flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=240){
                        setAnimationTick(0);
                        setAnimationState(21);
                    }
                }
                if(getAnimationTick()>=28) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    setAnimationState(0);
                    int r = getRandom().nextInt(2048);
                    if (r <= 720) {
                        setCombatState(0);
                    }
                }
                break;
            case 23:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 18) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 22) {
                    doAttack(0,1.0f,1.0f);
                }
                if (getAnimationTick() >= 32 && flag) {
                    setAnimationTick(0);
                    setAnimationState(24);
                }
                if (getAnimationTick() >= 36) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 24:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 18) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 22) {
                    doAttack(0,1.0f,1.0f);
                }
                /*if (getAnimationTick() >= 28 && flag) {
                    setAnimationTick(0);
                    setAnimationState(24);
                }*/
                if (getAnimationTick() >= 36) {
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
        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
        DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                this.position().add((range*2.0f) * this.getLookAngle().x,
                        0.5,
                        (range*2.0f) * this.getLookAngle().z),
                (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)*dmgMull+dmgFlat, 5);
        h.setOwner(this);
        h.setHitboxScaleWidth(0.25f);
        h.setHitboxScaleHeight(0.25f);
        h.setHitboxType(0);
        h.setTarget(this.getTarget());
        this.level().addFreshEntity(h);
    }

    public class AttackGoal extends Goal {

        private final double walkingSpeedModifier = 1.1f;
        private final double runningSpeedModifier = 1.8f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final CapraDemon mob;
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


        public AttackGoal(CapraDemon entityIn) {
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
                if(r<=240)      {this.mob.setAnimationState(21);}
                else if(r<=480) {this.mob.setAnimationState(22);}
                else if(r<=1240) {this.mob.setAnimationState(23);}
                else if(r<=1720) {this.mob.setAnimationState(24);}
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