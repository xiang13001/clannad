import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JButton;


public class Reservation extends JComponent
{
	private Controller ctrl;
	private JTable jtbRoom;
	private JLabel jtcCheckIn,jtxCheckOut;
	private JTextField jtxNameOnCard;
	private JTextField jtxCardNum;
	private JTextField jtxSecureCode;
	private JTextField jtxPayment;
	private JComboBox<String> jcbStatus;
	private JTextField jtxIC;
	private JTextField jtxPassport;
	private JTextField jtxCusName;
	private JTextField jtxAge;
	private JTextField jtxContact;
	private JTextField jtxEmail;
	private JLabel jtxReservation;
	private JRadioButton rdbtnCash,rdbtnCreditCard,rdbtnLocal,rdbtnForeign;
	private JLayeredPane jlCash,jlCreditCard;
	private JLabel lblIc,lblPassport;
	private JTextArea jtxAddress;
	private Double TtlPrice,Deposit;
	private Date nowDate = new Date();
	private Customer customer;
	private CreditCard CrdCard;
	private RevDetail rev;
	private Date inDate,outDate;
	private JComboBox jcbMonth,jcbYear;
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private RevFrame revFrame;
	private JLabel lblPaymentDetail;
	private int day;
	
	@SuppressWarnings("serial")
	public Reservation(Controller ctrl, Date inDate, Date outDate, int day, RevFrame revFrame) 
	{
		setLayout(null);
		this.ctrl=ctrl;
		this.inDate=inDate;
		this.outDate=outDate;
		this.revFrame=revFrame;
		this.day=day;
		setVisible(true);
		setBounds(0, 0, 1100, 580);
		
		

		JLayeredPane jpGeneralInfo = new JLayeredPane() {
		};
		jpGeneralInfo.setBounds(41, 66, 311, 400);
		add(jpGeneralInfo);
		jpGeneralInfo.setBorder(new TitledBorder(null, "General Information", TitledBorder.LEADING, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12), null));
		jpGeneralInfo.setLayout(null);
		
			Vector<String> Name = new Vector<String>(Arrays.asList("Room Number","Person"));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(30, 160, 250, 212);
			jpGeneralInfo.add(scrollPane);
			
			jtbRoom = new JTable(ctrl.getSelcRoom(),Name);
			jtbRoom.setCellSelectionEnabled(true);
			scrollPane.setViewportView(jtbRoom);
			scrollPane.setOpaque(true);
		
		JLabel lblReservationId = new JLabel("Reservation ID");
		lblReservationId.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblReservationId.setBounds(30, 40, 128, 15);
		jpGeneralInfo.add(lblReservationId);
		
		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblCheckOut.setBounds(30, 130, 114, 15);
		jpGeneralInfo.add(lblCheckOut);
		
		JLabel lblCheckIn = new JLabel("Check In");
		lblCheckIn.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblCheckIn.setBounds(30, 100, 94, 15);
		jpGeneralInfo.add(lblCheckIn);
		
		
		jtxCheckOut = new JLabel();
		jtxCheckOut.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtxCheckOut.setBounds(165, 130, 100, 15);
		jpGeneralInfo.add(jtxCheckOut);
		jtxCheckOut.setText(formatter.format(outDate));
		
		jtcCheckIn = new JLabel();
		jtcCheckIn.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtcCheckIn.setBounds(165, 100, 100, 15);
		jpGeneralInfo.add(jtcCheckIn);
		jtcCheckIn.setText(formatter.format(inDate));
		
		JLabel lblBookDateTime = new JLabel("Booking Date");
		lblBookDateTime.setBounds(30, 70, 114, 17);
		jpGeneralInfo.add(lblBookDateTime);
		lblBookDateTime.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		
		jtxReservation = new JLabel();
		jtxReservation.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtxReservation.setBounds(165, 41, 100, 15);
		jpGeneralInfo.add(jtxReservation);
		jtxReservation.setText(ctrl.getNewRevID());
		
		JLabel lblBookDate = new JLabel("date");
		lblBookDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblBookDate.setBounds(165, 71, 74, 15);
		lblBookDate.setText(formatter.format(nowDate));
		jpGeneralInfo.add(lblBookDate);
		
		JLayeredPane jpPayment = new JLayeredPane() {
		};
		jpPayment.setBorder(new TitledBorder(null, "Payment Information", TitledBorder.LEADING, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12), null));
		jpPayment.setBounds(385, 66, 311, 400);
		add(jpPayment);
		jpPayment.setLayout(null);
		
		JLabel lblPaymentMethod = new JLabel("Payment method");
		lblPaymentMethod.setBounds(22, 88, 120, 15);
		lblPaymentMethod.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		jpPayment.add(lblPaymentMethod);
		
		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBounds(49, 113, 64, 23);
		rdbtnCash.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		jpPayment.add(rdbtnCash);
		rdbtnCash.setOpaque(false);
		rdbtnCash.setSelected(true);
		rdbtnCash.addItemListener(new btnLis());
		
		rdbtnCreditCard = new JRadioButton("Credit Card");
		rdbtnCreditCard.setBounds(142, 113, 108, 23);
		rdbtnCreditCard.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		jpPayment.add(rdbtnCreditCard);
		rdbtnCreditCard.setOpaque(false);
		rdbtnCreditCard.addItemListener(new btnLis());
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCash);
		group.add(rdbtnCreditCard);
		
		jlCash = new JLayeredPane();
		jlCash.setBorder(new TitledBorder(null, "Cash", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jlCash.setBounds(22, 150, 265, 209);
		jpPayment.add(jlCash);
		jlCash.setLayout(null);
		
		jlCreditCard = new JLayeredPane();
		jlCreditCard.setBounds(22, 150, 265, 209);
		jlCreditCard.setBorder(new TitledBorder(null, "Credit Card", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpPayment.add(jlCreditCard);
		jlCreditCard.setLayout(null);
		jlCreditCard.setVisible(false);

				JLabel lblMin = new JLabel("Deposit");
				lblMin.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
				lblMin.setBounds(22, 55, 64, 15);
				jlCash.add(lblMin);
				
				JLabel lblPay = new JLabel("Pay Amount");
				lblPay.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
				lblPay.setBounds(22, 100, 105, 15);
				jlCash.add(lblPay);
				
				JLabel lblStatus = new JLabel("Pay Status");
				lblStatus.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
				lblStatus.setBounds(22, 137, 105, 15);
				jlCash.add(lblStatus);
				
				jtxPayment = new JTextField();
				jtxPayment.setColumns(10);
				jtxPayment.setBounds(154, 98, 90, 21);
				jlCash.add(jtxPayment);
				
				String[] status = {"Pending","Cleared"};
				jcbStatus = new JComboBox(status);
				jcbStatus.setBounds(154, 135, 90, 21);
				jlCash.add(jcbStatus);
				
				JLabel lblDPrice = new JLabel("Price");
				lblDPrice.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
				lblDPrice.setBounds(154, 56, 100, 15);
				jlCash.add(lblDPrice);
				TtlPrice =  ctrl.getRoomPrice(day);
				System.out.println("Total Price"+TtlPrice);
				Deposit = TtlPrice*0.6;
				lblDPrice.setText(String.format("RM %6.2f",Deposit));
		
		/////////////////////ÎÒÊÇ·Ö¸îÏß///////////////////////
		
				JLabel lblName = new JLabel("Name");
				lblName.setToolTipText("Name On Card !!");
				lblName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
				lblName.setBounds(20, 27, 64, 15);
				jlCreditCard.add(lblName);
				
				JLabel lblCardNum = new JLabel("Card Number");
				lblCardNum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
				lblCardNum.setBounds(20, 57, 105, 15);
				jlCreditCard.add(lblCardNum);
				
				JLabel lblSecurityCode = new JLabel("Security Code");
				lblSecurityCode.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
				lblSecurityCode.setBounds(20, 87, 105, 15);
				jlCreditCard.add(lblSecurityCode);
				
				JLabel lblExpirationInfo = new JLabel("Expiration Date");
				lblExpirationInfo.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
				lblExpirationInfo.setBounds(20, 117, 105, 15);
				jlCreditCard.add(lblExpirationInfo);
				
				jtxNameOnCard = new JTextField();
				jtxNameOnCard.setBounds(152, 25, 90, 21);
				jlCreditCard.add(jtxNameOnCard);
				jtxNameOnCard.setColumns(10);
				
				jtxCardNum = new JTextField();
				jtxCardNum.setColumns(10);
				jtxCardNum.setBounds(152, 55, 90, 21);
				jlCreditCard.add(jtxCardNum);
				
				jtxSecureCode = new JTextField();
				jtxSecureCode.setColumns(10);
				jtxSecureCode.setBounds(152, 85, 90, 21);
				jlCreditCard.add(jtxSecureCode);
				
				String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				jcbMonth = new JComboBox(month);
				jcbMonth.setBounds(73, 142, 52, 21);
				jlCreditCard.add(jcbMonth);
				
				String[] year = {"2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025"};
				jcbYear = new JComboBox(year);
				jcbYear.setBounds(154, 142, 69, 21);
				jlCreditCard.add(jcbYear);
				
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		lblTotalAmount.setBounds(22, 35, 127, 36);
		jpPayment.add(lblTotalAmount);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lblPrice.setBounds(182, 35, 120, 36);
		jpPayment.add(lblPrice);
		lblPrice.setText(String.format("RM %6.2f",TtlPrice));
		
		lblPaymentDetail = new JLabel("Payment Detail");
		lblPaymentDetail.setForeground(new Color(153, 51, 255));
		lblPaymentDetail.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblPaymentDetail.setBounds(215, 375, 86, 15);
		jpPayment.add(lblPaymentDetail);
		lblPaymentDetail.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				new PayFrame(ctrl, day,TtlPrice);
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblPaymentDetail.setText("<html>Payment Detail</html>");
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblPaymentDetail.setText("<html><u>Payment Detail</u></html>");
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLayeredPane panel = new JLayeredPane() {
		};
		panel.setBounds(735, 66, 311, 400);
		panel.setBorder(new TitledBorder(null, "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12), null));
		add(panel);
		panel.setLayout(null);
		
		lblIc = new JLabel("IC");
		lblIc.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblIc.setBounds(34, 73, 54, 20);
		panel.add(lblIc);
		
		lblPassport = new JLabel("Passport");
		lblPassport.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblPassport.setBounds(34, 73, 80, 20);
		lblPassport.setVisible(false);
		panel.add(lblPassport);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblName_1.setBounds(34, 113, 54, 20);
		panel.add(lblName_1);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblAge.setBounds(34, 153, 54, 20);
		panel.add(lblAge);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblContact.setBounds(34, 193, 54, 20);
		panel.add(lblContact);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblEmail.setBounds(34, 233, 54, 20);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblAddress.setBounds(34, 273, 54, 20);
		panel.add(lblAddress);
		
		getCusLis cl = new getCusLis();
		
		jtxIC = new JTextField();
		jtxIC.setBounds(130, 74, 121, 21);
		panel.add(jtxIC);
		jtxIC.setColumns(10);
		jtxIC.addFocusListener(cl);
		
		jtxPassport = new JTextField();
		jtxPassport.setBounds(130, 74, 121, 21);
		panel.add(jtxPassport);
		jtxPassport.setColumns(10);
		jtxPassport.addFocusListener(cl);
		
		jtxCusName = new JTextField();
		jtxCusName.setColumns(10);
		jtxCusName.setBounds(130, 114, 121, 21);
		panel.add(jtxCusName);
		jtxCusName.addFocusListener(cl);
		
		jtxAge = new JTextField();
		jtxAge.setColumns(10);
		jtxAge.setBounds(130, 154, 121, 21);
		panel.add(jtxAge);
		
		jtxContact = new JTextField();
		jtxContact.setColumns(10);
		jtxContact.setBounds(130, 194, 121, 21);
		panel.add(jtxContact);
		
		jtxEmail = new JTextField();
		jtxEmail.setColumns(10);
		jtxEmail.setBounds(130, 234, 121, 21);
		panel.add(jtxEmail);
		
		jtxAddress = new JTextArea();
		jtxAddress.setBounds(130, 273, 157, 92);
		panel.add(jtxAddress);
		
		rdbtnLocal = new JRadioButton("Local");
		rdbtnLocal.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		rdbtnLocal.setBounds(45, 31, 81, 23);
		rdbtnLocal.setOpaque(false);
		panel.add(rdbtnLocal);
		rdbtnLocal.setSelected(true);
		rdbtnLocal.addItemListener(new btnLis());
		
		rdbtnForeign = new JRadioButton("Foreign");
		rdbtnForeign.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		rdbtnForeign.setBounds(141, 31, 121, 23);
		rdbtnForeign.setOpaque(false);
		panel.add(rdbtnForeign);
		rdbtnForeign.addItemListener(new btnLis());
		
		ButtonGroup CusType = new ButtonGroup();
		CusType.add(rdbtnLocal);
		CusType.add(rdbtnForeign);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(953, 494, 93, 23);
		add(btnConfirm);
		btnConfirm.addActionListener(new confirmLis());
		
		JButton btnCheckIn = new JButton("Check-in Now");
		btnCheckIn.setBounds(830, 494, 113, 23);
		add(btnCheckIn);

		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Image background = null;
    	try {
			background = ImageIO.read(new File("img/rev_back.png"));//±³¾°Í¼À´µÄ...
		} catch (IOException e) {

			e.printStackTrace();
		}
    	background.getScaledInstance(1100, 580,Image.SCALE_SMOOTH);
    	g.drawImage(background, 0, 0, 1100, 580, this);

        super.paintComponent(g);
		
	}
	
	public Object[][] getRoomDetail (JTable table) {
	    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    Object[][] tableData = new Object[nRow][nCol];
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)
	            tableData[i][j] = dtm.getValueAt(i,j);
	    System.out.println("Data :"+tableData);
	    return tableData;
	}
	
	class btnLis implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource()==rdbtnCash) 
			{
				jlCash.setVisible(true);
				jlCreditCard.setVisible(false);
		    }
		    else if (e.getSource()==rdbtnCreditCard) {
		    	jlCash.setVisible(false);
		    	jlCreditCard.setVisible(true);
		    }
		    else if (e.getSource()==rdbtnLocal) {
		    	lblIc.setVisible(true);
		    	lblPassport.setVisible(false);
		    	jtxIC.setVisible(true);
		    	jtxPassport.setVisible(false);
		    	jtxPassport.setText("");
		    }
		    else if (e.getSource()==rdbtnForeign) {
		    	lblIc.setVisible(false);
		    	lblPassport.setVisible(true);
		    	jtxIC.setVisible(false);
		    	jtxPassport.setVisible(true);
		    	jtxIC.setText("");
		    }
			repaint();
			revalidate();
			
		}
	}
	
	class confirmLis implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(jtxIC.getText().length()>13)
			{
				JOptionPane.showMessageDialog(null,"Please enter correct IC format",
													"Warning!!",JOptionPane.ERROR_MESSAGE);
			}
			else if(!jtxPayment.getText().matches("[0-9]+")&&!jtxPayment.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Amount can be number only !",
						"Warning!!",JOptionPane.ERROR_MESSAGE);
			}
			else if(!jtxPayment.getText().equals("")&&Double.parseDouble(jtxPayment.getText())<Deposit&&jcbStatus.getSelectedIndex()==1)
			{
				JOptionPane.showMessageDialog(null,"Customer must pay at least RM "+Deposit,
						"Warning!!",JOptionPane.ERROR_MESSAGE);
			}
			else if(jtxIC.equals("")&&jtxPassport.equals(""))
			{
				JOptionPane.showMessageDialog(null,"IC or Passport cannot be blank !!",
						"Warning!!",JOptionPane.ERROR_MESSAGE);
			}
			else if(jtxCusName.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please fill in your name !!",
						"Warning!!",JOptionPane.ERROR_MESSAGE);
			}
			else if(!jtxAge.getText().matches("[0-9]+"))
			{
				JOptionPane.showMessageDialog(null,"Age can be number only !",
						"Warning!!",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
	        	customer.setIC(jtxIC.getText());
	        	customer.setPassport(jtxPassport.getText());
	        	customer.setCus_Name(jtxCusName.getText());
	        	customer.setAge(Integer.parseInt(jtxAge.getText()));
	        	customer.setContact_Num(jtxContact.getText());
	        	customer.setAddress(jtxAddress.getText());
	        	customer.setEmail(jtxEmail.getText());
	        	customer = ctrl.setCustomer(customer);	
	        	
	        	rev = new RevDetail();
	        	rev.setRevID(jtxReservation.getText());
	        	rev.setCusID(customer.getCus_ID());
	        	rev.setStfID(ctrl.getStaff().getStf_ID());
	        	rev.setRevDateTime(nowDate);
	        	
	        	if(rdbtnCreditCard.isSelected())
	        	{
	        		rev.setPayType(2);
		        	rev.setPayment("N");
		        	CrdCard = new CreditCard();
		        	CrdCard.setCardName(jtxNameOnCard.getText());
		        	CrdCard.setCardNum(jtxCardNum.getText());
		        	CrdCard.setRevID(jtxReservation.getText());
		        	CrdCard.setSecCode(jtxSecureCode.getText());
		        	CrdCard.setExpMonth(jcbMonth.getSelectedIndex());
		        	CrdCard.setExpYear(Integer.parseInt(jcbYear.getSelectedItem().toString()));
		        	ctrl.setCreditCard(CrdCard);
	        	}
	        	else
	        	{
	        		rev.setPayType(1);
	        		if(jcbStatus.getSelectedIndex()==0)
	        			rev.setPayment("N");
	        		else
	        			rev.setPayment("Y");
	        	}
	        	rev.setStartDate(inDate);
	        	rev.setEndDate(outDate);
	        	rev.setRoomDetail(getRoomDetail(jtbRoom));
	        	ctrl.newTrans(jtxReservation.getText(),ctrl.getStaff().getStf_ID(),jtxPayment.getText(),TtlPrice,day);
	        	ctrl.saveRevDetail(rev);
	        	revFrame.dispose();
	        	ctrl.SetPanel(new RoomPreview(ctrl));
	        	
			}
		}
		
	}
	
	class getCusLis implements FocusListener
	{

		private int getfocus=0;

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource()==jtxCusName&&getfocus==1)
			{
				customer=ctrl.getCustomer(jtxIC.getText(), jtxPassport.getText());
				if(customer.getCus_ID()!=null)
				{
					jtxCusName.setText(customer.getCus_Name());
					jtxAge.setText(customer.getAge()+"");
					jtxContact.setText(customer.getContact_Num());
					jtxAddress.setText(customer.getAddress());
					jtxEmail.setText(customer.getEmail());
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"No data retrive !!",
							"Error!!",JOptionPane.ERROR_MESSAGE);
				}
				getfocus=0;
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(e.getSource()==jtxIC||e.getSource()==jtxPassport)
				getfocus=1;
	
		}
		
	}
}
