package dgsw.hs.kr.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemoDBHelper extends SQLiteOpenHelper {
    public MemoDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        result = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table memo ( sequenceNumber integer primary key autoincrement, title text, content text, createdTime integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table memo";
        db.execSQL(sql);
        onCreate(db);
    }
    ArrayList<UserBean> result;
    public long insert(UserBean user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("title", user.getTitle());
        value.put("content", user.getContent());
        value.put("createdTime", user.getCreatedTime());

        return db.insert("memo",null, value);
    }

    public UserBean get(int seqNum){
        SQLiteDatabase db =  getWritableDatabase();
        //Cursor c = db.rawQuery("SELECT * FROM memo where sequenceNumber = ", null);
        String sql = "SELECT * FROM memo WHERE sequenceNumber = "+ seqNum;
        Log.i("Sql " , sql);
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()){
            do {
                // Passing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                String column3 = c.getString(2);
                String column4 = c.getString(3);
                Log.i("aaaa : ", column1 + " " + column2 + " " + column3 + " " + column4);
            } while(c.moveToNext());
        }

        if(c.moveToFirst()){
            int id = c.getInt(0);
            String voca = c.getString(1);
        }

        if(c.getCount() > 0) {
            c.moveToFirst();
            return new UserBean(c.getInt(0), c.getString(1), c.getString(2), c.getLong(3));
        }
        return null;
    }

    public ArrayList<UserBean> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("memo", null, null, null, null, null, null);

        while(cursor.moveToNext()){
            UserBean user = new UserBean();
            user.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            user.setCreatedTime(cursor.getLong(cursor.getColumnIndex("createdTime")));
            user.setSequenceNumber(cursor.getInt(cursor.getColumnIndex("sequenceNumber")));
            result.add(user);
        }
        return result;
    }

    public long delete(int resultCode) {
        SQLiteDatabase db =  getWritableDatabase();
        String sequence = String.valueOf(resultCode);
        return db.delete("memo", "sequenceNumber=?", new String[] {sequence});
    }
}
