package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private final ArrayList<Word> wordList = new ArrayList<>();
    public static final String DATABASE_NAME = "database_mobile";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "words";

    public Database(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                " ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "e_word TEXT," +
                "m_word TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("e_word", word.getSourceWord());
        contentValues.put("m_word", word.getTargetWord());
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean updateData(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("e_word", word.getSourceWord());
        contentValues.put("m_word", word.getTargetWord());
        Cursor cursor = db.rawQuery("SELECT * FROM words WHERE ID = ?", new String[]{String.valueOf(word.getId())});
        if(cursor.getCount() > 0){
            long result = db.update(TABLE_NAME, contentValues, "id = ?", new String[]{String.valueOf(word.getId())});
            db.close();
            return result != -1;
        }else{
            db.close();
            return false;
        }
    }
    public boolean deleteData(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM words WHERE ID = ?", new String[]{String.valueOf(word.getId())});
        if(cursor.getCount() > 0){
            long result = db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(word.getId())});
            db.close();
            return result != -1;
        }else{
            db.close();
            return false;
        }
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM words", null);
        return cursor;
    }

    public ArrayList<Word> getWords(){
        wordList.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM words", null);
        if(cursor.getCount() == 0){
            return wordList;
        }
        else{
            while(cursor.moveToNext()){
                Word word = new Word();
                word.setId(cursor.getInt(0));
                word.setSourceWord(cursor.getString(2));
                word.setTargetWord(cursor.getString(1));
                wordList.add(word);
            }
        }
        db.close();
        return wordList;
    }
}
