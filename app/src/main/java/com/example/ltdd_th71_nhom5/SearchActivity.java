package com.example.ltdd_th71_nhom5;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ltdd_th71_nhom5.adapter.HotkeyAdapter;
import com.example.ltdd_th71_nhom5.adapter.RecentQueryAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class SearchActivity extends AppCompatActivity  implements HotkeyAdapter.ItemClickListener, RecentQueryAdapter.RecentItemClickListener{
    public MaterialSearchView mySearch;
    private  String[] mSuggestions;
    private  String[] mHotKey;
    private RecyclerView rvHotKey, rvRecentQuery;
    private HotkeyAdapter hotkeyAdapter;
    private RecentQueryAdapter queryAdapter;
    private FrameLayout frameRecentQuery;

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

        mapView();

        //MaterialSearchView
        mySearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query != "") {
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("Search text", query);
                    startActivity(intent);
                    addRecentQuery(query);
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
                onBackPressed();
            }
        });

        checkData();
    }

    private void mapView() {
        frameRecentQuery = (FrameLayout)findViewById(R.id.frameRecentQuery);
        mySearch = findViewById(R.id.mySearch);
        mySearch.setSuggestions(mSuggestions);
        //HotkeyAdapter and recyclerView
        hotkeyAdapter = new HotkeyAdapter(this,mHotKey);
        hotkeyAdapter.setClickListener(this);
        rvHotKey = findViewById(R.id.rvHotKey);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.HORIZONTAL);
        rvHotKey.setLayoutManager(layoutManager);
        rvHotKey.setAdapter(hotkeyAdapter);

        //RecentQueryAdapter and recyclerView
        queryAdapter = new RecentQueryAdapter(this, MainActivity.listRecentQuery);
        queryAdapter.setClickListener(this);
        rvRecentQuery = findViewById(R.id.rvRecentQuery);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRecentQuery.setLayoutManager(linearLayoutManager);
        rvRecentQuery.setAdapter(queryAdapter);
    }

    private void checkData() {
        if(MainActivity.listRecentQuery.size() > 0)
            frameRecentQuery.setVisibility(View.VISIBLE);
        else
            frameRecentQuery.setVisibility(View.INVISIBLE);
    }

    private void addRecentQuery(String query) {
        if(MainActivity.listRecentQuery.size() > 0){
            boolean exists = false;
            for(int i = 0; i < MainActivity.listRecentQuery.size(); i++){
                if (MainActivity.listRecentQuery.get(i) == query.toLowerCase()){
                    exists = true;
                    break;
                }
            }
            if (!exists)
                MainActivity.listRecentQuery.add(query.toLowerCase());
        }else{
            MainActivity.listRecentQuery.add(query.toLowerCase());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        mySearch.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (!mySearch.isSearchOpen())
            mySearch.showSearch();
        mySearch.setQuery(hotkeyAdapter.getItem(position),true);
    }

    @Override
    public void onRecentItemClick(View view, int position) {
        if (!mySearch.isSearchOpen())
            mySearch.showSearch();
        mySearch.setQuery(queryAdapter.getItem(position),false);
    }
}
