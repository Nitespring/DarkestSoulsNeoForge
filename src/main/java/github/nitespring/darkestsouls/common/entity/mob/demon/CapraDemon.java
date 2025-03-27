package github.nitespring.darkestsouls.common.entity.mob.demon;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.projectile.spell.ChaosFireball;
import github.nitespring.darkestsouls.common.entity.projectile.spell.MagmaBurstEntity;
import github.nitespring.darkestsouls.common.entity.projectile.spell.MagmaBurstParent;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.Flame;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import static com.ibm.icu.text.PluralRules.Operand.f;

public class CapraDemon extends DarkestSoulsAbstractEntity implements GeoEntity {

    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(1.2f, 0.8f, 0.6f, EntityAttachments.createDefault(1.2f, 0.8f),false);
    protected Vec3 aimVec;
    //protected List<Vec2> posVecList;
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
        } else {
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
                    if(this.isInLiquid() && !getBlockStateOn().isSolid()
                            /*&& (this.getFluidHeight(FluidTags.LAVA) >= this.getFluidJumpThreshold() ||
                            this.getFluidHeight(FluidTags.WATER) >= this.getFluidJumpThreshold())*/) {
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
        capraDemonAIGoals();
        defaultCapraDemonGoals();
        super.registerGoals();

    }

    @Override
    public void jumpInFluid(FluidType type) {
        super.jumpInFluid(type);
    }

    protected void defaultCapraDemonGoals(){
        this.goalSelector.addGoal(0, new CapraDemon.FloatGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,0.2f,1));
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose p_21047_) {

        if ((this.isInFluidType() && !getBlockStateOn().isSolid() &&
                /*(this.getFluidHeight(FluidTags.LAVA) >= this.getFluidJumpThreshold() ||
                this.getFluidHeight(FluidTags.WATER) >= this.getFluidJumpThreshold()) && */
                this.getAnimationState() == 0)) {
            return CRAWLING_BB;
        } else {
            return this.getType().getDimensions();
        }
    }



    @Override
    public double getFluidJumpThreshold() {
        return 1.6f;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return true;
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    protected boolean isAffectedByFluids() {
        return true;
    }



    protected void capraDemonAIGoals(){

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
        return 32;
    }
    @Override
    public int getMaxPoise() {return 8;}

    @Override
    public void tick() {
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
        boolean flag = this.getTarget() != null && this.distanceTo(this.getTarget()) <= 5.2;
        boolean flag1 = this.getTarget() != null && this.distanceTo(this.getTarget()) <= 8;
        switch (this.getAnimationState()) {
            case 1:
                this.getNavigation().stop();
                if(getAnimationTick()==1){
                    this.playSound(SoundEvents.BLAZE_HURT);
                    //posVecList=null;
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
                    //posVecList=null;
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

                if(getAnimationTick()==17) {
                    this.setDeltaMovement(0,0.75,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==20){
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.5));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.5));
                    }
                }
                if (getAnimationTick() == 22) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if(getAnimationTick()==27) {
                    doAttack(2.0f,1.1f,1.0f);
                }
                if(getAnimationTick()>=30&&flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=800){
                        setAnimationTick(0);
                        setAnimationState(22);
                    }else if(r<=912){
                        setAnimationTick(0);
                        setAnimationState(30);
                    }else if(r<=1024){
                        setAnimationTick(0);
                        setAnimationState(31);
                    }else if(r<=1136){
                        setAnimationTick(0);
                        setAnimationState(33);
                    }
                }
                if(getAnimationTick()>=36) {
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
                if(getAnimationTick()==15) {
                    doAttack(1.0f,1.0f,1.0f);
                }
                if(getAnimationTick()>=20&&flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=360){
                        setAnimationTick(0);
                        setAnimationState(21);
                    }else if(r<=480){
                        setAnimationTick(0);
                        setAnimationState(30);
                    }else if(r<=720){
                        setAnimationTick(0);
                        setAnimationState(31);
                    }else if(r<=840){
                        setAnimationTick(0);
                        setAnimationState(33);
                    }
                }
                if(getAnimationTick()>=27) {
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
                if (getAnimationTick() >= 28 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=800) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    }else if(r<=900) {
                        setAnimationTick(0);
                        setAnimationState(27);
                    }else if(r<=1300) {
                        setAnimationTick(0);
                        setAnimationState(35);
                    }
                }
                if (getAnimationTick() >= 32) {
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
                if (getAnimationTick() >= 28 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=800) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    }else if(r<=900) {
                        setAnimationTick(0);
                        setAnimationState(26);
                    }else if(r<=1300) {
                        setAnimationTick(0);
                        setAnimationState(34);
                    }
                }
                if (getAnimationTick() >= 32) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 25:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 26) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 34) {
                    doAttack(1.0f,1.2f,1.0f);
                }
                if (getAnimationTick() >= 36 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=240){
                        setAnimationTick(0);
                        setAnimationState(21);
                    }else if(r<=360){
                        setAnimationTick(0);
                        setAnimationState(22);
                    }else if(r<=480){
                        setAnimationTick(0);
                        setAnimationState(30);
                    }else if(r<=640){
                        setAnimationTick(0);
                        setAnimationState(31);
                    }else if(r<=720){
                        setAnimationTick(0);
                        setAnimationState(33);
                    }
                }
                if (getAnimationTick() >= 48) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 26:
                if (getAnimationTick() <= 3) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 12) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 17) {
                    doAttack(0,1.0f,1.0f);
                }
                if (getAnimationTick() >= 22 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=240) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    }else if(r<=960) {
                        setAnimationTick(0);
                        setAnimationState(27);
                    }else if(r<=1440) {
                        setAnimationTick(0);
                        setAnimationState(35);
                    }else if(r<=1600){
                        setAnimationTick(0);
                        setAnimationState(21);
                    }else if(r<=1720){
                        setAnimationTick(0);
                        setAnimationState(30);
                    }else if(r<=1800){
                        setAnimationTick(0);
                        setAnimationState(31);
                    }else if(r<=1900){
                        setAnimationTick(0);
                        setAnimationState(33);
                    }
                }
                if (getAnimationTick() >= 27) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 27:
                if (getAnimationTick() <= 3) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 12) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 17) {
                    doAttack(0,1.0f,1.0f);
                }
                if (getAnimationTick() >= 22 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=240) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    }else if(r<=960) {
                        setAnimationTick(0);
                        setAnimationState(26);
                    }else if(r<=1440) {
                        setAnimationTick(0);
                        setAnimationState(34);
                    }else if(r<=1600){
                        setAnimationTick(0);
                        setAnimationState(21);
                    }else if(r<=1720){
                        setAnimationTick(0);
                        setAnimationState(30);
                    }else if(r<=1800){
                        setAnimationTick(0);
                        setAnimationState(31);
                    }else if(r<=1900){
                        setAnimationTick(0);
                        setAnimationState(33);
                    }
                }
                if (getAnimationTick() >= 27) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 28:
                if (getAnimationTick() <= 2) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 10) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 12) {
                    doAttack(0,1.0f,1.0f);
                }
                if (getAnimationTick() >= 18 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=240){
                        setAnimationTick(0);
                        setAnimationState(21);
                    }else if(r<=360){
                        setAnimationTick(0);
                        setAnimationState(23);
                    }else if(r<=480){
                        setAnimationTick(0);
                        setAnimationState(24);
                    }else if(r<=640){
                        setAnimationTick(0);
                        setAnimationState(25);
                    }
                }
                if (getAnimationTick() >= 24) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 29:
                if (getAnimationTick() <= 10) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 10) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 12) {
                    doAttack(0,1.0f,1.0f);
                }
                if (getAnimationTick() >= 16 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=480){
                        setAnimationTick(0);
                        setAnimationState(30);
                    }else if(r<=600){
                        setAnimationTick(0);
                        setAnimationState(31);
                    }else if(r<=840){
                        setAnimationTick(0);
                        setAnimationState(33);
                    }
                }
                if (getAnimationTick() >= 24) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 30:
                if (getAnimationTick() <= 8) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 8) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 10) {
                    doAttack(0,0.75f,1.0f);
                }
                if (getAnimationTick() >= 16 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=480){
                        setAnimationTick(0);
                        setAnimationState(22);
                    }else if(r<=600){
                        setAnimationTick(0);
                        setAnimationState(31);
                    }else if(r<=840){
                        setAnimationTick(0);
                        setAnimationState(32);
                    }
                }
                if (getAnimationTick() >= 22) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 31:
                if (getAnimationTick() <= 2) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 7) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 8) {
                    doAttack(-1.0f,0.5f,1.0f);
                }
                if (getAnimationTick() >= 12 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=480){
                        setAnimationTick(0);
                        setAnimationState(23);
                    }else if(r<=600){
                        setAnimationTick(0);
                        setAnimationState(24);
                    }else if(r<=840){
                        setAnimationTick(0);
                        setAnimationState(26);
                    }else if(r<=960){
                        setAnimationTick(0);
                        setAnimationState(27);
                    }
                }
                if (getAnimationTick() >= 20) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 32:
                if (getAnimationTick() <= 4) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==7) {
                    doAttack(-1.0f,0.8f,0.75f);
                }
                if(getAnimationTick()==8) {
                    this.setDeltaMovement(0,0.75,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0)).scale(-1);
                    } else {
                        this.aimVec = this.getLookAngle().scale(-1);
                    }
                }
                if(getAnimationTick()==10){
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.5));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(-0.5));
                    }
                }
                if(getAnimationTick()>=17) {
                    int r = new Random().nextInt(2048);
                    if(flag) {
                        if (r <= 360) {
                            setAnimationTick(0);
                            setAnimationState(25);
                        } else if (r <= 720) {
                            setAnimationTick(0);
                            setAnimationState(30);
                        } else if (r <= 960) {
                            setAnimationTick(0);
                            setAnimationState(33);
                        } else if (r <= 1240) {
                            setAnimationTick(0);
                            setAnimationState(41);
                        }else if (r <= 1480) {
                            setAnimationTick(0);
                            setAnimationState(42);
                        }
                    }else {
                        if (r <= 480) {
                            setAnimationTick(0);
                            setAnimationState(41);
                        } else if (r <= 960) {
                            setAnimationTick(0);
                            setAnimationState(42);
                        }
                    }
                }
                if(getAnimationTick()>=24) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    setAnimationState(0);
                    int r = getRandom().nextInt(2048);
                    if (r <= 720) {
                        setCombatState(0);
                    }
                }
                break;
            case 33:
                if (getAnimationTick() <= 15) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 18) {
                    this.playSound(SoundEvents.RAVAGER_ATTACK, 0.2f, 0.4f);
                }
                if (getAnimationTick() == 20) {
                    doAttack(1.5f,1.05f,1.25f);
                }
                if (getAnimationTick() >= 26 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=480){
                        setAnimationTick(0);
                        setAnimationState(30);
                    }else if(r<=600){
                        setAnimationTick(0);
                        setAnimationState(31);
                    }
                }
                if (getAnimationTick() >= 34) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 34:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 23) {
                    doAttack(1.0f,1.0f,1.0f);
                }
                if (getAnimationTick() >= 26 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=800) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    }else if(r<=900) {
                        setAnimationTick(0);
                        setAnimationState(27);
                    }else if(r<=1024) {
                        setAnimationTick(0);
                        setAnimationState(33);
                    }else if(r<=1300) {
                        setAnimationTick(0);
                        setAnimationState(35);
                    }
                }
                if (getAnimationTick() >= 32) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 35:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 23) {
                    doAttack(1.0f,1.0f,1.0f);
                }
                if (getAnimationTick() >= 26 && flag) {
                    int r = new Random().nextInt(2048);
                    if(r<=800) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    }else if(r<=900) {
                        setAnimationTick(0);
                        setAnimationState(26);
                    }else if(r<=1024) {
                        setAnimationTick(0);
                        setAnimationState(33);
                    }else if(r<=1300) {
                        setAnimationTick(0);
                        setAnimationState(34);
                    }
                }
                if (getAnimationTick() >= 32) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 41:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 18) {
                    if (this.getTarget() == null) {
                        aimVec = this.getLookAngle().normalize();
                    } else {
                        aimVec = this.getTarget().position().add(0,0,0).add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()>=18) {
                    if(aimVec!=null) {
                        this.getLookControl().setLookAt(pos.add(aimVec));
                    }
                }
                if (getAnimationTick() == 22) {
                    createFireball();
                }
                if (getAnimationTick() >= 44) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 42:
                if (getAnimationTick() <= 6) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 12) {
                    if (this.getTarget() == null) {
                        aimVec = this.getLookAngle().normalize();
                    } else {
                        aimVec = this.getTarget().position().add(0,1.75,0).add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()>=12) {
                    if(aimVec!=null) {
                        this.getLookControl().setLookAt(pos.add(aimVec));
                    }
                }
                if (getAnimationTick() >= 15) {
                    setAnimationTick(0);
                    setAnimationState(43);
                }
                break;
            case 43:
                /*if (aimVec==null) {
                    if (this.getTarget() == null) {
                        aimVec = this.getLookAngle().normalize();
                    } else {
                        aimVec = this.getTarget().position().add(0,0.25,0).add(pos.scale(-1)).normalize();
                    }
                }*/
                if(aimVec!=null) {
                    this.getLookControl().setLookAt(pos.add(aimVec));
                }
                if ((getAnimationTick()) % 8 == 0) {
                    doFireBreath(pos);
                }
                if(getAnimationTick()>=80&&!flag1) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                if(getAnimationTick()>=96&&flag) {
                    int r = new Random().nextInt(1024);
                    if(r<=240) {
                        setAnimationTick(0);
                        setAnimationState(33);
                    }
                }
                if(getAnimationTick()>=144) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 44:
                if (getAnimationTick() <= 2) {
                    this.moveToTarget();
                } else {
                    this.getNavigation().stop();
                }
                /*if (posVecList==null) {
                    Random r = new Random();
                    posVecList= new ArrayList<Vec2>();
                    for(int i = 0; i <= 12; i = 0) {
                        posVecList.add(i,new Vec2(r.nextFloat(-1, 1), r.nextFloat(-1, 1)));
                    }
                }*/
                if (getAnimationTick() >= 36) {
                    setAnimationTick(0);
                    setAnimationState(45);
                }
                break;
            case 45:
                if(getAnimationTick()<=48){
                    if ((getAnimationTick()) % 4 == 0) {
                        doFireStorm(Math.min(12,getAnimationTick()/4));
                    }
                }
                if ((getAnimationTick()) % 12 == 0) {
                    doFireStorm(12);
                }
                if(getAnimationTick()>=256) {
                    setAnimationTick(0);
                    setAnimationState(46);
                    //posVecList=null;
                }
                break;
            case 46:

                if(getAnimationTick()>=27) {
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
            this.getNavigation().moveTo(path, 1.25f);
        }

    }

    public void createFireball(){
        this.playSound(SoundEvents.BLAZE_SHOOT);
        Level levelIn = this.level();
        Vec3 pos = this.position();
        Vec3 aim;
        if(aimVec!=null){
            aim = aimVec;
        }else{
            aim = this.getLookAngle();
        }
        Random r = new Random();
        float f = 0.0f;
        if(getTarget()!=null) {
            f = 0.25f * (Math.min(25, distanceTo(getTarget())/25));
        }
        ChaosFireball entity = new ChaosFireball(EntityInit.CHAOS_FIREBALL.get(), levelIn);


        entity.setPos(pos.add(0,2.75,0).add(aim.normalize().multiply(1.0f,1.25f,1.0f)));
        float flyingPower = 0.15f;

        entity.setDamage((float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.2f));
        entity.setMaxLifeTime(100);
        entity.setDimensionScale(1.5f);
        entity.setOwner(this);
        Vec3 aim2 = new Vec3(aim.x * (1 + ((r.nextFloat() - 0.5) * 0.025) * (1 + 0.1)),
                aim.y + f,
                aim.z * (1 + ((r.nextFloat() - 0.5) * 0.025) * (1 + 0.1)));
        entity.setDeltaMovement(aim2.scale(flyingPower));
        entity.accelerationPower=flyingPower;
        /*entity.xPower = flyingPower * aim1.x;
                        entity.yPower = flyingPower * aim1.y;
                        entity.zPower = flyingPower * aim1.z;*/
        this.level().addFreshEntity(entity);
    }

    public void doFireBreath(Vec3 pos){
        this.playSound(SoundEvents.FIRE_EXTINGUISH);
        Vec3 aim;
        if(aimVec!=null){
            aim = aimVec;
        }else{
            aim = this.getLookAngle();
        }
        double a=  Math.PI/12;
        //for(int i = 0; i<=1; i++) {
            Random r = new Random();
            float rF = 2*(r.nextFloat()-0.5f);
            float b = (float) (a*rF);
            float f = 0.2f;
            if(getTarget()!=null) {
                f = 0.5f * (Math.min(20, distanceTo(getTarget())/20));
            }
            Vec3 aim1 = new Vec3((aim.x*Math.cos(b)-aim.z*Math.sin(b)),
                    aim.y+f,
                    (aim.z*Math.cos(b)+aim.x*Math.sin(b)));
            float x = (float) (pos.x + 1.2 * aim.x+ 0.2 * aim1.x);
            float y = (float) (pos.y + 1.6 + 0.6 * aim1.y);
            float z = (float) (pos.z + 1.2 * aim.z+ 0.2 * aim1.z);
            MagmaBurstParent entity = new MagmaBurstParent(EntityInit.MAGMA_BURST.get(), this.level());
            entity.setPos(x, y, z);
            float flyingPower = 0.17f;
            entity.setDeltaMovement(aim1.scale(flyingPower));
            entity.accelerationPower=flyingPower;
                        /*entity.xPower = flyingPower * aim1.x;
                        entity.yPower = flyingPower * aim1.y;
                        entity.zPower = flyingPower * aim1.z;*/
            entity.setOwner(this);
            entity.setDamage(1+(float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.6f));
            entity.setLifeTicks(36);
            this.level().addFreshEntity(entity);
        //}
    }
    public void doFireStorm(int n){
        this.playSound(SoundEvents.FIRE_EXTINGUISH);
        Vec3 pos = this.position();

        //double a=  Math.PI/12;
        double a=  Math.PI/3;
        double a1=  Math.PI/6;
        /*if (posVecList==null) {
            Random r = new Random();
            posVecList= new ArrayList<Vec2>();
            for(int i = 0; i <= 12; i = 0) {
                posVecList.add(i,new Vec2(r.nextFloat(-1, 1), r.nextFloat(-1, 1)));
            }
        }*/
        for(int j = -1; j <= 1; j++) {
            float R = 2.5f *(2 + j);
            for(int i = 0; i <= Math.min(n,6); i++) {

            Random r = new Random();
            //if(posVecList!=null && posVecList.size()>=i) {
            //    Vec2 aim2d = posVecList.get(i);
                float rX = r.nextFloat(-1, 1);
                float rZ = r.nextFloat(-1, 1);
                float aN = (float) (a * i + a1 * j);
                /*Vec3 aim = new Vec3(
                        Math.cos(aN) * aim2d.x,
                        1,
                        Math.sin(aN) * aim2d.y
                );*/
                Vec3 aim = new Vec3(
                        Math.cos(aN),
                        1,
                        Math.sin(aN)
                );
                if(j!=0){
                    aim = new Vec3(
                            j*Math.cos(aN),
                            1,
                            j*Math.sin(aN)
                    );
                }

                float x = (float) (pos.x + R * aim.x + 0.25 * rX);
                float yi = (float) (pos.y + 5);
                float z = (float) (pos.z + R * aim.z + 0.25 * rZ);
                BlockPos blockpos = new BlockPos((int) x, (int) yi, (int) z);
                boolean flag = false;
                double d0 = 0.0D;

                do {
                    BlockPos blockpos1 = blockpos.below();
                    BlockState blockstate = level().getBlockState(blockpos1);
                    if (blockstate.isFaceSturdy(level(), blockpos1, Direction.UP)) {
                        if (!level().isEmptyBlock(blockpos)) {
                            BlockState blockstate1 = level().getBlockState(blockpos);
                            VoxelShape voxelshape = blockstate1.getCollisionShape(level(), blockpos);
                            if (!voxelshape.isEmpty()) {
                                d0 = voxelshape.max(Direction.Axis.Y);
                            }
                        }

                        flag = true;
                        break;
                    }

                    blockpos = blockpos.below();
                } while (blockpos.getY() >= Mth.floor(pos.y - 2) - 1);

                if (flag) {
                    Vec3 aim1 = new Vec3(
                            0,
                            1,
                            0
                    );
                    MagmaBurstParent entity = new MagmaBurstParent(EntityInit.MAGMA_BURST.get(), this.level());
                    float y = (float) (blockpos.getY());
                    entity.setPos(x, y, z);
                    float flyingPower = 0.25f;
                    entity.setDeltaMovement(aim1.scale(flyingPower));
                    entity.accelerationPower = flyingPower;
                        /*entity.xPower = flyingPower * aim1.x;
                        entity.yPower = flyingPower * aim1.y;
                        entity.zPower = flyingPower * aim1.z;*/
                    entity.setOwner(this);
                    entity.setDamage(1 + (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.4f));
                    entity.setLifeTicks(6);
                    this.level().addFreshEntity(entity);
                }
            }
        }
    }

    public void doAttack(float dmgFlat, float dmgMull, float range){
        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
        DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                this.position().add((range*2.25f) * this.getLookAngle().x,
                        0.25,
                        (range*2.25f) * this.getLookAngle().z),
                (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)*dmgMull+dmgFlat, 5);
        h.setOwner(this);
        h.setHitboxScaleWidth(0.4f);
        h.setHitboxScaleHeight(0.5f);
        h.setHitboxType(0);
        h.setTarget(this.getTarget());
        this.level().addFreshEntity(h);
    }

    public class AttackGoal extends Goal {

        private final double walkingSpeedModifier = 1.0f;
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
            this.ticksUntilNextRangedAttack = this.mob.getRandom().nextInt(80);;
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
            //this.checkForAttack(distance, reach);
            this.checkForPreciseAttack();
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
            /*if(this.ticksUntilNextRangedAttack<=0 && this.ticksUntilNextAttack <= 0){
                this.ticksUntilNextRangedAttack=10;
            }*/

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

                this.mob.setAnimationState(44);
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
            if (distance <= reach*3.5f && this.ticksUntilNextRangedAttack <= 0) {
                int r = this.mob.getRandom().nextInt(1024);
                if(r<=480)      {this.mob.setAnimationState(41);}
                else if(r<=720)      {this.mob.setAnimationState(42);}
            }
            if (distance <= reach*1.5f && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(1024);
                if(r<=360)      {this.mob.setAnimationState(21);}
                else if(r<=480) {this.mob.setAnimationState(33);}
                else if(r<=560) {this.mob.setAnimationState(44);}
            }
            if (distance <= reach && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(5600);
                if(r<=720)      {this.mob.setAnimationState(21);}
                else if(r<=900) {this.mob.setAnimationState(22);}
                else if(r<=2140) {this.mob.setAnimationState(23);}
                else if(r<=2400) {this.mob.setAnimationState(24);}
                else if(r<=2700) {this.mob.setAnimationState(25);}
                else if(r<=3000) {this.mob.setAnimationState(26);}
                else if(r<=3200) {this.mob.setAnimationState(27);}
                else if(r<=3300) {this.mob.setAnimationState(28);}
                else if(r<=3400) {this.mob.setAnimationState(29);}
                else if(r<=3500) {this.mob.setAnimationState(30);}
                else if(r<=3700) {this.mob.setAnimationState(31);}
                else if(r<=3800) {this.mob.setAnimationState(32);}
                else if(r<=4400) {this.mob.setAnimationState(33);}
                else if(r<=4700) {this.mob.setAnimationState(34);}
                else if(r<=4800) {this.mob.setAnimationState(35);}
                else if(r<=5200) {this.mob.setAnimationState(44);}
            }


        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity target) {
            return getAttackReach(target)*getAttackReach(target);
        }

        protected double getAttackReach (LivingEntity target){
            return this.mob.getBbWidth() + target.getBbWidth() + 2.5f;
        }

    }

    public class FloatGoal extends Goal {
        private final Mob mob;

        public FloatGoal(Mob mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP));
            mob.getNavigation().setCanFloat(true);
        }

        @Override
        public boolean canUse() {
            return this.mob.isInWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getFluidJumpThreshold() || this.mob.isInLava() && this.mob.getFluidHeight(FluidTags.LAVA) > this.mob.getFluidJumpThreshold() || this.mob.isInFluidType((fluidType, height) -> this.mob.canSwimInFluidType(fluidType) && height > this.mob.getFluidJumpThreshold());
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            if (this.mob.getRandom().nextFloat() < 0.8F) {
                this.mob.getJumpControl().jump();
            }
        }
    }

}