package net.fluffyman.injection.transformer;

import org.objectweb.asm.tree.ClassNode;

public interface Transformer {
    void transform(ClassNode class_node);
}
