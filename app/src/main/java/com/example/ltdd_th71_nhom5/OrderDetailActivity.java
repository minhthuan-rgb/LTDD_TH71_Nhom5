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
import android.widget.TextView;

import com.example.ltdd_th71_nhom5.adapter.OrderAdapter;
import com.example.ltdd_th71_nhom5.adapter.OrderDetailAdapter;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    List<ShoppingCart> listCart;
    RecyclerView rvOrderDetail;
    OrderDetailAdapter orderDetailAdapter;
    TextView txtName, txtPhone, txtAddress, txtTotalDetail;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        int ID = intent.getExtras().getInt("OrderID");

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(String.format("Chi tiết đơn hàng (%d)", ID + 1));
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        listCart = MainActivity.listOrder.get(ID).getListCart();

        mapView();

        txtName.setText(MainActivity.currentUser.getUserName());
        if (MainActivity.currentUser.getPhone() != null)
            txtPhone.setText( "0" + MainActivity.currentUser.getPhone().substring(3));
        else txtPhone.setText("Không có");
        txtAddress.setText(MainActivity.currentUser.getDiaChi());
        double total = 0;
        for(ShoppingCart cart:listCart)
            total += cart.getNewValue();
        txtTotalDetail.setText(String.format("Thanh toán: %.3f VND", total));
    }

    private void mapView() {
        rvOrderDetail = findViewById(R.id.rvOrderDetail);
        orderDetailAdapter = new OrderDetailAdapter(getApplicationContext(),listCart);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvOrderDetail.setLayoutManager(verticalLayoutManager);
        rvOrderDetail.setAdapter(orderDetailAdapter);

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtTotalDetail = findViewById(R.id.txtTotalDetail);
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
}