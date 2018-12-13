package RU.POLITEXNIK;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        // проверка работы
        byte[] arr = {0,1,2,3,4,5,6,7};
        byte[] key = {2, 1, 0};
        System.out.println("Исходный массив\n" + Arrays.toString(arr));
        arr = encryptArr(arr, key);
        System.out.println("Закодированный массив\n" + Arrays.toString(arr));
        arr = decryptArr(arr, key);
        System.out.println("Раскодированный массив\n" + Arrays.toString(arr));
    }

    //кодирование массива
    public static byte[] encryptArr(byte[] baseArr, byte[] key) throws Exception{
        if (baseArr.length / key.length < 2) {
            throw new Exception("The key array length is too low");
        }
        byte[] adaptedArr = adaptArrToEncryption(baseArr, key);
        int partLength = key.length;        // длина куска разбиения

        for (int i = 0; i < adaptedArr.length / key.length; i++) {
            encryptPartOfArray(adaptedArr, i * partLength, key);
        }

        return adaptedArr;
    }

    public static byte[] decryptArr(byte[] encryptedArr, byte[] key) throws Exception{
        int partLength = key.length;
        if (partLength < 2) {
            throw new Exception("The key array length is too low");
        }

        for (int i = 0; i < encryptedArr.length / key.length; i++) {
            decryptPartOfArray(encryptedArr, i * partLength, key);
        }

        for (int i = encryptedArr.length - 1; i >= 0 ; i--) {
            if (encryptedArr[i] == 1) {
                return Arrays.copyOf(encryptedArr, i);
            }
        }
        return null;
    }

    //метод для копирования части массива
//    public static void copyRange(byte[] arrFrom, byte[] arrTo, int fromIndex, int toIndex, int length){
//        for (int i = 0; i < length; i++) {
//            arrTo[i + toIndex] = arrFrom[i + fromIndex];
//        }
//    }

    //подготовка массива к шифрованию, докопирование до нужного размера
    public static byte[] adaptArrToEncryption(byte[] baseArr, byte[] keyArr) {
        int baseLength = baseArr.length;
        int keyLength = keyArr.length;
        int newLength = 0;
        if (baseLength % keyLength == 0) {
            newLength = baseLength + keyLength;
        } else {
            newLength = (baseLength / keyLength + 1) * keyLength;
        }
        byte[] resultArr = Arrays.copyOf(baseArr, newLength);
        resultArr[baseLength] = 1;
        return resultArr;
    }

    public static class KeyNode {
        byte key;
        int value;
        public KeyNode(byte key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public static void encryptPartOfArray(byte[] arr, int startIndex, byte[] key){
        KeyNode[] keys = new KeyNode[key.length];
        for (int i = 0; i < key.length; i++) {
            keys[i] = new KeyNode(key[i], arr[startIndex + i]);
        }
        Arrays.sort(keys, (o1, o2) -> o1.key - o2.key);

        for (int i = 0; i < key.length; i++) {
            arr[startIndex + i] = (byte)keys[i].value;
        }
    }

    public static void decryptPartOfArray(byte[] arr, int startIndex, byte[] key){
        KeyNode[] keys = new KeyNode[key.length];
        for (byte i = 0; i < key.length; i++) {
            keys[i] = new KeyNode(key[i], i);
        }
        Arrays.sort(keys, (o1, o2) -> o1.key - o2.key);
        byte[] tempArr = new byte[key.length];
        for (int i = 0; i < key.length; i++) {
            tempArr[i] = arr[keys[i].value + startIndex];
        }
        for (int i = 0; i < key.length; i++) {
            arr[startIndex + i] = tempArr[i];
        }
    }
}
