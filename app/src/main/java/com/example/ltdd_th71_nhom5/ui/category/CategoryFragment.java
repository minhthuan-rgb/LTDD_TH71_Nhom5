package com.example.ltdd_th71_nhom5.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.adapter.CategoryAdapter;

public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    Context ct;
    String s1[],s2[];
    int images[] = {R.drawable.langman,R.drawable.phieuluu,R.drawable.kinhte,R.drawable.treem,R.drawable.tieuthuyet,
            R.drawable.sinhhoc,R.drawable.toanhoc,R.drawable.vatly,R.drawable.cuocsong,R.drawable.nauan,R.drawable.ngoaingu,
            R.drawable.diali,R.drawable.lichsu,R.drawable.phunu,R.drawable.khoahoc,R.drawable.giaoduc,R.drawable.truyentranh,
            R.drawable.kinhdi};

    private CategoryViewModel categoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        s1 = getResources().getStringArray(R.array.recView_category);
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = root.findViewById(R.id.recView_Category);

        CategoryAdapter categoryAdapter = new CategoryAdapter(ct ,s1,images);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

}
