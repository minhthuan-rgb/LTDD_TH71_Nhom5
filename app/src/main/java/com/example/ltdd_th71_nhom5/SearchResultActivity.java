package com.example.ltdd_th71_nhom5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.adapter.SearchResultAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    RecyclerView rvSearchResult;
    TextView txtSearch, txtCountResult, txtNotification;
    List<Book> listBook = new ArrayList<>();
    SearchResultAdapter adapter = null;
    String strSearch;
    CoordinatorLayout listNull;
    FrameLayout listNotNull;
    Button btnContinue3;

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
        strSearch = intent.getExtras().getString("Search text");
        txtSearch.setText(strSearch);

        adapter = new SearchResultAdapter(this, listBook);
        createList();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvSearchResult.setLayoutManager(layoutManager);
        rvSearchResult.setAdapter(adapter);

        checkData();

        catchViewEventListener();
    }

    private void catchViewEventListener() {
        txtSearch.setOnClickListener(v -> onBackPressed());

        btnContinue3.setOnClickListener(v -> {
            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(homeIntent);
        });
    }

    @SuppressLint("DefaultLocale")
    private void checkData() {
        if(listBook.size() > 0) {
            txtCountResult.setText(String.format("Bao gồm (%d) kết quả", listBook.size()));
            listNotNull.setVisibility(View.VISIBLE);
            listNull.setVisibility(View.INVISIBLE);
        }
        else{
            listNotNull.setVisibility(View.INVISIBLE);
            listNull.setVisibility(View.VISIBLE);
            txtNotification.setText(String.format("Không có kết quả nào cho \"%s\"", strSearch));
        }
    }

    private void mapView() {
        txtSearch = findViewById(R.id.txtSearch);
        rvSearchResult = findViewById(R.id.rvSearchResult);
        listNotNull = findViewById(R.id.listNotNull);
        listNull = findViewById(R.id.listNull);
        txtCountResult = findViewById(R.id.txtCountResult);
        txtNotification = findViewById(R.id.txtNotification);
        btnContinue3 = findViewById(R.id.btnContinue3);
    }

    public void createList(){
        for(Book book: MainActivity.allBookList){
            if (book.getTitle().toLowerCase().contains(strSearch)) {
                listBook.add(book);
                adapter.notifyDataSetChanged();
            }
        }
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
