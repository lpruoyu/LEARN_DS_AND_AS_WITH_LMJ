import taught_by_mjlmj.other.printer.BinaryTrees;
import taught_by_mjlmj.tree.BinarySearchTree;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int arr[] = {35, 23, 18, 10, 5, 21, 20, 25, 27, 28, 37, 48, 47};
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        BinaryTrees.println(bst);
        System.out.println(bst.height());
        bst.remove(35);
        bst.remove(20);
        bst.remove(18);
        bst.remove(5);
        bst.remove(27);
        bst.remove(48);
        BinaryTrees.println(bst);
        System.out.println(bst.height());
        bst.clear();
        System.out.println(bst.height());
    }

}
