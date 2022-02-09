package taught_by_mjlmj.steptwo.unionfind;

public class UnionFind_QuickUnion extends UnionFind {

    public UnionFind_QuickUnion(int capacity) {
        super(capacity);
    }

    @Override
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) return;
        // 让v1的根节点指向v2的根节点
        parents[r1] = r2;
    }

    // 找根节点（属于哪一个集合）
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

}
