package com.example.ltdd_th71_nhom5.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.BookActivity;
import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.model.Book;

import java.util.List;

public class HomeBookAdapter extends RecyclerView.Adapter<HomeBookAdapter.ViewHolder> {
    private List<Book> mData;
    private Context mContext;

    // data is passed into the constructor
    public HomeBookAdapter(Context mcontext, List<Book> mData) {
        this.mContext = mcontext;
        this.mData = mData;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.book_home, parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtSale.setText( String.format("%d",mData.get(position).getSale()) + "%");
        holder.bookImg.setImageResource(mData.get(position).getImgID());

        // set click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,BookActivity.class);

                // passing data to the book activity
                intent.putExtra("ID", mData.get(position).getBookID());
                intent.putExtra("Title", mData.get(position).getTitle());
                intent.putExtra("Image", mData.get(position).getImgID());
                intent.putExtra("Value", mData.get(position).getValue());
                intent.putExtra("Sale", mData.get(position).getSale());
                intent.putExtra("description", mData.get(position).getDescription());

                // start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSale;
        ImageView bookImg;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSale = itemView.findViewById(R.id.sale);
            bookImg = itemView.findViewById(R.id.imgBookHome);
            cardView = itemView.findViewById(R.id.cvBookHome);
        }
    }
}


