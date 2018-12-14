package RU.POLITEXNIK;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class InvertedIndex {
    private Map<String, Set<Path>> map;
    private Map<String, Integer> wordsFrequenceQueue;

    public InvertedIndex(Set<Path> stringSet) throws IOException {
        map = new HashMap<>();
        wordsFrequenceQueue = new HashMap<>();

        //пробег по полученному списку текстов
        for (Path path: stringSet) {
            String[] strArr = (String[])Files.readAllLines(path).toArray();
            for (String strLine: strArr) {
                String[] words = strLine.split(" ");//разбили строки на слова, пробег по каждому слову в тексте
                for (String word: words) {
                    wordsFrequenceQueue.put(word, wordsFrequenceQueue.getOrDefault(word, 0) + 1);
                    Set<Path> currentPathList = map.getOrDefault(word, new HashSet<>());
                    //если вернул новый список - добавляем его в мапу
                    if (currentPathList.size() == 0)
                        map.put(word, currentPathList);
                        currentPathList.add(path);  //при добавлении в Set, эквивалентный объект не добавится
                }
            }
        }
        ArrayList<Map.Entry<String, Integer>> freqenceList = new ArrayList(wordsFrequenceQueue.entrySet());
                Collections.sort(freqenceList, (o1, o2) -> {
                    return Integer.compare(o1.getValue(), o2.getValue());
                });
        Collections.reverse(freqenceList);
        map.get("123").retainAll();
    }


}
