package com.devproject.miguelfagundez.searchviewjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Members
    private ListView listView;
    private ArrayList<String> listData;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize members
        setupViews();
        // Initialize array values
        setupArray();
        // Initialize adapter
        setupAdapter();
        // Initialize the listeners
        setupListeners();
    }

    private void setupViews() {
        // Connecting view id with member
        listView = findViewById(R.id.list_view_data);
    }

    private void setupAdapter() {
        // Connecting adapter with data
        listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, listData);

        // Setting adapter to list_view
        listView.setAdapter(listAdapter);
    }

    private void setupArray() {
        listData = new ArrayList<String>();
        // Add item to the list
        for(int i = 0; i < 50; i++){
            listData.add("Data " + i + " included");
        }
    }

    private void setupListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Displaying the array item which was clicked
                Toast.makeText(getApplicationContext(), listAdapter.getItem(i),Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Creating a basic search icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        // Initialize menu item & search view
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        // Initialize the search view listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                // Changing the array list using adapter (filtering result)
                listAdapter.getFilter().filter(s);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
