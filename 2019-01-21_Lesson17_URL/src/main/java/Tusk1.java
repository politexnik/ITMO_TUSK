import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Tusk1 {

    //1. Создать экземпляр URL указывающий на любимый сайт и получить URLConnection.
    // Выведете на консоль ContentLength, ContentType, список всех HeaderFields.
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://ya.ru");
        URLConnection connection = url.openConnection();

        System.out.println("ContentLength " + connection.getContentLength());
        System.out.println("ContentType " + connection.getContentType());
        for (Map.Entry<String, List<String>> entry: connection.getHeaderFields().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }





}
