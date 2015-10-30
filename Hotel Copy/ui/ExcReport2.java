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


public class ExcReport2 extends JComponent {

	private BufferedImage background;
	private Controller ctrl;
	private JTable jtbStf;
	private JTextField textField;
	private JLabel lblfrist;
	private JLabel lblBack;
	private JLabel lblNext;
	private JLabel lblLast;
	private String year;
	private int paging;

	/**
	 * Create the panel.
	 * @param outDate 
	 * @param inDate 
	 */
	public ExcReport2(Controller ctrl, String year) {
		this.ctrl=ctrl;
		this.year=year;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		System.out.println("test "+year);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(166, 125, 950, 520);
		//add(layeredPane);

		Vector<String> Name = new Vector<String>(Arrays.asList("Month","Visitor"));
		Vector<Vector> StfSet = ctrl.getException(year);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 366, 883, 233);
		add(scrollPane);
		
		jtbStf = new JTable(StfSet,Name);
		jtbStf.setCellSelectionEnabled(true);
		scrollPane.setViewportView(jtbStf);
		
		JLabel lblDate = new JLabel("Lowest Visitor Exception Report");
		lblDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 22));
		lblDate.setBounds(299, 315, 793, 30);
		add(lblDate);
		
		ReportTag rt = new ReportTag(ctrl);
		rt.setBounds(901, 148, 193, 135);
		rt.setPage(paging);
		add(rt);
		
		
		
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
	
}