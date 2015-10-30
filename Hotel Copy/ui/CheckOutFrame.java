import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class CheckOutFrame extends JFrame {

	private Controller ctrl;

	public CheckOutFrame(Controller ctrl, String RevID) {
		this.ctrl=ctrl;
		
		CheckOut c = new  CheckOut(ctrl,(JFrame) SwingUtilities.getRoot(this),RevID);
		setBounds(0, 0, 1006, 480);
        setLocationRelativeTo(null);
        setVisible(true);
		add(c);
		setTitle("Check-Out");
	}

}
