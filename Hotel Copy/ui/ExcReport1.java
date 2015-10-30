import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ExcReport1 extends JComponent {

	private Controller ctrl;
	private Image background;
	private DatePicker jtxInDate;
	private DatePicker jtxOutDate;
	private Date outDate;
	private Date inDate;
	private JComboBox<String> year;

	public ExcReport1(Controller ctrl) {
		//super.setWrapper(110, 80, 1044, 522);
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(110, 80, 1044, 522);
		//add(layeredPane);
		
		String years[] = {"2014","2015","2016","2017","2018"};
		year = new JComboBox<>(years);
		year.setBounds(538, 290, 117, 35);
		add(year);
		
		JLabel lblStartDate = new JLabel("Year");
		lblStartDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		lblStartDate.setBounds(389, 295, 139, 20);
		add(lblStartDate);
		
		JLabel lblDetailReport = new JLabel("Lowest Visitor Exception Report");
		lblDetailReport.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblDetailReport.setBounds(199, 132, 651, 61);
		add(lblDetailReport);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			ctrl.SetPanel(new ExcReport2(ctrl, year.getSelectedItem().toString()));
			}
		});
		btnSubmit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnSubmit.setBounds(689, 414, 90, 29);
		add(btnSubmit);
		

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
    	background.getScaledInstance(906, 349,Image.SCALE_SMOOTH);
    	g.drawImage(background,166, 125, 906, 349, this);

        super.paintComponent(g);
		
	}
	
}