import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class Logo extends JPanel
{
    float alpha;
    
	public Logo(float alpha)
	{
		this.alpha=alpha;
		setBackground(Color.WHITE);
    }


    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        
        if(alpha<9&&alpha!=1)
        {
            AffineTransform at=new AffineTransform();
            at.setToRotation(alpha-1f);
            g2d.transform(at);
        }
        
        AlphaComposite alphaComposite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);
      
        ImageIcon imageIcon= new ImageIcon(getClass().getResource("Logo.png"));
        g2d.drawImage(imageIcon.getImage(),0,0,null);

    }
	

}
