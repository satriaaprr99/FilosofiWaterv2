package narsis.xiirpl2.appfilosofi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import narsis.xiirpl2.appfilosofi.api.ApiRequest;
import narsis.xiirpl2.appfilosofi.api.FilosofiServer;
import narsis.xiirpl2.appfilosofi.model.ResponsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText nama,jenis,harga,stok;
    RadioButton gelas,botol,galon;
    Button btntambah;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama  = (EditText) findViewById(R.id.namabarang);
        jenis  = (EditText) findViewById(R.id.jenisbarang);
        gelas = (RadioButton) findViewById(R.id.jenisgelas);
        botol = (RadioButton) findViewById(R.id.jenisbotol);
        galon = (RadioButton) findViewById(R.id.jenisgalon);
        harga = (EditText) findViewById(R.id.hargabarang);
        stok  = (EditText) findViewById(R.id.stokbarang);
        btntambah = (Button) findViewById(R.id.btn_tambahbarang);
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
                            Toast.makeText(MainActivity.this, "Data Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Error! Data Gagal Disimpan!", Toast.LENGTH_SHORT).show();
                        }
                        if(response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Response Success!", Toast.LENGTH_SHORT).show();
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