package net.fluffyman.injection.injector;

import net.fluffyman.client.FluffyX;
import net.fluffyman.injection.adaptor.HookAdaptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarOutputStream;

public class HookInjector extends XInjector {

    public HookInjector() {
        super();
    }

    @Override
    public void onInit() {
        final File original_jar = FluffyX.CLIENT_FILE;
        final File injected_jar = FluffyX.INJECTED_CLIENT_FILE;

        loadJar(original_jar, HookAdaptor.class);

        try (JarOutputStream jar_output_stream = new JarOutputStream(new FileOutputStream(injected_jar))) {
            saveJar(jar_output_stream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
