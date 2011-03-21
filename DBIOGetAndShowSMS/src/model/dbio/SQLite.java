package model.dbio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
  private static final String DATABASE_NAME = "smscar.db"; // database name
  private static final int DATABASE_VERSION = 1; // database version

  private SQLiteDatabase db;

  public SQLite(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    db = this.getWritableDatabase();
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String DATABASE_CREATE_TABLE = "create table smscar ("
        + "id INTEGER PRIMARY KEY," + "name TEXT," + "tel TEXT" + ");";
    // create config table
    db.execSQL(DATABASE_CREATE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // oldVersion: old database version
    // newVersion: new database version
    db.execSQL("DROP TABLE IF EXISTS config"); // drop old table
    onCreate(db);
  }

  // get all records
  public Cursor getAll() {
    return db.rawQuery("SELECT * FROM table_name", null);
  }

  // get one record
  public Cursor get(long rowId) throws SQLException {
    Cursor cursor = db.query(true, "table_name", // 資料表名稱
        new String[] { "_ID", "name", "value" }, // 欄位名稱
        "_ID=" + rowId, // WHERE
        null, // WHERE 的參數
        null, // GROUP BY
        null, // HAVING
        null, // ORDOR BY
        null // 限制回傳的rows數量
        );

    // 注意：不寫會出錯
    if (cursor != null) {
      cursor.moveToFirst(); // 將指標移到第一筆資料
    }
    return cursor;
  }

  // 新增一筆記錄，成功回傳rowID，失敗回傳-1
  public long create(String name, String value) {
    ContentValues args = new ContentValues();
    args.put("name", name);
    args.put("value", value);

    return db.insert("table_name", null, args);
  }

  // 刪除記錄，回傳成功刪除筆數
  public int delete(long rowId) {
    return db.delete("table_name", // 資料表名稱
        "_ID=" + rowId, // WHERE
        null // WHERE的參數
        );
  }

  // 修改記錄，回傳成功修改筆數
  public int update(long rowId, String value) {
    ContentValues args = new ContentValues();
    args.put("value", value);

    return db.update("table_name", // 資料表名稱
        args, // VALUE
        "_ID=" + rowId, // WHERE
        null // WHERE的參數
        );
  }
}