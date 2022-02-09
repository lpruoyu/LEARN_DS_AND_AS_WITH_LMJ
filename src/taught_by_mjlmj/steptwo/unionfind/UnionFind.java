package taught_by_mjlmj.steptwo.unionfind;

public abstract class UnionFind implements UF {
    protected int[] parents;
    public UnionFind(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be >= 1.");
        parents = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
        }
    }
    protected void rangeCheck(int v) {
        if (v < parents.length && v >= 0) return;
        throw new IllegalArgumentException("The v must be >= 0 AND < " + parents.length);
    }
}
