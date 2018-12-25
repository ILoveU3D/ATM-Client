package ui;
import javax.swing.*;

import console.Bank;
import exception.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegisterPanel extends JFrame{
	JLabel password=new JLabel("��  �룺");
	JPasswordField passwordField=new JPasswordField();
	JLabel password2=new JLabel("ȷ�����룺");
	JPasswordField passwordField2=new JPasswordField();
	JLabel name=new JLabel("��  ����");
	JTextField nameField=new JTextField();
	JLabel personalID=new JLabel("���֤�ţ�");
	JTextField personalIDField=new JTextField();
	JLabel email=new JLabel("email:");
	JTextField emailFeild=new JTextField();
	JRadioButton ceiling=new JRadioButton("�����˻�");
	JRadioButton loan=new JRadioButton("�����˻�");
	JButton button_1=new JButton("����");
	JButton button_2=new JButton("ȡ��");
	JPanel panel=new JPanel();
	Bank bank=Bank.getBank();
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
					new Dialog("��������Ϣ");
					return;
				}
				if(!String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField2.getPassword()))) {
					new Dialog("���벻һ��");
					return;
				}
				try {
					if(id!=0) {
						if(!ceiling.isSelected()||Constant.credit!=Double.MIN_VALUE) {
							if(bank.modify(id,Long.valueOf(String.valueOf(passwordField.getPassword())), nameField.getText(),personalIDField.getText(), emailFeild.getText(), ceiling.isSelected(), loan.isSelected(),Constant.credit)) {
								new Dialog("�޸ĳɹ���");
							}
							else
								new Dialog("�޸�ʧ�ܣ�");
							Constant.credit=Double.MIN_VALUE;
							dispose();
						}else {
							new CreditDialog();
						}
					}else{
						if(!ceiling.isSelected()||Constant.credit!=Double.MIN_VALUE) { 
							bank.register(Long.valueOf(String.valueOf(passwordField.getPassword())), nameField.getText(),personalIDField.getText(), emailFeild.getText(), ceiling.isSelected(), loan.isSelected(),Constant.credit);
							new Dialog("ע��ɹ���");
							Constant.credit=Double.MIN_VALUE;
							dispose();
						}else {
							new CreditDialog();
						}
					}
				}catch (NumberFormatException e1) {
					new Dialog("ע��ʧ��,���������Ƿ���Ч��");
					Constant.credit=Double.MIN_VALUE;
				}catch (RegisterException e2) {
					new Dialog("ע��ʧ�ܣ�");
				}
			}
		});
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(id==0) {
					dispose();
					MainPanel mainPanel=new MainPanel();
				}else {
					dispose();
					try {
						bank.login(id);
					} catch (LogInException e1) {
						new Dialog("�����쳣");
					}
				}
			}
		});
		panel.setBounds(20, 20, 250, 330);
		this.add(panel);
		
		this.setResizable(false);
		this.setTitle("ע��");
		this.setBounds(20, 20, 300, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
