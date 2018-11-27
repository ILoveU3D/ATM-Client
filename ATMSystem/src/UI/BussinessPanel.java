package UI;
import javax.swing.*;
import Console.*;
import java.awt.*;
public class BussinessPanel extends JFrame{
	JLabel id=new JLabel();
	JLabel name=new JLabel();
	JLabel email=new JLabel();
	JLabel personId=new JLabel();
	JLabel kind=new JLabel();
	JLabel balance=new JLabel();
	JLabel credit=new JLabel();
	JLabel loan=new JLabel();
	JButton button=new JButton("存款");
	JButton button2=new JButton("取款");
	JButton button3=new JButton("修改个人信息");
	JButton button4=new JButton("退出登录");
	JButton button5=new JButton("贷款");
	public BussinessPanel(Account account) {
		id.setText(""+account.getId());
		id.setFont(Constant.dialog_1);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBorder(BorderFactory.createTitledBorder("个人账号"));
		name.setText(account.getName());
		name.setFont(Constant.dialog_1);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBorder(BorderFactory.createTitledBorder("姓名"));
		personId.setText(""+account.getPersonId());
		personId.setFont(Constant.dialog_1);
		personId.setHorizontalAlignment(SwingConstants.CENTER);
		personId.setBorder(BorderFactory.createTitledBorder("身份证号"));
		email.setText(account.getEmail());
		email.setFont(Constant.dialog_1);
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setBorder(BorderFactory.createTitledBorder("邮箱"));
		balance.setText(""+account.getBalance());
		balance.setFont(Constant.dialog_1);
		balance.setForeground(Color.RED);
		balance.setHorizontalAlignment(SwingConstants.CENTER);
		balance.setBorder(BorderFactory.createTitledBorder("余额"));
		credit.setFont(Constant.dialog_1);
		credit.setHorizontalAlignment(SwingConstants.CENTER);
		credit.setBorder(BorderFactory.createTitledBorder("信用额度"));
		loan.setFont(Constant.dialog_1);
		loan.setHorizontalAlignment(SwingConstants.CENTER);
		loan.setBorder(BorderFactory.createTitledBorder("贷款额"));
		kind.setFont(Constant.dialog_1);
		kind.setHorizontalAlignment(SwingConstants.CENTER);
		kind.setBorder(BorderFactory.createTitledBorder("账户类型"));
		if(account instanceof SavingAccount) {
			if(account instanceof LoanSavingAccount) {
				loan.setText(""+((LoanSavingAccount) account).getLoan());
				kind.setText("贷款储蓄账户");
			}else {
				loan.setText("————");
				kind.setText("普通储蓄账户");
			}
			credit.setText("信用额度：————");
		}else {
			if(account instanceof LoanCreditAccount) {
				loan.setText(""+((LoanCreditAccount) account).getLoan());
				kind.setText("贷款信用账户");
			}else {
				loan.setText("————");
				kind.setText("普通信用账户");
			}
			credit.setText(""+((CreditAccount)account).getCeiling());
		}
		button.setFont(Constant.button_2);
		button.setBackground(Color.GRAY);
		button.setForeground(Color.WHITE);
		button.setBorder(BorderFactory.createEtchedBorder());
		button2.setFont(Constant.button_2);
		button2.setBackground(Color.GRAY);
		button2.setForeground(Color.WHITE);
		button2.setBorder(BorderFactory.createEtchedBorder());
		button3.setFont(Constant.button_2);
		button3.setForeground(Color.WHITE);
		button3.setHorizontalAlignment(SwingConstants.CENTER);
		button3.setBackground(Color.GRAY);
		button3.setBorder(BorderFactory.createEtchedBorder());
		button4.setFont(Constant.button_2);
		button4.setBackground(Color.GRAY);
		button4.setForeground(Color.WHITE);
		button4.setBorder(BorderFactory.createEtchedBorder());
		button5.setFont(Constant.button_2);
		button5.setBackground(Color.GRAY);
		button5.setForeground(Color.WHITE);
		button5.setBorder(BorderFactory.createEtchedBorder());
		JPanel panel=new JPanel(new GridLayout(8,1));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.WHITE);
		JPanel panel2=new JPanel(new GridLayout(7, 1,0,15));
		panel2.setBackground(Color.DARK_GRAY);
		panel.add(id);
		panel.add(kind);
		panel.add(name);
		panel.add(personId);
		panel.add(email);
		panel.add(balance);
		panel.add(credit);
		panel.add(loan);
		panel2.add(button);
		panel2.add(button2);
		panel2.add(button5);
		panel2.add(new JPanel() {@Override
		public void setBackground(Color arg0) {
			super.setBackground(Color.DARK_GRAY);
		}});
		panel2.add(new JPanel() {@Override
		public void setBackground(Color arg0) {
			super.setBackground(Color.DARK_GRAY);
		}});
		panel2.add(button3);
		panel2.add(button4);
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
		this.add(panel2, BorderLayout.WEST);
		this.setTitle("欢迎，"+account.getName()+"("+kind.getText()+")");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
