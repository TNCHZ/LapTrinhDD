package com.example.jpyou;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class giaodien_nguoidung extends AppCompatActivity {
    private EditText pass;
    private CheckBox check_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.giaodien_ngdung);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// CheckBox -> Show Password
        pass = findViewById(R.id.editTextTextPassword);//lấy id EditText Password
        check_show = findViewById(R.id.checkBox);//lấy id CheckBox Show
        //Hàm Show/Hint Password
        check_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    pass.setTransformationMethod(null);
                }else{
                    pass.setTransformationMethod(new PasswordTransformationMethod());
                    pass.setSelection(pass.getText().length());
                }
            }
        });

    }
}