package com.example.ltdd_th71_nhom5.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.ui.category.CategoryViewModel;

import java.util.Observer;

public class HomeFragment extends Fragment {
    ViewFlipper vfHome;
    Animation in, out;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        vfHome = root.findViewById(R.id.vfHome);

        in = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(root.getContext(), R.anim.fade_out);
        vfHome.setInAnimation(in);
        vfHome.setOutAnimation(out);
        vfHome.setAutoStart(true);

        return root;
    }
}
