package Shared;

import java.util.Iterator;

// Author - Brice Pieterse
// Student id - T00700445
// abstract representation of a binary tree
public interface BinaryTreeADT<T> {
    
    // returns the root element
    T getRootElement();

    // checks if the tree contains the target element
    boolean contains(T targetElement);

    // seraches for a particular element
    T find(T targetElement);

    // several iterators using various tree traversals

    Iterator<T> iteratorInOrder();

    Iterator<T> iteratorPreOrder();

    Iterator<T> iteratorPostOrder();

    Iterator<T> iteratorLevelOrder();

    // checks if empty
    boolean isEmpty();

    // returns size
    int size();

    String toString();

}
