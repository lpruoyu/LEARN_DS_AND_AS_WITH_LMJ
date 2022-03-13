package taught_by_mjlmj.steptwo.graph;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graph<Object, Double> graph = undirectedGraphWithWM(Data.MST_01);
        final Set<Graph.EdgeInfo<Object, Double>> edgeInfos = graph.mst();
        for (Graph.EdgeInfo<Object, Double> edgeInfo : edgeInfos) {
            System.out.println(edgeInfo);
        }
    }

    public static void heapTest2() {
        List<Integer> arrayList = Arrays.asList(23, -456, 11, 34, 5666, 222, 345, -9, 10, 1, 44);
        MinHeap<Integer> heap = new MinHeap<>(arrayList);
        System.out.println(heap.get());
//        MinHeap<Integer> heap = new MinHeap<>();
//        for (int i = 10; i >= 0; i--) {
//            heap.add(1 + i);
//        }
//        System.out.println(heap.get());
//        heap = new MinHeap<>();
//        for (int i = 0; i <= 10; i++) {
//            heap.add(1 + i);
//        }
//        System.out.println(heap.get());
    }

    public static void heapTest() {
        // Java默认的堆是PriorityQueue，是一个小顶堆（最小堆）
        // 批量建堆的同时不能传比较器
        // 传比较器的同时不能批量建堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
//        PriorityQueue<Integer> heap = new PriorityQueue<>(new ArrayList<>());
//        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return 0;
//            }
//        });
        for (int i = 0; i < 10; i++) {
            heap.offer(1 + i);
        }
        System.out.println(heap.peek());
    }

    private static void testTopologicalSort() {
        Graph<Object, Double> graph = directedGraph(Data.TOPO);
        graph.topologicalSort().forEach(System.out::println);
    }

    private static void testDFS() {
        Graph.Visitor<Object> visitor = new Graph.Visitor<Object>() {
            @Override
            public boolean vertex(Object obj) {
                System.out.println(obj + "---------- visitor");
                return false;
            }
        };
        Graph<Object, Double> graph = undirectedGraph(Data.DFS_01);
        graph.dfs(1, visitor);
        System.out.println("===========================");
        graph = directedGraph(Data.DFS_02);
        graph.dfs("a", visitor);
        System.out.println("===========================");

//        Graph.Visitor<Object> visitor = new Graph.Visitor<Object>() {
//            @Override
//            public void vertex(Object obj) {
//                System.out.println(obj + "---------- visitor");
//            }
//        };
//        Graph<Object, Double> graph = undirectedGraph(Data.DFS_01);
//        graph.dfs(1, visitor);
//        System.out.println("===========================");
//        graph = directedGraph(Data.DFS_02);
//        graph.dfs("a", visitor);
//        System.out.println("===========================");

//        ListGraph<Object, Double> graph = (ListGraph<Object, Double>) undirectedGraph(Data.DFS_01);
//        graph.dfs(1);
//        System.out.println("===========================");
//        graph.dfs2(1);
//        System.out.println("===========================");
//        graph = (ListGraph<Object, Double>) directedGraph(Data.DFS_02);
//        graph.dfs("a");
//        System.out.println("===========================");
//        graph.dfs2("a");
//        System.out.println("===========================");
    }

    private static void testBFS() {
        Graph<Object, Double> graph = undirectedGraph(Data.BFS_01);
        Graph.Visitor<Object> visitor = new Graph.Visitor<Object>() {
            @Override
            public boolean vertex(Object obj) {
                System.out.println(obj);
                return false;
            }
        };
        graph.bfs("A", visitor);
        System.out.println("===========================");
        graph = directedGraph(Data.BFS_02);
        graph.bfs(0, visitor);
        System.out.println("===========================");
        graph.bfs(7, visitor);
        System.out.println("===========================");
        graph.bfs(5, visitor);

//        Graph<Object, Double> graph = undirectedGraph(Data.BFS_01);
//        graph.bfs("A");
//        System.out.println("===========================");
//        graph = directedGraph(Data.BFS_02);
//        graph.bfs(0);
//        System.out.println("===========================");
//        graph.bfs(7);
//        System.out.println("===========================");
//        graph.bfs(5);
    }

    public static void main3(String[] args) {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("v0", "v4", 6);
        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v3", "v4", 1);

//        graph.bfs("v1");
    }

    public static void main2(String[] args) {
        // 有向图表达无向图
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("v0", "v1");
        graph.addEdge("v1", "v0");

        graph.addEdge("v0", "v2");
        graph.addEdge("v2", "v0");

        graph.addEdge("v0", "v3");
        graph.addEdge("v3", "v0");

        graph.addEdge("v1", "v2");
        graph.addEdge("v2", "v1");

        graph.addEdge("v2", "v3");
        graph.addEdge("v3", "v2");

        graph.print();
    }

    public static void main1(String[] args) {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("v0", "v4", 6);
        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v3", "v4", 1);

//        graph.removeEdge("v0", "v4");
//        graph.removeEdge("v1", "v0");
//        graph.removeEdge("v1", "v2");
//        graph.removeEdge("v2", "v0");
//        graph.removeEdge("v2", "v3");
//        graph.removeEdge("v3", "v4");

//        graph.print();

        graph.removeVertex("v0");
//        graph.removeVertex("v1");
//        graph.removeVertex("v2");
//        graph.removeVertex("v3");
//        graph.removeVertex("v4");

        graph.print();
//        graph.addEdge("v0", "v4", 1);
//        graph.addEdge("v1", "v0", 1);
//        graph.addEdge("v1", "v2", 1);
//        graph.addEdge("v2", "v0", 1);
//        graph.addEdge("v2", "v3", 1);
//        graph.addEdge("v3", "v4", 1);

//        graph.print();

//        graph.addEdge("v0", "v4", 6);
//        graph.addEdge("v1", "v0", 9);
//        graph.addEdge("v1", "v2", 3);
//        graph.addEdge("v2", "v0", 2);
//        graph.addEdge("v2", "v3", 5);
//        graph.addEdge("v3", "v4", 1);

//        graph.print();
    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }

    private static final Graph.WeightManager<Double> weightManager = new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double weight1, Double weight2) {
            return weight1.compareTo(weight2);
        }

        @Override
        public Double add(Double weight1, Double weight2) {
            return weight1 + weight2;
        }
    };

    /**
     * 有向图（带权值管理器）
     */
    private static Graph<Object, Double> directedGraphWithWM(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图（带权值管理器）
     */
    private static Graph<Object, Double> undirectedGraphWithWM(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }

}
