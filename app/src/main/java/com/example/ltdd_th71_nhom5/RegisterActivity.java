package com.example.ltdd_th71_nhom5;

import androidx.appcompat.app.AppCompatActivity;

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
                MainActivity.mData.child("TaiKhoan").setValue(new User(null, txtHoTen.getText().toString(), txtDiaChi.getText().toString(), "+84" + txtSoDienThoai.getText().toString(), txtEmail.getText().toString(), txtMatKhau.getText().toString(), null));
                Toast.makeText(RegisterActivity.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
            }
        });
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