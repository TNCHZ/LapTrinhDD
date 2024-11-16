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

import com.example.jpyou.Admin.AdminLogin;
import com.example.jpyou.Doctor.DoctorInterface;
import com.example.jpyou.Nurse.NurseInterface;
import com.example.jpyou.User.UserInterface;

public class Login extends AppCompatActivity {
    private Button btnDangKy, btnDangNhap;
    private EditText txtPass;
    private CheckBox checkShowPassword;
    private Button btnRole;

    //tạo và lấy menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        btnRole = findViewById(R.id.btnRole);
        if (btnRole.getText() != "Bệnh nhân") {
            getMenuInflater().inflate(R.menu.context_menu_role, menu);
            menu.setHeaderTitle("Chọn chức vụ");
            menu.setHeaderIcon(R.drawable.ic_person);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        btnRole = findViewById(R.id.btnRole);
        if (btnRole.getText() != "Bệnh nhân") {
            if (item.getItemId() == R.id.role_doctor) {
                btnRole.setText("Bác sĩ");
                Toast.makeText(this, "Bác sĩ", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.role_nurse) {
                btnRole.setText("Y tá");
                Toast.makeText(this, "Y tá", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.role_admin) {
                btnRole.setText("Admin");
                Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();
                Intent intent_AdminLogin = new Intent(Login.this, AdminLogin.class);
                startActivity(intent_AdminLogin);
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //thiết lập button lấy menu
        btnRole = findViewById(R.id.btnRole);
        registerForContextMenu(btnRole);

        //thiết lập role
        role();


        //Ẩn/Hiện Password
        txtPass = findViewById(R.id.txtMatKhau_User);//lấy id EditText Password
        checkShowPassword = findViewById(R.id.checkPassword_User);//lấy id CheckBox Show
        showHintPassword();

        //Chuyển layout
        btnDangKy = findViewById(R.id.btnDangKy_User);
        btnDangNhap = findViewById(R.id.btnLogin_User);
        convert();
    }

    private void role() {
        Intent give = getIntent();
        int buttonID_role_User = give.getIntExtra("role", -1);
        int roleStaff = R.id.btnRole_Staff;
        int rolePatient = R.id.btnRole_Patient;

        if (buttonID_role_User == roleStaff) {
            btnRole.setText("Bác sĩ");
            btnDangKy = findViewById(R.id.btnDangKy_User);
            btnDangKy.setVisibility(View.GONE);
        }
        if (buttonID_role_User == rolePatient) {
            btnRole.setText("Bệnh nhân");
        }
    }

    private void showHintPassword() {
        //Hàm Show/Hint Password
        checkShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    txtPass.setTransformationMethod(null);
                } else {
                    txtPass.setTransformationMethod(new PasswordTransformationMethod());
                    txtPass.setSelection(txtPass.getText().length());
                }
            }
        });
    }

    private void convert() {
        //Chuyển  layout from_dang_ky
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_dki = new Intent(Login.this, form_dang_ky.class);
                startActivity(intent_dki);
            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRole = findViewById(R.id.btnRole);
                String text = btnRole.getText().toString();
                switch (text){
//                    case "Bệnh nhân":
//                        Intent intent_user = new Intent(Login.this, UserInterface.class);
//                        startActivity(intent_user);
//                        break;
                    case "Bác sĩ":
                        Intent intent_doctor = new Intent(Login.this, DoctorInterface.class);
                        startActivity(intent_doctor);
                        break;
                    case "Y tá":
                        Intent intent_nurse = new Intent(Login.this, NurseInterface.class);
                        startActivity(intent_nurse);
                        break;
                    default:
                        Intent intent_user = new Intent(Login.this, UserInterface.class);
                        startActivity(intent_user);
                        break;
                }
            }
        });
    }
}