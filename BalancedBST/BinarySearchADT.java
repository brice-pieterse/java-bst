package BalancedBST;

import Shared.BinaryTreeADT;

// Author - Brice Pieterse
// Student id - T00700445
// abstract data type for a binary search tree
// extension of a general binary tree
public interface BinarySearchADT<T> extends BinaryTreeADT<T> {

    // adds an element to the tree
    void addElement(T elem);

    // removes an element from the tree
    T removeElement(T elem);

    // removes all opccurances of an element from the tree
    void removeAllOccurances(T elem);

    // several methods for retrieval and removal of min and max in the tree
    T removeMin();
    T removeMax();
    T findMin();
    T findMax();
}
