package taught_by_mjlmj.steptwo.unionfind;

// QuickUnion-基于rank的优化 + 路径减半
// 实现并查集
public class UnionFind_QU_R_PathHalving extends UnionFind_QuickUnion_Rank {
    public UnionFind_QU_R_PathHalving(int capacity) {
        super(capacity);
    }
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
