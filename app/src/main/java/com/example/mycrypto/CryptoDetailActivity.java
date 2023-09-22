package com.example.mycrypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CryptoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_detail);

        // Find views in the layout
        ImageView backButton = findViewById(R.id.backButton);
        TextView cryptoSymbolTextView = findViewById(R.id.cryptoSymbol);
        TextView cryptoNameTextView = findViewById(R.id.cryptoName);
        TextView cryptoPriceTextView = findViewById(R.id.cryptoPrice);
        TextView cryptoChangeTextView = findViewById(R.id.cryptoChange);
        ImageView cryptoIconImageView = findViewById(R.id.cryptoIcon);

        // Retrieve cryptocurrency data from the intent
        Intent intent = getIntent();
        String cryptoSymbol = intent.getStringExtra("cryptoSymbol");
        String cryptoName = intent.getStringExtra("cryptoName");
        String cryptoPrice = intent.getStringExtra("cryptoPrice");
        String cryptoChange = intent.getStringExtra("cryptoChange");
        int cryptoIconResId = intent.getIntExtra("cryptoIconResId",R.drawable.default_icon); // Replace default_icon with your default icon resource

        // Set cryptocurrency data to the views
        cryptoSymbolTextView.setText(cryptoSymbol);
        cryptoNameTextView.setText(cryptoName);
        cryptoPriceTextView.setText(cryptoPrice);
        cryptoChangeTextView.setText(cryptoChange);
        cryptoIconImageView.setImageResource(cryptoIconResId); // Set the cryptocurrency icon


        // Set an OnClickListener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to return to the previous activity (dashboard)
                finish();
            }
        });
    }
}
