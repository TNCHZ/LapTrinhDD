package com.example.jpyou;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login_nguoidung extends AppCompatActivity {
    private Button btn_DangKy, btn_DangNhap;
    private EditText txt_Pass;
    private CheckBox check_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_nguoidung);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// CheckBox -> Show Password
        txt_Pass = findViewById(R.id.txtMatKhau_NguoiDung);//lấy id EditText Password
        check_show = findViewById(R.id.showPassword);//lấy id CheckBox Show
        //Hàm Show/Hint Password
        check_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    txt_Pass.setTransformationMethod(null);
                }else{
                    txt_Pass.setTransformationMethod(new PasswordTransformationMethod());
                    txt_Pass.setSelection(txt_Pass.getText().length());
                }
            }
        });


        btn_DangKy = findViewById(R.id.btnDangKy_NguoiDung);
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_nguoidung.this, form_dangki.class);
                startActivity(intent);
            }
        });

        btn_DangNhap = findViewById(R.id.btnLogin_NguoiDung);
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_nguoidung.this, interface_nguoidung.class);
                startActivity(intent);
            }
        });
    }
}