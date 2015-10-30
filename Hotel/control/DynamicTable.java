

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class DynamicTable implements ListSelectionListener,TableColumnModelListener,ActionListener
{
	JTable jtb;
	Controller ctrl;
	int[] SelcCols,SelcRows,PSelcCols,PSelcRows,SlcCols,SlcRows,PSlcCols,PSlcRows;
	int SelcCol,SelcRow,PSelcCol,PSelcRow,start,type,SlcCol,SlcRow,PSlcCol,PSlcRow;
	String data;
	ListSelectionModel cellSelc;
	TableColumnModel columnModel;
	public DynamicTable(JTable jtb,JButton jbtDelete,Controller ctrl,int type)
	{
		this.jtb=jtb;
		this.ctrl=ctrl;
		this.type=type;
		cellSelc = jtb.getSelectionModel();
	    cellSelc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelc.addListSelectionListener(this);
	    columnModel = jtb.getColumnModel();
	    columnModel.addColumnModelListener(this);
	    jbtDelete.addActionListener(this);
	}

	@Override
	public void columnAdded(TableColumnModelEvent arg0) {
		
	}

	@Override
	public void columnMarginChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void columnMoved(TableColumnModelEvent arg0) {
		
		
	}

	@Override
	public void columnRemoved(TableColumnModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void columnSelectionChanged(ListSelectionEvent arg0) {
		PSelcCols = SelcCols;
		SelcCols = jtb.getSelectedColumns();
		PSelcRows = SelcRows;
		SelcRows = jtb.getSelectedRows();
		PSelcCol = SelcCol;
		SelcCol = jtb.getSelectedColumn();
		PSelcRow = SelcRow;
		SelcRow = jtb.getSelectedRow();
		
		if(PSelcRow==SelcRow&&PSelcRows!=null&&cellSelc.getValueIsAdjusting()==true)
		{
			data=(jtb.getValueAt(PSelcRows[0], PSelcCols[0])).toString();
			ctrl.tableCtrl(type, PSelcCol, PSelcRow, data,jtb.getValueAt(PSelcRow, 0).toString());
			System.out.print(jtb.getValueAt(PSelcRow, 0));
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		PSlcCols = SlcCols;
		SlcCols = jtb.getSelectedColumns();
		PSlcRows = SlcRows;
		SlcRows = jtb.getSelectedRows();
		PSlcCol = SlcCol;
		SlcCol = jtb.getSelectedColumn();
		PSlcRow = SlcRow;
		SlcRow = jtb.getSelectedRow();
		
		if(PSlcCols!=null&&cellSelc.getValueIsAdjusting()==true)
		{
			data=(jtb.getValueAt(PSlcRows[PSlcRows.length-1], PSlcCols[PSlcCols.length-1])).toString();
			ctrl.tableCtrl(type, PSlcCol, PSlcRow, data,jtb.getValueAt(PSlcRow, 0).toString());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ctrl.rowDel(type, SlcRow,jtb.getValueAt(PSlcRow, 0).toString());
		((DefaultTableModel)jtb.getModel()).removeRow(SlcRow);
		jtb.revalidate();
		jtb.repaint();	
		
		
	}

}
