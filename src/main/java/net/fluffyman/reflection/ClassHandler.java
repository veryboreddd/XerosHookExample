package net.fluffyman.reflection;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ClassHandler {
    public static List<Class> getAllClasses(String package_name) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(package_name))
                .map(clazz -> clazz.load())
                .collect(Collectors.toList());
    }
}
