package com.example.ltdd_th71_nhom5.ui.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ltdd_th71_nhom5.MainActivity;
import com.example.ltdd_th71_nhom5.MoreBookActivity;
import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.adapter.HomeBookAdapter;
import com.example.ltdd_th71_nhom5.model.Book;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{
    ViewFlipper vfHome;
    Animation in, out;
    RecyclerView recyclerFlash, recyclerEconomy, recyclerLiterary, recyclerMentality, recyclerParenting,
            recyclerFLanguage, recyclerChildren;
    HomeBookAdapter adapter1 = null, adapter2 = null, adapter3 = null, adapter4 = null,
            adapter5 = null, adapter6 = null, adapter7 = null;
    List<Book> flashdealList, literaryList, economyList, mentalityList,
            parentingList, fLanguageList, childrenList;
    Button btnMoreFlash, btnMoreLiterary, btnMoreEconomy, btnMoreMentality, btnMoreParenting, btnMoreFLanguage, btnMoreChildren;


    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mapView(root);
        initList();


        // init adapter
        adapter1 = new HomeBookAdapter(getContext(), flashdealList);
        adapter2 = new HomeBookAdapter(getContext(), literaryList);
        adapter3 = new HomeBookAdapter(getContext(), economyList);
        adapter4 = new HomeBookAdapter(getContext(), mentalityList);
        adapter5 = new HomeBookAdapter(getContext(), parentingList);
        adapter6 = new HomeBookAdapter(getContext(), fLanguageList);
        adapter7 = new HomeBookAdapter(getContext(), childrenList);

        //get data
        getFlashDealBook();
        loadData("VanHoc", literaryList, adapter2, true);
        loadData("KinhTe", economyList, adapter3, true);
        loadData("TamLy", mentalityList, adapter4, true);
        loadData("NuoiDayCon", parentingList, adapter5, true);
        loadData("NgoaiNgu", fLanguageList, adapter6, true);
        loadData("ThieuNhi", childrenList, adapter7, true);


        //set up recyclerView
        setUpRecyclerView(recyclerFlash, adapter1);
        setUpRecyclerView(recyclerLiterary, adapter2);
        setUpRecyclerView(recyclerEconomy, adapter3);
        setUpRecyclerView(recyclerMentality, adapter4);
        setUpRecyclerView(recyclerParenting, adapter5);
        setUpRecyclerView(recyclerFLanguage, adapter6);
        setUpRecyclerView(recyclerChildren, adapter7);

        catchViewEventListener();

        return root;
    }

    private void initList() {
        flashdealList = new ArrayList<>();
        literaryList = new ArrayList<>();
        economyList = new ArrayList<>();
        mentalityList = new ArrayList<>();
        parentingList = new ArrayList<>();
        fLanguageList = new ArrayList<>();
        childrenList = new ArrayList<>();
    }

    private void mapView(View root) {
        //view flipper
        vfHome = root.findViewById(R.id.vfHome);
        in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        vfHome.setInAnimation(in);
        vfHome.setOutAnimation(out);
        vfHome.setAutoStart(true);

        //recycler view
        recyclerFlash = root.findViewById(R.id.recyclerFlash);
        recyclerLiterary = root.findViewById(R.id.recyclerLiterary);
        recyclerEconomy = root.findViewById(R.id.recyclerEconomy);
        recyclerMentality = root.findViewById(R.id.recyclerMentality);
        recyclerParenting = root.findViewById(R.id.recyclerParenting);
        recyclerFLanguage = root.findViewById(R.id.recyclerFLanguage);
        recyclerChildren = root.findViewById(R.id.recyclerChildren);

        //Button
        btnMoreFlash = root.findViewById(R.id.btnMoreFlash);
        btnMoreLiterary = root.findViewById(R.id.btnMoreLiterary);
        btnMoreEconomy = root.findViewById(R.id.btnMoreEconomy);
        btnMoreMentality = root.findViewById(R.id.btnMoreMentality);
        btnMoreParenting = root.findViewById(R.id.btnMoreParenting);
        btnMoreFLanguage = root.findViewById(R.id.btnMoreFLanguage);
        btnMoreChildren = root.findViewById(R.id.btnMoreChildren);
    }

    public static void loadData(String s, List<Book> listBook, HomeBookAdapter adapter, boolean getTopFive) {
        Query query = MainActivity.mData.child(s);
        if (getTopFive)
            query = MainActivity.mData.child(s).limitToFirst(5);

        query.addChildEventListener(new ChildEventListener() {
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

    private void setUpRecyclerView(RecyclerView recyclerView, HomeBookAdapter adapter){
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void catchViewEventListener() {
        btnMoreFlash.setOnClickListener(v -> {
            Intent allIntent = new Intent(getContext(), MoreBookActivity.class);
            allIntent.putExtra("Key", "FD");
            allIntent.putExtra("CategoryName", "Flash Deal");
            startActivity(allIntent);
        });

        btnMoreLiterary.setOnClickListener(v -> {
            Intent allIntent = new Intent(getContext(), MoreBookActivity.class);
            allIntent.putExtra("Key", "VanHoc");
            allIntent.putExtra("CategoryName", "Văn Học");
            startActivity(allIntent);
        });

        btnMoreEconomy.setOnClickListener(v -> {
            Intent allIntent = new Intent(getContext(), MoreBookActivity.class);
            allIntent.putExtra("Key", "KinhTe");
            allIntent.putExtra("CategoryName", "Kinh Tế");
            startActivity(allIntent);
        });

        btnMoreMentality.setOnClickListener(v -> {
            Intent allIntent = new Intent(getContext(), MoreBookActivity.class);
            allIntent.putExtra("Key", "TamLy");
            allIntent.putExtra("CategoryName", "Tâm Lý");
            startActivity(allIntent);
        });

        btnMoreParenting.setOnClickListener(v -> {
            Intent allIntent = new Intent(getContext(), MoreBookActivity.class);
            allIntent.putExtra("Key", "NuoiDayCon");
            allIntent.putExtra("CategoryName", "Nuôi Dạy Con");
            startActivity(allIntent);
        });

        btnMoreFLanguage.setOnClickListener(v -> {
            Intent allIntent = new Intent(getContext(), MoreBookActivity.class);
            allIntent.putExtra("Key", "NgoaiNgu");
            allIntent.putExtra("CategoryName", "Ngoại Ngữ");
            startActivity(allIntent);
        });

        btnMoreChildren.setOnClickListener(v -> {
            Intent allIntent = new Intent(getContext(), MoreBookActivity.class);
            allIntent.putExtra("Key", "ThieuNhi");
            allIntent.putExtra("CategoryName", "Thiếu Nhi");
            startActivity(allIntent);
        });
    }

    private void getFlashDealBook() {
        MainActivity.mData.child("TrinhTham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }
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
        MainActivity.mData.child("VanHoc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }

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
        MainActivity.mData.child("KinhTe").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }
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
        MainActivity.mData.child("TamLy").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }
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
        MainActivity.mData.child("NuoiDayCon").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }
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
        MainActivity.mData.child("NgoaiNgu").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }
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
        MainActivity.mData.child("ThieuNhi").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }
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
        MainActivity.mData.child("KinhDi").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }
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
        MainActivity.mData.child("TieuThuyet").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setBookID(dataSnapshot.getKey());
                if(book.getSale() != 0) {
                    if (flashdealList.size() < 5) {
                        flashdealList.add(book);
                        adapter1.notifyDataSetChanged();
                    }
                    MainActivity.allFlashDealBookList.add(book);
                }
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
}
