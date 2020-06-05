package com.example.ltdd_th71_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_th71_nhom5.ui.home.HomeFragment;
import com.example.ltdd_th71_nhom5.ui.personal.PersonalFragment;

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
            case R.id.action_search_sub:
                Intent searchIntent = new Intent(BookActivity.this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.action_shopping_cart_sub:
                Toast.makeText(getApplicationContext(), "Action shopping card", Toast.LENGTH_LONG).show();
                return  true;
            case R.id.action_home:
            case R.id.action_personal:
            case R.id.action_category:
            case R.id.action_notification:
                Intent homeIntent = new Intent(BookActivity.this, MainActivity.class);
                startActivity(homeIntent);
                return  true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
}
