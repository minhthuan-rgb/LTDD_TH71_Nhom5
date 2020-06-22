package com.example.ltdd_th71_nhom5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity{
    RecyclerView rvSearchResult;
    TextView txtSearch, txtCountResult, txtNotification;
    List<Book> listBook = new ArrayList<>();
    SearchResultAdapter adapter = null;
    String strSearch;
    CoordinatorLayout listNull;
    FrameLayout listNotNull;
    Button btnContinue3;
    Spinner spinnerSort;
    LinearLayout linearSort;

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
        Collections.sort(listBook, (p1, p2)-> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvSearchResult.setLayoutManager(layoutManager);
        rvSearchResult.setAdapter(adapter);

        checkData();

        catchViewEventListener();

        CatchEventSpinner();
    }

    private void catchViewEventListener() {
        txtSearch.setOnClickListener(v -> onBackPressed());

        btnContinue3.setOnClickListener(v -> {
            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(homeIntent);
        });

       spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               switch (position){
                   case 0:
                       Collections.sort(listBook, (p1, p2)-> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
                       break;
                   case 1:
                       Collections.sort(listBook, (p2, p1)-> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
                       break;
                   case 2:
                       Collections.sort(listBook, (o1, o2) -> Double.compare(o1.getValue(), o2.getValue()));
                       break;
                   case 3:
                       Collections.sort(listBook, (o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));
                       break;
               }
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }

    private void CatchEventSpinner() {
        String[] sortType = new String[]{"Tên tăng dần","Tên giảm dần","Giá tăng dần","Giá giảm dần"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sortType);
        spinnerSort.setAdapter(arrayAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void checkData() {
        if(listBook.size() > 0) {
            txtCountResult.setText(String.format("Bao gồm (%d) kết quả", listBook.size()));
            listNotNull.setVisibility(View.VISIBLE);
            listNull.setVisibility(View.INVISIBLE);
            if (listBook.size() > 1)
                linearSort.setVisibility(View.VISIBLE);
            else linearSort.setVisibility(View.INVISIBLE);
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
        spinnerSort = findViewById(R.id.spinnerSort);
        linearSort = findViewById(R.id.linearSort);
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
            case R.id.action_search_home:
                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
