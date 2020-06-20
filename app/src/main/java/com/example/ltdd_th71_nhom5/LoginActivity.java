package com.example.ltdd_th71_nhom5;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ltdd_th71_nhom5.model.User;
import com.example.ltdd_th71_nhom5.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText txtNameLogin, txtPassWord;
    Button btnSignIn, btnSignUp;
    TextView txtForgotPassword;
    ImageView imgCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapView();
        catchViewClickEvent();
    }

    private void catchViewClickEvent() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Đăng nhập", Toast.LENGTH_SHORT).show();
                for(int i = 0; i < MainActivity.listUser.size(); i++){
                    if(txtNameLogin.getText().toString().equals(MainActivity.listUser.get(i).getId()) && txtPassWord.getText().toString().equals(MainActivity.listUser.get(i).getPassWord())) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        MainActivity.isLogin = true;
                        MainActivity.currentUser = MainActivity.listUser.get(i);
                        onBackPressed();


                        break;
                    }
                }

            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPWIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPWIntent);
            }
        });

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reGister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(reGister);
            }
        });
    }

    private void mapView() {
        txtNameLogin = findViewById(R.id.txtNameLogin);
        txtPassWord = findViewById(R.id.txtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        imgCancel = findViewById(R.id.imgCancel);
        txtForgotPassword.setPaintFlags(txtForgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
