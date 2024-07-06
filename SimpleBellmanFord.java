package pattern;
//Rajveer singh

import java.util.ArrayList;
import java.util.List;
class Edge {
    int source, destination, weight;
    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
class Graph {
    private final int V;
    private final List<Edge> edges;
    Graph(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
    }
    void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }
    void bellmanFord(int startVertex) {
        int[] distances = new int[V];
        for (int i = 0; i < V; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[startVertex] = 0;
        for (int i = 1; i < V; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] != Integer.MAX_VALUE && distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                }
            }
        }
        for (Edge edge : edges) {
            if (distances[edge.source] != Integer.MAX_VALUE && distances[edge.source] + edge.weight < distances[edge.destination]) {
                System.out.println("Graph contains a negative-weight cycle");
                return;
            }
        }
        printSolution(distances, startVertex);
    }
    void printSolution(int[] distances, int startVertex) {
        System.out.println("Vertex distances from source vertex " + startVertex + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " distance: " + (distances[i] == Integer.MAX_VALUE ? "∞" : distances[i]));
        }
    }
}
public class SimpleBellmanFord {
    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);
        graph.bellmanFord(0);
    }
}
