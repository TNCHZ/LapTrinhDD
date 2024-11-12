package com.example.jpyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChooseRoles extends AppCompatActivity {

    private ImageButton btn_Staff;
    private ImageButton btn_Patient;
    private int buttonID_role_ChooseRoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.choose_roles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        btn_Staff = findViewById(R.id.btnRole_Staff);
        btn_Patient = findViewById(R.id.btnRole_Patient);
        Intent intent = new Intent(ChooseRoles.this, UserLogin.class);


        btn_Staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonID_role_ChooseRoles = R.id.btnRole_Staff;
                intent.putExtra("role", buttonID_role_ChooseRoles);
                startActivity(intent);
            }
        });

        btn_Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonID_role_ChooseRoles = R.id.btnRole_Patient;
                intent.putExtra("role", buttonID_role_ChooseRoles);
                startActivity(intent);
            }
        });
    }
}