package net.fluffyman.injection.transformer;

import net.fluffyman.injection.info.FieldHookInfo;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class XFieldTransformer implements FieldTransformer {
    private final FieldHookInfo info;

    public XFieldTransformer(FieldHookInfo info) {
        this.info = info;
    }
    @Override
    public boolean condition(String owner, String name, String desc) {
        if(
                info.getOwner().equals(owner) &&
                info.getName().equals(name) &&
                info.getDescriptor().equals(desc)
        ) {
            return true;
        }
        return false;
    }

    @Override
    public void transform(ClassNode class_node) {
        class_node.methods.forEach(method_node -> method_node.instructions.forEach(ins -> {
            if(ins.getOpcode() == info.getType()) {
                FieldInsnNode field_ins = (FieldInsnNode) ins;

                if(condition(field_ins.owner, field_ins.name, field_ins.desc)) {
                    InsnList list = new InsnList();
                    list.add(new InsnNode(Opcodes.DUP));
                    list.add(new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            info.getHookOwner(),
                            info.getHookName(),
                            "(I)V",
                            false
                    ));
                    method_node.instructions.insert(ins.getPrevious(), list);
                }
            }
        }));
    }
}
