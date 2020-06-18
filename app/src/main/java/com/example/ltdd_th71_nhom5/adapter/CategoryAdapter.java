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
import com.example.ltdd_th71_nhom5.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    List<Category> models;
    private OnCategoryListener mOnCategoryListener;

    public CategoryAdapter(Context context, List<Category> models,OnCategoryListener onCategoryListener) {
        this.context = context;
        this.models = models;
        this.mOnCategoryListener = onCategoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_category, parent,false);
        return new ViewHolder(view,mOnCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(models.get(position).getCategoryName());
        Picasso.get()
                .load(models.get(position).getURL())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .into(holder.myImage);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public String getItemKey(int position){
        return models.get(position).getKey();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView categoryName;
        ImageView myImage;
        OnCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView,OnCategoryListener onCategoryListener) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.txtCategory);
            myImage = itemView.findViewById(R.id.imageCategory);
            this.onCategoryListener = onCategoryListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }
    public interface OnCategoryListener{
        void onCategoryClick(int position);
    }
}
