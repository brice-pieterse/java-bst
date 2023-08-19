package BalancedBST;

import Shared.LinkedBinaryTree;
import Shared.BinaryTreeNode;
import Shared.NonComparableElementException;
import Shared.ElementNotFoundException;
import java.lang.Comparable;
import Shared.ArrayUnorderedList;


// Author - Brice Pieterse
// Student id - T00700445
// implementation of a linked binary search tree
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchADT<T> {


    // forms a binary tree from a root element
    public LinkedBinarySearchTree(){
        super();
    }

    
    // forms a binary tree from a root element
    public LinkedBinarySearchTree(T element){
        super(element);
    }


    // forms a binary tree from a root element and two other binary trees
    public LinkedBinarySearchTree(T element, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right){
        super(element, left, right);
    }


    // forms a binary tree from a root element and two binary tree nodes
    public LinkedBinarySearchTree(T element, BinaryTreeNode<T> left, BinaryTreeNode<T> right){
        super(element, left, right);
    }


    // adds an element to the tree
    public void addElement(T elem) throws NonComparableElementException {
        if(!(elem instanceof Comparable)){
            throw new NonComparableElementException("LinkedBinarySearchTree");
        }

        Comparable<T> comparable = (Comparable<T>)elem;

        if(isEmpty()){
            root = new BinaryTreeNode<T>(elem);
        }
        else {
            if(comparable.compareTo(root.getElement()) < 0){
                if(root.getLeft() == null){
                    root.setLeft(new BinaryTreeNode<T>(elem));
                } else {
                    addElement(elem, root.getLeft());
                }
            } 
            else {
                if(root.getRight() == null){
                    root.setRight(new BinaryTreeNode<T>(elem));
                } else {
                    addElement(elem, root.getRight());
                }
            }
        }
        modCount++;
    
    }


    // helper method for addElement
    private void addElement(T elem, BinaryTreeNode<T> root){
        Comparable<T> comparable = (Comparable<T>)elem;

        if(comparable.compareTo(root.getElement()) < 0){
            if(root.getLeft() == null){
                root.setLeft(new BinaryTreeNode<T>(elem));
            } else {
                addElement(elem, root.getLeft());
            }
        } 
        else {
            if(root.getRight() == null){
                root.setRight(new BinaryTreeNode<T>(elem));
            } else {
                addElement(elem, root.getRight());
            }
        }
    }


    // removes an element from the tree
    public T removeElement(T elem) throws ElementNotFoundException, NullPointerException {
        T result = null;
        if(isEmpty()){
            throw new ElementNotFoundException("LinkedBinarySearchTree");
        }
        if(elem == null){
            throw new NullPointerException("LinkedBinarySearchTree");
        }
        else {
            BinaryTreeNode<T> parent = null;
            
            if(((Comparable<T>)elem).equals(root.getElement())){
                result = root.getElement();
                BinaryTreeNode<T> temp = replacement(root);
                if(temp == null){
                    root = null;
                }
                else {
                    root.setElement(temp.getElement());
                    root.setRight(temp.getRight());
                    root.setLeft(temp.getLeft());
                }
                modCount--;
            } else {
                parent = root;
                if(((Comparable<T>)elem).compareTo(root.getElement()) < 0){
                    result = removeElement(elem, root.getLeft(), parent);
                } else {
                    result = removeElement(elem, root.getRight(), parent);
                }
            }
        }

        return result;
    }


    // helper method for removeElement
    private T removeElement(T elem, BinaryTreeNode<T> node, BinaryTreeNode<T> parent) throws ElementNotFoundException {
        T result = null;
        if(node == null){
            throw new ElementNotFoundException("LinkedBinarySearchTree");
        }
        else {
            if(((Comparable<T>)elem).equals(node.getElement())){
                result = node.getElement();
                BinaryTreeNode<T> temp = replacement(node);
                if(parent.getRight() == node){
                    parent.setRight(temp);
                }
                else {
                    parent.setLeft(temp);
                }
                modCount--;
            }
            else {
                parent = node;
                if(((Comparable<T>)elem).compareTo(node.getElement()) < 0){
                    result = removeElement(elem, node.getLeft(), parent);
                } 
                else {
                    result = removeElement(elem, node.getRight(), parent);
                }
            }
        }
        return result;
    }


    // helper method for removeElement
    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node){
        // if node has no children, returns null
        if(node.getLeft() ==  null && node.getRight() == null){
            return null;
        }
        // if node has one child, returns that child
        else if(node.getLeft() != null && node.getRight() == null){
            return node.getLeft();
        }
        else if(node.getRight() != null && node.getLeft() == null){
            return node.getRight();
        }
        // inorder successor becomes the replacemernt
        // it takes over nodes left and right
        // its parent takes over its right if it has one
        else {
            BinaryTreeNode<T> current = node.getRight();
            BinaryTreeNode<T> parent = null;
            while(current.getLeft() != null){
                parent = current;
                current = current.getLeft();
            }
            current.setLeft(node.getLeft());
            if(node.getRight() != current){
                parent.setLeft(current.getRight());
            }
            current.setRight(node.getRight());
            return current;
        }
    }


    // removes every occurance of the element
    public void removeAllOccurances(T elem) throws ClassCastException, ElementNotFoundException {
        boolean found = false;
        if(!(elem instanceof Comparable)){
            throw new ClassCastException("LinkedBinarySearchTree");
        }
        try {
            // loop exits after first exception
            while (true){
                removeElement(elem);
                found = true;
            }
        }
        // exception checks whether an element was found or not, and throws the exception if not
        catch(ElementNotFoundException e){
            if(!found){
                throw e;
            }
        }

    }


    /* removal and retrieval and min and max elements in the tree */
    public T removeMin(){
        T elem = findMin();
        removeElement(elem);
        return elem;
    }

    public T removeMax(){
        T elem = findMax();
        removeElement(elem);
        return elem;
    }

    public T findMin(){
        BinaryTreeNode<T> current = root;
        T elem = null;
        while (current.getLeft() != null){
            current = current.getLeft();
        }

        if(current != null){
            elem = current.getElement();
        }
        
        return elem;
    }

    public T findMax(){
        BinaryTreeNode<T> current = root;
        T elem = null;
        while (current.getRight() != null){
            current = current.getRight();
        }

        if(current != null){
            elem = current.getElement();
        }

        return elem;
    }


    // balance the tree with a brute force approach
    public void balanceTree(){
        // use inOrder to put the tree in an ordered array
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inOrder(root, tempList);

        // recursively partition the array into two sub arrays around a middle element
        root = orderedListToTree(tempList);
    }

    // helper method that builds a balanced binary search tree from an ordered list
    private BinaryTreeNode<T> orderedListToTree(ArrayUnorderedList<T> ul){
        if(ul.arr.size() == 0){
            return null;
        }

        int rootIndex = (ul.arr.size() - 1)/2;
        T root = ul.arr.get(rootIndex);
        BinaryTreeNode<T> rootNode = new BinaryTreeNode<T>(root);


        // recursive call to build a balanced left subtree
        ArrayUnorderedList<T> leftEls = new ArrayUnorderedList<T>();
        for(int i = 0; i < rootIndex; i++){
            leftEls.addToRear(ul.arr.get(i));
        }
        rootNode.setLeft(orderedListToTree(leftEls));


        // recursive call to build a balanced right subtree
        ArrayUnorderedList<T> rightEls = new ArrayUnorderedList<T>();
        for(int i = rootIndex + 1; i < ul.arr.size(); i++){
            rightEls.addToRear(ul.arr.get(i));
        }
        rootNode.setRight(orderedListToTree(rightEls));


        return rootNode;
    }


    // calculates the height (longest path from root to leaf) in the tree
    public int getHeight(){
        if(root == null){
            return 0;
        }

        int left = getHeight(root.getLeft());
        int right = getHeight(root.getRight());

        return Math.max(left, right);
    }

    
    // recursive helper method for finding the height of the tree
    public int getHeight(BinaryTreeNode<T> node){
        if(node == null){
            return 0;
        }
        
        int left = 1 + getHeight(node.getLeft());
        int right = 1 + getHeight(node.getRight());

        return Math.max(left, right);
    }

}
