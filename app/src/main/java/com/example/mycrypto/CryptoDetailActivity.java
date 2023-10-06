package com.example.mycrypto;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CryptoDetailActivity extends AppCompatActivity {

    private WebView cryptoChartWebView;
    private ImageView moneyIconImageView;
    private TextView cryptoNameTextView; // Fixed variable name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_detail);

        // Find views in the layout
        cryptoChartWebView = findViewById(R.id.cryptoChartWebView);
        moneyIconImageView = findViewById(R.id.cryptoIcon);
        cryptoNameTextView = findViewById(R.id.cryptoName);

        // Retrieve the cryptocurrency data from the intent extras
        Intent intent = getIntent();
        String cryptoSymbol = intent.getStringExtra("cryptoSymbol");
        String cryptoPrice = intent.getStringExtra("cryptoPrice");
        String cryptoPriceChange = intent.getStringExtra("cryptoPriceChange");
        String cryptoName = intent.getStringExtra("cryptoName"); // Fixed variable name

        int cryptoIconResId = intent.getIntExtra("cryptoIconResId", R.drawable.default_icon); // Replace default_icon with your default icon resource

        // Set the cryptocurrency data to the views
        cryptoNameTextView.setText(cryptoName); // Fixed variable name

        // Set the cryptocurrency icon
        moneyIconImageView.setImageResource(cryptoIconResId);

        // Set up click listeners for interval buttons
        Button button24h = findViewById(R.id.button24h);
        Button button7d = findViewById(R.id.button7d);
        Button button14d = findViewById(R.id.button14d);
        Button button30d = findViewById(R.id.button30d);
        Button button90d = findViewById(R.id.button90d);
        Button button1y = findViewById(R.id.button1y);

        // Handle the "Return" button click
        ImageView buttonReturn = findViewById(R.id.buttonReturn);
        buttonReturn.setOnClickListener(view -> finish());

        // Load the default TradingView chart when the activity is created
        loadChartWithInterval("1d", cryptoSymbol);

        // Set up click listeners for interval buttons
        button24h.setOnClickListener(view -> loadChartWithInterval("oneDay", cryptoSymbol));
        button7d.setOnClickListener(view -> loadChartWithInterval("oneWeek", cryptoSymbol));
        button14d.setOnClickListener(view -> loadChartWithInterval("twoWeek", cryptoSymbol));
        button30d.setOnClickListener(view -> loadChartWithInterval("oneMonth", cryptoSymbol));
        button90d.setOnClickListener(view -> loadChartWithInterval("threeMonth", cryptoSymbol));
        button1y.setOnClickListener(view -> loadChartWithInterval("1y", cryptoSymbol));
    }

    private void loadChartWithInterval(String interval, String cryptoSymbol) {
        // Construct the TradingView chart URL based on the interval and cryptocurrency symbol
        String chartUrl = "https://s.tradingview.com/widgetembed/?symbol=" + cryptoSymbol + "USD&interval=" + interval + "&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=";

        // Enable JavaScript in the WebView
        WebSettings webSettings = cryptoChartWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Enable caching by setting cache mode
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        // Load the TradingView chart URL
        cryptoChartWebView.loadUrl(chartUrl);

        // Set a WebViewClient to handle page loading events
        cryptoChartWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Page finished loading
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                // Show a more detailed error message
                showErrorMessage("Error: " + error.getDescription());
            }
        });
    }

    // Function to show an error message as a Toast
    private void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
