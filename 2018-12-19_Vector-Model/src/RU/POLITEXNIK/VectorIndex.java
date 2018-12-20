package RU.POLITEXNIK;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class VectorIndex {
    private Map<Path, int[]> fileVectorBoolMap;
    private Map<Path, float[]> fileVectorTFMap;
    private Map<Path, float[]> fileVectorTFIDFMap;
    //создание векторного пространства, определение количества координат
    public VectorIndex(Set<Path> pathSet) throws IOException {
        HashSet<String> wordsSet = new HashSet<>(); //в сет соберем слова, потом перегоним в мапу для адресации по массиву координат
        HashMap<String, Integer> wordMap = new HashMap<>();

        //пробег по полученному списку текстов, заполнение wordSet
        for (Path path: pathSet) {
            //
            for (String strLine : Files.readAllLines(path)) {
                String[] words = strLine.split("»?[,!?\":;\\.]*\\s«?");//разбили строки на слова, пробег по каждому слову в тексте
                for (String word : words) {
                    word = word.toLowerCase();
                    if (word.length() == 0)
                        continue;
                    wordsSet.add(word);
                }
            }
        }
        //преобразование в wordMap (Потом будем искать номер слова)
        int k = 0;
        for (String str: wordsSet) {
            wordMap.put(str, k++);
        }
        //Создание векторов файлов
        fileVectorBoolMap = new HashMap<>();
        fileVectorTFMap = new HashMap<>();
        fileVectorTFIDFMap = new HashMap<>();
        for (Path path: pathSet) {
            int countWords = 0;
            int[] arrBool = new int[wordMap.size()];    //массив вектора файла (номер ячейки массива соответствует значению по ключу из wordMap
            float[] arrTF = new float[wordMap.size()];
            float[] arrTFIDF;
            for (String strLine : Files.readAllLines(path)) {
                String[] words = strLine.split("»?[,!?\":;\\.]*\\s«?");//разбили строки на слова, пробег по каждому слову в тексте
                for (String word : words) {
                    word = word.toLowerCase();
                    if (word.length() == 0)
                        continue;
                        countWords++;
                        arrBool[wordMap.get(word)] = 1; //булевский вес
                        arrTF[wordMap.get(word)]++;   // частота терма, в конце массив нормализуется по сумме элементов в массиве
                }
            }

            fileVectorBoolMap.put(path, arrBool);
            //нормализуем массив частоты термов
            for (int i = 0; i < arrTF.length; i++) {
                arrTF[i] = 1.0f * arrTF[i] / countWords;
            }
            fileVectorTFMap.put(path,arrTF);

            //копируем из массива TF значения в TF-IDF
            arrTFIDF = Arrays.copyOf(arrTF, arrTF.length);
            fileVectorTFIDFMap.put(path, arrTFIDF);
        }
        //создание массива векторов TF-IDF.
        //бежим по файлам для каждого слова, считаем количество файлов, в котором оно встречается
        int[] countDocsWithWord = new int[wordMap.size()];  //вспомогательный массив, сюда сосчитаем количество файлов, где встречается слово
        for (int i = 0; i < wordMap.size(); i++) {
            int count = 0;
            for (Path path: fileVectorBoolMap.keySet()) {
                if (fileVectorBoolMap.get(path)[i] != 0) {
                    count++;
                }
            }
            countDocsWithWord[i] = count;
        }
        // окончательно оформляем массивы TF-IDF
        for (Path path: fileVectorBoolMap.keySet()) {
            float[] arr = fileVectorTFIDFMap.get(path);
            for (int i = 0; i < arr[i]; i++) {
                arr[i] = 1.0f * arr[i] / countDocsWithWord[i];
            }
        }
    }
}
