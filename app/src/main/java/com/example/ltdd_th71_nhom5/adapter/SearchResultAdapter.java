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

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>{
    private List<Book> mData;
    private Context mContext;

    // data is passed into the constructor
    public SearchResultAdapter(Context mcontext, List<Book> mData) {
        this.mContext = mcontext;
        this.mData = mData;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.book_search_result, parent,false);
        return new SearchResultAdapter.ViewHolder(view);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bookTitle.setText(mData.get(position).getTitle());
        holder.bookValue.setText(String.format("%.3f VNĐ",mData.get(position).getValue()));
            if (mData.get(position).getSale() != 0)
                holder.bookSale.setText(String.format("-%d",mData.get(position).getSale()) + "%");
            else
                holder.bookSale.setVisibility(View.GONE);
        holder.bookImg.setImageResource(mData.get(position).getImgID());

        // set click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, BookActivity.class);

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
        TextView bookTitle, bookValue, bookSale;
        ImageView bookImg;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.txtTitleSearch);
            bookImg = itemView.findViewById(R.id.imgBookSearch);
            cardView = itemView.findViewById(R.id.cvBookSearch);
            bookValue = itemView.findViewById(R.id.txtValueSearch);
            bookSale = itemView.findViewById(R.id.txtSaleSearch);
        }
    }
}
