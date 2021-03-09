package narsis.xiirpl2.appfilosofi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilosofiServer {
<<<<<<< HEAD
    private static final String base_url = "http://192.168.7.120:8000/api/";
=======
    private static final String base_url = "http://192.168.1.4:8000/api/";
>>>>>>> 842d7468668fa5124a1f8ae510118b59274669c7
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
