package RU.POLITEXNIK;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Path path1 = Paths.get("c:\\Users\\User\\Documents\\Code\\TestFiles\\1.txt");
        Path path2 = Paths.get("c:\\Users\\User\\Documents\\Code\\TestFiles\\2.txt");
        Path path3 = Paths.get("c:\\Users\\User\\Documents\\Code\\TestFiles\\3.txt");
        Path path4 = Paths.get("c:\\Users\\User\\Documents\\Code\\TestFiles\\4.txt");
        Set<Path> pathSet = new HashSet<Path>();
        pathSet.add(path1);
        pathSet.add(path2);
        pathSet.add(path3);
        pathSet.add(path4);
        InvertedIndex index = new InvertedIndex(pathSet);
        for (Path path : index.searchPhrase("подобной") ) {
            System.out.println(path.toString());
        }
    }
}
