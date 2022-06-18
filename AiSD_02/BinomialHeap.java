public class BinomialHeap<T extends Comparable> {
    private Node<T> head;
    public void insert(T value){
        BinomialHeap<T> H=new BinomialHeap<>();

    }
    private Node<T> merge(BinomialHeap<T> H1,BinomialHeap<T> H2){
        Node<T> headH;
        Node<T> currentH;
        Node<T> currentH1=H1.head;
        Node<T> currentH2=H2.head;
        if(currentH1.degree<=currentH2.degree){
            headH=currentH1;
            currentH=headH;
            currentH1=currentH1.sibling;
        }
        else {
            headH=currentH2;
            currentH=headH;
            currentH2=currentH2.sibling;
        }
        while(currentH1!=null && currentH2!=null){
            if(currentH1!=null && currentH2!=null){
                if(currentH1.degree<=currentH2.degree){
                    currentH.sibling=currentH1;
                    currentH=currentH.sibling;
                    currentH1=currentH1.sibling;
                }else{
                    currentH.sibling=currentH2;
                    currentH=currentH.sibling;
                    currentH2=currentH2.sibling;
                }
            }else if(currentH1!=null){
                currentH.sibling=currentH1;
                currentH=currentH.sibling;
                currentH1=currentH1.sibling;
            }else{
                currentH.sibling=currentH2;
                currentH=currentH.sibling;
                currentH2=currentH2.sibling;

            }
        }


        return headH;
    }
    public BinomialHeap<T> union(BinomialHeap<T> H1,BinomialHeap<T> H2){
        BinomialHeap<T> H=new BinomialHeap<>();
        H.head=merge(H1,H2);
        if(H.head==null) return H;
        Node<T> previous=null;
        Node<T> current=H.head;
        Node<T> next=current.sibling;
        while(next!=null){
            if(current.degree!=next.degree || (next.sibling!=null && next.sibling.degree==current.degree)){
              previous=current;
              current=next;
            }else {
                if (current.key.compareTo(next.key) <= 0) {
                    current.sibling = next.sibling;
                    link(next, current);
                } else {
                    if (previous == null) {
                        H.head = next;
                    } else {
                        previous.sibling = next;
                    }
                    link(current, next);
                    current = next;
                }

            }
            next=current.sibling;
        }
        return H;
    }
    public void link(Node<T> y,Node<T> z){
        y.parent=z;
        y.sibling=z.child;
        z.child=y;
        z.degree++;
    }
    public void insert(BinomialHeap<T> H,Node<T> x){
        BinomialHeap<T> H2=new BinomialHeap<>();
        x.parent=null;
        x.child=null;
        x.sibling=null;
        x.degree=0;
        H2.head=x;
        H=union(H,H2);
    }
    public Node<T> minimum(BinomialHeap<T> H){
        Node<T> current=H.head;
        Node<T> min=H.head;
        while(current!=null){
            if(current.key.compareTo(min.key)<0) min=current;
            current=current.sibling;
        }
        return min;
    }
    public Node<T> extractMin(BinomialHeap<T> H){
        Node<T> min=minimum(H);
        Node<T> previous=null;
        if(H.head!=min){
            previous=H.head;
            while(previous.sibling!=min){
                previous=previous.sibling;
            }
        }
        if(previous==null) H.head=min.sibling;
        else previous.sibling=min.sibling;
        BinomialHeap<T> H2=new BinomialHeap<>();
        Node<T> x=min.child;
        int dgr=min.degree;
        Node<T> currentH2=null;
        while(min.degree!=0) {
            if(x.sibling!=null){
                x=x.sibling;
            }
            else{
                if(min.degree==dgr){
                    H2.head=x;
                    currentH2=H2.head;
                    x=null;
                    min.degree--;
                    x=min.child;
                }
                else{
                    currentH2.sibling=x;
                    currentH2=currentH2.sibling;
                    x=null;
                    min.degree--;

                }

            }


        }
        H=union(H,H2);
        return min;
        }
    public void decreaseKey(BinomialHeap<T> H,Node<T> x,T k) throws Exception {
        if(k.compareTo(x.key)>0) throw new Exception();
        x.key=k;
        Node<T> y=x;
        Node<T> z=y.parent;
        while(z!=null && y.key.compareTo(z.key)<0){
           T tmp=y.key;
           z.key=y.key;
           y.key=tmp;
           y=z;
           z=y.parent;
        }
    }
    public void delete(BinomialHeap<T> H, Node<T> x) throws Exception {
        decreaseKey(H,x,minimum(H).key);
        extractMin(H);
    }


    }






