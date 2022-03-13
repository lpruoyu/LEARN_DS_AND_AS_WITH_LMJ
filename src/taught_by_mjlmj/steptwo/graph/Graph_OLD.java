package taught_by_mjlmj.steptwo.graph;

import java.util.List;
import java.util.Set;

public interface Graph_OLD<V, E> {
    int vertexSize(); // 顶点的数量
    int edgeSize(); // 边的数量
    void addVertex(V v); // 添加一个顶点
    void removeVertex(V v); // 删除一个顶点
    void addEdge(V from, V to); // 添加一条边 // 如果发现某个顶点不存在那么自动创建该顶点
    void addEdge(V from, V to, E weight); // 添加一条边（带权值） // 如果发现某个顶点不存在那么自动创建该顶点
    void removeEdge(V from, V to); // 删除一条边

//    void bfs(V begin); // 广度优先搜索遍历
//    void dfs(V begin); // 深度优先搜索遍历
    void bfs(V begin, Visitor<V> visitor); // 广度优先搜索遍历
    void dfs(V begin, Visitor<V> visitor); // 深度优先搜索遍历
// 不能停止遍历
//    interface Visitor<V> {
//        void vertex(V v);
//    }
// 可以停止遍历
    interface Visitor<V> {
        boolean vertex(V v); // 返回true，就终止遍历
    }

    // 有向无环图DAG才能拓扑排序
    List<V> topologicalSort();

    // 最小生成树（连通图）minimum spanning tree
    Set<EdgeInfo<V,E>> mst();
    class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;
        protected EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public V getFrom() {
            return from;
        }
        public void setFrom(V from) {
            this.from = from;
        }
        public V getTo() {
            return to;
        }
        public void setTo(V to) {
            this.to = to;
        }
        public E getWeight() {
            return weight;
        }
        public void setWeight(E weight) {
            this.weight = weight;
        }
    }
}
