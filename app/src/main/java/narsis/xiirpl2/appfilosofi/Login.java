package narsis.xiirpl2.appfilosofi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import narsis.xiirpl2.appfilosofi.api.ApiRequest;
import narsis.xiirpl2.appfilosofi.api.FilosofiServer;
import narsis.xiirpl2.appfilosofi.model.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button login_butt;
    TextView emailtext, passwordtext;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailtext = findViewById(R.id.email);
        passwordtext  = findViewById(R.id.pass);
        pd = new ProgressDialog(this);
        login_butt = findViewById(R.id.buttlog);
        login_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Send Data ...");
                pd.setCancelable(false);
                pd.show();
                String email = emailtext.getText().toString().trim();
                String pass  = passwordtext.getText().toString().trim();

                if(email.isEmpty()){
                    pd.hide();
                    emailtext.setError("Email tidak Boleh Kosong!");
                    emailtext.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    pd.hide();
                    emailtext.setError("Email tidak Valid!");
                    emailtext.requestFocus();
                    return;
                }

                if(pass.isEmpty()){
                    pd.hide();
                    passwordtext.setError("Password tidak Boleh Kosong!");
                    passwordtext.requestFocus();
                    return;
                }

                ApiRequest api = FilosofiServer.getClient().create(ApiRequest.class);
                Call<LoginResponse> call = api.userLogin(email, pass);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String status = response.body().getMessage();
                        if(status.equals("Login Success")){
                            Intent intent = new Intent(Login.this, HomeFragment.class);
                            intent.putExtra("nama", response.body().getMessage());
                            intent.putExtra("email", response.body().getMessage());
                            intent.putExtra("nohp", response.body().getStatus());
                            startActivity(intent);

                            Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Gagal Mengirim Request!");
                    }
                });
            }
        });
    }
}