package net.fluffyman.client.loader;

import net.fluffyman.client.loader.rs.RSClassLoader;

import java.lang.reflect.Method;

public class Loader {
    /**
     * Initialize the client internally using the method a(String... args)
     * @param args arguments to pass to the internal method.
     *
     * For all arguments see:
     *  {@link #}
     */
    public static Object initClientInternal(String... args) throws Exception {
        RSClassLoader class_loader = new RSClassLoader();
        Class<?> client = class_loader.loadClass("com.client.Client");
        Method initialize_client = client.getMethod("a", boolean.class, String[].class);
        initialize_client.setAccessible(true);
        return initialize_client.invoke(client, false, (Object) args);
    }
}
