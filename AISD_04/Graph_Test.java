import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Graph_Test {
    @Test
    //Testowałem graph z prezentacji wykładowcy, prezentacja W11, 11 strona
    public void graphTest() throws Exception {
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
        g1.addEdge(A,B,2);
        g1.addEdge(A,C,5);
        g1.addEdge(B,E,4);
        g1.addEdge(B,D,3);
        g1.addEdge(C,D,5);
        g1.addEdge(C,F,6);
        g1.addEdge(D,E,3);
        g1.addEdge(D,F,1);
        g1.addEdge(E,H,2);
        g1.addEdge(E,G,8);
        g1.addEdge(E,F,4);
        g1.addEdge(F,G,7);
        g1.addEdge(G,H,1);


        Assertions.assertEquals(8,g1.size());
        Assertions.assertEquals(13,g1.edgeAmount());
        Assertions.assertEquals(false,g1.isDirected());
        Assertions.assertEquals(true,g1.isWeighted());
        Assertions.assertEquals(51,g1.weightSum());
        Assertions.assertEquals(4,g1.degree(D));
        Mst mst=new Mst(g1);
        mst.selectVertex(A);
        mst.makeMst();
        Assertions.assertEquals(7,mst.mstEdgesAmount());
        Assertions.assertEquals(17,mst.mstEdgesWeightSum());
        Assertions.assertEquals(6,g1.dijkstraAlgo(A,E));
        Assertions.assertEquals(6,g1.dijkstraAlgo(F,H));
        Assertions.assertEquals(7,g1.dijkstraAlgo(B,G));







    }
}
