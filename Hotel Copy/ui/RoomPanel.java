import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.swing.JComboBox;


public class RoomPanel extends JComponent {
	private RoomIcon ri;
	private String[] floor={"1","2","3","4","5","6","7","8","9","10"};
	private char Afloor='A';
	private Controller ctrl;
	private int info=0;
	private JLabel lblRoom;
	private JLabel lblCheckInN,lblAvaibleN,lblMaintenanceN,lblHKN;
	private HashMap current_status;
	private JLabel roomName;

	public RoomPanel(Controller ctrl) 
	{
		setLayout(null);
		setBounds(0, 0, 1000, 682);
		this.ctrl=ctrl;
		ctrl.setRoomPane(RoomPanel.this);
		
		setIcon();
		
	}
	
	public void setIcon()
	{
		UserInfo us = new UserInfo(ctrl);
		us.setBounds(10, 10, 150, 150);
		add(us);
		
		JLabel logo = new JLabel();
		logo.setBounds(820, 560, 220, 170);
		logo.setIcon(new ImageIcon("img/summer.png"));
		add(logo);
		
		JLabel lblFloor = new JLabel("Floor");
		lblFloor.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		lblFloor.setBounds(820, 50, 50, 21);
		add(lblFloor);
		
		ri = new RoomIcon(ctrl,info,Afloor+"01");
		ri.setBounds(220, 160, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"02");
		ri.setBounds(290, 160, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"03");
		ri.setBounds(400, 160, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"04");
		ri.setBounds(470, 160, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"05");
		ri.setBounds(580, 160, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"06");
		ri.setBounds(650, 160, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"07");
		ri.setBounds(760, 160, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"08");
		ri.setBounds(830, 160, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"09");
		ri.setBounds(220, 245, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"10");
		ri.setBounds(220, 330, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"11");
		ri.setBounds(290, 330, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"12");
		ri.setBounds(400, 330, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"13");
		ri.setBounds(470, 330, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"14");
		ri.setBounds(580, 330, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"15");
		ri.setBounds(650, 330, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"16");
		ri.setBounds(760, 330, 65, 60);
		add(ri);
		
		ri = new RoomIcon(ctrl,info,Afloor+"17");
		ri.setBounds(830, 330, 65, 60);
		add(ri);
		
		JComboBox<Object> comboBox = new JComboBox<Object>(floor);
		comboBox.setBounds(875, 50, 60, 21);
		add(comboBox);
		comboBox.setSelectedIndex(Afloor-65);
		
		lblRoom = new JLabel("Deluxe Room");
		lblRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 26));
		lblRoom.setBounds(470, 89, 199, 45);
		add(lblRoom);
		getRoomName();
		
		comboBox.addItemListener(new FloorLis());
		
		if(info==1)
		{
			CurrentStatus cr = new CurrentStatus(current_status);
			cr.setBounds(40, 490, 285, 170);
			add(cr);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		@SuppressWarnings("unused")
		Image background = null,glass = null;
    	
    	try {
			background = ImageIO.read(new File("img/test.png"));
			//glass = ImageIO.read(new File("img/test2.png"));
			
		} catch (IOException e) {

			e.printStackTrace();
		}

    	g.drawImage(background, 0, 0, 1000, 682, this);
    	//g.drawImage(glass, 0, 0, 1000, 682, this);
    	super.paintComponent(g);

	}
	
	class FloorLis implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Afloor = (char) (64+Integer.parseInt(e.getItem().toString()));
				removeAll();
				setIcon();
				revalidate();
				repaint();
			}       
		}
	}
	
	public void getRoomName()
	{
		if(Afloor=='A'||Afloor=='B')
			lblRoom.setText("Single Room");
		else if(Afloor=='C'||Afloor=='D'||Afloor=='E')
			lblRoom.setText("Double Room");
		else if(Afloor=='F'||Afloor=='G')
			lblRoom.setText("Twin Room");
		else if(Afloor=='H'||Afloor=='I'||Afloor=='J')
			lblRoom.setText("Family Room");
	}
	
	public void setCurrentStatus(HashMap current_status, int current)
	{
		this.current_status=current_status;
		info=current;
	}

}
