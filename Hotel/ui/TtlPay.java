import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;


public class TtlPay extends JComponent {

	private Controller ctrl;
	private JTable jtbDetail;
	private JLabel lblSave;
	private String Trans_ID;

	public TtlPay(Controller ctrl, String revID, JLabel jtxTtlAmount) {
		this.ctrl=ctrl;
		setBounds(0, 0, 530, 320);
		
		Trans_ID = ctrl.getTransID(revID);
		
		Vector<String> Name = new Vector<String>(Arrays.asList("Describe","Quantity","Price"));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 45, 478, 235);
		add(scrollPane);
		
		jtbDetail = new JTable(ctrl.getTtlPay(revID),Name);
		jtbDetail.setCellSelectionEnabled(true);
		scrollPane.setViewportView(jtbDetail);
		scrollPane.setOpaque(true);
		
		DefaultTableModel model = (DefaultTableModel) jtbDetail.getModel();
		
		JLabel label = new JLabel("");
		label.setBounds(474, 10, 25, 25);
		label.setIcon(new ImageIcon("img/add.png"));
		add(label);
		
		
		label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(lblSave==null)
				{
					lblSave = new JLabel("");
					lblSave.setBounds(443, 10, 25, 25);
					lblSave.setIcon(new ImageIcon("img/save.png"));
					add(lblSave);
					lblSave.addMouseListener(new MouseListener() {
						
						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mousePressed(MouseEvent e) {
							ctrl.savePayDetail(getPayDetail(jtbDetail),Trans_ID);
							JOptionPane.showMessageDialog(null,"Payment detail save !!",
									"Saving complete",JOptionPane.ERROR_MESSAGE);
							jtxTtlAmount.setText(String.format("RM %6.2f", Double.parseDouble(ctrl.getTtlAmount(revID))));
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
					repaint();
					revalidate();
				}
				model.addRow(new Object[]{"", "", ""});
				
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
	
	public Object[][] getPayDetail (JTable table) {
	    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    Object[][] tableData = new Object[nRow][nCol];
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)
	            tableData[i][j] = dtm.getValueAt(i,j);
	    System.out.println("Data :"+tableData);
	    return tableData;
	}

}
