package com.example.jpyou;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class admin_taongdung extends AppCompatActivity {

    TextView txtName, txtNamSinh, txtDiaChi, txtCCCD, txtSDT, txtEmail, txtChucVu;
    RadioButton rdNam;
    String gioiTinh;
    Button bt;

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
        txtName = findViewById(R.id.txtName);
        txtNamSinh = findViewById(R.id.txtNamSinh);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtCCCD = findViewById(R.id.txtCCCD);
        txtSDT = findViewById(R.id.txtSDT);
        txtEmail = findViewById(R.id.txtEmail);
        txtChucVu = findViewById(R.id.txtChucVu);
        rdNam = findViewById(R.id.rdNam);
        bt = findViewById(R.id.btn_dangKiAdmin);

        gioiTinh = rdNam.isChecked() ? "Male" : "Female";

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
                String chucVu = txtChucVu.getText().toString();
                int chucVuID = mydb.getVaitroID(chucVu);

                mydb.addNguoiDung(
                        name,
                        gioiTinh,
                        namSinh,
                        email,
                        sdt,
                        cccd,
                        sdt,  // Replace with actual username
                        cccd,  // Replace with actual password
                        chucVuID  // Assuming ChucVu holds a numeric role ID
                );
            }
        });

    }
}
