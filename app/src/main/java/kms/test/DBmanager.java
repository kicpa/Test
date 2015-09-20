package kms.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KMS on 2015-03-05.
 */
public class DBmanager extends SQLiteOpenHelper {


    public DBmanager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("xxx", "onCreate >>>>>>>>>>>>>>>.....");
        String query = "create table test ( _id integer primary key autoincrement, name text, age integer, address text );";
        //Primary key는 기본열을 지정. 기본열은 반드시 입력해야하고 중복될 수 없다.
        db.execSQL(query);
        /*onCreate() is only run when the database file did not exist and was just created.
        If onCreate() returns successfully (doesn't throw an exception),
        the database is assumed to be created with the requested version number.
        As an implication, you should not catch SQLExceptions in onCreate() yourself.*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ){
        String query  = "DROP TABLE IF EXISTS test";
        db.execSQL(query);
        onCreate(db);
    }
}