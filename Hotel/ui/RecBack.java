import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


@SuppressWarnings("serial")
public class RecBack extends JComponent
{
	private int width;
	private int height;
	private Image background;
	private int x,y;
	//private Graphics g;
	
	public void setWrapper(int x,int y,int width,int height)
	{
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		
		//revalidate();

	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
    	try {
			background = ImageIO.read(new File("img/test.png"));//±³¾°Í¼À´µÄ...
		} catch (IOException e) {

			e.printStackTrace();
		}
    	background.getScaledInstance(width, height,Image.SCALE_SMOOTH);
    	g.drawImage(background, x, y, width, height, this);

        super.paintComponent(g);
		
	}
	
	

	
	
}
