package com.example.ltdd_th71_nhom5.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ltdd_th71_nhom5.R;

public class PersonalFragment extends Fragment {

    private PersonalViewModel personnalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal_tool, container, false);
        return root;
    }
}
