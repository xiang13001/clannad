import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class FgPassReset extends RecBack {
	
	private JTextField NewPassField,RePassField;
	private JButton btnSubmit;
	private Controller ctrl;
	private Staff staff;
	private String NewPass;

	public FgPassReset(Controller ctrl, Staff staff) 
	{
		super.setWrapper(166, 125, 906, 349);

		this.ctrl=ctrl;
		this.staff=staff;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		JLabel userIcon = new JLabel("");
		userIcon.setBounds(190, 170, 256, 256);
		userIcon.setIcon(new ImageIcon("img/check1.png"));
		add(userIcon);
		
		JLabel lblNewPass = new JLabel("New Password");
		lblNewPass.setBounds(507, 196, 269, 45);
		lblNewPass.setFont(new Font("Arial Black", Font.PLAIN, 32));
		add(lblNewPass);
		
		JLabel lblRe_Pass = new JLabel("Re-Type Pass");
		lblRe_Pass.setFont(new Font("Arial Black", Font.PLAIN, 32));
		lblRe_Pass.setBounds(507, 268, 269, 45);
		add(lblRe_Pass);
		
		NewPassField = new JTextField();
		NewPassField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		NewPassField.setBounds(786, 206, 240, 30);
		add(NewPassField);
		
		RePassField = new JTextField();
		RePassField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		RePassField.setBounds(786, 275, 240, 30);
		add(RePassField);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		btnSubmit.setBounds(919, 396, 107, 30);
		add(btnSubmit);
		btnSubmit.addActionListener(new FindPassLis());
		
		

		
	}
	
	
	class FindPassLis implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			NewPass = NewPassField.getText();
			if(NewPass.equals(RePassField.getText()))
			{
				ctrl.FgPassReset(staff.getStf_ID(), NewPass);
				ctrl.SetPanel(new Login(ctrl));
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Password mismatch !!",
						":(",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
