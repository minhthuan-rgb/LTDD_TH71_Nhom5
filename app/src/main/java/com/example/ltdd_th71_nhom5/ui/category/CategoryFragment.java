package com.example.ltdd_th71_nhom5.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.adapter.CategoryAdapter;
import com.example.ltdd_th71_nhom5.model.Category;
import com.example.ltdd_th71_nhom5.model.Model_category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryAdapter.OnCategoryListener{

    RecyclerView recyclerView;
    CategoryAdapter madapter;

    private CategoryViewModel categoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = root.findViewById(R.id.recView_Category);
        madapter = new CategoryAdapter(root.getContext(),getmyList(),this);
        recyclerView.setAdapter(madapter);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),3));
        return root;
    }

    private List<Category> getmyList(){

        /*ArrayList<Category> models = new ArrayList<>();
        Model_category m = new Model_category();
        m.setTitle("Lãng mạn");
        m.setImg(R.drawable.langman);
        models.add(m);

        m = new Model_category();
        m.setTitle("Phiêu lưu");
        m.setImg(R.drawable.phieuluu);
        models.add(m);

        m = new Model_category();
        m.setTitle("Kinh tế");
        m.setImg(R.drawable.kinhte);
        models.add(m);

        m = new Model_category();
        m.setTitle("Trẻ em");
        m.setImg(R.drawable.treem);
        models.add(m);

        m = new Model_category();
        m.setTitle("Tiểu thuyết");
        m.setImg(R.drawable.tieuthuyet);
        models.add(m);

        m = new Model_category();
        m.setTitle("Sinh học");
        m.setImg(R.drawable.sinhhoc);
        models.add(m);

        m = new Model_category();
        m.setTitle("Toán học");
        m.setImg(R.drawable.toanhoc);
        models.add(m);

        m = new Model_category();
        m.setTitle("Vật lí học");
        m.setImg(R.drawable.vatly);
        models.add(m);

        m = new Model_category();
        m.setTitle("Cuộc sống");
        m.setImg(R.drawable.cuocsong);
        models.add(m);

        m = new Model_category();
        m.setTitle("Nấu ăn");
        m.setImg(R.drawable.nauan);
        models.add(m);

        m = new Model_category();
        m.setTitle("Ngoại ngữ");
        m.setImg(R.drawable.ngoaingu);
        models.add(m);

        m = new Model_category();
        m.setTitle("Địa lí");
        m.setImg(R.drawable.diali);
        models.add(m);

        m = new Model_category();
        m.setTitle("Lịch sử");
        m.setImg(R.drawable.lichsu);
        models.add(m);

        m = new Model_category();
        m.setTitle("Phụ nữ");
        m.setImg(R.drawable.phunu);
        models.add(m);

        m = new Model_category();
        m.setTitle("Khoa học");
        m.setImg(R.drawable.khoahoc);
        models.add(m);

        m = new Model_category();
        m.setTitle("Giáo dục");
        m.setImg(R.drawable.giaoduc);
        models.add(m);

        m = new Model_category();
        m.setTitle("Truyện tranh");
        m.setImg(R.drawable.truyentranh);
        models.add(m);

        m = new Model_category();
        m.setTitle("Kinh dị");
        m.setImg(R.drawable.kinhdi);
        models.add(m);
        
        return models;*/
    }

    @Override
    public void onCategoryClick(int position) {

    }
}
