import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;


public class Database 
{
	Connection dbConn = null;
    PreparedStatement statement = null;
    String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Hotel";   

	String userName="sa";   String userPwd="tylywx";
	String query;
	
	private Staff staff;
	private Room room;
	private Customer customer;
	
	public Database()
	{
		try   
		{     
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);     
			System.out.println("连接数据库成功");
			//testBulkInsert();
		}
		catch(Exception e)   
		{     
			e.printStackTrace();     
			System.out.println("连接失败");   
		}
	}
	
	public void testBulkInsert()
	{
		setQuery("insert into Room values (?,?,?,?,?)");
		
		try 
		{
			for(int i=1;i<=10;i++)
			{
				for(int j=1;j<=17;j++)
				{
					statement = dbConn.prepareStatement(query);
					statement.setString(1,String.format("ROM%04d",((i-1)*17)+j ));
					System.out.println(String.format("ROM%04d",(i*17)+j ));
					statement.setString(2,String.format("%c%02d",64+i,j ));
					System.out.println(String.format("%c%02d",64+i,j ));
					if(i==1||i==2)
						statement.setString(3,"TYP01");
					else if(i==3||i==4||i==5)
						statement.setString(3,"TYP02");
					else if(i==6||i==7)
						statement.setString(3,"TYP03");
					else if(i==8||i==9||i==10)
						statement.setString(3,"TYP04");
					statement.setString(4,"A");
					statement.setInt(5,0);

					statement.executeUpdate();
				}
				
			}
			
			
			CloseConn();

		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "bulk insert Error!!");
			e.printStackTrace();
		}
	}
	
	public Staff login(String user,String password) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		staff = new Staff();
		setQuery("select * from Staff where [User]=? and Password = ?");
		try 
		{
			password=Encoder(password);
			System.out.println(password);
			statement = dbConn.prepareStatement(query);
			statement.setString(1, user);
	        statement.setString(2, password);
	        
	        ResultSet rs = statement.executeQuery();
	        while(rs.next())
	        {
	        	
	            if(user.equals(rs.getString("User"))&&password.equals(rs.getString("Password")))
	            {
	            	staff.setStf_ID(rs.getString(1));
	            	staff.setStf_Name(rs.getString(2));
	            	staff.setIC(rs.getString(3));
	            	staff.setJob(rs.getString(4));
	            	staff.setAge(rs.getInt(5));
	            	staff.setContact(rs.getString(6));
	            	staff.setUser(rs.getString(7));
	            	staff.setPassword(rs.getString(8));
	            	staff.setQues(rs.getString(9));
	            	staff.setAns(rs.getString(10));
	            }
	        }

	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return staff;
	}
	
	public Staff FgPassCheck1(String Name,String IC)
	{
		staff = new Staff();
		setQuery("select * from Staff where Stf_Name=? and IC = ?");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, Name);
	        statement.setString(2, IC);
	        
	        ResultSet rs = statement.executeQuery();
	        while(rs.next())
	        {
	        	
	        	staff.setStf_ID(rs.getString(1));
            	staff.setStf_Name(rs.getString(2));
            	staff.setIC(rs.getString(3));
            	staff.setJob(rs.getString(4));
            	staff.setAge(rs.getInt(5));
            	staff.setContact(rs.getString(6));
            	staff.setUser(rs.getString(7));
            	staff.setPassword(rs.getString(8));
            	staff.setQues(rs.getString(9));
            	staff.setAns(rs.getString(10));
	        }

	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return staff;
	}
	
	public void FgPassReset(String Stf_ID,String NewPass)
	{
		
		setQuery("update Staff set Password = ? where Stf_ID = ?");
		try 
		{
			System.out.println(" Stf_ID = "+Stf_ID);
			NewPass=Encoder(NewPass);
			System.out.println(" NewPass = "+NewPass);
			statement = dbConn.prepareStatement(query);
			statement.setString(1, NewPass);
	        statement.setString(2, Stf_ID);
	        statement.executeUpdate();

	        JOptionPane.showMessageDialog(null, "Password Change!!");
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
	
	public Room getRoomStatus(String RoomNum)
	{
		room = new Room();
		setQuery("select * from Room where Room_Num = ?");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, RoomNum);
	        
			ResultSet rs = statement.executeQuery();
	        while(rs.next())
	        {
	        	room.setRoom_ID(rs.getString(1));
	        	room.setRoom_Num(rs.getString(2));
	        	room.setType_ID(rs.getString(3));
	        	room.setStatus(rs.getString(4));
	        	room.setSeleted(rs.getInt(5));
	        }
	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return room;
	}
	
	public void setRoomStatus(String status, String roomID)
	{
		setQuery("update Room set Status = ? where Room_ID = ?");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, status);
	        statement.setString(2, roomID);
	        
	        statement.executeUpdate();

	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void saveSecRoom(String roomID,int selection) {
		setQuery("update Room set Selected = ? where Room_ID = ?");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setInt(1, selection);
	        statement.setString(2, roomID);
	        
	        statement.executeUpdate();
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void clearRomStatus()
	{
		try {
			statement = dbConn.prepareStatement("update Room set Status = 'A' where Status != 'M' or Status != 'H'");
			statement.executeUpdate();
			CloseConn();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void clearRomSelected()
	{
		try {
			statement = dbConn.prepareStatement("update Room set Selected = '0'");
			statement.executeUpdate();
			CloseConn();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public HashMap getRoomStatus(String inDate,String outDate) 
	{
		
		clearRomStatus();

		setQuery("update Room "
					+"set Status = case "
									+"when Room_Rev.In_DateTime IS NOT NULL and Room_Rev.Out_DateTime IS NULL "
										+"then 'U' "
					                +"else 'R' "
								+"end "
								+"FROM Room JOIN Room_Rev " 
					+"ON Room_Rev.Room_ID=Room.Room_ID "
						+ "and "
							+ "((Room_Rev.Start_Date >= ? and Room_Rev.End_Date <= ?) "
							+ "OR "
							+ "(Room_Rev.Start_Date<= ? and Room_Rev.End_Date> ?) "
							+ "OR "
							+ "(Room_Rev.Start_Date< ? and Room_Rev.End_Date>= ?)) "
							+ "and Room_Rev.Out_DateTime IS NULL ");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, inDate);
	        statement.setString(2, outDate);
	        statement.setString(3, inDate);
	        statement.setString(4, inDate);
	        statement.setString(5, outDate);
	        statement.setString(6, outDate);
	        
	        statement.executeUpdate();
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		HashMap n = new HashMap(); 
		setQuery("select Type_ID,COUNT(*) from Room where (Status = 'U' or Status = 'R') group by Type_ID");
		try 
		{
			statement = dbConn.prepareStatement(query);
	        
			ResultSet rs = statement.executeQuery();
	        while(rs.next())
	        {
	        	n.put(rs.getString(1), rs.getString(2));
	        }
	        
	        for(int i=1;i<5;i++)
	        {
	        	if(n.get("TYP0"+i)==null)
	        		n.put("TYP0"+i, 0);
	        }
	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return n;
	}
	
	public HashMap<String, Integer> getStatus()
	{
		HashMap n = new HashMap(); 
		String[] status = {"A","U","R","H","M"};
		setQuery("select Status,COUNT(*) from Room group by Status");
		try 
		{
			statement = dbConn.prepareStatement(query);
	        
			ResultSet rs = statement.executeQuery();
	        while(rs.next())
	        {
	        	n.put(rs.getString(1), rs.getString(2));
	        }
	        
	        for(int i=1;i<5;i++)
	        {
	        	if(n.get(status[i])==null)
	        		n.put(status[i], 0);
	        }
	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return n;
	}
	
	public String getNewRevID() 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		
		setQuery("select Rev_ID from Reservation order by Rev_ID desc");
		String RevID = null;
		try 
		{
			statement = dbConn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next()==false)
			{
				RevID="R"+formatter.format(new Date())+"0000";
			}
			else
			{
				RevID=rs.getString(1);
				if(formatter.format(new Date()).equals(RevID.subSequence(1, 7)))
				{
					RevID=RevID.substring(7);
					RevID=String.format("R"+formatter.format(new Date())+"%04d", Integer.parseInt(RevID)+1);
				}
				else
				{
					RevID="R"+formatter.format(new Date())+"0000";
				}

			}
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return RevID;
	}
	
	public Vector<Vector> getSelcRoom()
	{
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		int num = 0;
		setQuery("select Room_Num,Type_ID from Room where Selected = '1'");
		try 
		{
			statement = dbConn.prepareStatement(query);
	        
			ResultSet rs = statement.executeQuery();
	        while(rs.next())
	        {
	        	row_rec = new Vector<Comparable>();
				row_rec.addElement(rs.getString(1));
				if(rs.getString(2).equals("TYP01"))
					num=1;
				else if(rs.getString(2).equals("TYP02")||rs.getString(2).equals("TYP03"))
					num=2;
				else if(rs.getString(2).equals("TYP04"))
					num=4;
				row_rec.addElement(num);
				record_set.addElement(row_rec);
	        }
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return record_set;
	}
	
	public Double getRoomPrice(int day)
	{
		double price = 0;
		setQuery("select sum(Room_Type.Price*?) from Room join Room_Type on "
				+ "Room.Type_ID = Room_Type.Des_ID where Room.Selected = 1");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setInt(1, day);
			ResultSet rs = statement.executeQuery();
	        rs.next();
	        price=rs.getDouble(1);
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return price;
	}
	
	public Customer getCustomer(String IC,String Passport)
	{
		customer = new Customer();
		
		if(!IC.equals(""))
			setQuery("select * from Customer where IC = ?");
		else if(!Passport.equals(""))
			setQuery("select * from Customer where Passport = ?");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			if(!IC.equals(""))
				statement.setString(1, IC);
			else if(!Passport.equals(""))
				statement.setString(1, Passport);
			
			if((!IC.equals("") && Passport.equals(""))||(IC.equals("") && !Passport.equals("")))
			{
				ResultSet rs = statement.executeQuery();
		        while(rs.next())
		        {
		        	customer.setCus_ID(rs.getString(1));
		        	customer.setIC(rs.getString(2));
		        	customer.setPassport(rs.getString(3));
		        	customer.setCus_Name(rs.getString(4));
		        	customer.setAge(rs.getInt(5));
		        	customer.setContact_Num(rs.getString(6));
		        	customer.setAddress(rs.getString(7));
		        	customer.setEmail(rs.getString(8));
		        }
			}
	        
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return customer;
		
	}
	
	public String getNewCusID() {
		
		setQuery("select Cus_ID from Customer order by Cus_ID desc");
		String CusID = null;
		try 
		{
			statement = dbConn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next()==false)
			{
				CusID="CUS00001";
			}
			else
			{
				CusID=rs.getString(1);
				CusID=CusID.replaceAll("CUS", "");
				CusID=String.format("CUS%05d", Integer.parseInt(CusID)+1);
			}
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return CusID;
	}
	
	public void setCustomer(Customer customer) {
		setQuery("IF NOT EXISTS(SELECT Cus_ID FROM Customer WHERE Cus_ID=? ) "
				+ "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?); "
				+ "else "
				+ "update Customer set IC=?, Passport=?, Cus_Name=?, "
				+ "Age=?, Contact_Num=?, Address=?, Email=? where Cus_ID=?;");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, customer.getCus_ID());
			statement.setString(2, customer.getCus_ID());
	        statement.setString(3, customer.getIC());
	        statement.setString(4, customer.getPassport());
	        statement.setString(5, customer.getCus_Name());
	        statement.setInt(6, customer.getAge());
	        statement.setString(7, customer.getContact_Num());
	        statement.setString(8, customer.getAddress());
	        statement.setString(9, customer.getEmail());
	        statement.setString(10, customer.getIC());
	        statement.setString(11, customer.getPassport());
	        statement.setString(12, customer.getCus_Name());
	        statement.setInt(13, customer.getAge());
	        statement.setString(14, customer.getContact_Num());
	        statement.setString(15, customer.getAddress());
	        statement.setString(16, customer.getEmail());
	        statement.setString(17, customer.getCus_ID());
	        statement.executeUpdate();
	        
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
	}
	
	public void setCreditCard(CreditCard crdCard) {
		setQuery("insert into Credit_Card values (?,?,?,?,?,?)");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1,crdCard.getRevID());
			statement.setString(2,crdCard.getCardName());
			statement.setString(3,crdCard.getCardNum());
			statement.setString(4,crdCard.getSecCode());
			statement.setInt(5,crdCard.getExpMonth());
			statement.setInt(6,crdCard.getExpYear());

			statement.executeUpdate();
			
			CloseConn();

		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		
	}
	
	public void setReservation(RevDetail rev) {
		setQuery("insert into Reservation values (?,?,?,?,?,?)");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1,rev.getRevID());
			statement.setString(2,rev.getCusID());
			statement.setString(3,rev.getStfID());
			statement.setString(4, rev.getRevDateTime());
			statement.setInt(5,rev.getPayType());
			statement.setString(6,rev.getPayment());

			statement.executeUpdate();
			CloseConn();
		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		
	}
	
	public void setRevDetail(RevDetail rev) {
		setQuery("insert into Room_Rev (Rev_ID,Room_ID,Person,Start_Date,End_Date) values (?,"
				+ "(select Room_ID from Room where Room_Num = ?),?,?,?)");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1,rev.getRevID());
			statement.setString(2,rev.getRoomNum());
			statement.setInt(3,rev.getPerson());
			statement.setString(4,  rev.getStartDate());
			statement.setString(5, rev.getEndDate());

			statement.executeUpdate();
			
			setQuery("update Room set Selected=0");
			statement = dbConn.prepareStatement(query);
			statement.executeUpdate();
			CloseConn();
		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		
	}
	
	public Vector<Vector> getPayDetails(int day)
	{
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		
		setQuery("select Room.Type_ID,count(Room.Type_ID),sum(Room_Type.Price*?) "
				+ "from Room join Room_Type "
				+ "on Room.Type_ID = Room_Type.Des_ID "
				+ "where Room.Selected = 1 GROUP BY Room.Type_ID");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setInt(1, day);
			ResultSet rs = statement.executeQuery();
			String roomType = null;
			
	        while(rs.next())
	        {
	        	row_rec = new Vector<Comparable>();
	        	System.out.println("Get date detail run! day = "+day);
				if(rs.getString(1).equals("TYP01"))
					roomType="Single Room";
				else if(rs.getString(1).equals("TYP02"))
					roomType="Double Room";
				else if(rs.getString(1).equals("TYP03"))
					roomType="Twin Room";
				else if(rs.getString(1).equals("TYP04"))
					roomType="Family Room";
				row_rec.addElement(roomType);
				row_rec.addElement(rs.getInt(2));
				row_rec.addElement(String.format(" %6.2f", rs.getDouble(3)));
				record_set.addElement(row_rec);
	        }
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return record_set;
		
	}
	
	public String getNewTransID() 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		
		setQuery("select Trans_ID from [Transaction] order by Trans_ID desc");
		String TransID = null;
		try 
		{
			statement = dbConn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()==false)
			{
				
				TransID="T"+formatter.format(new Date())+"0000";
				
			}
			else
			{
				TransID=rs.getString(1);
//				System.out.println("Trans ID"+TransID);
//				System.out.println("Date : "+formatter.format(new Date()));
//				System.out.println("Cut : "+TransID.subSequence(1, 6));
				if(formatter.format(new Date()).equals(TransID.subSequence(1, 7)))
				{
					TransID=TransID.substring(7);
					TransID=String.format("T"+formatter.format(new Date())+"%04d", Integer.parseInt(TransID)+1);
				}
				else
				{
					TransID="T"+formatter.format(new Date())+"0000";
				}
			}

			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		System.out.println("Rev ID = "+TransID);
		return TransID;
	}
	
	public void newTrans(String revID, String stf_ID, double payment,Double ttlPrice, int day) 
	{
		Vector<Vector> detail = getPayDetails(day);
		String TransID=getNewTransID();
		setQuery("INSERT INTO [Transaction] VALUES(?,?,?,?,?,?)");
		try 
		{
			statement = dbConn.prepareStatement(query);
			System.out.println("Trans ID = "+TransID);
			statement.setString(1, TransID);
			statement.setString(2, revID);
	        statement.setString(3, stf_ID);
	        statement.setDouble(4, ttlPrice);
	        statement.setString(5, "N");
	        statement.setString(6, "");
	        statement.executeUpdate();
	        
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		System.out.println("rev is null = "+detail.isEmpty());
		
		for(Vector<Vector> vec : detail)
		{
			Object[] arylis = vec.toArray();
			
			setQuery("INSERT INTO Trans_Detail VALUES(?,?,?,?)");
			try 
			{
				statement = dbConn.prepareStatement(query);
				statement.setString(1, TransID);
				statement.setString(2, arylis[0].toString());
		        statement.setInt(3, Integer.parseInt(arylis[1].toString()));
		        statement.setDouble(4, Double.parseDouble(arylis[2].toString()));
		        statement.executeUpdate();
		        
				CloseConn();
			}
			catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, "Error!!");
				e.printStackTrace();
			}
			
		}
		
		if(payment!=0)
		{
			setQuery("INSERT INTO Trans_Detail (Trans_ID,Describe,Price) VALUES(?,?,?)");
			try 
			{
				statement = dbConn.prepareStatement(query);
				statement.setString(1, TransID);
				statement.setString(2, "Deposit");
				statement.setDouble(3, -payment);
		        statement.executeUpdate();
		        
				CloseConn();
			}
			catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, "Error!!");
				e.printStackTrace();
			}
		}
		
	}
	
	public int getCount() 
	{
		int count=0;
		setQuery("select COUNT(*) from Room where Selected = '1'");
		try 
		{
			statement = dbConn.prepareStatement(query);
	        
			ResultSet rs = statement.executeQuery();
	        while(rs.next())
	        {
	        	count=rs.getInt(1);
	        }
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return count;
	}
	
	public int getSameRev(String room_ID,String date, int i) 
	{
		int rowNum=0;	//记录 updated 行数
		setQuery("update Room set Selected = ? from Room join Room_Rev on Room.Room_ID = Room_Rev.Room_ID "
				+ "where Room_Rev.Rev_ID = "
				+ "(select Rev_ID from Room_Rev where Room_ID = ? "
				+ "and (Start_Date<= ? and End_Date>?) and Room_Rev.Out_DateTime IS NULL)");
		try 
		{	
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			
			if(hour<12)
			{
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				date= formatter.format(cal.getTime());
			}
			statement = dbConn.prepareStatement(query);
			statement.setInt(1, i);
			statement.setString(2, room_ID);
			statement.setString(3, date);
			statement.setString(4, date);
			rowNum=statement.executeUpdate();
	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return rowNum;
		
	}
	
	public Vector getRevRoom(String rev_ID) {
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		
		setQuery("select Room_Num,Room_Rev.In_DateTime from Room join Room_Rev on Room.Room_ID = Room_Rev.Room_ID where Rev_ID =?");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, rev_ID);
			ResultSet rs = statement.executeQuery();

			
	        while(rs.next())
	        {
	        	row_rec = new Vector<Comparable>();
	        	
				row_rec.addElement(rs.getString(1));
				if(rs.getString(2)==null)
				{
					row_rec.addElement("");
					row_rec.addElement(Boolean.FALSE);
				}
				else
				{
					row_rec.addElement(rs.getString(2));
					row_rec.addElement(Boolean.TRUE);
				}
				
				record_set.addElement(row_rec);
	        }
	        
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return record_set;
	}
	
	public Vector<Comparable> getRevDetail(String revID) {
		Vector<Comparable> row_rec =null;
		setQuery("select r.Rev_ID,c.IC,c.Cus_Name,r.Rev_DateTime,rr.Start_Date,rr.End_Date,r.Pay_Type,t.Ttl_Amount "
				+ "from [Transaction] t,Room_Rev rr,Customer c,Reservation r "
				+ "where r.Rev_ID = ? and rr.Rev_ID=? and t.Rev_ID = ? and c.Cus_ID = r.Cus_ID");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, revID);
			statement.setString(2, revID);
			statement.setString(3, revID);
			ResultSet rs = statement.executeQuery();
			
			row_rec = new Vector<Comparable>();
			while(rs.next())
	        {
				row_rec.addElement(rs.getString(1));
				row_rec.addElement(rs.getString(2));
				row_rec.addElement(rs.getString(3));
				row_rec.addElement(rs.getString(4).substring(0, 11));
				row_rec.addElement(rs.getString(5));
				row_rec.addElement(rs.getString(6));
				if(rs.getInt(7)==1)
					row_rec.addElement("Cash");
				else
					row_rec.addElement("Credit Card");
				row_rec.addElement(String.format("RM %-9.2f", rs.getDouble(8)));
				row_rec.add(rs.getDate(5));
				row_rec.add(rs.getDate(6));
				break;
	        }

			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return row_rec;
	}
	
	public void setInDetail(String roomID, String date, String revID) {
		
		setQuery("update Room_Rev set In_DateTime = ? "
				+ "from Room join Room_Rev "
				+ "on Room.Room_ID=Room_Rev.Room_ID "
				+ "where Room.Room_Num = ? and Room_Rev.Rev_ID = ?");
		
		System.out.println(roomID+" "+date+" "+revID);
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, date);
	        statement.setString(2, roomID);
	        statement.setString(3, revID);
	        
	        statement.executeUpdate();
	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public String getRoomRev(String roomID, String date) {
		String revID = null;
		setQuery("select Rev_ID from Room_Rev where Room_ID = ? and (Start_Date<= ? and End_Date>?)");
		try 
		{	//这里非常注重日期判断
			statement = dbConn.prepareStatement(query);
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			
			if(hour<12)
			{
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				date= formatter.format(cal.getTime());
			}
			statement.setString(1, roomID);
			statement.setString(2, date);
			statement.setString(3, date);
			ResultSet rs = statement.executeQuery();
			
	        while(rs.next())
	        {
	        	revID=rs.getString(1);	//放着给它拿到最后一次
	        }
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		
		return revID;
	}
	
	public Vector getInRoom(String rev_ID) {
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		
		setQuery("select Room_Num,Room_Rev.In_DateTime,Room_Rev.Out_DateTime from Room join Room_Rev on Room.Room_ID = Room_Rev.Room_ID where Rev_ID =?");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, rev_ID);
			ResultSet rs = statement.executeQuery();

			
	        while(rs.next())
	        {
	        	row_rec = new Vector<Comparable>();
	        	
				row_rec.addElement(rs.getString(1));
				if(rs.getString(2)==null)
					row_rec.addElement("");
				else
					row_rec.addElement(rs.getString(2));
				if(rs.getString(3)==null)
				{
					row_rec.addElement("");
					row_rec.addElement(Boolean.FALSE);
				}
				else
				{
					row_rec.addElement(rs.getString(3));
					row_rec.addElement(Boolean.TRUE);
				}
				
				record_set.addElement(row_rec);
	        }
	        
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return record_set;
	}
	
	public void setOutDetail(String roomID, String Outdate, String revID) {

		setQuery("update Room_Rev set Out_DateTime = ? "
				+ "from Room join Room_Rev "
				+ "on Room.Room_ID=Room_Rev.Room_ID "
				+ "where Room.Room_Num = ? and Room_Rev.Rev_ID = ?");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, Outdate);
	        statement.setString(2, roomID);
	        statement.setString(3, revID);
	        
	        statement.executeUpdate();
	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public Vector getTtlPay(String revID) {
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		
		setQuery("select Describe,Quantity,Price from Trans_Detail where Trans_ID = "
				+ "(select Trans_ID from [Transaction] where Rev_ID = ?)");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, revID);
			ResultSet rs = statement.executeQuery();
			String roomType = null;
			
	        while(rs.next())
	        {
	        	row_rec = new Vector<Comparable>();

				row_rec.addElement(rs.getString(1));
				row_rec.addElement(rs.getInt(2));
				row_rec.addElement(String.format(" % 10.2f", rs.getDouble(3)));
				record_set.addElement(row_rec);
	        }
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return record_set;
	}
	
	public void setPayDetail(String des, String quantity, String price,String TransID) {
		setQuery("IF NOT EXISTS(SELECT Describe from Trans_Detail where Trans_ID = ? and Describe =?) "
				+ "INSERT INTO Trans_Detail VALUES(?,?,?,?); "
				+ "else "
				+ "update Trans_Detail set Quantity=? ,Price=? where Trans_ID = ? and Describe =?;");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, TransID);
	        statement.setString(2, des);
	        statement.setString(3, TransID);
	        statement.setString(4, des);
	        statement.setString(5, quantity);
	        statement.setDouble(6, Double.parseDouble(price));
	        statement.setString(7, quantity);
	        statement.setDouble(8, Double.parseDouble(price));
	        statement.setString(9, TransID);
	        statement.setString(10, des);
	        statement.executeUpdate();
	        System.out.println("Des = "+des);

	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public String getTransID(String revID) {
		setQuery("select Trans_ID from [Transaction] where Rev_ID = ?");
		String TransID = null;
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, revID);
			ResultSet rs = statement.executeQuery();
			rs.next();
			
			TransID = rs.getString(1);
			
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return TransID;
	}
	
	public void savePay(String transID) {
		setQuery("update [Transaction] set Pay_Status = 'Y' where Trans_ID = ?");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, transID);
	        statement.executeUpdate();
	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void updateTtlAmount(String transID) {
		setQuery("update [Transaction] set Ttl_Amount = (select SUM(price) "
				+ "from Trans_Detail where Trans_ID=?) where Trans_ID = ?;");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, transID);
	        statement.setString(2, transID);
	        
	        statement.executeUpdate();

	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public String getTtlAmount(String transID) {
		String ttlAmount = null;
		setQuery("select SUM(price) "
				+ "from Trans_Detail where Trans_ID=?");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, transID);
	        
	        ResultSet rs = statement.executeQuery();
	        rs.next();
	        ttlAmount = rs.getString(1);
	        
	        CloseConn();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ttlAmount;
	}
	

	public String getNewStfID() {
		setQuery("select Stf_ID from Staff order by Stf_ID desc");
		String StfID = null;
		try 
		{
			statement = dbConn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next()==false)
			{
				StfID="STF001";
			}
			else
			{
				StfID=rs.getString(1);
				StfID=StfID.replaceAll("STF", "");
				StfID=String.format("STF%03d", Integer.parseInt(StfID)+1);
			}
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		return StfID;
	}
	
	public void setNewStaff(Staff staff) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		setQuery("INSERT INTO Staff VALUES(?,?,?,?,?,?,?,?,?,?)");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, staff.getStf_ID());
			statement.setString(2, staff.getStf_Name());
	        statement.setString(3, staff.getIC());
	        statement.setString(4, staff.getJob());
	        statement.setInt(5, staff.getAge());
	        statement.setString(6, staff.getContact());
	        statement.setString(7, staff.getUser());
	        statement.setString(8, Encoder(staff.getPassword()));
	        statement.setString(9, staff.getQues());
	        statement.setString(10, staff.getAns());
	        statement.executeUpdate();
	        
	        JOptionPane.showMessageDialog(null,"Update complete",
					"Info",JOptionPane.INFORMATION_MESSAGE);
	        
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
		
	}

	public Vector<Vector> getStaffSet() {
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		setQuery("select * from Staff");
		try 
		{
			statement = dbConn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next())
			{
				row_rec = new Vector<Comparable>();
				row_rec.addElement(rs.getString(1));
				row_rec.addElement(rs.getString(2));
				row_rec.addElement(rs.getString(3));
				row_rec.addElement(rs.getString(4));
				row_rec.addElement(rs.getInt(5));
				row_rec.addElement(rs.getString(6));
				row_rec.addElement(rs.getString(7));
				row_rec.addElement(rs.getString(8));
				row_rec.addElement(rs.getString(9));
				row_rec.addElement(rs.getString(10));

				record_set.addElement(row_rec);
			}
			
			CloseConn();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return record_set;
	}
	
	public void updateStaff(int col,int row,String data,String pk)
	{
		String[] staffCol={"Stf_ID","Stf_Name","IC","Job","Age","Contact","User","Password","Question","Answer"};
		
		if(col==0||col==1||col==2||col==3||col==5||col==6||col==7||col==8||col==9)
		setQuery("update Staff set "+staffCol[col]+" = '"+data+"' where Stf_ID = '"+pk+"'");
		if(col==4)
		{
			if(isNumeric(data))
				setQuery("update Staff set "+staffCol[col]+" = "+data+" where Stf_ID = '"+pk+"'");
			else
				JOptionPane.showMessageDialog(null,"Age just can accept numeric!",
						"Error!!",JOptionPane.ERROR_MESSAGE);
		}
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.executeUpdate();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
			
	}
	
	public void staffRowDel(int row,String pk)
	{
		setQuery("delete from Staff where Stf_ID = ?");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, pk);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Row Delete!!");
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
	}
	public Vector<Vector> getCustomerSet() {
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		setQuery("select * from Customer");
		try 
		{
			statement = dbConn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next())
			{
				row_rec = new Vector<Comparable>();
				row_rec.addElement(rs.getString(1));
				row_rec.addElement(rs.getString(2));
				row_rec.addElement(rs.getString(3));
				row_rec.addElement(rs.getString(4));
				row_rec.addElement(rs.getInt(5));
				row_rec.addElement(rs.getString(6));
				row_rec.addElement(rs.getString(7));
				row_rec.addElement(rs.getString(8));
	
				record_set.addElement(row_rec);
			}
			
			CloseConn();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return record_set;
	}
	public void updateCustomer(int col,int row,String data,String pk)
	{
		String[] custCol={"Cus_ID","IC","Password","Cus_Name","Age","Contact_Num","Address","Email"};
		
		if(col==0||col==1||col==2||col==3||col==5||col==6||col==7)
		setQuery("update Customer set "+custCol[col]+" = '"+data+"' where Cus_ID = '"+pk+"'");
		if(col==4)
		{
			if(isNumeric(data))
				setQuery("update Customer set "+custCol[col]+" = "+data+" where Cus_ID = '"+pk+"'");
			else
				JOptionPane.showMessageDialog(null,"Age just can accept numeric!",
						"Error!!",JOptionPane.ERROR_MESSAGE);
		}
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.executeUpdate();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
			
	}
	public void customerRowDel(int row,String pk)
	{
		setQuery("delete from Customer where Cus_ID = ?");
		
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, pk);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Row Delete!!");
			CloseConn();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error!!");
			e.printStackTrace();
		}
	}
	public void setQuery(String query)
	{
		this.query = query;
	}
	
	public void CloseConn() throws SQLException
	{
		if(statement!=null)
			statement.close();
	}
	
	public String Encoder (String str)throws NoSuchAlgorithmException,UnsupportedEncodingException
	{

			MessageDigest messageDigest = null;
		
			messageDigest = MessageDigest.getInstance("MD5");
		
			messageDigest.reset();
		
			messageDigest.update(str.getBytes("UTF-8"));
		
			byte[] byteArray = messageDigest.digest();
		
			StringBuffer md5StrBuff = new StringBuffer();

			for (int i = 0; i < byteArray.length; i++) 
			{
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}

			return md5StrBuff.toString();
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	public Vector<Vector> getReport(String inDate, String outDate,int paging) {
		Vector<Comparable> row_rec;
		Vector<Vector> record_set = new Vector<Vector>();
		setQuery("select top 5 Reservation.Rev_ID,Room_Rev.Start_Date,Reservation.Cus_ID,SUM(Room_Rev.Person),SUM([Transaction].Ttl_Amount),Room.Type_ID "
				+"from [Transaction],Room,Room_Rev join Reservation "
				+"on Reservation.Rev_ID = Room_Rev.Rev_ID "
				+"where ((Room_Rev.Start_Date >= ? and Room_Rev.End_Date <= ?) "+
                                "OR "+ 
                    "(Room_Rev.Start_Date<= ? and Room_Rev.End_Date> ?) "+ 
                                "OR "+ 
                    "(Room_Rev.Start_Date< ? and Room_Rev.End_Date>= ?)) "+
                    "and [Transaction].Rev_ID=Reservation.Rev_ID and Room.Room_ID = Room_Rev.Room_ID "
                    + "and Reservation.Rev_ID not in (select top (?*5) Rev_ID from Reservation order by Rev_ID) "
                    + "group by Room.Type_ID,Reservation.Rev_ID,Room_Rev.Start_Date,Reservation.Cus_ID "
                    + "order by Rev_ID");
		try 
		{
			statement = dbConn.prepareStatement(query);
			statement.setString(1, inDate);
	        statement.setString(2, outDate);
	        statement.setString(3, inDate);
	        statement.setString(4, inDate);
	        statement.setString(5, outDate);
	        statement.setString(6, outDate);
	        statement.setInt(7, paging);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next())
			{
				row_rec = new Vector<Comparable>();
				row_rec.addElement(rs.getString(1));
				row_rec.addElement(rs.getString(2));
				row_rec.addElement(rs.getString(3));
				row_rec.addElement(rs.getString(4));
				row_rec.addElement(rs.getString(5));
				String roomType = null;
				if(rs.getString(6).equals("TYP01"))
					roomType="Single Room";
				else if(rs.getString(6).equals("TYP02"))
					roomType="Double Room";
				else if(rs.getString(6).equals("TYP03"))
					roomType="Twin Room";
				else if(rs.getString(6).equals("TYP04"))
					roomType="Family Room";
				row_rec.addElement(roomType);
	
				record_set.addElement(row_rec);
			}
			
			CloseConn();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return record_set;
	}
	
	public ArrayList<Double> getTypeReport(String date) {
		ArrayList<Double> values = new ArrayList<Double>();
		setQuery("select SUM(DATEDIFF(DAY, Start_Date, End_Date)) "
				+ "from Room join Room_Rev "
				+ "on Room.Room_ID = Room_Rev.Room_ID and CAST(Start_Date AS char) LIKE '"+date+"%' "
				+ "group by Room.Type_ID ");
		try 
		{
			statement = dbConn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next())
			{
				values.add(rs.getDouble(1));
				System.out.println("print");
			}
			
			CloseConn();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}

	public ArrayList<Double> getPayRpt(String date) {
		ArrayList<Double> values = new ArrayList<Double>();
		setQuery("select pay_type,count(pay_type) "
				+ "from Reservation "
				+ "CAST(Reservation.Rev_DateTime AS char) LIKE '%"+date+"%' "
				+ "group by Pay_Type ");
		try 
		{
			statement = dbConn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next())
			{
				values.add(rs.getDouble(1));
				System.out.println("print");
			}
			
			CloseConn();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}


}
