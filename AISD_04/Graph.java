import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Graph<T> implements IGraph<T>{
    boolean directed;
    boolean weighted;
    ArrayList<T> vertices;
    ArrayList<Edge> edges;

    Graph(boolean directed,boolean weighted){
        this.directed=directed;
        this.weighted=weighted;
        vertices=new ArrayList<>();
        edges=new ArrayList<>();
    }
    @Override
    public void addVertex(T vertex) {
        if(vertices.contains(vertex)) return;
        else vertices.add(vertex);
    }

    @Override
    public void addEdge(T source, T destination) {
        if(!vertices.contains(source)||!vertices.contains(destination)) throw new NoSuchElementException();
        if(!isWeighted()) throw new IllegalArgumentException();
        Edge e=new Edge(source,destination);
        if(!isDirected()){
        if(edges.contains(e)) return;
        else {
            edges.add(e);
            edges.add(new Edge(destination, source));
        }
        }
        else{
            if(edges.contains(e)) return;
            else {
                edges.add(e);
            }

        }
    }
    public void addEdge(T source, T destination,int weight) {
        if(!vertices.contains(source)||!vertices.contains(destination)) throw new NoSuchElementException();
        if(!isWeighted()) throw new IllegalArgumentException();
        Edge e=new Edge(source,destination,weight);
        if(!isDirected()){
            if(edges.contains(e)) return;
            else {
                edges.add(e);
                edges.add(new Edge(destination, source,weight));
            }
        }
        else{
            if(edges.contains(e)) return;
            else {
                edges.add(e);
            }

        }
    }
    @Override
    public boolean containsVertex(T vertex) {
        if(vertices.contains(vertex)) return true;
        return false;
    }

    @Override
    public boolean containsEdge(T source, T destination) {
        Edge E=new Edge(source,destination);
        if(edges.contains(E)) return true;
        return false;
    }

    @Override
    public int degree(T vertex) {
        if(!vertices.contains(vertex)) throw new NoSuchElementException();
        int x=0;
        for(Edge E : edges){
            if(E.source==vertex) x++;
        }
        return x;
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public int edgeAmount() {
        if(!isDirected()) return edges.size()/2;
        return edges.size();
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean isWeighted() {
        return weighted;
    }

    @Override
    public boolean isEmpty() {
        if(vertices.size()>0) return false;
        return true;
    }
    @Override
    public T remove(T vertex) {
        if(!vertices.contains(vertex)) throw new NoSuchElementException();
        vertices.remove(vertex);
        for(Edge E : edges){
            if(E.source==vertex) edges.remove(E);
            if(E.destination==vertex) edges.remove(E);
        }
        return vertex;
    }
    public int weightSum(){
        int sum=0;
        for(Edge E : edges){
            sum+=E.weight;
        }
        if(!isDirected()) return sum/2;
        return sum;
    }
    public int dijkstraAlgo(T source, T dst) throws Exception {
        int [][]w=makeMatrix();
        boolean[] visitedVertices=new boolean[vertices.size()];
        int[] D=new int[vertices.size()];
        int infinite=Integer.MAX_VALUE;
        for(int i=0;i< vertices.size();i++){
            D[i]=infinite;
            visitedVertices[i]=false;
        }
        D[vertices.indexOf(source)]=0;
        for(int i=0;i< vertices.size();i++){
            int u = minDistance(D,visitedVertices);
            visitedVertices[u]=true;
            for(int v=0;v< vertices.size();v++){
                if(!visitedVertices[v] && w[u][v]!=0 && (D[u]+w[u][v]<D[v])) {
                    D[v]=D[u]+w[u][v];

                }
            }



        }
        int dstIndex=vertices.indexOf(dst);
        return D[dstIndex];

        }





    private int minDistance(int[] D,boolean[] visited){
        int min=Integer.MAX_VALUE;
        int minVertex=-1;
        for(int i=0; i<D.length;i++){
            if(!visited[i]&&D[i]<min){
                min=D[i];
                minVertex=i;
            }
        }
        return minVertex;
    }
    public int[][] makeMatrix(){
        int[][] matrix=new int[vertices.size()][vertices.size()];
        for(int i=0;i< vertices.size();i++){
            for(int j=0;j< vertices.size();j++){
                T src=vertices.get(i);
                T dst=vertices.get(j);
                int weight=0;
                for(Edge E : edges){
                    if(E.source==src&&E.destination==dst) weight=E.weight;
                }
                matrix[i][j]=weight;
                matrix[j][i]=weight;

            }

        }
        return matrix;
    }



        public static void main(String[] args) throws Exception {
        Graph<Integer> g1=new Graph<>(true,false);
        g1.addVertex(1);
        g1.addVertex(2);
        g1.addVertex(3);
        g1.addVertex(4);
        g1.addVertex(5);
        System.out.println(g1.size());
        g1.addEdge(3,5);
        System.out.println(g1.edgeAmount());


    }
}
