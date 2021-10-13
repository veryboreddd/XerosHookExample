package net.fluffyman.injection.annotation;

import org.objectweb.asm.Opcodes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InjectMethod {
    String target();
    int access();
    String owner();
    String name();
    String descriptor();
    String field_name();
    String field_owner();
    String field_desc();
    int return_type();
}
