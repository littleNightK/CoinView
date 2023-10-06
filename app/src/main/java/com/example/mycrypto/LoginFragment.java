package com.example.mycrypto;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {
    FirebaseAuth mAuth;



    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is signed in, update the UI
            updateUI(currentUser); // Call your updateUI method defined in the fragment
        }
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // User is signed in, perform UI updates as needed
            // For example, you can start a new fragment or activity
            Fragment fragment = new AfterLoginFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            // User is not signed in, you can update the UI accordingly
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);
        TextView userEdt = root.findViewById(R.id.gmail);
        TextView passEdt = root.findViewById(R.id.pass);

        Button loginbtn = root.findViewById(R.id.siginbut);
        Button signupbtn=root.findViewById(R.id.signupbut);

        String TAG ="LoginFragment";
        mAuth = FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userEdt.getText().toString();
                String pass = passEdt.getText().toString();

                if (user.isEmpty()) {
                    userEdt.setError("Please enter your email");
                    userEdt.requestFocus();
                    return;
                }

                if (pass.isEmpty()) {
                    passEdt.setError("Please enter your password");
                    passEdt.requestFocus();
                    return;
                }

                if (getActivity() != null) {
                    mAuth.signInWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (getActivity() != null) { // Check again in case getActivity() became null during the task
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "signInWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            updateUI(user);

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                                            Toast.makeText(getActivity(), "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            updateUI(null);
                                        }
                                    }
                                }
                            });
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
                mAuth = FirebaseAuth.getInstance();


            }

            


        });
        return root;



    }}

