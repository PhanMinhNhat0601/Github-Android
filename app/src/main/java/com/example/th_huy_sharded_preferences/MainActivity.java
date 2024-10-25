package com.example.th_huy_sharded_preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    CheckBox rememberPass;
    Button login;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Anhxa();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        username.setText(sharedPreferences.getString("taikhoan",""));
        password.setText(sharedPreferences.getString("matkhau",""));
        rememberPass.setChecked(sharedPreferences.getBoolean("Checked", false));

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (name.equals("Honda") && pass.equals("120000")) {
                    Toast.makeText(MainActivity.this, "Nhập thông tin thành công", Toast.LENGTH_SHORT).show();
                    //nếu có check
                    if (rememberPass.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", name);
                        editor.putString("matkhau", pass);
                        editor.putBoolean("Checked", true);
                        editor.commit();
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("Checked");
                        editor.commit();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Lỗi nhập thông tin!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Anhxa() {
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        rememberPass = findViewById(R.id.cb_remeberPass);
        login = findViewById(R.id.btn_login);
    }
}