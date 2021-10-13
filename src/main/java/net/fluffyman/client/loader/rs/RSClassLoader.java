package net.fluffyman.client.loader.rs;

import com.google.common.io.ByteStreams;
import net.fluffyman.client.FluffyX;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class RSClassLoader extends ClassLoader {

    public RSClassLoader() {
        super();
    }

    /**
     * {@link #findClass(String)}}
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try(JarFile client_jar = new JarFile(FluffyX.INJECTED_CLIENT_FILE)) {
            JarEntry entry = client_jar.getJarEntry(replace(name));
            InputStream i_stream = client_jar.getInputStream(entry);
            byte[] bytes = ByteStreams.toByteArray(i_stream);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException exception) {
            throw new ClassNotFoundException(null, exception);
        }
    }

    /**
     *
     * @param class_name name of the class file
     * @return class name replacing all instances of '.' with the file character seperator.
     */
    private String replace(String class_name) {
        return class_name.replace('.', '/').concat(".class");
    }
}
