import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class ViewStaff extends JComponent {

	private Controller ctrl;
	private JTable jtbStf;
	int[] SelcCols,SelcRows,PSelcCols,PSelcRows;

	public ViewStaff(Controller ctrl) {
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		Vector<String> Name = new Vector<String>(Arrays.asList("Staff ID","Staff Name","Staff IC","Job","Age","Contact","User","Passowrd","Security question","Answer"));
		Vector<Vector> StfSet = ctrl.getStaffSet();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 29, 1228, 541);
		add(scrollPane);
		
		jtbStf = new JTable(StfSet,Name);
		jtbStf.setCellSelectionEnabled(true);
		scrollPane.setViewportView(jtbStf);
		
		JButton jbtDeleteRow = new JButton("Delete Row");
		jbtDeleteRow.setBounds(1063, 591, 158, 29);
		add(jbtDeleteRow);
		
		new DynamicTable(jtbStf,jbtDeleteRow,ctrl,1);
	}

}
