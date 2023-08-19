package Shared;

// Author - Brice Pieterse
// Student id - T00700445
// a single node in a binary tree, allows access to that element along with its children
public class BinaryTreeNode<T> {
    protected T element;
    protected BinaryTreeNode<T> left, right;


    // constructor - takes in element
    public BinaryTreeNode(T obj){
        element = obj;
        left = null;
        right = null;
    }


    // constructor - takes in element plus left and right subtrees
    public BinaryTreeNode(T obj, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right){
        element = obj;

        if(left == null){
            this.left = null;
        } 
        else this.left = left.getRootNode();

        if(right == null){
            this.right = null;
        } 
        else this.right = right.getRootNode();
    }


    // returns the number of children in the node's left and right subtrees
    public int numChildren(){
        int children = 0;

        if(left != null){
            children += 1 + left.numChildren();
        }

        if(right != null){
            children += 1 + right.numChildren();
        }

        return children;
    }


    // returns the element at this node
    public T getElement(){
        return element;
    }


    // sets the element at this node
    public T setElement(T elem){
        element = elem;
        return element;
    }


    // returns the node's right child node
    public BinaryTreeNode<T> getRight(){
        return right;
    }


    // sets the node's right child node
    public void setRight(BinaryTreeNode<T> r){
        right = r;
    }


    // returns the node's left child node
    public BinaryTreeNode<T> getLeft(){
        return left;
    }


    // sets the node's left child node
    public void setLeft(BinaryTreeNode<T> l){
        left = l;
    }

}
