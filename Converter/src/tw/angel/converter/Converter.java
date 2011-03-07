package tw.angel.converter;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Converter extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);
    }
    public void cvOnClick(View cvView){
      EditText kilogramsInput = (EditText) findViewById(R.id.Kilograms);
      TextView pondsOutput = (TextView) findViewById(R.id.Ponds);
      DecimalFormat pondsFormat = new DecimalFormat(".#####");
      String outcome = pondsFormat.format(Integer.parseInt(kilogramsInput.getText().toString())*2.20462262);
      pondsOutput.setText(outcome);
    }
    public void nextOnClick(View vView){
      setContentView(R.layout.page2);
    }
    public void preOnClick(View vView){
      setContentView(R.layout.main);
    }
    public void btnReturn_OnClick(View vView){
      TextView txtNumOutput = (TextView) findViewById(R.id.txtSMSNumber2);
      TextView txtCntOutput = (TextView) findViewById(R.id.txtSMSContents2);
      String sNum = txtNumOutput.getText().toString();
      String sCnt = txtCntOutput.getText().toString();
      setContentView(R.layout.sender);
      TextView txtNumInput = (TextView) findViewById(R.id.txtSMSNumber);
      TextView txtCntInput = (TextView) findViewById(R.id.txtSMSContents);
      txtNumInput.setText(sNum);
      txtCntInput.setText(sCnt);
    }
    public void btnSendSMS_OnClick(View vView){
      setContentView(R.layout.sender);
    }
    public void btnSMSSend_OnClick(View vView){
      TextView txtNumInput = (TextView) findViewById(R.id.txtSMSNumber);
      TextView txtCntInput = (TextView) findViewById(R.id.txtSMSContents);
      String sNum = txtNumInput.getText().toString();
      String sCnt = txtCntInput.getText().toString();
      if(txtNumInput.length()>0 && txtCntInput.length()>0){
        setContentView(R.layout.sender_check);
        TextView txtNumOutput = (TextView) findViewById(R.id.txtSMSNumber2);
        TextView txtCntOutput = (TextView) findViewById(R.id.txtSMSContents2);
        txtNumOutput.setText(sNum);
        txtCntOutput.setText(sCnt);
      }
    }
    public void btnSubmit_OnClick(View vView){
      TextView txtNumOutput = (TextView) findViewById(R.id.txtSMSNumber2);
      TextView txtCntOutput = (TextView) findViewById(R.id.txtSMSContents2);
      String sNum = txtNumOutput.getText().toString();
      String sCnt = txtCntOutput.getText().toString();
      //openDialog(); //修改成yes no
      sendSMS(sNum, sCnt);
    }
    
    private void sendSMS(String phoneNumber, String message){   
      try{
        PendingIntent pi = PendingIntent.getActivity(this, 0,
            new Intent(this, Converter.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      Toast.makeText(this, "送出成功", Toast.LENGTH_SHORT).show();
    }
    private void openDialog() {
      new AlertDialog.Builder(this)
        .setTitle("Tip")
        .setMessage("傳送成功")
        .setPositiveButton("返回",
          new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              // TODO Auto-generated method stub
              //按下按鈕後執行的動作，沒寫則退出Dialog
            }
          }
        )
        .show();
    }
    
    public void btnGoManager_OnClick(View vView){
      setContentView(R.layout.manager);
    }
}