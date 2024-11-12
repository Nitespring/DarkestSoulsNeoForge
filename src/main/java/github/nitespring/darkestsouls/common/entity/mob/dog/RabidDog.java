package github.nitespring.darkestsouls.common.entity.mob.dog;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

import java.util.Random;

public class RabidDog extends Dog{


    public RabidDog(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public int getDefaultDogType() {
        return 0;
    }

    @Override
    protected void defineDogType() {
        Random rn = new Random();
        int r = rn.nextInt(12) + 1;
        switch(r) {
            case 1,2,3:
                this.setDogType(1);
                break;
            case 4,5,6:
                this.setDogType(2);
                break;
            default:
                this.setDogType(0);
                break;
        }
    }

    @Override
    protected int getDSDefaultTeam() {
        return 3;
    }


}
