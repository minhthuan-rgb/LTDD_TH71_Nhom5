package com.example.ltdd_th71_nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ltdd_th71_nhom5.model.User;

public class RegisterActivity extends AppCompatActivity {
    EditText txtHoTen, txtMatKhau,txtEmail,txtSoDienThoai,txtDiaChi;
    Button btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registers);
        mapView();
        catchViewClickEvent();
    }
    private void catchViewClickEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEditText(txtHoTen) && checkEditText(txtSoDienThoai) && isValidEmail(txtEmail.getText().toString()) && checkEditText(txtMatKhau) && checkEditText(txtDiaChi)) {
                    if (isUniqueEmail(txtEmail)){
                        MainActivity.mData.child("TaiKhoan").push().setValue(new User("", txtHoTen.getText().toString(), txtDiaChi.getText().toString(), "+84" + txtSoDienThoai.getText().toString().substring(1), txtEmail.getText().toString(), txtMatKhau.getText().toString(), null));
                        Toast.makeText(RegisterActivity.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }
            }
        });
    }

    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            return true;
        else {
            txtEmail.setError("Email sai định dạng!");
        }
        return false;
    }

    private boolean isUniqueEmail(EditText edtText) {
        for (int i = 0; i < MainActivity.listUser.size(); i++) {
            if (edtText.getText().toString().equals(MainActivity.listUser.get(i).getId())) {
                edtText.setError("Email đã tồn tại!");
                return false;
            }
        }
        return true;
    }

        private void mapView() {
        txtHoTen = findViewById(R.id.editTextHoTen);
        txtMatKhau = findViewById(R.id.editTextMatKhau);
        btnDangKy = findViewById(R.id.btnDangKy);
        txtEmail = findViewById(R.id.editTextEmail);
        txtSoDienThoai = findViewById(R.id.editTextSoDienThoai);
        txtDiaChi = findViewById(R.id.editTextDiaChi);
    }
}