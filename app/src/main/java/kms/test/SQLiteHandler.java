package kms.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by KMS on 2015-03-10.
 */
public class SQLiteHandler {
    DBmanager helper;
    SQLiteDatabase db;

    // 초기화 작업
    public SQLiteHandler(Context context,String title) {
        helper = new DBmanager(context,title, null, 1);
    }

    //open
    public static SQLiteHandler open(Context context, String title) {
        return new SQLiteHandler(context,title);
    }

    //close
    public void close() {
        db.close();
    }

    //저장
    public void insert(String name, int age, String address) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("address", address);
        db.insert("test", null, values);
        Log.i("xxx", "Insert done! >>>>>>>>>>>>>>>.....");
    }//end insert

    //수정
    public void update(String name, int age) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age", age);
        db.update("test", values, "name=?", new String[]{name});
    }//end update

    //삭제
    public void delete(String name) {
        db = helper.getWritableDatabase();
        db.delete("test", "name=?", new String[]{name});
    }//end delete

    //검색
    public Cursor select(String table, String[] columns, String selection, String[] selectionArgs, String orderBy ) {
        db = helper.getReadableDatabase();
        Cursor c = db.query(table, columns, selection, selectionArgs, null, null, orderBy);
        return c;
    }//end select
}//end class