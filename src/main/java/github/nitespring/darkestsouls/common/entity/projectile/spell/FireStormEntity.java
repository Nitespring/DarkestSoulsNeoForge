package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.util.CustomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class FireStormEntity extends AbstractHurtingProjectile implements ItemSupplier {

    protected int lifeTicks = 0;
    protected int maxLifeTicks = 120;

    protected float damage = 2.0f;

    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;

    public FireStormEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
        super(p_36833_, p_36834_);
    }

    public FireStormEntity(EntityType<? extends AbstractHurtingProjectile> p_310629_, double p_311590_, double p_312782_, double p_309484_, Level p_311660_) {
        super(p_310629_, p_311590_, p_312782_, p_309484_, p_311660_);
    }

    /*public MagmaBurstEntity(EntityType<? extends AbstractHurtingProjectile> p_36826_, LivingEntity p_36827_, double p_36828_, double p_36829_, double p_36830_, Level p_36831_) {
        super(p_36826_, p_36827_, p_36828_, p_36829_, p_36830_, p_36831_);
    }*/

    @Override
    public ItemStack getItem() {
        return Items.BLAZE_POWDER.getDefaultInstance();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_20052_) {

        if (p_20052_.hasUUID("Owner")) {
            this.ownerUUID = p_20052_.getUUID("Owner");
        }

    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_20139_) {

        if (this.ownerUUID != null) {
            p_20139_.putUUID("Owner", this.ownerUUID);
        }

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity e) {

        return new ClientboundAddEntityPacket(this,e);
    }


    @Override
    public void tick() {
        super.tick();
        if(++lifeTicks>=maxLifeTicks){
            this.doRemoval();
        }
        ParticleOptions particle = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.LAVA.defaultBlockState());
        ParticleOptions particle1 = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MAGMA_BLOCK.defaultBlockState());
        ParticleOptions particle2 = ParticleTypes.FLAME;
        RandomSource rng = this.random;
        float width = this.getBbWidth() * 0.5f;
        float height = this.getBbHeight() * 0.5f;
        Vec3 pos = this.position();
        Level world = this.level();
        for (int i = 0; i < 2; ++i) {

            Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
                    rng.nextDouble() * width - width / 2);
            if(world instanceof ServerLevel) {
                ((ServerLevel) world).sendParticles( particle, pos.x, pos.y, pos.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                ((ServerLevel) world).sendParticles( particle1, pos.x, pos.y, pos.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                ((ServerLevel) world).sendParticles( particle2, pos.x, pos.y, pos.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);

            }
        }
        this.doGravity();


        BlockPos blockPos = new BlockPos((int) pos.x, (int) pos.y, (int) pos.z);

        if(level().getBlockState(blockPos).is(CustomBlockTags.FLAME_BREAKABLE)){
            level().destroyBlock(blockPos, true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
        }
        if(BaseFireBlock.canBePlacedAt(level(),blockPos, Direction.getNearest(pos.x,pos.y,pos.z))) {
            BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
            level().setBlock(blockPos, blockstate, 11);
            level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
        }
    }

    public void doGravity(){
        this.setDeltaMovement(this.getDeltaMovement().add(0,-0.1,0));

    }
    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {

        Entity e = p_37259_.getEntity();
        if(e != this.getOwner() && !e.isAlliedTo(getOwner())) {
            e.hurt(this.level().damageSources().inFire(),damage);
            this.doRemoval();
            if(p_37259_.getEntity() instanceof DarkestSoulsAbstractEntity e1){
                if(!this.level().isClientSide()) {
                    e1.damagePoiseHealth(2);
                    e1.damagePostureHealth(1);
                }
            }
        }


    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
            this.discard();
    }

    public int getLifeTicks() {
        return maxLifeTicks;
    }

    public void setLifeTicks(int lifeTicks) {
        this.maxLifeTicks = lifeTicks;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void doRemoval(){
        this.discard();
    }


}
