package net.fluffyman.injection.transformer;

import org.objectweb.asm.tree.ClassNode;

public class InterfaceTransformer implements Transformer{
    private final String interface_name;

    public InterfaceTransformer(String interface_name) {
        this.interface_name = interface_name;
    }

    @Override
    public void transform(ClassNode class_node) {
        class_node.interfaces.add(interface_name);
    }
}
