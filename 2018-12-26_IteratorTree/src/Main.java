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

        System.out.println(normalizeList(list));



    }


    public static ArrayList<String> normalizeList(ArrayList baseList) {
        ArrayList<String> targetList = new ArrayList<>();

        ArrayDeque IterDeque = new ArrayDeque();
        for (Object obj : baseList) {
            if (obj instanceof Iterator) {
                IterDeque.add(obj);
            } else {
                targetList.add((String) obj);
            }
        }
        Object currentObject;
        while (!IterDeque.isEmpty()) {
            currentObject = IterDeque.poll();

            Iterator<Object> iter = (Iterator) currentObject;
            while (iter.hasNext()) {
                Object obj = iter.next();
                if (obj instanceof String ) {
                    targetList.add((String) obj);
                } else {
                    IterDeque.add(iter.next());
                }
            }

        }

        return targetList;
    }


}
