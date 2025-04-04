package github.nitespring.darkestsouls.common.entity.mob.hollow;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
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

public class HollowAssassin extends Hollow implements GeoEntity {

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public Vec3 aimVec;

    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f, 0.6f, EntityAttachments.createDefault(0.9f, 0.8f),false);


    public HollowAssassin(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=10;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "cape_controller", 0, this::capePredicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }


    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState capePredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.cape"));

        return PlayState.CONTINUE;
    }
    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.stun"));
                    break;
                case 2:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.hit_stun"));
                    break;
                case 11:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dash_right"));
                    break;
                case 12:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dash_left"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dagger.attack1"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dagger.attack2"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dagger.attack3"));
                    break;
                case 24:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dagger.attack4"));
                    break;
                case 25:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dagger.attack5"));
                    break;
                case 26:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dagger.attack6"));
                    break;
                case 27:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.dagger.attack7"));
                    break;
                case 31:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.throw1"));
                    break;
                default:
                    if(this.isInWater()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.swim"));
                    }else if(this.onClimbable()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.climb"));
                    }else if(!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.fall"));
                        
                    }else if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        if(this.getCombatState()==1) {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.run"));
                        }else{

                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.walk"));
                        }
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.idle"));
                    }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    public int getDefaultHatType() {
        return 0;
    }
    @Override
    public int getDefaultRobeType() {
        return 5;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {

        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_);
    }
    @Override
    public void populateClothing(){
                this.setRobeType(8);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new BreakDoorGoal(this, (p_34082_) -> {
            return p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
        }));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 1.0D, false, 4, ()->true));

        this.goalSelector.addGoal(2, new HollowAssassin.AttackGoal(this));
        this.goalSelector.addGoal(2, new HollowAssassin.AttackGoalRunning(this));


        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,0.2f,1));
        super.registerGoals();

    }



    @Override
    public EntityDimensions getDefaultDimensions(Pose p_21047_) {

         if((this.isInWater()&&this.getAnimationState()==0)||this.getAnimationState()==1) {
             return CRAWLING_BB;
         }else {
             return this.getType().getDimensions();
         }
    }

    @Override
    public void sinkInFluid(FluidType type) {
        /*if(this.canSwimInFluidType(type)){
            self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double)0.02F, 0.0D));
        }*/

        super.sinkInFluid(type);
    }


    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return true;
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

        /*if(this.getAnimationState()==0&&this.getCombatState()==1&&this.getTarget()==null){
            this.setCombatState(0);
        }*/

        if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {
            this.playAnimation();
        }
        super.tick();
    }


    protected void playAnimation() {
        this.increaseAnimationTick(1);
        Level levelIn = this.level();
        Vec3 pos = this.position();
        boolean flag = this.getTarget()!=null && this.distanceTo(this.getTarget())<=2;
        this.getNavigation().stop();
        switch (this.getAnimationState()) {
            case 1:
                this.getNavigation().stop();
                if(getAnimationTick()==1){
                    this.playSound(SoundEvents.BLAZE_HURT);
                }
                if(getAnimationTick()>=85) {
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
            case 11:
                if(getAnimationTick()==1) {
                    this.setDeltaMovement(0,0.5,0);
                    double b = Math.PI*4/12;
                    if(this.getTarget()==null) {
                        Vec3 aim = this.getLookAngle();
                        aimVec = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
                                            Math.max(0,aim.y),
                                            aim.z*Math.cos(b)+aim.x*Math.sin(b));
                    }else{
                        Vec3 aim = this.getTarget().position().add(pos.scale(-1));
                        aimVec = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
                                            Math.max(0,aim.y),
                                            aim.z*Math.cos(b)+aim.x*Math.sin(b));
                    }
                }
                if(getAnimationTick()==2) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.25f,0).scale(0.75));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.25f,0).scale(0.75));
                    }
                }
                if(getAnimationTick()>=10&&flag) {

                    int r = this.getRandom().nextInt(2048);
                    if (r <= 36) {
                        setAnimationTick(0);
                        setAnimationState(21);
                    } else if (r <= 84) {
                        setAnimationTick(0);
                        setAnimationState(22);
                    } else if (r <= 132) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    } else if (r <= 156) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    } else if (r <= 204) {
                        setAnimationTick(0);
                        setAnimationState(25);
                    } else if (r <= 244) {
                        setAnimationTick(0);
                        setAnimationState(26);
                    } else if (r <= 296) {
                        setAnimationTick(0);
                        setAnimationState(27);
                    }
                }
                if(getAnimationTick()>12) {
                    setAnimationTick(0);
                    int r = this.getRandom().nextInt(2048);
                    if (r <= 960) {
                        setAnimationState(31);
                    } else  {
                        setAnimationState(0);
                    }
                }
                break;
            case 12:
                if(getAnimationTick()==1) {
                    this.setDeltaMovement(0,0.5,0);
                    double b = -Math.PI*4/12;
                    if(this.getTarget()==null) {
                        Vec3 aim = this.getLookAngle();
                        aimVec = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
                                Math.max(0,aim.y),
                                aim.z*Math.cos(b)+aim.x*Math.sin(b));
                    }else{
                        Vec3 aim = this.getTarget().position().add(pos.scale(-1));
                        aimVec = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
                                Math.max(0,aim.y),
                                aim.z*Math.cos(b)+aim.x*Math.sin(b));
                    }
                }
                if(getAnimationTick()==2) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.25f,0).scale(0.75));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.25f,0).scale(0.75));
                    }
                }
                if(getAnimationTick()>=10&&flag) {

                    int r = this.getRandom().nextInt(2048);
                    if (r <= 36) {
                        setAnimationTick(0);
                        setAnimationState(21);
                    } else if (r <= 84) {
                        setAnimationTick(0);
                        setAnimationState(22);
                    } else if (r <= 132) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    } else if (r <= 156) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    } else if (r <= 204) {
                        setAnimationTick(0);
                        setAnimationState(25);
                    } else if (r <= 244) {
                        setAnimationTick(0);
                        setAnimationState(26);
                    } else if (r <= 296) {
                        setAnimationTick(0);
                        setAnimationState(27);
                    }
                }
                if(getAnimationTick()>12) {
                    setAnimationTick(0);
                    int r = this.getRandom().nextInt(2048);
                    if (r <= 960) {
                        setAnimationState(31);
                    } else  {
                        setAnimationState(0);
                    }
                }
                break;
            //Attack
            case 21:
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==8) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=10&&flag) {
                    int r = this.getRandom().nextInt(2048);
                    if (r <= 480) {
                        setAnimationTick(0);
                        setAnimationState(22);
                    } else if (r <= 960) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    } else if (r <= 1440) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    }
                }
                if(getAnimationTick()>=12) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 22:
                if(getAnimationTick()==4) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=10&&flag) {
                    int r = this.getRandom().nextInt(2048);
                    if (r <= 360) {
                        setAnimationTick(0);
                        setAnimationState(21);
                    } else if (r <= 840) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    } else if (r <= 1320) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    }
                }
                if(getAnimationTick()>=8) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 23:
                if(getAnimationTick()==3) {
                    this.playSound(this.getAttackSound());
                }
                if(getAnimationTick()==4) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=16) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 24:
                if(getAnimationTick()==2) {
                    this.playSound(this.getAttackSound());
                }
                if(getAnimationTick()==3) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=8) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 25:
                if(getAnimationTick()==8) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==10) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.1f)*this.getLookAngle().x,
                                    0.25,
                                    (1.1f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)+2.0f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=18) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 26:
                if(getAnimationTick()==9) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==11) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.1f)*this.getLookAngle().x,
                                    0.25,
                                    (1.1f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=16&&flag) {
                    setAnimationTick(0);
                    setAnimationState(22);
                }
                if(getAnimationTick()>=32) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 27:
                if(getAnimationTick()==1) {
                    this.setDeltaMovement(0,1.0,0);
                    if(this.getTarget()==null) {
                        aimVec = this.getLookAngle().normalize();
                    }else{
                        aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()==2) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.35));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.35));
                    }
                }
                if(getAnimationTick()==12) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)+2.0f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>18) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 31:
                if(getAnimationTick()==5) {
                    if(this.getTarget()==null) {
                        aimVec = this.getLookAngle().normalize();
                    }else{
                        aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()==6) {
                    if (aimVec == null) {aimVec = this.getLookAngle().normalize();}
                    //this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    this.playSound(SoundEvents.FISHING_BOBBER_THROW);
                    float x = (float) (pos.x + 0.6 * aimVec.x);
                    float y = (float) (pos.y + 1.4 + 0.6 * aimVec.y);
                    float z = (float) (pos.z + 0.6 * aimVec.z);
                    ThrowingKnifeEntity entity = new ThrowingKnifeEntity(EntityInit.THROWING_KNIFE.get(), levelIn);
                    entity.setPos(x,y,z);
                    float flyingPower = 0.25f;
                    entity.setDeltaMovement(aimVec);
                    entity.accelerationPower=flyingPower;
                    /*entity.xPower=flyingPower*aimVec.x;
                    entity.yPower=flyingPower*aimVec.y;
                    entity.zPower=flyingPower*aimVec.z;*/
                    entity.setOwner(this);
                    entity.setItem(ItemInit.THROWING_KNIFE.get().getDefaultInstance());
                    entity.setAttackPower((float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
                    entity.setBloodDamage(2);
                    entity.setPoiseDamage(2);
                    entity.setGravPower(0.0005f);
                    levelIn.addFreshEntity(entity);

                }
                if(getAnimationTick()>=10) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
        }
    }

    public class AttackGoal extends Goal {


        private final double speedModifier = 1.0f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final HollowAssassin mob;
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




        public AttackGoal(HollowAssassin entityIn) {
            this.mob = entityIn;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            if(this.mob.getAnimationState()==0&&this.mob.getCombatState()!=1) {
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
            if(this.mob.getAnimationState()!=0||this.mob.getCombatState()==1) {
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
            this.ticksUntilNextAttack = 5;
            this.ticksUntilNextRangedAttack = 10;
            this.lastCanUpdateStateCheck = 150;
            int r = this.mob.getRandom().nextInt(2048);
            if(r<=1040) {
                this.mob.setCombatState(1);
                this.stop();
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
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=450) {
                    this.mob.setCombatState(1);
                    this.stop();
                }
                this.lastCanUpdateStateCheck=200;
            }


            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.ticksUntilNextRangedAttack = Math.max(this.ticksUntilNextRangedAttack - 1, 0);
            if(this.ticksUntilNextRangedAttack<=0 && this.ticksUntilNextAttack <= 0){
                    this.ticksUntilNextRangedAttack=5;
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

                if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }
            }

        }







        protected void checkForAttack(double distance, double reach){
            if (distance <= reach && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=360) {

                    this.mob.setAnimationState(21);

                }else if(r<=520){

                    this.mob.setAnimationState(22);

                }else if(r<=720){

                    this.mob.setAnimationState(23);

                } else if(r<=960){

                    this.mob.setAnimationState(24);

                }else if(r<=1850){

                    this.mob.setAnimationState(25);

                }else if(r<=1650){

                    this.mob.setAnimationState(26);

                }else{

                    this.mob.setAnimationState(27);
                }
            }
            if (distance <= reach*4 && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=36) {

                    this.mob.setAnimationState(27);

                }else if(r<=84){

                    this.mob.setAnimationState(11);

                }
                else if(r<=132){

                    this.mob.setAnimationState(12);

                }
            }
            if (this.ticksUntilNextRangedAttack <= 2 && this.ticksUntilNextAttack <= 2) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=560) {

                    this.mob.setAnimationState(31);

                }
            }

        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(this.mob.getBbWidth() * 16.0F * this.mob.getBbWidth());
        }

    }

    public class AttackGoalRunning extends Goal {


        private final double speedModifier = 2.0f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final HollowAssassin mob;
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




        public AttackGoalRunning(HollowAssassin entityIn) {
            this.mob = entityIn;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            if(this.mob.getAnimationState()==0&&this.mob.getCombatState()==1) {
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
            if(this.mob.getAnimationState()!=0||this.mob.getCombatState()!=1) {
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
            this.ticksUntilNextAttack = 5;
            this.lastCanUpdateStateCheck = 666;
            this.mob.setAnimationState(0);
        }
        @Override
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }
            int r = this.mob.getRandom().nextInt(2048);
            if(r<=650 || livingentity==null || !livingentity.isAlive() || livingentity.isSpectator()
                    || (livingentity instanceof Player && ((Player)livingentity).isCreative())) {

                this.mob.setCombatState(0);

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


            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

            this.lastCanUpdateStateCheck = Math.max(this.lastCanUpdateStateCheck-1, 0);
            if(this.lastCanUpdateStateCheck<=0){
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=480) {
                    this.mob.setCombatState(0);
                    this.stop();
                }
                this.lastCanUpdateStateCheck=200;
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

                if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }
            }

        }







        protected void checkForAttack(double distance, double reach){
            if (distance <= reach && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=360) {

                    this.mob.setAnimationState(21);

                }else if(r<=520){

                    this.mob.setAnimationState(22);

                }else if(r<=720){

                    this.mob.setAnimationState(23);

                } else if(r<=960){

                    this.mob.setAnimationState(24);

                }else if(r<=1850){

                    this.mob.setAnimationState(25);

                }else if(r<=1650){

                    this.mob.setAnimationState(26);

                }else{

                    this.mob.setAnimationState(27);
                }
            }
            if (distance <= reach*4 && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=36) {

                    this.mob.setAnimationState(27);

                }else if(r<=84){

                    this.mob.setAnimationState(11);

                }
                else if(r<=132){

                    this.mob.setAnimationState(12);

                }
            }



        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(this.mob.getBbWidth() * 16.0F * this.mob.getBbWidth());
        }

    }


}

