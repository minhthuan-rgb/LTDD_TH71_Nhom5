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
    List<Book> listBook;

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

        //Textview
        Intent intent = getIntent();
        String strSearch = intent.getExtras().getString("Search text");
        txtSearch = findViewById(R.id.txtSearch);
        txtSearch.setText(strSearch);

        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        createList();
        //Recycler View
        SearchResultAdapter adapter = new SearchResultAdapter(this, listBook);
        rvSearchResult = findViewById(R.id.rvSearchResult);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvSearchResult.setLayoutManager(layoutManager);
        rvSearchResult.setAdapter(adapter);
    }

    public void createList(){
        listBook = new ArrayList<>();
        listBook.add(new Book(1,"a", 1, 45.000, 50, "abcdefghijklmnopq", R.drawable.vf_1));
        listBook.add(new Book(2,"b", 2, 45.000, 40, "abcdefghijklmnopq", R.drawable.vf_2));
        listBook.add(new Book(3,"c", 3, 45.000, 30, "abcdefghijklmnopq", R.drawable.vf_3));
        listBook.add(new Book(4,"d", 4, 45.000, 20, "abcdefghijklmnopq", R.drawable.vf_4));
        listBook.add(new Book(5,"e", 5, 45.000, 10, "abcdefghijklmnopq", R.drawable.vf_5));
        listBook.add(new Book(6,"f", 6, 45.000, 0, "abcdefghijklmnopq", R.drawable.vf_6));
        listBook.add(new Book(8,"g", 7, 45.000, 0, "abcdefghijklmnopq", R.drawable.vf_7));
        listBook.add(new Book(9,"h", 8, 45.000, 0, "abcdefghijklmnopq", R.drawable.vf_8));
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
