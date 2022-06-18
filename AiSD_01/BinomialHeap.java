import java.util.EmptyStackException;

public class BinomialHeap <T extends Comparable>{
    private Node<T> head;

    public void insert(T key) {
        Node<T> node = new Node<>(key);
        node.sibling = head;
        head = node;
        merge();
    }
    private void merge() {
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> next = head.sibling;

        while (next != null) {
            if (next.degree != current.degree
                    || next.sibling != null
                    && current.degree == next.sibling.degree) {
                previous = current;
                current = next;
            } else if (current.key.compareTo(next.key)<= 0) {
                current.sibling = next.sibling;
                next.parent = current;
                next.sibling = current.child;
                current.child = next;
                current.degree++;
            } else {
                if (previous == null) {
                    head = next;
                } else {
                    previous.sibling = next;
                }
                current.parent = next;
                current.sibling = next.child;
                next.child = current;
                next.degree++;
                current = next;
            }
            next = current.sibling;
        }
    }
    public Node<T> minimumNode() throws EmptyHeapException {
        if(head==null) throw new EmptyHeapException();
        Node<T> current=head;
        Node<T> min= head;
        while(current!=null){
            if(current.key.compareTo(min.key)<0) min=current;
            current=current.sibling;
        }
        return min;
    }

    public T minimum() throws EmptyHeapException {
      T min=minimumNode().key;
      return min;
    }



    private Node<T> extractMinimum() throws EmptyHeapException {
        if(head==null) throw new EmptyHeapException();
        Node<T> min=minimumNode();
        Node<T> previous = null;
        if (head != min) {
            previous = head;
            while (previous.sibling != min) {
                previous = previous.sibling;
            }
        }

        if (previous == null) {
            head = min.sibling;
        } else {
            previous.sibling = root.sibling;
        }

        if (node.child != null) {
            Node<K> rootList = null;

            Node<K> child = root.child;
            // for each child of root
            while (child != null) {
                Node<K> next = child.sibling;
                child.sibling = rootList;
                rootList = child;
                child = next;
            }
        }

        size--;
        return root;

    }





    public static void main(String[] args) {
       String x="aa";
       String y="bb";
        if(x.compareTo(y)<0) System.out.println("dziala");
        else  System.out.println("nie dziala");
    }
}


