import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList<>();
        //for (int i = 0; i < 3; i++) {
            list.add("1");
            list.add("2");
            Iterator iter = list.iterator();
            list = new ArrayList<>();
            list.add(iter);
            list.add("3");
            list.add("4");
        //}

        IteratorForIteratorTree iterForTree = new IteratorForIteratorTree(list);


        System.out.println(iterForTree.next());
        System.out.println(iterForTree.next());
        System.out.println(iterForTree.next());
        System.out.println(iterForTree.next());



    }


}
