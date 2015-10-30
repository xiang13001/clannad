import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuListener;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;


public class RoomIcon extends JComponent {
	private Controller ctrl;
	private Room room;
	private JLabel lblroom;
	private JPopupMenu popup;
	private JLabel lblRoomNum,lblGlassPane,lblStatus;
	ImageIcon[] roomIco = {new ImageIcon("img/room/room_ok.png"),
						new ImageIcon("img/room/room_x.png"),
						new ImageIcon("img/room/room_r.png"),
						new ImageIcon("img/room/room_m.png"),
						new ImageIcon("img/room/room_h.png"),
						new ImageIcon("img/room/single.jpg"),
						new ImageIcon("img/room/twin.jpg"),
						new ImageIcon("img/room/double.jpg"),
						new ImageIcon("img/room/family.jpg")};
	ImageIcon[] selectedIcon = { new ImageIcon("img/room/blank.png"),
								new ImageIcon("img/room/selected.png")};
	ImageIcon[] statusIcon = { new ImageIcon("img/room/room_in.png"),
								new ImageIcon("img/room/room_r.png"),
								new ImageIcon("img/room/blank.png")};
	private int IcoNum=1;
	private int info;
	private String RoomNum;
	private int selected,rev_status;

	
	
	public RoomIcon(Controller ctrl,int info,String RoomNum)
	{
		this.ctrl=ctrl;
		this.info=info;
		this.RoomNum=RoomNum;
		room = ctrl.getRoomStatus(RoomNum);
		
		if(room.getStatus().equals("A")||room.getStatus().equals("U")||room.getStatus().equals("R"))
		{
			if(room.getType_ID().equals("TYP01"))
				IcoNum=5;
			
			if(room.getType_ID().equals("TYP02"))
				IcoNum=6;
			
			if(room.getType_ID().equals("TYP03"))
				IcoNum=7;
			
			if(room.getType_ID().equals("TYP04"))
				IcoNum=8;
		}
		if(room.getStatus().equals("M"))
			IcoNum=3;
			
		else if(room.getStatus().equals("H"))
			IcoNum=4;
		
		
		lblroom = new JLabel(RoomNum);
		lblroom.setForeground(new Color(255, 250, 205));
		lblroom.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 16));
		lblroom.setBounds(0, 0, 65, 60);
		lblroom.setIcon(roomIco[IcoNum]);
		add(lblroom);
		lblroom.setVerticalTextPosition(JLabel.CENTER); 
		lblroom.setHorizontalTextPosition(JLabel.CENTER);
		
		selected=room.getSeleted();
		lblGlassPane = new JLabel();
		lblGlassPane.setBounds(0, 0, 65, 60);
		lblGlassPane.setIcon(selectedIcon[selected]);
		add(lblGlassPane);
		
		if(room.getStatus().equals("U"))
			rev_status=0;
		else if(room.getStatus().equals("R"))
			rev_status=1;
		else
			rev_status=2;
		
		lblStatus = new JLabel();
		lblStatus.setBounds(0, 0, 65, 60);
		lblStatus.setIcon(statusIcon[rev_status]);
		add(lblStatus);
		
		setComponentZOrder(lblroom, 2);
		setComponentZOrder(lblStatus, 1);
		setComponentZOrder(lblGlassPane, 0);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				 if(e.getButton() == MouseEvent.BUTTON1)
				    {
					 if(info==0&&rev_status==2)
					 {
					 	if(selected==1)
							selected=0;
						else if(selected==0)
							selected=1;
					 	lblGlassPane.setIcon(selectedIcon[selected]);
						ctrl.saveSecRoom(room,selected);
						revalidate();
						repaint();
					 }
					 else if(info==0&&rev_status!=2)
					 {
						 //do nothing
					 }
					 else
					 {
						 if(selected==1)
							 ctrl.getSameRev(room,0);
						 else
							 ctrl.getSameRev(room,1);
					 }
					 	
				    }
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		popup = new JPopupMenu();
		menuListener MListener = new menuListener();
		//popup.setBackground(new Color(0, true));	”–ø’‘ŸÕÊ...
		
		
		JMenuItem item;
		popup.add(item = new JMenuItem("Detail", new ImageIcon("1.gif")));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(MListener);
		popup.addSeparator();
		popup.add(item = new JMenuItem("Reservation", new ImageIcon("1.gif")));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(MListener);
	    popup.add(item = new JMenuItem("Check In", new ImageIcon("1.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(MListener);
	    popup.add(item = new JMenuItem("Check Out", new ImageIcon("1.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(MListener);
	    popup.addSeparator();
	    popup.add(item = new JMenuItem("Available", new ImageIcon("2.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(MListener);
	    popup.add(item = new JMenuItem("Maintenance", new ImageIcon("2.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(MListener);
	    popup.add(item = new JMenuItem("House Keeping", new ImageIcon("3.gif")));
	    item.setHorizontalTextPosition(JMenuItem.RIGHT);
	    item.addActionListener(MListener);


	    popup.setBorder(new BevelBorder(BevelBorder.RAISED));
	    addMouseListener(new MousePopupListener());
	}
	
	public void RoomSelected()
	{
		
	}
	
	class menuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			String roomID = room.getRoom_ID();
			if(command.equals("Available"))
			{
				ctrl.setRoomStatus("A",roomID);
				
				if(room.getType_ID().equals("TYP01"))
					IcoNum=5;
					
				else if(room.getType_ID().equals("TYP02"))
					IcoNum=6;
					
				else if(room.getType_ID().equals("TYP03"))
					IcoNum=7;
					
				else if(room.getType_ID().equals("TYP04"))
					IcoNum=8;
				lblroom.setIcon(roomIco[IcoNum]);
			}
			else if(command.equals("Maintenance"))
			{
				ctrl.setRoomStatus("M",roomID);
				lblroom.setIcon(roomIco[3]);
			}
			else if(command.equals("House Keeping"))
			{
				ctrl.setRoomStatus("H",roomID);
				lblroom.setIcon(roomIco[4]);
			}
			else if(IcoNum!=3&&IcoNum!=4)
			{
				System.out.println("Room Ico No "+IcoNum);
				if(command.equals("Reservation"))
				{
					if(rev_status==0||rev_status==1)
					{
						JOptionPane.showMessageDialog(null,"Room reserved !!",
								"Error!!",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						ctrl.saveSecRoom(room,1);
						ctrl.singleRoomRev(room);
					}
					
				}
				else if(command.equals("Check In"))
				{
					if(ctrl.getRoomRev(room)!=null)
						new CheckInFrame(ctrl,ctrl.getRoomRev(room));
				}
				else if(command.equals("Check Out"))
				{
					new CheckOutFrame(ctrl,ctrl.getRoomRev(room));
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Room Unavailable !!",
						"Error!!",JOptionPane.ERROR_MESSAGE);
			}
			
			
			revalidate();
			repaint();
		}
		
	}
	
	class MousePopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	      checkPopup(e);
	    }

	    public void mouseClicked(MouseEvent e) {
	      checkPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	      checkPopup(e);
	    }

	    private void checkPopup(MouseEvent e) {
	      if (e.isPopupTrigger()) {
	        popup.show(RoomIcon.this, e.getX(), e.getY());
	      }
	    }
	  }
	
	
}
