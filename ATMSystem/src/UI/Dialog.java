package UI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Dialog extends JDialog{
	JLabel jLabel=new JLabel();
	JButton ok=new JButton("确定");
	public Dialog(String src) {
		jLabel.setText(src);
		jLabel.setSize(200,100);
		jLabel.setFont(Constant.dialog_1);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setVerticalAlignment(SwingConstants.CENTER);
		JPanel panel=new JPanel(new FlowLayout());
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(ok);
		this.setLayout(new BorderLayout());
		this.add(jLabel,BorderLayout.CENTER);
		this.add(panel, BorderLayout.SOUTH);
		this.setTitle("系统提示");
		this.setBounds(20, 20, 300, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
