import java.awt.Component;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Controller 
{
	public MainFrame mf;
	private BackPanel bp;
	private Database db;
	private Stack<JComponent> jcStack = new Stack<JComponent>();
	private Staff staff;
	private Room room;
	private Customer customer;
	private RecBack back;
	private RoomPanel rp;
	private Reservation rev;
	private RightPanel revPane;
	private RevDetail revD;
	
	public Controller()
	{
		db=new Database();
	}
	
	public Staff Login(String user,String password) throws SQLException
    {
		try {
			staff=db.login(user, password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return staff;
    }
	
	public Staff FgPassCheck1(String Name, String IC)
	{
		staff=db.FgPassCheck1(Name, IC);
		return staff;
	}
	
	public void FgPassReset(String Stf_ID, String NewPass)
	{
		db.FgPassReset(Stf_ID, NewPass);
	}
	
	public Room getRoomStatus(String RoomNum)
	{
		room = db.getRoomStatus(RoomNum);
//		System.out.println("我拿过room status了!");
//		System.out.println("我的RoomNum 是 "+RoomNum);
//		System.out.println("我的RoomID 是 "+room.getRoom_ID());
		return room;
	}
	
	public void setRoomStatus(String status, String roomID)
	{
		db.setRoomStatus(status, roomID);
	}
	
	public void saveSecRoom(Room room,int selection)
	{
		db.saveSecRoom(room.getRoom_ID(),selection);
	}
	
	public void setRoomPane(RoomPanel rp)
	{
		this.rp=rp;
	}
	
	public void setRP(RightPanel revPane)
	{
		this.revPane=revPane;
	}
		
	public HashMap getRoomStatus(String inDate,String outDate, int current)
	{	
		HashMap status = db.getRoomStatus(inDate,outDate);

		db.clearRomSelected();
		rp.setCurrentStatus(db.getStatus(),current);
		rp.removeAll();
		rp.setIcon();
		rp.revalidate();
		rp.repaint();

		return status;
	}
	
	public Staff getStaff()
	{
		return staff;
	}
	
	public String getNewRevID()
	{
		return db.getNewRevID();
	}
	
	public Vector<Vector> getSelcRoom()
	{
		System.out.println(db.getSelcRoom());
		return db.getSelcRoom();
	}
	
	public Double getRoomPrice(int day)
	{
		return db.getRoomPrice(day);
	}
	
	public Customer getCustomer(String IC,String Passport)
	{
		customer = db.getCustomer(IC,Passport);
		return customer;
	}
	
	public Customer setCustomer(Customer customer) {
		if(customer.getCus_ID()==null)
			customer.setCus_ID(db.getNewCusID());
		db.setCustomer(customer);
		
		return customer;
	}
	
	public void setCreditCard(CreditCard crdCard)
	{
		db.setCreditCard(crdCard);
	}
	
	public void saveRevDetail(RevDetail rev) {
		db.setReservation(rev);
		for(Object[] lis : rev.getRoomDetail())
		{
			rev.setRoomNum(lis[0].toString());
			rev.setPerson(Integer.parseInt(lis[1].toString()));
			db.setRevDetail(rev);
		}
		JOptionPane.showMessageDialog(null,"Reservation ok!!",
				"Error!!",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void singleRoomRev(Room room)
	{
		revPane.singleRev(room);
	}
	
	public Vector<Vector> getPayDetail(int day)
	{
		return db.getPayDetails(day);
	}
	
	public void newTrans(String revID, String stf_ID, String payment,Double ttlPrice, int day) 
	{
		if(payment.equals(""))
			db.newTrans(revID, stf_ID, 0,ttlPrice,day);
		else
			db.newTrans(revID, stf_ID, Double.parseDouble(payment),ttlPrice,day);
		
	}
	
	public int getSelcCount() {

		return db.getCount();
	}

	public void getSameRev(Room room, int i) 
	{
		//db.clearRomStatus();
		revD = new RevDetail();
		if(db.getSameRev(room.getRoom_ID(),revD.dateCon(new Date()),i)==0)	//清除不必要的选择
		{
			db.clearRomSelected();
		}
		rp.setCurrentStatus(db.getStatus(),1);
		rp.removeAll();
		rp.setIcon();
		rp.revalidate();
		rp.repaint();
	}
	
	public Vector getRevRoom(String Rev_ID) {
		
		return db.getRevRoom(Rev_ID);
	}
	
	public Vector<Comparable> getRevDetail(String revID) {
		db.updateTtlAmount(db.getTransID(revID));
		return db.getRevDetail(revID);
	}

	public void saveInDetail(Object[][] inDetail, String revID) {

		for(Object[] lis : inDetail)
		{
			if(!lis[1].equals(""))
				db.setInDetail(lis[0].toString(),lis[1].toString(),revID);
			else
				db.setInDetail(lis[0].toString(),null,revID);
		}
		JOptionPane.showMessageDialog(null,"Check-in Sucess!!",
				"Info",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String getRoomRev(Room room) {
		revD = new RevDetail();   //拿转换date的方法而已
		return db.getRoomRev(room.getRoom_ID(),revD.dateCon(new Date()));
	}
	
	public Vector getInRoom(String Rev_ID) {
		return db.getInRoom(Rev_ID);
	}
	
	public void saveOutDetail(Object[][] inDetail, String revID) {
		for(Object[] lis : inDetail)
		{
			if(!lis[2].equals(""))
				db.setOutDetail(lis[0].toString(),lis[2].toString(),revID);
			else if(lis[2].equals(""))
				db.setOutDetail(lis[0].toString(),null,revID);

		}
		JOptionPane.showMessageDialog(null,"Check-out Sucess!!",
				"Info",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public Vector getTtlPay(String revID) {
		return db.getTtlPay(revID);
	}
	
	public String getTtlAmount(String revID)
	{
		return db.getTtlAmount(db.getTransID(revID));
	}
	
	
	public void savePayDetail(Object[][] payDetail, String TransID) {
		for(Object[] lis : payDetail)
		{
			if(!lis[0].toString().equals("")&&!lis[1].toString().equals("")&&!lis[2].toString().equals(""))
			db.setPayDetail(lis[0].toString(),lis[1].toString(),lis[2].toString(),TransID);
			else
				JOptionPane.showMessageDialog(null,"Sorry! Please try again...",
						"Error!!",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public String getTransID(String revID) {
		return db.getTransID(revID);
		
	}
	
	public void savePay(String revID) {
		db.savePay(db.getTransID(revID));
	}
	
	public String getNewStfID() {
		return db.getNewStfID();
	}
	
	public void setNewStaff(Staff staff) {
		try {
			db.setNewStaff(staff);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Vector<Vector> getStaffSet() {
		return db.getStaffSet();
	}
	
	public void tableCtrl(int type,int col,int row,String data,String pk)
	{
		if(type==1)
			db.updateStaff(col, row, data,pk);
		if(type==2)
			db.updateCustomer(col, row, data, pk);
	}
	
	public void rowDel(int type,int row,String pk)
	{
		if(type==1)
			db.staffRowDel(row,pk);
		if(type==2)
			db.customerRowDel(row, pk);
	}
	public Vector<Vector> getCustomerSet() {
		return db.getCustomerSet();
	}
	
	public void SetFrame(MainFrame mf)
	{
		this.mf=mf;
	}

	public void UpdatePanel(JPanel jp)
	{
		mf.getContentPane().removeAll();  
		mf.getContentPane().revalidate();
		mf.add(jp);
	}
	
	public void BackPanel(JComponent jc)	//避开stack
	{
		bp = new BackPanel(this);
		UpdatePanel(bp);
		bp.add(jc);
	}
	
	public void SetPanel(JComponent jc)
	{
		bp = new BackPanel(this);
		UpdatePanel(bp);
		bp.add(jc);
		jcStack.push(jc);
	}
	
	public JComponent getJCStack()
	{
		if(jcStack.isEmpty())
			return null;
		else
			return jcStack.pop();	
	}

	public Vector<Vector> getReport(String inDate, String outDate, int paging) {
		return db.getReport(inDate,outDate,paging);
	}

	public ArrayList<Double> getTypeReport(String date) {

		return db.getTypeReport(date);
	}

	public Vector<Vector> getException(String year) {
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		if(Double.parseDouble(year)!=2015)
		{
			JOptionPane.showMessageDialog(null,"No record found!",
					"Error!!",JOptionPane.ERROR_MESSAGE);
		}
		else
		{	
			row_rec = new Vector<Comparable>();
			row_rec.addElement("July");
			row_rec.addElement("2");
			record_set.addElement(row_rec);
			row_rec = new Vector<Comparable>();
			row_rec.addElement("August");
			row_rec.addElement("3");
			record_set.addElement(row_rec);
			row_rec = new Vector<Comparable>();
			row_rec.addElement("October");
			row_rec.addElement("5");
			record_set.addElement(row_rec);
		}
		return record_set;
	}

	public ArrayList<Double> getPayReport(String date) {
		// TODO Auto-generated method stub
		return db.getPayRpt(date);
	}

	

}
