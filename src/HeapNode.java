public class HeapNode<T> {
    double priority;
    T data;
    HeapNode nodeLeft;
    HeapNode nodeRight;

    public HeapNode(){

    }

    public HeapNode(HeapNode nLeft, HeapNode nRight){
        this.priority = nLeft.getP()+nRight.getP();
        data = (T)(nLeft.getD()+""+nRight.getD());
        this.nodeLeft = nLeft;
        this.nodeRight = nRight;
    }

    public HeapNode(T datum, double priority){
        this.priority=priority;
        data=datum;
    }

    public HeapNode getNodeLeft() {
        return nodeLeft;
    }

    public HeapNode getNodeRight() {
        return nodeRight;
    }

    public boolean isLeaf(){
        if(nodeLeft==null&& nodeRight==null){
            return true;
        }else return false;
    }
    public double getP(){return priority;}
    public T getD(){return data;}
    public String toString(){
        return "("+priority+") "+data;
    }
}
