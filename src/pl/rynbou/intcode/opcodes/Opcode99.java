package pl.rynbou.intcode.opcodes;

import pl.rynbou.intcode.Opcode;
import pl.rynbou.intcode.OpcodeInfo;
import pl.rynbou.intcode.ProgramExecutor;

public class Opcode99 extends Opcode {

    @Override
    public void run(ProgramExecutor executor) {
        executor.stop();
    }

    @Override
    public OpcodeInfo getOpcodeInfo() {
        return OpcodeInfo.END;
    }
}
