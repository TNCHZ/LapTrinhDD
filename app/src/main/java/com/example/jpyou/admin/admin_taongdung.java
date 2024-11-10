package com.example.jpyou.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jpyou.MyDatabaseHelper;
import com.example.jpyou.R;

public class admin_taongdung extends AppCompatActivity {

    private TextView txtName, txtNamSinh, txtDiaChi, txtCCCD, txtSDT, txtEmail, txtChuyenKhoa;
    private RadioButton rdNam;
    private String gioiTinh;
    private Button bt;
    private Spinner sp;
    private String chucVu;
    private String[] roles = {"Bác sĩ", "Y tá"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_taongdung);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sp = findViewById(R.id.spinner_role);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);

// Thiết lập layout khi mục được chọn
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Gán adapter cho Spinner
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chucVu = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        txtName = findViewById(R.id.txtName);
        txtNamSinh = findViewById(R.id.txtNamSinh);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtCCCD = findViewById(R.id.txtCCCD);
        txtSDT = findViewById(R.id.txtSDT);
        txtEmail = findViewById(R.id.txtEmail);
        txtChuyenKhoa = findViewById(R.id.txtChuyenKhoa);
        rdNam = findViewById(R.id.rdNam);
        bt = findViewById(R.id.btn_dangKiAdmin);


        gioiTinh = rdNam.isChecked() ? "Nam" : "Nữ";


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper mydb = new MyDatabaseHelper(admin_taongdung.this);
                String name = txtName.getText().toString();
                String namSinh = txtNamSinh.getText().toString();
                String diaChi = txtDiaChi.getText().toString();
                String cccd = txtCCCD.getText().toString();
                String sdt = txtSDT.getText().toString();
                String email = txtEmail.getText().toString();
                String chuyenKhoa = txtChuyenKhoa.getText().toString();


                mydb.addNguoiDung(
                        name,
                        gioiTinh,
                        namSinh,
                        diaChi,
                        email,
                        sdt,
                        cccd,
                        chuyenKhoa,
                        chucVu
                );
            }
        });

    }
}
