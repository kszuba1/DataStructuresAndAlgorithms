import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Mst<T> implements IMst<T> {
    ArrayList<T> mstVertices;
    Graph G;
    ArrayList<Edge> mstEdges;
    private ArrayList<Edge> finalEdges;

    Mst(Graph G) {
        this.G = G;
        finalEdges=new ArrayList<>();

    }

    public void selectVertex(T vertex) {
        if (!G.vertices.contains(vertex)) throw new NoSuchElementException();
        mstVertices=new ArrayList<>(G.vertices.size());
        mstVertices.add(vertex);
    }

    private void makeMstHelper() {
        T tempSrc=null;
        T tempDst=null;
        for (Edge E : mstEdges) {
            if (mstVertices.contains(E.source) && !mstVertices.contains(E.destination)) {
                mstVertices.add((T) E.destination);
                tempSrc = (T) E.source;
                tempDst = (T) E.destination;
                finalEdges.add(E);
                mstEdges.remove(E);
                break;
            }
        }
        for(Edge edg : mstEdges){
            if(edg.source==tempDst && edg.destination==tempSrc){
                mstEdges.remove(edg);
                finalEdges.add(edg);
                break;
            }
        }
        if (mstVertices.size() != G.vertices.size()) makeMstHelper();
    }
        public Graph<T> makeMst () {
        mstEdges=new ArrayList<>();
            ArrayList<Edge> edges = G.edges;
            for (Edge E : edges) {
                mstEdges.add(new Edge(E.source, E.destination, E.weight));
            }
            Collections.sort(mstEdges);
            makeMstHelper();
            Graph<T> graph=new Graph(false,true);
            graph.vertices=mstVertices;
            graph.edges=finalEdges;
            return graph;
        }
        public int mstEdgesAmount(){
            return finalEdges.size()/2;
        }
        public int mstEdgesWeightSum(){
        int sum=0;
        for(Edge E:finalEdges){
            sum+=E.weight;
        }
        return sum/2;
        }




    public static void main(String[] args) throws Exception {
        Graph g1=new Graph(false,true);
        String A=new String("A");
        String B=new String("B");
        String C=new String("C");
        String D=new String("D");
        String E=new String("E");
        String F=new String("F");
        String G=new String("G");
        String H=new String("H");

        g1.addVertex(A);
        g1.addVertex(B);
        g1.addVertex(C);
        g1.addVertex(D);
        g1.addVertex(E);
        g1.addVertex(F);
        g1.addVertex(G);
        g1.addVertex(H);
        g1.addEdge(A,C,2);
        g1.addEdge(A,F,5);
        g1.addEdge(C,D,3);
        g1.addEdge(F,D,5);
        g1.addEdge(C,E,4);
        g1.addEdge(D,E,3);
        g1.addEdge(D,B,1);
        g1.addEdge(F,B,6);
        g1.addEdge(B,E,3);
        g1.addEdge(B,H,7);
        g1.addEdge(H,G,1);
        g1.addEdge(D,H,8);
        g1.addEdge(E,G,2);
        System.out.println(g1.size());
        System.out.println(g1.edgeAmount());
        System.out.println(g1.weightSum());
        Mst m1=new Mst(g1);
        m1.selectVertex(B);
        Graph g2=m1.makeMst();
        System.out.println(g2.size());
        System.out.println(g2.edgeAmount());
        System.out.println(g1.dijkstraAlgo(A,D));
    }
    }

