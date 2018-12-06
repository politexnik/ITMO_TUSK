package RU.POLITEXNIK;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] arr = {3,7,8,5,2,1,9,4,4,16,3,4/*,4,56,8,90,22,11,5,0,34*/};
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    static void quickSort(int[] arr, int startPos, int endPos) {

        //Условия выхода из рекурсии
        if (startPos == endPos) {
            return;
        } else if ((endPos - startPos) == 1) {
            if (arr[startPos] > arr[endPos]) {
                int temp = arr[startPos];
                arr[startPos] = arr[endPos];
                arr[endPos] = temp;
            }
            return;
        } else if ((endPos - startPos) == 2) {
            for (int i = startPos; i < endPos; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i+1] = temp;
                }
            }
            return;
        }

        //Работа с рекурсией
        int leftBorderPos = startPos;
        int rightBorderPos = endPos - 1;

        int baseElem = arr[endPos];     //опорный элемент
        int baseElemPos = endPos;       //его позиция
        int baseElemCount = 1;      //кол-во элементов в среднем массиве
        System.out.println(Arrays.toString(arr));
        while (leftBorderPos < baseElemPos) {
            //int[] arrbackup = Arrays.copyOf(arr, arr.length);
//            if (rightBorderPos == leftBorderPos) {
//                if (arr[leftBorderPos] > baseElem) {
//                    int temp = arr[leftBorderPos];
//                    arr[leftBorderPos] = baseElem;
//                    arr[baseElemPos] = temp;
//                }
//                break;
//            }

            while (arr[leftBorderPos] < baseElem) {
                leftBorderPos++;
            }

            int temp = arr[leftBorderPos];
            arr[leftBorderPos] = arr[rightBorderPos];
            arr[rightBorderPos] = baseElem;
            arr[baseElemPos + baseElemCount - 1] = temp;
            if (temp == baseElem) baseElemCount++;
            baseElemPos--;
            rightBorderPos--;

            //if (rightBorderPos == leftBorderPos) break;

            //System.out.printf("CountBase %d. BaseelemPos %d\n", baseElemCount, baseElemPos);
        }

        quickSort(arr,startPos, baseElemPos - 1);
        quickSort(arr, baseElemPos + baseElemCount, endPos);

    }

}
