package narsis.xiirpl2.appfilosofi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilosofiServer {
    private static final String base_url = "http://172.16.100.82:8000/api/";
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
