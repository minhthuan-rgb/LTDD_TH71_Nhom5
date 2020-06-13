package com.example.ltdd_th71_nhom5.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
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
import com.example.ltdd_th71_nhom5.ShoppingCartActivity;
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
        if (mData.get(position).getBook().getSale() > 0){
            holder.txtNewValue.setText(String.format("%.3f VNĐ",(mData.get(position).getBook().getValue()
                    * (100 - mData.get(position).getBook().getSale()))/100));
            String oldValue = String.format("%.3f VNĐ",(mData.get(position).getBook().getValue()));
            SpannableString spanned = new SpannableString(oldValue);
            spanned.setSpan(new StrikethroughSpan(),0,oldValue.length(),0);
            holder.txtBookValue.setText(spanned);
            holder.txtBookValue.setTextColor(Color.parseColor("#9E9898"));
            holder.txtBookValue.setTextSize(20);
        }
        else
            holder.txtBookValue.setText(String.format("%.3f VNĐ",(mData.get(position).getBook().getValue())));
        holder.txtTitle.setText(mData.get(position).getBook().getTitle());
        holder.txtQuantity.setText(String.format("X%d", mData.get(position).getQuantity()));
        holder.imgCart.setImageResource(mData.get(position).getBook().getImgID());

        if ( mData.get(position).getQuantity() >= 10)
            holder.btnAdd.setVisibility(View.INVISIBLE);
        else if (mData.get(position).getQuantity() <= 1)
            holder.btnSub.setVisibility(View.INVISIBLE);
        else {
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.btnSub.setVisibility(View.VISIBLE);
        }

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
                int quantity = mData.get(position).getQuantity() + 1;
                int sale = mData.get(position).getBook().getSale();
                double newValue = 0;
                MainActivity.listShoppingCart.get(position).setQuantity(quantity);

                if (sale != 0)
                    newValue = (MainActivity.listShoppingCart.get(position).getQuantity()
                            * MainActivity.listShoppingCart.get(position).getBook().getValue() * (100 - sale))/100;
                else
                    newValue = MainActivity.listShoppingCart.get(position).getBook().getValue()
                            *MainActivity.listShoppingCart.get(position).getQuantity();
                MainActivity.listShoppingCart.get(position).setNewValue(newValue);
                ShoppingCartActivity.calTotalValue();

                if ( mData.get(position).getQuantity() > 9) {
                    holder.btnAdd.setVisibility(View.INVISIBLE);
                    holder.btnSub.setVisibility(View.VISIBLE);
                    holder.txtQuantity.setText(String.format("X%d", quantity));
                }
                else {
                    holder.btnAdd.setVisibility(View.VISIBLE);
                    holder.btnSub.setVisibility(View.VISIBLE);
                    holder.txtQuantity.setText(String.format("X%d", quantity));
                }
            }
        });

        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = mData.get(position).getQuantity() - 1;
                int sale = mData.get(position).getBook().getSale();
                double newValue = 0;
                holder.txtQuantity.setText(String.format("X%d", quantity));
                MainActivity.listShoppingCart.get(position).setQuantity(quantity);

                if (sale != 0)
                    newValue = (MainActivity.listShoppingCart.get(position).getQuantity()
                            * MainActivity.listShoppingCart.get(position).getBook().getValue() * (100 - sale))/100;
                else
                    newValue = MainActivity.listShoppingCart.get(position).getBook().getValue()
                            *MainActivity.listShoppingCart.get(position).getQuantity();
                MainActivity.listShoppingCart.get(position).setNewValue(newValue);
                ShoppingCartActivity.calTotalValue();

                if ( mData.get(position).getQuantity() < 2) {
                    holder.btnSub.setVisibility(View.INVISIBLE);
                    holder.btnAdd.setVisibility(View.VISIBLE);
                    holder.txtQuantity.setText(String.format("X%d", quantity));
                }
                else {
                    holder.btnSub.setVisibility(View.VISIBLE);
                    holder.btnAdd.setVisibility(View.VISIBLE);
                    holder.txtQuantity.setText(String.format("X%d", quantity));
                }
            }
        });

        holder.btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.listShoppingCart.remove(position);
                        ShoppingCartActivity.calTotalValue();
                        ShoppingCartActivity.checkData();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCart;
        TextView txtTitle, txtBookValue, txtQuantity, txtNewValue;
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
            txtNewValue = itemView.findViewById(R.id.txtNewValueCart);
        }
    }
}
