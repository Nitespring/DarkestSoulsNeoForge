package github.nitespring.darkestsouls.common.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ICustomSweepAttackItem {
    public abstract void performSweepAttack(Player player, ItemStack item);
}
