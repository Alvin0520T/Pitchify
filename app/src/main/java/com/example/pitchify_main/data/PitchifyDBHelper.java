package com.example.pitchify_main.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pitchify_main.model.User;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PitchifyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PitchifyDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "User";
    private static final String FIRST_NAME_FIELD = "firstname"; // Set name as the primary key
    private static final String LAST_NAME_FIELD = "lastname";
    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";


    public PitchifyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                FIRST_NAME_FIELD + " TEXT , " +
                LAST_NAME_FIELD + " TEXT , " +
                EMAIL_FIELD + " TEXT PRIMARY KEY, " + // Set name as the primary key
                PASSWORD_FIELD + " TEXT)";
        sqLiteDatabase.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }

    public boolean updateUserPassword(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the updated values
        ContentValues values = new ContentValues();
        values.put(PASSWORD_FIELD, newPassword);

        // Define the WHERE clause
        String selection = EMAIL_FIELD + " = ?";
        String[] selectionArgs = { email };

        // Perform the update operation
        int rowsUpdated = db.update(TABLE_NAME, values, selection, selectionArgs);

        // Close the database connection
        db.close();

        // Check if the update was successful
        return rowsUpdated > 0;
    }

    public Boolean initializeVendorData() throws Exception {
        SQLiteDatabase DB = this.getWritableDatabase();

        // Check if the database is empty
        Cursor cursor = DB.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        Log.d("Database Initialization", "Current row count: " + count);

        // If the database is empty, add initial vendors
        if (count == 0) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            ArrayList<User> initialUser = UserInitData.initializeUserToArrayList();

            // Insert initial vendors into the database
            for (User user : initialUser) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FIRST_NAME_FIELD, user.getFirstName());
                contentValues.put(LAST_NAME_FIELD, user.getLastName());
                contentValues.put(EMAIL_FIELD, user.getEmail());
                contentValues.put(PASSWORD_FIELD, user.getPassword());


                long result = DB.insert(TABLE_NAME, null, contentValues);

                if (result == -1) {
                    Log.d("Database Initialization", "Insert failed for vendor with name: " + user.getFirstName());
                    return false; // If any insertion fails, return false
                } else {
                    Log.d("Database Initialization", "Insert successful for vendor with name: " + user.getFirstName());
                }
            }

            return true; // All initial vendors inserted successfully
        } else {
            Log.d("Database Initialization", "Database is not empty, no need to initialize");
            return false; // Database is not empty, no need to initialize
        }
    }

    public void clearDatabase() {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete(TABLE_NAME, null, null);
        DB.close();
    }

    public void logVendorData() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String firstname = cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD));
                    @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD));
                    @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(EMAIL_FIELD));
                    @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PASSWORD_FIELD));

                    Log.d("Database Content", "First Name: " +firstname + " | Last Name: " + lastname + " | Email: " + email+ " | password: " + password);

                    // Add more fields as needed
                } while (cursor.moveToNext());
            } else {
                Log.d("Database Content", "No data found in the database.");
            }
        } catch (Exception e) {
            Log.e("Database Content", "Error logging data: " + e.getMessage());
        } finally {
            cursor.close();
        }
    }



    //
// SIGNUP, LOGIN, BOOKING METHODS
//
    public boolean addNewVendor(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_FIELD, user.getFirstName());
        values.put(LAST_NAME_FIELD, user.getLastName());
        values.put(EMAIL_FIELD, user.getEmail()); // Use name as primary key
        values.put(PASSWORD_FIELD, user.getPassword());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result != -1;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        // Define the columns you want to retrieve
        String[] columns = {FIRST_NAME_FIELD, LAST_NAME_FIELD, EMAIL_FIELD, PASSWORD_FIELD};

        // Define the selection criteria (WHERE clause)
        String selection = EMAIL_FIELD + " = ?";
        String[] selectionArgs = {email};

        // Perform the query to retrieve the vendor with the specified email
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Extract vendor information from the cursor
            @SuppressLint("Range") String firstname = cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD));
            @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PASSWORD_FIELD));

            // Create a User object
            user  = new User(firstname, lastname, email, password);
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return user;
    }



    public User getUserByName(String firstname) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        // Define the columns you want to retrieve
        String[] columns = {FIRST_NAME_FIELD, LAST_NAME_FIELD, EMAIL_FIELD, PASSWORD_FIELD};

        // Define the selection criteria (WHERE clause)
        String selection = FIRST_NAME_FIELD + " = ?";
        String[] selectionArgs = {firstname};

        // Perform the query to retrieve the user with the specified name
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Extract user information from the cursor
            @SuppressLint("Range") String fetchedFirstname = cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD));
            @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(EMAIL_FIELD));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PASSWORD_FIELD));

            // Create a User object
            user = new User(fetchedFirstname, lastname, email, password);
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return user;
    }


    public User authenticateUser(String email, String password) {
        // Retrieve user data from the database using email
        User user = getUserByEmail(email);

        // Check if a user with the given email exists and the password matches
        if (user != null && password.equals(user.getPassword())) {
            return user; // Return the authenticated vendor
        } else {
            return null; // Return null if authentication fails
        }
    }

    public boolean isEmailUsed(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

}