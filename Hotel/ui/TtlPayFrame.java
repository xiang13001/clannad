import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TtlPayFrame extends JFrame {



	private Controller ctrl;

	public TtlPayFrame(Controller ctrl, String revID, JLabel jtxTtlAmount) {
		this.ctrl=ctrl;
		
		TtlPay r = new TtlPay(ctrl,revID,jtxTtlAmount);
		setBounds(0, 0, 530, 370);
        setLocationRelativeTo(null);
        setVisible(true);
		getContentPane().add(r);
		setTitle("Total Payment Detail");
	}

}