package com.example.ltdd_th71_nhom5;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.Suggestion;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity{
    private  String[] mHotKey;
    private FloatingSearchView mSearchView;
    private List<Suggestion> mSuggestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mHotKey = new String[]{};

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
        mSearchView.setSearchFocused(true);
        mSearchView.showProgress();
        List<Suggestion> list = new ArrayList<>();
        for(Suggestion suggestion:mSuggestions){
            if (suggestion.getIsHistory())
                list.add(suggestion);
        }
        mSearchView.swapSuggestions(list);
        mSearchView.hideProgress();
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
                boolean exists = false;
                for(Suggestion s : mSuggestions){
                    if (s.getBody().equals(suggestion.getBody())) {
                        exists = true;
                        s.setIsHistory(true);
                        break;
                    }
                }
                if (!exists) {
                    suggestion.setIsHistory(true);
                    mSuggestions.add(suggestion);
                }
                onSearchAction(suggestion.getBody());
            }

            @Override
            public void onSearchAction(String currentQuery) {
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                boolean exists = false;
                for(Suggestion suggestion : mSuggestions){
                    if (suggestion.getBody().toLowerCase().equals(currentQuery.toLowerCase())) {
                        exists = true;
                        suggestion.setIsHistory(true);
                        break;
                    }
                }
                if (!exists) {
                    Suggestion suggestion = new Suggestion(currentQuery);
                    suggestion.setIsHistory(true);
                    mSuggestions.add(suggestion);
                }
                addRecentQuery(currentQuery);
                intent.putExtra("Search text", currentQuery);
                startActivity(intent);
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                mSearchView.showProgress();
                List<Suggestion> list = new ArrayList<>();
                for(Suggestion suggestion:mSuggestions){
                    if (suggestion.getIsHistory())
                        list.add(suggestion);
                }
                mSearchView.swapSuggestions(list);
                mSearchView.hideProgress();
            }

            @Override
            public void onFocusCleared() {

            }
        });

        mSearchView.setOnBindSuggestionCallback((suggestionView, leftIcon, textView, item, itemPosition) -> {
            Suggestion suggestion = (Suggestion) item;


            if (suggestion.getIsHistory()) {
                leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                        R.drawable.recent, null));

                Util.setIconColor(leftIcon, Color.parseColor("#000000"));
                leftIcon.setAlpha(.36f);
            } else {
                leftIcon.setAlpha(0.0f);
                leftIcon.setImageDrawable(null);
            }

            textView.setTextColor(Color.parseColor("#000000"));
            String text = suggestion.getBody()
                    .replaceFirst(mSearchView.getQuery(),
                            "<font color=\"" + "#DD0A0A" + "\">" + mSearchView.getQuery() + "</font>");
            textView.setText(Html.fromHtml(text));
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
}
