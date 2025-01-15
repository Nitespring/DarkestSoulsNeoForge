package github.nitespring.darkestsouls.common.entity.mob.kin.spider;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.projectile.spell.ArcaneBullet;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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
import java.util.Random;

public class Spider extends DarkestSoulsAbstractEntity implements GeoEntity {

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public Vec3 aimVec;
    public Spider(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=12;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 1;
    }

    @Override
    public int getMaxPoise() {
        return 12;
    }

    @Override
    public int getMaxPosture() {
        return 24;
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    public int getHeadType() {return 0;}
    public float getBaseScale() {return 1.0f;}


    public boolean canPerformMagic() {
        return false;
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
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.spider.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.spider.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.spider.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.spider.stun"));
                    break;
                case 2:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.spider.hit_stun"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.spider.atk1"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.spider.atk2"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.spider.atk3"));
                    break;
                case 31,32:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.spider.cast"));
                    break;
                default:
                    if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        if(this.getCombatState()==1) {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.spider.run"));
                        }else {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.spider.walk2"));
                        }
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.spider.idle"));
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
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 motionMultiplier) {
        if (!state.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(state, motionMultiplier);
        }
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new Spider.AttackGoal(this));

        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,0.2f,1));
        super.registerGoals();

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
                if(getAnimationTick()<=6){
                    this.moveToTarget();
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==10) {
                    this.playSound(SoundEvents.SPIDER_AMBIENT, 0.2f,0.4f);
                }
                if(getAnimationTick()==16) {
                    doLargeAttack(1.0f,1.25f,1.0f);
                }
                if(getAnimationTick()>=30) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 22:
                if(getAnimationTick()<=6){
                    this.moveToTarget();
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==10) {
                    this.playSound(SoundEvents.SPIDER_AMBIENT, 0.2f,0.4f);
                }
                if(getAnimationTick()==12) {
                    doAttack(0.0f,1.0f,1.0f);
                }
                if(getAnimationTick()>=18&&flag) {
                    setAnimationTick(0);
                    setAnimationState(23);
                }
                if(getAnimationTick()>=22) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 23:
                if(getAnimationTick()<=6){
                    this.moveToTarget();
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==10) {
                    this.playSound(SoundEvents.SPIDER_AMBIENT, 0.2f,0.4f);
                }
                if(getAnimationTick()==12) {
                    doAttack(1.0f,1.0f,1.0f);
                }
                if(getAnimationTick()>=24) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 31:
                performRangedAttack1();
                break;
            case 32:
                performRangedAttack2();
                break;
        }
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
            if (aimVec == null) {aimVec = this.getLookAngle().normalize();}
            //this.playSound(this.getAttackSound(), 0.2f,1.0f);
            this.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE);
            float x = (float) (pos.x + 0.6 * aimVec.x);
            float y = (float) (pos.y + 1.8 + 0.6 * aimVec.y);
            float z = (float) (pos.z + 0.6 * aimVec.z);
            ArcaneBullet entity = new ArcaneBullet(EntityInit.ARCANE_BULLET.get(), levelIn);
            entity.setPos(x,y,z);
            entity.setMaxLifeTime(40);
            float flyingPower = 0.05f;
            entity.setDeltaMovement(aimVec.scale(0.05f));
            entity.accelerationPower=flyingPower;
            entity.setOwner(this);
            if(this.getTarget()!=null){entity.setTarget(this.getTarget());}
            entity.setDamage((float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
            levelIn.addFreshEntity(entity);

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
        if(getAnimationTick()==12||getAnimationTick()==16||getAnimationTick()==20) {
            if (aimVec == null) {aimVec = this.getLookAngle().normalize();}
            //this.playSound(this.getAttackSound(), 0.2f,1.0f);
            this.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE);
            float x = (float) (pos.x + 0.6 * aimVec.x);
            float y = (float) (pos.y + 1.8 + 0.6 * aimVec.y);
            float z = (float) (pos.z + 0.6 * aimVec.z);
            ArcaneBullet entity = new ArcaneBullet(EntityInit.ARCANE_BULLET.get(), levelIn);
            entity.setPos(x,y,z);
            entity.setMaxLifeTime(40);
            float flyingPower = 0.05f;
            entity.setDeltaMovement(aimVec.add(
                    new Vec3(r.nextInt(-3,4)*f,
                            r.nextInt(4)*f,
                            r.nextInt(-3,4)*f)
            ).scale(0.05f));
            entity.accelerationPower=flyingPower;
            entity.setOwner(this);
            if(this.getTarget()!=null){entity.setTarget(this.getTarget());}
            entity.setDamage((float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
            levelIn.addFreshEntity(entity);

        }

        if(getAnimationTick()>=48) {
            setAnimationTick(0);
            setAnimationState(0);
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
    public void doLargeAttack(float dmgFlat, float dmgMull, float range){
        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
        DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                this.position().add((range*1.75f) * this.getLookAngle().x,
                        0.25,
                        (range*1.75f) * this.getLookAngle().z),
                (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)*dmgMull+dmgFlat, 5);
        h.setOwner(this);
        h.setHitboxScaleWidth(0.5f);
        h.setHitboxScaleHeight(0);
        h.setHitboxType(0);
        h.setTarget(this.getTarget());
        this.level().addFreshEntity(h);
    }
    public void doAttack(float dmgFlat, float dmgMull, float range){
        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
        DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                this.position().add((range*1.25f) * this.getLookAngle().x,
                        0.25,
                        (range*1.25f) * this.getLookAngle().z),
                (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)*dmgMull+dmgFlat, 5);
        h.setOwner(this);
        h.setHitboxScaleWidth(0.25f);
        h.setHitboxScaleHeight(0);
        h.setHitboxType(0);
        h.setTarget(this.getTarget());
        this.level().addFreshEntity(h);
    }

    public class AttackGoal extends Goal {


        private final double walkingSpeedModifier = 1.1f;
        private final double runningSpeedModifier = 1.5f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final Spider mob;
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


        public AttackGoal(Spider entityIn) {
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
            //this.mob.getNavigation().moveTo(this.path, this.getSpeedModifier());
            this.mob.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextAttack = 8;
            this.ticksUntilNextRangedAttack = 10;
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
                else if(r<=1400) {this.mob.setAnimationState(22);}
                else if(r<=1900){this.mob.setAnimationState(23);}
            }
            if(canPerformMagic()) {
                if (this.ticksUntilNextRangedAttack <= 2 && this.ticksUntilNextAttack <= 2) {
                    int r = this.mob.getRandom().nextInt(2048);
                    if (r <= 560) {

                        this.mob.setAnimationState(31);

                    }else if (r <= 960) {

                        this.mob.setAnimationState(32);

                    }
                }
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