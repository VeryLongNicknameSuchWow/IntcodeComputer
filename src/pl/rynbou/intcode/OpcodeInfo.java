package pl.rynbou.intcode;

import pl.rynbou.intcode.opcodes.*;

public enum OpcodeInfo {

    ADD(1,3, Opcode1.class),
    MULTIPLY(2,3, Opcode2.class),
    INPUT(3,1, Opcode3.class),
    OUTPUT(4,1, Opcode4.class),
    JUMP_IF_TRUE(5, 2, Opcode5.class),
    JUMP_IF_FALSE(6, 2, Opcode6.class),
    LESS_THAN(7, 3, Opcode7.class),
    EQUALS(8,3, Opcode8.class),
    END(99, 0, Opcode99.class);

    private final int opcode;
    private final int argsAmount;
    private final Class<? extends Opcode> clazz;

    public static OpcodeInfo recognise(int opcode) {
        for (OpcodeInfo entry : OpcodeInfo.values()) if (entry.getOpcode() == opcode % 100) return entry;
        throw new RuntimeException("Unable to recognise: " + opcode);
    }

    OpcodeInfo(int opcode, int argsAmount, Class<? extends Opcode> clazz) {
        this.opcode = opcode;
        this.argsAmount = argsAmount;
        this.clazz = clazz;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public int getArgsAmount() {
        return this.argsAmount;
    }

    public Class<? extends Opcode> getOpcodeClass() {
        return this.clazz;
    }

    public Opcode getOpcodeObject() throws Exception {
        return this.clazz.getDeclaredConstructor().newInstance();
    }
}
