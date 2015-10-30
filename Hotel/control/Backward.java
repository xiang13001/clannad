

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Backward implements MouseListener
{
	private JPanel jp;
	private Controller ctrl;
	private JLabel btnBack;
	public Backward(JPanel jp,Controller ctrl,JLabel btnBack)
	{
		this.jp=jp;
		this.ctrl=ctrl;
		this.btnBack=btnBack;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		btnBack.setIcon(new ImageIcon(BackPanel.class.getResource("backward.png")));
		btnBack.revalidate();
		btnBack.repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		btnBack.setIcon(new ImageIcon(BackPanel.class.getResource("empty.png")));
		jp.revalidate();
		jp.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		JComponent jc = ctrl.getJCStack();
		
		if(jc instanceof AddStaff|| jc instanceof ViewStaff)
			ctrl.BackPanel(new StaffCtrl(ctrl));
		if(jc instanceof StaffCtrl||jc instanceof DetailReport1||jc instanceof ViewCustomer||jc instanceof Report)
			ctrl.BackPanel(new ManagerPane(ctrl));
		if(jc instanceof FgPassCheck1||jc instanceof FgPassCheck2||jc instanceof FgPassReset)
			ctrl.BackPanel(new Login(ctrl));
		if(ctrl.getStaff().getJob().equals("Manager")&&jc instanceof RoomPreview)
			ctrl.BackPanel(new ManagerPane(ctrl));
		
		if(ctrl.getStaff().getJob().equals("FDC")&&jc instanceof RoomPreview||jc instanceof ManagerPane)
		{
			if(JOptionPane.showConfirmDialog(null, "Logout?")==0)
				ctrl.BackPanel(new Login(ctrl));
			else
				ctrl.SetPanel(jc);

		}
	}
}
