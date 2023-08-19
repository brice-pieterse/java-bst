import java.io.FileNotFoundException;
import DecisionTrees.DecisionTree;

// Author - Brice Pieterse
// Student id - T00700445
// Driver class for testing our decision trees
// use one or more args to test the career paths tree, else back pain analyzer will be tested
class DecisionTreeDriver {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length > 0){
            System.out.println("Let's figure out the right career for you...");
            DecisionTree expert = new DecisionTree("CareerPaths.txt");
            expert.evaluate();
        }
        else {
            System.out.println("So, you're having back pain...");
            DecisionTree expert = new DecisionTree("BackPainTree.txt");
            expert.evaluate();
        }
    }
}