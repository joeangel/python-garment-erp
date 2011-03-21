package model.dbio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySQLiteHelper extends SQLiteOpenHelper {

  private String db_table_name;
  
  public MySQLiteHelper(Context context, String name, String db_table_name, CursorFactory factory,
      int version) {
    super(context, name, factory, version);
    this.setDb_table_name(db_table_name);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table if not exists " + this.getDb_table_name() + "("
        + "id integer primary key," + "name text," + "tel text)");

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }

  public void setDb_table_name(String db_table_name) {
    this.db_table_name = db_table_name;
  }

  public String getDb_table_name() {
    return db_table_name;
  }

}