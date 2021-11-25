package com.hfad.productmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "StockData.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "PRODUCT";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "STOCK_NAME";
    private static final String COL_3 = "STOCKONHAND";
    private static final String COL_4 = "STOCKINTRANSIT";
    private static final String COL_5 = "PRICE";
    private static final String COL_6 = "REORDER_QUANTITY";
    private static final String COL_7 = "REORDER_AMOUNT";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, STOCK_NAME TEXT, STOCKONHAND INTEGER, STOCKINTRANSIT INTEGER,  PRICE INTEGER, REORDER_QUANTITY INTEGER, REORDER_AMOUNT INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertStockData(String stock_name, String stockonhand, String stockintransit, String price, String reorder_quantity, String reorder_amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, stock_name);
        contentValues.put(COL_3, stockonhand);
        contentValues.put(COL_4, stockintransit);
        contentValues.put(COL_5, price);
        contentValues.put(COL_6, reorder_quantity);
        contentValues.put(COL_7, reorder_amount);
        long results = db.insert(TABLE_NAME, null, contentValues);
        if (results == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


}


