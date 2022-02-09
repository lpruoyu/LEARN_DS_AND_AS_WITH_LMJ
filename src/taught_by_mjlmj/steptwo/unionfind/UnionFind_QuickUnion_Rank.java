package taught_by_mjlmj.steptwo.unionfind;

// QuickUnion基于树的高度（rank）的优化
public class UnionFind_QuickUnion_Rank extends UnionFind_QuickUnion {
    // 存放树的高度
    protected final int[] ranks;

    public UnionFind_QuickUnion_Rank(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            // 初始化时，每个元素都是一颗独立的树
            // 每一颗树的高度都应该为1
            ranks[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        final int r1 = find(v1);
        final int r2 = find(v2);
        if (r1 == r2) return;
        if (ranks[r1] < ranks[r2]) { // v2所在的树比较高
            parents[r1] = r2;
        } else if (ranks[r1] > ranks[r2]) { // v1所在的树比较高
            parents[r2] = r1;
        } else { // v1 与 v2 的树的高度相同时，合并两棵树后需要更改树的高度
            parents[r1] = r2; // v1嫁接到v2上，或者v2嫁接到v1上都可以
            ranks[r2]++;
        }
    }
}
