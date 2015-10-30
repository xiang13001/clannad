import javax.swing.JComponent;
import javax.swing.JLayeredPane;


public class RoomPreview extends JComponent
{
	Controller ctrl;
	public RoomPreview(Controller ctrl) 
	{
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		
		RoomPanel rp = new RoomPanel(ctrl);
		rp.setBounds(0, 0, 1000, 682);
		add(rp);
		
		RightPanel rg = new RightPanel(ctrl);
		rg.setBounds(999, 0, 265, 682);
		add(rg);
	}

}