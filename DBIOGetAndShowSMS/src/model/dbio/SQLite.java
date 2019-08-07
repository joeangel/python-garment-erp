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
    Cursor cursor = db.query(true, "table_name", // ��ƪ�W��
        new String[] { "_ID", "name", "value" }, // ���W��
        "_ID=" + rowId, // WHERE
        null, // WHERE ���Ѽ�
        null, // GROUP BY
        null, // HAVING
        null, // ORDOR BY
        null // ����^�Ǫ�rows�ƶq
        );

    // �`�N�G���g�|�X��
    if (cursor != null) {
      cursor.moveToFirst(); // �N���в���Ĥ@�����
    }
    return cursor;
  }

  // �s�W�@���O���A���\�^��rowID�A���Ѧ^��-1
  public long create(String name, String value) {
    ContentValues args = new ContentValues();
    args.put("name", name);
    args.put("value", value);

    return db.insert("table_name", null, args);
  }

  // �R���O���A�^�Ǧ��\�R������
  public int delete(long rowId) {
    return db.delete("table_name", // ��ƪ�W��
        "_ID=" + rowId, // WHERE
        null // WHERE���Ѽ�
        );
  }

  // �ק�O���A�^�Ǧ��\�קﵧ��
  public int update(long rowId, String value) {
    ContentValues args = new ContentValues();
    args.put("value", value);

    return db.update("table_name", // ��ƪ�W��
        args, // VALUE
        "_ID=" + rowId, // WHERE
        null // WHERE���Ѽ�
        );
  }
}