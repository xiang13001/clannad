import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class ReportTag extends JComponent {

	private Controller ctrl;
	private JLabel lblPage;

	public ReportTag(Controller ctrl) {
		setLayout(null);
		setBounds(46, 41, 193, 135);
		this.ctrl=ctrl;
		
		JLabel label = new JLabel("Staff ID");
		label.setBounds(10, 35, 54, 15);
		add(label);
		
		JLabel label_1 = new JLabel("Produced by :");
		label_1.setBounds(10, 10, 78, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("Printed Date : ");
		label_2.setBounds(10, 60, 92, 15);
		add(label_2);
		
		JLabel label_3 = new JLabel("Printed Time : ");
		label_3.setBounds(10, 85, 92, 15);
		add(label_3);
		
		JLabel label_4 = new JLabel("Pages :");
		label_4.setBounds(10, 110, 54, 15);
		add(label_4);
		
		JLabel lblStaffName = new JLabel("New label");
		lblStaffName.setBounds(113, 10, 54, 15);
		add(lblStaffName);
		lblStaffName.setText(ctrl.getStaff().getStf_Name());
		
		JLabel lblStfID = new JLabel(ctrl.getStaff().getStf_ID());
		lblStfID.setBounds(113, 35, 54, 15);
		add(lblStfID);
		
		JLabel lblDate = new JLabel(dateCon(new Date()));
		lblDate.setBounds(113, 60, 70, 15);
		add(lblDate);
		
		JLabel lblTime = new JLabel(timeCon(new Date()));
		lblTime.setBounds(113, 85, 70, 15);
		add(lblTime);
		
		lblPage = new JLabel("1 of 1");
		lblPage.setBounds(113, 110, 54, 15);
		add(lblPage);
		
		setBorder(new BevelBorder(BevelBorder.RAISED));

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
	
	public String timeCon(Date date) {
		String date1 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		if(date!=null)
			date1= formatter.format(date);
		else
			System.out.println("Date fail to convert!!");
	    return date1;
	}

	public void setPage(int paging) {
		lblPage.setText((paging+1)+"");
		
	}

}
