import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MyClass {
    public static void main(String[] args) {

    }

//1. Написать метод, который читает текстовый файл и возвращает его в виде списка строк.
    public static List<String> readTextFileToListStrings(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

//2. Написать метод, который записывает в файл строку, переданную параметром.
    public static void writeStringToFile(File file, String string) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(string);
        }
    }

//3. Используя решение 1 и 2, напишите метод, который склеивает два текстовый файла один.
    public static void stickFiles(File file1, File file2, String resultFileName) throws IOException {
        List<String> list1 = readTextFileToListStrings(file1);
        List<String> list2 = readTextFileToListStrings(file2);

        File file = new File(resultFileName);
        list1.stream().forEach((string) -> {
            try {
                writeStringToFile(file, string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        list2.stream().forEach((string) -> {
            try {
                writeStringToFile(file, string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

//4. Написать метод для копирования файла (побайтно, или массивами байт).
    public static void copyFile(File baseFile, String resultFileName) throws IOException {
        byte[] copyArr = Files.readAllBytes(baseFile.toPath());
        Files.write(new File(resultFileName).toPath(), copyArr);
    }

/*
5. Написать метод, который в каталоге ищет файлы, в имени которых содержится определенная строка, и который возвращает
список имен таких файлов.
 */
    public static List<File> searchFilesinDirectory(File dir, String queryString) {
        if (!dir.isDirectory()) return null;
        List<File> resultList = new ArrayList<>();
        File[] filesArr = dir.listFiles();
        Arrays.stream(filesArr).forEach((file) -> {
            if (file.getName().contains(queryString)) {
                resultList.add(file);
            }
        });
        return resultList;
    }
/*
6. Написать метод, который в каталоге ищет текстовые файлы, в которых содержится определенная строка, и который
возвращает список имен таких файлов.
 */
    public static List<File> searchFilesContainsString(File dir, String queryString){
        if (!dir.isDirectory()) return null;
        List<File> resultList = new ArrayList<>();
        File[] filesArr = dir.listFiles();
        Arrays.stream(filesArr).forEach((file) -> {
            try {
                List<String> listStrings = Files.readAllLines(file.toPath());
                listStrings.stream().forEach((string) -> {
                    if (string.equals(queryString)){
                        resultList.add(file);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return resultList;
    }

    public static List<File> searchFilesByFunction(Path path, Function<Path, Boolean> functionInterface){
        if (!Files.isDirectory(path))
            return null;
        List<File> resultList = new ArrayList<>();
        File[] filesArr = path.toFile().listFiles();
        Arrays.stream(filesArr).forEach((file) -> {
            if (functionInterface.apply(path)) {
                resultList.add(file);
            }
        });
        return resultList;
    }
}
