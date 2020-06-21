package com.example.ltdd_th71_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.ltdd_th71_nhom5.adapter.HotkeyAdapter;
import com.example.ltdd_th71_nhom5.adapter.OrderAdapter;
import com.example.ltdd_th71_nhom5.model.Order;

public class OrderManagementActivity extends AppCompatActivity implements OrderAdapter.ItemClickListener{
    Button btnLogin;
    LinearLayout linearNotLogin;
    FrameLayout frameIsLogin;
    TextView txtCountOrder;
    RecyclerView rvOrder;
    OrderAdapter orderAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý đơn hàng");
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        mapView();

        checkLogin();

        catchViewEventListener();
    }

    private void catchViewEventListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("Activity", "Order");
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkLogin() {
        if (MainActivity.isLogin){
            linearNotLogin.setVisibility(View.INVISIBLE);
            frameIsLogin.setVisibility(View.VISIBLE);
            txtCountOrder.setText(txtCountOrder.getText() + " (" + MainActivity.listOrder.size() + ")");
        }
        else{
            linearNotLogin.setVisibility(View.VISIBLE);
            frameIsLogin.setVisibility(View.INVISIBLE);
        }
    }


    private void mapView() {
        btnLogin = findViewById(R.id.btnLogin);
        linearNotLogin = findViewById(R.id.linearNotLogin);
        txtCountOrder = findViewById(R.id.txtCountOrder);
        rvOrder = findViewById(R.id.rvOrder);
        frameIsLogin = findViewById(R.id.frameIsLogin);

        //
        orderAdapter = new OrderAdapter(getApplicationContext(),MainActivity.listOrder);
        orderAdapter.setClickListener(this);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvOrder.setLayoutManager(verticalLayoutManager);
        rvOrder.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
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
    public void onItemClick(View view, int position) {
        Intent detailIntent = new Intent(getApplicationContext(), OrderDetailActivity.class);
        detailIntent.putExtra("OrderID", position);
        startActivity(detailIntent);
    }
}