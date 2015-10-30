import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;





public class MainFrame extends JFrame
{
	static MainFrame mf;
	static Controller ctrl;
	static float alpha = 1;
	
	public MainFrame()
	{	
        setBounds(10, 10, 1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setTitle("SUMMER HOTEL");
        setVisible(true);
        this.getContentPane().setBackground(Color.WHITE);
        //setResizable(false);
        
        
	}

	public static void main(String[] args)
	{
		
		mf = new MainFrame();
		ctrl = new Controller();
		ctrl.SetFrame(mf);
		
		Timer time = new Timer(1000,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				 
				ctrl.UpdatePanel(new Logo(alpha));
				
				((Timer) e.getSource()).setDelay(10);
				
				alpha-=0.01;
				if(alpha<0)
				{
					ctrl.SetPanel(new Login(ctrl));
					((Timer) e.getSource()).stop();
				}
					
			}
		});
		time.start();
			
		
		
		
	}

}

