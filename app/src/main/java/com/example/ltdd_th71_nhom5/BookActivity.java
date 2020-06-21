package com.example.ltdd_th71_nhom5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;
import com.squareup.picasso.Picasso;

public class BookActivity extends AppCompatActivity {
    private TextView txtTitle, txtValue, txtSale, txtDescription, txtNewValue;
    private ImageView imgBookSingle;
    private Spinner spinner;
    private Button btnChoose;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //map view
        mapView();

        //Recieve data
        Intent intent = getIntent();
        String ID = intent.getExtras().getString("ID");
        String title = intent.getExtras().getString("Title");
        String imgURL = intent.getExtras().getString("URL");
        double value = intent.getExtras().getDouble("Value");
        int sale = intent.getExtras().getInt("Sale");
        String description = intent.getExtras().getString("Description");

        Book book = new Book(ID, title, value, sale, description, imgURL);

        //set up view
        txtTitle.setText(title);
        if (sale > 0) {
            txtSale.setText(String.format("-%d", sale) + "%");
            txtNewValue.setText(String.format("%.3f VNĐ", (value * (100 - sale)) / 100));
            String oldValue = String.format("%.3f VNĐ", value);
            SpannableString spanned = new SpannableString(oldValue);
            spanned.setSpan(new StrikethroughSpan(), 0, oldValue.length(), 0);
            txtValue.setText(spanned);
            txtValue.setTextColor(Color.parseColor("#9E9898"));
            txtValue.setTextSize(20);
        } else
            txtValue.setText(String.format("%.3f VNĐ", value));

        Picasso.get()
                .load(imgURL)
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(imgBookSingle);
        txtDescription.setText(description);

        //spinner
        CatchEventSpinner();

        // Button Choose
        btnChoose.setOnClickListener(v -> {
            int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
            double newValue = 0;
            if(MainActivity.listShoppingCart.size() > 0){
                boolean exists = false;
                for (int i = 0; i < MainActivity.listShoppingCart.size(); i++) {
                    if (MainActivity.listShoppingCart.get(i).getBook().getBookID().equals(ID)) {
                        MainActivity.listShoppingCart.get(i)
                                .setQuantity(MainActivity.listShoppingCart.get(i).getQuantity() + quantity);

                        if (MainActivity.listShoppingCart.get(i).getQuantity() > 10)
                            MainActivity.listShoppingCart.get(i).setQuantity(10);

                        if (sale != 0)
                            newValue = (MainActivity.listShoppingCart.get(i).getQuantity() * value
                                    * (100 - sale)) / 100;
                        else
                            newValue = value * MainActivity.listShoppingCart.get(i).getQuantity();

                        MainActivity.listShoppingCart.get(i).setNewValue(newValue);
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    if (sale != 0)
                        newValue = (quantity * value * (100 - sale)) / 100;
                    else
                        newValue = quantity * value;

                    MainActivity.listShoppingCart.add(new ShoppingCart(book, quantity, newValue));
                }
            } else {
                if (sale != 0)
                    newValue = (quantity * value * (100 - sale)) / 100;
                else
                    newValue = quantity * value;

                MainActivity.listShoppingCart.add(new ShoppingCart(book, quantity, newValue));
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
            builder.setTitle("Một sản phẩm đã được thêm vào giỏ hàng");
            builder.setMessage(book.getTitle() + "\nGiá: " + String.format("%.3f VND",
                    book.getValue()) + (sale > 0 ?(String.format("%d", book.getSale()) + "%"):""));
            builder.setPositiveButton("Xem giỏ hàng", (dialog, which) -> {
                Intent cartIntent = new Intent(BookActivity.this, ShoppingCartActivity.class);
                startActivity(cartIntent);
            });

            builder.show();
        });
    }
    private void mapView() {
        txtTitle = findViewById(R.id.txtTitle);
        txtSale = findViewById(R.id.txtSale);
        txtValue = findViewById(R.id.txtValue);
        imgBookSingle = findViewById(R.id.imgBookSingle);
        txtDescription = findViewById(R.id.txtDescription);
        spinner = findViewById(R.id.spinner);
        btnChoose = findViewById(R.id.btnChoose);
        txtNewValue = findViewById(R.id.txtNewValue);
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
            case R.id.action_category:
            case R.id.action_personal:
            case R.id.action_notification:
                Intent homeIntent = new Intent(BookActivity.this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
}
