import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorForIteratorTree implements Iterator {
    private ArrayList baseList;
    private ArrayList<String> stringList;
    ArrayDeque<Iterator> iterDeque;
    Iterator currentIter;

    public IteratorForIteratorTree(ArrayList baseList) {

        this.baseList = baseList;
        iterDeque = new ArrayDeque();
        stringList = new ArrayList<>();
        for (Object obj : baseList) {
            if (obj instanceof Iterator) {
                iterDeque.add((Iterator) obj);
            } else {
                stringList.add((String) obj);
            }
        }
        while (stringList.isEmpty() && !iterDeque.isEmpty()) {  //для случая если в arrayList только итераторы лежат
            goForLevel();
        }
    }

    @Override
    public Object next() {
        while (true) {
            if (stringList.size() > 1) {  // кол-во элементов в листе > 1 чтобы успешно написать hasNext
                return stringList.remove(0);
            } else if (!iterDeque.isEmpty()) {   //очередь не пуста
                goForLevel();
            } else if (stringList.size() == 1) {
                return stringList.remove(0);
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return stringList.isEmpty() ? false : true;
    }

    private void goForLevel() {
        currentIter = (Iterator) iterDeque.poll();
        Object obj;
        while (currentIter.hasNext()) {
            obj = currentIter.next();
            if (obj instanceof Iterator) {
                iterDeque.add((Iterator) obj);
            } else {
                stringList.add((String) obj);
            }
        }
    }
}