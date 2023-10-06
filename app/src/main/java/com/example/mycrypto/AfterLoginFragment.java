package com.example.mycrypto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

//get user information(user name, card number, card name and card expire) from firebase and display it
public class AfterLoginFragment extends Fragment {
    FirebaseAuth mAuth;
    private String cardnum, cardname,cardexp,userName;
    private TextView txtCardNum, txtCardName,txtCardExp,txtUsername;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.after_login_fragment, container, false);


        ImageButton logoutbtn = root.findViewById(R.id.logoutbut);
        ImageView receive=root.findViewById(R.id.receivebtn);
        ImageView deposit=root.findViewById(R.id.sendbtn);


        txtCardNum = root.findViewById(R.id.etCardNumber);
        txtCardName = root.findViewById(R.id.etName);
        txtCardExp = root.findViewById(R.id.etExpiry);
        txtUsername = root.findViewById(R.id.Name);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String uid = currentUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Register Users");
        referenceProfile.child(uid).addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readWriteUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readWriteUserDetails != null) {
                    //toast username
                    cardnum = readWriteUserDetails.cardNumber;
                    cardname = readWriteUserDetails.cardName;
                    cardexp = readWriteUserDetails.cardExp;
                    userName = readWriteUserDetails.username;
                    txtCardName.setText(cardname);
                    txtCardExp.setText(cardexp);
                    txtCardNum.setText(cardnum);
                    txtUsername.setText("Welcome, "+userName);

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error, e.g., log it or display an error message
                Log.e("DatabaseError", "Error: " + error.getMessage());
            }
        });





        logoutbtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             mAuth = FirebaseAuth.getInstance();
                                             mAuth.signOut();

                                             Fragment fragment = new LoginFragment();
                                             FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                             fragmentTransaction.replace(R.id.frame_layout, fragment);
                                             fragmentTransaction.addToBackStack(null);
                                             fragmentTransaction.commit();
                                         }
                                     }

        );
        //open receive activity

        receive.setOnClickListener(new View.OnClickListener() {
                                       Intent intent = new Intent(getActivity(), WithdrawActivity.class);
                                       @Override
                                       public void onClick(View v) {
                                           startActivity(intent);
                                       }

                                   }

        );

        //open deposit activity

        deposit.setOnClickListener(new View.OnClickListener() {
                                       Intent intent = new Intent(getActivity(), DepositActivity.class);
                                       @Override
                                       public void onClick(View v) {
                                           startActivity(intent);
                                       }

                                   }

        );
        return root;
    }


}


        

