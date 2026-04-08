package github.nitespring.darkestsouls.common.entity.util;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public abstract class EntitySpawnRandomizer extends DarkestSoulsAbstractEntity {


    public EntitySpawnRandomizer(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    public void finalizeMobSpawn(DarkestSoulsAbstractEntity mob){
        mob.setPos(this.position());
        mob.setDSTeam(getDSTeam());
        if(getOwner()!=null){mob.setOwner(getOwner());}
    }

    public static  AttributeSupplier.Builder setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.0D)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.ATTACK_DAMAGE, 0D)
                .add(Attributes.ATTACK_SPEED, 0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0D)
                .add(Attributes.FOLLOW_RANGE, 0)
                .add(Attributes.STEP_HEIGHT, 0)
                .add(Attributes.GRAVITY,0);
    }
}
