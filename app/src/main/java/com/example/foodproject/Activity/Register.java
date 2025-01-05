package com.example.foodproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodproject.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();


        binding.BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = binding.txtInputLoginRegister.getText().toString().trim();
        String password = binding.txtInputPasswordRegister.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            binding.txtInputLoginRegister.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            binding.txtInputPasswordRegister.setError("Password is required");
            return;
        }
        if (password.length() < 6) {
            binding.txtInputPasswordRegister.setError("Password must be at least 6 characters");
            return;
        }


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Register.this, Login.class));
                        finish();
                    } else {

                        Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
