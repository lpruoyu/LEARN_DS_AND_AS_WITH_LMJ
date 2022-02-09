package taught_by_mjlmj.steptwo.unionfind;

// QuickUnion-基于rank的优化 + 路径压缩
// 实现并查集
public class UnionFind_QU_R_PathCompression extends UnionFind_QuickUnion_Rank {
    public UnionFind_QU_R_PathCompression(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
