package pl.rynbou.intcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cluster {

    private List<ProgramExecutor> nodes = new ArrayList<>();
    private Iterator<ProgramExecutor> iterator;

    public Cluster(String program, int nodesAmount) {
        for (int i = 0; i < nodesAmount; i++) nodes.add(new ProgramExecutor(program, false, false));
        iterator = nodes.iterator();
    }

    public Cluster(List<String> programs, int nodesAmount) {
        if (programs.size() != nodesAmount) throw new RuntimeException("List of programs size is different than the amount of nodes");
        for (int i = 0; i < nodesAmount; i++) nodes.add(new ProgramExecutor(programs.get(i), false, false));
        iterator = nodes.iterator();
    }

    public ProgramExecutor getNode(int index) {
        return nodes.get(index);
    }

    public Iterator<ProgramExecutor> getIterator() {
        return iterator;
    }

    public int getSize() {
        return nodes.size();
    }
}
