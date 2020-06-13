package com.example.ltdd_th71_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ForgotPasswordActivity2 extends AppCompatActivity {
    EditText txtNewPW, txtAgain;
    Button btnSummit, btnBackToHome;
    RelativeLayout layoutResetPW;
    CoordinatorLayout layoutFinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
        //actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setTitle("Quên mật khẩu");
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mapView();

        cacthButtonClickEvent();
    }

    private void cacthButtonClickEvent() {
        btnSummit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                String newPW = txtNewPW.getText().toString();
                String againPW = txtAgain.getText().toString();
                if (newPW.isEmpty() || againPW.isEmpty())
                    Toast.makeText(ForgotPasswordActivity2.this, "Bạn phải nhập đầy đủ cả hai ô mật khẩu", Toast.LENGTH_LONG).show();
                else if (!newPW.equals(againPW))
                    Toast.makeText(ForgotPasswordActivity2.this, "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
                else{
                   layoutResetPW.setVisibility(View.INVISIBLE);
                   layoutFinish.setVisibility(View.VISIBLE);
                }
            }
        });

        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ForgotPasswordActivity2.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    private void mapView() {
        txtNewPW = findViewById(R.id.txtNewPW);
        txtAgain = findViewById(R.id.txtAgain);
        btnSummit = findViewById(R.id.btnSummit);
        btnBackToHome = findViewById(R.id.btnBackToHome);
        layoutResetPW = findViewById(R.id.layoutResetPW);
        layoutFinish = findViewById(R.id.layoutFinish);

        txtNewPW.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        txtAgain.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);

        layoutResetPW.setVisibility(View.VISIBLE);
        layoutFinish.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}