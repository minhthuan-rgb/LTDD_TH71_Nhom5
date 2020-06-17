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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.MainActivity;
import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.adapter.HomeBookAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{
    ViewFlipper vfHome;
    Animation in, out;
    RecyclerView recyclerFlash, recyclerLove, recyclerDetective, recyclerHorror, recyclerAdventure, recyclerNovel;
    HomeBookAdapter adapter1 = null, adapter2 = null, adapter3 = null, adapter4 = null,
            adapter5 = null, adapter6 = null;


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

        //map view
        recyclerFlash = root.findViewById(R.id.recyclerFlash);
        recyclerDetective = root.findViewById(R.id.recyclerDetective);
        recyclerLove = root.findViewById(R.id.recyclerLove);
        recyclerHorror = root.findViewById(R.id.recyclerHorror);
        recyclerAdventure = root.findViewById(R.id.recyclerAdventure);
        recyclerNovel = root.findViewById(R.id.recyclerNovel);

        MainActivity.flashdealList = new ArrayList<>();
        String s = "FlashDeal";


        // init adapter
        adapter1 = new HomeBookAdapter(root.getContext(), MainActivity.flashdealList);
        adapter1 = new HomeBookAdapter(root.getContext(), MainActivity.flashdealList);
        adapter1 = new HomeBookAdapter(root.getContext(), MainActivity.flashdealList);
        adapter1 = new HomeBookAdapter(root.getContext(), MainActivity.flashdealList);
        adapter1 = new HomeBookAdapter(root.getContext(), MainActivity.flashdealList);
        adapter1 = new HomeBookAdapter(root.getContext(), MainActivity.flashdealList);

        //get data
        LoadDuLieu(s, MainActivity.flashdealList, adapter1);

        //set up recyclerView
        setUpRecyclerView(root.getContext(),recyclerFlash, adapter1);
        setUpRecyclerView(root.getContext(),recyclerDetective, adapter2);
        setUpRecyclerView(root.getContext(),recyclerLove, adapter3);
        setUpRecyclerView(root.getContext(),recyclerHorror, adapter4);
        setUpRecyclerView(root.getContext(),recyclerAdventure, adapter5);
        setUpRecyclerView(root.getContext(),recyclerNovel, adapter6);

        return root;
    }

    private void LoadDuLieu(String s, List<Book> listBook, HomeBookAdapter adapter) {
        MainActivity.mData.child(s).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                listBook.add(book);
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

    public static void setUpRecyclerView(Context context , RecyclerView recyclerView, HomeBookAdapter adapter){
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
