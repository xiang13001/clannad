import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class CheckInFrame extends JFrame {

	private Controller ctrl;
	private CheckIn c;

	public CheckInFrame(Controller ctrl, String RevID) {
		this.ctrl=ctrl;
		
			c = new  CheckIn(ctrl,(JFrame) SwingUtilities.getRoot(this),RevID);
		setBounds(0, 0, 1006, 480);
        setLocationRelativeTo(null);
        setVisible(true);
		add(c);
		setTitle("Check-In");
	}

}
