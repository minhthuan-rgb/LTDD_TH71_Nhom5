package com.example.ltdd_th71_nhom5.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.model.Order;
import com.example.ltdd_th71_nhom5.model.ShoppingCart;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private ItemClickListener itemClickListener;
    private List<Order> mData;
    private Context mContext;

    public OrderAdapter(Context mContext, List<Order> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.order, parent,false);
        return new OrderAdapter.ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitleOrder.setText(String.format("Đơn số %d:", position + 1));
        holder.txtOrderDate.setText(String.format("Ngày lập đơn: %s", mData.get(position).getDate()));
        int quantity = 0;
        double total = 0;
        for (ShoppingCart cart:mData.get(position).getListCart()){
            quantity += cart.getQuantity();
        }
        holder.txtBookQuantity.setText(String.format("Số lượng sách: %d", quantity));
        for (ShoppingCart cart:mData.get(position).getListCart()){
            total += cart.getNewValue();
        }
        holder.txtTotal.setText(String.format("Tổng tiền: %.3f VND", total));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtTitleOrder, txtBookQuantity, txtTotal, txtOrderDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleOrder = itemView.findViewById(R.id.txtTitleOrder);
            txtBookQuantity = itemView.findViewById(R.id.txtBookQuantity);
            txtTotal = itemView.findViewById(R.id.txtTotal);
            txtOrderDate = itemView.findViewById(R.id.txtOrderDate);
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

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
