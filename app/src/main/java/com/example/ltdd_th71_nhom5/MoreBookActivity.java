package com.example.ltdd_th71_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ltdd_th71_nhom5.adapter.HomeBookAdapter;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MoreBookActivity extends AppCompatActivity {
    TextView txtTitleCategory;
    ImageView imgUp, imgDown;
    RecyclerView rvMoreBook;
    HomeBookAdapter adapter = null;
    List<Book> lisBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_book);

        // action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get key and category name
        Intent intent = getIntent();
        String key = intent.getExtras().getString("Key");
        String name = intent.getExtras().getString("CategoryName");

        mapView();
        txtTitleCategory.setText(name);

        lisBook = new ArrayList<>();
        adapter = new HomeBookAdapter(this, lisBook);
        HomeFragment.loadData(key, lisBook, adapter, false);
        setUpRecyclerView(rvMoreBook, adapter);

        cacthViewEventClickListener();
    }

    private void cacthViewEventClickListener() {
        imgDown.setOnClickListener(v -> {
            imgDown.setVisibility(View.INVISIBLE);
            imgUp.setVisibility(View.VISIBLE);
            Collections.sort(lisBook, (p2,p1)-> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
            adapter.notifyDataSetChanged();
        });

        imgUp.setOnClickListener(v -> {
            imgDown.setVisibility(View.VISIBLE);
            imgUp.setVisibility(View.INVISIBLE);
            Collections.sort(lisBook, (p1,p2)-> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void mapView() {
        txtTitleCategory = findViewById(R.id.txtTitleCategory);
        imgUp = findViewById(R.id.imgUp);
        imgDown = findViewById(R.id.imgDown);
        rvMoreBook = findViewById(R.id.rvMoreBook);
        imgDown.setVisibility(View.VISIBLE);
        imgUp.setVisibility(View.INVISIBLE);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_search_sub:
                Intent searchIntent = new Intent(MoreBookActivity.this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.action_shopping_cart_sub:
                Intent cartIntent  = new Intent(MoreBookActivity.this, ShoppingCartActivity.class);
                startActivity(cartIntent);
                return  true;
            case R.id.action_home:
            case R.id.action_category:
            case R.id.action_personal:
            case R.id.action_notification:
                Intent homeIntent = new Intent(MoreBookActivity.this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public void setUpRecyclerView(RecyclerView recyclerView, HomeBookAdapter adapter){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MoreBookActivity.this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}