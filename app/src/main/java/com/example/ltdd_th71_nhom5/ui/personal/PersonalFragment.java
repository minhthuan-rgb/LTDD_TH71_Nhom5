package com.example.ltdd_th71_nhom5.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ltdd_th71_nhom5.LoginActivity;
import com.example.ltdd_th71_nhom5.R;

public class PersonalFragment extends Fragment {
    ImageView imgLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal_tool, container, false);
        imgLogin = root.findViewById(R.id.imgLogin);

        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(root.getContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });
        return root;
    }
}
