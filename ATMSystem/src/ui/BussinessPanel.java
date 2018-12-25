package ui;
import javax.swing.*;

import console.*;
import exception.ATMException;
import exception.BalanceNotEnoughException;
import exception.LoanException;
import ui.Dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	JButton button6=new JButton("还贷");
	JButton button7=new JButton("转账");
	Bank bank=Bank.getBank();
	JFrame frame=new JFrame("请输入");
	JLabel label_f=new JLabel("请输入金额:		");
	JTextField field_f=new JTextField();
	JButton button_f=new JButton("确认");
	JButton button_f_cannel=new JButton("取消");
	JFrame frame2=new JFrame("转账");
	JLabel label_tr_obj=new JLabel("请输入目标账号：		");
	JLabel label_tr_money=new JLabel("请输入金额：		");
	JTextField field_tr_obj=new JTextField();
	JTextField field_tr_money=new JTextField();
	JButton button_tr=new JButton("开始转账");
	JButton button_tr_cancel=new JButton("取消");
	String state="";
	long tempId;
	public BussinessPanel(Account account) {
		id.setText(""+account.getId());
		id.setFont(Constant.DIALOG1);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBorder(BorderFactory.createTitledBorder("个人账号"));
		name.setText(account.getName());
		name.setFont(Constant.DIALOG1);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBorder(BorderFactory.createTitledBorder("姓名"));
		personId.setText(""+account.getPersonId());
		personId.setFont(Constant.DIALOG1);
		personId.setHorizontalAlignment(SwingConstants.CENTER);
		personId.setBorder(BorderFactory.createTitledBorder("身份证号"));
		email.setText(account.getEmail());
		email.setFont(Constant.DIALOG1);
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setBorder(BorderFactory.createTitledBorder("邮箱"));
		balance.setText(""+account.getBalance());
		balance.setFont(Constant.DIALOG1);
		balance.setForeground(Color.RED);
		balance.setHorizontalAlignment(SwingConstants.CENTER);
		balance.setBorder(BorderFactory.createTitledBorder("余额"));
		credit.setFont(Constant.DIALOG1);
		credit.setHorizontalAlignment(SwingConstants.CENTER);
		credit.setBorder(BorderFactory.createTitledBorder("信用额度"));
		loan.setFont(Constant.DIALOG1);
		loan.setHorizontalAlignment(SwingConstants.CENTER);
		loan.setBorder(BorderFactory.createTitledBorder("贷款额"));
		kind.setFont(Constant.DIALOG1);
		kind.setHorizontalAlignment(SwingConstants.CENTER);
		kind.setBorder(BorderFactory.createTitledBorder("账户类型"));
		if(account instanceof SavingAccount) {
			if(account instanceof LoanSavingAccount) {
				loan.setText(""+((LoanSavingAccount) account).getLoan());
				kind.setText(Constant.LOAN_SAVING_ACCOUNT);
			}else {
				loan.setText("————");
				kind.setText(Constant.SAVING_ACCOUNT);
				button5.setEnabled(false);
				button6.setEnabled(false);
			}
			credit.setText("信用额度：————");
		}else {
			if(account instanceof LoanCreditAccount) {
				loan.setText(""+((LoanCreditAccount) account).getLoan());
				kind.setText(Constant.lOAN_CREDIT_ACCOUNT);
			}else {
				loan.setText("————");
				kind.setText(Constant.CREDIT_ACCOUNT);
				button5.setEnabled(false);
				button6.setEnabled(false);
			}
			credit.setText(""+((CreditAccount)account).getCeiling());
		}
		button.setFont(Constant.BUTTON2);
		button.setBackground(Color.GRAY);
		button.setForeground(Color.WHITE);
		button.setBorder(BorderFactory.createEtchedBorder());
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state=Constant.DEPOIST;
				frame.setVisible(true);
			}
		});
		button2.setFont(Constant.BUTTON2);
		button2.setBackground(Color.GRAY);
		button2.setForeground(Color.WHITE);
		button2.setBorder(BorderFactory.createEtchedBorder());
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state=Constant.WITHDRAW;
				frame.setVisible(true);
			}
		});
		button3.setFont(Constant.BUTTON2);
		button3.setForeground(Color.WHITE);
		button3.setHorizontalAlignment(SwingConstants.CENTER);
		button3.setBackground(Color.GRAY);
		button3.setBorder(BorderFactory.createEtchedBorder());
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RegisterPanel(account.getId());
				dispose();
			}
		});
		button4.setFont(Constant.BUTTON2);
		button4.setBackground(Color.GRAY);
		button4.setForeground(Color.WHITE);
		button4.setBorder(BorderFactory.createEtchedBorder());
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MainPanel();
				dispose();
			}
		});
		button5.setFont(Constant.BUTTON2);
		button5.setBackground(Color.GRAY);
		button5.setForeground(Color.WHITE);
		button5.setBorder(BorderFactory.createEtchedBorder());
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state=Constant.LOAN;
				frame.setVisible(true);
			}
		});
		button6.setFont(Constant.BUTTON2);
		button6.setBackground(Color.GRAY);
		button6.setForeground(Color.WHITE);
		button6.setBorder(BorderFactory.createEtchedBorder());
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state=Constant.PAY_LOAN;
				frame.setVisible(true);
			}
		});
		button7.setFont(Constant.BUTTON2);
		button7.setBackground(Color.GRAY);
		button7.setForeground(Color.WHITE);
		button7.setBorder(BorderFactory.createEtchedBorder());
		button7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state=Constant.TRANSFER;
				frame2.setVisible(true);
			}
		});
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
		panel2.add(button6);
		panel2.add(button7);
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
		
		label_f.setFont(Constant.DIALOG1);
		button_f.setFont(Constant.BUTTON2);
		button_f_cannel.setFont(Constant.BUTTON2);
		button_f.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					switch (state) {
					case Constant.DEPOIST:
						bank.deposit(account, Double.valueOf(field_f.getText()));
						break;
					case Constant.WITHDRAW:
						bank.withdraw(account, Double.valueOf(field_f.getText()));
						break;
					case Constant.LOAN:
						bank.loan(account, Double.valueOf(field_f.getText()));
						break;
					case Constant.PAY_LOAN:
						bank.payLoan(account, Double.valueOf(field_f.getText()));
						break;
					}
					new BussinessPanel(account);
					frame.dispose();
					dispose();
				} catch (NumberFormatException e) {
					new Dialog("输入有误！");
					frame.dispose();
				} catch (BalanceNotEnoughException e) {
					new Dialog("余额或信用额度不足！");
					frame.dispose();
				} catch (LoanException e) {
					new Dialog("贷款异常");
					frame.dispose();
				}
			}
		});
		button_f_cannel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		label_tr_obj.setFont(Constant.DIALOG2);
		label_tr_money.setFont(Constant.DIALOG2);
		button_tr.setFont(Constant.BUTTON1);
		button_tr_cancel.setFont(Constant.BUTTON1);
		
		JPanel panel_1=new JPanel(new GridLayout(1,2,5,5));
		JPanel panel_2=new JPanel();
		panel_1.add(label_f);
		panel_1.add(field_f);
		panel_2.add(button_f);
		panel_2.add(button_f_cannel);
		frame.setTitle("请输入");
		frame.setLayout(new BorderLayout());
		frame.add(panel_1,BorderLayout.CENTER);
		frame.add(panel_2,BorderLayout.SOUTH);
		frame.setSize(400, 120);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(false);
		
		JPanel panel_3=new JPanel(new GridLayout(2,2,5,5));
		JPanel panel_4=new JPanel();
		panel_3.add(label_tr_obj);panel_3.add(field_tr_obj);
		panel_3.add(label_tr_money);panel_3.add(field_tr_money);
		panel_4.add(button_tr);panel_4.add(button_tr_cancel);
		button_tr.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					bank.transfer(account,Long.valueOf(field_tr_obj.getText()), Double.valueOf(field_tr_money.getText()));
					frame2.dispose();
					new BussinessPanel(account);
					dispose();
					new Dialog("转账成功！");
				} catch (NumberFormatException e) {
					new Dialog("输入有误");
				} catch (BalanceNotEnoughException e) {
					new Dialog("余额不足");
				} catch (ATMException e) {
					new Dialog("账户不存在，转账失败");
				}
			}
		});
		button_tr_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
			}
		});
		frame2.setLayout(new BorderLayout());
		frame2.add(panel_3,BorderLayout.CENTER);
		frame2.add(panel_4, BorderLayout.SOUTH);
		frame2.setSize(400, 170);
		frame2.setLocationRelativeTo(null);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setVisible(false);
	}
}
