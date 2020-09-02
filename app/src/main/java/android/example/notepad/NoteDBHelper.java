package android.example.notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class NoteDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;

    public NoteDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NoteDBContract.NoteEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NoteDBContract.NoteEntry.SQL_DROP_TABLE);
        onCreate(db);
    }
}
