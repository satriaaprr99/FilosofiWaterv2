package narsis.xiirpl2.appfilosofi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import narsis.xiirpl2.appfilosofi.adapter.AdapterDataBarang;
import narsis.xiirpl2.appfilosofi.api.ApiRequest;
import narsis.xiirpl2.appfilosofi.api.FilosofiServer;
import narsis.xiirpl2.appfilosofi.model.DataBarangModel;
import narsis.xiirpl2.appfilosofi.model.ResponsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopFragment extends AppCompatActivity {
    private RecyclerView mrecycle;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayout;
    private List<DataBarangModel> mitems = new ArrayList<>();
    ProgressDialog pd;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shop);

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom);
        bottomNavigationView.setSelectedItemId(R.id.shop);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homepage:
                        startActivity(new Intent(getApplicationContext(), HomeFragment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.shop:
                        return true;
                    case R.id.notify:
                        startActivity(new Intent(getApplicationContext(), NotifyFragment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileFragment.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        pd = new ProgressDialog(this);

        mrecycle = findViewById(R.id.recyclebarang);
        mlayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mrecycle.setLayoutManager(mlayout);

        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        ApiRequest api = FilosofiServer.getClient().create(ApiRequest.class);
        Call<ResponsModel> getdata = api.getData();
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE :" + response.body().getMessage());
                mitems = response.body().getData();

                madapter = new AdapterDataBarang(ShopFragment.this, mitems);
                mrecycle.setAdapter(madapter);
                madapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : Respon Gagal");
                Log.d("RETRO", t.getMessage());
            }
        });
    }
}