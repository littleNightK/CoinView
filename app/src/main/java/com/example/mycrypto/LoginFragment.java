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



public class LoginFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);
        TextView userEdt = root.findViewById(R.id.gmail);
        TextView passEdt = root.findViewById(R.id.pass);

        Button loginbtn = root.findViewById(R.id.siginbut);
        Button signupbtn=root.findViewById(R.id.signupbut);
        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String user = userEdt.getText().toString();
                String pass = passEdt.getText().toString();
                if(user.equals("admin") && pass.equals("admin")){
                    Toast.makeText(getActivity(),"Login Successful",Toast.LENGTH_SHORT).show();
                    MainActivity.isLogin = true;
                    Fragment fragment = new AfterLoginFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }else{
                    Toast.makeText(getActivity(),"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }

        });

        signupbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment fragment = new SignupFragment();
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

