package RU.POLITEXNIK;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public byte[] encryptArr(byte[] baseArr, byte[] key){
       //TODO Закончить метод


        return null;
    }

    //метод для копирования части массива с места на место
    public static void moveRange(byte[] arr, int fromPos, int toPos,int length) {
        byte temp;
        for (int i = 0; i < length - 1; i++) {
            temp = arr[fromPos + i];
            arr[fromPos + i] = arr[toPos + i];
            arr[toPos + i] = temp;
        }
    }

    //подготовка массива к шифрованию, докопирование до нужного размера
    public static byte[] adaptArrToEncryption(byte[] baseArr, byte[] keyArr) {
        int baseLength = baseArr.length;
        int keyLength = keyArr.length;
        int newLength = 0;
        if (baseLength % keyLength == 0) {
             newLength = baseLength + (baseLength / keyLength);
        } else {
            newLength = (baseLength / keyLength + 1) * keyLength;
        }
        byte[] resultArr = Arrays.copyOf(baseArr, newLength);
        resultArr[baseLength] = 1;
        return resultArr;
    }



}
