package pl.rynbou.intcode.opcodes;

import pl.rynbou.intcode.Opcode;
import pl.rynbou.intcode.OpcodeInfo;
import pl.rynbou.intcode.ParameterMode;
import pl.rynbou.intcode.ProgramExecutor;

public class Opcode3 extends Opcode {

    @Override
    public void run(ProgramExecutor executor) {
        int rawCode = (int) executor.getAtPointerAndIncrement();
        long argument = executor.getAtPointerAndIncrement();
        ParameterMode mode = ParameterMode.modesFromRaw(rawCode).get(0);

        executor.set(executor.getWriteAddress(mode, argument), executor.getIo().in());
    }

    @Override
    public OpcodeInfo getOpcodeInfo() {
        return OpcodeInfo.INPUT;
    }
}
