package pl.rynbou.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum ParameterMode {
    POSITION,
    IMMEDIATE;

    public static List<ParameterMode> modesFromRaw(int opcode, int amount) {
        List<ParameterMode> modes = new ArrayList<>();
        OpcodeInfo info = OpcodeInfo.recognise(opcode);
        int modesOnly = (opcode - info.getOpcode()) / 100;
        int len = String.valueOf(modesOnly).length();
        List<Integer> digits = String.valueOf(modesOnly).chars().map(i -> i - '0').boxed().collect(Collectors.toList());

        for (int i = len - 1; i >= 0; i--)
            if (digits.get(i) == 1) modes.add(ParameterMode.IMMEDIATE);
            else modes.add(ParameterMode.POSITION);

        for (int i = 0; i < amount - len; i++) modes.add(ParameterMode.POSITION);

        return modes;
    }
}
