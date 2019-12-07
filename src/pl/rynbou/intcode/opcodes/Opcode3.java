package pl.rynbou.intcode.opcodes;

import pl.rynbou.intcode.Opcode;
import pl.rynbou.intcode.OpcodeInfo;
import pl.rynbou.intcode.ProgramExecutor;

public class Opcode3 extends Opcode {

    @Override
    public void run(ProgramExecutor executor) {
        int address;
        executor.getAtPointerAndIncrement();
        address = executor.getAtPointerAndIncrement();
        executor.set(address, executor.getIo().in());
    }

    @Override
    public OpcodeInfo getOpcodeInfo() {
        return OpcodeInfo.INPUT;
    }
}
