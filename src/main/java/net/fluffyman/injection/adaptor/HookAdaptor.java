package net.fluffyman.injection.adaptor;

import net.fluffyman.injection.info.FieldHookInfo;
import net.fluffyman.injection.info.MethodInjectionInfo;
import net.fluffyman.injection.transformer.InterfaceTransformer;
import net.fluffyman.injection.transformer.XFieldTransformer;
import net.fluffyman.injection.transformer.XMethodTransformer;
import net.fluffyman.reflection.AnnotationHandler;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HookAdaptor extends ClassNode {
    protected final ClassVisitor class_visitor;

    public HookAdaptor(ClassVisitor class_visitor) {
        super(Opcodes.ASM9);
        this.class_visitor = class_visitor;
    }

    @Override
    public void visitEnd() {
        /**
         * Interface injections
         */
        Map<String, String> interface_hooks = AnnotationHandler.interface_hooks_map;

        interface_hooks.entrySet().forEach(entry -> {
            if(entry.getKey().equals(this.name)) {
                InterfaceTransformer interface_transformer = new InterfaceTransformer(entry.getValue());
                interface_transformer.transform(this);
            }
        });

        /**
         * Method injections
         */
        Map<String, List<MethodInjectionInfo>> method_hook_map = AnnotationHandler.method_hooks_map;

        List<MethodInjectionInfo> method_hook_list = method_hook_map.get(this.name);

        if(Objects.nonNull(method_hook_list)) {
            method_hook_list.forEach(method_hook -> {
                XMethodTransformer method_transformer = new XMethodTransformer(method_hook);
                method_transformer.transform(this);
            });
        }

        /**
         * Field hooks
         */
        List<FieldHookInfo> field_hooks = AnnotationHandler.field_hooks_list;

        field_hooks.forEach(hook -> {
            XFieldTransformer field_transformer = new XFieldTransformer(hook);
            field_transformer.transform(this);
        });

        accept(class_visitor);
    }
}
