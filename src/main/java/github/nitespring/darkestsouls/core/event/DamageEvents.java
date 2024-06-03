package github.nitespring.darkestsouls.core.event;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

@EventBusSubscriber(modid = DarkestSouls.MODID)
public class DamageEvents {
    @SubscribeEvent
    public static void applyDamageEvent(final LivingHurtEvent event){
        DamageSource source = event.getSource();
        //System.out.println(event.getAmount());
        //System.out.println(event.getEntity().getType());
        //System.out.println(event.getEntity().getType().getTags().count());
        if(source.is(DamageTypes.PLAYER_ATTACK)&&source.getEntity()!=null&&source.getEntity() instanceof Player attacker){

            if(attacker.hasItemInSlot(EquipmentSlot.MAINHAND)){
                ItemStack itemStack = attacker.getItemInHand(InteractionHand.MAIN_HAND);

                if (itemStack.getItem() instanceof Weapon weapon) {
                    if(event.getEntity().getType().is(CustomEntityTags.BEAST)) {
                        event.setAmount((float) (event.getAmount()*(1+0.1*weapon.getSerratedLevel(itemStack))));
                        //System.out.println(event.getAmount());
                    }
                } else {
                    if(itemStack.isEnchanted()) {
                        if(itemStack.getEnchantmentLevel(EnchantmentInit.SERRATED.get())>=1){
                            if(event.getEntity().getType().is(CustomEntityTags.BEAST)) {
                                event.setAmount((float) (event.getAmount() * (1 + (0.1 * itemStack.getEnchantmentLevel(EnchantmentInit.SERRATED.get())))));
                                System.out.println(event.getAmount());
                            }
                        }
                    }
                }

            }

        }

    }




}
