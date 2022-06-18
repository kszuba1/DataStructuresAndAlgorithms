import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class KMP {
    public static ArrayList KMP_Matcher(String text, String pattern){
        int N = text.length();
        int M = pattern.length();
        char[] T=new char[N];
        for(int i=0;i<N;i++){
            T[i]=text.charAt(i);
        }
        char[] P=new char[M];
        for(int i=0;i<M;i++){
            P[i]=pattern.charAt(i);
        }
        int q = 0;
        int[] pi=Compute_Prefix_Function(pattern);
        int i=0;
        ArrayList<Integer> indexes=new ArrayList<>();
        while (i < N) {
            if (P[q] == T[i]) {
                q++;
                i++;
            }
            if (q == M) {
                indexes.add(i-M);
                q = pi[q-1];
            }
            else if (i<N && P[q]!= T[i]) {

                if (q != 0)
                    q = pi[q-1];
                else
                    i++;
            }
        }
        return indexes;

    }
    public static void KMP_MatcherPrinter(String text, String pattern){
        int N = text.length();
        int M = pattern.length();
        char[] T=new char[N];
        for(int i=0;i<N;i++){
            T[i]=text.charAt(i);
        }
        char[] P=new char[M];
        for(int i=0;i<M;i++){
            P[i]=pattern.charAt(i);
        }
        int q = 0;//przechodzenie po wzorcu
        int[] pi=Compute_Prefix_Function(pattern);
        int i=0; //przechodzenie po tekscie
        while (i < N) {
            if (P[q] == T[i]) {
                q++;
                i++;
            }
            if (q == M) {
                System.out.println("wzorzec występuje z przesunięciem " + (i - M));
                q--;
                q = pi[q];
            }
            else if (i<N && P[q]!= T[i]) {

                if (q != 0)
                    q = pi[q-1];
                else
                    i++;
            }
        }

    }

    private static int[] Compute_Prefix_Function(String pattern){
        int m=pattern.length();
        char[] P=new char[m];
        for(int j=0;j<m;j++){
            P[j]=pattern.charAt(j);
        }
        int k = 0;
        int[] pi=new int[m];
        pi[0] = 0;
        int i = 1;
        while (i < m) {
            if (P[i] == P[k]) {
                k++;
                pi[i] = k;
                i++;
            }
            else {
                if (k != 0) {
                    k = pi[k - 1];
                }
                else {
                    pi[i] = 0;
                    i++;
                }
            }
        }
        return pi;
    }
    private static String  readFile(String path) {
        String filePath=path;
        return readBytesHelper(filePath);
    }
    private static String readBytesHelper(String filePath)
    {
        String content = "";

        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }
    public static void longInLong(String pattern){
        String text=readFile("C:/Users/K/Downloads/ksiazka.txt");
        long startTime = System.nanoTime();
        KMP_MatcherPrinter(text,pattern);
        long endTime   = System.nanoTime();
        System.out.println("Dlugi wzorzec w dlugim tekscie: "+(endTime-startTime));
    }
    public static void shortInLong(String pattern){
        String text=readFile("C:/Users/K/Downloads/ksiazka.txt");
        long startTime = System.nanoTime();
        KMP_MatcherPrinter(text,pattern);
        long endTime   = System.nanoTime();
        System.out.println("Krótki wzorzec w dlugim tekscie: "+(endTime-startTime));
    }
    public static void longInShort(String pattern){
        String text=readFile("C:/Users/K/Downloads/hymn.txt");
        long startTime = System.nanoTime();
        KMP_MatcherPrinter(text,pattern);
        long endTime   = System.nanoTime();
        System.out.println("Dlugi wzorzec w krótkim tekscie: "+(endTime-startTime));
    }
    public static void shortInShort(String pattern){
        String text=readFile("C:/Users/K/Downloads/hymn.txt");
        long startTime = System.nanoTime();
        KMP_MatcherPrinter(text,pattern);
        long endTime   = System.nanoTime();
        System.out.println("Krótki wzorzec w krótkim tekscie: "+(endTime-startTime));
    }



    public static void main(String args[]) {
        //longInLong("czas");
        //System.out.println("# # # # # # # # # # #");
        //shortInLong("ac");
        //System.out.println("# # # # # # # # # # #");
        longInShort("Marsz, marsz Dąbrowski");
        //System.out.println("# # # # # # # # # # #");
        //shortInShort("am");




    }
}

