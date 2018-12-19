package RU.POLITEXNIK;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class VectorIndex {
    private Map<String, Set<Path>> map;
    private Map<String, Integer> wordsFrequenceQueue;

    //создание векторного пространства, определение количества координат
    public VectorIndex(Set<Path> pathSet) throws IOException {
        HashSet<String> wordsSet = new HashSet<>();


        //пробег по полученному списку текстов
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
        String[] words = (String[])wordsSet.toArray();
    }
}
