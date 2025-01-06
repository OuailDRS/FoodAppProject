package com.example.foodproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.foodproject.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        binding.BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.txtInputEditLogin.getText().toString().trim();
                String password = binding.txtInputEditPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    binding.txtInputEditLogin.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    binding.txtInputEditPassword.setError("Password is required");
                    return;
                }

                // Authenticate the user
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Login successful, navigate to MainActivity
                                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, MainActivity.class));
                                finish();
                            } else {

                                Toast.makeText(Login.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
