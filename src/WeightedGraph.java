import java.util.*;

public class WeightedGraph<V> {
    private List<Vertex<V>> vertices;
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.vertices = new ArrayList<>();
        this.directed = directed;
    }

    public Vertex<V> addVertex(V data) {
        Vertex<V> vertex = new Vertex<>(data);
        vertices.add(vertex);
        return vertex;
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = getVertex(source);
        Vertex<V> destVertex = getVertex(dest);

        if (sourceVertex == null) {
            sourceVertex = addVertex(source);
        }
        if (destVertex == null) {
            destVertex = addVertex(dest);
        }

        sourceVertex.addAdjacentVertex(destVertex, weight);
        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        for (Vertex<V> vertex : vertices) {
            if (vertex.getData().equals(data)) {
                return vertex;
            }
        }
        return null;
    }

    public List<Vertex<V>> getVertices() {
        return vertices;
    }
}
