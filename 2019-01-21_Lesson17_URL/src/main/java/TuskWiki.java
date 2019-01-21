import MyJSON.JClass;
import MyJSON.Search;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;


public class TuskWiki {
    public static void main(String[] args) throws IOException {
        GsonBuilder gBuilder = new GsonBuilder();
        Gson gson = gBuilder.setPrettyPrinting().create();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.println("Введите поисковый запрос либо завершите командой \\end");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ru.wikipedia.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        while (!(input = br.readLine()).equals("\\end")) {
            input = URLEncoder.encode(input, "UTF-8");
            WikiService service = retrofit.create(WikiService.class);
            Call<JClass> responseQuery = service.responseQuery(input);
            Response<JClass> response = responseQuery.execute();
//            Response<WikiMain> response = service.responseQuery(input).execute();
            if (response.isSuccessful()) {
                for (Search re: response.body().getQuery().getSearch()) {
                    System.out.println(re);
                }
            }
        }
    }


}
