package com.example.foodproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodproject.databinding.ActivityProfilBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profil extends AppCompatActivity {

    ActivityProfilBinding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Get current user
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            binding.tvEmail.setText(email); // Display the email
        } else {
            Toast.makeText(this, "No user is logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Profil.this, Login.class));
            finish();
        }

        // Logout button
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Toast.makeText(Profil.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profil.this, Login.class));
                finish();
            }
        });
    }
}
