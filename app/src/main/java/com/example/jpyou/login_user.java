package com.example.jpyou;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login_user extends AppCompatActivity {
    private Button btn_DangKy, btn_DangNhap;
    private EditText txt_Pass;
    private CheckBox check_show;
    private Button btn_Role;

    //tạo và lấy menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_role, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        btn_Role = findViewById(R.id.btnRole);
        if(item.getItemId() == R.id.role_staff){
            btn_Role.setText("Nhân viên");
            Toast.makeText(this, "Nhân viên", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.role_patient){
            btn_Role.setText("Bệnh nhân");
            Toast.makeText(this, "Bệnh nhân", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //thiết lập button lấy menu
        btn_Role = findViewById(R.id.btnRole);
        registerForContextMenu(btn_Role);

        //Ẩn/Hiện Password
        showHintPassword();

        //Chuyển layout
        convert();
    }

    private void showHintPassword() {
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
    }

    private void convert() {
        btn_DangKy = findViewById(R.id.btnDangKy_NguoiDung);
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_user.this, form_dangki.class);
                startActivity(intent);
            }
        });

        btn_DangNhap = findViewById(R.id.btnLogin_NguoiDung);
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_user.this, interface_nguoidung.class);
                startActivity(intent);
            }
        });
    }
}