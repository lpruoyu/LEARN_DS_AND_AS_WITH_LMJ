package taught_by_mjlmj.steptwo.graph;

import java.util.List;
import java.util.Set;

public abstract class Graph<V, E> {
    protected WeightManager weightManager;
    // 无权图
    public Graph() {
    }
    // 有权图
    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }
    // 顶点数量
    public abstract int vertexSize();
    // 边的数量
    public abstract int edgeSize();

    public abstract void addVertex(V v);

    public abstract void removeVertex(V v);

    public abstract void addEdge(V from, V to);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeEdge(V from, V to);
    // 广度优先遍历
    public abstract void bfs(V begin, Visitor<V> visitor);
    // 深度优先遍历
    public abstract void dfs(V begin, Visitor<V> visitor);
    // 对DAG（有向无环图）进行拓扑排序
    public abstract List<V> topologicalSort();
    // 连通图的最小生成树
    public abstract Set<EdgeInfo<V, E>> mst();

    public interface Visitor<V> {
        boolean vertex(V v); // 返回true，就终止遍历
    }

    public static class EdgeInfo<V, E> {
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

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
    // 对有权图的权值进行管理
    public interface WeightManager<E> {
        int compare(E weight1, E weight2); // 比较权值的大小
        E add(E weight1, E weight2); // 为最短路径服务（两个权值相加）
    }
}
