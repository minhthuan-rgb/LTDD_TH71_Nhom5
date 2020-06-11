package com.example.ltdd_th71_nhom5.ui.home;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.adapter.HomeBookAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{
    ViewFlipper vfHome;
    Animation in, out;
    RecyclerView recyclerFlash, recyclerLove, recyclerDetective, recyclerHorror, recyclerAdventure, recyclerNovel;
    //  HomeBookAdapter adapterSale, adapterNull;
    List<Book> listBook;

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // view flipper
        vfHome = root.findViewById(R.id.vfHome);
        in = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_out);
        vfHome.setInAnimation(in);
        vfHome.setOutAnimation(out);
        vfHome.setAutoStart(true);

        createList();

        //set up RecyclerView
        recyclerFlash = root.findViewById(R.id.recyclerFlash);
        recyclerFlash = root.findViewById((R.id.recyclerFlash));
        recyclerDetective = root.findViewById(R.id.recyclerDetective);
        recyclerLove = root.findViewById(R.id.recyclerLove);
        recyclerHorror = root.findViewById(R.id.recyclerHorror);
        recyclerAdventure = root.findViewById(R.id.recyclerAdventure);
        recyclerNovel = root.findViewById(R.id.recyclerNovel);

        setUpRecyclerView(root.getContext(),listBook,recyclerFlash);
        setUpRecyclerView(root.getContext(),listBook,recyclerDetective);
        setUpRecyclerView(root.getContext(),listBook,recyclerLove);
        setUpRecyclerView(root.getContext(),listBook,recyclerHorror);
        setUpRecyclerView(root.getContext(),listBook,recyclerAdventure);
        setUpRecyclerView(root.getContext(),listBook,recyclerNovel);

        return root;
    }

    public void createList(){
        listBook = new ArrayList<>();
        listBook.add(new Book(1,"a", 1, 45.000, 50, "abcdefghijklmnopq", R.drawable.vf_1));
        listBook.add(new Book(2,"b", 2, 45.000, 40, "abcdefghijklmnopq", R.drawable.vf_2));
        listBook.add(new Book(3,"c", 3, 45.000, 30, "abcdefghijklmnopq", R.drawable.vf_3));
        listBook.add(new Book(4,"d", 4, 45.000, 20, "abcdefghijklmnopq", R.drawable.vf_4));
        listBook.add(new Book(5,"e", 5, 45.000, 10, "abcdefghijklmnopq", R.drawable.vf_5));
        listBook.add(new Book(6,"f", 6, 45.000, 0, "abcdefghijklmnopq", R.drawable.vf_6));
        listBook.add(new Book(8,"g", 7, 45.000, 0, "abcdefghijklmnopq", R.drawable.vf_7));
        listBook.add(new Book(9,"h", 8, 45.000, 0, "abcdefghijklmnopq", R.drawable.vf_8));
    }

    public void setUpRecyclerView(Context context, List<Book> listBook, RecyclerView recyclerView){
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        HomeBookAdapter adapter = new HomeBookAdapter(context, listBook);
        recyclerView.setAdapter(adapter);
    }
}
