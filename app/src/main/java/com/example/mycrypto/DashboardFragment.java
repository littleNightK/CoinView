package com.example.mycrypto;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private List<CryptoItem> cryptoItemList = new ArrayList<>();
    private com.example.mycrypto.CryptoAdapter cryptoAdapter;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView cryptoRecyclerView = rootView.findViewById(R.id.cryptoRecyclerView);


        // Initialize your cryptoAdapter and set it to the RecyclerView
        cryptoAdapter = new com.example.mycrypto.CryptoAdapter(getCryptoItemList());
        cryptoRecyclerView.setAdapter(cryptoAdapter);

        // Find the search button in the layout
        ImageView searchButton = rootView.findViewById(R.id.searchButton);

        // Set an onClickListener for the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                if (searchView != null) {
                    searchView.setVisibility(View.VISIBLE);
                    searchView.setIconified(false);
                }
            }
        });

        // Find the SearchView in the layout
        searchView = rootView.findViewById(R.id.action_search);

        // Set the text color of the SearchView
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.WHITE);  // Set the text color to white

        // Set up a query listener for the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the search query submission
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the list based on user input
                cryptoAdapter.getFilter().filter(newText);
                return true;
            }
        });

        // Find the notification button in the layout
        ImageView notificationButton = rootView.findViewById(R.id.notificationIcon);

        // Set an onClickListener for the notification button
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                openNotificationPopUp();
            }
        });

        return rootView;
    }

    private void openNotificationPopUp(){
        Intent notificationpopup = new Intent( requireContext(), NotificationPopUp.class);
        startActivity(notificationpopup);
    }

    private void performSearch(String query) {
        // Implement your search functionality here
        // You can filter the data in cryptoAdapter based on the query
    }
    private List<CryptoItem> getCryptoItemList() {
        // Initialize your cryptocurrency data here (replace this with your actual data source)
        List<CryptoItem> cryptoItemList = new ArrayList<>();
        cryptoItemList.add(new CryptoItem("BTC", "Bitcoin", "$27,666", "+$244 (+0.89%)",R.drawable.bitcoin,"graphbtc"));
        cryptoItemList.add(new CryptoItem("ETH", "Ethereum", "$1,633", "+$21.6 (+1.34%)",R.drawable.eth,"grapheth"));
        cryptoItemList.add(new CryptoItem("ADA", "Cardano", "$0.26", "+$0.003 (+1.27%)",R.drawable.ada,"graphbtc"));
        cryptoItemList.add(new CryptoItem("TON", "Toncoin", "$2.13", "+$0.0078 (+4.05%)",R.drawable.ton,"graphbtc"));
        cryptoItemList.add(new CryptoItem("SOL", "Solana", "$23.19", "+$0.49 (+2.20%)",R.drawable.solana,"grapheth"));

        cryptoItemList.add(new CryptoItem("TRX", "Tron", "$0.086", "-$0.0016 (-1.92%)",R.drawable.trx,"graphbtc"));
        cryptoItemList.add(new CryptoItem("BNB", "BNB", "$212.0", "+$1.4 (+0.66%)", R.drawable.bnb,"grapheth"));

        cryptoItemList.add(new CryptoItem("USDC", "USD Coin", "$1.00", "-$0,01 (-0.01%)", R.drawable.usd,"grapheth"));
        cryptoItemList.add(new CryptoItem("LINK", "Chaninlink", "$6.95", "+$1.12 (16,06%)", R.drawable.link,"grapheth"));
        cryptoItemList.add(new CryptoItem("MKR", "Maker", "$1,305.58", "+$212.8 (16.3%)",R.drawable.mkr,"grapheth"));
        cryptoItemList.add(new CryptoItem("QNT", "Quant", "$91.83", "-$0.77 (-0.84%)",R.drawable.quant,"grapheth"));
        cryptoItemList.add(new CryptoItem("EGLD", "MultiversX", "$26.97", "+$3.37 (12.65%)",R.drawable.elrond,"graphbtc"));
        cryptoItemList.add(new CryptoItem("RUNE", "THORCHAIN", "$1.92", "+$0.565 (+29.44%)",R.drawable.rune,"graphbtc"));
        cryptoItemList.add(new CryptoItem("GNO", "Gnoisis", "$102.47", "+$4.17 (+4.07%)",R.drawable.gno,"graphbtc"));
        cryptoItemList.add(new CryptoItem("ARK", "Ark", "$27.78", "+$0,377 (+1.36%)",R.drawable.ark,"graphbtc"));




        // Add more cryptocurrency items as needed
        return cryptoItemList;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_dashboard, menu);

        // Get the SearchView from the menu
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        // Set an OnQueryTextListener to handle search query changes
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the search query submission
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle the search query text changes
                cryptoAdapter.getFilter().filter(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
