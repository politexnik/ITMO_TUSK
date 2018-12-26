import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class IteratorArray2Dimention<T> implements Iterator<T> {
    private int rowNo = 0, columnNo = 0;
    private T[][] arr;
    private int rowSize;

    public IteratorArray2Dimention(T[][] arr){
        this.arr = arr;
        rowSize = arr.length;
    }

    @Override
    public boolean hasNext() {
        skipEmptyRows();
        if (rowNo < rowSize && columnNo < arr[rowSize - 1].length) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        skipEmptyRows();

        if (rowNo < rowSize) {
            if (columnNo < arr[rowNo].length) {
                return arr[rowNo][columnNo++];
            } else {
                columnNo = 0;
                rowNo++;
                skipEmptyRows();
                return arr[rowNo][columnNo++];
            }
        }
        return null;
    }

    //пропуск пустых строк
    private void skipEmptyRows() {
        while (rowNo < rowSize && arr[rowNo].length == 0) {  //проверка на нулевую длину подмассива
            rowNo++;
            columnNo = 0;
        }
    }



    public static void main(String[] a){
        IteratorArray2Dimention iteratorArray2Dimention = new IteratorArray2Dimention(null);
        for(Object o : iteratorArray2Dimention){

        }
    }

}
