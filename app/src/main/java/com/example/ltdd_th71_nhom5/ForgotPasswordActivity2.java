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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ForgotPasswordActivity2 extends AppCompatActivity {
    EditText txtNewPW, txtAgain;
    Button btnSummit, btnBackToLogin, btnBackToHome;
    RelativeLayout layoutResetPW;
    LinearLayout layoutFinish;
    String key = "";
    int position;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
        //actionbar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setTitle("Quên mật khẩu");
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        key = intent.getExtras().getString("Key");
        position = intent.getExtras().getInt("Position");

        mapView();

        cacthButtonClickEvent();
    }
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }
    private void cacthButtonClickEvent() {
        btnSummit.setOnClickListener(v -> {
            String newPW = txtNewPW.getText().toString();
            String againPW = txtAgain.getText().toString();
            if (checkEditText(txtNewPW) || checkEditText(txtAgain)) {
                if (!newPW.equals(againPW))
                    Toast.makeText(ForgotPasswordActivity2.this, "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
                else {
                    MainActivity.mData.child("TaiKhoan").child(key).child("passWord").setValue(newPW);
                    MainActivity.listUser.get(position).setPassWord(newPW);
                    layoutResetPW.setVisibility(View.INVISIBLE);
                    layoutFinish.setVisibility(View.VISIBLE);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    actionBar.setDisplayShowTitleEnabled(false);
                }
            }
        });

        btnBackToHome.setOnClickListener(v -> {
            Intent mainIntent = new Intent(ForgotPasswordActivity2.this, MainActivity.class);
            mainIntent.putExtra("Activity", "Forgot");
            startActivity(mainIntent);
        });

        btnBackToLogin.setOnClickListener(v -> {
            Intent loginIntent = new Intent(ForgotPasswordActivity2.this, LoginActivity.class);
            loginIntent.putExtra("Activity", "Forgot");
            startActivity(loginIntent);
        });
    }

    private void mapView() {
        txtNewPW = findViewById(R.id.txtNewPW);
        txtAgain = findViewById(R.id.txtAgain);
        btnSummit = findViewById(R.id.btnSummit);
        btnBackToHome = findViewById(R.id.btnBackToHome);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);
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