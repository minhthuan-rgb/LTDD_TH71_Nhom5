package com.example.ltdd_th71_nhom5.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.MainActivity;
import com.example.ltdd_th71_nhom5.MoreBookActivity;
import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.adapter.CategoryAdapter;
import com.example.ltdd_th71_nhom5.model.Category;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryAdapter.OnCategoryListener{
    public List<Category> listCategory;
    RecyclerView recyclerView;
    CategoryAdapter mAdapter = null;
    RecyclerView recyclerView1;
    CategoryAdapter mAdapter1 = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = root.findViewById(R.id.recView_Category);
        listCategory = new ArrayList<>();
        mAdapter = new CategoryAdapter(root.getContext(), listCategory,this);
        getCategoryList(listCategory, mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),3));
        recyclerView.setAdapter(mAdapter);

        recyclerView1 = root.findViewById(R.id.rec_Viewcategory1);
        recyclerView1.setLayoutManager(new GridLayoutManager(root.getContext(),3));
        final LinearLayoutManager layoutManager = new  LinearLayoutManager(root.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(mAdapter);
        return root;
    }


    private void getCategoryList(List<Category> listCategory, CategoryAdapter adapter){
        MainActivity.mData.child("LoaiSach").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Category category = dataSnapshot.getValue(Category.class);
                category.setId(dataSnapshot.getKey());
                listCategory.add(category);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        Intent allIntent = new Intent(getContext(), MoreBookActivity.class);
        allIntent.putExtra("Key", mAdapter.getItemKey(position));
        allIntent.putExtra("CategoryName", mAdapter.getItemName(position));
        startActivity(allIntent);
    }
}
