import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;


public class DetailReport2 extends JComponent {

	private BufferedImage background;
	private Controller ctrl;
	private JTable jtbStf;
	private JTextField textField;
	private JLabel lblfrist;
	private JLabel lblBack;
	private JLabel lblNext;
	private JLabel lblLast;
	private String inDate;
	private String outDate;
	private int paging;

	/**
	 * Create the panel.
	 * @param outDate 
	 * @param inDate 
	 */
	public DetailReport2(Controller ctrl, String inDate, String outDate, int paging) {
		this.ctrl=ctrl;
		this.inDate=inDate;
		this.outDate=outDate;
		this.paging=paging;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(166, 125, 950, 520);
		//add(layeredPane);

		Vector<String> Name = new Vector<String>(Arrays.asList("Reservation ID","Start Date","Customer ID","Person","Total Amount","Room type"));
		Vector<Vector> StfSet = ctrl.getReport(inDate,outDate,paging);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 366, 883, 233);
		add(scrollPane);
		
		jtbStf = new JTable(StfSet,Name);
		jtbStf.setCellSelectionEnabled(true);
		scrollPane.setViewportView(jtbStf);
		
		JLabel lblDate = new JLabel("Daily Reservation Detail Report ("+inDate+") to ("+outDate+")");
		lblDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 22));
		lblDate.setBounds(299, 315, 793, 30);
		add(lblDate);
		
		ReportTag rt = new ReportTag(ctrl);
		rt.setBounds(901, 148, 193, 135);
		rt.setPage(paging);
		add(rt);
		
		pageLis pl = new pageLis();
		
		lblfrist = new JLabel("");
		lblfrist.setBounds(520, 609, 35, 35);
		lblfrist.setIcon(new ImageIcon("img/report/First.png"));
		lblfrist.addMouseListener(pl);
		add(lblfrist);
		
		lblBack = new JLabel("");
		lblBack.setBounds(575, 609, 35, 35);
		lblBack.setIcon(new ImageIcon("img/report/Back.png"));
		lblBack.addMouseListener(pl);
		add(lblBack);
		
		lblNext = new JLabel("");
		lblNext.setBounds(688, 609, 35, 35);
		lblNext.setIcon(new ImageIcon("img/report/Next.png"));
		lblNext.addMouseListener(pl);
		add(lblNext);
		
		lblLast = new JLabel("");
		lblLast.setBounds(740, 609, 35, 35);
		lblLast.setIcon(new ImageIcon("img/report/Last.png"));
		lblLast.addMouseListener(pl);
		add(lblLast);
		
		textField = new JTextField();
		textField.setBounds(631, 609, 35, 35);
		add(textField);
		textField.setColumns(10);
		textField.setText(""+(paging+1));
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
    	try {
    		background = ImageIO.read(new File("img/reportb.png"));//±³¾°Í¼À´µÄ...
		} catch (IOException e) {

			e.printStackTrace();
		}
    	background.getScaledInstance(906, 349,Image.SCALE_SMOOTH);
    	g.drawImage(background,166, 125, 950, 520, this);

        super.paintComponent(g);
		
	}
	
	class pageLis implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==lblNext)
			{
				ctrl.BackPanel(new DetailReport2(ctrl, inDate, outDate, paging+1));
			}
			else if(e.getSource()==lblBack)
			{
				ctrl.BackPanel(new DetailReport2(ctrl, inDate, outDate, paging-1));
			}
			else if(e.getSource()==lblfrist)
			{
				ctrl.BackPanel(new DetailReport2(ctrl, inDate, outDate, 0));
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
