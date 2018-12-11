package RU.POLITEXNIK;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
	// проверка работы
        byte[] arr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        byte[] key = {3, 2, 5, 2, 4 };
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

        int partLength = adaptedArr.length / key.length;        // длина куска разбиения
        KeyNode[] keys = new KeyNode[key.length];       //направляющие ключа для кодировки
        for (int i = 0; i < key.length; i++) {
            keys[i] = new KeyNode(key[i], i * partLength);
        }

        Arrays.sort(keys, (o1, o2) -> o1.key - o2.key);

        byte[] result = new byte[adaptedArr.length];
        int count = 0;
        for (KeyNode entry: keys) {
            copyRange(adaptedArr, result, entry.value, count * partLength, partLength);
            count++;
        }
        return result;
    }

    public static byte[] decryptArr(byte[] encryptedArr, byte[] key) throws Exception{
        int partLength = encryptedArr.length / key.length;
        if (partLength < 2) {
            throw new Exception("The key array length is too low");
        }
        byte[] adaptedArr = new byte[encryptedArr.length];
        KeyNode[] keys = new KeyNode[key.length];

        for (int i = 0; i < key.length; i++) {
            keys[i] = new KeyNode(key[i], i * partLength);
        }

        Arrays.sort(keys, (o1, o2) -> o1.key - o2.key);

        for (int i = 0; i < key.length; i++) {
         copyRange(encryptedArr, adaptedArr, i * partLength, keys[i].value, partLength);
        }
        //System.out.println(Arrays.toString(adaptedArr));

        for (int i = adaptedArr.length - 1; i >= 0 ; i--) {
            if (adaptedArr[i] == 1) {
                return Arrays.copyOf(adaptedArr, i);
            }
        }

        return null;
    }

    //метод для копирования части массива
    public static void copyRange(byte[] arrFrom, byte[] arrTo, int fromIndex, int toIndex, int length){
        for (int i = 0; i < length; i++) {
            arrTo[i + toIndex] = arrFrom[i + fromIndex];
        }
    }

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
}
