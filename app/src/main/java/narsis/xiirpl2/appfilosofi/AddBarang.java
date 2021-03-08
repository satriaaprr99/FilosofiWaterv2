package narsis.xiirpl2.appfilosofi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.view.MenuItem;

import narsis.xiirpl2.appfilosofi.api.ApiRequest;
import narsis.xiirpl2.appfilosofi.api.FilosofiServer;
import narsis.xiirpl2.appfilosofi.model.ResponsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddBarang extends AppCompatActivity {
    EditText nama,jenis,harga,stok;
    Button btntambah;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_barang);

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homepage:
                        startActivity(new Intent(getApplicationContext(), HomeFragment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.shop:
                        startActivity(new Intent(getApplicationContext(), ShopFragment.class));
                        overridePendingTransition(0,0);
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

        nama   = findViewById(R.id.namabarang);
        jenis  = findViewById(R.id.jenisbarang);
        harga  = findViewById(R.id.hargabarang);
        stok   = findViewById(R.id.stokbarang);
        btntambah = findViewById(R.id.btn_tambahbarang);
        pd = new ProgressDialog(this);

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send Data ...");
                pd.setCancelable(false);
                pd.show();
                String snama  = nama.getText().toString();
                String sharga = harga.getText().toString();
                String sstok  = stok.getText().toString();
                String sjenis = jenis.getText().toString();

                ApiRequest api = FilosofiServer.getClient().create(ApiRequest.class);
                Call<ResponsModel> sendbarang = api.sendData(snama,sjenis,sharga,sstok);
                sendbarang.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String message = response.body().getMessage();
                        if(message.equals("success")){
                            Toast.makeText(AddBarang.this, "Data Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddBarang.this, "Error! Data Gagal Disimpan!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Gagal Mengirim Request!");
                    }
                });
            }
        });

    }
}
