package com.example.mycrypto;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class SignupFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);
        TextView signupgmail = root.findViewById(R.id.signupgmail);
        TextView signuppass = root.findViewById(R.id.signuppass);
        TextView phone=root.findViewById(R.id.phone);
        TextView retypepass=root.findViewById(R.id.retype_pass);

        Button subsignupbtn=root.findViewById(R.id.subsignupbut);

        Button back_btn=root.findViewById(R.id.back_button);


        subsignupbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String signgmail = signupgmail.getText().toString();
                String signpass = signuppass.getText().toString();
                String phonenum=phone.getText().toString();
                String retype=retypepass.getText().toString();
                if(signgmail.isEmpty() || signpass.isEmpty() || phonenum.isEmpty() || retype.isEmpty()){
                    Toast.makeText(getActivity(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else if (!signpass.equals(retype)){
                    Toast.makeText(getActivity(),"The retype password is not valid",Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(getActivity(),"Sign up success",Toast.LENGTH_SHORT).show();}
                    }



        });
        
        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment fragment = new LoginFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return root;
    }
    }


