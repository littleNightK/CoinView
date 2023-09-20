package com.example.mycrypto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private List<CryptoItem> cryptoItemList = new ArrayList<>();
    private CryptoAdapter cryptoAdapter;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView cryptoRecyclerView = rootView.findViewById(R.id.cryptoRecyclerView);


        // Initialize your cryptoAdapter and set it to the RecyclerView
        cryptoAdapter = new CryptoAdapter(getCryptoItemList());
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

        return rootView;
    }

    private List<CryptoItem> getCryptoItemList() {
        // Initialize your cryptocurrency data here (replace this with your actual data source)
        List<CryptoItem> cryptoItemList = new ArrayList<>();
        cryptoItemList.add(new CryptoItem("BTC", "Bitcoin", "$45,000", "+$200 (2%)"));
        cryptoItemList.add(new CryptoItem("ETH", "Ethereum", "$3,000", "-$50 (-1%)"));
        cryptoItemList.add(new CryptoItem("ADA", "Cardano", "$2.50", "+$0.20 (2.5%)"));
        cryptoItemList.add(new CryptoItem("TON", "Toncoin", "$2.42", "+$0.003872 (0.16%)"));
        cryptoItemList.add(new CryptoItem("SOL", "Solana", "$19.80", "-$0.06534 (-0.33%)"));
        cryptoItemList.add(new CryptoItem("TRX", "Tron", "$0.084", "+$0.0001512 (0.18%)"));
        cryptoItemList.add(new CryptoItem("BNB", "BNB", "$216.60", "-$0.5 (-0.23%)"));

        cryptoItemList.add(new CryptoItem("USDC", "USD Coin", "$1.00", "-$0,01 (-0.01%)"));
        cryptoItemList.add(new CryptoItem("LINK", "Chaninlink", "$6.95", "+$1.12 (16,06%)"));
        cryptoItemList.add(new CryptoItem("MKR", "Maker", "$1,305.58", "+$212.8 (16.3%)"));
        cryptoItemList.add(new CryptoItem("QNT", "Quant", "$91.83", "-$0.77 (-0.84%)"));
        cryptoItemList.add(new CryptoItem("EGLD", "MultiversX", "$26.97", "+$3.37 (12.65%)"));
        cryptoItemList.add(new CryptoItem("RUNE", "THORCHAIN", "$1.92", "+$0.565 (+29.44%)"));
        cryptoItemList.add(new CryptoItem("GNO", "Gnoisis", "$102.47", "+$4.17 (+4.07%)"));
        cryptoItemList.add(new CryptoItem("XCH", "Chia", "$27.78", "+$0,377 (+1.36%)"));






        // Add more cryptocurrency items as needed
        return cryptoItemList;
    }

    private void performSearch(String query) {
        // Implement your search functionality here
        // You can filter the data in cryptoAdapter based on the query
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