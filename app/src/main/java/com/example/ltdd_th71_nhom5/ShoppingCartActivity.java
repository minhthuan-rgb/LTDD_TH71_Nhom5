package com.example.ltdd_th71_nhom5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.adapter.ShoppingCartAdapter;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<ShoppingCart> list;
    static LinearLayout linearNullList;
    static LinearLayout linearListNotNull;
    RecyclerView rvShoppingCart;
    ListView lvOffer;
    Button btnContinue1, btnContinue2, btnPay;
    static TextView totalValue;
    static ShoppingCartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setTitle("Giỏ hàng");
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mapView();
        checkData();
        calTotalValue();
        catchEventButton();
    }

    private void catchEventButton() {
        btnContinue1.setOnClickListener(ShoppingCartActivity.this);
        btnContinue2.setOnClickListener(ShoppingCartActivity.this);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ShoppingCartActivity.this, LoginActivity.class);
                loginIntent.putExtra("Activity", "Cart");
                startActivity(loginIntent);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    public static void calTotalValue() {
        double total = 0;
        for (int i = 0; i < MainActivity.listShoppingCart.size(); i++){
            total += MainActivity.listShoppingCart.get(i).getNewValue();
        }
        totalValue.setText(String.format("%.3f VNĐ",total));
    }

    public static void checkData(){
        if(MainActivity.listShoppingCart.size() > 0){
            adapter.notifyDataSetChanged();
            linearNullList.setVisibility(View.INVISIBLE);
            linearListNotNull.setVisibility(View.VISIBLE);
        }
        else
        {
            adapter.notifyDataSetChanged();
            linearNullList.setVisibility(View.VISIBLE);
            linearListNotNull.setVisibility(View.INVISIBLE);
        }
    }

    private void mapView() {
        linearNullList = findViewById(R.id.linearNullList);
        linearListNotNull = findViewById(R.id.linearListNotNull);
        btnPay = findViewById(R.id.btnPay);
        btnContinue1 = findViewById(R.id.btnContinue1);
        btnContinue2 = findViewById(R.id.btnContinue2);
        totalValue = findViewById(R.id.totalValue);

        rvShoppingCart = findViewById(R.id.rvShoppingCart);
        adapter = new ShoppingCartAdapter(ShoppingCartActivity.this, MainActivity.listShoppingCart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvShoppingCart.setLayoutManager(layoutManager);
        rvShoppingCart.setAdapter(adapter);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnContinue1 || v.getId() == R.id.btnContinue2){
            Intent homeIntent = new Intent(ShoppingCartActivity.this, MainActivity.class);
            startActivity(homeIntent);
        }
    }
}
