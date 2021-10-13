package net.fluffyman.reflection;

import net.fluffyman.injection.annotation.FieldHook;
import net.fluffyman.injection.annotation.InjectInterface;
import net.fluffyman.injection.annotation.InjectMethod;
import net.fluffyman.injection.info.FieldHookInfo;
import net.fluffyman.injection.info.MethodInjectionInfo;

import java.io.IOException;
import java.util.*;

public class AnnotationHandler {
    public static List<FieldHookInfo> field_hooks_list;
    public static Map<String, String> interface_hooks_map;
    public static Map<String, List<MethodInjectionInfo>> method_hooks_map;

    static {
        try {
            interface_hooks_map = getInterfaceHooks(ClassHandler.getAllClasses("net.fluffyman.api"));
            method_hooks_map = getMethodHooks(ClassHandler.getAllClasses("net.fluffyman.api"));
            field_hooks_list = getFieldHooks(ClassHandler.getAllClasses("net.fluffyman.client.hooks"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getInterfaceHooks(List<Class> classes) {
        Map<String, String> interface_hook_map = new HashMap<>();
        classes.forEach(clazz -> {
            final InjectInterface annotation = (InjectInterface) clazz.getAnnotation(InjectInterface.class);

            if(Objects.nonNull(annotation)) {
                interface_hook_map.put(
                        annotation.target(),
                        clazz.getName().replace('.', '/')
                );
            }

        });

        return interface_hook_map;
    }

    public static Map<String, List<MethodInjectionInfo>> getMethodHooks(List<Class> classes) {
        Map<String, List<MethodInjectionInfo>> method_hook_info_map = new HashMap<>();
        classes.forEach(clazz -> {
            final List<MethodInjectionInfo> method_hook_list = new ArrayList<>();
            Arrays.stream(clazz.getMethods()).forEach(method -> {
                final InjectMethod annotation = method.getAnnotation(InjectMethod.class);

                if(Objects.nonNull(annotation)) {
                    method_hook_list.add(new MethodInjectionInfo(
                            annotation.access(),
                            annotation.name(),
                            annotation.owner(),
                            annotation.descriptor(),
                            annotation.field_name(),
                            annotation.field_owner(),
                            annotation.field_desc(),
                            annotation.return_type()
                    ));
                }
            });
            method_hook_info_map.put("com/client/Client", method_hook_list);
        });

        return method_hook_info_map;
    }

    public static List<FieldHookInfo> getFieldHooks(List<Class> classes) {
        List<FieldHookInfo> field_hook_info_list = new ArrayList<>();
        classes.forEach(clazz -> {
            Arrays.stream(clazz.getMethods()).forEach(method -> {
                final FieldHook annotation = method.getAnnotation(FieldHook.class);

                if(Objects.nonNull(annotation)) {
                    field_hook_info_list.add(
                            new FieldHookInfo(
                                    annotation.name(),
                                    annotation.owner(),
                                    annotation.descriptor(),
                                    method.getName(),
                                    method.getDeclaringClass().getName().replace('.', '/'),
                                    method.getDeclaringClass().descriptorString(),
                                    annotation.type()
                            )
                    );
                }
            });
        });

        return field_hook_info_list;
    }
}

