import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> parentMap;
    private Vertex<V> start;
    private Vertex<V> goalVertex;

    public BreadthFirstSearch(WeightedGraph<V> graph, V start) {
        this.start = graph.getVertex(start);
        this.parentMap = new HashMap<>();
        bfs(graph);
    }

    private void bfs(WeightedGraph<V> graph) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            for (Vertex<V> neighbor : current.getAdjacentVertices()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V goal) {
        List<V> path = new LinkedList<>();
        goalVertex = parentMap.keySet().stream()
                .filter(v -> v.getData().equals(goal))
                .findFirst().orElse(null);

        if (goalVertex == null) return path;

        for (Vertex<V> at = goalVertex; at != null; at = parentMap.get(at)) {
            path.add(at.getData());
        }
        Collections.reverse(path);
        return path;
    }
}
