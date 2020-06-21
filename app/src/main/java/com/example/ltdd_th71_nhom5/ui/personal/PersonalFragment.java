package com.example.ltdd_th71_nhom5.ui.personal;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ltdd_th71_nhom5.BookManagementActivity;
import com.example.ltdd_th71_nhom5.LoginActivity;
import com.example.ltdd_th71_nhom5.MainActivity;
import com.example.ltdd_th71_nhom5.OrderManagementActivity;
import com.example.ltdd_th71_nhom5.R;
import com.example.ltdd_th71_nhom5.model.Book;
import com.example.ltdd_th71_nhom5.model.User;

public class PersonalFragment extends Fragment {
    ImageView imgLogin;
    LinearLayout isLogin;
    TextView txtUserName, txtEmail, txtSignOut, txtOrderManagement, txtBookManagement;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal_tool, container, false);

        mapView(root);

        checkIsLogin();

        catchViewEventListener();

        return root;
    }

    private void catchViewEventListener() {
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                loginIntent.putExtra("Activity", "Person");
                startActivity(loginIntent);
            }
        });

        txtSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Đã đăng xuất tài khoản " + MainActivity.currentUser.getId(), Toast.LENGTH_SHORT).show();
                MainActivity.isLogin = false;
                MainActivity.currentUser = new User();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        txtOrderManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrderManagementActivity.class);
                startActivity(intent);
            }
        });

        txtBookManagement.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BookManagementActivity.class);
                startActivity(intent);
            }
        }));
    }

    private void checkIsLogin() {
        if (MainActivity.isLogin){
            imgLogin.setVisibility(View.INVISIBLE);
            isLogin.setVisibility(View.VISIBLE);
            txtUserName.setText(MainActivity.currentUser.getUserName());
            txtEmail.setText(MainActivity.currentUser.getId());
        }
        else{
            imgLogin.setVisibility(View.VISIBLE);
            isLogin.setVisibility(View.INVISIBLE);
        }
    }

    private void mapView(View root) {
        imgLogin = root.findViewById(R.id.imgLogin);
        isLogin = root.findViewById(R.id.isLogin);
        txtUserName = root.findViewById(R.id.txtUserName);
        txtEmail = root.findViewById(R.id.txtEmail);
        txtSignOut = root.findViewById(R.id.txtSignOut);
        txtSignOut.setPaintFlags(txtSignOut.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtOrderManagement = root.findViewById(R.id.txtOrderManagement);
        txtBookManagement = root.findViewById(R.id.txtBookManagement);
    }
}
