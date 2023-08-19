import java.lang.Integer;
import java.util.Iterator;
import BalancedBST.LinkedBinarySearchTree;
import Shared.ElementNotFoundException;
import Shared.NonComparableElementException;

// Author - Brice Pieterse
// Student id - T00700445
// Driver class for testing our LinkedBinarySearchTree class
// use one or more args to run first series of tests, else second series will be run
public class BalancedBSTDriver {
    public static void main(String[] args){

        if(args.length >= 1){
            LinkedBinarySearchTree<Integer> bst = new LinkedBinarySearchTree<Integer>(32);

            try {
                System.out.println("Removing an element that doesn't exist.");
                bst.removeElement(10);
            }
            catch(ElementNotFoundException e){
                System.out.println("Caught the error.");
            }

            try {
                System.out.println("Trying to add null.");
                bst.addElement(null);
            }
            catch(NonComparableElementException e){
                System.out.println("Caught the error.");
            }

            for (int i = 0; i <= 20; i++){
                bst.addElement( (int)Math.ceil(Math.random() * 100) );
            }

            System.out.println("20 random integers added to the binary search tree");
            System.out.println("Tree now has size: " + bst.size());
            System.out.println("Tree now has height: " + bst.getHeight());

            bst.balanceTree();
            System.out.println("After calling the tree's balanceTree method:");
            System.out.println("Tree now has size: " + bst.size());
            System.out.println("Tree now has height: " + bst.getHeight());
            System.out.println("Tree is balanced: " + balanceCheck(bst.getHeight(), bst.size()));

            System.out.println("Tree has min: " + bst.findMin());
            System.out.println("Removed one occurance of min: " + bst.removeMin());
            System.out.println("Tree now has min: " + bst.findMin());
        } 
        else {
            LinkedBinarySearchTree<Integer> bst = new LinkedBinarySearchTree<Integer>();

            // this will construct a degenerate tree
            for (int i = 3; i <= 10; i++){
                bst.addElement(i);
            }
    
            System.out.println("New Binary Search Tree created with integers 3-10 added consecutively");
            System.out.println("Tree has size: " + bst.size());
            System.out.println("Tree has height: " + bst.getHeight());
    
            bst.removeElement(5);
            bst.removeElement(8);
    
            System.out.println("Removed 5 and 8.");
            System.out.println("Tree now has size: " + bst.size());
            System.out.println("Tree now has height: " + bst.getHeight());
    
            bst.balanceTree();
            System.out.println("After calling the tree's balanceTree method:");
            System.out.println("Tree now has size: " + bst.size());
            System.out.println("Tree now has height: " + bst.getHeight());
            System.out.println("Tree is balanced: " + balanceCheck(bst.getHeight(), bst.size()));
    
            bst.addElement(2);
            bst.addElement(1);
            bst.addElement(0);
    
            System.out.println("Adding 0-2, which makes the tree no longer balanced.");
            System.out.println("Tree now has size: " + bst.size());
            System.out.println("Tree now has height: " + bst.getHeight());
    
            bst.balanceTree();
            System.out.println("But after calling the tree's balanceTree method again:");
            System.out.println("Tree now has size: " + bst.size());
            System.out.println("Tree now has height: " + bst.getHeight());
            System.out.println("Tree is balanced: " + balanceCheck(bst.getHeight(), bst.size()));
            System.out.println("Tree has min: " + bst.findMin());
            System.out.println("Tree has max: " + bst.findMax());
    
            bst.addElement(0);
            bst.addElement(0);
            bst.addElement(0);
            bst.removeAllOccurances(0);
            System.out.println("Added several 0s. Now removing all occurances of 0. 1 should now be the min:");
            System.out.println("Tree has min: " + bst.findMin());

            bst.addElement(11);
            bst.addElement(12);
            bst.addElement(13);
            bst.addElement(14);

            System.out.println("Adding 11-14, which makes the tree no longer balanced.");
            System.out.println("Tree now has size: " + bst.size());
            System.out.println("Tree now has height: " + bst.getHeight());
            System.out.println("Removing the max, which should be 14: " + bst.removeMax());
    
            bst.balanceTree();
            System.out.println("But after calling the tree's balanceTree method again:");
            System.out.println("Tree now has size: " + bst.size());
            System.out.println("Tree now has height: " + bst.getHeight());
            System.out.println("Tree is balanced: " + balanceCheck(bst.getHeight(), bst.size()));
            System.out.println("Tree has min: " + bst.findMin());
            System.out.println("Tree has max: " + bst.findMax());

            System.out.println("Preparing to iterate the tree using level order:");
            Iterator<Integer> levelIterator = bst.iteratorLevelOrder();

            while(levelIterator.hasNext()){
                System.out.println(levelIterator.next());
            }

        }
    }

    // helper for checking tree balance using height and number of elements in the tree
    static boolean balanceCheck(int height, int size){
        double logBase2 = Math.log(size) / Math.log(2);
        if(logBase2 >= height && logBase2 <= height + 1){
            return true;
        }
        return false;
    }
}
