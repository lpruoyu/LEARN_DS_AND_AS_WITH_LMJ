package taught_by_mjlmj.stepone.other;

import taught_by_mjlmj.stepone.linked_list.CircleLinkedListPlus;

/*约瑟夫问题*/
public class JosephusProblem {

    public static void main(String[] args) {
        josephus(8, 3);
    }

    public static void josephus(int count, int step) {
        CircleLinkedListPlus<Integer> list = new CircleLinkedListPlus<>();
        for (int i = 1; i <= count; i++) {
            list.add(i);
        }
        list.reset();
        while (!list.isEmpty()) {
            int stepC = step;
            while (--stepC != 0) {
                list.next();
            }
            System.out.println(list.remove());
        }
    }

}
