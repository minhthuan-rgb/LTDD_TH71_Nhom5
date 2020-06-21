package com.example.ltdd_th71_nhom5;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalInfoActivity extends AppCompatActivity {
    CheckBox checkBox;
    Button btnLuu;
    EditText edtMKCu,edtMKMoi,edtAuthMK, editTextHoTen_Personal, editTextSDT_Personal, editTextDiaChi_Personal;
    TextView txtEmail_Personal;
    LinearLayout linearChangePW;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        mapView();

        catchViewEventListener();

        editTextHoTen_Personal.setText(MainActivity.currentUser.getUserName());
        editTextDiaChi_Personal.setText(MainActivity.currentUser.getDiaChi());
        if (MainActivity.currentUser.getPhone() != null)
            editTextSDT_Personal.setText( "0" + MainActivity.currentUser.getPhone().substring(3));
        txtEmail_Personal.setText(MainActivity.currentUser.getId());
    }

    private void catchViewEventListener() {
        btnLuu.setOnClickListener(v -> {
            if(checkEditText(editTextHoTen_Personal) && checkEditText(editTextSDT_Personal) && checkEditText(editTextDiaChi_Personal)) {
                if (checkBox.isChecked()) {
                    if (!edtMKCu.getText().toString().equals(MainActivity.currentUser.getPassWord()))
                        Toast.makeText(getApplicationContext(), "Mật khẩu cũ không chính xác!", Toast.LENGTH_LONG).show();
                    else if (!edtMKMoi.getText().toString().equals(edtAuthMK.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Xác nhận mật khẩu mới không trùng khớp!", Toast.LENGTH_LONG).show();
                    else if (edtMKCu.getText().toString().equals(edtMKMoi.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Mật khẩu mới trùng với mật khẩu cũ!", Toast.LENGTH_LONG).show();
                    else {
                        MainActivity.mData.child("TaiKhoan").child(MainActivity.currentUser.getUserId())
                                .child("passWord").setValue(edtMKMoi.getText().toString());
                        MainActivity.currentUser.setPassWord(edtMKMoi.getText().toString());
                        MainActivity.mData.child("TaiKhoan").child(MainActivity.currentUser.getUserId())
                                .child("userName").setValue(editTextHoTen_Personal.getText().toString());
                        MainActivity.mData.child("TaiKhoan").child(MainActivity.currentUser.getUserId())
                                .child("diaChi").setValue(editTextDiaChi_Personal.getText().toString());
                        MainActivity.mData.child("TaiKhoan").child(MainActivity.currentUser.getUserId())
                                .child("phone").setValue("+84" + editTextSDT_Personal.getText().toString().substring(1));
                    }
                } else {
                    MainActivity.mData.child("TaiKhoan").child(MainActivity.currentUser.getUserId())
                            .child("userName").setValue(editTextHoTen_Personal.getText().toString());
                    MainActivity.mData.child("TaiKhoan").child(MainActivity.currentUser.getUserId())
                            .child("diaChi").setValue(editTextDiaChi_Personal.getText().toString());
                    MainActivity.mData.child("TaiKhoan").child(MainActivity.currentUser.getUserId())
                            .child("phone").setValue("+84" + editTextSDT_Personal.getText().toString().substring(1));
                }
            }
        });

        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (checkBox.isChecked())
                linearChangePW.setVisibility(View.VISIBLE);
            else
                linearChangePW.setVisibility(View.INVISIBLE);
        });
    }


    private void mapView(){
        checkBox = findViewById(R.id.checkBoxDoiMatKhau);
        btnLuu = findViewById(R.id.btnLuu_Personal);
        edtMKCu = findViewById(R.id.editTextMatKhauCu_Personal);
        edtMKMoi = findViewById(R.id.editTextMatKhauMoi_Personal);
        edtAuthMK = findViewById(R.id.editTextAuthMKMoi_Personal);
        editTextDiaChi_Personal = findViewById(R.id.editTextDiaChi_Personal);
        editTextHoTen_Personal = findViewById(R.id.editTextHoTen_Personal);
        editTextSDT_Personal = findViewById(R.id.editTextSDT_Personal);
        txtEmail_Personal = findViewById(R.id.txtEmail_Personal);
    }
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}