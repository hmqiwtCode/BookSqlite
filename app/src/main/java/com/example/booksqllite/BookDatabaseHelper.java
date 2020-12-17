package com.example.booksqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookDatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase = null;
    private static final String DATABASE_NAME = "bookdb.db";
    private static final int VERSION_DATABASE = 1;
    private static final String TABLE_NAME = "book";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_PAGE = "page";

    public BookDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createSQL = "CREATE TABLE IF NOT EXISTS book(_id INTEGER PRIMARY KEY AUTOINCREMENT,title,author,page)";
        sqLiteDatabase.execSQL(createSQL);
        this.sqLiteDatabase = sqLiteDatabase;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String deleteTable = "DROP TABLE IF EXISTS book";
        sqLiteDatabase.execSQL(deleteTable);
        onCreate(sqLiteDatabase);
    }

    public List<Book> getAllRow(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Book> listBook = new ArrayList<>();
        String getSql = "SELECT * FROM book";
        Cursor cursor = sqLiteDatabase.rawQuery(getSql,null);
        while (cursor.moveToNext()){
            Book book = new Book(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Integer.valueOf(cursor.getString(3)));
            listBook.add(book);
        }
        return listBook;
    }


    public boolean addBook(Book book){
        sqLiteDatabase = this.getWritableDatabase();
        boolean check = false;
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,book.getTitle());
        values.put(COLUMN_AUTHOR,book.getAuthor());
        values.put(COLUMN_PAGE,book.getPage());
        check = sqLiteDatabase.insert(TABLE_NAME,null,values) > 0;
        return check;
    }

    public boolean updateBook(Book book){
        sqLiteDatabase = this.getWritableDatabase();
        boolean check = false;
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,book.getTitle());
        values.put(COLUMN_AUTHOR,book.getAuthor());
        values.put(COLUMN_PAGE,book.getPage());
        check = sqLiteDatabase.update(TABLE_NAME,values,"_id=?",new String[]{book.getId()+""}) > 0;
        return check;
    }

    public boolean deleteBook(String id){
        sqLiteDatabase = this.getWritableDatabase();
        boolean check = false;
        check = sqLiteDatabase.delete(TABLE_NAME,"_id=?",new String[]{id}) > 0;
        return check;
    }

}
