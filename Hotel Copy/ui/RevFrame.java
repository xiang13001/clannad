import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class RevFrame extends JFrame {



	private Controller ctrl;

	public RevFrame(Controller ctrl, Date inDate, Date outDate, int day) {
		this.ctrl=ctrl;
		
		Reservation r = new Reservation(ctrl,inDate,outDate,day,this);
		setBounds(0, 0, 1100, 580);
        setLocationRelativeTo(null);
        setVisible(true);
		add(r);
		setTitle("Reservation");
		//setAlwaysOnTop (true);
	}

}
