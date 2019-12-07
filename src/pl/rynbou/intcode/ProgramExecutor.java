package pl.rynbou.intcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramExecutor {
    private List<Integer> memory;
    private IO io;
    private int memoryPointer = 0;
    private boolean running = false;

    public ProgramExecutor(List<Integer> program, boolean consoleInput, boolean consoleOutput) {
        memory = new ArrayList<>(program);
        io = new IO(this, consoleInput, consoleOutput);
    }

    public ProgramExecutor(String program, boolean consoleInput, boolean consoleOutput) {
        memory = Arrays.stream(program.split(",")).mapToInt(s -> Integer.parseInt(s.trim())).boxed().collect(Collectors.toCollection(ArrayList::new));
        io = new IO(this, consoleInput, consoleOutput);
    }

    public void run() throws Exception {
        running = true;

        while (running) {
            OpcodeInfo info = OpcodeInfo.recognise(getAtPointer());
            Opcode opcode = info.getOpcodeObject();
            opcode.run(this);
        }
    }

    public void stop() {
        running = false;
    }

    public int getAtPointer() {
        return memory.get(memoryPointer);
    }

    public int get(ParameterMode mode, int address) {
        return mode == ParameterMode.IMMEDIATE ? address : memory.get(address);
    }

    public int getAtPointerAndIncrement() {
        return memory.get(memoryPointer++);
    }

    public void setMemoryPointer(int address) {
        memoryPointer = address;
    }

    public void set(int address, int value) {
        memory.set(address, value);
    }

    public void addValue(int value) {
        memory.add(value);
    }

    public IO getIo() {
        return io;
    }
}
