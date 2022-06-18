public interface IGraph<T> {
     void addVertex(T vertex);
     void addEdge(T source, T destination);
     boolean containsVertex(T vertex);
     boolean containsEdge(T source, T destination);
     int degree(T vertex);
     int size();
     int edgeAmount();
     boolean isDirected();
     boolean isWeighted();
     boolean isEmpty();
     T remove(T vertex);
     int weightSum();


}
