package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CreditDialog extends JDialog{
	JLabel label=new JLabel("您注册的是信用账户，请设置您的信用额度");
	JLabel label2=new JLabel("信用额度：");
	TextField textField=new TextField();
	JButton button=new JButton("确认");
	public CreditDialog() {
		label.setFont(Constant.DIALOG2);
		label2.setFont(Constant.DIALOG1);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(30,10, 100, 25);
		textField.setBounds(150,10 ,150, 25);
		textField.setFont(Constant.DIALOG1);
		button.setFont(Constant.BUTTON1);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Constant.credit=Double.valueOf(textField.getText());
					dispose();
				} catch (Exception e2) {
					new Dialog("输入有误!");
				}
			}
		});
		JPanel panel=new JPanel(new BorderLayout(10,15));
		JPanel panel2=new JPanel(null);
		JPanel panel3=new JPanel(new FlowLayout());
		panel.add(label,BorderLayout.NORTH);
		panel2.add(label2);panel2.add(textField);
		panel.add(panel2,BorderLayout.CENTER);
		panel3.add(button);
		panel.add(panel3,BorderLayout.SOUTH);
		this.add(panel);
		this.setSize(450, 167);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
