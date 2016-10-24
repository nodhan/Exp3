package com.nodhan.exp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nodhan on 21-08-2016.
 */
public class DBConnection extends SQLiteOpenHelper {

    /**
     * Helper function to create a database object
     *
     * @param context
     * @return database object
     */
    public static DBConnection createDbConnection(Context context) {
        return new DBConnection(context, "employee", null, 1);
    }

    public DBConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table employee('id' varchar(3) primary key, 'name' varchar(30), 'dept' varchar(10), 'desgn' varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists employee");
        onCreate(sqLiteDatabase);
    }

    /***
     * To insert details of employee into MySQLite DB
     *
     * @param id    of the employee
     * @param name  of the employee
     * @param dept  of the employee
     * @param desgn of the employee
     */
    public boolean addData(String id, String name, String dept, String desgn) {
        //get DB in writable mode
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if (checkValidity(id, name)) {
            return false;
        } else {
            //prepare ContentValues object
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("dept", dept);
            contentValues.put("desgn", desgn);
            contentValues.put("name", name);
            sqLiteDatabase.insert("employee", null, contentValues);
            return true;
        }
    }

    /***
     * To retrieve details of employee into MySQLite DB
     *
     * @param flag checks for admin or employee
     * @param id   employee id
     * @return data - String[][] which contains the data
     */
    public String[][] displayData(boolean flag, String id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int i = 0;
        Cursor cursor;
        if (flag)
            cursor = sqLiteDatabase.rawQuery("select * from employee", null); // No args
        else
            cursor = sqLiteDatabase.rawQuery("select * from employee where id=?", new String[]{id}); // with args
        String data[][] = new String[cursor.getCount()][4];
        //pushing the cursor to first row
        while (cursor.moveToNext()) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = cursor.getString(j);
            }
            i++;
        }
        return data;
    }

    /***
     * To update details of employee into MySQLite DB
     *
     * @param data of the employee
     */
    public void updateData(String[] data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", data[0]);
        contentValues.put("dept", data[2]);
        contentValues.put("desgn", data[3]);
        contentValues.put("name", data[1]);
        sqLiteDatabase.update("employee", contentValues, "id=?", new String[]{data[0]});
    }

    /**
     * Checks if the details of user are present in SQLite DB or not
     *
     * @param id   of the employee
     * @param name of the employee
     * @return true if unique record exists else false
     */
    public boolean checkValidity(String id, String name) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM employee WHERE id=? AND name=?", new String[]{id, name});
        return cursor.getCount() == 1;
    }

    /***
     * checks for id in SQLite DB
     *
     * @param id of the employee
     * @return true if unique record exists else false
     */
    public boolean checkId(String id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM employee WHERE id=?", new String[]{id});
        return cursor.getCount() == 1;
    }

    /***
     * Deletes the row belonging to id
     *
     * @param id of the employee
     * @return true if row gets deleted
     */
    public boolean deleteData(String id) {
        return this.getWritableDatabase().delete("employee", "id=?", new String[]{id}) == 1;
    }
}