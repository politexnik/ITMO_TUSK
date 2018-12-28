import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class IteratorForIteratorTree2 implements Iterator {
    Iterator currentIter;
    ArrayDeque<Iterator> iterDeque;

    public IteratorForIteratorTree2(Iterator mainIter) {
        currentIter = mainIter;
        iterDeque = new ArrayDeque<>(); //очередь для последовательного прохода по итераторам
    }

    @Override
    public boolean hasNext() {
//        Iterator iterBackup = currentIter;
//        ArrayDeque<Iterator> iterDequeBackup = new ArrayDeque<>(iterDeque);
//        if (next() == null) {
//            return false;
//        }
//        else {
//         iterDeque = iterDequeBackup;
//         currentIter = iterBackup;
//         return true;
//        }
        //TODO


    }

    @Override
    public String next() {
        String returnString;
        while (true) {
            if ((returnString = getNextFromCurrentIter(currentIter)) != null) {
                return returnString;
            }
            if (!iterDeque.isEmpty()) {
                currentIter = iterDeque.poll();
            } else {
                return null;
            }
        }
//        return null;
    }

    private String getNextFromCurrentIter(Iterator iterator){
        while (currentIter.hasNext()) {
            Object object = currentIter.next();
            if (object instanceof String) {
                return (String) object;
            } else {
                iterDeque.add((Iterator) object);
            }
        }
        return null;
    }
}