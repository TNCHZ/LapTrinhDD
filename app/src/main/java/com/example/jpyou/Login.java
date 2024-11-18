package com.example.jpyou;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
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
    private MyDatabaseHelper db;
    private TextView username, password;
    private int taiKhoanID;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_role, menu);
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

        // Initialize MyDatabaseHelper
        db = new MyDatabaseHelper(this);

        // Button for Role selection
        btnRole = findViewById(R.id.btnRole);
        registerForContextMenu(btnRole);

        // Set role logic
        role();

        // Password visibility toggle
        txtPass = findViewById(R.id.txtMatKhau_User);
        checkShowPassword = findViewById(R.id.checkPassword_User);
        showHintPassword();

        // Button actions for registration and login
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
            showOptionsDialog();
        } else if (buttonID_role_User == rolePatient) {
            btnRole.setText("Bệnh nhân");
        } else
            btnRole.setText("Bệnh nhân");
    }

    private void showHintPassword() {
        // Toggle show/hide password
        checkShowPassword.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                txtPass.setTransformationMethod(null);
            } else {
                txtPass.setTransformationMethod(new PasswordTransformationMethod());
                txtPass.setSelection(txtPass.getText().length());
            }
        });
    }

    private void convert() {
        username = findViewById(R.id.txtTaiKhoan_User);
        password = findViewById(R.id.txtMatKhau_User);


        // Registration button click listener
        btnDangKy.setOnClickListener(view -> {
            Intent intent_dki = new Intent(Login.this, form_dang_ky.class);
            startActivity(intent_dki);
        });

        // Login button click listener
        btnDangNhap.setOnClickListener(view -> {
            taiKhoanID = Integer.parseInt(db.verifyPassword(username.getText().toString(),
                    password.getText().toString(), btnRole.getText().toString()));
            if (taiKhoanID != -1) {
                if ("Bệnh nhân".equals(btnRole.getText().toString())) {
                    Intent intent_user = new Intent(Login.this, UserInterface.class);
                    intent_user.putExtra("TaiKhoanID", String.valueOf(taiKhoanID));
                    startActivity(intent_user);
                } else if ("Bác sĩ".equals(btnRole.getText().toString())) {
                    Intent intent_doctor = new Intent(Login.this, DoctorInterface.class);
                    intent_doctor.putExtra("TaiKhoanID", String.valueOf(taiKhoanID));
                    startActivity(intent_doctor);
                } else {
                    Intent intent_doctor = new Intent(Login.this, NurseInterface.class);
                    startActivity(intent_doctor);
                }
            } else {
                Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn là:");
        builder.setItems(new CharSequence[]{"Bác sĩ", "Y tá", "Admin"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        btnRole.setText("Bác sĩ");
                        break;
                    case 1:
                        btnRole.setText("Y tá");
                        break;
                    case 2: {
                        Intent intent = new Intent(Login.this, AdminLogin.class);
                        startActivity(intent);
                    }
                }
            }
        });
        builder.show();
    }

}

