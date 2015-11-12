import java.text.SimpleDateFormat;
import java.util.Date;


public class RevDetail {
	String 	RevID,CusID,StfID,payment,RoomID,RoomNum;
	Date StartDate,EndDate,In_DateTime,RevDateTime,OutDateTime;
	int PayType,Person;
	public int getPerson() {
		return Person+3;
	}
	public void setPerson(int person) {
		Person = person;
	}
	public String getRoomID() {
		return RoomID;
	}
	public void setRoomID(String roomID) {
		RoomID = roomID;
	}
	

	
	public Object[][] getRoomDetail() {
		return roomDetail;
	}
	public String getRoomNum() {
		return RoomNum;
	}
	public void setRoomNum(String roomNum) {
		RoomNum = roomNum;
	}
	public void setRoomDetail(Object[][] roomDetail) {
		this.roomDetail = roomDetail;
	}
	public String getRevID() {
		return RevID;
	}
	public void setRevID(String revID) {
		RevID = revID;
	}
	public String getCusID() {
		return CusID;
	}
	public void setCusID(String cusID) {
		CusID = cusID;
	}
	public String getStfID() {
		return StfID;
	}
	public void setStfID(String stfID) {
		StfID = stfID;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getStartDate() {
		return dateCon(StartDate);
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return dateCon(EndDate);
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public String getIn_DateTime() {
		return dateTimeCon(In_DateTime);
	}
	public void setIn_DateTime(Date in_DateTime) {
		In_DateTime = in_DateTime;
	}
	public String getRevDateTime() {
		return dateTimeCon(RevDateTime);
	}
	public void setRevDateTime(Date revDateTime) {
		RevDateTime = revDateTime;
	}
	public String getOutDateTime() {
		return dateTimeCon(OutDateTime);
	}
	public void setOutDateTime(Date outDateTime) {
		OutDateTime = outDateTime;
	}
	public int getPayType() {
		return PayType;
	}
	public void setPayType(int payType) {
		PayType = payType;
	}
	
	public String dateCon(Date date) {
		String date1 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null)
			date1= formatter.format(date);
		else
			System.out.println("Date fail to convert!!");
	    return date1;
	}
	
	public String dateTimeCon(Date date) {
		String date1 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date!=null)
			date1= formatter.format(date);
		else
			System.out.println("Date time fail to convert!!");
	    return date1;
	}
	
	
	
}
