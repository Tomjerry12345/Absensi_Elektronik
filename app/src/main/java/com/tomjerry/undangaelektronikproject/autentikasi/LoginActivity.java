package com.tomjerry.undangaelektronikproject.autentikasi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import com.tomjerry.undangaelektronikproject.MainActivity;
import com.tomjerry.undangaelektronikproject.R;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnSignup, btnLogin, btnReset;
    private com.google.android.material.textfield.TextInputLayout txtEmail , txtPassword;
    private androidx.appcompat.widget.AppCompatTextView txtErrorEmail,txtErrorPassword;
    private ProgressDialog progressDialog;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }

        // set the view now
        setContentView(R.layout.activity_login);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtErrorEmail = findViewById(R.id.txt_error_email);
        txtErrorPassword = findViewById(R.id.txt_error_password);

        progressDialog = new ProgressDialog(this);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
//                    inputEmail.setError(getString(R.string.error_email));
                    txtErrorEmail.setText(getString(R.string.error_email));
                    txtEmail.setBoxStrokeColor(10);
//                    txtPassword.setPasswordVisibilityToggleEnabled(true);
                    return;
                }
                else if (!inputEmail.getText().toString().trim().matches(emailPattern)) {
                    txtErrorEmail.setText("Format email salah!!!");
                }
                else if (TextUtils.isEmpty(password)) {
                    txtErrorPassword.setText("Password tidak boleh kosong");
                    txtErrorEmail.setText("");
                    return;
                }
                else if (password.length() < 6)
                {
                    txtErrorPassword.setText("Password harus lebih dari 6 karakter ");
                }
                else {
                    txtErrorEmail.setText("");
                    txtErrorPassword.setText("");
                    progressDialog.setTitle("Proses");
                    progressDialog.setMessage("Silahkan tunggu...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    //authenticate user
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener
                                    if (!task.isSuccessful()) {

                                            progressDialog.hide();
                                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
//
                                    } else {
                                        progressDialog.hide();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }
}