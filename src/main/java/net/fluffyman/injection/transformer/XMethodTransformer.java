package net.fluffyman.injection.transformer;

import net.fluffyman.injection.info.MethodInjectionInfo;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class XMethodTransformer implements Transformer {
    private final MethodInjectionInfo info;

    public XMethodTransformer(MethodInjectionInfo info) {
        this.info = info;
    }

    @Override
    public void transform(ClassNode class_node) {
        MethodNode method_node = new MethodNode(info.getAccess(), info.getName(), info.getDescriptor(), null, null);
        if(info.getAccess() != Opcodes.ACC_STATIC) {
            method_node.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
            method_node.instructions.add(
                    new FieldInsnNode(
                        Opcodes.GETFIELD,
                        info.getFieldOwner(),
                        info.getFieldName(),
                        info.getFieldDescriptor()
                    )
            );
        } else {
            method_node.instructions.add(
                    new FieldInsnNode(
                            Opcodes.GETSTATIC,
                            info.getFieldOwner(),
                            info.getFieldName(),
                            info.getFieldDescriptor()
                    )
            );
        }
        method_node.instructions.add(new InsnNode(info.getReturnType()));
        method_node.visitMaxs(method_node.instructions.size(), method_node.instructions.size());
        method_node.visitEnd();
        class_node.methods.add(method_node);
    }
}
