/**
 * HeapNode class
 *
 * @author Hieu Duong
 * @date 4/2/18
 */

public class HeapNode<T>{
    double priority;
    T data;
    public HeapNode(T datum, double priority){
        this.priority=priority;
        data=datum;
    }

    public double getP(){return priority;}
    public T getD(){return data;}
    public String toString(){
        return "("+priority+") "+data;
    }
}