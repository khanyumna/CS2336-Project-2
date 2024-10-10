import java.util.*;
public class Graph {
   private Map<String, List<Edge>> vertices;
   
   public Graph() {
    this.vertices = new HashMap<>();
   }

   public void insert(String vertex, String adjacentVertex, int weight) {


   }

    private static class Edge {
    private String vertex;
    private int weight;

    public Edge(String vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
   }
}
