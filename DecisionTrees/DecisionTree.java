package DecisionTrees;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import Shared.LinkedBinaryTree;
import java.util.ArrayList;

// Author - Brice Pieterse
// Student id - T00700445
// takes in a file, reads the contents, and constructs a binary tree
public class DecisionTree {
    private LinkedBinaryTree<String> tree;
    
    public DecisionTree(String filename) throws FileNotFoundException {
        File inputFile = new File(filename);
        Scanner scan = new Scanner(inputFile);

        int numberNodes = scan.nextInt();
        scan.nextLine();

        ArrayList<LinkedBinaryTree<String>> nodes = new ArrayList<LinkedBinaryTree<String>>(numberNodes);


        // creates a single element binary tree for each element to be added
        for(int i = 0; i < numberNodes; i++){
            String s = scan.nextLine();
            nodes.add(i, new LinkedBinaryTree<String>(s));
        }


        // the final entries in the file are sets of 3 numbers corresponding to the index position of a root, and its left and right child
        int root = 0, left, right;
        while(scan.hasNext()){
            root = scan.nextInt();
            left = scan.nextInt();
            right = scan.nextInt();
            nodes.set(root, new LinkedBinaryTree<String>(nodes.get(root).getRootElement(), nodes.get(left), nodes.get(right)));
        }

        // Retrieves the root
        tree = nodes.get(0);
        scan.close();
    }

    // kicks off program evaluation for the decision tree
    public void evaluate(){
        LinkedBinaryTree<String> current = tree;
        Scanner scan = new Scanner(System.in);

        while(current.size() > 1){
            System.out.println(current.getRootElement());
            if(scan.nextLine().equalsIgnoreCase("N")){
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
        }

        System.out.println(current.getRootElement());
        scan.close();
    }
}
