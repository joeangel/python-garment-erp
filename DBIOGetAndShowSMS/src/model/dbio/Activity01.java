package model.dbio;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Activity01 extends Activity {
  MySQLiteHelper myHelper;
  TextView tv;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    tv = (TextView) findViewById(R.id.txtText);
    myHelper = new MySQLiteHelper(this, "my.db", "", null, 1);
    insertAndUpdateData(myHelper);
    String result = queryData(myHelper);
    tv.setTextColor(Color.RED);
    tv.setTextSize(20.0f);
    tv.setText("name\tlevel\n" + result);

  }

  public void insertAndUpdateData(MySQLiteHelper myHelper) {
    SQLiteDatabase db = myHelper.getWritableDatabase();
    db.execSQL("insert into hero_info(name,level) values('bb',0)");
    ContentValues values = new ContentValues();
    values.put("name", "xh");
    values.put("level", 5);
    db.insert("hero_info", "id", values);
    values.clear();
    values.put("name", "xh");
    values.put("level", 10);
    db.update("hero_info", values, "level = 5", null);

    db.close();
  }

  public String queryData(MySQLiteHelper myHelper) {
    String result = "";

    SQLiteDatabase db = myHelper.getReadableDatabase();

    Cursor cursor = db.query("hero_info", null, null, null, null, null,
        "id asc");

    int nameIndex = cursor.getColumnIndex("name");

    int levelIndex = cursor.getColumnIndex("level");
    for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
      result = result + cursor.getString(nameIndex) + "\t\t";
      result = result + cursor.getInt(levelIndex) + "       \n";
    }
    cursor.close();
    db.close();
    return result;
  }

  @Override
  protected void onDestroy() {
    SQLiteDatabase db = myHelper.getWritableDatabase();

    db.delete("hero_info", "1", null);
    super.onDestroy();
  }
}