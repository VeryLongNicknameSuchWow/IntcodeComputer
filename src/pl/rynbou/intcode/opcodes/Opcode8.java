package pl.rynbou.intcode.opcodes;

import pl.rynbou.intcode.Opcode;
import pl.rynbou.intcode.OpcodeInfo;
import pl.rynbou.intcode.ParameterMode;
import pl.rynbou.intcode.ProgramExecutor;

import java.util.ArrayList;
import java.util.List;

public class Opcode8 extends Opcode {
    @Override
    public void run(ProgramExecutor executor) {
        int rawCode = executor.getAtPointerAndIncrement();
        List<Integer> arguments = new ArrayList<>();
        List<ParameterMode> modes = ParameterMode.modesFromRaw(rawCode, 2);

        for (int i = 0; i < getOpcodeInfo().getArgsAmount(); i++) arguments.add(executor.getAtPointerAndIncrement());

        if (executor.get(modes.get(0), arguments.get(0)) == executor.get(modes.get(1), arguments.get(1))) executor.set(arguments.get(2), 1);
        else executor.set(arguments.get(2), 0);
    }

    @Override
    public OpcodeInfo getOpcodeInfo() {
        return OpcodeInfo.EQUALS;
    }
}
