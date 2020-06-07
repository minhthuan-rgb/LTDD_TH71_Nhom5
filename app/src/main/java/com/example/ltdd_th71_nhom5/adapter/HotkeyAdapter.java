package com.example.ltdd_th71_nhom5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.R;

public class HotkeyAdapter extends RecyclerView.Adapter<HotkeyAdapter.ViewHolder>{
    private ItemClickListener itemClickListener;
    private String[] mData;
    private Context mContext;

    public HotkeyAdapter(Context mContext, String[] mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.hotkey, parent,false);
        return new HotkeyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtHK.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtHK;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHK = itemView.findViewById(R.id.txtHotKey);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public String getItem(int position) {
        return mData[position];
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

