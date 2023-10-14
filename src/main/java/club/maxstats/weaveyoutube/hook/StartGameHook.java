package club.maxstats.weaveyoutube.hook;

import net.weavemc.loader.api.Hook;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class StartGameHook extends Hook {
    public StartGameHook() {
        super("net/minecraft/client/Minecraft");
    }

    @Override
    public void transform(@NotNull ClassNode classNode, @NotNull AssemblerConfig assemblerConfig) {
        MethodNode mn = classNode.methods.stream().filter(methodNode -> methodNode.name.equals("startGame")).findFirst().get();
        InsnList inject = new InsnList();
        System.out.println();
        inject.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
        inject.add(new LdcInsnNode("Hello World from StartGameHook"));
        inject.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"));
        mn.instructions.insert(inject);
    }
}
