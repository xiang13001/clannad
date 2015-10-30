import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JButton;
import javax.swing.ImageIcon;


public class RightPanel extends JComponent
{

	private DatePicker jtxInDate,jtxOutDate;
	private Controller ctrl;
	private Date inDate,outDate;
	private JLabel lblSRoom,lblTWRoom,lblDRoom,lblFRoom,lblDays,btnCheckIn, btnException, btnChkCRT;
	private  Date today = new Date(); 
	public AncestorLis anLis = new AncestorLis();
	public int day;
	
	public RightPanel(Controller ctrl) 
	{
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(999, 0, 265, 682);
		setLocale(Locale.ENGLISH);
		ctrl.setRP(RightPanel.this);
		
		JLabel lblCheck = new JLabel("Check-in Date");
		lblCheck.setForeground(Color.WHITE);
		lblCheck.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblCheck.setBounds(30, 85, 118, 20);
		add(lblCheck);
		
		UtilDateModel Inmodel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl IndatePanel = new JDatePanelImpl(Inmodel, p);
		jtxInDate = new DatePicker(IndatePanel, new DateLabelFormatter());
		jtxInDate.setBounds(30, 110, 167, 20);
		add(jtxInDate);
		IndatePanel.addAncestorListener(anLis);
		
		
		UtilDateModel Outmodel = new UtilDateModel();
		JDatePanelImpl OutdatePanel = new JDatePanelImpl(Outmodel, p);
		
		jtxOutDate = new DatePicker(OutdatePanel, new DateLabelFormatter());
		jtxOutDate.setBounds(30, 175, 167, 20);
		add(jtxOutDate);
		OutdatePanel.addAncestorListener(anLis);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setForeground(Color.WHITE);
		lblDuration.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblDuration.setBounds(30, 219, 80, 20);
		add(lblDuration);
		
		JLabel lblCheckoutDate = new JLabel("Check-out Date");
		lblCheckoutDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblCheckoutDate.setForeground(new Color(255, 255, 255));
		lblCheckoutDate.setBounds(30, 150, 132, 20);
		add(lblCheckoutDate);
		
		JLabel lblReservationInfo = new JLabel("Reservation Info");
		lblReservationInfo.setForeground(new Color(255, 255, 255));
		lblReservationInfo.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblReservationInfo.setBounds(10, 23, 208, 31);
		add(lblReservationInfo);
		
		JLabel lblAvailable = new JLabel("Room Status");
		lblAvailable.setForeground(Color.WHITE);
		lblAvailable.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		lblAvailable.setBounds(30, 347, 151, 20);
		add(lblAvailable);
		
		JLabel lblSingle = new JLabel("Single");
		lblSingle.setForeground(Color.YELLOW);
		lblSingle.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		lblSingle.setBounds(43, 393, 54, 15);
		add(lblSingle);
		
		JLabel lblDouble = new JLabel("Double");
		lblDouble.setForeground(new Color(30, 144, 255));
		lblDouble.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		lblDouble.setBounds(43, 423, 54, 15);
		add(lblDouble);
		
		JLabel lblTwin = new JLabel("Twin");
		lblTwin.setForeground(new Color(204, 102, 204));
		lblTwin.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		lblTwin.setBounds(43, 453, 54, 15);
		add(lblTwin);
		
		JLabel lblFamily = new JLabel("Family");
		lblFamily.setForeground(new Color(255, 0, 0));
		lblFamily.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		lblFamily.setBounds(43, 483, 54, 15);
		add(lblFamily);
		
		lblSRoom = new JLabel("0/10");
		lblSRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblSRoom.setForeground(Color.YELLOW);
		lblSRoom.setBounds(158, 394, 54, 15);
		add(lblSRoom);
		
		lblDRoom = new JLabel("0/10");
		lblDRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblDRoom.setForeground(new Color(30, 144, 225));
		lblDRoom.setBounds(158, 424, 54, 15);
		add(lblDRoom);
		
		lblTWRoom = new JLabel("0/10");
		lblTWRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblTWRoom.setBounds(158, 454, 54, 15);
		lblTWRoom.setForeground(new Color(204, 102, 204));
		add(lblTWRoom);
		
		lblFRoom = new JLabel("0/10");
		lblFRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblFRoom.setForeground(Color.RED);
		lblFRoom.setBounds(158, 484, 54, 15);
		add(lblFRoom);
		
		getNowStatus();	//È¡µÃÏÖÔÚ·¿¼ä×´Ì¬(ÖØÒª¿ª¹Ø)
		
		lblDays = new JLabel("");
		lblDays.setForeground(new Color(255, 255, 255));
		lblDays.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblDays.setBounds(143, 223, 100, 15);
		add(lblDays);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(175, 267, 80, 23);
		add(btnSubmit);
		
		
		btnCheckIn = new JLabel("1");
		btnCheckIn.setIcon(new ImageIcon("img/bottom/1.png"));
		btnCheckIn.setBounds(0, 582, 88, 100);
		btnCheckIn.addMouseListener(new MouseLis());
		add(btnCheckIn);
		
		
		btnException = new JLabel("2");
		btnException.setBounds(88, 582, 88, 100);
		btnException.setIcon(new ImageIcon("img/bottom/2.png"));
		btnException.addMouseListener(new MouseLis());
		add(btnException);
		
		
		btnChkCRT = new JLabel("3");
		btnChkCRT.setBounds(175, 582, 88, 100);
		btnChkCRT.setIcon(new ImageIcon("img/bottom/3.png"));
		btnChkCRT.addMouseListener(new MouseLis());
		add(btnChkCRT);
		
		
		
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Reserve();
				
			}
		});

		
	}
	
	public void Reserve()
	{
		outDate = (Date) jtxOutDate.getModel().getValue();
		inDate = (Date) jtxInDate.getModel().getValue();
		if(inDate==null)
		{
			JOptionPane.showMessageDialog(null,"You haven select Check-out date !!",
					"Error!!",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(outDate==null)
		{
			JOptionPane.showMessageDialog(null,"You haven select Check-out date !!",
					"Error!!",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(ctrl.getSelcCount()==0)
		{
			JOptionPane.showMessageDialog(null,"Please at least select a room !!",
					"Warning!!",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			new RevFrame(ctrl,inDate,outDate,day);
		}
	}
	
	public void singleRev(Room room)
	{
		Reserve();
		ctrl.saveSecRoom(room, 0);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Image background = null;
    	
    	try {
			background = ImageIO.read(new File("img/right.png"));
			
		} catch (IOException e) {

			e.printStackTrace();
		}

    	g.drawImage(background, 0, 0, 265, 682, this);
    	super.paintComponent(g);

	}
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
	
	public void getRevStatus(Date inDate,Date outDate)
	{
		int current=0;
		String InDate,OutDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		InDate = sdf.format(inDate);
		if(outDate==null)
		{
			OutDate=InDate;
			current=1;
		}
		else
			OutDate = sdf.format(outDate);

		HashMap status = ctrl.getRoomStatus(InDate,OutDate,current);
		lblSRoom.setText(status.get("TYP01")+"/34");
		lblDRoom.setText(status.get("TYP02")+"/51");
		lblTWRoom.setText(status.get("TYP03")+"/34");
		lblFRoom.setText(status.get("TYP04")+"/51");
		
	}
	
	public void getNowStatus()
	{
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		
		if(hour>=12)
			getRevStatus(new Date(), null);
		else
		{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			Date yesterday = cal.getTime();
			getRevStatus(yesterday, null);
		}
			
	}
	
	public static int calcDays(Date a, Date b)
	{
	    int tempDifference = 0;
	    int difference = 0;
	    Calendar earlier = Calendar.getInstance();
	    Calendar later = Calendar.getInstance();

	    if (a.compareTo(b) < 0)
	    {
	        earlier.setTime(a);
	        later.setTime(b);
	    }
	    else
	    {
	        earlier.setTime(b);
	        later.setTime(a);
	    }

	    while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR))
	    {
	        tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
	        difference += tempDifference;

	        earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
	    }

	    if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR))
	    {
	        tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
	        difference += tempDifference;

	        earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
	    }

	    return difference;
	}
	
	class AncestorLis implements AncestorListener
	{
		
		@Override
		public void ancestorRemoved(AncestorEvent arg0) {
			outDate = (Date) jtxOutDate.getModel().getValue();
			inDate = (Date) jtxInDate.getModel().getValue();
			Date todayIn = today; 
			
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if(hour<12)
			{
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -2);
				todayIn = cal.getTime();
			}
			
			if(inDate!=null&&todayIn.compareTo(inDate)>0)
			{
				if(hour<12)
					JOptionPane.showMessageDialog(null,"Date choosen must be after yesterday !!",
						"Error!!",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,"Date choosen must be after today !!",
							"Error!!",JOptionPane.INFORMATION_MESSAGE);
				jtxInDate.getModel().setSelected(false);
			}
			else if(outDate!=null&&today.compareTo(outDate)>0)
			{
				JOptionPane.showMessageDialog(null,"Date choosen must be after today !!",
						"Error!!",JOptionPane.INFORMATION_MESSAGE);
				jtxOutDate.getModel().setSelected(false);
			}
			else if(inDate!=null&&outDate!=null)
			{
				day=calcDays(inDate, outDate);
				if(day==0)
				{
					JOptionPane.showMessageDialog(null,"Date choosen cannot be same day!!",
							"²»ÒªÇ·´ò !!",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(inDate.compareTo(outDate)>0)
				{
					JOptionPane.showMessageDialog(null,"Check-In date must be less than Check-out date !!",
							"Error!!",JOptionPane.INFORMATION_MESSAGE);
					jtxInDate.getModel().setSelected(false);
					jtxOutDate.getModel().setSelected(false);
				}
				else
				{
					getRevStatus(inDate, outDate);
					lblDays.setText(day+" Days");
				}		
			}
						
		}
		
		@Override
		public void ancestorMoved(AncestorEvent arg0) {

		}
		
		@Override
		public void ancestorAdded(AncestorEvent arg0) {
			
			
		}
	}
	
	class MouseLis implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			
			if(l.getText().equals("1"))
				new CheckInFrame(ctrl,"");
			else if(l.getText().equals("2"))
				btnException.setIcon((new ImageIcon("img/bottom/2h.png")));
			else if(l.getText().equals("3"))
			{
				getNowStatus();
				jtxInDate.getModel().setSelected(false);
				jtxOutDate.getModel().setSelected(false);
			}
				
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			
			if(l.getText().equals("1"))
				btnCheckIn.setIcon((new ImageIcon("img/bottom/1h.png")));
			else if(l.getText().equals("2"))
				btnException.setIcon((new ImageIcon("img/bottom/2h.png")));
			else if(l.getText().equals("3"))
				btnChkCRT.setIcon((new ImageIcon("img/bottom/3h.png")));

			RightPanel.this.revalidate();
			RightPanel.this.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			
			if(l.getText().equals("1"))
				btnCheckIn.setIcon((new ImageIcon("img/bottom/1.png")));
			else if(l.getText().equals("2"))
				btnException.setIcon((new ImageIcon("img/bottom/2.png")));
			else if(l.getText().equals("3"))
				btnChkCRT.setIcon((new ImageIcon("img/bottom/3.png")));
			RightPanel.this.revalidate();
			RightPanel.this.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
