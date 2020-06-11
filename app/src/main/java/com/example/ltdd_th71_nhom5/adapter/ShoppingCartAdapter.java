package com.example.ltdd_th71_nhom5.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.BookActivity;
import com.example.ltdd_th71_nhom5.MainActivity;
import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>  {
    private List<ShoppingCart> mData;
    private Context mContext;

    // data is passed into the constructor
    public ShoppingCartAdapter(Context mcontext, List<ShoppingCart> mData) {
        this.mContext = mcontext;
        this.mData = mData;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.book_cart, parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtTitle.setText(mData.get(position).getBook().getTitle());
        holder.txtBookValue.setText(String.format("%.3f VNĐ",mData.get(position).getBook().getValue()));
        holder.txtQuantity.setText(String.format("X%d", mData.get(position).getQuantity()));
        holder.imgCart.setImageResource(mData.get(position).getBook().getImgID());

        // set click listener
        holder.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookActivity.class);

                // passing data to the book activity
                intent.putExtra("ID", mData.get(position).getBook().getBookID());
                intent.putExtra("Title", mData.get(position).getBook().getTitle());
                intent.putExtra("Image", mData.get(position).getBook().getImgID());
                intent.putExtra("Value", mData.get(position).getBook().getValue());
                intent.putExtra("Sale", mData.get(position).getBook().getSale());
                intent.putExtra("Description", mData.get(position).getBook().getDescription());
                intent.putExtra("CategoryID", mData.get(position).getBook().getCategoryID());

                // start the activity
                mContext.startActivity(intent);
            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = mData.get(position).getQuantity();
                if ((quantity + 1) <= 10) {
                    holder.txtQuantity.setText(String.format("X%d", ++quantity));
                    mData.get(position).setQuantity(quantity);
                }
                else
                    holder.txtQuantity.setText(String.format("X%d", quantity));
            }
        });

        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = mData.get(position).getQuantity();
                if ((quantity - 1) > 0) {
                    holder.txtQuantity.setText(String.format("X%d", --quantity));
                    mData.get(position).setQuantity(quantity);
                }
                else{
                    MainActivity.listShoppingCart.remove(MainActivity.listShoppingCart.get(position));
                    mData.remove(mData.get(position));
                }
            }
        });

        holder.btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.listShoppingCart.remove(MainActivity.listShoppingCart.get(position));
                mData.remove(mData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCart;
        TextView txtTitle, txtBookValue, txtQuantity;
        Button btnAdd, btnSub, btnMultiply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            imgCart = itemView.findViewById(R.id.imgBookCart);
            txtTitle = itemView.findViewById(R.id.txtTitleCart);
            txtBookValue = itemView.findViewById(R.id.txtValueCart);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnSub = itemView.findViewById(R.id.btnSub);
            btnMultiply = itemView.findViewById(R.id.btnMultiply);
        }
    }
}
