package com.example.ltdd_th71_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {
    private TextView txtTitle, txtValue, txtSale;
    private ImageView imgBookSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setDisplayShowTitleEnabled(false);

        txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtSale = (TextView)findViewById(R.id.txtSale);
        txtValue = (TextView)findViewById(R.id.txtValue);
        imgBookSingle =(ImageView)findViewById(R.id.imgBookSingle);

        //Recieve data
        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        int image = intent.getExtras().getInt("Image");
        double value = intent.getExtras().getDouble("Value");
        String sale = intent.getExtras().getString("Sale");

        //set up view
        txtTitle.setText(title);
        txtValue.setText(String.valueOf(value));
        txtSale.setText(sale);
        imgBookSingle.setImageResource(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_search:
                Toast.makeText(getApplicationContext(),"Action help",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_shopping_cart:
                Toast.makeText(getApplicationContext(), "Action search", Toast.LENGTH_LONG).show();
                return  true;
            case R.id.action_home:
                Toast.makeText(getApplicationContext(), "Action refresh", Toast.LENGTH_LONG).show();
                return  true;
            case R.id.action_personal:
                Toast.makeText(getApplicationContext(), "Action personal", Toast.LENGTH_LONG).show();
                return  true;
            case R.id.action_category:
                Toast.makeText(getApplicationContext(), "Action category", Toast.LENGTH_LONG).show();
                return  true;
            case R.id.action_notification:
                Toast.makeText(getApplicationContext(), "Action notification", Toast.LENGTH_LONG).show();
                return  true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
}
