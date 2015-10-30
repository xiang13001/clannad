import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class FgPassCheck1 extends RecBack {
	
	private JTextField NameField,ICField;
	private JButton btnSubmit;
	private Controller ctrl;
	private Staff staff;

	public FgPassCheck1(Controller ctrl) 
	{
		super.setWrapper(166, 125, 906, 349);

		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		JLabel userIcon = new JLabel("");
		userIcon.setBounds(190, 170, 256, 256);
		userIcon.setIcon(new ImageIcon("img/check.png"));
		add(userIcon);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(507, 196, 210, 45);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 32));
		add(lblName);
		
		JLabel lblIC = new JLabel("IC");
		lblIC.setFont(new Font("Arial Black", Font.PLAIN, 32));
		lblIC.setBounds(507, 268, 210, 45);
		add(lblIC);
		
		NameField = new JTextField();
		NameField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		NameField.setBounds(760, 206, 266, 30);
		add(NameField);
		
		ICField = new JTextField();
		ICField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		ICField.setBounds(760, 275, 266, 30);
		add(ICField);
		
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
			staff = ctrl.FgPassCheck1(NameField.getText(), ICField.getText());
			if(staff.getStf_ID()==null)
			{
				JOptionPane.showMessageDialog(null,"IC and Name mismatch !!",
						"Error!!",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
					ctrl.SetPanel(new FgPassCheck2(ctrl,staff));
			}
		}
	}
	
}
