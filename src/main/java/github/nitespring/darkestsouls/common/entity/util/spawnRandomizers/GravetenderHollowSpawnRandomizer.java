package github.nitespring.darkestsouls.common.entity.util.spawnRandomizers;

import github.nitespring.darkestsouls.common.entity.mob.church.*;
import github.nitespring.darkestsouls.common.entity.mob.dog.HollowDog;
import github.nitespring.darkestsouls.common.entity.mob.hollow.GravetenderHollowBrokenStraightsword;
import github.nitespring.darkestsouls.common.entity.mob.hollow.GravetenderHollowCrossbow;
import github.nitespring.darkestsouls.common.entity.mob.hollow.GravetenderHollowLongsword;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
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

public class GravetenderHollowSpawnRandomizer extends EntitySpawnRandomizer {


    public GravetenderHollowSpawnRandomizer(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 2;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData=super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        Random r = new Random();
        Hollow mob;
        switch(r.nextInt(15)) {
            case 1,2,3,4,5:
                mob = new GravetenderHollowLongsword(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_gravetender_hollow.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
            case 6,7:
                mob = new GravetenderHollowCrossbow(EntityInit.GRAVETENDER_HOLLOW_CROSSBOW.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_gravetender_hollow.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
            case 8,9,10:
                HollowDog dog  = new HollowDog(EntityInit.HOLLOW_DOG.get(), level.getLevel());
                dog.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(dog);
                level.getLevel().addFreshEntity(dog);
                this.discard();
                break;
            default :
                mob = new GravetenderHollowBrokenStraightsword(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                if(CommonConfig.spawn_gravetender_hollow.get()){level.getLevel().addFreshEntity(mob);}
                this.discard();
                break;
        }
        //this.discard();
        return spawnGroupData;
    }

}
