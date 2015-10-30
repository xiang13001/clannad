import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class FgPassCheck2 extends RecBack {
	
	private JTextField AnsField;
	private JButton btnSubmit;
	private JLabel lblForgetPassword;
	private Controller ctrl;
	private Staff staff;
	private JLayeredPane layeredPane;
	private Image background;
	int i=3;

	public FgPassCheck2(Controller ctrl, Staff staff) 
	{
		super.setWrapper(166, 125, 906, 349);

		this.ctrl=ctrl;
		this.staff=staff;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		JLabel userIcon = new JLabel("");
		userIcon.setBounds(190, 170, 256, 256);
		userIcon.setIcon(new ImageIcon("img/check.png"));
		add(userIcon);
		
		JLabel lblQusetion = new JLabel("QUESTION");
		lblQusetion.setBounds(507, 196, 210, 45);
		lblQusetion.setFont(new Font("Arial Black", Font.PLAIN, 32));
		add(lblQusetion);
		
		lblForgetPassword = new JLabel(staff.getQues());
		lblForgetPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		lblForgetPassword.setBounds(772, 198, 364, 36);
		add(lblForgetPassword);
		
		JLabel lblAnswer = new JLabel("ANSWER");
		lblAnswer.setBounds(507, 268, 210, 45);
		lblAnswer.setFont(new Font("Arial Black", Font.PLAIN, 32));
		add(lblAnswer);
		
		AnsField = new JTextField();
		AnsField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		AnsField.setBounds(772, 275, 266, 30);
		add(AnsField);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		btnSubmit.setBounds(931, 396, 107, 30);
		add(btnSubmit);
		btnSubmit.addActionListener(new FindPassLis());
		
		

		
	}
	
	
	class FindPassLis implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(AnsField.getText().equals(staff.getAns()))
			{
				ctrl.SetPanel(new FgPassReset(ctrl, staff));
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Wrong Answer!!\n"
													+ "You Have more "+i+" time to try!!","Error!!",
													JOptionPane.ERROR_MESSAGE);
				i--;
				if(i==0)
				{
					JOptionPane.showMessageDialog(null,"Please contact the manager to reset password!!",
							"Error!!",JOptionPane.INFORMATION_MESSAGE);
					ctrl.SetPanel(new Login(ctrl));
				}
			}
		}
	}
	
}
