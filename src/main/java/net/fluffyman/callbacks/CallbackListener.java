package net.fluffyman.callbacks;

import com.google.common.eventbus.Subscribe;
import net.fluffyman.client.events.InterfaceOpenedEvent;

public class CallbackListener {

    @Subscribe
    public void onInterfaceChanged(InterfaceOpenedEvent interface_event) {
        System.out.println("Interface id opened: " + interface_event.interface_id);
    }
}
