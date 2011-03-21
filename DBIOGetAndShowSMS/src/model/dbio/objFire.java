package model.dbio;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class objFire extends Activity {
  final String db_name = "smscar"; // database name
  final String db_path = db_name + ".db"; // database path = database name +
                                          // ".db"
  final String db_table_name = "smscar"; // table name

  // main function
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    String sSMS = "smscar:Angel,0922999666;Jeff,0989333777;";
    fire(sSMS);
  }

  public void fire(String sSMS) {
    String smsContent = sSMS;

    // read sms and store data to database
    MySQLiteHelper myHelper = new MySQLiteHelper(this, db_path, db_table_name,
        null, 1);
    final String smsPre = "smscar:";

    // db
    SQLiteDatabase db = myHelper.getWritableDatabase();

    //
    ArrayList<SMSCar_info> smsInfos = new ArrayList<SMSCar_info>();
    String[] tmpStrArray = {};
    String tmp_str = "";

    //
    if (!(smsContent.indexOf(smsPre) == 0)) {
      this.makeText("is not smscar.");
      return;
    }

    //
    tmpStrArray = smsContent.replaceFirst(smsPre, "").split(";");
    for (String item : tmpStrArray) {
      String[] tmp = item.split(",");
      SMSCar_info smsInfo = new SMSCar_info();
      smsInfo.setSmsName(tmp[0]);
      smsInfo.setSmsTel(tmp[1]);
      smsInfos.add(smsInfo);
    }

    tmp_str = smsContent + "\n";
    for (SMSCar_info item : smsInfos) {
      tmp_str += "\n";
      // sql format
      String tmp_sSql_insert_f = "insert into %s(name,tel) values('%s','%s')";
      String tmp_sSql_update_f = "update %s set tel = '%s' where name = '%s'";
      String tmp_sSql = "";
      // check weather the data is exist in table
      Cursor cursor = db.query(db_table_name, null,
          "name = '" + item.getSmsName() + "'", null, null, null, "id asc");
      if (cursor.getCount() > 0)
        tmp_sSql = String.format(tmp_sSql_update_f, db_table_name,
            item.getSmsTel(), item.getSmsName());
      else
        tmp_sSql = String.format(tmp_sSql_insert_f, db_table_name,
            item.getSmsName(), item.getSmsTel());
      db.execSQL(tmp_sSql);
      tmp_str += tmp_sSql;
    }
    makeText(String.valueOf(tmp_str));

    //
    db.close();

    //
    this.makeText("Finish.");

  }

  private void makeText(String sMsg) {

    Toast.makeText(this, sMsg, Toast.LENGTH_SHORT).show();

  }

  public String queryData(MySQLiteHelper myHelper) {
    String result = "";

    SQLiteDatabase db = myHelper.getReadableDatabase();

    Cursor cursor = db.query(db_table_name, null, null, null, null, null,
        "id asc");

    int nameIndex = cursor.getColumnIndex("name");

    int telIndex = cursor.getColumnIndex("tel");
    for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
      result = result + cursor.getString(nameIndex) + "\t\t";
      result = result + cursor.getString(telIndex) + "       \n";
    }
    cursor.close();
    db.close();
    return result;
  }

  @Override
  protected void onDestroy() {
    MySQLiteHelper myHelper = new MySQLiteHelper(this, db_path, db_table_name,
        null, 1);
    SQLiteDatabase db = myHelper.getWritableDatabase();

    db.delete(db_table_name, "1", null);
    super.onDestroy();
  }
}