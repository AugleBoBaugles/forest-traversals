import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeProblems {

  /*
   postOrder (Node Version)
   -----------
   Given the root of a tree print out the values of the nodes in post-order.
   Print each value on a separate line.

   Example:
   If the tree is:
          5
       /  |  \
      3   9   8
        / | \  
       4  1  2
   The output should be:
   3
   4
   1
   2
   9
   8
   5

   If the root is null, do nothing.
   */
  public static <T> void postOrder(Node<T> root) {

    if (root == null) {
      return;
    }

    if (root.children == null) {
      return;
    }

    for (var child : root.children) {
      postOrder(child);
    }

    System.out.println(root.value);

  }

  /*
   postOrder (Node Version)
   -----------
   Given the root of a tree print out the values of the nodes in post-order.
   Print each value on a separate line.
   If the tree is null or does not contain the root, do nothing.

   Example:
   For a tree represented as:
     5 -> [3, 9, 8]
     3 -> []
     9 -> [4, 1, 2]
     4 -> []
     1 -> []
     2 -> []
   The output should be:
   3
   4
   1
   2
   9
   8
   5
   */
  public static <T> void postOrder(Map<T, List<T>> tree, T root) {
    // base case
    if (root == null || tree == null || !tree.containsKey(root)) return;

    // traverse
    List<T> children = tree.get(root);

    for( var child : children){
      postOrder(tree, child);
    }

    // handle

    System.out.println(root);
  }

  /*
   sumTree (Node Version)
   -----------
   Given a tree built with the Node class (with integer values), compute and return the sum of all the node values.
   Example:
   If the tree is:
          5
       /  |  \
      3   9   8
        / | \  
       4  1  2
   then the method should return 32.
   A null tree should return 0
  */
  public static int sumTree(Node<Integer> root) {

    if (root == null) {
      return 0;
    }

    if (root.children.isEmpty()) {
      return root.value;
    }

    int valueTracker = root.value;

    for (var child : root.children) {
      System.out.println(child.value);
      valueTracker += sumTree(child);
    }

    return valueTracker;
  }

  /*
   sumTree (Map Version)
   -----------
   Given a tree represented as a map (where every node appears as a key and leaf nodes map to an empty list),
   compute the sum of all nodes.
   Example:
   For a tree represented as:
     5 -> [3, 9, 8]
     3 -> []
     9 -> [4, 1, 2]
     4 -> []
     1 -> []
     2 -> []
   the method should return 32.

   A null tree should return 0

   Hint: There's a simple way to do this!
  */
  public static int sumTree(Map<Integer, List<Integer>> tree) {
    // base case 
    if (tree == null || tree.keySet() == null) return 0;

    int counter = 0;

    // handle
    for (var key : tree.keySet()){
      counter += key;
    }
    
    // traverse

    return counter;
  }

  /*
   findRoot
   -----------
   Given a tree represented as a map where each key is a parent node and the value is a list of its children,
   find the root of the tree. The root is the node with no parents.
   Example:
   If the tree is represented as:
     20 -> [40]
     8  -> []
     30 -> []
     10 -> [20, 30, 99]
     40 -> []
     99 -> [8]
   then the method should return 10.

  You can assume the tree is non-null and well-formed.

   Hint: No recursion needed! Think about how you would do this by hand.
  */
  public static <T> T findRoot(Map<T, List<T>> tree) {
        
    List<T> checkValues = new ArrayList<>();

  for (var value : tree.values()) {
    checkValues.addAll(value);
  }

    for (var key : tree.keySet()) {
      if (!checkValues.contains(key)) {
        return key;
      }
    }

    return null;
  }

  /*
   maxDepth (Node Version)
   -----------
   Compute the maximum depth of a tree using the Node class. The depth is the number of nodes along
   the longest path from the root down to the farthest leaf. The root is at depth 1. If the tree is null, return 0.
   Example:
   For a tree structured as:
          A
       /  |  \
      B   E   C
      |      / \
      E     D   Q
             \ 
              Z
   the maximum depth is 4.

   
  */
  public static <T> int maxDepth(Node<T> root) {
    if (root == null) return 0;
    if (root.children == null) return 1;

    int betterChild = 0;

    // loop thorugh children, find highest child
    for (var child : root.children){
      betterChild = Math.max(betterChild, maxDepth(child));
      
    }

    return betterChild + 1;
  }

  /*
   maxDepth (Map Version)
   -----------
   Compute the maximum depth of a tree using the Node class. The depth is the number of nodes along
   the longest path from the root down to the farthest leaf. The root is at depth 1. If the tree is null, return 0.
   Example:
   For a tree structured as:
    A -> [B, E, C]
    B -> [E]
    E -> []
    C -> [D, Q]
    D -> [Z]
    Q -> []
    Z -> []
   the maximum depth is 4 (A->C->D->Z).

   Hint: Use findRoot to start. Then, make a recursive helper method.
  */
  public static int maxDepth(Map<String, List<String>> tree) {

    if (tree == null || tree.keySet() == null) return 0;

    if (tree.size() == 1) return 1;

    String treeRoot = findRoot(tree);

    return mapDepthHelper(tree, treeRoot, 1);
  }

  public static int mapDepthHelper(Map<String, List<String>> tree, String treeRoot, int currentDepth) {

    if(!tree.containsKey(treeRoot)) {
      return currentDepth;
    }

    int mapBetterChild = 0;

    for (var child : tree.get(treeRoot)) {
      mapBetterChild = Math.max(mapBetterChild, mapDepthHelper(tree, child, currentDepth));
    };

    return mapBetterChild + 1;
  }

}
