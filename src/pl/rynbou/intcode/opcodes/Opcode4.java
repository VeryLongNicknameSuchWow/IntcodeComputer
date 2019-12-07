package pl.rynbou.intcode.opcodes;

import pl.rynbou.intcode.Opcode;
import pl.rynbou.intcode.OpcodeInfo;
import pl.rynbou.intcode.ParameterMode;
import pl.rynbou.intcode.ProgramExecutor;

import java.util.ArrayList;
import java.util.List;

public class Opcode4 extends Opcode {

    int rawCode;
    private List<ParameterMode> modes = new ArrayList<>();
    private List<Integer> arguments = new ArrayList<>();

    @Override
    public void run(ProgramExecutor executor) {
        rawCode = executor.getAtPointerAndIncrement();
        modes = ParameterMode.modesFromRaw(rawCode, 1);

        for (int i = 0; i < getOpcodeInfo().getArgsAmount(); i++) arguments.add(executor.getAtPointerAndIncrement());

        executor.getIo().out(executor.get(modes.get(0), arguments.get(0)));
    }

    @Override
    public OpcodeInfo getOpcodeInfo() {
        return OpcodeInfo.OUTPUT;
    }
}
