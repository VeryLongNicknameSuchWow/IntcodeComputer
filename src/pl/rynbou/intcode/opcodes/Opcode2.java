package pl.rynbou.intcode.opcodes;

import pl.rynbou.intcode.Opcode;
import pl.rynbou.intcode.OpcodeInfo;
import pl.rynbou.intcode.ParameterMode;
import pl.rynbou.intcode.ProgramExecutor;

import java.util.ArrayList;
import java.util.List;

public class Opcode2 extends Opcode {

    @Override
    public void run(ProgramExecutor executor) {
        int rawCode = executor.getAtPointerAndIncrement();
        List<Integer> arguments = new ArrayList<>();
        List<ParameterMode> modes = ParameterMode.modesFromRaw(rawCode, 2);

        for (int i = 0; i < getOpcodeInfo().getArgsAmount(); i++) arguments.add(executor.getAtPointerAndIncrement());

        executor.set(arguments.get(2), executor.get(modes.get(0), arguments.get(0)) * executor.get(modes.get(1), arguments.get(1)));
    }

    @Override
    public OpcodeInfo getOpcodeInfo() {
        return OpcodeInfo.MULTIPLY;
    }
}
