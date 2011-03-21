package model.dbio;

public class SMSCar_info {

  private String smsName;
  private String smsTel;

  public SMSCar_info() {
    this.smsName = "";
    this.smsTel = "";
  }

  public void setSmsName(String smsName) {
    this.smsName = smsName;
  }

  public String getSmsName() {
    return this.smsName;
  }

  public void setSmsTel(String smsTel) {
    this.smsTel = smsTel;
  }

  public String getSmsTel() {
    return this.smsTel;
  }
}
