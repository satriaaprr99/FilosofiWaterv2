package narsis.xiirpl2.appfilosofi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PesanActivity extends AppCompatActivity {
    TextView nama,jenis,stok,harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesan_barang);

        Intent intent = getIntent();

        final String namae = intent.getExtras ( ).getString ( "nama" );
        final String jenise = intent.getExtras ( ).getString ( "jenis" );
        final String stoke = intent.getExtras ( ).getString ( "stok" );
        final String hargae = intent.getExtras ( ).getString ( "harga" );


        nama = findViewById ( R.id.namabr );
        jenis = findViewById ( R.id.jenisbr );
        stok = findViewById ( R.id.stokbr );
        harga = findViewById ( R.id.hargabr );

        nama.setText(namae);
        jenis.setText(jenise);
        stok.setText(stoke);
        harga.setText(hargae);
    }
}
