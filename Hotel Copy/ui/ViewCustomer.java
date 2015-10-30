import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ViewCustomer extends JComponent {

	private Controller ctrl;
	private JTable jtbCus;
	int[] SelcCols,SelcRows,PSelcCols,PSelcRows;

	public ViewCustomer(Controller ctrl) {
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		Vector<String> Name = new Vector<String>(Arrays.asList("Customer ID","Customer IC","Passport","Customer Name","Age","Contact","Address","Email"));
		Vector<Vector> StfSet = ctrl.getCustomerSet();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 29, 1228, 541);
		add(scrollPane);
		
		jtbCus = new JTable(StfSet,Name);
		jtbCus.setCellSelectionEnabled(true);
		scrollPane.setViewportView(jtbCus);
		
		JButton jbtDeleteRow = new JButton("Delete Row");
		jbtDeleteRow.setBounds(1063, 591, 158, 29);
		add(jbtDeleteRow);
		
		new DynamicTable(jtbCus,jbtDeleteRow,ctrl,2);
	}

}
