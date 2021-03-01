package narsis.xiirpl2.appfilosofi.api;

import narsis.xiirpl2.appfilosofi.model.ResponsModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("barang")
    Call<ResponsModel> sendData(@Field("nama_barang") String nama,
                                @Field("jenis_barang") String jenis,
                                @Field("harga") String harga,
                                @Field("stok") String stok);
}
