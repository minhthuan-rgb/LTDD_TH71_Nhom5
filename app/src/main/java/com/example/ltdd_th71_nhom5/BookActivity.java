package com.example.ltdd_th71_nhom5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private TextView txtTitle, txtValue, txtSale, txtDescription;
    private ImageView imgBookSingle;
    private Spinner spinner;
    private Button btnChoose;
    CoordinatorLayout main_content;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setDisplayShowTitleEnabled(false);

        //map view
        mapView();

        //Recieve data
        Intent intent = getIntent();
        int ID = intent.getExtras().getInt("ID");
        String title = intent.getExtras().getString("Title");
        int image = intent.getExtras().getInt("Image");
        double value = intent.getExtras().getDouble("Value");
        int sale = intent.getExtras().getInt("Sale");
        String description = intent.getExtras().getString("Description");
        int categoryID = intent.getExtras().getInt("CategoryID");

        Book book = new Book(ID, title, categoryID, value, sale, description, image);

        //set up view
        txtTitle.setText(title);
        txtValue.setText(String.format("%.3f VNĐ",value));
        txtSale.setText(String.format("-%d",sale) + "%");
        imgBookSingle.setImageResource(image);
        txtDescription.setText(description);

        //spinner
        CatchEventSpinner();

        // Button Choose
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
                double newValue = 0;
                if(MainActivity.listShoppingCart.size() > 0){
                    boolean exists = false;
                    for(int i = 0; i < MainActivity.listShoppingCart.size(); i++){
                        if (MainActivity.listShoppingCart.get(i).getBook().getBookID() == ID){
                            MainActivity.listShoppingCart.get(i)
                                    .setQuantity(MainActivity.listShoppingCart.get(i).getQuantity() + quantity);

                            if (MainActivity.listShoppingCart.get(i).getQuantity() > 10)
                                MainActivity.listShoppingCart.get(i).setQuantity(10);

                            if (sale != 0)
                                newValue = (MainActivity.listShoppingCart.get(i).getQuantity() * value
                                        *(100 - sale))/100;
                            else
                                newValue = value*MainActivity.listShoppingCart.get(i).getQuantity();

                            MainActivity.listShoppingCart.get(i).setNewValue(newValue);
                            exists = true;
                            break;
                        }
                    }

                    if (!exists){
                        if (sale != 0)
                            newValue = (quantity * value * (100 - sale))/100;
                        else
                            newValue = quantity * value;

                        MainActivity.listShoppingCart.add(new ShoppingCart(book, quantity, newValue));
                    }
                }else{
                    if (sale != 0)
                        newValue = (quantity * value * (100 - sale))/100;
                    else
                        newValue = quantity * value;

                    MainActivity.listShoppingCart.add(new ShoppingCart(book, quantity, newValue));
                }
            }
        });
    }
    private void mapView() {
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtSale = (TextView)findViewById(R.id.txtSale);
        txtValue = (TextView)findViewById(R.id.txtValue);
        imgBookSingle = (ImageView)findViewById(R.id.imgBookSingle);
        txtDescription = (TextView)findViewById(R.id.txtDescription);
        spinner = (Spinner)findViewById(R.id.spinner);
        btnChoose = (Button)findViewById(R.id.btnChoose);
    }

    private void CatchEventSpinner() {
        Integer[] quantity = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, quantity);
        spinner.setAdapter(arrayAdapter);
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
                Intent cartIntent  = new Intent(BookActivity.this, ShoppingCartActivity.class);
                startActivity(cartIntent);
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
