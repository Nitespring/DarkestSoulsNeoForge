package github.nitespring.darkestsouls.common.entity.util;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DamageHitboxEntity extends Entity {

	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;
	@Nullable
	private LivingEntity target;
	private int maxTargets = 0; //if 0 there is no max number of targets
	private int delayTicks = 5;
	private int hitboxType = 0;
	private float damage = 4.0f;
	private float hitboxScaleWidth = 0.0f;
	private float hitboxScaleHeight = 0.0f;
	private List<LivingEntity> hitEntities = new ArrayList<LivingEntity>();

	public DamageHitboxEntity(EntityType<?> e, Level level) {
		super(e, level);
	}

	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg) {
		this(e, level);
		this.setPos(pos);
		this.damage = dmg;
	}

	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks) {
		this(e, level, pos, dmg);
		this.delayTicks = delayTicks;
	}

	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks, float scale) {
		this(e, level, pos, dmg, delayTicks);
		this.hitboxScaleWidth = scale;
	}

	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks, float scale, float height) {
		this(e, level, pos, dmg, delayTicks, scale);
		this.hitboxScaleHeight = height;
	}

	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks, float scale, float height, int maxTargets) {
		this(e, level, pos, dmg, delayTicks, scale, height);
		this.maxTargets = maxTargets;
	}

	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks, float scale, float height, int maxTargets, LivingEntity target) {
		this(e, level, pos, dmg, delayTicks, scale, height, maxTargets);
		this.target = target;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {

	}

	@Override
	protected void readAdditionalSaveData(CompoundTag p_20052_) {
		if (p_20052_.hasUUID("Owner")) {
			this.ownerUUID = p_20052_.getUUID("Owner");
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag p_20139_) {
		if (this.ownerUUID != null) {
			p_20139_.putUUID("Owner", this.ownerUUID);
		}

	}

	public void setOwner(@Nullable LivingEntity p_36939_) {
		this.owner = p_36939_;
		this.ownerUUID = p_36939_ == null ? null : p_36939_.getUUID();
	}

	public void setHitboxType(int i){
		this.hitboxType=i;
	}
	public int getHitboxType(){
		return hitboxType;
	}

	@Nullable
	public LivingEntity getOwner() {
		if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
			Entity entity = ((ServerLevel) this.level()).getEntity(this.ownerUUID);
			if (entity instanceof LivingEntity) {
				this.owner = (LivingEntity) entity;
			}
		}

		return this.owner;
	}

	public void setHitboxScaleWidth(float hitboxScaleAbsolute) {
		this.hitboxScaleWidth = hitboxScaleAbsolute;
	}

	public void setHitboxScaleHeight(float hitboxScaleHeight) {
		this.hitboxScaleHeight = hitboxScaleHeight;
	}

	public void setTarget(@Nullable LivingEntity e) {
		this.target = e;
	}

	public LivingEntity getTarget() {
		return this.target;
	}

	public void setMaxTargets(int i) {
		this.maxTargets = i;
	}

	public int getMaxTargets() {
		return this.maxTargets;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity e) {
		return new ClientboundAddEntityPacket(this,e);
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
	public boolean isOnFire() {
		return false;
	}

	@Override
	public void tick() {
		delayTicks--;
		if (delayTicks <= 0) {

			this.remove(RemovalReason.DISCARDED);
		}
		for (LivingEntity localTarget : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(hitboxScaleWidth, hitboxScaleHeight, hitboxScaleWidth))) {
			if ((this.target != null) && (localTarget == this.target)) {
				this.attackLocalTarget(localTarget);
			} else if (this.getOwner() == null || !localTarget.isAlliedTo(this.getOwner())) {

				if (this.getMaxTargets() <= 0 || hitEntities.size() < this.getMaxTargets()) {
				this.attackLocalTarget(localTarget);
				}

			}
		}
		super.tick();
	}

	private void attackLocalTarget(LivingEntity localTarget){
		if (!hitEntities.contains(localTarget)) {
			this.dealDamageTo(localTarget);
			hitEntities.add(localTarget);
		}
       }
	 
	private void dealDamageTo(LivingEntity target) {
		if(!this.level().isClientSide) {
			LivingEntity owner = this.getOwner();
			if (target == null || target == this.getOwner()) {
				return;
			}
			if (target.isAlive() && !target.isInvulnerable() && target != owner) {
				if (owner != null) {
					if (owner.isAlliedTo(target) || owner == target) {
						return;
					} else {
						switch (this.hitboxType) {
							case 1:
								target.hurt(this.damageSources().mobAttack(owner), damage);
								target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0), this.getOwner());
								break;
							case 2:
								target.hurt(this.damageSources().mobAttack(owner), damage);
								target.addEffect(new MobEffectInstance(MobEffects.POISON, 140, 1), this.getOwner());
								break;
							case 3:
								target.hurt(this.damageSources().mobAttack(owner), damage);
								if (target.hasEffect(EffectInit.BLEED)) {
									int amount = target.getEffect(EffectInit.BLEED).getAmplifier() + 2;
									target.removeEffect(EffectInit.BLEED);
									target.addEffect(new MobEffectInstance(EffectInit.BLEED, 400, amount));
								} else {
									target.addEffect(new MobEffectInstance(EffectInit.BLEED, 400, 1), this.getOwner());
								}
								break;
							case 4:
								target.hurt(this.damageSources().mobAttack(owner), damage);
								if (target.hasEffect(EffectInit.BLEED)) {
									int amount = target.getEffect(EffectInit.BLEED).getAmplifier() + 1;
									target.removeEffect(EffectInit.BLEED);
									target.addEffect(new MobEffectInstance(EffectInit.BLEED, 400, amount));
								} else {
									target.addEffect(new MobEffectInstance(EffectInit.BLEED, 400, 0), this.getOwner());
								}
								break;
							case 5:
								target.hurt(this.damageSources().mobAttack(owner), damage);
								target.addEffect(new MobEffectInstance(MobEffects.WITHER, 140, 0), this.getOwner());
								break;
							case 6:
								target.hurt(this.damageSources().inFire(), damage);
								target.igniteForTicks(60);
								break;
							case 7:
								target.hurt(this.damageSources().mobAttack(owner), damage);
								target.addEffect(new MobEffectInstance(EffectInit.FROST, 100, 0), this.getOwner());
								break;
							default:
								target.hurt(this.damageSources().mobAttack(owner), damage);
								break;
						}
					if(target instanceof DarkestSoulsAbstractEntity e1 && (this.getOwner()==null||target!=this.getOwner()&&!target.isAlliedTo(this.getOwner()))){
						if(!this.level().isClientSide()) {
							e1.damagePoiseHealth(Math.round(damage));
							e1.damagePostureHealth(Math.round(damage));
						}
					}
					}
				} else {
					target.hurt(this.damageSources().generic(), damage);
					if (target instanceof DarkestSoulsAbstractEntity e1) {
						if(!this.level().isClientSide()) {
							e1.damagePoiseHealth(Math.round(damage));
							e1.damagePostureHealth(Math.round(damage));
						}
					}
				}

			}
		}
	}

	 
}
