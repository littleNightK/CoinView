package com.example.mycrypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


import com.example.mycrypto.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static boolean isLogin = false;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new DashboardFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Dashboard) {
                replaceFragment(new DashboardFragment());
            } else if (item.getItemId() == R.id.Account) {
                if (isLogin) {
                    replaceFragment(new AfterLoginFragment());
                } else {
                    replaceFragment(new LoginFragment());
                }
            } else if (item.getItemId() == R.id.Setting) {
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
