package com.example.ltdd_th71_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ltdd_th71_nhom5.adapter.OrderAdapter;
import com.example.ltdd_th71_nhom5.adapter.SearchResultAdapter;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.Order;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class BookManagementActivity extends AppCompatActivity {
    Button btnLogin1;
    LinearLayout layoutNotLogin;
    FrameLayout layoutIsLogin;
    TextView txtCountBook;
    RecyclerView rvBook;
    List<Book> listBook = new ArrayList<>();
    SearchResultAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_management);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sản phẩm đã mua");
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        mapView();

        createListBook();

        checkLogin();

        catchViewEventListener();
    }

    private void createListBook() {
        for (Order order:MainActivity.listOrder){
            for (ShoppingCart cart:order.getListCart()){
                boolean exists = false;
                for(Book book:listBook){
                    if(book.getBookID().equals(cart.getBook().getBookID())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists)
                    listBook.add(cart.getBook());
            }
        }
    }

    private void catchViewEventListener() {
        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("Activity", "Book");
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkLogin() {
        if (MainActivity.isLogin){
            layoutNotLogin.setVisibility(View.INVISIBLE);
            layoutIsLogin.setVisibility(View.VISIBLE);
            txtCountBook.setText(txtCountBook.getText() + " (" + listBook.size() + ")");
        }
        else{
            layoutNotLogin.setVisibility(View.VISIBLE);
            layoutIsLogin.setVisibility(View.INVISIBLE);
        }
    }

    private void mapView() {
        btnLogin1 = findViewById(R.id.btnLogin1);
        layoutNotLogin = findViewById(R.id.layoutNotLogin);
        layoutIsLogin = findViewById(R.id.layoutIsLogin);
        txtCountBook = findViewById(R.id.txtCountBook);
        rvBook = findViewById(R.id.rvBook);

        adapter = new SearchResultAdapter(this, listBook);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rvBook.setLayoutManager(gridLayoutManager);
        rvBook.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}