import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class Report extends RecBack {

	private JLabel lblR1;
	private JLabel lblR2;
	private JLabel lblReport;
	private JPanel panel;
	private JLabel lblReservation;
	private Controller ctrl;
	private JLabel lblNewLabel;

	public Report(Controller ctrl) {
		super.setWrapper(110, 80, 1044, 522);
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		lblR1 = new JLabel("<html><Center>Daily Reservation Detail Report</Center></html>");
		lblR1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 17));
		lblR1.setBounds(130, 181, 220, 312);
		lblR1.setHorizontalTextPosition(JLabel.CENTER);
		lblR1.setVerticalTextPosition(JLabel.BOTTOM);
		lblR1.setIcon(new ImageIcon("img/report/2.png"));
		lblR1.addMouseListener(new clickLis());
		add(lblR1);
		
		lblR2 = new JLabel("<html><Center>Monthly Reserved Room Type Summary Report</Center></html>");
		lblR2.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblR2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblR2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 17));
		lblR2.setBounds(391, 181, 220, 312);
		lblR2.setIcon(new ImageIcon("img/report/3.png"));
		lblR2.addMouseListener(new clickLis());
		add(lblR2);
		
		lblReport = new JLabel("<html><Center>Top 3 Month Lowest Visitor Exception Report</Center></html>");
		lblReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReport.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReport.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 17));
		lblReport.setBounds(652, 181, 220, 312);
		lblReport.setIcon(new ImageIcon("img/report/1.png"));
		lblReport.addMouseListener(new clickLis());
		add(lblReport);
		
		lblReservation  = new JLabel("<html><Center>Payment Type Summary Report</Center></html>");
		lblReservation.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblReservation.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReservation.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 17));
		lblReservation.setIcon(new ImageIcon("img/report/4.png"));
		lblReservation.setBounds(913, 181, 220, 312);
		lblReservation.addMouseListener(new clickLis());
		add(lblReservation);
		
		lblNewLabel = new JLabel("REPORT");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 28));
		lblNewLabel.setBounds(130, 119, 162, 30);
		add(lblNewLabel);
		
		
		

		
		
	}
	
	class clickLis implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getSource()==lblR1)
			{
				ctrl.SetPanel(new DetailReport1(ctrl));
			}
			else if(arg0.getSource()==lblR2)
			{	
				ctrl.SetPanel(new Report_Rtype(ctrl));
			}
			else if(arg0.getSource()==lblReport)
			{
				ctrl.SetPanel(new ExcReport1(ctrl));
			}
			else if(arg0.getSource()==lblReservation)
			{
				ctrl.SetPanel(new payTypeRpt(ctrl));
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
