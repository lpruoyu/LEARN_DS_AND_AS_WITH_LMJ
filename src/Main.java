import taught_by_mjlmj.stepone.other.printer.BinaryTrees;
import taught_by_mjlmj.stepone.tree.AVLTree;

public class Main {

    public static void main(String[] args) {

    }

    static void test1() {
        Integer data[] = new Integer[]{
                67, 52, 92, 96, 53, 95, 13, 63, 34
        };

//        System.out.println("-----------------------begin add----------------");

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
//            System.out.println("【" + data[i] + "】");
//            System.out.println("---------------------------------------");
        }

//        BinaryTrees.println(avl);

//        System.out.println("-----------------------begin remove----------------");

        for (int i = 0; i < data.length; i++) {
            avl.remove(data[i]);
            System.out.println("\n\n---------------------------------------");
            BinaryTrees.println(avl);
        }

    }

    private static void test2() {
        AVLTree<Integer> bst = new AVLTree<>();
        int arr[] = {
                35, 23, 18, 10, 5, 21, 20, 25, 27, 28, 37, 48, 47
        };
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        BinaryTrees.println(bst);
        System.out.println(bst.height());
    }

}
