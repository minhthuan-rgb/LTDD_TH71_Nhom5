package com.example.ltdd_th71_nhom5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfoActivity extends AppCompatActivity {
    CheckBox checkBox;
    Button btnLuu;
    EditText edtMKCu,edtMKMoi,edtAuthMK;
    TextView txtMKCu,txtMKMoi,txtAuthMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        mapView();
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked())
                {
                    edtMKCu.setVisibility(View.VISIBLE);
                    edtMKMoi.setVisibility(View.VISIBLE);
                    edtAuthMK.setVisibility(View.VISIBLE);
                    txtAuthMK.setVisibility(View.VISIBLE);
                    txtMKCu.setVisibility(View.VISIBLE);
                    txtMKMoi.setVisibility(View.VISIBLE);
                }
                else
                {
                    edtAuthMK.setVisibility(View.INVISIBLE);
                    edtMKMoi.setVisibility(View.INVISIBLE);
                    edtMKCu.setVisibility(View.INVISIBLE);
                    txtMKMoi.setVisibility(View.INVISIBLE);
                    txtMKCu.setVisibility(View.INVISIBLE);
                    txtAuthMK.setVisibility(View.INVISIBLE);
                }
            }
        });
    }



    private void mapView(){
        checkBox = findViewById(R.id.checkBoxDoiMatKhau);
        btnLuu = findViewById(R.id.btnLuu_Personal);
        edtMKCu = findViewById(R.id.editTextMatKhauCu_Personal);
        edtMKMoi = findViewById(R.id.editTextMatKhauMoi_Personal);
        edtAuthMK = findViewById(R.id.editTextAuthMKMoi_Personal);
        txtMKCu = findViewById(R.id.textViewMKCu_Personal);
        txtAuthMK = findViewById(R.id.textViewAuthMK_Personal);
        txtMKMoi = findViewById(R.id.textViewMKMoi_Personal);
    }
}