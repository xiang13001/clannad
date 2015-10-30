import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class BackPanel extends JPanel 
{
	private Image background;

	JLabel lblBack;
	int i;
	public BackPanel(Controller ctrl) 
	{
		setLayout(null);
		
		
		lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(BackPanel.class.getResource("empty.png")));
		lblBack.setBounds(0, 230, 65, 145);
		add(lblBack);
		
		lblBack.addMouseListener(new Backward(this,ctrl,lblBack));
		
		try {
			background = ImageIO.read(new File("img/background.png"));//±³¾°Í¼À´µÄ...
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }

}
