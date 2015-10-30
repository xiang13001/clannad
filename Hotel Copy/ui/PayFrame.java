import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class PayFrame extends JFrame {



	private Controller ctrl;

	public PayFrame(Controller ctrl,int day,Double price) {
		this.ctrl=ctrl;
		
		PayDetail r = new PayDetail(ctrl,this,day,price);
		setBounds(0, 0, 530, 320);
        setLocationRelativeTo(null);
        setVisible(true);
		getContentPane().add(r);
		setTitle("Payment Detail");
		setAlwaysOnTop (true);
	}

}
