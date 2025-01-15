package github.nitespring.darkestsouls.common.entity.mob.church;

import github.nitespring.darkestsouls.common.entity.projectile.weapon.Flame;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
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
import java.util.Random;

public class ChurchDoctorFlamesprayer extends ChurchDoctor implements GeoEntity {
    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public Vec3 aimVec;
    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f, 0.6f, EntityAttachments.createDefault(0.9f, 0.8f),false);
    public ChurchDoctorFlamesprayer(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=15;
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
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.church_doctor.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.church_doctor.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.church_doctor.flamesprayer.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.church_doctor.stun"));
                    break;
                case 2:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.church_doctor.hit_stun"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.church_doctor.flamesprayer.shoot"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.church_doctor.flamesprayer.attack"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.church_doctor.flamesprayer.attack1"));
                    break;
                default:
                    if(this.isInWater()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.church_doctor.swim"));
                    }else if(this.onClimbable()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.church_doctor.climb"));
                    }else if(!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.church_doctor.fall"));

                    }else if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.church_doctor.flamesprayer.walk"));
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.church_doctor.flamesprayer.idle"));
                    }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    public int getDefaultHatType() {
        return 1;
    }
    @Override
    public int getDefaultRobeType() {
        return 0;
    }
    @Override
    public ItemStack getRightHandItem() {
        return ItemInit.CHURCH_CANE.get().getDefaultInstance();
    }

    @Override
    public ItemStack getLeftHandItem() {
        return ItemInit.FLAMESPRAYER.get().getDefaultInstance();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {

        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_);
    }
    @Override
    public void populateClothing(){
        Random rn = new Random();
        int r = rn.nextInt(48) + 1;
        switch(r) {
            case 1:
                this.setRobeType(1);
                break;
            case 2,3:
                this.setRobeType(2);
                break;
            case 4,5,6,7,8,9,10:
                this.setRobeType(3);
                break;
            case 11,12,13,14,15,16,17,18,19,20:
                this.setRobeType(4);
                break;
            case 21,22,23,24,25:
                this.setRobeType(5);
                break;
            case 26,27,28:
                this.setRobeType(6);
                break;
            default:
                this.setRobeType(0);
                break;
        }
        int r1 = rn.nextInt(48) + 1;
        switch(r1) {
            case 1,2,3,4,5:
                this.setHatType(0);
                break;
            case 6:
                this.setHatType(2);
                break;
            default:
                this.setHatType(1);
                break;
        }
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new BreakDoorGoal(this, (p_34082_) -> {
            return p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
        }));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 1.0D, false, 4, ()->true));

        this.goalSelector.addGoal(2, new ChurchDoctorFlamesprayer.AttackGoal(this));

        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,0.2f,1));
        super.registerGoals();

    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose p_21047_) {

        if((this.isInWater()&&this.getAnimationState()==0)) {
            return CRAWLING_BB;
        }else {
            return this.getType().getDimensions();
        }
    }
    @Override
    public void tick() {
        if(this.onClimbable()&&this.getAnimationState()==0){
            if(this.getTarget()!=null&&this.getTarget().position().y<this.position().y){
                self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double) -0.2F, 0.0D));
            }else {
                self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double) 0.08F, 0.0D));
            }
        }
        if(this.tickCount%5==0){this.refreshDimensions();}

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
        boolean flag1 = this.getTarget() != null && this.distanceTo(this.getTarget()) <= 8;
        this.getNavigation().stop();
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
                /*if(getAnimationTick()>=4&&getAnimationTick()<=8) {
                    this.getNavigation().stop();*/
                if(getAnimationTick()>=8) {
                    this.getNavigation().stop();
                /*}else if(getAnimationTick()>=24){
                    if ((getAnimationTick()-16) % 7 == 0) {
                        if (this.getTarget() == null) {
                            aimVec = this.getLookAngle().normalize();
                        } else {
                            aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                        }
                    }*/
                }
                if (getAnimationTick() == 12) {
                    if (this.getTarget() == null) {
                        aimVec = this.getLookAngle().normalize();
                    } else {
                        aimVec = this.getTarget().position().add(0,0.25,0).add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()>=12) {
                    if(aimVec!=null) {
                        this.getLookControl().setLookAt(pos.add(aimVec));
                    }
                }
                if(getAnimationTick()>=14) {
                    if ((getAnimationTick()-14) % 7 == 0) {
                        this.playSound(SoundEvents.FIRE_EXTINGUISH);
                        Vec3 aim;
                        if(aimVec!=null){
                            aim = aimVec;
                        }else{
                            aim = this.getLookAngle();
                        }
                        double a=  5*Math.PI/19;
                        for(int i = 0; i<=4; i++) {
                            Random r = new Random();
                            float rF = 2*(r.nextFloat()-0.5f);
                            float b = (float) (a*rF);
                            Vec3 aim1 = new Vec3((aim.x*Math.cos(b)-aim.z*Math.sin(b)),aim.y,(aim.z*Math.cos(b)+aim.x*Math.sin(b)));
                            float x = (float) (pos.x + 0.4 * aim.x+ 0.4 * aim1.x);
                            float y = (float) (pos.y + 0.6 + 0.6 * aim1.y);
                            float z = (float) (pos.z + 0.4 * aim.z+ 0.4 * aim1.z);
                            Flame entity = new Flame(EntityInit.FLAME.get(), this.level());
                            entity.setPos(x, y, z);
                            float flyingPower = 0.08f;
                            entity.setDeltaMovement(aim1.scale(flyingPower));
                            entity.accelerationPower=flyingPower;
                            /*entity.xPower = flyingPower * aim1.x;
                            entity.yPower = flyingPower * aim1.y;
                            entity.zPower = flyingPower * aim1.z;*/
                            entity.setOwner(this);
                            entity.setAttackDamage((float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.4f));
                            entity.setPoiseDamage(1);
                            entity.setFlyingTime(10);
                            entity.setSize(0.8f);
                            entity.setRicochet(0);
                            this.level().addFreshEntity(entity);
                        }
                    }
                }
                if(getAnimationTick()>=80&&!flag1) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                if(getAnimationTick()>=96&&flag) {
                    int r = new Random().nextInt(1024);
                    if(r<=400) {
                        setAnimationTick(0);
                        setAnimationState(22);
                    }
                }
                if(getAnimationTick()>=144) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 22:
                if(getAnimationTick()<=22){
                    this.moveToTarget(1.5f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==22) {
                    this.playSound(this.getAttackSound(), 0.2f,0.4f);
                }
                if(getAnimationTick()==26) {
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
                if(getAnimationTick()>=36&&flag) {
                    int r = new Random().nextInt(1024);
                    if(r<=400) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    }else if(r<=600) {
                        setAnimationTick(0);
                        setAnimationState(21);
                    }
                }
                if(getAnimationTick()>=42) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 23:
                this.getNavigation().stop();
                if(getAnimationTick()==12) {
                    this.playSound(this.getAttackSound(), 0.2f,0.4f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.8f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=16&&flag) {
                    int r = new Random().nextInt(1024);
                    if(r<=400) {
                        setAnimationTick(0);
                        setAnimationState(22);
                    }else if(r<=600) {
                        setAnimationTick(0);
                        setAnimationState(21);
                    }
                }
                if(getAnimationTick()>=20) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
        }
    }
    public void moveToTarget(float speed){
        boolean flag = this.getTarget()!=null;
        if(flag) {
            this.getLookControl().setLookAt(this.getTarget(), 10.0F, 10.0F);
            Path path = this.getNavigation().createPath(this.getTarget(), 0);
            this.getNavigation().moveTo(path, speed);
        }

    }
    public class AttackGoal extends Goal {


        private final double speedModifier = 1.1f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final ChurchDoctor mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;




        public AttackGoal(ChurchDoctor entityIn) {
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
            this.mob.getNavigation().moveTo(this.path, this.speedModifier);
            this.mob.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextAttack = 8;

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
            //this.checkForPreciseAttack();


            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

        }


        @SuppressWarnings("unused")
        private void checkForPreciseAttack() {
            LivingEntity target = this.mob.getTarget();
            double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            double reach = this.getAttackReachSqr(target);
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

                if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }
            }

        }







        protected void checkForAttack(double distance, double reach){
            if (distance <= reach && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=400)      {this.mob.setAnimationState(21);}
                else if(r<=800) {this.mob.setAnimationState(22);}
                else if(r<=1600){this.mob.setAnimationState(23);}
            }
            if (distance <= 4*reach && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=40){this.mob.setAnimationState(21);}
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
