import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;


public class DetailReport1 extends JComponent {

	private Controller ctrl;
	private Image background;
	private DatePicker jtxInDate;
	private DatePicker jtxOutDate;
	public AncestorLis anLis = new AncestorLis();
	private Date outDate;
	private Date inDate;
	
	public DetailReport1(Controller ctrl) {
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(110, 80, 1044, 522);
		//add(layeredPane);
		
		UtilDateModel Inmodel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl IndatePanel = new JDatePanelImpl(Inmodel, p);
		jtxInDate = new DatePicker(IndatePanel, new DateLabelFormatter());
		jtxInDate.setBounds(402, 332, 167, 23);
		add(jtxInDate);
		IndatePanel.addAncestorListener(anLis);
		
		
		UtilDateModel Outmodel = new UtilDateModel();
		JDatePanelImpl OutdatePanel = new JDatePanelImpl(Outmodel, p);
		
		jtxOutDate = new DatePicker(OutdatePanel, new DateLabelFormatter());
		jtxOutDate.setBounds(869, 332, 167, 23);
		add(jtxOutDate);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lblStartDate.setBounds(296, 332, 96, 20);
		add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lblEndDate.setBounds(773, 332, 90, 20);
		add(lblEndDate);
		
		JLabel lblDetailReport = new JLabel("Detail Report");
		lblDetailReport.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		lblDetailReport.setBounds(199, 132, 206, 61);
		add(lblDetailReport);
		OutdatePanel.addAncestorListener(anLis);

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
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
	
	class AncestorLis implements AncestorListener
	{


		@Override
		public void ancestorRemoved(AncestorEvent arg0) {
			outDate = (Date) jtxOutDate.getModel().getValue();
			inDate = (Date) jtxInDate.getModel().getValue();

			if(inDate!=null&&outDate!=null)
			ctrl.SetPanel(new DetailReport2(ctrl,dateCon(inDate),dateCon(outDate),0));
		}
		
		@Override
		public void ancestorMoved(AncestorEvent arg0) {

		}
		
		@Override
		public void ancestorAdded(AncestorEvent arg0) {
			
			
		}
	}
	
	public String dateCon(Date date) {
		String date1 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null)
			date1= formatter.format(date);
		else
			System.out.println("Date fail to convert!!");
	    return date1;
	}

}
