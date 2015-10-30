import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;



public class CheckOut extends JComponent implements TableModelListener{
	

	protected Controller ctrl;
	private JLabel jtxCheckOut;
	private JLabel jtcCheckIn;
	protected JTextField jtxReservation;
	private JTextField jtxIC;
	protected JTable jtbRoom;
	private JLabel jtxCusName;
	private JLabel jtxBookDate;
	private JLabel jtxTtlAmount;
	private JLabel jtxPayType;
	protected JLayeredPane jpTable;
	private int i=0,j=1;
	private JFrame checkInFrame;
	private Vector<Comparable> revDetail;
	private JLabel lblPaymentDetail;
	private JComboBox comboBox;

	public CheckOut(Controller ctrl, JFrame jFrame, String revID) {
		this.ctrl=ctrl;
		this.checkInFrame=jFrame;
		setLayout(null);
		setBounds(0, 0, 1006, 470);
		
		JLayeredPane jpGeneralInfo = new JLayeredPane() {
		};
		jpGeneralInfo.setBounds(41, 66, 311, 320);
		add(jpGeneralInfo);
		jpGeneralInfo.setBorder(new TitledBorder(null, "General Information", TitledBorder.LEADING, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12), null));
		jpGeneralInfo.setLayout(null);
		
		JLabel lblReservationId = new JLabel("Reservation ID");
		lblReservationId.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblReservationId.setBounds(30, 40, 128, 19);
		jpGeneralInfo.add(lblReservationId);
		
		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblCheckOut.setBounds(30, 200, 114, 15);
		jpGeneralInfo.add(lblCheckOut);
		
		JLabel lblCheckIn = new JLabel("Check In");
		lblCheckIn.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblCheckIn.setBounds(30, 170, 94, 15);
		jpGeneralInfo.add(lblCheckIn);
		
		
		jtxCheckOut = new JLabel();
		jtxCheckOut.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtxCheckOut.setBounds(165, 200, 100, 15);
		jpGeneralInfo.add(jtxCheckOut);

		
		jtcCheckIn = new JLabel();
		jtcCheckIn.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtcCheckIn.setBounds(165, 170, 100, 15);
		jpGeneralInfo.add(jtcCheckIn);

		
		JLabel lblBookDateTime = new JLabel("Booking Date");
		lblBookDateTime.setBounds(30, 140, 114, 17);
		jpGeneralInfo.add(lblBookDateTime);
		lblBookDateTime.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		
		jtxReservation = new JTextField(revID);
		jtxReservation.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtxReservation.setBounds(165, 38, 100, 21);
		jpGeneralInfo.add(jtxReservation);
		
		jtxBookDate = new JLabel("");
		jtxBookDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtxBookDate.setBounds(165, 141, 80, 15);

		jpGeneralInfo.add(jtxBookDate);
		
		JLabel lblCustomerIC = new JLabel("Customer IC");
		lblCustomerIC.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblCustomerIC.setBounds(30, 70, 128, 20);
		jpGeneralInfo.add(lblCustomerIC);
		
		jtxIC = new JTextField();
		jtxIC.setBounds(165, 70, 100, 21);
		jpGeneralInfo.add(jtxIC);
		jtxIC.setColumns(10);
		
		
		JLabel lblPaymentType = new JLabel("Payment Type");
		lblPaymentType.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblPaymentType.setBounds(30, 228, 114, 15);
		jpGeneralInfo.add(lblPaymentType);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblTotalAmount.setBounds(30, 257, 114, 15);
		jpGeneralInfo.add(lblTotalAmount);
		
		jtxPayType = new JLabel();
		jtxPayType.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtxPayType.setBounds(165, 228, 100, 15);
		jpGeneralInfo.add(jtxPayType);
		
		jtxTtlAmount = new JLabel();
		jtxTtlAmount.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtxTtlAmount.setBounds(165, 257, 100, 15);
		jpGeneralInfo.add(jtxTtlAmount);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblCustomerName.setBounds(30, 110, 114, 17);
		jpGeneralInfo.add(lblCustomerName);
		
		jtxCusName = new JLabel("");
		jtxCusName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		jtxCusName.setBounds(165, 110, 74, 15);
		jpGeneralInfo.add(jtxCusName);
		
		jpTable = new JLayeredPane() {
		};
		jpTable.setBorder(new TitledBorder(null, "Check Out Table", TitledBorder.LEADING, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12), null));
		jpTable.setBounds(385, 66, 580, 320);
		add(jpTable);
		jpTable.setLayout(null);
		
		
		JButton btnCheckIn = new JButton("Check Out");
		btnCheckIn.setBounds(872, 400, 93, 23);
		add(btnCheckIn);
		btnCheckIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int allOut=1;
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				Date nowDate = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				for(int i=0;i<jtbRoom.getRowCount();i++)
				{
					if(!((Boolean)jtbRoom.getValueAt(i, 3)).booleanValue())
						allOut=0;
				}
				if(comboBox.getSelectedIndex()==0&&allOut==1)
				{
					JOptionPane.showMessageDialog(null,"Please pay before check out",
							":(",JOptionPane.ERROR_MESSAGE);
					
				}else
				{
					ctrl.saveOutDetail(getInDetail(jtbRoom),jtxReservation.getText());
					ctrl.savePay(jtxReservation.getText());
					jFrame.dispose();
					ctrl.SetPanel(new RoomPreview(ctrl));
					if(hour>12&&nowDate.compareTo((Date) revDetail.get(9))>0)
						JOptionPane.showMessageDialog(null,"Late check out! \n Guest need to pay late check-out fee !",
								":(",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		lblPaymentDetail = new JLabel("Payment Detail");
		lblPaymentDetail.setForeground(new Color(153, 51, 255));
		lblPaymentDetail.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblPaymentDetail.setBounds(484, 288, 86, 15);
		jpTable.add(lblPaymentDetail);
		lblPaymentDetail.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				new TtlPayFrame(ctrl, revID,jtxTtlAmount);
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblPaymentDetail.setText("<html>Payment Detail</html>");
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblPaymentDetail.setText("<html><u>Payment Detail</u></html>");
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addTable();
		
		renewTable();
		jtxReservation.setEditable(false);
		jtxIC.setEditable(false);
		
		JLabel lblPaymentStatus = new JLabel("Payment Status");
		lblPaymentStatus.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		lblPaymentStatus.setBounds(30, 285, 105, 15);
		jpGeneralInfo.add(lblPaymentStatus);
		
		String[] status = {"Pending","Cleared"};
		comboBox = new JComboBox(status);
		comboBox.setBounds(165, 283, 100, 21);
		jpGeneralInfo.add(comboBox);

	}
	
	public Object[][] getInDetail (JTable table) {
	    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    Object[][] tableData = new Object[nRow][nCol];
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)
	            tableData[i][j] = dtm.getValueAt(i,j);
	    System.out.println("Data :"+tableData);
	    return tableData;
	}
	
	public void addTable()
	{
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 25, 525, 253);
		jpTable.add(scrollPane);
		
		Vector<String> Name = new Vector<String>(Arrays.asList("Room Number","Check-In Date Time","Check-Out Date Time","Selected"));
		
		DefaultTableModel model = new DefaultTableModel(ctrl.getInRoom(jtxReservation.getText()),Name);
		jtbRoom = new JTable(model)
		{
			 @Override
	         public Class getColumnClass(int column) {
	             switch (column) {
		             case 0:
	                     return String.class;
	                 case 1:
	                     return String.class;
	                 case 2:
	                     return String.class;
	                 default:
	                     return Boolean.class;
	             }
	         }
			 
			 @Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       return column == 3;
			   }
		};
		jtbRoom.setCellSelectionEnabled(true);
		jtbRoom.setPreferredScrollableViewportSize(jtbRoom.getPreferredSize());
		scrollPane.setViewportView(jtbRoom);
		model.addTableModelListener(this);
		repaint();
		revalidate();
	}
	
	public void renewTable()
	{
		revDetail = ctrl.getRevDetail(jtxReservation.getText());
		if(!revDetail.isEmpty())
		{
			jtxIC.setText(revDetail.get(1).toString());
			jtxCusName.setText(revDetail.get(2).toString());
			jtxBookDate.setText(revDetail.get(3).toString());
			jtcCheckIn.setText(revDetail.get(4).toString());
			jtxCheckOut.setText(revDetail.get(5).toString());
			jtxPayType.setText(revDetail.get(6).toString());
			jtxTtlAmount.setText(revDetail.get(7).toString());
			
		}
		else
		{
			JOptionPane.showMessageDialog(null,"No data retrive !!",
					"Error!!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Image background = null;
    	try {
			background = ImageIO.read(new File("img/rev_back.png"));//±³¾°Í¼À´µÄ...
		} catch (IOException e) {

			e.printStackTrace();
		}
    	background.getScaledInstance(1100, 580,Image.SCALE_SMOOTH);
    	g.drawImage(background, 0, 0, 1100, 580, this);

        super.paintComponent(g);
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.getColumn()==3)
		{
			int rowSelc = jtbRoom.getSelectedRow();
			if(!(boolean) jtbRoom.getValueAt(rowSelc, 3))
			{
				jtbRoom.setValueAt("", rowSelc, 2);
			}
			else
			{
				jtbRoom.setValueAt(dateTimeCon(new Date()), rowSelc, 2);
			}
		}

	}
	
	public String dateTimeCon(Date date) {
		String date1 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date!=null)
			date1= formatter.format(date);
		else
			System.out.println("Date time fail to convert!!");
	    return date1;
	}
}
