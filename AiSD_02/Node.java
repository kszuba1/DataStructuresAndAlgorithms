public class Node<T> {
    int degree;
    T key;
    Node<T> parent;
    Node<T> sibling;
    Node<T> child;
    Node(T key){
        this.key=key;
    }
}
