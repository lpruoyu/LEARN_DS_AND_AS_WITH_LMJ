package taught_by_mjlmj.steptwo.unionfind;

public class UnionFind_QuickFind extends UnionFind {

    public UnionFind_QuickFind(int capacity) {
        super(capacity);
    }

    @Override
    public void union(int v1, int v2) {
        int r1 = find(v1); // v1所在集合的根节点
        int r2 = find(v2); // v2所在集合的根节点
        if (r1 == r2) return; // 如果两个元素本来就在同一个集合中
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == r1) { // 找出v1所在集合的所有节点
                parents[i] = r2; // 让v1所在集合的所有节点都指向v2的根节点
            }
        }
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        // 使用QuickFind这种做法实现并查集
        // 元素的根节点就是它的父节点（看图示笔记）
        return parents[v];
    }

}
