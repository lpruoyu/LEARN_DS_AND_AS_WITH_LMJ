package taught_by_mjlmj.steptwo.unionfind;

public interface UF {

    // 合并v1，v2所属的集合
    void union(int v1, int v2);

    // 查找v所属集合（根节点）
    int find(int v);

    // 检查v1、v2是否属于同一个集合
    default boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

}
