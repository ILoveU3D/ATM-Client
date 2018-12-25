package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class MainPanel extends JFrame{
	JLabel welcome=new JLabel("»¶Ó­µÇÂ½ATMÏµÍ³");
	JButton button_1=new JButton("µÇÂ½");
	JButton button_2=new JButton("×¢²á");
	JButton button_3=new JButton("ÍË³ö");
	JPanel panel_1=new JPanel();
	JPanel panel_2=new JPanel();
	public MainPanel() {
		welcome.setForeground(Color.GRAY);
		welcome.setFont(Constant.WELCOME);
		panel_1.setLayout(new FlowLayout());
		panel_1.add(welcome);
		button_1.setFont(Constant.BUTTON1);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LogInPanel();
			}
		});
		button_2.setFont(Constant.BUTTON1);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegisterPanel(0);
			}
		});
		button_3.setFont(Constant.BUTTON1);
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_2.setLayout(new FlowLayout());
		panel_2.add(button_1);
		panel_2.add(button_2);
		panel_2.add(button_3);
		this.setBounds(20, 20, 300, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(3, 1));
		this.add(new JPanel());
		this.add(panel_1);
		this.add(panel_2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
