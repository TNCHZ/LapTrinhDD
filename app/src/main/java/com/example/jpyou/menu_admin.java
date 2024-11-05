package com.example.jpyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menu_admin extends AppCompatActivity {

    private Button btn_taoNgDung, btn_listNgDung, btn_listNhVien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_taoNgDung = findViewById(R.id.btn_taoNgDung);
        btn_listNgDung = findViewById(R.id.btn_listNgDung);
        btn_listNhVien = findViewById(R.id.btn_listNhanVien);

        btn_taoNgDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_admin.this, admin_taongdung.class);
                startActivity(intent);
            }
        });




    }

}
