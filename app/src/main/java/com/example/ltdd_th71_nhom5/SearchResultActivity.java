package com.example.ltdd_th71_nhom5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ltdd_th71_nhom5.adapter.Book;
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

        //Textview
        Intent intent = getIntent();
        String strSearch = intent.getExtras().getString("Search text");
        txtSearch = findViewById(R.id.txtSearch);
        txtSearch.setText(strSearch);

        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(SearchResultActivity.this, SearchActivity.class);
                startActivity(searchIntent);
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

    public void createList() {
        listBook = new ArrayList<>();
        listBook.add(new Book("a", "Trinh thám", 45.000, "Fahasa", "50%", "abcdefghijklmnopq", R.drawable.vf_1));
        listBook.add(new Book("b", "Trinh thám", 45.000, "Fahasa", "40%", "abcdefghijklmnopq", R.drawable.vf_2));
        listBook.add(new Book("c", "Trinh thám", 45.000, "Fahasa", "30%", "abcdefghijklmnopq", R.drawable.vf_3));
        listBook.add(new Book("d", "Trinh thám", 45.000, "Fahasa", "20%", "abcdefghijklmnopq", R.drawable.vf_4));
        listBook.add(new Book("e", "Trinh thám", 45.000, "Fahasa", "10%", "abcdefghijklmnopq", R.drawable.vf_5));
        listBook.add(new Book("f", "Trinh thám", 45.000, "Fahasa", "", "abcdefghijklmnopq", R.drawable.vf_6));
        listBook.add(new Book("g", "Trinh thám", 45.000, "Fahasa", "", "abcdefghijklmnopq", R.drawable.vf_7));
        listBook.add(new Book("h", "Trinh thám", 45.000, "Fahasa", "", "abcdefghijklmnopq", R.drawable.vf_8));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_result_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
