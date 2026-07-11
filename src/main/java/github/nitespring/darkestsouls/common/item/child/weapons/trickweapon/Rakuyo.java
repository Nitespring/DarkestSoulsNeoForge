package github.nitespring.darkestsouls.common.item.child.weapons.trickweapon;

import github.nitespring.darkestsouls.common.item.DualWieldingWeapon;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class Rakuyo extends DualWieldingWeapon {

    public Rakuyo(Tier tier, float attack, float speed, float reach, float knockback, int poise, int posture, int blood, int poison, int frost, int rot, int death, int fire, int holy, int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, posture, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public Item getTransformedWeaponRight() {
        return ItemInit.RAKUYO_RIGHT.get();
    }

    @Override
    public Item getTransformedWeaponLeft() {
        return ItemInit.RAKUYO_LEFT.get();
    }
}
