package taught_by_mjlmj.steptwo.unionfind;

// QuickUnion基于size的优化
public class UnionFind_QuickUnion_Size extends UnionFind_QuickUnion {

    // 存放树的元素个数
    private int[] sizes;

    public UnionFind_QuickUnion_Size(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            // 初始化时，每个元素都是一颗独立的树
            // 每一颗树的元素个数都为1
            sizes[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) return;
        if (sizes[r1] < sizes[r2]) {
            parents[r1] = r2;
            sizes[r2] += sizes[r1];
        } else {
            parents[r2] = r1;
            sizes[r1] += sizes[r2];
        }
    }

}
