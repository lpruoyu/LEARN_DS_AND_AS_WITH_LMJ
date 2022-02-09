package taught_by_mjlmj.steptwo.unionfind;

// QuickUnion-基于rank的优化 + 路径分裂
// 实现并查集
public class UnionFind_QU_R_PathSpliting extends UnionFind_QuickUnion_Rank {
    public UnionFind_QU_R_PathSpliting(int capacity) {
        super(capacity);
    }
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            int parent = parents[v];
            parents[v] = parents[parent];
            v = parent;
        }
        return v;
    }
}
