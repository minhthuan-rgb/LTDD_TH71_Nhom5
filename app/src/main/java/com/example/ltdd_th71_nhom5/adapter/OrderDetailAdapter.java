package com.example.ltdd_th71_nhom5.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.BookActivity;
import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.model.Order;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>{
    private List<ShoppingCart> mData;
    private Context mContext;

    public OrderDetailAdapter(Context mContext, List<ShoppingCart> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.book_cart, parent,false);
        return new OrderDetailAdapter.ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.linearChangeQuantity.setVisibility(View.INVISIBLE);
        holder.btnMultiply.setVisibility(View.INVISIBLE);

        holder.txtQuantity.setText(String.format("X%d", mData.get(position).getQuantity()));
        Picasso.get()
                .load(mData.get(position).getBook().getURL())
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(holder.imgCart);
        holder.txtTitle.setText(mData.get(position).getBook().getTitle());
        if (mData.get(position).getBook().getSale() > 0)
            holder.txtBookValue.setText(String.format("%.3f VNĐ", (mData.get(position).getBook().getValue()
                    * (100 - mData.get(position).getBook().getSale())) / 100));
        else
            holder.txtBookValue.setText(String.format("%.3f VNĐ",(mData.get(position).getBook().getValue())));

        holder.linearContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookActivity.class);

                // passing data to the book activity
                intent.putExtra("ID", mData.get(position).getBook().getBookID());
                intent.putExtra("Title", mData.get(position).getBook().getTitle());
                intent.putExtra("URL", mData.get(position).getBook().getURL());
                intent.putExtra("Value", mData.get(position).getBook().getValue());
                intent.putExtra("Sale", mData.get(position).getBook().getSale());
                intent.putExtra("Description", mData.get(position).getBook().getDescription());

                // start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCart;
        TextView txtTitle, txtBookValue, txtQuantity;
        Button btnMultiply;
        LinearLayout linearChangeQuantity, linearContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            imgCart = itemView.findViewById(R.id.imgBookCart);
            txtTitle = itemView.findViewById(R.id.txtTitleCart);
            txtBookValue = itemView.findViewById(R.id.txtValueCart);
            btnMultiply = itemView.findViewById(R.id.btnMultiply);
            linearChangeQuantity = itemView.findViewById(R.id.linearChangeQuantity);
            linearContainer = itemView.findViewById(R.id.linearContainer);
        }
    }
}
