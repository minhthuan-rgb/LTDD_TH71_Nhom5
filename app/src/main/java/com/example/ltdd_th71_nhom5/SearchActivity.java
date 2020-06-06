package com.example.ltdd_th71_nhom5;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ltdd_th71_nhom5.adapter.HotkeyAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class SearchActivity extends AppCompatActivity  implements HotkeyAdapter.ItemClickListener {
    public MaterialSearchView mySearch;
    private  String[] mSuggestions;
    private  String[] mHotKey;
    private RecyclerView rvHotKey;
    private HotkeyAdapter adapter;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSuggestions = new String[]{"Ha Noi", "Ha nam", "Da nang", "Dong nai", "Phú Tho", "Quang ngai", "Thanh hoa", "Hue"};
        mHotKey = new String[]{"a","b", "c", "d", "e", "f", "Túp lều bác Tôm","Quẳng gánh lo đi và vui sống","3 ngày ở nước Tý hon","Đất Rừng Phương Nam",
        "Truyện Kiều","Đại Việt sử ký toàn thư","Đời mưa gió","Kỹ nghệ lấy Tây","Đại Nam quốc âm tự vị","Lục Vân Tiên"};

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        //MaterialSearchView
        mySearch = findViewById(R.id.mySearch);
        mySearch.setSuggestions(mSuggestions);
        mySearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query != "") {
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("Search text", query);
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               return false;
            }
        });

        mySearch.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                Intent mainIntent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        //HotkeyAdapter and recycler view hot key
        adapter = new HotkeyAdapter(this,mHotKey);
        adapter.setClickListener(this);
        rvHotKey = findViewById(R.id.rvHotKey);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.HORIZONTAL);
        rvHotKey.setLayoutManager(layoutManager);
        rvHotKey.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        mySearch.setMenuItem(item);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (mySearch.isSearchOpen()) {
            mySearch.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (!mySearch.isSearchOpen())
            mySearch.showSearch();
        mySearch.setQuery(adapter.getItem(position),true);
    }
}
