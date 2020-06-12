package com.example.ltdd_th71_nhom5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.R;

import java.util.List;

public class RecentQueryAdapter extends RecyclerView.Adapter<RecentQueryAdapter.ViewHolder>{
    private RecentItemClickListener itemClickListener;
    private List<String> mData;
    private Context mContext;

    public RecentQueryAdapter(Context mContext, List<String> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.recent_query, parent,false);
        return new RecentQueryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtRQ.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtRQ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRQ = itemView.findViewById(R.id.txtRecentQuery);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null)
                itemClickListener.onRecentItemClick(v, getAdapterPosition());
        }
    }

    public void setClickListener(RecentItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public String getItem(int position) {
        return mData.get(position);
    }

    public interface RecentItemClickListener {
        void onRecentItemClick(View view, int position);
    }
}
