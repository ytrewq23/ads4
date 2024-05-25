import java.util.*;

public class Vertex<V> {
    private V data;
    private List<Vertex<V>> adjacentVertices;
    private List<Double> weights;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new ArrayList<>();
        this.weights = new ArrayList<>();
    }

    public V getData() {
        return data;
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.add(destination);
        weights.add(weight);
    }

    public List<Vertex<V>> getAdjacentVertices() {
        return adjacentVertices;
    }

    public List<Double> getWeights() {
        return weights;
    }
}
