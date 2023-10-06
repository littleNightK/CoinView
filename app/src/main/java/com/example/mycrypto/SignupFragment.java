package com.example.mycrypto;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.nfc.Tag;
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






public class SignupFragment extends Fragment {
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
            Fragment fragment = new LoginFragment();
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
        super.onCreate(savedInstanceState);
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);
        TextView userEdt = root.findViewById(R.id.signupgmail);
        TextView passEdt = root.findViewById(R.id.signuppass);
        TextView passEdt2 = root.findViewById(R.id.retype_pass);
        Button submit_signupbtn=root.findViewById(R.id.subsignupbut);
        Button back_btn = root.findViewById(R.id.back_button);
        mAuth = FirebaseAuth.getInstance();
        submit_signupbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = userEdt.getText().toString();
                String password = passEdt.getText().toString();
                String password2 = passEdt2.getText().toString();

                if(email.isEmpty()){
                    userEdt.setError("Please enter email id");
                    userEdt.requestFocus();
                    return;
                }
                else if(password.isEmpty()){
                    passEdt.setError("Please enter your password");
                    passEdt.requestFocus();
                    return;
                }
                else if(password2.isEmpty()){
                    passEdt2.setError("Please retype your password");
                    passEdt2.requestFocus();
                    return;
                }

                else if(password.length()<6){
                    passEdt.setError("Password must be at least 6 characters");
                    passEdt.requestFocus();
                    return;
                }
                else if(!password.equals(password2)){
                    passEdt2.setError("Passwords do not match");
                    passEdt2.requestFocus();
                    return;
                }

                //validate email format
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    userEdt.setError("Please enter a valid email address");
                    userEdt.requestFocus();
                    return;
                }


                if (getActivity() != null) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "createUserWithEmail:success");
                                        Toast.makeText(getActivity(), "Authentication success.",
                                                Toast.LENGTH_SHORT).show();
                                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                        //open RegisterInforActivity to get user's name and last name
                                        Intent intent = new Intent(getActivity(), RegisterInfoActivity.class);
                                        //prevent user from going back to login page
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        getActivity().finish();



                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getActivity(), "Authentication failed: " + task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();


                                    }

                                }
                            });
                }
            }
        });





        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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



