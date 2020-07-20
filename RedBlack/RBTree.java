package RedBlack;


import java.util.ArrayList;
import java.util.List;

public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {
    RedBlackNode root;
    RedBlackNode parent;
    RedBlackNode grand;
    public RBTree(){
        this.root = new RedBlackNode();
        root=null;
    }


    @Override
    public void insert(T key, E value) {
        RedBlackNode node = new RedBlackNode(key,value);
        RedBlackNode temp;
        temp=parent=grand=root;
        if(root==null){
            root=node;
            node.colour=0;
            node.parent=null;
        }
        else {
            node.colour = 1;
            while (true) {
                if (temp.element.compareTo(key) < 0) {
                    if(temp.left==null){
                        temp.left=node;
                        node.parent=temp;
                        break;
                    }
                    else temp = temp.left;
                } else if(temp.element.compareTo(key)>=0) {
                    if(temp.right==null) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    }
                    else temp = temp.right;
                }
            }
            rearrange(node);
        }

    }

    @Override
    public RedBlackNode<T, E> search(T key) {
        RedBlackNode temp = root;
        boolean flag = false;
        RedBlackNode returned = new RedBlackNode();
        List x = new ArrayList();
        if(root==null) return null;
        while (temp!=null){
            if(temp.element.compareTo(key)<0)temp = temp.left;
            else if(temp.element.compareTo(key)>0) temp = temp.right;
            else {
                x.add(temp.getValue());
                flag=true;
                temp=temp.right;
            }
        }
        if(flag) {
            returned.setReq(x);
            return returned;
        }
        else {
            returned.setReq(null);
            return returned;
        }
    }

    private void rearrange(RedBlackNode node){
        while(node.parent!=null&& node.parent.colour==1){
            if(node.parent==node.parent.parent.left){
                RedBlackNode uncle = node.parent.parent.right;
                if(uncle!=null&& uncle.colour==1){
                    node.parent.colour=0;
                    uncle.colour=0;
                    node.parent.parent.colour=1;
                    node = node.parent.parent;
                    continue;
                }
                if(node==node.parent.right){
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.colour = 0;
                node.parent.parent.colour=1;
                rotateRight(node.parent.parent);
            }
            else { /// no parent means root
                RedBlackNode uncle = node.parent.parent.left;
                if(uncle!=null&& uncle.colour==1){
                    node.parent.colour=0;
                    uncle.colour=0;
                    node.parent.parent.colour=1;
                    node = node.parent.parent;
                    continue;
                }
                if(node==node.parent.left){
                    node = node.parent;
                    if(node!=null) rotateRight(node);
                }
                node.parent.colour = 0;
                node.parent.parent.colour=1;
                rotateLeft(node.parent.parent);
            }
        }
        root.colour=0;
    }


    public void rotateLeft(RedBlackNode node){
        if(node.parent!=null){
            if(node==node.parent.left) node.parent.left=node.right;
            else node.parent.right=node.right;

            node.right.parent=node.parent;
            node.parent=node.right;
            if(node.right.left!=null) node.right.left.parent=node;
        }
        else { // no parent means root
            RedBlackNode temp = root.right;
            root.right= temp.left;
            if( temp.left!=null)temp.left.parent=root;
            root.parent=temp;
            temp.left=root;
            temp.parent=null;
            root=temp;
        }

    }
    public void rotateRight(RedBlackNode node){
        if(node.parent!=null){
            if(node==node.parent.left) node.parent.left=node.left;
            else node.parent.right=node.left;

            node.left.parent=node.parent;
            node.parent=node.left;
            if(node.left.right!=null) node.left.right.parent=node;
        }
        else {
            RedBlackNode temp = root.left;
            root.left= temp.right;
            if(temp.right!=null)temp.right.parent=root;
            root.parent=temp;
            temp.right=root;
            temp.parent=null;
            root=temp;
        }

    }
}