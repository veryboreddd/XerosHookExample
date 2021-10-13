package net.fluffyman.injection.info;

public class MethodInjectionInfo {
    private final int access;
    private final String name;
    private final String owner;
    private final String descriptor;
    private final String field_name;
    private final String field_owner;
    private final String field_desc;
    private final int return_type;

    public MethodInjectionInfo(int access, String name, String owner, String descriptor, String field_name, String field_owner, String field_desc, int return_type) {
        this.access = access;
        this.name = name;
        this.owner = owner;
        this.descriptor = descriptor;
        this.field_name = field_name;
        this.field_owner = field_owner;
        this.field_desc = field_desc;
        this.return_type = return_type;
    }

    public int getAccess() {
        return this.access;
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

    public String getFieldName() {
        return this.field_name;
    }

    public String getFieldOwner() {
        return this.field_owner;
    }

    public String getFieldDescriptor(){
        return this.field_desc;
    }

    public int getReturnType() {
        return this.return_type;
    }
}
