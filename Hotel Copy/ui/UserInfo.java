import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;



public class UserInfo extends JComponent {

	

	private Controller ctrl;
	private Staff staff;

	public UserInfo(Controller ctrl) 
	{
		this.ctrl=ctrl;
		staff=ctrl.getStaff();
		setLayout(null);
		
		JLabel userIcon = new JLabel("");
		userIcon.setBounds(0, 0, 70, 70);
		userIcon.setIcon(new ImageIcon("img/"+staff.getJob()+".png"));
		add(userIcon);
		
		JLabel lblUsername = new JLabel(staff.getStf_Name());
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		lblUsername.setBounds(70, 13, 90, 21);
		add(lblUsername);
		
		JLabel lblJob= new JLabel("Front Desk");
		lblJob.setForeground(new Color(0, 0, 0));
		lblJob.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		if(staff.getJob().equals("FDC"))
			lblJob = new JLabel("Front Desk");
		else
			lblJob = new JLabel("Manager");
		lblJob.setBounds(70, 38, 90, 21);
		add(lblJob);
		

		
		
	}
	
	public static BufferedImage makeRoundedCorner(BufferedImage image) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = output.createGraphics();

	    // This is what we want, but it only does hard-clipping, i.e. aliasing
	    // g2.setClip(new RoundRectangle2D ...)

	    // so instead fake soft-clipping by first drawing the desired clip shape
	    // in fully opaque white with antialiasing enabled...
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    if(w>h)
	    	g2.fill((Shape) new Ellipse2D.Double((w/2)-(h/2), 0, h, h));
	    

	    // ... then compositing the image on top,
	    // using the white shape from above as alpha source
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);

	    g2.dispose();

	    return output;
	}
	

}
