package com.example.ltdd_th71_nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText txtNameLogin, txtPassWord;
    Button btnSignIn, btnSignUp, btn;
    TextView txtForgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtNameLogin = (EditText)findViewById(R.id.txtNameLogin);
        txtPassWord = (EditText)findViewById(R.id.txtPassword);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        txtForgotPassword = (TextView)findViewById(R.id.txtForgotPassword);

        txtForgotPassword.setPaintFlags(txtForgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
