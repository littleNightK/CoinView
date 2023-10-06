package com.example.mycrypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;


import com.example.mycrypto.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ActivityMainBinding binding;
    SharedPreferences sharedPref;

    public boolean isLoggedIn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new DashboardFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Dashboard) {
                replaceFragment(new DashboardFragment());


            } else if (item.getItemId() == R.id.Account) {

                if (currentUser != null) {
                    replaceFragment(new AfterLoginFragment());
                } else {
                    replaceFragment(new LoginFragment());
                }

            }
            else if (item.getItemId() == R.id.Setting) {
                replaceFragment(new SettingFragment());
            }
            return true;
        });


    }



    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();}


}
