package net.fluffyman.api;

import net.fluffyman.injection.annotation.InjectInterface;
import net.fluffyman.injection.annotation.InjectMethod;
import org.objectweb.asm.Opcodes;

@InjectInterface(
        target = "com/client/Client"
)
public interface Client {
    /**
     * Gets the current x destination for this client instance.
     * @return X Destination
     */
    @InjectMethod(
            target = "com/client/Client",
            access = Opcodes.ACC_PUBLIC,
            owner = "com/cient/Client",
            name = "getDestinationX()",
            descriptor = "()I",
            field_name = "kF",
            field_owner = "com/client/Client",
            field_desc = "I",
            return_type = Opcodes.IRETURN
    )
    int getDestinationX();

    /**
     * Gets the current y destination for this client instance.
     * @return Y Destination.
     */
    @InjectMethod(
            target = "com/client/Client",
            access = Opcodes.ACC_PUBLIC,
            owner = "com/cient/Client",
            name = "getDestinationY()",
            descriptor = "()I",
            field_name = "kG",
            field_owner = "com/client/Client",
            field_desc = "I",
            return_type = Opcodes.IRETURN
    )
    int getDestinationY();
}
