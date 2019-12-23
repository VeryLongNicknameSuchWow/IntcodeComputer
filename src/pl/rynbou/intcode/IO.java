package pl.rynbou.intcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class IO {

    private static Scanner scanner = new Scanner(System.in);
    private boolean consoleInput;
    private boolean consoleOutput;
    private boolean outputInterrupt = false;
    private List<Integer> inputs = new ArrayList<>();
    private List<Integer> inputsLog = new ArrayList<>();
    private List<Integer> outputsLog = new ArrayList<>();
    private Iterator<Integer> inputsIterator = inputs.iterator();
    private ProgramExecutor executor;

    public IO(ProgramExecutor executor, boolean consoleInput, boolean consoleOutput) {
        this.executor = executor;
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
    }

    public void out(int i) {
        if (consoleOutput) System.out.println("OUT: " + i);
        if (outputInterrupt) executor.pause();
        outputsLog.add(i);
    }

    public int in() {
        int input;
        if (!consoleInput && inputsIterator.hasNext()) {
            input = inputsIterator.next();
        } else {
            System.out.print("IN (int): ");
            input = scanner.nextInt();
        }
        inputsLog.add(input);
        return input;
    }

    public void addInput(int input) {
        inputs.add(input);
        this.inputsIterator = this.inputs.iterator();
    }

    public void clearInputs() {
        inputs = new ArrayList<>();
    }

    public void setInputs(List<Integer> inputs) {
        this.inputs = new ArrayList<>(inputs);
        this.inputsIterator = this.inputs.iterator();
    }

    public List<Integer> getOutputsLog() {
        return outputsLog;
    }

    public List<Integer> getInputsLog() {
        return inputsLog;
    }

    public ProgramExecutor getExecutor() {
        return executor;
    }

    public int getLastOutput() {
        return outputsLog.get(outputsLog.size() - 1);
    }

    public void enableConsoleInput(boolean consoleInput) {
        this.consoleInput = consoleInput;
    }

    public void enableConsoleOutput(boolean consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public void enableOutputInterrupt(boolean outputInterrupt) {
        this.outputInterrupt = outputInterrupt;
    }
}
