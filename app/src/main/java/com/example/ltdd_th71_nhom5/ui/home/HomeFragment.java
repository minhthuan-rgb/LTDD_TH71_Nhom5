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
import com.example.ltdd_th71_nhom5.adapter.HomeBookAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements  HomeBookAdapter.ItemClickListener{
    ViewFlipper vfHome;
    Animation in, out;
    RecyclerView recyclerFlash, recyclerLove, recyclerDetective, recyclerHorror, recyclerAdventure, recyclerNovel;
    //  HomeBookAdapter adapterSale, adapterNull;

    ArrayList<Integer> imgID = new ArrayList<>();
    ArrayList<String> textSales = new ArrayList<>();
    ArrayList<String> nullText = new ArrayList<>();

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        vfHome = root.findViewById(R.id.vfHome);
        recyclerFlash = root.findViewById(R.id.recyclerFlash);

        in = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_out);

        vfHome.setInAnimation(in);
        vfHome.setOutAnimation(out);
        vfHome.setAutoStart(true);

        createList();
        //set up RecyclerView
        recyclerFlash = root.findViewById((R.id.recyclerFlash));
        recyclerDetective = root.findViewById(R.id.recyclerDetective);
        recyclerLove = root.findViewById(R.id.recyclerLove);
        recyclerHorror = root.findViewById(R.id.recyclerHorror);
        recyclerAdventure = root.findViewById(R.id.recyclerAdventure);
        recyclerNovel = root.findViewById(R.id.recyclerNovel);

        setUpRecyclerView(root.getContext(),textSales,imgID,recyclerFlash);
        setUpRecyclerView(root.getContext(),nullText,imgID,recyclerDetective);
        setUpRecyclerView(root.getContext(),nullText,imgID,recyclerLove);
        setUpRecyclerView(root.getContext(),nullText,imgID,recyclerHorror);
        setUpRecyclerView(root.getContext(),nullText,imgID,recyclerAdventure);
        setUpRecyclerView(root.getContext(),nullText,imgID,recyclerNovel);

        return root;
    }

    public void createList(){
        imgID.add(R.drawable.vf_1);
        imgID.add(R.drawable.vf_2);
        imgID.add(R.drawable.vf_3);
        imgID.add(R.drawable.vf_4);
        imgID.add(R.drawable.vf_5);
        imgID.add(R.drawable.vf_6);
        imgID.add(R.drawable.vf_7);
        imgID.add(R.drawable.vf_8);

        textSales.add("50%");
        textSales.add("20%");
        textSales.add("10%");
        textSales.add("30%");
        textSales.add("40%");
        textSales.add("60%");
        textSales.add("70%");
        textSales.add("80%");

        for (int i = 0; i < imgID.size(); i++)
            nullText.add("");
    }

    public void setUpRecyclerView(Context context, List<String> textSales, List<Integer> imgID, RecyclerView recyclerView){
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        HomeBookAdapter adapter = new HomeBookAdapter(context, textSales, imgID);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this.getContext() ,"You clicked item position " + position, Toast.LENGTH_LONG).show();
    }
}
