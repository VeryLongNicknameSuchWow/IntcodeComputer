package pl.rynbou.intcode;

import java.util.*;
import java.util.stream.Collectors;

public class ProgramExecutor {
    private Map<Long, Long> memory = new HashMap<>();
    private IO io;
    private long relativePointer = 0;
    private long memoryPointer = 0;
    private boolean finished = false;
    private boolean paused;

    public ProgramExecutor(List<Long> program, boolean consoleInput, boolean consoleOutput) {
        for (int i = 0; i < program.size(); i++)
            memory.put((long) i, program.get(i));

        io = new IO(this, consoleInput, consoleOutput);
    }

    public ProgramExecutor(String program, boolean consoleInput, boolean consoleOutput) {
        List<Long> list = Arrays.stream(program.split(",")).mapToLong(s -> Long.parseLong(s.trim())).boxed().collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < list.size(); i++)
            memory.put((long) i, list.get(i));

        io = new IO(this, consoleInput, consoleOutput);
    }

    public void run() throws Exception {
        paused = false;

        while (!finished && !paused) {
            OpcodeInfo info = OpcodeInfo.recognise((int) getAtPointer());
            Opcode opcode = info.getOpcodeObject();
            opcode.run(this);
        }
    }

    public void reset() {
        finished = false;
        paused = false;
        memoryPointer = 0;
    }

    public void adjustRelativePointer(long offset) {
        relativePointer += offset;
    }

    public void pause() {
        paused = true;
    }

    public void stop() {
        finished = true;
    }

    public long getAtPointer() {
        return memory.get(memoryPointer);
    }

    public long getWriteAddress(ParameterMode mode, long arg) {
        switch (mode) {
            case POSITION:
                return arg;

            case RELATIVE:
                return relativePointer + arg;

            case IMMEDIATE:
                throw new RuntimeException("Parameter that you are writing to can't be in immediate mode");

            default:
                throw new RuntimeException("Invalid address parameter mode");
        }
    }

    public long get(ParameterMode mode, long arg) {
        switch (mode) {
            case POSITION:
                return memory.getOrDefault(arg, (long) 0);

            case IMMEDIATE:
                return arg;

            case RELATIVE:
                return memory.getOrDefault(relativePointer + arg, (long) 0);

            default:
                throw new RuntimeException("Invalid parameter mode");
        }
    }

    public long getAtPointerAndIncrement() {
        return memory.getOrDefault(memoryPointer++, (long) 0);
    }

    public void setMemoryPointer(long address) {
        memoryPointer = address;
    }

    public void set(long address, long value) {
        memory.put(address, value);
    }

    public IO getIo() {
        return io;
    }
}
