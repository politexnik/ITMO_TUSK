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
            //
            for (String strLine: Files.readAllLines(path)) {
                String[] words = strLine.split("»?[,!?\":;\\.]*\\s«?");//разбили строки на слова, пробег по каждому слову в тексте
                for (String word: words) {
                    word = word.toLowerCase();
                    if (word.length() == 0)
                        continue;
                    wordsFrequenceQueue.put(word, wordsFrequenceQueue.getOrDefault(word, 0) + 1);
                    Set<Path> currentPathList = map.getOrDefault(word, new HashSet<>());
                    //если вернул новый список - добавляем его в мапу
                    if (currentPathList.size() == 0)
                        map.put(word, currentPathList);
                        currentPathList.add(path);  //при добавлении в Set, эквивалентный объект не добавится
                }
            }
        }
        ArrayList<Map.Entry<String, Integer>> frequenceList = new ArrayList(wordsFrequenceQueue.entrySet());
                Collections.sort(frequenceList, (o1, o2) -> {
                    return Integer.compare(o1.getValue(), o2.getValue());
                });
        Collections.reverse(frequenceList);
    }

    public Set<Path> searchPhrase(String phrase){

        String[] words = phrase.split("\\s");
        ArrayList<Set<Path>> pathSetList = new ArrayList();
        for (String word: words) {
            Set<Path> pathSet = map.get(word);
            if (pathSet != null) {
                pathSetList.add(pathSet);
            } else {
                return new HashSet<>();
            }
        }
        Set<Path> resutSet = new HashSet<>(pathSetList.get(0));
        //Set<Path> resutSet = Objects. pathSetList.get(0);
        for (int i = 1; i < pathSetList.size() - 1; i++) {
            resutSet.retainAll(pathSetList.get(i));
        }
        return resutSet;
    }


}
