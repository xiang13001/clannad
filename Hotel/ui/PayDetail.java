import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;


public class PayDetail extends JComponent {

	private Controller ctrl;
	private PayFrame payFrame;
	private JTable jtbDetail;

	public PayDetail(Controller ctrl,PayFrame payFrame, int day,Double price) {
		this.ctrl=ctrl;
		this.payFrame=payFrame;
		setVisible(true);
		setBounds(0, 0, 530, 320);
		
		Vector<String> Name = new Vector<String>(Arrays.asList("Room Type","Quantity","Price"));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 496, 235);
		add(scrollPane);
		
		jtbDetail = new JTable(ctrl.getPayDetail(day),Name);
		jtbDetail.setCellSelectionEnabled(true);
		scrollPane.setViewportView(jtbDetail);
		scrollPane.setOpaque(true);
		
		JLabel lblTtlPrice = new JLabel("");
		lblTtlPrice.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		lblTtlPrice.setBounds(287, 240, 219, 50);
		add(lblTtlPrice);
		lblTtlPrice.setText(String.format("Total    RM %6.2f", price));
		
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
}
