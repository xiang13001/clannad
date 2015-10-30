import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RootPaneContainer;
import javax.swing.plaf.RootPaneUI;




public class Login extends RecBack
{

	private JTextField UserField;
	private JPasswordField PasswordField;
	private JButton btnLogin;
	private JLabel lblForgetPassword;
	private Controller ctrl;
	private Staff staff;
	private JLayeredPane layeredPane;
	private Image background;
	
	public Login(Controller ctrl) 
	{
		super.setWrapper(166, 125, 906, 349);
		
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		JLabel userIcon = new JLabel("");
		userIcon.setBounds(220, 170, 256, 256);
		userIcon.setIcon(new ImageIcon("img/User.png"));
		add(userIcon);
		
		JLabel lblUser = new JLabel("USER");
		lblUser.setBounds(537, 196, 210, 45);
		lblUser.setFont(new Font("Arial Black", Font.PLAIN, 32));
		add(lblUser);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(537, 268, 210, 45);
		lblPassword.setFont(new Font("Arial Black", Font.PLAIN, 32));
		add(lblPassword);

		UserField = new JTextField();
		UserField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		UserField.setBounds(802, 203, 210, 30);
		add(UserField);
		UserField.setColumns(10);

		PasswordField = new JPasswordField();
		PasswordField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		PasswordField.setBounds(802, 275, 210, 30);
		add(PasswordField);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		btnLogin.setBounds(905, 379, 107, 30);
		add(btnLogin);
		btnLogin.addActionListener(new LoginLis());

		
		PasswordField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						staff=ctrl.Login(UserField.getText(), PasswordField.getText());
						if(staff.getStf_ID()==null)
						{
							JOptionPane.showMessageDialog(null,"Wrong user passwords",
									"Error",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							if(staff.getJob().equals("FDC"))
								ctrl.SetPanel(new RoomPreview(ctrl));
							else if(staff.getJob().equals("Manager"))
								ctrl.SetPanel(new ManagerPane(ctrl));

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Error",
								"Error!!",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}	
				}
				
			}
		});
		
		lblForgetPassword = new JLabel("Forget Password");
		lblForgetPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblForgetPassword.setForeground(Color.blue);
		lblForgetPassword.setBounds(537, 377, 150, 30);
		add(lblForgetPassword);
		
		lblForgetPassword.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ctrl.SetPanel(new FgPassCheck1(ctrl));
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblForgetPassword.setForeground(Color.BLUE);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblForgetPassword.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		
	}
	
	class LoginLis implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try {
				staff=ctrl.Login(UserField.getText(), PasswordField.getText());
				if(staff.getStf_ID()==null)
				{
					JOptionPane.showMessageDialog(null,"Wrong User or Password",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(staff.getJob().equals("FDC"))
						ctrl.SetPanel(new RoomPreview(ctrl));
					else if(staff.getJob().equals("Manager"))
						ctrl.SetPanel(new ManagerPane(ctrl));

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error!!!");
				e1.printStackTrace();
			}	
		}
	}
	

}
