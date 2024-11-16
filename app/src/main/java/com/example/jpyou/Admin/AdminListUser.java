package com.example.jpyou.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jpyou.MyDatabaseHelper;
import com.example.jpyou.R;
import com.example.jpyou.User.userinform;

import java.util.List;

public class AdminListUser extends AppCompatActivity {
    private ListView lv;
    private MyDatabaseHelper db;
    private List<String> users;
    private String vaitro;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_list_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.list_ngDung);
        db = new MyDatabaseHelper(this); // Initialize the database helper
        vaitro = getIntent().getStringExtra("vaitro");

        refreshUserList();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedUser = users.get(position);
                showOptionsDialog(selectedUser, position);
            }
        });
    }

    private void showOptionsDialog(String selectedUser, int position) {
        db = new MyDatabaseHelper(this); // Initialize the database helper

        switch (vaitro) {
            case "Bệnh nhân":
            case "Nhân viên":
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Chọn việc cần làm với người dùng này");
                builder.setItems(new CharSequence[]{"Dừng hoạt động", "Cập nhật thông tin"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: {
                                String result = selectedUser.substring(0, selectedUser.indexOf(" "));
                                db.HoatDong(Integer.parseInt(result), 0);
                                refreshUserList();
                            }
                            break;
                            case 1: // Update User

                                break;
                        }
                    }
                });
                builder.show();
            }
                break;
            default: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Mở trạng thái họat động cho tài khoản");
                builder.setItems(new CharSequence[]{"Mở", "Đóng"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: {
                                String result = selectedUser.substring(0, selectedUser.indexOf(" "));
                                db.HoatDong(Integer.parseInt(result), 1);
                                refreshUserList();
                            }
                            break;
                            case 1:
                                break;
                        }
                    }
                });
                builder.show();
            }
                break;
        }

    }
    private void refreshUserList() {
        switch (vaitro) {
            case "Bệnh nhân":
                users = db.dsNguoiDung("Bệnh nhân");
                break;
            case "Nhân viên":
                users = db.dsNguoiDung();
                break;
            default:
                users = db.dsNguoiKhongHD();
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        lv.setAdapter(adapter); // Update the ListView with new data
    }

}
