package UI;
import javax.swing.*;
import Console.Bank;
import Exception.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegisterPanel extends JFrame{
	JLabel password=new JLabel("密  码：");
	JPasswordField passwordField=new JPasswordField();
	JLabel password2=new JLabel("确认密码：");
	JPasswordField passwordField2=new JPasswordField();
	JLabel name=new JLabel("姓  名：");
	JTextField nameField=new JTextField();
	JLabel personalID=new JLabel("身份证号：");
	JTextField personalIDField=new JTextField();
	JLabel email=new JLabel("email:");
	JTextField emailFeild=new JTextField();
	JRadioButton ceiling=new JRadioButton("信用账户");
	JRadioButton loan=new JRadioButton("贷款账户");
	JButton button_1=new JButton("保存");
	JButton button_2=new JButton("取消");
	JPanel panel=new JPanel();
	public static double credit=Double.MIN_VALUE;
	public RegisterPanel(long id) {
		this.setLayout(null);
		panel.setLayout(new GridLayout(8, 2,20,5));
		panel.add(password);panel.add(passwordField);
		panel.add(password2);panel.add(passwordField2);
		panel.add(name);panel.add(nameField);
		panel.add(personalID);panel.add(personalIDField);
		panel.add(email);panel.add(emailFeild);
		panel.add(ceiling);panel.add(new JPanel());
		panel.add(loan);panel.add(new JLabel());
		panel.add(button_1);panel.add(button_2);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(passwordField.getPassword()).equals("")||nameField.getText().equals("")||personalIDField.getText().equals("")||emailFeild.getText().equals("")) {
					new Dialog("请完整信息");
					return;
				}
				if(!String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField2.getPassword()))) {
					new Dialog("密码不一致");
					return;
				}
				Bank bank=Bank.getBank();
				try {
					if(id!=0) {
						if(!ceiling.isSelected()||credit!=Double.MIN_VALUE) {
							if(bank.modify(id,Long.valueOf(String.valueOf(passwordField.getPassword())), nameField.getText(),personalIDField.getText(), emailFeild.getText(), ceiling.isSelected(), loan.isSelected(),credit))
								new Dialog("修改成功！");
							else
								new Dialog("修改失败！");
							credit=Double.MIN_VALUE;
							dispose();
						}else {
							new CreditDialog();
						}
					}else{
						if(!ceiling.isSelected()||credit!=Double.MIN_VALUE) { 
							bank.register(Long.valueOf(String.valueOf(passwordField.getPassword())), nameField.getText(),personalIDField.getText(), emailFeild.getText(), ceiling.isSelected(), loan.isSelected(),credit);
							new Dialog("注册成功！");
							credit=Double.MIN_VALUE;
							dispose();
						}else {
							new CreditDialog();
						}
					}
				}catch (Exception e1) {
					new Dialog("注册失败,请检查输入是否有效！");
					credit=Double.MIN_VALUE;
				}
			}
		});
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainPanel mainPanel=new MainPanel();
			}
		});
		panel.setBounds(20, 20, 250, 330);
		this.add(panel);
		
		this.setResizable(false);
		this.setTitle("注册");
		this.setBounds(20, 20, 300, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
