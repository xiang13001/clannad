import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StaffCtrl extends RecBack {

	private Controller ctrl;
	private JLabel lblAddStaff;
	private JLabel lblViewStaff;

	public StaffCtrl(Controller ctrl) 
	{
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		super.setWrapper(157, 126, 950, 430);
		
		lblAddStaff = new JLabel("ADD STAFF");
		lblAddStaff.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblAddStaff.setBounds(301, 220, 256, 312);
		lblAddStaff.setHorizontalTextPosition(JLabel.CENTER);
		lblAddStaff.setVerticalTextPosition(JLabel.BOTTOM);
		lblAddStaff.setIcon(new ImageIcon("img/add_staff.png"));
		lblAddStaff.addMouseListener(new clickLis());
		add(lblAddStaff);
		
		lblViewStaff = new JLabel("VIEW STAFF");
		lblViewStaff.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblViewStaff.setHorizontalTextPosition(SwingConstants.CENTER);
		lblViewStaff.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblViewStaff.setBounds(743, 220, 256, 312);
		lblViewStaff.setIcon(new ImageIcon("img/view_staff.png"));
		lblViewStaff.addMouseListener(new clickLis());
		add(lblViewStaff);
	}
	
	class clickLis implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getSource()==lblAddStaff)
			{
				ctrl.SetPanel(new AddStaff(ctrl));
			}
			else if(arg0.getSource()==lblViewStaff)
			{
				ctrl.SetPanel(new ViewStaff(ctrl));
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
