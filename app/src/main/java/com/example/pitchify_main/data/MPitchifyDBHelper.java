package com.example.pitchify_main.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pitchify_main.model.User;

import java.util.ArrayList;

public class MPitchifyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MPitchifyDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Admin";
    private static final String FIRST_NAME_FIELD = "firstname";
    private static final String LAST_NAME_FIELD = "lastname";
    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";

    public MPitchifyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                FIRST_NAME_FIELD + " TEXT, " +
                LAST_NAME_FIELD + " TEXT, " +
                EMAIL_FIELD + " TEXT PRIMARY KEY, " +
                PASSWORD_FIELD + " TEXT)";
        sqLiteDatabase.execSQL(createTableSQL);

        // Initialize admin data after creating the table
        try {
            initializeAdminData(sqLiteDatabase);
        } catch (Exception e) {
            Log.e("Database Initialization", "Error initializing admin data: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void initializeAdminData(SQLiteDatabase db) throws Exception {
        ArrayList<User> adminList = ManagerInitData.initializeToArrayList();

        for (User admin : adminList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FIRST_NAME_FIELD, admin.getFirstName());
            contentValues.put(LAST_NAME_FIELD, admin.getLastName());
            contentValues.put(EMAIL_FIELD, admin.getEmail());
            contentValues.put(PASSWORD_FIELD, admin.getPassword());

            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                throw new Exception("Insert failed for admin: " + admin.getFirstName());
            } else {
                Log.d("Database Initialization", "Insert successful for admin: " + admin.getFirstName());
            }
        }
    }

    public boolean isAdminEmailUsed(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count > 0;
    }

    @SuppressLint("Range")
    public User getAdminByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        User admin = null;

        // Define the columns you want to retrieve
        String[] columns = {FIRST_NAME_FIELD, LAST_NAME_FIELD, EMAIL_FIELD, PASSWORD_FIELD};

        // Define the selection criteria (WHERE clause)
        String selection = EMAIL_FIELD + " = ?";
        String[] selectionArgs = {email};

        // Perform the query to retrieve the admin with the specified email
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Extract admin information from the cursor
            String firstname = cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD));
            String lastname = cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD_FIELD));

            // Create a User object
            admin = new User(firstname, lastname, email, password);
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return admin;
    }

    public boolean updateAdminPassword(String email, String newPassword) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the updated values
        ContentValues values = new ContentValues();
        values.put(PASSWORD_FIELD, newPassword);

        // Define the WHERE clause
        String selection = EMAIL_FIELD + " = ?";
        String[] selectionArgs = {email};

        // Perform the update operation
        int rowsUpdated = db.update(TABLE_NAME, values, selection, selectionArgs);

        // Close the database connection
        db.close();

        // Check if the update was successful
        if (rowsUpdated > 0) {
            return true;
        } else {
            throw new Exception("Update failed for admin with email: " + email);
        }
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void logAdminData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String firstname = cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD));
                    @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD));
                    @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(EMAIL_FIELD));
                    @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PASSWORD_FIELD));

                    Log.d("Database Content", "First Name: " + firstname + " | Last Name: " + lastname + " | Email: " + email + " | Password: " + password);

                    // Add more fields as needed
                } while (cursor.moveToNext());
            } else {
                Log.d("Database Content", "No data found in the database.");
            }
        } catch (Exception e) {
            Log.e("Database Content", "Error logging data: " + e.getMessage());
        } finally {
            cursor.close();
            db.close();
        }
    }
}
