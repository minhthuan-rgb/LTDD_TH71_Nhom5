package com.example.ltdd_th71_nhom5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.R;

import java.util.List;

public class HomeBookAdapter extends RecyclerView.Adapter<HomeBookAdapter.ViewHolder> {
    private List<String> textSales;
    private List<Integer> imgID;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public HomeBookAdapter(Context context, List<String> textSales, List<Integer> imgID) {
        this.mInflater = LayoutInflater.from(context);
        this.textSales = textSales;
        this.imgID = imgID;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.book_home,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int img = imgID.get(position);
        String textSale = textSales.get(position);
        holder.bookImg.setImageResource(img);
        holder.txtSale.setText(textSale);
    }

    @Override
    public int getItemCount() {
        return imgID.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtSale;
        ImageView bookImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSale = itemView.findViewById(R.id.sale);
            bookImg = itemView.findViewById(R.id.imgBookHome);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    // covenience method for getting data at click position
    public String getItem(int id){
        return textSales.get(id);
    }

    //allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    //parent activity will implement this method to respond to click events
    public interface  ItemClickListener{
        void onItemClick(View view, int position);
    }
}


