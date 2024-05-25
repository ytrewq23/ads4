import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> parentMap;
    private Map<Vertex<V>, Double> distances;
    private Vertex<V> start;
    private Vertex<V> goalVertex;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.start = graph.getVertex(start);
        this.parentMap = new HashMap<>();
        this.distances = new HashMap<>();
        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(this.start, 0.0);
        dijkstra();
    }

    private void dijkstra() {
        PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparing(distances::get));
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> current = priorityQueue.poll();

            for (int i = 0; i < current.getAdjacentVertices().size(); i++) {
                Vertex<V> neighbor = current.getAdjacentVertices().get(i);
                double weight = current.getWeights().get(i);

                double newDist = distances.get(current) + weight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    parentMap.put(neighbor, current);
                    priorityQueue.add(neighbor);
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
