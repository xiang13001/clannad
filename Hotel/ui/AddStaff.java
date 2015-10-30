import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddStaff extends RecBack{

	private Controller ctrl;
	private JTextField jtfName;
	private JTextField jtfIC;
	private JTextField jtfJob;
	private JTextField jtfAge;
	private JTextField jtfContact;
	private JTextField jtfUser;
	private JTextField jtfPassword;
	private JTextField jtfAns;
	private Staff staff;
	private JComboBox jcbSecurity;

	/**
	 * Create the panel.
	 */
	public AddStaff(Controller ctrl) {
		super.setWrapper(90, 80, 1084, 522);
		this.ctrl=ctrl;
		setLayout(null);
		setBounds(0, 0, 1264, 682);
		staff = new Staff();
		
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblStaffId.setBounds(162, 204, 135, 30);
		add(lblStaffId);
		
		JLabel lblStaffName = new JLabel("Staff Name");
		lblStaffName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblStaffName.setBounds(162, 244, 135, 30);
		add(lblStaffName);
		
		JLabel lblStaff = new JLabel("Staff IC");
		lblStaff.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblStaff.setBounds(162, 284, 118, 30);
		add(lblStaff);
		
		JLabel lblJobTitle = new JLabel("Job Title");
		lblJobTitle.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblJobTitle.setBounds(162, 324, 135, 30);
		add(lblJobTitle);
		
		JLabel lblNewLabel = new JLabel("Age");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblNewLabel.setBounds(162, 368, 96, 30);
		add(lblNewLabel);
		
		JLabel lblContactNo = new JLabel("Contact No");
		lblContactNo.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblContactNo.setBounds(162, 408, 135, 30);
		add(lblContactNo);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblUsername.setBounds(610, 284, 135, 30);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblPassword.setBounds(610, 324, 118, 30);
		add(lblPassword);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question");
		lblSecurityQuestion.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblSecurityQuestion.setBounds(610, 204, 192, 30);
		add(lblSecurityQuestion);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		lblAnswer.setBounds(610, 244, 118, 30);
		add(lblAnswer);
		
		jtfName = new JTextField();
		jtfName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jtfName.setBounds(352, 244, 188, 28);
		add(jtfName);
		jtfName.setColumns(10);
		
		jtfIC = new JTextField();
		jtfIC.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jtfIC.setBounds(352, 284, 188, 28);
		add(jtfIC);
		jtfIC.setColumns(10);
		
		jtfJob = new JTextField();
		jtfJob.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jtfJob.setBounds(352, 324, 188, 28);
		add(jtfJob);
		jtfJob.setColumns(10);
		
		jtfAge = new JTextField();
		jtfAge.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jtfAge.setBounds(352, 368, 188, 28);
		add(jtfAge);
		jtfAge.setColumns(10);
		
		jtfContact = new JTextField();
		jtfContact.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jtfContact.setBounds(352, 408, 188, 28);
		add(jtfContact);
		jtfContact.setColumns(10);
		
		jtfUser = new JTextField();
		jtfUser.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jtfUser.setBounds(845, 286, 188, 28);
		add(jtfUser);
		jtfUser.setColumns(10);
		
		jtfPassword = new JTextField();
		jtfPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jtfPassword.setBounds(845, 326, 188, 28);
		add(jtfPassword);
		jtfPassword.setColumns(10);
		
		String[] ques = {"What is your fravote food ?",
				"What is your mother name? ",
				"What is your frist teacher name? ","What street name you born? "};
		jcbSecurity = new JComboBox(ques);
		jcbSecurity.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jcbSecurity.setBounds(845, 204, 283, 28);
		add(jcbSecurity);
		
		jtfAns = new JTextField();
		jtfAns.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jtfAns.setBounds(845, 244, 241, 28);
		add(jtfAns);
		jtfAns.setColumns(10);
		
		JLabel lblCusID = new JLabel(ctrl.getNewStfID());
		lblCusID.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lblCusID.setBounds(352, 204, 240, 30);
		add(lblCusID);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		btnNewButton.setBounds(1066, 553, 93, 30);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				staff.setStf_ID(lblCusID.getText());
				staff.setStf_Name(jtfName.getText());
				staff.setIC(jtfIC.getText());
				staff.setAge(Integer.parseInt(jtfAge.getText()));
				staff.setJob(jtfJob.getText());
				staff.setContact(jtfContact.getText());
				staff.setUser(jtfUser.getText());
				staff.setPassword(jtfPassword.getText());
				staff.setQues(String.valueOf(jcbSecurity.getSelectedItem()));
				staff.setAns(jtfAns.getText());
				ctrl.setNewStaff(staff);
				ctrl.BackPanel(new AddStaff(ctrl));
			}
		});
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		btnNewButton_1.setBounds(958, 553, 93, 30);
		add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.BackPanel(new AddStaff(ctrl));
				
			}
		});
		
		JLabel lblAddStaff = new JLabel("ADD STAFF");
		lblAddStaff.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 22));
		lblAddStaff.setBounds(128, 114, 130, 46);
		add(lblAddStaff);
	}
}
