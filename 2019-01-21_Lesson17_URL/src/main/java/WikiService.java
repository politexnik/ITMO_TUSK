
import MyJSON.JClass;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface WikiService {
    //https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=%22Java%22
    @GET("/w/api.php?action=query&list=search&utf8=&format=json")
    //@GET("users/{user}/repos")
    Call<JClass> responseQuery(@Query("srsearch") String queryString);
}
