package com.example.ltdd_th71_nhom5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.adapter.SearchResultAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView rvSearchResult;
    private TextView txtSearch;
    List<Book> listBook = new ArrayList<>();
    SearchResultAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarResult);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mapView();

        //Textview
        Intent intent = getIntent();
        String strSearch = intent.getExtras().getString("Search text");
        txtSearch.setText(strSearch);

        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        createList();
        //Recycler View
        adapter = new SearchResultAdapter(this, listBook);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvSearchResult.setLayoutManager(layoutManager);
        rvSearchResult.setAdapter(adapter);
    }

    private void mapView() {
        txtSearch = findViewById(R.id.txtSearch);
        rvSearchResult = findViewById(R.id.rvSearchResult);
    }

    public void createList(){
        listBook = MainActivity.allBookList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_result_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return  true;
            case R.id.action_search_shopping:
                Intent cartIntent = new Intent(SearchResultActivity.this, ShoppingCartActivity.class);
                startActivity(cartIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
