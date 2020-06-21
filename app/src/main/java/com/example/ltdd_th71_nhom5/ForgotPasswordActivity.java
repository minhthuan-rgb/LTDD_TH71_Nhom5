package com.example.ltdd_th71_nhom5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText txtContact;
    TextView txtContactSended;
    Button btnGetPW, btnContinue;
    RelativeLayout layoutContact;
    CoordinatorLayout layoutSended;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //actionbar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setTitle("Quên mật khẩu");
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mapView();

        //init channel
        creataNotificationChannel();

        catchButtonClickEvent();
    }

    private void creataNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Channel-001", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void catchButtonClickEvent() {
        btnGetPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean exists = false;
                for(int i = 0; i < MainActivity.listUser.size(); i++){
                    if(txtContact.getText().toString().equals(MainActivity.listUser.get(i).getId())) {
                        addNotification(MainActivity.listUser.get(i).getUserId(), i);
                        txtContactSended.setText(
                                String.format("Mật khẩu đã được gửi đến Email %s. Vui lòng kiểm tra thông báo để cập nhật thông tin.",
                                        txtContact.getText()));
                        layoutContact.setVisibility(View.INVISIBLE);
                        layoutSended.setVisibility(View.VISIBLE);
                        exists = true;
                        actionBar.setDisplayHomeAsUpEnabled(false);
                        actionBar.setDisplayShowTitleEnabled(false);
                        break;
                    }
                }
                if (!exists)
                    Toast.makeText(ForgotPasswordActivity.this, "Email không tồn tại.", Toast.LENGTH_LONG).show();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    private void addNotification(String key, int position) {
        Intent forgotPWIntent = new Intent(this, ForgotPasswordActivity2.class);
        forgotPWIntent.putExtra("Key", key);
        forgotPWIntent.putExtra("Position", position);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, forgotPWIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Channel-001")
                .setSmallIcon(R.drawable.people_2)
                .setContentTitle("Parzival's Store")
                .setContentText("Nhấp vào đây để hoàn tất yêu cầu quên mật khẩu")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    private void mapView() {
        txtContact = findViewById(R.id.txtContact);
        btnGetPW = findViewById(R.id.btnGetPW);
        btnContinue = findViewById(R.id.btnContinue);
        txtContactSended = findViewById(R.id.txtContactSended);
        layoutContact = findViewById(R.id.layoutContact);
        layoutSended = findViewById(R.id.layoutSended);
        layoutContact.setVisibility(View.VISIBLE);
        layoutSended.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}