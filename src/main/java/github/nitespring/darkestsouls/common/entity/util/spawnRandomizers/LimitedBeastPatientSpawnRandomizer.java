package github.nitespring.darkestsouls.common.entity.util.spawnRandomizers;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.beast.BeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.beast.CloakedBeastPatient;
import github.nitespring.darkestsouls.common.entity.util.EntitySpawnRandomizer;
import github.nitespring.darkestsouls.config.CommonConfig;
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

public class LimitedBeastPatientSpawnRandomizer extends EntitySpawnRandomizer {


    public LimitedBeastPatientSpawnRandomizer(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 4;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData=super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        Random r = new Random();
        DarkestSoulsAbstractEntity mob;
        switch(r.nextInt(15)) {
            case 1,2,3,4,5,6:
                mob = new CloakedBeastPatient(EntityInit.CLOAKED_BEAST_PATIENT.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_cloaked_beast_patient.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
            default :
                mob = new BeastPatient(EntityInit.BEAST_PATIENT.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_beast_patient.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
        }
        //this.discard();
        return spawnGroupData;
    }

}
