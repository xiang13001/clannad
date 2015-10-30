import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.JPanel;


public class ManagerPane extends RecBack {

	private Controller ctrl;
	private JLabel lblStaff;
	private JLabel lblCustomer;
	private JLabel lblReport;
	private JPanel panel;
	private JLabel lblReservation;

	public ManagerPane(Controller ctrl) {
		super.setWrapper(110, 80, 1044, 522);
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		lblStaff = new JLabel("STAFF");
		lblStaff.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblStaff.setBounds(130, 181, 220, 312);
		lblStaff.setHorizontalTextPosition(JLabel.CENTER);
		lblStaff.setVerticalTextPosition(JLabel.BOTTOM);
		lblStaff.setIcon(new ImageIcon("img/staff.png"));
		lblStaff.addMouseListener(new clickLis());
		add(lblStaff);
		
		lblCustomer = new JLabel("CUSTOMER");
		lblCustomer.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCustomer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCustomer.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblCustomer.setBounds(391, 181, 220, 312);
		lblCustomer.setIcon(new ImageIcon("img/guest.png"));
		lblCustomer.addMouseListener(new clickLis());
		add(lblCustomer);
		
		lblReport = new JLabel("REPORT");
		lblReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReport.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReport.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblReport.setBounds(652, 181, 220, 312);
		lblReport.setIcon(new ImageIcon("img/report.png"));
		lblReport.addMouseListener(new clickLis());
		add(lblReport);
		
		lblReservation = new JLabel("RESERVATION");
		lblReservation.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReservation.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReservation.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblReservation.setIcon(new ImageIcon("img/calendar.png"));
		lblReservation.setBounds(913, 181, 220, 312);
		lblReservation.addMouseListener(new clickLis());
		add(lblReservation);
		
		
		

		
		
	}
	
	class clickLis implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getSource()==lblStaff)
			{
				ctrl.SetPanel(new StaffCtrl(ctrl));
			}
			else if(arg0.getSource()==lblCustomer)
			{
				ctrl.SetPanel(new ViewCustomer(ctrl));
			}
			else if(arg0.getSource()==lblReport)
			{
				ctrl.SetPanel(new Report(ctrl));
			}
			else if(arg0.getSource()==lblReservation)
			{
				ctrl.SetPanel(new RoomPreview(ctrl));
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
