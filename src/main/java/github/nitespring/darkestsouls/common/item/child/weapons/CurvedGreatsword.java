package github.nitespring.darkestsouls.common.item.child.weapons;

import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.WeaponAttackEntity;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CurvedGreatsword extends Weapon {
    public CurvedGreatsword(Tier tier, float attack, float speed, float reach, float knockback, int poise, int posture, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, posture, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public void performSweepAttack(Player playerIn, ItemStack stackIn) {
        if(CommonConfig.do_special_attacks.get()) {
            if (!playerIn.isUsingItem()) {
                Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 2.0, 0.4, playerIn.getLookAngle().z() * 2.0);

                Level levelIn = playerIn.level();
                WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.CURVED_GREATSWORD.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
                entity.setOwner(playerIn);
                entity.setItemStack(stackIn);
                entity.setMaxTargets(this.getMaxTargets(playerIn, stackIn));
                entity.setDamage(
                        this.getSweepAttackDamage(playerIn, stackIn),
                        this.getSweepPoiseDamage(playerIn, stackIn),
                        this.getSweepPostureDamage(playerIn, stackIn),
                        this.getFireAttack(playerIn,stackIn),
                        this.getSmiteAttack(playerIn,stackIn),
                        this.getBaneOfArthropodsAttack(playerIn,stackIn),
                        this.getBeastHunterAttack(playerIn,stackIn),
                        this.getBloodAttack(playerIn,stackIn),
                        this.getPoisonAttack(playerIn,stackIn),
                        this.getToxicAttack(playerIn,stackIn),
                        this.getRotAttack(playerIn,stackIn),
                        this.getFrostAttack(playerIn,stackIn),
                        this.getWitherAttack(playerIn,stackIn));
                entity.setHitboxModifications(1.2f, 0f, 0.4f, 2.0f);
                entity.configureTicks(6, 10, 1, 2);
                levelIn.addFreshEntity(entity);
            }
        }
    }

    @Override
    public void doRightClickAction(Player playerIn, ItemStack stackIn) {
        super.doRightClickAction(playerIn, stackIn);
    }
}
