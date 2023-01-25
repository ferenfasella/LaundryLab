package com.yln.laundrylab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivityjavanya extends AppCompatActivity {
private EditText editNama, editEmail, editPassword, editKonfirmasiPassword;
private Button btnRegister;
private TextView btnLogin;
private FirebaseAuth mAuth;
private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanState) {
        super.onCreate(savedInstanState);
        setContentView(R.layout.register);

        editNama = findViewById(R.id.editTextTextName);
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        editPassword = findViewById(R.id.editTextTextPassword);
        editKonfirmasiPassword = findViewById(R.id.editTextKonfirmPassword);

        btnLogin = findViewById(R.id.buttonRegisterLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RegisterActivityjavanya.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan tunggu");
        progressDialog.setCancelable(false);

        Log.d("LaundryLab", "Open Register");
        btnRegister.setOnClickListener(v -> {
            Log.d("LaundryLab", "Attempting Register");

            if(editNama.getText().length()>0 &&
            editEmail.getText().length()>0 &&
            editPassword.getText().length()>0 &&
                    editKonfirmasiPassword.getText().length()>0 ){
                if(editPassword.getText().toString().equals(editKonfirmasiPassword.getText().toString())){
                    register(editNama.getText().toString(), editEmail.getText().toString(),editPassword.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "Password tidak sama", Toast.LENGTH_SHORT).show();

                }
            }else {
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(v -> {
            finish();
        });

    }
    private void register(String nama, String email, String password){
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult()!=null){
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    if(firebaseUser!=null) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nama)
                                .build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
reload();
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), "Register gagal", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
