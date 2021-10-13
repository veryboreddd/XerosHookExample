package net.fluffyman.client;

import com.google.common.eventbus.EventBus;
import net.fluffyman.api.Client;
import net.fluffyman.callbacks.CallbackListener;
import net.fluffyman.client.loader.Loader;
import net.fluffyman.injection.injector.HookInjector;

import java.io.File;

public class FluffyX {
    public static final String CLIENT_NAME = "Xeros.jar";
    public static final String INJECTED_CLIENT_NAME = "Xeros_Injected.jar";
    public static final String DIRECTORY_NAME = ".fluffyx";
    public static final File FLUFFYX_DIRECTIRY = new File(System.getProperty("user.home"), DIRECTORY_NAME);
    public static final File CLIENT_FILE = new File(FLUFFYX_DIRECTIRY, CLIENT_NAME);
    public static final File INJECTED_CLIENT_FILE = new File(FLUFFYX_DIRECTIRY, INJECTED_CLIENT_NAME);

    public static Client client;
    public static EventBus event_bus;

    public static void main(String[] args) {
        injectHooks();
        setupEventBus();
        loadClient();
    }

    private static void loadClient() {
        try {
            client = (Client) Loader.initClientInternal("-d");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void injectHooks() {
        HookInjector injector = new HookInjector();
        injector.onInit();
    }

    private static void setupEventBus() {
        event_bus = new EventBus();
        event_bus.register(new CallbackListener());
    }
}
