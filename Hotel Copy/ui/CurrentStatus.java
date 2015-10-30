import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CurrentStatus extends JComponent {
	
	private HashMap current_status;

	public CurrentStatus(HashMap current_status) {
		setLayout(null);
		this.current_status=current_status;
		
		JLabel lblRoomInfo = new JLabel("Current Status");
		lblRoomInfo.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		lblRoomInfo.setBounds(33, 16, 170, 22);
		add(lblRoomInfo);
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblAvailable.setBounds(45, 58, 54, 15);
		add(lblAvailable);
		
		JLabel lblHouseKeeping = new JLabel("House Keeping");
		lblHouseKeeping.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblHouseKeeping.setBounds(45, 133, 106, 15);
		add(lblHouseKeeping);
		
		JLabel lblMaintennance = new JLabel("Maintenance");
		lblMaintennance.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblMaintennance.setBounds(45, 108, 87, 15);
		add(lblMaintennance);
		
		JLabel lblCheckIn = new JLabel("Check In");
		lblCheckIn.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblCheckIn.setBounds(45, 83, 54, 15);
		add(lblCheckIn);
		
		JLabel lblAvaibleN = new JLabel("n");
		lblAvaibleN.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblAvaibleN.setBounds(171, 58, 80, 15);
		add(lblAvaibleN);
		
		JLabel lblCheckInN = new JLabel("n");
		lblCheckInN.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblCheckInN.setBounds(171, 83, 54, 15);
		add(lblCheckInN);
		
		JLabel lblMaintenanceN = new JLabel("n");
		lblMaintenanceN.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblMaintenanceN.setBounds(171, 108, 87, 15);
		add(lblMaintenanceN);
		
		JLabel lblHKN = new JLabel("n");
		lblHKN.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblHKN.setBounds(171, 133, 113, 15);
		add(lblHKN);
		
		if(current_status!=null)
		{
			lblAvaibleN.setText(current_status.get("A")+" / 170");
			lblCheckInN.setText(current_status.get("U")+" / "+current_status.get("R"));
			lblMaintenanceN.setText(current_status.get("H")+" / 170");
			lblHKN.setText(current_status.get("H")+" / 170");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		Image background = null;
    	
    	try {
			background = ImageIO.read(new File("img/test.png"));
			//glass = ImageIO.read(new File("img/test2.png"));
			
		} catch (IOException e) {

			e.printStackTrace();
		}

    	g.drawImage(background, 0, 0, 285, 170, this);
    	super.paintComponent(g);

	}

}
