package net.fluffyman.injection.injector;

import com.google.common.io.ByteStreams;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class XInjector implements ClassInjector {
    private final Map<String, ClassNode> class_map = new HashMap();
    private final Map<String, byte[]> file_map = new HashMap();

    private ClassWriter class_writer;

    public XInjector() {
        this.class_writer =  new ClassWriter(ClassWriter.COMPUTE_MAXS);
    }

    @Override
    public <T extends ClassNode> void loadJar(File file, Class<T> clazz) {
        try {
            JarFile jar_file = new JarFile(file);
            jar_file.stream().forEach(entry -> readJar(jar_file, entry, clazz));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public <T extends ClassNode> void readJar(JarFile jar, JarEntry entry, Class<T> clazz) {
        try (InputStream input_stream = jar.getInputStream(entry)) {
            final byte[] bytes = ByteStreams.toByteArray(input_stream);

            if(!entry.getName().endsWith(".class")) {
                file_map.put(entry.getName(), bytes);
                return;
            }

            ClassNode class_node = clazz.getConstructor(ClassVisitor.class).newInstance(class_writer);
            final ClassReader class_reader = new ClassReader(bytes);
            class_reader.accept(class_node, ClassReader.SKIP_DEBUG);
            class_map.put(class_node.name, class_node);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void saveJar(JarOutputStream output_stream) {
        class_map.values().forEach(node -> {
            final JarEntry entry = new JarEntry(node.name + ".class");
            class_writer =  new ClassWriter(ClassWriter.COMPUTE_MAXS);

            try {
                output_stream.putNextEntry(entry);
                node.accept(class_writer);
                output_stream.write(class_writer.toByteArray());
                output_stream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        file_map.entrySet().forEach(entry -> {
            try {
                output_stream.putNextEntry(new JarEntry(entry.getKey()));
                output_stream.write(entry.getValue());
                output_stream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onInit() {

    }
}
