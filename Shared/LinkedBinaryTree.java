package Shared;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

// Author - Brice Pieterse
// Student id - T00700445
// implementation of a general linked binary tree
// includes useful methods such as tree traversals and tree iterators
public class LinkedBinaryTree<T> implements BinaryTreeADT<T>, Iterable<T> {

    protected BinaryTreeNode<T> root;
    protected int modCount;
    
    public LinkedBinaryTree(){
        root = null;
    }

    // forms a binary tree from a root element
    public LinkedBinaryTree(T element){
        root = new BinaryTreeNode<T>(element);
    }

    // forms a binary tree from a root element and two other binary trees
    public LinkedBinaryTree(T element, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right){
        root = new BinaryTreeNode<T>(element);
        root.setLeft(left.root);
        root.setRight(right.root);
    }

    // forms a binary tree from a root element and two binary tree nodes
    public LinkedBinaryTree(T element, BinaryTreeNode<T> left, BinaryTreeNode<T> right){
        root = new BinaryTreeNode<T>(element);
        root.setLeft(left);
        root.setRight(right);
    }

    public BinaryTreeNode<T> getRootNode(){
        return root;
    }

    public T getRootElement(){
        return root.element;
    }

    // returns a binary tree formed by the root's left child
    public LinkedBinaryTree<T> getLeft(){
        BinaryTreeNode<T> node = root.getLeft();
        T root = node.getElement();
        BinaryTreeNode<T> left = node.getLeft();
        BinaryTreeNode<T> right = node.getRight();
        return new LinkedBinaryTree<T>(root, left, right);
    }

    // returns a binary tree formed by the root's right child
    public LinkedBinaryTree<T> getRight(){
        BinaryTreeNode<T> node = root.getRight();
        T root = node.getElement();
        BinaryTreeNode<T> left = node.getLeft();
        BinaryTreeNode<T> right = node.getRight();
        return new LinkedBinaryTree<T>(root, left, right);
    }


    // Since this is a binary tree, not binary search tree, we'll rely on inorder traversal check if it contains the target
    public boolean contains(T target){
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inOrder(root, tempList);
        return tempList.arr.contains(target);
    }

    
    public boolean isEmpty(){
        return root == null;
    }

    // Since this is a binary tree, not binary search tree, we'll rely on inorder traversal to find the target
    public T find(T target) throws NoSuchElementException {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inOrder(root, tempList);
        int i = 0;
        while(i < tempList.arr.size()){
            if(tempList.arr.get(i).equals(target)){
                return tempList.arr.get(i);
            }
            i++;
        }
        throw new NoSuchElementException("Element not found");
    }


    // return the number of elements in the linked binary tree
    public int size(){
        return root.numChildren();
    }


    // Visit the left child, then the node, then the right child
    protected void inOrder(BinaryTreeNode<T> start, ArrayUnorderedList<T> arr){
        if(start.getLeft() != null){
            inOrder(start.getLeft(), arr);
        }
        arr.addToRear(start.getElement());
        if(start.getRight() != null){
            inOrder(start.getRight(), arr);
        }
    }

    // visits each node, then its left children, then its right children
    // starting from the root
    protected void preOrder(BinaryTreeNode<T> start, ArrayUnorderedList<T> arr){
        arr.addToRear(start.getElement());
        if(start.getLeft() != null){
            preOrder(start.getLeft(), arr);
        }
        if(start.getRight() != null){
            preOrder(start.getRight(), arr);
        }
    }

    // visits the left children, then the right children, then the node itself
    protected void postOrder(BinaryTreeNode<T> start, ArrayUnorderedList<T> arr){
        if(start.getLeft() != null){
            postOrder(start.getLeft(), arr);
        }
        if(start.getRight() != null){
            postOrder(start.getRight(), arr);
        }
        arr.addToRear(start.getElement());
    }


    // visits the nodes at each level, one at a time, starting from the root
    protected void levelOrder(BinaryTreeNode<T> start, ArrayUnorderedList<T> arr){
        Queue<BinaryTreeNode<T>> nodes = new LinkedList<BinaryTreeNode<T>>();
        nodes.offer(start);
        while(nodes.size() > 0){
            BinaryTreeNode<T> next = nodes.poll();
            if(next.getLeft() != null){
                nodes.offer(next.getLeft());
            }
            if(next.getRight() != null){
                nodes.offer(next.getRight());
            }
            arr.addToRear(next.getElement());
        }
    }

    // defaults to inOrder traversal
    public Iterator<T> iterator(){
        return iteratorInOrder();
    }


    // various iterators for the tree using different tree traversal options
    public Iterator<T> iteratorInOrder(){
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inOrder(root, tempList);
        return new TreeIterator(tempList.arr.iterator());
    }


    public Iterator<T> iteratorPreOrder(){
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        preOrder(root, tempList);
        return new TreeIterator(tempList.arr.iterator());
    }


    public Iterator<T> iteratorPostOrder(){
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        postOrder(root, tempList);
        return new TreeIterator(tempList.arr.iterator());
    }


    public Iterator<T> iteratorLevelOrder(){
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        levelOrder(root, tempList);
        return new TreeIterator(tempList.arr.iterator());
    }


    // helper iterator class that allows for vsiting the various elements in the tree
    private class TreeIterator implements Iterator<T> {

        int iteratorModCount;
        Iterator<T> listIterator;

        // contstructor, takes in the iterator for a particular tree traveral.
        // ie. inOrder traversal passes the iterator for the array constructed in an inOrder traversal
        public TreeIterator(Iterator<T> i){
            iteratorModCount = modCount;
            listIterator = i;
        }

        // checks if tree has a next element in the iteration of its elements
        public boolean hasNext() throws ConcurrentModificationException{
            if(iteratorModCount != modCount){
                throw new ConcurrentModificationException("Binary Tree out of sync with iterator");
            }
            return listIterator.hasNext();
        }

        // returns the next element in the iteration of the tree
        public T next() throws ConcurrentModificationException {
            if(iteratorModCount != modCount){
                throw new ConcurrentModificationException("Binary Tree out of sync with iterator");
            }
            return listIterator.next();
        }

        // unsupported remove method for this iterator (remove method is optional to iterators)
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Removal not permitted using this iterator");
        }

    }


}
