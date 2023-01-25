package com.yln.laundrylab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivityjavanya extends AppCompatActivity {
    private EditText editEmail, editPassword;
    private Button btnLogin;
    private TextView btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanState){
        super.onCreate(savedInstanState);
        setContentView(R.layout.login);

        editEmail = findViewById(R.id.editTextEmailMasuk);
        editPassword = findViewById(R.id.editTextKonfirmPassword);
        btnLogin = findViewById(R.id.buttonRegisterLogin);
        btnRegister = findViewById(R.id.buttonRegisterLogin);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LoginActivityjavanya.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu");
        progressDialog.setCancelable(false);


        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivityjavanya.class));
        });

        btnLogin.setOnClickListener(v -> {
if(editEmail.getText().length()>0 && editPassword.getText().length()>0){
    login(editEmail.getText().toString(), editPassword.getText().toString());
} else {
    Toast.makeText(getApplicationContext(), "Silahkan isi email dan password", Toast.LENGTH_SHORT).show();
}
        });
    }
    private void login(String email, String password){
mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful() && task.getResult() != null){
            if(task.getResult().getUser()!=null){
reload();
            }else {
                Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();

            }
        }else {
            Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();

        }
    }
});
    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(), NavbarActivity.class));
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){

        }
    }

}
