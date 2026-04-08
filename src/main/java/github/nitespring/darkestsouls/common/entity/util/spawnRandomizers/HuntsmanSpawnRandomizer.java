package github.nitespring.darkestsouls.common.entity.util.spawnRandomizers;

import github.nitespring.darkestsouls.common.entity.mob.church.*;
import github.nitespring.darkestsouls.common.entity.mob.dog.HuntingDog;
import github.nitespring.darkestsouls.common.entity.mob.dog.RabidDog;
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

public class HuntsmanSpawnRandomizer extends EntitySpawnRandomizer {


    public HuntsmanSpawnRandomizer(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 3;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData=super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        Random r = new Random();
        Huntsman mob;
        switch(r.nextInt(15)) {
            case 1,2,3,4:
                mob = new HuntsmanCutlass(EntityInit.HUNTSMAN_CUTLASS.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_huntsman_cutlass.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
            case 5,6,7:
                mob = new HuntsmanPitchfork(EntityInit.HUNTSMAN_PITCHFORK.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_huntsman_pitchfork.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
            case 8,9:
                mob = new HuntsmanRifle(EntityInit.HUNTSMAN_RIFLE.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_huntsman_rifle.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
            case 10,11:
                RabidDog dog  = new RabidDog(EntityInit.RABID_DOG.get(), level.getLevel());
                dog.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(dog);
                level.getLevel().addFreshEntity(dog);
                this.discard();
                break;
            case 12:
                HuntingDog hdog  = new HuntingDog(EntityInit.HUNTING_DOG.get(), level.getLevel());
                hdog.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(hdog);
                level.getLevel().addFreshEntity(hdog);
                this.discard();
                break;
            default :
                mob = new HuntsmanAxe(EntityInit.HUNTSMAN_AXE.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_huntsman_axe.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
        }
        //this.discard();
        return spawnGroupData;
    }

}
