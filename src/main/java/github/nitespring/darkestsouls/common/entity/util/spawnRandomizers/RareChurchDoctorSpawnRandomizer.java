package github.nitespring.darkestsouls.common.entity.util.spawnRandomizers;

import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctor;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctorLantern;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctorStick;
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

public class RareChurchDoctorSpawnRandomizer extends EntitySpawnRandomizer {


    public RareChurchDoctorSpawnRandomizer(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 5;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData=super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        Random r = new Random();
        ChurchDoctor mob;
        switch(r.nextInt(15)) {
            case 8,9:
                mob = new ChurchDoctorLantern(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_church_doctor_flamesprayer.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
            case 10,11,12:
                mob = new ChurchDoctorLantern(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_church_doctor_scythe.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
            default :
                mob = new ChurchDoctorStick(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_church_doctor_crucifix.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
        }
        //this.discard();
        return spawnGroupData;
    }

}
