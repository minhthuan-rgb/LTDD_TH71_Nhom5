package com.example.ltdd_th71_nhom5.adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.media.Image;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.ui.category.CategoryFragment;
import com.example.ltdd_th71_nhom5.ui.category.Model_category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    List<Model_category> models;
    private OnCategoryListener mOnCategoryListener;

    public CategoryAdapter(Context context, List<Model_category> models,OnCategoryListener onCategoryListener) {
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
        holder.myText1.setText(models.get(position).getTitle());
        holder.myImage.setImageResource(models.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myText1;
        ImageView myImage;
        OnCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView,OnCategoryListener onCategoryListener) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.txt_category);
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
