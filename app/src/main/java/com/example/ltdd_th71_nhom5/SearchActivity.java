package com.example.ltdd_th71_nhom5;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.ltdd_th71_nhom5.adapter.HotkeyAdapter;
import com.example.ltdd_th71_nhom5.adapter.RecentQueryAdapter;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.Suggestion;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity  implements HotkeyAdapter.ItemClickListener,
        RecentQueryAdapter.RecentItemClickListener{
    private  String[] mHotKey;
    private RecyclerView rvHotKey, rvRecentQuery;
    private HotkeyAdapter hotkeyAdapter;
    private RecentQueryAdapter queryAdapter;
    private FrameLayout frameRecentQuery;
    private FloatingSearchView mSearchView;
    private List<Suggestion> mSuggestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mHotKey = new String[]{"a","b", "c", "d", "e", "f", "Túp lều bác Tôm","Quẳng gánh lo đi và vui sống","3 ngày ở nước Tý hon","Đất Rừng Phương Nam",
        "Truyện Kiều","Đại Việt sử ký toàn thư","Đời mưa gió","Kỹ nghệ lấy Tây","Đại Nam quốc âm tự vị","Lục Vân Tiên"};

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Tìm kiếm");
        actionBar.setDisplayHomeAsUpEnabled(true);

        initSuggestionList();

        mapView();

        searchViewEventListener();

        checkData();
    }

    private void searchViewEventListener() {
        mSearchView.setOnQueryChangeListener((oldQuery, newQuery) -> {
            if (!oldQuery.equals("") && newQuery.equals("")) {
                mSearchView.clearSuggestions();
            } else {
                mSearchView.showProgress();
                mSearchView.swapSuggestions(getSuggestion(newQuery));
                mSearchView.setSuggestionRightIconColor(R.drawable.suggestion_item_background);
                mSearchView.hideProgress();
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Suggestion suggestion = (Suggestion) searchSuggestion;
                onSearchAction(suggestion.getBody());
            }

            @Override
            public void onSearchAction(String currentQuery) {
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                addRecentQuery(currentQuery);
                intent.putExtra("Search text", currentQuery);
                startActivity(intent);
            }
        });
    }

    private void initSuggestionList() {
       for (Book book : MainActivity.allBookList){
           mSuggestions.add(new Suggestion(book.getTitle()));
       }
    }

    private List<Suggestion> getSuggestion(String query){
        List<Suggestion> suggestions = new ArrayList<>();
        for(Suggestion suggestion:mSuggestions){
            if(suggestion.getBody().toLowerCase().contains(query.toLowerCase()))
                    suggestions.add(suggestion);
        }
        return suggestions;
    }


    private void mapView() {
        frameRecentQuery = (FrameLayout)findViewById(R.id.frameRecentQuery);
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

        //mSearchView
        mSearchView = findViewById(R.id.mSearchView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onRecentItemClick(View view, int position) {

    }
}
