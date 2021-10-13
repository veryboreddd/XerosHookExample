package net.fluffyman.injection.injector;

import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public interface ClassInjector extends Injector {
    <T extends ClassNode> void loadJar(File file, Class<T> clazz);
    <T extends ClassNode> void readJar(JarFile jar, JarEntry entry, Class<T> clazz);
    void saveJar(JarOutputStream output_stream);
}
