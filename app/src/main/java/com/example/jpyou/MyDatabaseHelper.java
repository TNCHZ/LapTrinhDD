package com.example.jpyou;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
        String createVaitroTable = "CREATE TABLE Vaitro (" +
                "vaitroID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenVaiTro TEXT NOT NULL);";

        String createNguoiDungTable = "CREATE TABLE NguoiDung (" +
                "nguoidungID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TenNguoiDung TEXT NOT NULL, " +
                "gioiTinh TEXT, " +
                "ngaySinh TEXT, " +
                "email TEXT, " +
                "soDT TEXT, " +
                "CCCD TEXT, " +
                "username TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "vaitroID INTEGER, " +
                "FOREIGN KEY(vaitroID) REFERENCES Vaitro(vaitroID));";

        String createBenhNhanTable = "CREATE TABLE BenhNhan (" +
                "benhnhanID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nguoidungID INTEGER, " +
                "FOREIGN KEY(nguoidungID) REFERENCES NguoiDung(nguoidungID));";

        db.execSQL(createVaitroTable);
        db.execSQL(createNguoiDungTable);
        db.execSQL(createBenhNhanTable);

        String insertVaiTro1 = "INSERT INTO Vaitro (TenVaiTro) VALUES ('NguoiDangKi');";
        String insertVaiTro2 = "INSERT INTO Vaitro (TenVaiTro) VALUES ('BacSi');";
        db.execSQL(insertVaiTro1);
        db.execSQL(insertVaiTro2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Vaitro");
        db.execSQL("DROP TABLE IF EXISTS NguoiDung");
        db.execSQL("DROP TABLE IF EXISTS BenhNhan");
        onCreate(db);
    }

    public void addNguoiDung(String tenNguoiDung, String gioiTinh, String ngaySinh, String email, String soDT, String cccd, String username, String password, int vaitroID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("TenNguoiDung", tenNguoiDung);
        values.put("gioiTinh", gioiTinh);
        values.put("ngaySinh", ngaySinh);
        values.put("email", email);
        values.put("soDT", soDT);
        values.put("CCCD", cccd);
        values.put("username", username);
        values.put("password", hashPassword(password));
        values.put("vaitroID", vaitroID);

        long result = db.insert("NguoiDung", null, values);
        if(result == -1)
            Toast.makeText(context, "Khong the duoc", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
        db.close();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
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
        String hashedInputPassword = hashPassword(plainPassword);
        return storedHashedPassword.equals(hashedInputPassword); // Return true if they match
    }


    @SuppressLint("Range")
    public Integer getVaitroID(String chucVu) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT vaitroID FROM Vaitro WHERE TenVaiTro = ?";
        Cursor cursor = db.rawQuery(query, new String[]{chucVu});

        Integer vaitroID = null; // Initialize to null to indicate not found

        if (cursor.moveToFirst()) {
            // Retrieve the actual value of vaitroID from the cursor
            vaitroID = cursor.getInt(cursor.getColumnIndex("vaitroID"));
        }

        cursor.close();
        db.close();

        return vaitroID; // Will return null if not found
    }

    public List<String> getAllUser(String vaitroID) {
        List<String> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT nguoidungID, TenNguoiDung FROM NguoiDung WHERE vaitroID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{vaitroID});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex("TenNguoiDung"));
                @SuppressLint("Range") String nameID = cursor.getString(cursor.getColumnIndex("nguoidungID"));
                users.add(nameID + " " + userName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return users;
    }

    public boolean isDelete_user(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Execute the delete operation
        int result = db.delete("NguoiDung", "nguoidungID = ?", new String[]{String.valueOf(id)});

        db.close(); // Close the database connection

        // Check if a row was deleted (result > 0 means success)
        return result > 0;
    }



}
