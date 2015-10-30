import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListDataListener;

public class ComboBoxDatePicker extends JComponent implements ItemListener {

    private static final long serialVersionUID = 1L;
    private JComboBox myMonth;
    private JComboBox myYear;
    private Collection<ItemListener> myListeners;
    
    public ComboBoxDatePicker()
    {
    	//setOpaque(true);
    }

    public ComboBoxDatePicker(String name) {
        this(name, new Date(System.currentTimeMillis()));
    }

    public ComboBoxDatePicker(String name, Date date) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        myListeners = new HashSet<ItemListener>();

        myMonth = new JComboBox(new RangeModel(1, 12));
        myYear = new JComboBox(new RangeModel(2000, 2020));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        myMonth.setSelectedItem(calendar.get(Calendar.MONTH) + 1);
        myYear.setSelectedItem(calendar.get(Calendar.YEAR));

        myMonth.addItemListener(this);
        myYear.addItemListener(this);

        add(new JLabel(name));   
        add(myMonth);
        add(myYear);

        doLayout();
    }

    public void setEnabled(boolean enabled) {
        myMonth.setEnabled(enabled);
        myYear.setEnabled(enabled);
    }

    public Date getDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, (Integer) myMonth.getSelectedItem() - 1);
        calendar.set(Calendar.YEAR, (Integer) myYear.getSelectedItem());
        return calendar.getTime();
    }
    
    public String getMonthYear()
    {
    	 String month[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    	 System.out.println("Month = "+myMonth.getSelectedItem());
    	 return month[myMonth.getSelectedIndex()]+" "+(Integer) myYear.getSelectedItem();
    }

    private static class RangeModel implements ComboBoxModel {

        private int myMin;
        private int myMax;
        private Object mySelection;

        public RangeModel(int min, int max) {
            myMin = Math.min(min, max);
            myMax = Math.max(min, max);
            mySelection = myMin;
        }
        public Object getSelectedItem() {
            return mySelection;
        }
        public void setSelectedItem(Object anItem) {
            mySelection = anItem;
        }
        public int getSize() {
            return myMax - myMin + 1;
        }
        public Object getElementAt(int index) {
            return myMin + index;
        }
        public void addListDataListener(ListDataListener l) {
        }
        public void removeListDataListener(ListDataListener l) {
        }
    }

    public void addItemListener(ItemListener l) {
        myListeners.add(l);
    }

    public void itemStateChanged(ItemEvent e) {
        for (ItemListener listener : myListeners) {
            listener.itemStateChanged(e);
        }
    }
}