package taught_by_mjlmj.steptwo.graph;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private final Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private final Set<Edge<V, E>> edges = new HashSet<>();

    private final Comparator<Edge<V, E>> edgeComparator
            = (e1, e2) -> weightManager.compare(e1.weight, e2.weight);

    public ListGraph() {
    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    public void print() {
        System.out.println("顶点：");
        vertices.forEach((k, v) -> {
            System.out.println(k);
            System.out.println("in:");
            System.out.println(v.inEdges);
            System.out.println("out:");
            System.out.println(v.outEdges);
            System.out.println("---------------------");
        });
        System.out.println("边：");
        edges.forEach(System.out::println);
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void removeVertex(V v) {
//        Vertex<V, E> vertex = vertices.get(v);
//        if (null == vertex) return;
        final Vertex<V, E> removeVertex = vertices.remove(v);
        if (null == removeVertex) return;

        for (Iterator<Edge<V, E>> iterator = removeVertex.outEdges.iterator(); iterator.hasNext(); ) {
            final Edge<V, E> edge = iterator.next();
            edges.remove(edge);
            edge.to.inEdges.remove(edge);
//            iterator.remove();
        }
//        removeVertex.outEdges.clear();
        removeVertex.outEdges = null;
        for (Iterator<Edge<V, E>> iterator = removeVertex.inEdges.iterator(); iterator.hasNext(); ) {
            final Edge<V, E> edge = iterator.next();
            edges.remove(edge);
            edge.from.outEdges.remove(edge);
//            iterator.remove();
        }
//        removeVertex.inEdges.clear();
        removeVertex.inEdges = null;
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    // 如果发现某个顶点不存在那么需要创建该顶点
    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        Vertex<V, E> toVertex = vertices.get(to);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, weight);
        if (fromVertex.outEdges.remove(edge)) { // 如果已经存在这条边了
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        Vertex<V, E> toVertex = vertices.get(to);
        if (fromVertex == null || toVertex == null) {
            return;
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, null);
        if (edges.remove(edge)) { // 如果存在这条边的话，才需要删除
            toVertex.inEdges.remove(edge);
            fromVertex.outEdges.remove(edge);
        }
    }

//    // 广度优先搜索
//    @Override
//    public void bfs(V begin) {
//        final Vertex<V, E> beginVertex = vertices.get(begin);
//        if (null == beginVertex) return;
//
//        Queue<Vertex<V, E>> queue = new LinkedList<>();
//        Set<Vertex<V, E>> set = new HashSet<>();
//        queue.offer(beginVertex);
//        set.add(beginVertex);
//
//        while (!queue.isEmpty()) {
//            final Vertex<V, E> vertex = queue.poll();
//
//            System.out.println(vertex);
//
//            for (Edge<V, E> edge : vertex.outEdges) {
//                if (set.contains(edge.to)) continue;
//                queue.offer(edge.to);
//                set.add(edge.to);
//            }
//        }
//    }
//
//    // 深度优先搜索——非递归实现
//    @Override
//    public void dfs(V begin) {
//        final Vertex<V, E> beginVertex = vertices.get(begin);
//        if (null == beginVertex) return;
//
//        Set<Vertex<V, E>> set = new HashSet<>();
//        Stack<Vertex<V, E>> stack = new Stack<>();
//        stack.push(beginVertex);
//        set.add(beginVertex);
//        System.out.println(beginVertex);
//        while (!stack.isEmpty()) {
//            final Vertex<V, E> vertex = stack.pop();
//            for (Edge<V, E> edge : vertex.outEdges) {
//                if (set.contains(edge.to)) continue;
//                stack.push(edge.from);
//                stack.push(edge.to);
//                set.add(edge.to);
//                System.out.println(edge.to);
//                break;
//            }
//        }
//    }
//
//    // 深度优先搜索——递归实现
//    public void dfs2(V begin) {
//        final Vertex<V, E> beginVertex = vertices.get(begin);
//        if (null == beginVertex) return;
//        dfs2(beginVertex, new HashSet<>());
//    }
//
//    private void dfs2(Vertex<V, E> vertex, Set<Vertex<V, E>> set) {
//        System.out.println(vertex);
//        set.add(vertex);
//        for (Edge<V, E> edge : vertex.outEdges) {
//            if (set.contains(edge.to)) continue;
//            dfs2(edge.to, set);
//        }
//    }

// 加入Visiter，但是不能停止遍历
//
//    // 广度优先搜索
//    @Override
//    public void bfs(V begin, Visitor<V> visitor) {
//        if (null == visitor) return;
//        final Vertex<V, E> beginVertex = vertices.get(begin);
//        if (null == beginVertex) return;
//
//        Queue<Vertex<V, E>> queue = new LinkedList<>();
//        Set<Vertex<V, E>> set = new HashSet<>();
//        queue.offer(beginVertex);
//        set.add(beginVertex);
//        while (!queue.isEmpty()) {
//            final Vertex<V, E> vertex = queue.poll();
//            visitor.vertex(vertex.value);
//            for (Edge<V, E> edge : vertex.outEdges) {
//                if (set.contains(edge.to)) continue;
//                queue.offer(edge.to);
//                set.add(edge.to);
//            }
//        }
//    }
//
//    // 深度优先搜索——非递归实现
//    @Override
//    public void dfs(V begin, Visitor<V> visitor) {
//        if (null == visitor) return;
//        final Vertex<V, E> beginVertex = vertices.get(begin);
//        if (null == beginVertex) return;
//
//        Set<Vertex<V, E>> set = new HashSet<>();
//        Stack<Vertex<V, E>> stack = new Stack<>();
//        stack.push(beginVertex);
//        set.add(beginVertex);
//        visitor.vertex(beginVertex.value);
//        while (!stack.isEmpty()) {
//            final Vertex<V, E> vertex = stack.pop();
//            for (Edge<V, E> edge : vertex.outEdges) {
//                if (set.contains(edge.to)) continue;
//                stack.push(edge.from);
//                stack.push(edge.to);
//                set.add(edge.to);
//                visitor.vertex(edge.to.value);
//                break;
//            }
//        }
//    }
//
//    // 深度优先搜索——递归实现
//    public void dfs2(V begin, Visitor<V> visitor) {
//        if (null == visitor) return;
//        final Vertex<V, E> beginVertex = vertices.get(begin);
//        if (null == beginVertex) return;
//        dfs2(beginVertex, new HashSet<>(), visitor);
//    }
//
//    private void dfs2(Vertex<V, E> vertex, Set<Vertex<V, E>> set, Visitor<V> visitor) {
//        visitor.vertex(vertex.value);
//        set.add(vertex);
//        for (Edge<V, E> edge : vertex.outEdges) {
//            if (set.contains(edge.to)) continue;
//            dfs2(edge.to, set, visitor);
//        }
//    }

// 可以停止遍历

    // 广度优先搜索
    @Override
    public void bfs(V begin, Visitor<V> visitor) {
        if (null == visitor) return;
        final Vertex<V, E> beginVertex = vertices.get(begin);
        if (null == beginVertex) return;

        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Set<Vertex<V, E>> set = new HashSet<>();
        queue.offer(beginVertex);
        set.add(beginVertex);
        while (!queue.isEmpty()) {
            final Vertex<V, E> vertex = queue.poll();
            if (visitor.vertex(vertex.value)) return;
            for (Edge<V, E> edge : vertex.outEdges) {
                if (set.contains(edge.to)) continue;
                queue.offer(edge.to);
                set.add(edge.to);
            }
        }
    }

    // 深度优先搜索——非递归实现
    @Override
    public void dfs(V begin, Visitor<V> visitor) {
        if (null == visitor) return;
        final Vertex<V, E> beginVertex = vertices.get(begin);
        if (null == beginVertex) return;

        Set<Vertex<V, E>> set = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        stack.push(beginVertex);
        set.add(beginVertex);
        if (visitor.vertex(beginVertex.value)) return;
        while (!stack.isEmpty()) {
            final Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges) {
                if (set.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                set.add(edge.to);
                if (visitor.vertex(edge.to.value)) return;
                break;
            }
        }
    }

    // 使用卡恩算法对DAG实现拓扑排序
    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>(); // list用来存放遍历结果，返回给使用者
        Queue<Vertex<V, E>> queue = new LinkedList<>(); // queue用来存放入度为0的顶点
        Map<Vertex<V, E>, Integer> map = new HashMap<>(); // 顶点的入度表

        // 初始化入度表和queue（将度为0的节点都放入队列）
        vertices.forEach((v, vertex) -> {
            int inSize = vertex.inEdges.size();
            if (inSize == 0) queue.offer(vertex);
            else
                // 初始化入度表时，没有必要将入度为0的顶点放入入度表中
                map.put(vertex, inSize);
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            vertex.outEdges.forEach(edge -> {
                Integer integer = map.get(edge.to);
                integer--;
                if (integer == 0)
                    // 将这个顶点入队后，就不用更新该顶点的入度了
                    queue.offer(edge.to);
                else map.put(edge.to, integer);
            });
        }

        return list;
    }

    // minimum spanning tree
    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return prim();
    }

    private Set<EdgeInfo<V, E>> prim() {
        final Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        if (!iterator.hasNext()) return null; // 如果没有顶点的话（空图）返回null
        // 相当于PPT上的A集合：最小生成树的边集
        final Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        // 相当于PPT上的S集合：最小生成树的顶点
        final Set<Vertex<V, E>> addedVertex = new HashSet<>();
        final Vertex<V, E> startVertex = iterator.next();
        addedVertex.add(startVertex);
        final MinHeap<Edge<V, E>> minHeap = new MinHeap<>(startVertex.outEdges, edgeComparator);
//        final int edgeSize = vertices.size() - 1; // 终止条件： 边集的数量 == 顶点的数量 - 1
//        while (!minHeap.isEmpty() && edgeInfos.size() < edgeSize) {
        final int verticesSize = vertices.size(); // 终止条件：最小生成树的顶点的数量 == 顶点的数量
        while (!minHeap.isEmpty() && addedVertex.size() < verticesSize) {
            Edge<V, E> edge = minHeap.remove();
            if (addedVertex.contains(edge.to)) continue;
            edgeInfos.add(edge.edgeInfo());
            addedVertex.add(edge.to);
            minHeap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    // 顶点
    private static class Vertex<V, E> {
        V value; // 顶点存储的元素
        Set<Edge<V, E>> inEdges = new HashSet<>(); // 以该顶点为终点的边（到达该顶点的边）
        Set<Edge<V, E>> outEdges = new HashSet<>(); // 以该顶点为起点的边（从该顶点出发的边）

        Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            Vertex<V, E> vertex = (Vertex<V, E>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    // 边
    private static class Edge<V, E> {
        E weight; // 边的权值
        Vertex<V, E> from; // 这条边从哪个顶点出发
        Vertex<V, E> to; // 这条边要到达哪个顶点

        Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            Edge<V, E> edge = (Edge<V, E>) o;
            return from.equals(edge.from) && to.equals(edge.to);
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + from.hashCode();
            result = 31 * result + to.hashCode();
            return result;
        }

        public EdgeInfo<V, E> edgeInfo() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
