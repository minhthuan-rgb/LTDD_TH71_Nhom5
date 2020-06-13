package com.example.ltdd_th71_nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ltdd_th71_nhom5.server.JsonPlaceHolderApi;
import com.example.ltdd_th71_nhom5.server.Post;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://172.16.1.62:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi =  retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, response.code(),Toast.LENGTH_SHORT).show();
                }
                List<Post> posts = response.body();
                for (Post post : posts){
                    String content = "";
                    content += "User: " + post.getUsername() + "\n";
                    content += "Password: " + post.getPassword() + "\n";

                    Toast.makeText(LoginActivity.this, content.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void catchViewClickEvent() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainIntent);
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
