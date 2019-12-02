package com.tomjerry.undangaelektronikproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tomjerry.undangaelektronikproject.autentikasi.LoginActivity;
import com.tomjerry.undangaelektronikproject.db.TamuHelper;
import com.tomjerry.undangaelektronikproject.model.Tamu;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button scanBtn , daftarTamuBtn;
    private IntentIntegrator intentIntegrator;

    private Tamu tamu;

    private TamuHelper tamuHelper;

    private int nomor = 0;

    private Button btnSignout;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.btn_scan);
        daftarTamuBtn = findViewById(R.id.btn_tamu);
        btnSignout = findViewById(R.id.btn_signout);

        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed i
                } else {
                    // User is signed out
                    startActivity(new Intent(MainActivity.this , LoginActivity.class));
                }
            }
        };

        tamuHelper = TamuHelper.getInstance(this);

        tamuHelper.open();

        intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setCameraId(0);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentIntegrator.initiateScan();


            }
        });

        daftarTamuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TamuActivity.class);
                startActivity(intent);

            }
        });

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
            } else {
                // jika qrcode berisi data
                try {
                    // converting the data json

                    JSONObject object = new JSONObject(result.getContents());

                    tamu = new Tamu();
                    nomor++;
                    tamu.setNama(object.getString("nama"));
                    tamu.setAlamat(object.getString("alamat"));
                    tamu.setNo(nomor);

                    long result1 = tamuHelper.insertTamu(tamu);
                    if (result1 > 0) {
                        tamu.setId((int)result1);
                        Toast.makeText(this, "succes", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(this, "Data : " + nomor, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    // jika format encoded tidak sesuai maka hasil
                    // ditampilkan ke toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
