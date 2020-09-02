package android.example.notepad;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class DataManager {

    public static ArrayList<MyNotes> fetchAllNotes(NoteDBHelper helper) {
        ArrayList<MyNotes> notes = new ArrayList<>();
        SQLiteDatabase database = helper.getWritableDatabase();

        String[] columns = {NoteDBContract.NoteEntry.COLUMN_ID,
                NoteDBContract.NoteEntry.COLUMN_TITLE,
                NoteDBContract.NoteEntry.COLUMN_CONTENT};

        Cursor cursor = database.query(
                NoteDBContract.NoteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        int positionID = cursor.getColumnIndex(NoteDBContract.NoteEntry.COLUMN_ID);
        int positionTitle = cursor.getColumnIndex(NoteDBContract.NoteEntry.COLUMN_TITLE);
        int positionContent = cursor.getColumnIndex(NoteDBContract.NoteEntry.COLUMN_CONTENT);


        while (cursor.moveToNext()) {
            int ID = cursor.getInt(positionID);
            String title = cursor.getString(positionTitle);
            String content = cursor.getString(positionContent);

            notes.add(new MyNotes(ID, title, content));
        }
        cursor.close();
        return notes;
    }

    public static MyNotes fetchNote(NoteDBHelper helper, int noteID) {
        SQLiteDatabase database = helper.getWritableDatabase();
        MyNotes myNotes = new MyNotes();
        String[] columns = {NoteDBContract.NoteEntry.COLUMN_ID,
                NoteDBContract.NoteEntry.COLUMN_TITLE,
                NoteDBContract.NoteEntry.COLUMN_CONTENT};

        String selection = NoteDBContract.NoteEntry.COLUMN_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(noteID)};

        Cursor cursor = database.query(
                NoteDBContract.NoteEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int positionID = cursor.getColumnIndex(NoteDBContract.NoteEntry.COLUMN_ID);
        int positionTitle = cursor.getColumnIndex(NoteDBContract.NoteEntry.COLUMN_TITLE);
        int positionContent = cursor.getColumnIndex(NoteDBContract.NoteEntry.COLUMN_CONTENT);


        while (cursor.moveToNext()) {
            int ID = cursor.getInt(positionID);
            String title = cursor.getString(positionTitle);
            String content = cursor.getString(positionContent);

            myNotes = new MyNotes(ID, title, content);
        }
        cursor.close();
        return myNotes;
    }

    public static boolean deleteNote(NoteDBHelper helper, int noteID){
        SQLiteDatabase database = helper.getWritableDatabase();

        String whereClause = NoteDBContract.NoteEntry.COLUMN_ID + " LIKE ?";
        String[] whereArgs = {String.valueOf(noteID)};

        return database.delete(NoteDBContract.NoteEntry.TABLE_NAME,whereClause, whereArgs) > 0;
    }
}
