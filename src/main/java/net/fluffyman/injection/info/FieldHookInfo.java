package net.fluffyman.injection.info;

import java.lang.reflect.Method;

public class FieldHookInfo {
    private final String name;
    private final String owner;
    private final String descriptor;
    private final String hook_name;
    private final String hook_owner;
    private final String hook_descriptor;
    private final int type;

    public FieldHookInfo(String name, String owner, String descriptor, String hook_name, String hook_owner, String hook_descriptor, int type){
        this.name = name;
        this.owner = owner;
        this.descriptor = descriptor;
        this.hook_name = hook_name;
        this.hook_owner = hook_owner;
        this.hook_descriptor = hook_descriptor;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public String getHookName() {
        return this.hook_name;
    }

    public String getHookOwner() {
        return this.hook_owner;
    }

    public String getHookDescriptor() {
        return this.hook_descriptor;
    }

    public int getType() {
        return this.type;
    }
}
