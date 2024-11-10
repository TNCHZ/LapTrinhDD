package com.example.jpyou;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "phongmach.db";
    public static final int DATABASE_VERSION = 1;



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng ThongTinNguoiDung
        String createThongTinNguoiDungTable = "CREATE TABLE ThongTinNguoiDung (" +
                "nguoidungID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenNguoiDung TEXT NOT NULL, " +
                "gioiTinh TEXT, " +
                "ngaySinh TEXT, " +
                "diaChi TEXT, " +
                "email TEXT UNIQUE, " +
                "soDT TEXT UNIQUE, " +
                "CCCD TEXT UNIQUE);";

        // Tạo bảng NguoiDung
        String createNguoiDungTable = "CREATE TABLE NguoiDung (" +
                "nguoidungID INTEGER PRIMARY KEY, " +
                "username TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "active INTEGER NOT NULL, " +
                "createdDay TEXT NOT NULL, " +
                "chucVu TEXT, " +
                "FOREIGN KEY(nguoidungID) REFERENCES ThongTinNguoiDung(nguoidungID));";


        // Tạo bảng BacSi
        String createBacSiTable = "CREATE TABLE BacSi (" +
                "nguoidungID INTEGER PRIMARY KEY, " +
                "chuyenKhoa TEXT NOT NULL, " +
                "FOREIGN KEY(nguoidungID) REFERENCES NguoiDung(nguoidungID));";

        String createYTaTable = "CREATE TABLE YTa (" +
                "nguoidungID INTEGER PRIMARY KEY, " +
                "chuyenKhoa TEXT NOT NULL, " +
                "FOREIGN KEY(nguoidungID) REFERENCES NguoiDung(nguoidungID));";

        String createBenhNhanTable = "CREATE TABLE BenhNhan (" +
                "nguoidungID INTEGER PRIMARY KEY, " +
                "FOREIGN KEY(nguoidungID) REFERENCES NguoiDung(nguoidungID));";

        // Tạo bảng LichHen
        String createLichHenTable = "CREATE TABLE LichHen (" +
                "lichhenID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "bacsiID INTEGER, " +
                "benhnhanID INTEGER, " +
                "ngayGioKham TEXT NOT NULL, " +
                "soThuTuKham INTEGER, " +
                "tinhTrangHen TEXT, " +
                "FOREIGN KEY(bacsiID) REFERENCES BacSi(nguoidungID), " +
                "FOREIGN KEY(benhnhanID) REFERENCES BenhNhan(nguoidungID));";

        // Tạo bảng ketQuaChuanDoan
        String createKetQuaChuanDoanTable = "CREATE TABLE ketQuaChuanDoan (" +
                "ketquachuandoanID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenKetQuaChuanDoan TEXT NOT NULL, " +
                "trieuChung TEXT, " +
                "ngayKeToa TEXT, " +
                "lichhenID INTEGER, " +
                "FOREIGN KEY(lichhenID) REFERENCES LichHen(lichhenID));";

        // Tạo bảng Thuoc
        String createThuocTable = "CREATE TABLE Thuoc (" +
                "thuocID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenThuoc TEXT NOT NULL, " +
                "huongDanSuDung TEXT);";

        // Tạo bảng ToaThuoc
        String createToaThuocTable = "CREATE TABLE ToaThuoc (" +
                "toathuocID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "thuocID INTEGER, " +
                "ketquachuandoanID INTEGER, " +
                "FOREIGN KEY(thuocID) REFERENCES Thuoc(thuocID), " +
                "FOREIGN KEY(ketquachuandoanID) REFERENCES ketQuaChuanDoan(ketquachuandoanID));";

        // Thực thi các lệnh tạo bảng
        db.execSQL(createYTaTable);
        db.execSQL(createBenhNhanTable);
        db.execSQL(createThongTinNguoiDungTable);
        db.execSQL(createNguoiDungTable);
        db.execSQL(createBacSiTable);
        db.execSQL(createLichHenTable);
        db.execSQL(createKetQuaChuanDoanTable);
        db.execSQL(createThuocTable);
        db.execSQL(createToaThuocTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa các bảng nếu chúng tồn tại
        db.execSQL("DROP TABLE IF EXISTS YTa");
        db.execSQL("DROP TABLE IF EXISTS ToaThuoc");
        db.execSQL("DROP TABLE IF EXISTS Thuoc");
        db.execSQL("DROP TABLE IF EXISTS ketQuaChuanDoan");
        db.execSQL("DROP TABLE IF EXISTS LichHen");
        db.execSQL("DROP TABLE IF EXISTS BenhNhan");
        db.execSQL("DROP TABLE IF EXISTS BacSi");
        db.execSQL("DROP TABLE IF EXISTS NguoiDung");
        db.execSQL("DROP TABLE IF EXISTS ThongTinNguoiDung");

        // Gọi lại onCreate để tạo lại các bảng với cấu trúc mới
        onCreate(db);
    }


    public void addNguoiDung(String tenNguoiDung, String gioiTinh, String ngaySinh, String diaChi, String email, String soDT, String cccd, String chuyenKhoa, String chucVu) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            // Bước 1: Thêm người dùng vào bảng ThongTinNguoiDung
            ContentValues thongTinValues = new ContentValues();
            thongTinValues.put("TenNguoiDung", tenNguoiDung);
            thongTinValues.put("gioiTinh", gioiTinh);
            thongTinValues.put("ngaySinh", ngaySinh);
            thongTinValues.put("diaChi", diaChi);
            thongTinValues.put("email", email);
            thongTinValues.put("soDT", soDT);
            thongTinValues.put("CCCD", cccd);
            long nguoiDungID = db.insert("ThongTinNguoiDung", null, thongTinValues);

            // Kiểm tra xem việc chèn vào ThongTinNguoiDung có thành công không
            if (nguoiDungID == -1) {
                Toast.makeText(context, "Không thể thêm ThongTinNguoiDung", Toast.LENGTH_SHORT).show();
                return; // Nếu không thành công thì dừng thực hiện thêm vào bảng NguoiDung
            }

            // Bước 2: Thêm thông tin tài khoản người dùng vào bảng NguoiDung
            ContentValues nguoiDungValues = new ContentValues();
            nguoiDungValues.put("nguoidungID", nguoiDungID); // Sử dụng ID vừa tạo ở bước 1
            nguoiDungValues.put("username", soDT);
            nguoiDungValues.put("password", cccd); // Hàm hashPassword để mã hóa mật khẩu
            nguoiDungValues.put("active", 1); // Đặt giá trị mặc định cho active
            nguoiDungValues.put("createdDay", getCurrentDate()); // Hàm getCurrentDate để lấy ngày hiện tại
            nguoiDungValues.put("chucVu", chucVu);
            long result = db.insert("NguoiDung", null, nguoiDungValues);

            ContentValues bacSiValues = new ContentValues();
            bacSiValues.put("chuyenKhoa", chuyenKhoa);
            long result2 = db.insert("BacSi", null, bacSiValues);

            // Kiểm tra kết quả khi thêm vào bảng NguoiDung
            if (result == -1 && result2 == -1) {
                Toast.makeText(context, "Không thể thêm NguoiDung", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            // Xử lý lỗi nếu có
            Toast.makeText(context, "Lỗi khi thêm người dùng: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            // Đảm bảo đóng cơ sở dữ liệu trong mọi trường hợp
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }


    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }


    @SuppressLint("Range")
    public boolean verifyPassword(String username, String plainPassword) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to retrieve the hashed password for the given username
        String query = "SELECT password FROM NguoiDung WHERE username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        String storedHashedPassword = null;
        if (cursor.moveToFirst()) {
            storedHashedPassword = cursor.getString(cursor.getColumnIndex("password"));
        }
        cursor.close();
        db.close();

        if (storedHashedPassword == null) {
            // Username not found in the database
            return false;
        }

        // Hash the input password and compare with the stored hash
        return true;
    }



    public List<String> getAllUser(String excludeRole) {
        List<String> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT ThongTinNguoiDung.nguoidungID, ThongTinNguoiDung.TenNguoiDung, NguoiDung.vaitro " +
                "FROM ThongTinNguoiDung " +
                "JOIN NguoiDung ON ThongTinNguoiDung.nguoidungID = NguoiDung.nguoidungID " +
                "WHERE NguoiDung.vaitro != ?";

        Cursor cursor = db.rawQuery(query, new String[]{excludeRole});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String userId = cursor.getString(cursor.getColumnIndex("nguoidungID"));
                @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex("TenNguoiDung"));
                users.add(userId + " " + userName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return users;
    }


}
