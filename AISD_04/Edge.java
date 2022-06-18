public class Edge<T> implements Comparable<Edge>{
    T source;
    T destination;
    int weight;
    Edge(T source,T destination){
        this.source=source;
        this.destination=destination;

    }
    Edge(T source,T destination,int weight){
        this.source=source;
        this.destination=destination;
        this.weight=weight;

    }

    @Override
    public int compareTo(Edge e) {
        return this.weight-e.weight;
    }
}
