package net.fluffyman.client.hooks;

import net.fluffyman.client.FluffyX;
import net.fluffyman.client.events.InterfaceOpenedEvent;
import net.fluffyman.injection.annotation.FieldHook;
import org.objectweb.asm.Opcodes;

public class XClient {
    @FieldHook(
            name = "bj",
            owner = "com/client/Client",
            descriptor = "I",
            type = Opcodes.PUTSTATIC
    )
    public static void interfce_changed_hook(int interface_id) {
        if(interface_id != -1) {
            FluffyX.event_bus.post(new InterfaceOpenedEvent(interface_id));
        }
    }
}
