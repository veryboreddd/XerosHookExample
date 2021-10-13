package net.fluffyman.injection.transformer;

public interface FieldTransformer extends Transformer {
    boolean condition(String owner, String name, String desc);
}
