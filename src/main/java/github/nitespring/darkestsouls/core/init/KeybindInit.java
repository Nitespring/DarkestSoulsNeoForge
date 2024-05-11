package github.nitespring.darkestsouls.core.init;


import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = DarkestSouls.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class KeybindInit {
	public static KeyMapping trickKeybind;
	public static KeyMapping parryKeyBind;
	
	    /*public static void register()
	    {
	    	
       	 trickKeybind = new KeyMapping("key.trick", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc");
       	//.registerKeyBinding(trickKeybind);
    	 reloadKeybind = new KeyMapping("key.reload", GLFW.GLFW_KEY_R, "key.categories.misc");
    	//.registerKeyBinding(reloadKeybind);
	       
	    }*/
	    
	    
	    @SubscribeEvent
	    public static void registerKeyBinds(RegisterKeyMappingsEvent event) {
	    	
	    	event.register(trickKeybind = new KeyMapping("key.transform", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc"));
	       
	    	event.register(parryKeyBind = new KeyMapping("key.parry", GLFW.GLFW_KEY_R, "key.categories.misc"));
	    	
	    }
	
}
