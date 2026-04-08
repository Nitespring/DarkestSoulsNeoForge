package github.nitespring.darkestsouls.common.entity.util.spawnRandomizers;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctor;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctorLantern;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctorStick;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonCurvedSwords;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonFalchion;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonSpear;
import github.nitespring.darkestsouls.common.entity.util.EntitySpawnRandomizer;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SkeletonSpawnRandomizer extends EntitySpawnRandomizer {


    public SkeletonSpawnRandomizer(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 6;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData=super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        Random r = new Random();
        DarkestSoulsAbstractEntity mob;
        switch(r.nextInt(15)) {
            case 1,2,3,4,5:
                mob = new SkeletonSpear(EntityInit.SKELETON_SPEAR.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
                this.discard();
                break;
            case 6,7:
                mob = new SkeletonCurvedSwords(EntityInit.SKELETON_CURVED_SWORDS.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
                this.discard();
                break;
            default :
                mob = new SkeletonFalchion(EntityInit.SKELETON_FALCHION.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
                this.discard();
                break;
        }
        //this.discard();
        return spawnGroupData;
    }

}
