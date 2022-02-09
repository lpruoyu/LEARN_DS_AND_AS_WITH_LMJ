package taught_by_mjlmj.steptwo.unionfind;

// QuickUnion + 基于Size + PathHalving
// 实现并查集（UnionFind）
public class UF_QU_S_PH {
    // 存放元素的父节点
    protected int[] parents;
    // 存放树的元素个数
    protected int[] sizes;

    public UF_QU_S_PH(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be >= 1.");
        parents = new int[capacity];
        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            // 初始化时，每个元素都是一颗独立的树
            // 因此，每个元素的父节点就是它本身
            parents[i] = i;
            // 初始化时，每个元素都是一颗独立的树
            // 因此，每一颗树的元素个数都应该为1
            sizes[i] = 1;
        }
    }

    // 合并v1，v2所属的集合
    public void union(int v1, int v2) {
        int r1 = find(v1); // v1所在集合的根节点
        int r2 = find(v2); // v2所在集合的根节点
        if (r1 == r2) return; // 如果两个元素本来就在同一个集合中就不用合并
        if (sizes[r1] < sizes[r2]) {
            parents[r1] = r2;
            sizes[r2] += sizes[r1];
        } else {
            parents[r2] = r1;
            sizes[r1] += sizes[r2];
        }
    }

    // 查找v所属集合（根节点）
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }

    // 检查v1、v2是否属于同一个集合
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (v < parents.length && v >= 0) return;
        throw new IllegalArgumentException("The v must be >= 0 AND < " + parents.length);
    }

}
