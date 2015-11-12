import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPanel;
import javax.swing.Popup;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;


@SuppressWarnings("serial")
public class DatePicker extends JDatePickerImpl {
	
	  private JFormattedTextField formattedTextField;
	  AbstractFormatter formatter;


	public DatePicker(JDatePanelImpl datePanel, AbstractFormatter formatter) {
		super(datePanel, formatter);
		this.formatter=formatter;
	}
	
	  
	  public void setText(String str)
	  {
		  formattedTextField = new JFormattedTextField(formatter);

		  formattedTextField.setText(str);//test changing

	  }
	


}
