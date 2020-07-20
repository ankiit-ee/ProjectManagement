package RedBlack;

import Util.RBNodeInterface;

import java.util.ArrayList;
import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
    T element;
    E value;
    int colour;
    RedBlackNode parent;
    RedBlackNode left;
    RedBlackNode right;
    List<E> req;
    public  RedBlackNode(){

    }
    public RedBlackNode(T element, E value){
        req = new ArrayList<>();
        this.element=element;
        this.value=value;
        this.left=null;
        this.right=null;     ////////// did not intialise the parent
        req.add(value);
        this.colour = 0;    ////    RED == 1      BLACK == 0
    }
    public RedBlackNode grand(RedBlackNode n){
        if(n!=null && n.parent!=null){
            return n.parent.parent;
        }
        else return null;
    }
    public RedBlackNode uncle(RedBlackNode n){
        RedBlackNode g = grand(n);
        if(g==null){
            return null;
        }
        else if(n.parent == g.left) return g.right;
        else return g.left;
    }

    @Override
    public E getValue() {
        return value;
    }

    public void setReq(List value) {
        req = value;
    }

    @Override
    public List<E> getValues() {
        return req;
    }

}
