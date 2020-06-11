package com.example.ltdd_th71_nhom5;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.adapter.ShoppingCartAdapter;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    ArrayList<ShoppingCart> list;
    LinearLayout linearNullList;
    FrameLayout frameListNotNull;
    RecyclerView rvShoppingCart;
    ListView lvOffer;
    Button btnContinue1, btnContinue2, btnPay;
    TextView totalValue;
    ShoppingCartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //map view
        mapView();

        if(MainActivity.listShoppingCart.size() > 0){
            adapter.notifyDataSetChanged();
            linearNullList.setVisibility(View.INVISIBLE);
            frameListNotNull.setVisibility(View.VISIBLE);
        }
        else
        {
            adapter.notifyDataSetChanged();
            linearNullList.setVisibility(View.VISIBLE);
            frameListNotNull.setVisibility(View.INVISIBLE);
        }
    }

    private void mapView() {
        linearNullList = findViewById(R.id.linearNullList);
        frameListNotNull = findViewById(R.id.frameListNotNull);
        btnPay = findViewById(R.id.btnPay);
        btnContinue1 = findViewById(R.id.btnContinue1);
        btnContinue2 = findViewById(R.id.btnContinue2);

        rvShoppingCart = findViewById(R.id.rvShoppingCart);
        adapter = new ShoppingCartAdapter(ShoppingCartActivity.this, MainActivity.listShoppingCart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvShoppingCart.setLayoutManager(layoutManager);
        rvShoppingCart.setAdapter(adapter);
    }
}
