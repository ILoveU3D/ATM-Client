package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import console.Bank;
import exception.*;
public class LogInPanel extends JFrame{
	JLabel idJLabel=new JLabel("�û�����");
	JTextField idField=new JTextField();
	JLabel psw=new JLabel("��  �룺");
	JPasswordField pswField=new JPasswordField();
	JButton button_1=new JButton("��½");
	JButton button_2=new JButton("ȡ��");
	JPanel panel=new JPanel();
	JPanel panel_2=new JPanel();
	public LogInPanel() {
		panel.setLayout(null);
		idJLabel.setBounds(35, 20, 100, 20);
		idField.setBounds(135, 20, 100, 20);
		psw.setBounds(35, 60, 100, 20);
		pswField.setBounds(135, 60, 100, 20);
		panel.add(idJLabel);
		panel.add(idField);
		panel.add(psw);
		panel.add(pswField);
		panel_2.setLayout(new FlowLayout());
		panel_2.add(button_1);
		panel_2.add(button_2);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Bank bank=Bank.getBank();
				try {
					bank.login(Long.valueOf(idField.getText()), Long.valueOf(String.valueOf(pswField.getPassword())));
					dispose();
				} catch (LogInException e) {
					new Dialog("��½ʧ�ܣ�");
				} catch(NumberFormatException e) {
					new Dialog("��������");
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
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
		this.add(panel_2, BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.setTitle("��¼");
		this.setBounds(300,300,300,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
