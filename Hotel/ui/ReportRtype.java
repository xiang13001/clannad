import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;


public class ReportRtype extends JComponent {

	private BufferedImage background;
	private Controller ctrl;
	
	public ReportRtype(Controller ctrl, String date, String monthYear) {
		ArrayList<Double> values = new ArrayList<Double>();
		Double total = 0.0;
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		for(Double number: ctrl.getTypeReport(date))
		{
			total+=number;
		}
		if(total!=0.0)
		{
			for(Double number: ctrl.getTypeReport(date))
			{
				values.add((number/total)*100);
			}
	
			ArrayList<Color> colors = new ArrayList<Color>();
			colors.add(new Color(255, 153, 102));
			colors.add(new Color(128,0,128));
			colors.add(new Color(0,191,255));
			colors.add(Color.RED);
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBounds(166, 125, 950, 520);
			add(layeredPane);
					layeredPane.setLayout(null);
			
					
					JLabel lblDate = new JLabel("Reserved Room Type");
					lblDate.setBounds(27, 138, 793, 30);
					layeredPane.add(lblDate);
					lblDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 22));
			
			
			PieChart pieChart = new PieChart(values, colors);
			pieChart.setBounds(340, 192, 280, 280);
			layeredPane.add(pieChart);
			
			JLabel lblPieChart = new JLabel("( "+monthYear+" )");
			lblPieChart.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 22));
			lblPieChart.setBounds(37, 169, 212, 30);
			layeredPane.add(lblPieChart);
			
			ReportTag rt = new ReportTag(ctrl);
			rt.setBounds(734, 22, 193, 135);
			layeredPane.add(rt);
			
			JLayeredPane layeredPane_1 = new JLayeredPane();
			layeredPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			layeredPane_1.setBounds(710, 349, 209, 135);
			layeredPane.add(layeredPane_1);
			layeredPane_1.setBackground(SystemColor.menu);
			layeredPane_1.setOpaque(true);
			
			JLabel lblSingleRoom = new JLabel("¨}  Single Room    "+String.format("%8.2f%%", values.get(0)));
			lblSingleRoom.setForeground(new Color(255, 153, 102));
			lblSingleRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
			lblSingleRoom.setBounds(10, 10, 189, 15);
			layeredPane_1.add(lblSingleRoom);
			
			JLabel lblDoubleRoom = new JLabel("¨}  Double Room  "+String.format("%8.2f%%", values.get(1)));
			lblDoubleRoom.setForeground(new Color(153, 0, 102));
			lblDoubleRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
			lblDoubleRoom.setBounds(10, 40, 189, 15);
			layeredPane_1.add(lblDoubleRoom);
			
			JLabel lblTwinRoom = new JLabel  ("¨}  Twin Room      "+String.format("%8.2f%%", values.get(2)));
			lblTwinRoom.setForeground(new Color(51, 0, 255));
			lblTwinRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
			lblTwinRoom.setBounds(10, 70, 189, 15);
			layeredPane_1.add(lblTwinRoom);
			
			JLabel lblFamilyRoom = new JLabel("¨}  Family Room    "+String.format("%8.2f%%", values.get(3)));
			lblFamilyRoom.setForeground(new Color(255, 0, 0));
			lblFamilyRoom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
			lblFamilyRoom.setBounds(10, 100, 189, 15);
			layeredPane_1.add(lblFamilyRoom);
		}
		
		
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
