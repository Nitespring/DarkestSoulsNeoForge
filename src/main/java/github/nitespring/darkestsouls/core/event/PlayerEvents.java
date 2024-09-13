package github.nitespring.darkestsouls.core.event;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.ICustomSweepAttackItem;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.config.Config;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

@EventBusSubscriber(modid = DarkestSouls.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerEvents {

    @SubscribeEvent
    public static void attackEntityEvent(final AttackEntityEvent event){
        Player player = event.getEntity();
        Entity target = event.getTarget();
        Level level = player.level();
        ItemStack itemstack = player.getWeaponItem();
        if(itemstack.getItem() instanceof ICustomSweepAttackItem weapon && CommonConfig.do_special_attacks.get()){
            if(target.isAttackable()&&!target.skipAttackInteraction(player)){
                float f = player.isAutoSpinAttack() ? 1 :
                        (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);

                DamageSource damagesource = player.damageSources().playerAttack(player);
                float f1 = 0;
                if(!level.isClientSide()) {
                    f1 = EnchantmentHelper.modifyDamage((ServerLevel) level, itemstack, target,
                            level.damageSources().playerAttack(player),f) - f;
                }
                float f2 = player.getAttackStrengthScale(0.5F);
                f *= 0.2F + f2 * f2 * 0.8F;
                f1 *= f2;
                if (f > 0.0F||f1>0.0f) {
                    boolean flag4 = f2 > 0.9F;
                    boolean flag;
                    if (player.isSprinting() && flag4) {
                        player.level()
                                .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, player.getSoundSource(), 1.0F, 1.0F);
                        flag = true;
                    } else {
                        flag = false;
                    }

                    f += itemstack.getItem().getAttackDamageBonus(target, f, damagesource);
                    boolean flag1 = flag4
                            && player.fallDistance > 0.0F
                            && !player.onGround()
                            && !player.onClimbable()
                            && !player.isInWater()
                            && !player.hasEffect(MobEffects.BLINDNESS)
                            && !player.isPassenger()
                            && target instanceof LivingEntity
                            && !player.isSprinting();
                    double d0 = (double)(player.walkDist - player.walkDistO);
                    boolean flag2 = flag4 && !flag1 && !flag && player.onGround() && d0 < (double)player.getSpeed();
                    if(flag2){
                        weapon.performSweepAttack(player, itemstack);
                    }
                }

            }
        }



    }
}