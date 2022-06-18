public class Node<T> {
    T key;
    Node<T> parent;
    int degree;
    Node<T> sibling;
    Node<T> child;
    Node(T key){
        this.key=key;
    }


}
