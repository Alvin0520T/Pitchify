package com.example.pitchify_main.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
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
    private static final String TRANSCRIPT_FIELD = "transcript";
    private static final String TRANSCRIPTS_TABLE = "Transcripts";
    private static final String TRANSCRIPT_ID_FIELD = "id";
    private static final String TRANSCRIPT_CONTENT_FIELD = "transcript";

    public PitchifyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTableSQL = "CREATE TABLE " + TABLE_NAME + " (" +
                FIRST_NAME_FIELD + " TEXT , " +
                LAST_NAME_FIELD + " TEXT , " +
                EMAIL_FIELD + " TEXT PRIMARY KEY, " +
                PASSWORD_FIELD + " TEXT, " +
                TRANSCRIPT_FIELD + " TEXT)";
        sqLiteDatabase.execSQL(createUserTableSQL);

        String createTranscriptTableSQL = "CREATE TABLE " + TRANSCRIPTS_TABLE + " (" +
                TRANSCRIPT_ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TRANSCRIPT_CONTENT_FIELD + " TEXT)";
        sqLiteDatabase.execSQL(createTranscriptTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TRANSCRIPTS_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }

    public boolean updateTranscript(String transcript) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRANSCRIPT_CONTENT_FIELD, transcript);

        long result = db.insert(TRANSCRIPTS_TABLE, null, values);
        db.close();

        return result != -1;
    }

    public Boolean initializeVendorData() throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count == 0) {
            ArrayList<User> initialUsers = UserInitData.initializeUserToArrayList();
            for (User user : initialUsers) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FIRST_NAME_FIELD, user.getFirstName());
                contentValues.put(LAST_NAME_FIELD, user.getLastName());
                contentValues.put(EMAIL_FIELD, user.getEmail());
                contentValues.put(PASSWORD_FIELD, user.getPassword());

                long result = db.insert(TABLE_NAME, null, contentValues);
                if (result == -1) {
                    Log.d("Database Initialization", "Insert failed for user: " + user.getFirstName());
                    return false;
                }
            }
            return true;
        } else {
            Log.d("Database Initialization", "Database is not empty, no need to initialize");
            return false;
        }
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void logVendorData() {
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

    public boolean addNewVendor(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_FIELD, user.getFirstName());
        values.put(LAST_NAME_FIELD, user.getLastName());
        values.put(EMAIL_FIELD, user.getEmail());
        values.put(PASSWORD_FIELD, user.getPassword());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result != -1;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        String[] columns = {FIRST_NAME_FIELD, LAST_NAME_FIELD, EMAIL_FIELD, PASSWORD_FIELD};
        String selection = EMAIL_FIELD + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String firstname = cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD));
            @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PASSWORD_FIELD));

            user = new User(firstname, lastname, email, password);
        }

        cursor.close();
        db.close();

        return user;
    }

    public User getUserByName(String firstname) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        String[] columns = {FIRST_NAME_FIELD, LAST_NAME_FIELD, EMAIL_FIELD, PASSWORD_FIELD};
        String selection = FIRST_NAME_FIELD + " = ?";
        String[] selectionArgs = {firstname};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String fetchedFirstname = cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD));
            @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(EMAIL_FIELD));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PASSWORD_FIELD));

            user = new User(fetchedFirstname, lastname, email, password);
        }

        cursor.close();
        db.close();

        return user;
    }

    public User authenticateUser(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public boolean isEmailUsed(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count > 0;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String firstname = cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD));
                @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(EMAIL_FIELD));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PASSWORD_FIELD));
                users.add(new User(firstname, lastname, email, password));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    public boolean saveTranscript(String transcript) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRANSCRIPT_CONTENT_FIELD, transcript);
        long result = db.insert(TRANSCRIPTS_TABLE, null, values);
        db.close();
        return result != -1;
    }

    public ArrayList<String> getAllTranscripts() {
        ArrayList<String> transcripts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRANSCRIPTS_TABLE, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String transcript = cursor.getString(cursor.getColumnIndex(TRANSCRIPT_CONTENT_FIELD));
                transcripts.add(transcript);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return transcripts;
    }


}